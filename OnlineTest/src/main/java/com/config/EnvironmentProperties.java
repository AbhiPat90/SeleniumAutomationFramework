package com.config;
import org.aeonbits.owner.Config;

@Config.Sources("classpath:runtime.local.properties")

public interface EnvironmentProperties extends Config{

    @Key("test.url")
    String getURL();

    @Key("test.url.practice")
    String getPracticeURL();
}


