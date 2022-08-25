package com.example.es8noauth.config;


import lombok.SneakyThrows;
import org.apache.http.ssl.SSLContextBuilder;
import org.apache.http.ssl.SSLContexts;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.elasticsearch.client.ClientConfiguration;
import org.springframework.data.elasticsearch.client.RestClients;
import org.springframework.data.elasticsearch.config.AbstractElasticsearchConfiguration;
import org.springframework.http.HttpHeaders;

import javax.net.ssl.SSLContext;
import java.io.InputStream;
import java.security.KeyStore;
import java.security.cert.Certificate;
import java.security.cert.CertificateFactory;

@Configuration
public class RestClientConfig extends AbstractElasticsearchConfiguration {

    @SneakyThrows
    @Override
    @Bean
    public RestHighLevelClient elasticsearchClient() {
        final ClientConfiguration clientConfiguration = ClientConfiguration.builder().connectedTo("192.168.1.141:9200")
//                .usingSsl(sslContext)  // 使用ssl连接
//                .withBasicAuth("elastic", "_EcPChjXnpBZYhM0iDiK")
//                .withDefaultHeaders(compatibilityHeaders)
                .build();
        return RestClients.create(clientConfiguration).rest();
    }

//    @Override
//    @Bean
//    public ElasticsearchClient elasticsearchClient() {
//        RestClient restClient = RestClient.builder(new HttpHost("",9200)).build();
//        ElasticsearchTransport transport = new RestClientTransport(restClient,new JacksonJsonpMapper());
//        return new ElasticsearchClient(transport);
//    }
}
