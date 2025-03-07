package com.pw.application.config.file;

import com.pw.file.properties.PwFileProperties;
import com.pw.file.spec.PwFileLocalSpec;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PwFileLocalConfigure {

    @Value(value = "pw.file.root")
    private String root;

    @Value(value = "pw.file.temp")
    private String temp;

    @Bean
    public PwFileLocalSpec pwFileLocalSpec() {
        PwFileProperties properties = new PwFileProperties();
        properties.setRoot(root);
        properties.setTemp(temp);
        return new PwFileLocalSpec(properties);
    }

}
