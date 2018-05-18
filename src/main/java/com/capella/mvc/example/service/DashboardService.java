package com.capella.mvc.example.service;


/**
 *
 * @author Ramesh Rajendran
 */
public interface DashboardService {

    void sendMessage(String sourceQueue, String messageBody);

    void replayMessages(String sourceQueue, String destinationQueue);
}
