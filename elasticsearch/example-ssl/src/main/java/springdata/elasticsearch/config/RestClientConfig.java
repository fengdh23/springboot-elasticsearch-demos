package springdata.elasticsearch.config;


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
        HttpHeaders compatibilityHeaders = new HttpHeaders();
        // 让es 8.x使用兼容模式 兼容7.x客户端
        compatibilityHeaders.add("Accept", "application/vnd.elasticsearch+json;compatible-with=7");
        compatibilityHeaders.add("Content-Type", "application/vnd.elasticsearch+json;"
                + "compatible-with=7");
        //添加ssl
        // http_ca.crt需要从es server下的config/certs中拷贝到代码的resource目录下
        ClassPathResource classPathResource = new ClassPathResource("http_ca.crt");
        CertificateFactory factory = CertificateFactory.getInstance("X.509");

        Certificate trustedCa;
        try (InputStream is = classPathResource.getInputStream()) {
            trustedCa = factory.generateCertificate(is);
        }
        KeyStore trustStore = KeyStore.getInstance("pkcs12");
        trustStore.load(null, null);
        trustStore.setCertificateEntry("ca", trustedCa);
        SSLContextBuilder sslContextBuilder = SSLContexts.custom()
                .loadTrustMaterial(trustStore, new TrustAllStrategy());
        SSLContext sslContext = sslContextBuilder.build();

        final ClientConfiguration clientConfiguration = ClientConfiguration.builder()
                .connectedTo("192.168.1.141:9200")
                .usingSsl(sslContext)  // 使用ssl连接
                .withBasicAuth("elastic", "_EcPChjXnpBZYhM0iDiK")
                .withDefaultHeaders(compatibilityHeaders)
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
