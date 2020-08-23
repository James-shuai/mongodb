package com.cheguansuo.config;


import com.mongodb.*;

import com.mongodb.client.MongoDatabase;
import com.mongodb.client.gridfs.GridFSBucket;
import com.mongodb.client.gridfs.GridFSBuckets;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.core.SimpleMongoDbFactory;
import org.springframework.stereotype.Component;

import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

@Configuration
@Component
public class MongoConfig {

    private static final Logger logger = LoggerFactory.getLogger(MongoConfig.class);





    // 覆盖容器中默认的MongoDbFacotry Bean
    @Bean
    @Autowired
    public MongoDbFactory mongoDbFactory(MongoSettingsProperties properties) throws UnknownHostException {

        // 客户端配置（连接数，副本集群验证）
        MongoClientOptions.Builder builder = new MongoClientOptions.Builder();
        builder.connectionsPerHost(properties.getMaxConnectionsPerHost());
        builder.minConnectionsPerHost(properties.getMinConnectionsPerHost());
        if (properties.getReplicaSet() != null &&!"".equals(properties.getReplicaSet())) {
            builder.requiredReplicaSetName(properties.getReplicaSet());
        }
        builder.threadsAllowedToBlockForConnectionMultiplier(
                properties.getThreadsAllowedToBlockForConnectionMultiplier());
        builder.maxWaitTime(properties.getMaxWaitTime());
        builder.maxConnectionIdleTime(properties.getMaxConnectionIdleTime());
        builder.maxConnectionLifeTime(properties.getMaxConnectionLifeTime());
        builder.connectTimeout(properties.getConnectTimeout());
        builder.socketTimeout(properties.getSocketTimeout());
        builder.alwaysUseMBeans(properties.getAlwaysUseMBeans());
        builder.heartbeatFrequency(properties.getHeartbeatFrequency());
        builder.heartbeatConnectTimeout(properties.getHeartbeatConnectTimeout());
        builder.heartbeatSocketTimeout(properties.getHeartbeatSocketTimeout());
        MongoClientOptions mongoClientOptions = builder.build();

        // MongoDB地址列表
        List<ServerAddress> serverAddresses = new ArrayList<ServerAddress>();
        for (String address : properties.getAddress()) {
            String[] hostAndPort = address.split(":");
            String host = hostAndPort[0];
            Integer port = Integer.parseInt(hostAndPort[1]);
            ServerAddress serverAddress = new ServerAddress(host, port);
            serverAddresses.add(serverAddress);
        }

        logger.info("serverAddresses:" + serverAddresses.toString());

        // 连接认证
//         MongoCredential mongoCredential = null;
//         if (properties.getUsername() != null) {
//         	mongoCredential = MongoCredential.createScramSha1Credential(
//         			properties.getUsername(), properties.getAuthenticationDatabase() != null
//         					? properties.getAuthenticationDatabase() : properties.getDatabase(),
//         			properties.getPassword().toCharArray());
//         }

        // 创建认证客户端
//         MongoClient mongoClient = new MongoClient(serverAddresses, mongoCredential, mongoClientOptions);
//        MongoDatabase database = mongoClient.getDatabase("trff");
//        GridFSBucket gridFSBucket = GridFSBuckets.create(database,"is_photo");
        // 创建非认证客户端
        MongoClient mongoClient = new MongoClient(serverAddresses, mongoClientOptions);
        // 创建MongoDbFactory
        MongoDbFactory mongoDbFactory = new SimpleMongoDbFactory(mongoClient, properties.getDatabase());
//        new GridFsTemplate(mongoDbFactory, , "is_photo");
        return mongoDbFactory;
    }


}
