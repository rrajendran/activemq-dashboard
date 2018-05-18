package com.capella.mvc.example.domain;

/**
 * Copyright 2018 (c) Mastek UK Ltd
 * <p>
 * Created on : 18/05/2018
 *
 * @author Ramesh Rajendran
 */
@lombok.Data
@lombok.Builder
@lombok.AllArgsConstructor
@lombok.NoArgsConstructor

public class QueueMessageDto {

    private String queueName;
    private java.util.List<com.capella.mvc.example.domain.MessageDto> messages;

}
