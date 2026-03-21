package com.example.backend.config;

import com.example.backend.entity.*;
import com.example.backend.repository.SchemeRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import java.util.Arrays;
import java.util.List;

@Configuration
@Order(3)
public class DataInitializerWest {

    private void save(SchemeRepository r, Scheme s) {
        if (!r.existsByNameAndState(s.getName(), s.getState())) r.save(s);
    }

    @Bean("westRunner")
    CommandLineRunner initWest(SchemeRepository repo) {
        return args -> {
            // MAHARASHTRA
            List<Scheme> mh = Arrays.asList(
                new Scheme(null, "Mahatma Jyotiba Phule Jan Arogya Yojana", "Health insurance up to Rs.1.5 lakh per year for BPL families for 971 listed diseases.", CategoryType.HEALTH, "Maharashtra", GenderType.ALL, CasteType.ALL, 0, 100, null, false, true, false, false, "https://jeevandayee.gov.in/"),
                new Scheme(null, "Shiv Bhojan Thali", "Nutritious meal (Rs.10 for lunch/dinner) for workers and urban poor at government canteens.", CategoryType.FOOD, "Maharashtra", GenderType.ALL, CasteType.ALL, 0, 100, null, false, false, false, false, "https://maharashtra.gov.in/"),
                new Scheme(null, "Ladli Laxmi Yojana Maharashtra", "Financial support for girl child education and welfare from birth to graduation.", CategoryType.WOMEN_CHILD_WELFARE, "Maharashtra", GenderType.FEMALE, CasteType.ALL, 0, 21, 80000.0, false, false, false, false, "https://wcd.maharashtra.gov.in/"),
                new Scheme(null, "Maharashtra Farmer Loan Waiver", "Complete waiver of crop loans up to Rs.2 lakh for farmers in drought-prone areas.", CategoryType.AGRICULTURE, "Maharashtra", GenderType.ALL, CasteType.ALL, 18, 70, null, false, false, false, false, "https://mahadbt.maharashtra.gov.in/"),
                new Scheme(null, "Balasaheb Thackeray Accident Insurance", "Rs.2 lakh accident insurance for eligible beneficiaries.", CategoryType.SOCIAL_WELFARE, "Maharashtra", GenderType.ALL, CasteType.ALL, 18, 70, null, false, false, false, false, "https://maharashtra.gov.in/"),
                new Scheme(null, "Maharashtra Pension Scheme", "Monthly pension for elderly (60+), disabled, and widows from rural areas.", CategoryType.SOCIAL_WELFARE, "Maharashtra", GenderType.ALL, CasteType.ALL, 60, 100, null, true, true, true, false, "https://sjsa.maharashtra.gov.in/"),
                new Scheme(null, "Skill Development Mission Maharashtra", "Free vocational training in 30+ trades for youth aged 14-28 at 500+ centers.", CategoryType.SKILL_DEVELOPMENT, "Maharashtra", GenderType.ALL, CasteType.ALL, 14, 28, null, false, false, false, false, "https://mssds.org/"),
                new Scheme(null, "Maharashtra Housing Scheme", "Affordable pucca houses for urban slum dwellers and homeless BPL families.", CategoryType.HOUSING, "Maharashtra", GenderType.ALL, CasteType.ALL, 18, 65, null, false, true, false, false, "https://mhada.gov.in/"),
                new Scheme(null, "Education Scholarship Scheme Maharashtra", "Post-matric scholarships for SC/ST/OBC students for professional and technical education.", CategoryType.EDUCATION, "Maharashtra", GenderType.ALL, CasteType.ALL, 17, 30, 200000.0, false, false, false, false, "https://mahadbt.maharashtra.gov.in/"),
                new Scheme(null, "Health Insurance Scheme Maharashtra", "Group health insurance for unorganized sector workers and their families.", CategoryType.HEALTH, "Maharashtra", GenderType.ALL, CasteType.ALL, 18, 60, null, false, false, false, false, "https://mahadbt.maharashtra.gov.in/")
            );
            mh.forEach(s -> save(repo, s));

            // GUJARAT
            List<Scheme> gj = Arrays.asList(
                new Scheme(null, "Mukhyamantri Amrutam Yojana", "Free health insurance up to Rs.5 lakh for BPL families for 1400+ diseases.", CategoryType.HEALTH, "Gujarat", GenderType.ALL, CasteType.ALL, 0, 100, null, false, true, false, false, "https://magujarat.com/"),
                new Scheme(null, "Gujarat Kisan Sahay Yojana", "Compensation up to Rs.20000/hectare to farmers affected by natural disasters.", CategoryType.AGRICULTURE, "Gujarat", GenderType.ALL, CasteType.ALL, 18, 70, null, false, false, false, false, "https://ikhedut.gujarat.gov.in/"),
                new Scheme(null, "Vidhya Lakshmi Yojana", "Interest-free education loans for girls from economically weak families for higher education.", CategoryType.EDUCATION, "Gujarat", GenderType.FEMALE, CasteType.ALL, 18, 28, 120000.0, false, false, false, false, "https://myscholarship.gujarat.gov.in/"),
                new Scheme(null, "Gujarat Housing Scheme", "Affordable housing for EWS/LIG families under Pradhan Mantri Awas Yojana in Gujarat.", CategoryType.HOUSING, "Gujarat", GenderType.ALL, CasteType.ALL, 18, 70, 300000.0, false, true, false, false, "https://gujarat.gov.in/"),
                new Scheme(null, "Gujarat Skill Development Mission", "Free skill training in 40+ trades for unemployed youth across Gujarat.", CategoryType.SKILL_DEVELOPMENT, "Gujarat", GenderType.ALL, CasteType.ALL, 15, 35, null, false, false, false, false, "https://gsdc.gujarat.gov.in/"),
                new Scheme(null, "Women Empowerment Scheme Gujarat", "Livelihood support and self-employment assistance to women SHG members.", CategoryType.WOMEN_CHILD_WELFARE, "Gujarat", GenderType.FEMALE, CasteType.ALL, 18, 55, 100000.0, false, false, false, false, "https://gujrat.gov.in/"),
                new Scheme(null, "Gujarat Health Scheme", "Medical assistance to state employees, pensioners, and their dependents.", CategoryType.HEALTH, "Gujarat", GenderType.ALL, CasteType.ALL, 0, 100, null, false, false, false, false, "https://gujarat.gov.in/"),
                new Scheme(null, "Gujarat Scholarship Scheme", "Merit-cum-means scholarships for SC/ST/OBC professional course students.", CategoryType.EDUCATION, "Gujarat", GenderType.ALL, CasteType.ALL, 17, 30, 200000.0, false, false, false, false, "https://scholarship.gujarat.gov.in/"),
                new Scheme(null, "Gujarat Solar Subsidy Scheme", "Subsidy on solar panels for farmers to reduce irrigation electricity costs.", CategoryType.AGRICULTURE, "Gujarat", GenderType.ALL, CasteType.ALL, 18, 70, null, false, false, false, false, "https://geda.gujarat.gov.in/"),
                new Scheme(null, "Gujarat Farmer Support Scheme", "Input subsidy and financial support for small and marginal farmers for crop losses.", CategoryType.AGRICULTURE, "Gujarat", GenderType.ALL, CasteType.ALL, 18, 70, null, false, false, false, false, "https://ikhedut.gujarat.gov.in/")
            );
            gj.forEach(s -> save(repo, s));

            // RAJASTHAN
            List<Scheme> rj = Arrays.asList(
                new Scheme(null, "Mukhyamantri Chiranjeevi Health Insurance", "Universal health coverage up to Rs.25 lakh/year for every Rajasthan family.", CategoryType.HEALTH, "Rajasthan", GenderType.ALL, CasteType.ALL, 0, 100, null, false, false, false, false, "https://chiranjeevi.rajasthan.gov.in/"),
                new Scheme(null, "Indira Gandhi Free Mobile Scheme", "Free smartphone with 3 years internet for Chiranjeevi scheme and single women families.", CategoryType.SOCIAL_WELFARE, "Rajasthan", GenderType.FEMALE, CasteType.ALL, 18, 100, null, false, false, false, false, "https://igsy.rajasthan.gov.in/"),
                new Scheme(null, "Rajasthan Farmer Loan Waiver", "Crop loan waiver up to Rs.2 lakh for small and marginal farmers in Rajasthan.", CategoryType.AGRICULTURE, "Rajasthan", GenderType.ALL, CasteType.ALL, 18, 70, null, false, false, false, false, "https://rajasthan.gov.in/"),
                new Scheme(null, "Rajasthan Pension Scheme", "Social security pension for aged, widows, disabled, and single women.", CategoryType.SOCIAL_WELFARE, "Rajasthan", GenderType.ALL, CasteType.ALL, 55, 100, null, true, false, true, false, "https://ssp.rajasthan.gov.in/"),
                new Scheme(null, "Rajshree Yojana", "Financial benefits (Rs.50000 total) in installments for girl child born in government hospitals.", CategoryType.WOMEN_CHILD_WELFARE, "Rajasthan", GenderType.FEMALE, CasteType.ALL, 0, 12, 120000.0, false, false, false, false, "https://wcd.rajasthan.gov.in/"),
                new Scheme(null, "Rajasthan Skill Development Scheme", "Short-term skill training to unemployed youth with employment placement support.", CategoryType.SKILL_DEVELOPMENT, "Rajasthan", GenderType.ALL, CasteType.ALL, 15, 35, null, false, false, false, false, "https://rsldc.rajasthan.gov.in/"),
                new Scheme(null, "Rajasthan Housing Scheme", "Subsidized housing for urban and rural economically weaker section families.", CategoryType.HOUSING, "Rajasthan", GenderType.ALL, CasteType.ALL, 18, 65, 200000.0, false, true, false, false, "https://rajasthan.gov.in/"),
                new Scheme(null, "Rajasthan Scholarship Scheme", "Post-matric scholarships for SC/ST/OBC/EWS students in higher education.", CategoryType.EDUCATION, "Rajasthan", GenderType.ALL, CasteType.ALL, 17, 28, 250000.0, false, false, false, false, "https://sje.rajasthan.gov.in/"),
                new Scheme(null, "Free Electricity Scheme Rajasthan", "100 units free electricity per month for all domestic consumers in Rajasthan.", CategoryType.OTHER, "Rajasthan", GenderType.ALL, CasteType.ALL, 18, 100, null, false, false, false, false, "https://energy.rajasthan.gov.in/"),
                new Scheme(null, "Women Empowerment Scheme Rajasthan", "Interest-free loans and skill training for women entrepreneurs from BPL families.", CategoryType.WOMEN_CHILD_WELFARE, "Rajasthan", GenderType.FEMALE, CasteType.ALL, 18, 55, 80000.0, false, true, false, false, "https://wcd.rajasthan.gov.in/")
            );
            rj.forEach(s -> save(repo, s));

            // GOA
            List<Scheme> ga = Arrays.asList(
                new Scheme(null, "Griha Aadhar Scheme", "Rs.1500/month financial assistance to housewives for home management and welfare.", CategoryType.WOMEN_CHILD_WELFARE, "Goa", GenderType.FEMALE, CasteType.ALL, 18, 65, 300000.0, false, false, false, false, "https://socialdevelopment.goa.gov.in/"),
                new Scheme(null, "Pension Scheme Goa", "Monthly pension for elderly (60+), widows, and differently-abled persons in Goa.", CategoryType.SOCIAL_WELFARE, "Goa", GenderType.ALL, CasteType.ALL, 60, 100, null, true, false, true, false, "https://socialdevelopment.goa.gov.in/"),
                new Scheme(null, "Scholarship Scheme Goa", "Merit scholarships for students from low-income families pursuing professional courses.", CategoryType.EDUCATION, "Goa", GenderType.ALL, CasteType.ALL, 17, 28, 300000.0, false, false, false, false, "https://scholarships.goa.gov.in/"),
                new Scheme(null, "Health Scheme Goa", "Cashless medical care under Deen Dayal Swasthya Seva Yojana for Goa residents.", CategoryType.HEALTH, "Goa", GenderType.ALL, CasteType.ALL, 0, 100, null, false, false, false, false, "https://ddssy.goa.gov.in/"),
                new Scheme(null, "Housing Scheme Goa", "Subsidized housing for low-income families through Goa Housing Board.", CategoryType.HOUSING, "Goa", GenderType.ALL, CasteType.ALL, 18, 65, 400000.0, false, false, false, false, "https://ghboard.goa.gov.in/"),
                new Scheme(null, "Skill Development Scheme Goa", "Technical and vocational training for youth to enhance employability in Goa.", CategoryType.SKILL_DEVELOPMENT, "Goa", GenderType.ALL, CasteType.ALL, 15, 35, null, false, false, false, false, "https://goa.gov.in/"),
                new Scheme(null, "Women Welfare Scheme Goa", "Empowerment programs for women through SHGs, legal aid, and skill development.", CategoryType.WOMEN_CHILD_WELFARE, "Goa", GenderType.FEMALE, CasteType.ALL, 18, 55, null, false, false, false, false, "https://wcd.goa.gov.in/"),
                new Scheme(null, "Employment Scheme Goa", "Monthly stipend for educated unemployed youth registered with the employment exchange.", CategoryType.EMPLOYMENT, "Goa", GenderType.ALL, CasteType.ALL, 18, 35, null, false, false, false, false, "https://goa.gov.in/"),
                new Scheme(null, "Farmer Scheme Goa", "Agricultural input subsidies and crop insurance for farmers in Goa.", CategoryType.AGRICULTURE, "Goa", GenderType.ALL, CasteType.ALL, 18, 70, null, false, false, false, false, "https://agri.goa.gov.in/"),
                new Scheme(null, "Ration Scheme Goa", "Subsidized food grains for APL, BPL, and Antyodaya families through PDS.", CategoryType.FOOD, "Goa", GenderType.ALL, CasteType.ALL, 0, 100, null, false, true, false, false, "https://goacivilsupplies.gov.in/")
            );
            ga.forEach(s -> save(repo, s));

            System.out.println("West India schemes initialization completed!");
        };
    }
}
