package com.unn.serverNetwork.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Message
{
    private String from;
    private int status;
    private String idNeA;
    private String idNeZ;
}
