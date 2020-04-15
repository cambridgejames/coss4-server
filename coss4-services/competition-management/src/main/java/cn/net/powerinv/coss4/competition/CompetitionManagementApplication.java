package cn.net.powerinv.coss4.competition;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.session.data.mongo.config.annotation.web.http.EnableMongoHttpSession;

/**
 * 竞赛信息管理服务
 * @author PengJQ
 * @date 2020-04-03
 */
@EnableFeignClients
@EnableCircuitBreaker
@EnableEurekaClient
@EnableMongoHttpSession
@SpringBootApplication
@MapperScan("cn.net.powerinv.coss4.competition.mapper")
public class CompetitionManagementApplication {
    public static void main(String[] args) {
        SpringApplication.run(CompetitionManagementApplication.class, args);
    }
}
