package co.com.myphotosnameapp.myphotoswebbackend.configuration;

import lombok.extern.slf4j.Slf4j;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;

@Slf4j
@Component
public class Configuration {

    @Bean
    public DateTimeFormatter getDateFormatter() {
        return DateTimeFormatter.ofPattern("yyyy-MM-dd");
    }

    @Bean
    public SimpleDateFormat formatDate(){
        return new SimpleDateFormat("yyyy-MM-dd");
    }



}
