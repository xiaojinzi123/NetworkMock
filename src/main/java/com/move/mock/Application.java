package com.move.mock;

import com.move.mock.service.baseurl.BaseUrlService;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@MapperScan("com.move.mock.mapper")
@EnableTransactionManagement(proxyTargetClass = true)
public class Application {

    public static void main(String[] args) {

        SpringApplication application = new SpringApplication(Application.class);
        application.addListeners(new ApplicationStartup());
        application.run(args);

        // SpringApplication.run(Application.class, args);

    }

}
