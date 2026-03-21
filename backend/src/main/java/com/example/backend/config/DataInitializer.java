package com.example.backend.config;

import com.example.backend.entity.*;
import com.example.backend.repository.SchemeRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;
import java.util.List;

@Configuration
public class DataInitializer {

    @Bean
    CommandLineRunner initDatabase(SchemeRepository repository) {
        return args -> {
            List<Scheme> telanganaSchemes = Arrays.asList(
                new Scheme(null, "Hospitalisation Relief Scheme", 
                    "Financial assistance to construction workers hospitalized for 5+ days due to accident or disease.", 
                    CategoryType.HEALTH, "Telangana", GenderType.ALL, CasteType.ALL, 18, 60, null, true, false, false, false, "https://labour.telangana.gov.in/"),
                
                new Scheme(null, "Maternity Benefit Scheme", 
                    "Financial support to women construction workers and their families.", 
                    CategoryType.WOMEN_CHILD_WELFARE, "Telangana", GenderType.FEMALE, CasteType.ALL, 18, 45, null, false, false, false, false, "https://labour.telangana.gov.in/"),
                
                new Scheme(null, "Marriage Gift Scheme", 
                    "Financial assistance for marriage of women construction workers or their daughters.", 
                    CategoryType.SOCIAL_WELFARE, "Telangana", GenderType.FEMALE, CasteType.ALL, 18, 50, null, false, false, false, false, "https://labour.telangana.gov.in/"),
                
                new Scheme(null, "₹5 Lakh Insurance Cover To Farmers", 
                    "Insurance coverage for farmers aged 18–60.", 
                    CategoryType.AGRICULTURE, "Telangana", GenderType.ALL, CasteType.ALL, 18, 60, null, false, false, false, false, "https://rythubandhu.telangana.gov.in/"),
                
                new Scheme(null, "Disability Relief Scheme", 
                    "Financial support for construction workers with permanent or partial disability.", 
                    CategoryType.SOCIAL_WELFARE, "Telangana", GenderType.ALL, CasteType.ALL, 18, 60, null, true, false, false, false, "https://labour.telangana.gov.in/"),
                
                new Scheme(null, "Disability Aids and Appliances", 
                    "Provides artificial limbs, wheelchairs, tricycles to disabled construction workers.", 
                    CategoryType.SOCIAL_WELFARE, "Telangana", GenderType.ALL, CasteType.ALL, 18, 60, null, true, false, false, false, "https://labour.telangana.gov.in/"),
                
                new Scheme(null, "Feed The Seed", 
                    "Encourages farmers to produce seeds with government support.", 
                    CategoryType.AGRICULTURE, "Telangana", GenderType.ALL, CasteType.ALL, 18, 70, null, false, false, false, false, "https://agriculture.telangana.gov.in/"),
                
                new Scheme(null, "Chief Minister's Overseas Scholarship Scheme for Minorities", 
                    "Financial support for minority students to study abroad.", 
                    CategoryType.EDUCATION, "Telangana", GenderType.ALL, CasteType.ALL, 18, 35, 600000.0, false, false, false, true, "https://telanganaepass.cgg.gov.in/"),
                
                new Scheme(null, "Mahatma Jyothiba Phule Overseas Vidya Nidhi", 
                    "Scholarship for BC/EBC students for foreign education.", 
                    CategoryType.EDUCATION, "Telangana", GenderType.ALL, CasteType.OBC, 18, 35, 500000.0, false, false, false, false, "https://telanganaepass.cgg.gov.in/"),
                
                new Scheme(null, "KCR Nutrition Kit", 
                    "Nutritional support for pregnant women.", 
                    CategoryType.WOMEN_CHILD_WELFARE, "Telangana", GenderType.FEMALE, CasteType.ALL, 18, 45, null, false, false, false, false, "https://health.telangana.gov.in/"),
                
                new Scheme(null, "Rythu Bima Scheme", 
                    "Life insurance coverage for farmers.", 
                    CategoryType.AGRICULTURE, "Telangana", GenderType.ALL, CasteType.ALL, 18, 60, null, false, false, false, false, "https://rythubima.telangana.gov.in/"),
                
                new Scheme(null, "Fatal Accident Relief", 
                    "Financial assistance to families of deceased construction workers.", 
                    CategoryType.SOCIAL_WELFARE, "Telangana", GenderType.ALL, CasteType.ALL, 18, 60, null, false, false, false, false, "https://labour.telangana.gov.in/"),
                
                new Scheme(null, "Skill Development Training (15 Days Residential)", 
                    "Skill upgradation for construction workers.", 
                    CategoryType.SKILL_DEVELOPMENT, "Telangana", GenderType.ALL, CasteType.ALL, 18, 45, null, false, false, false, false, "https://nac.edu.in/"),
                
                new Scheme(null, "Skill Development Training (RPL)", 
                    "Recognition of prior learning training at work sites.", 
                    CategoryType.SKILL_DEVELOPMENT, "Telangana", GenderType.ALL, CasteType.ALL, 18, 50, null, false, false, false, false, "https://nac.edu.in/"),
                
                new Scheme(null, "Kalyana Lakshmi Pathakam", 
                    "Marriage financial assistance for SC/ST/minority girls.", 
                    CategoryType.SOCIAL_WELFARE, "Telangana", GenderType.FEMALE, CasteType.SC, 18, 30, 200000.0, false, false, false, false, "https://telanganaepass.cgg.gov.in/"),
                
                new Scheme(null, "Relief to Unregistered Workers", 
                    "Financial aid to families of unregistered workers who die in accidents.", 
                    CategoryType.SOCIAL_WELFARE, "Telangana", GenderType.ALL, CasteType.ALL, 18, 60, null, false, false, false, false, "https://labour.telangana.gov.in/"),
                
                new Scheme(null, "Shaadi Mubarak Scheme", 
                    "Marriage assistance for minority girls.", 
                    CategoryType.SOCIAL_WELFARE, "Telangana", GenderType.FEMALE, CasteType.ALL, 18, 30, 200000.0, false, false, false, true, "https://telanganaepass.cgg.gov.in/"),
                
                new Scheme(null, "Natural Death Relief", 
                    "Financial support for families of deceased workers.", 
                    CategoryType.SOCIAL_WELFARE, "Telangana", GenderType.ALL, CasteType.ALL, 18, 60, null, false, false, false, false, "https://labour.telangana.gov.in/"),
                
                new Scheme(null, "Skill Development Training (90 Days Residential)", 
                    "Skill training for unemployed youth.", 
                    CategoryType.SKILL_DEVELOPMENT, "Telangana", GenderType.ALL, CasteType.ALL, 18, 35, null, false, false, false, false, "https://tsmsc.telangana.gov.in/"),
                
                new Scheme(null, "Skill Development Training (Garment Stitching)", 
                    "Skill training for women in tailoring.", 
                    CategoryType.SKILL_DEVELOPMENT, "Telangana", GenderType.FEMALE, CasteType.ALL, 18, 45, null, false, false, false, false, "https://tsmsc.telangana.gov.in/"),
                
                new Scheme(null, "Dalit Bandhu", 
                    "Financial assistance for SC families to promote entrepreneurship.", 
                    CategoryType.SOCIAL_WELFARE, "Telangana", GenderType.ALL, CasteType.SC, 18, 60, null, false, false, false, false, "https://dalitbandhu.telangana.gov.in/"),
                
                new Scheme(null, "Haritha Haram", 
                    "Environmental initiative to increase green cover through plantation.", 
                    CategoryType.OTHER, "Telangana", GenderType.ALL, CasteType.ALL, 5, 100, null, false, false, false, false, "https://harithaharam.telangana.gov.in/")
            );

            for (Scheme scheme : telanganaSchemes) {
                if (!repository.existsByNameAndState(scheme.getName(), scheme.getState())) {
                    repository.save(scheme);
                }
            }
            System.out.println("Telangana schemes initialization completed!");
        };
    }
}
