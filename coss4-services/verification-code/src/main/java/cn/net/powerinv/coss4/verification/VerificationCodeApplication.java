package cn.net.powerinv.coss4.verification;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.session.data.mongo.config.annotation.web.http.EnableMongoHttpSession;

/**
 * 统一验证码认证服务（图片验证码、手机短信验证码、登录二维码和邮箱验证码等）
 * @author PengJQ
 * @date 2020-03-30
 */
@EnableFeignClients
@EnableCircuitBreaker
@EnableEurekaClient
@EnableMongoHttpSession
@SpringBootApplication
public class VerificationCodeApplication {
    public static void main(String[] args) {
        SpringApplication.run(VerificationCodeApplication.class, args);
    }
}
