package com.unn.serverNetwork.model.repository;

import com.arangodb.ArangoCursor;
import com.arangodb.springframework.annotation.Query;
import com.arangodb.springframework.repository.ArangoRepository;
import com.unn.serverNetwork.model.NeToInterface;
import org.springframework.data.repository.query.Param;

public interface NeToIntrRepository extends ArangoRepository<NeToInterface, String> {

    @Query(" for ne IN `ne-to-interface` FILTER ne._to == @intID RETURN ne")
    ArangoCursor<NeToInterface> findNeIdByInterID(@Param("intID") String intID);

    @Query("FOR ne IN `ne-to-interface` FILTER ne._to == @intID REMOVE {  _key: ne._key } IN `ne-to-interface`")
    void removeNeToInterfaceByIntId(@Param("intID")String intID);
}