package com.unn.serverNetwork.model;

import com.arangodb.springframework.annotation.Document;
import com.arangodb.springframework.annotation.Field;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.springframework.data.annotation.Id;

import static com.unn.serverNetwork.model.CollectionsNames.INTERFACE;

@Data
@Document(INTERFACE)
public class Interface {

    @Id
    private String id;

    private String name;

    @Field("mac-address")
    @JsonProperty("mac-address")
    private String macAddress;

    @Field("ip-address")
    @JsonProperty("ip-address")
    private String ipAddress;
}
