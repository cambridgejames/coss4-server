package cn.net.powerinv.coss4;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.session.data.mongo.config.annotation.web.http.EnableMongoHttpSession;

@EnableFeignClients
@EnableCircuitBreaker
@EnableEurekaClient
@EnableMongoHttpSession
@SpringBootApplication
@MapperScan("cn.net.powerinv.coss4.mapper")
public class UserManagementApplication {
    public static void main(String[] args) {
        SpringApplication.run(UserManagementApplication.class, args);
    }
}
