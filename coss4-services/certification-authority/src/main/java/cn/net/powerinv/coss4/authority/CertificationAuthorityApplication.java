package cn.net.powerinv.coss4.authority;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.session.data.mongo.config.annotation.web.http.EnableMongoHttpSession;

/**
 * 认证中心服务
 * @author PengJQ
 * @date 2020-05-31
 */
@EnableFeignClients
@EnableCircuitBreaker
@EnableEurekaClient
@EnableMongoHttpSession
@SpringBootApplication
@MapperScan("cn.net.powerinv.coss4.authority.mapper")
public class CertificationAuthorityApplication {
    public static void main(String[] args) {
        SpringApplication.run(CertificationAuthorityApplication.class, args);
    }
}
