package com.capella.mvc.example.service;

/**
 * Copyright 2018 (c) Mastek UK Ltd
 * <p>
 * Created on : 18/05/2018
 *
 * @author Ramesh Rajendran
 */
public interface ActiveMQService {

    void getMessages(String queueName, int pageNumber, int pageSize) throws javax.jms.JMSException;
}
