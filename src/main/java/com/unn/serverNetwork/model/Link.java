package com.unn.serverNetwork.model;

import com.arangodb.springframework.annotation.Edge;
import com.arangodb.springframework.annotation.Field;
import com.arangodb.springframework.annotation.From;
import com.arangodb.springframework.annotation.To;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;

import static com.unn.serverNetwork.model.CollectionsNames.LINK;

@Edge(LINK)
@Data
@Builder
public class Link {
    @Id
    private String id;

    @From
    private final Interface interfaceA;
    @To
    private final Interface interfaceZ;
    @Field("a-ne")
    @JsonProperty("a-ne")
    private String neAName;

    @Field("z-ne")
    @JsonProperty("z-ne")
    private String neZName;

    @Field("a-interface")
    @JsonProperty("a-interface")
    private String interAName;

    @Field("z-interface")
    @JsonProperty("z-interface")
    private String interZName;

}
