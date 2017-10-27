package me.smallyellow.hhy.config;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import freemarker.template.TemplateModelException;
import me.smallyellow.base.boot.web.WebPathProperties;

/**
 * 自定义插件
 *
 */
@Configuration
public class FreeMarkerConfig {

    @Autowired
    protected freemarker.template.Configuration configuration;
    
    @Value("${server.context-path}")
    private String contentPath;
    
	@Autowired
	private WebPathProperties webPathProperties;

    @PostConstruct
    public void  setSharedVariable() throws TemplateModelException{
        configuration.setDateFormat("yyyy-MM-dd");
        configuration.setDateTimeFormat("yyyy-MM-dd HH:mm:ss");
        configuration.setNumberFormat("#");
        configuration.setSharedVariable("contextStaticPath", webPathProperties.getStaticUrl());
    }
}