package com.unn.serverNetwork.model.repository;

import com.arangodb.ArangoCursor;
import com.arangodb.springframework.annotation.Query;
import com.arangodb.springframework.repository.ArangoRepository;
import com.unn.serverNetwork.model.NetworkElement;
import org.springframework.data.repository.query.Param;

import static com.unn.serverNetwork.model.CollectionsNames.NETWORK_ELEMENT;

public interface NeRepository extends ArangoRepository<NetworkElement, String> {

    @Query("FOR ne IN `" + NETWORK_ELEMENT + "` FILTER ne.name == @neName RETURN ne")
    ArangoCursor<NetworkElement> findByName(@Param("neName")String neName);

}
