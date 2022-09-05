package com.api.pautacontrol.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@Component
@PropertySource("classpath:message.properties")
public class MessageProperties {

    private final Environment env;

    public MessageProperties(Environment env) {
        this.env = env;
    }

    public String getValue(String value){
        return env.getProperty(value);
    }

}
