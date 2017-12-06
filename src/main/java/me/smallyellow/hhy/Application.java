package me.smallyellow.hhy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import me.smallyellow.base.boot.web.EnableWebPath;

@SpringBootApplication(scanBasePackages = "me.smallyellow", exclude = {DataSourceAutoConfiguration.class})
@EnableWebPath
@ServletComponentScan("me.smallyellow")
@EnableTransactionManagement
public class Application {
    public static void main( String[] args ) {
        SpringApplication.run(Application.class, args);
    }
}
