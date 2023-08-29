package co.com.myphotosnameapp.myphotoswebbackend.configuration;

import lombok.extern.slf4j.Slf4j;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;

@Slf4j
@Component
public class Configuration {


    @Bean
    public SimpleDateFormat formatDate(){
        return new SimpleDateFormat("yyyy-MM-dd");
    }



}
