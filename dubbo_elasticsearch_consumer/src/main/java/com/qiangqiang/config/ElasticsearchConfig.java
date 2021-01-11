package com.qiangqiang.config;

import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.client.ClientConfiguration;
import org.springframework.data.elasticsearch.client.RestClients;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;

/**
 * \* Created with IntelliJ IDEA.
 * \* @author: xiyue
 * \* Date: 2020/12/18
 * \* Time: 17:42
 * \* To change this template use File | Settings | File Templates.
 * \* Description:
 * \
 */
@Configuration
@EnableElasticsearchRepositories(basePackages = "com.qiangqiang")
public class ElasticsearchConfig {
    @Value("${elasticsearchHost}")
    public String elasticsearchHost;

    @Bean
    public RestHighLevelClient restHighLevelClient() {
        ClientConfiguration configuration = ClientConfiguration.builder()
                .connectedTo(elasticsearchHost)
                .build();
        RestHighLevelClient client = RestClients.create(configuration).rest();
        return client;
    }


}