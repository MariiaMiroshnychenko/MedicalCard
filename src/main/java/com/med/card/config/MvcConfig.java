package com.med.card.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MvcConfig implements WebMvcConfigurer {
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/patientPage").setViewName("patientPage");
        registry.addViewController("/doctorPage").setViewName("doctorPage");
        registry.addViewController("/specDoctorPage").setViewName("specDoctorPage");
        registry.addViewController("/medEmployeePage").setViewName("medEmployeePage");
        registry.addViewController("/adminPage").setViewName("adminPage");

        registry.addViewController("/patientPageForDoctor").setViewName("patientPageForDoctor");
        registry.addViewController("/doctorPageForPatient").setViewName("doctorPageForPatient");

//
        registry.addViewController("/medicalCard").setViewName("medicalCard");

//        registry.addViewController("/appointment").setViewName("make-appointment");
//        registry.addViewController("/doctorPage/doctor-appointment").setViewName("doctor-appointment");
    }
}
