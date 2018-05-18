package com.capella.mvc.example.service;

import javax.jms.Session;

/**
 * Copyright 2018 (c) Mastek UK Ltd
 * <p>
 * Created on : 18/05/2018
 *
 * @author Ramesh Rajendran
 */
public class ActiveMQServiceImpl implements com.capella.mvc.example.service.ActiveMQService {

    @org.springframework.beans.factory.annotation.Autowired
    private Session session;

    @Override
    public void getMessages(String queueName, int pageNumber, int pageSize) throws javax.jms.JMSException {
        javax.jms.Destination destination = session.createQueue(queueName);

        final javax.jms.QueueBrowser queueBrowser = session.createBrowser((javax.jms.Queue) destination);

        final java.util.Enumeration enumeration = queueBrowser.getEnumeration();

        // Elements
        while (enumeration.hasMoreElements()) {
            final Object element = enumeration.nextElement();
            if (element instanceof javax.jms.TextMessage) {
                final javax.jms.TextMessage textMessage = (javax.jms.TextMessage) element;

            }
        }
    }
}
