package com.unn.serverNetwork.model;

import com.arangodb.springframework.annotation.Document;
import com.arangodb.springframework.annotation.Relations;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.springframework.data.annotation.Id;

import java.util.List;

import static com.unn.serverNetwork.model.CollectionsNames.NETWORK_ELEMENT;

@Data
@Document(NETWORK_ELEMENT)
public class NetworkElement {
    @Id
    private String id;

    private String name;
    private String type;
    private String vendor;

    @Relations(edges = NeToInterface.class, direction = Relations.Direction.OUTBOUND)
    @JsonProperty("interfaces")
    private List<Interface> interfaces;

}
