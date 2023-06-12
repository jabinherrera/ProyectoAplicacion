package com.mezza.app.configuration;

import com.mezza.app.components.StringToTime;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    private final StringToTime stringToTime;

    public WebConfig(StringToTime stringToTime) {
        this.stringToTime = stringToTime;
    }

    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addConverter(stringToTime);
    }
}
