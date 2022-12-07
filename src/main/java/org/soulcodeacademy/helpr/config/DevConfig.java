package org.soulcodeacademy.helpr.config;

import org.soulcodeacademy.helpr.services.PopulateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import javax.annotation.PostConstruct;


@Configuration
@Profile("dev")
public class DevConfig {

    @Autowired
    private PopulateService populateService;


    @Value("${spring.jpa.hibernate.ddl-auto}")
    private String ddlAuto;


    @PostConstruct
    public void init() {

        if(this.ddlAuto.equals("create")) {

            this.populateService.populate();
        }
    }
}
