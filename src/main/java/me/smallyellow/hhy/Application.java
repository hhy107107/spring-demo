package me.smallyellow.hhy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

import me.smallyellow.base.boot.web.EnableWebPath;

@SpringBootApplication(scanBasePackages = "me.smallyellow.hhy", exclude = {DataSourceAutoConfiguration.class})
@EnableWebPath
public class Application {
    public static void main( String[] args ) {
        SpringApplication.run(Application.class, args);
    }
}
