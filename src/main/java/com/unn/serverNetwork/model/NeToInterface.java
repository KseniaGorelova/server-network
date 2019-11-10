package com.unn.serverNetwork.model;

import com.arangodb.springframework.annotation.Edge;
import com.arangodb.springframework.annotation.From;
import com.arangodb.springframework.annotation.To;
import lombok.Data;
import org.springframework.data.annotation.Id;

import static com.unn.serverNetwork.model.CollectionsNames.NE_TO_INTERFACE;

@Data
@Edge(NE_TO_INTERFACE)
public class NeToInterface {
    @Id
    private String id;
    @From
    private final NetworkElement ne;
    @To
    private final Interface anInterface;
}
