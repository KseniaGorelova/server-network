package com.unn.serverNetwork.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
public class ServerMessage {

    private String from;
    private int status;
    private String idNeA;
    private String idNeZ;
    private String topic;

    public String toString()
    {
        return String
                .format("{\"from\":\" %1$-10s\",\"topic\": \"%2$-10s\" \"mesg\": %3$s}",
                        getFrom(), getTopic(),
                        getStatus());
    }
}
