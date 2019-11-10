package com.unn.serverNetwork.model.repository;

import com.arangodb.ArangoCursor;
import com.arangodb.springframework.annotation.Query;
import com.arangodb.springframework.repository.ArangoRepository;
import com.unn.serverNetwork.model.Link;
import org.springframework.data.repository.query.Param;

public interface LinkRepository extends ArangoRepository<Link, String> {

    @Query("for link IN `link` filter link.`a-ne` == @neName " +
            "|| link.`z-ne` == @neName  return link")
    ArangoCursor<Link> findAllByNeName(@Param("neName")String neName);

    @Query("FOR link IN link FILTER"+
            " (link.`a-ne` == @aNe &&  link.`z-ne` == @zNe &&"+
            " link.`a-interface` == @aInt && link.`z-interface` == @zInt)"+
            "  || (link.`z-ne` == @aNe &&  link.`a-ne` == @zNe &&"+
            " link.`z-interface` == @aInt && link.`a-interface` == @zInt)"+
            " RETURN link" )
    ArangoCursor<Link> findLinkByEndPoints(@Param("aNe")String aNe,
                                           @Param("zNe")String zNe,
                                           @Param("aInt")String aInt,
                                           @Param("zInt")String zInt);

    @Query("for link IN `link` filter (link.`a-ne` == @neName  " +
            "&& link.`a-interface` == @intName) " +
            "|| (link.`z-ne` == @neName && link.`z-interface` == @intName )" +
            "  return link")
    ArangoCursor<Link> findAllByNeNameAndInterName
            (@Param("neName")String neName,
             @Param("intName")String intName );
}
