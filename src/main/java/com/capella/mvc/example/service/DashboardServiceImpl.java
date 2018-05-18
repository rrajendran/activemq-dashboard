package com.capella.mvc.example.service;

import org.springframework.stereotype.Component;

/**
 *
 * @author Ramesh Rajendran
 */
@Component
public class DashboardServiceImpl implements DashboardService {

    @Override
    public void sendMessage(String sourceQueue, String messageBody) {

    }

    @Override
    public void replayMessages(String sourceQueue, String destinationQueue) {

    }
}
