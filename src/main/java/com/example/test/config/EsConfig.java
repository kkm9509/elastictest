package com.example.test.config;

import java.net.InetAddress;

import org.elasticsearch.client.Client;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.transport.client.PreBuiltTransportClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class EsConfig {

    @Value("${spring.elasticsearch.clusterName}")
    private String clusterName;

    @Value("${spring.elasticsearch.host}")
    private String host;

    @Value("${spring.elasticsearch.port}")
    private int port;
    
	TransportClient client;
    
    @Bean
    public Client client() throws Exception {
        Settings settings = Settings.builder()
                .put("cluster.name", clusterName).build();

        client = new PreBuiltTransportClient(settings)
                .addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName(host), port));
        
        return client;
    }
}
