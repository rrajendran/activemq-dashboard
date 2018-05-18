package com.capella.mvc.example.controllers;

import com.capella.mvc.example.service.DashboardService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.MultiValueMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@Controller
public class PageController {
    private static final Logger LOGGER = LoggerFactory
                    .getLogger(PageController.class);
    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private DashboardService dashboardService;


    @RequestMapping(value = { "/{pageName}" })
    public ModelAndView handleRequest(HttpServletRequest request, @PathVariable("pageName") String pageName) {
        ModelAndView modelAndView = new ModelAndView(pageName);
        modelAndView.addObject("dateTime", LocalDateTime.now());
        modelAndView.addObject("pageTitle", pageName.toUpperCase());
        modelAndView.addObject("menus", getMenus());

        return modelAndView;
    }

    @RequestMapping(value = { "/replay.html" }, method = RequestMethod.POST)
    public String replaySubmit(HttpServletRequest request, @RequestBody MultiValueMap<String, String> formData) {
        String sourceQueue = formData.getFirst("sourceQueue");
        String destinationQueue = formData.getFirst("destinationQueue");

        dashboardService.replayMessages(sourceQueue, destinationQueue);
        System.out.println("Form data: " + formData);

        return "redirect:/sqs.html";
    }


    @RequestMapping(value = {"/sendMessage.html"}, method = RequestMethod.POST)
    public ModelAndView sendMessageSubmit(HttpServletRequest request, @RequestBody MultiValueMap<String, String> formData, BindingResult bindingResult) {
        String sourceQueue = formData.getFirst("sourceQueue");
        String messageBody = formData.getFirst("messageBody");
        if(org.apache.commons.lang3.StringUtils.isEmpty(sourceQueue) || org.apache.commons.lang3.StringUtils.isEmpty(messageBody)){
            bindingResult.addError(new org.springframework.validation.ObjectError("sourceQueue", "Source queue is required"));
        }else  if( org.apache.commons.lang3.StringUtils.isEmpty(messageBody)){
            bindingResult.addError(new org.springframework.validation.ObjectError("messageBody", "Message body is required"));
        }else{
            dashboardService.sendMessage(sourceQueue, messageBody);
            return new org.springframework.web.servlet.ModelAndView("redirect:/sendMessage.html");
        }
        ModelAndView modelAndView = new org.springframework.web.servlet.ModelAndView("sendMessage");
        modelAndView.addObject("pageTitle", "Send Message".toUpperCase());
        modelAndView.addObject("menus", getMenus());

        return modelAndView;

    } 

    public Map<String, String> getMenus() {
        Map<String, String> menus = new HashMap<>();
        menus.put("SQS Dashboard", "sqs.html");
        menus.put("Replay Messages", "replay.html");
        menus.put("Send Message", "sendMessage.html");
        return menus;
    }
}