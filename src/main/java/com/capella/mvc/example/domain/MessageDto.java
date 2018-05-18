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
public class MessageDto{
    private String messageId;
    private String sourceReferenceNumber;
    private String debugCorrelationId;
    private String nassRef;
    private String portRef;
    private String hoFileRef;
}
