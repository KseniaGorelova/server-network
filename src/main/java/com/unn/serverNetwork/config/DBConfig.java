package com.unn.serverNetwork.config;

import com.arangodb.ArangoDB;
import com.arangodb.springframework.annotation.EnableArangoRepositories;
import com.arangodb.springframework.config.AbstractArangoConfiguration;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableArangoRepositories(basePackages = {"com.unn.serverNetwork.model"})
@RequiredArgsConstructor
public class DBConfig extends AbstractArangoConfiguration {

    @Value("${arangodb.host}")
    String host;
    @Value("${arangodb.port}")
    int port;
    @Value("${arangodb.user}")
    String user;
    @Value("${arangodb.password}")
    String password;
    @Value("${arangodb.database}")
    String dataBase;


    @Override
    public ArangoDB.Builder arango() {
        return new ArangoDB.Builder().host(host, port)
                .user(user).password(password);
    }
    @Override
    public String database() {
        return dataBase;
    }
}

