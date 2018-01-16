package com.wangjx.pms.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.*;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: tigeeer
 * Date: 12/12/2016
 * Time: 12:54 AM
 */
@Configuration
public class WebMvcConfig extends WebMvcConfigurerAdapter {

    private BaseConfig baseConfig;

    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    public WebMvcConfig(BaseConfig baseConfig) {
        this.baseConfig = baseConfig;
    }

    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }

    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter = new MappingJackson2HttpMessageConverter();
        converters.add(mappingJackson2HttpMessageConverter);
    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**").allowedOrigins("*")
                .allowedMethods("GET", "HEAD", "POST", "PUT", "PATCH", "DELETE", "OPTIONS", "TRACE");
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/assets/**")
                .addResourceLocations("classpath:/static/assets/")
                .setCachePeriod(31556926);

        registry
                .addResourceHandler("/**")
                .addResourceLocations("classpath:/static/")
                .setCachePeriod(0);

        if (!registry.hasMappingForPattern("/upload/**")) {
            registry.addResourceHandler("/upload/**")
                    .addResourceLocations("file:" + baseConfig.getUploadDir() + "/upload/");
        }
    }

}