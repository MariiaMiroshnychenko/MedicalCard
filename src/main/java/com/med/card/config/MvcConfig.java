package com.med.card.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

import java.util.Locale;

@Configuration
public class MvcConfig implements WebMvcConfigurer {
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/patientPage").setViewName("patientPage");
        registry.addViewController("/greeting").setViewName("greeting");
        registry.addViewController("/patientPageForDoctor").setViewName("patientPageForDoctor");
        registry.addViewController("/doctorPageForPatient").setViewName("doctorPageForPatient");
        registry.addViewController("/doctorPage").setViewName("doctorPage");
        registry.addViewController("/medicalCard").setViewName("medicalCard");
    }
}
