package com.unn.serverNetwork.model.repository;

import com.arangodb.ArangoCursor;
import com.arangodb.springframework.annotation.Query;
import com.arangodb.springframework.repository.ArangoRepository;
import com.unn.serverNetwork.model.Interface;
import org.springframework.data.repository.query.Param;

import static com.unn.serverNetwork.model.CollectionsNames.NE_TO_INTERFACE;

public interface InterfaceRepository extends ArangoRepository<Interface, String> {

    @Query("FOR int IN 1..1 OUTBOUND @neID `" + NE_TO_INTERFACE + "` RETURN int")
    ArangoCursor<Interface> findInterfacesByNeId(@Param("neID") String neId);

    @Query("FOR int IN 1..1 OUTBOUND @neID `" + NE_TO_INTERFACE + "` FILTER int.name == @intName RETURN int")
    ArangoCursor<Interface> findInterfaceByNeIdAndName(@Param("neID") String neId, @Param("intName")String interfaceName);
}
