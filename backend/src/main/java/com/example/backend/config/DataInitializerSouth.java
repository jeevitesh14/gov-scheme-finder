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
@Order(2)
public class DataInitializerSouth {

    private void save(SchemeRepository r, Scheme s) {
        if (!r.existsByNameAndState(s.getName(), s.getState())) r.save(s);
    }

    @Bean("southRunner")
    CommandLineRunner initSouth(SchemeRepository repo) {
        return args -> {
            // ANDHRA PRADESH
            List<Scheme> ap = Arrays.asList(
                new Scheme(null, "YSR Rythu Bharosa", "Financial investment support of Rs.13500/year to all agriculture landholding farmer families.", CategoryType.AGRICULTURE, "Andhra Pradesh", GenderType.ALL, CasteType.ALL, 18, 70, null, false, false, false, false, "https://ysrrythubharosa.ap.gov.in/"),
                new Scheme(null, "Amma Vodi", "Financial assistance of Rs.15000/year to mothers who send children to school to combat dropout rates.", CategoryType.EDUCATION, "Andhra Pradesh", GenderType.FEMALE, CasteType.ALL, 18, 60, null, false, true, false, false, "https://ammavodi.ap.gov.in/"),
                new Scheme(null, "YSR Aarogyasri", "Comprehensive health coverage up to Rs.5 lakh for BPL families for critical illnesses.", CategoryType.HEALTH, "Andhra Pradesh", GenderType.ALL, CasteType.ALL, 0, 100, null, false, true, false, false, "https://aarogyasri.ap.gov.in/"),
                new Scheme(null, "YSR Pension Kanuka", "Pension of Rs.2750 per month for elderly, widows, disabled and weavers.", CategoryType.SOCIAL_WELFARE, "Andhra Pradesh", GenderType.ALL, CasteType.ALL, 55, 100, null, true, true, true, false, "https://ap.gov.in/"),
                new Scheme(null, "YSR Cheyutha", "Financial support of Rs.18750 per year for women from BC, SC, ST, minorities aged 45-60.", CategoryType.WOMEN_CHILD_WELFARE, "Andhra Pradesh", GenderType.FEMALE, CasteType.ALL, 45, 60, null, false, true, false, true, "https://ap.gov.in/"),
                new Scheme(null, "YSR Kapu Nestham", "Financial assistance of Rs.15000/year for economically poor Kapu community women.", CategoryType.SOCIAL_WELFARE, "Andhra Pradesh", GenderType.FEMALE, CasteType.GENERAL, 45, 60, 50000.0, false, false, false, false, "https://ap.gov.in/"),
                new Scheme(null, "Jagananna Vidya Deevena", "Full fee reimbursement for ITI, polytechnic, degree and PG students from SC/ST/BC/minorities.", CategoryType.EDUCATION, "Andhra Pradesh", GenderType.ALL, CasteType.ALL, 18, 30, 250000.0, false, false, false, false, "https://navasakam.ap.gov.in/"),
                new Scheme(null, "Jagananna Vasathi Deevena", "Hostel and boarding fees support for students pursuing ITI, Polytechnic, Degree and PG courses.", CategoryType.EDUCATION, "Andhra Pradesh", GenderType.ALL, CasteType.ALL, 15, 28, 200000.0, false, false, false, false, "https://navasakam.ap.gov.in/"),
                new Scheme(null, "YSR Sunna Vaddi", "Zero-interest loans for women self-help groups through DWCRA and SERP.", CategoryType.FINANCE, "Andhra Pradesh", GenderType.FEMALE, CasteType.ALL, 18, 60, 300000.0, false, false, false, false, "https://ap.gov.in/"),
                new Scheme(null, "YSR Matsyakara Bharosa", "Financial support of Rs.10000/year for fishermen during fishing ban period.", CategoryType.AGRICULTURE, "Andhra Pradesh", GenderType.ALL, CasteType.ALL, 18, 65, null, false, false, false, false, "https://ap.gov.in/")
            );
            ap.forEach(s -> save(repo, s));

            // KARNATAKA
            List<Scheme> ka = Arrays.asList(
                new Scheme(null, "Anna Bhagya", "10 kg free rice per month to BPL households under food security scheme.", CategoryType.FOOD, "Karnataka", GenderType.ALL, CasteType.ALL, 0, 100, null, false, true, false, false, "https://ahara.kar.nic.in/"),
                new Scheme(null, "Ksheera Bhagya", "Free milk to students in government schools to improve nutrition and reduce dropout.", CategoryType.EDUCATION, "Karnataka", GenderType.ALL, CasteType.ALL, 5, 15, null, false, false, false, false, "https://schooleducation.kar.nic.in/"),
                new Scheme(null, "Gruha Lakshmi", "Rs.2000/month direct benefit to women head of household for economic empowerment.", CategoryType.WOMEN_CHILD_WELFARE, "Karnataka", GenderType.FEMALE, CasteType.ALL, 18, 60, null, false, false, false, false, "https://sevasindhu.karnataka.gov.in/"),
                new Scheme(null, "Gruha Jyothi", "200 units of free electricity per month to domestic consumers.", CategoryType.OTHER, "Karnataka", GenderType.ALL, CasteType.ALL, 18, 100, null, false, false, false, false, "https://bescom.karnataka.gov.in/"),
                new Scheme(null, "Shakti Scheme (Free Bus for Women)", "Free travel for women on all KSRTC and BMTC state-run buses.", CategoryType.SOCIAL_WELFARE, "Karnataka", GenderType.FEMALE, CasteType.ALL, 18, 100, null, false, false, false, false, "https://ksrtc.in/"),
                new Scheme(null, "Yuva Nidhi", "Monthly allowance of Rs.3000 for diploma holders and Rs.1500 for graduates who are unemployed.", CategoryType.EMPLOYMENT, "Karnataka", GenderType.ALL, CasteType.ALL, 18, 36, null, false, false, false, false, "https://sevasindhu.karnataka.gov.in/"),
                new Scheme(null, "Raitha Siri", "Compensation and income support package for drought-affected farmers.", CategoryType.AGRICULTURE, "Karnataka", GenderType.ALL, CasteType.ALL, 18, 70, null, false, false, false, false, "https://raitamitra.karnataka.gov.in/"),
                new Scheme(null, "Vidyasiri Scholarship", "Post-matric scholarship for SC/ST students pursuing higher education.", CategoryType.EDUCATION, "Karnataka", GenderType.ALL, CasteType.SC, 18, 30, 250000.0, false, false, false, false, "https://sw.kar.nic.in/"),
                new Scheme(null, "Indira Canteen", "Heavily subsidized meals (Rs.5 breakfast, Rs.10 lunch/dinner) for urban poor and workers.", CategoryType.FOOD, "Karnataka", GenderType.ALL, CasteType.ALL, 0, 100, null, false, false, false, false, "https://indiracanteen.karnataka.gov.in/"),
                new Scheme(null, "Karnataka Health Scheme", "Comprehensive medical assistance to state government employees and their families.", CategoryType.HEALTH, "Karnataka", GenderType.ALL, CasteType.ALL, 0, 100, null, false, false, false, false, "https://karunaarogya.karnataka.gov.in/")
            );
            ka.forEach(s -> save(repo, s));

            // TAMIL NADU
            List<Scheme> tn = Arrays.asList(
                new Scheme(null, "Amma Canteen", "Subsidized food at Rs.1 for idli, Rs.5 for meals to reduce hunger among urban poor.", CategoryType.FOOD, "Tamil Nadu", GenderType.ALL, CasteType.ALL, 0, 100, null, false, false, false, false, "https://amma.tnhb.gov.in/"),
                new Scheme(null, "Amma Pharmacy", "Generic medicines at 50% discount through government pharmacies to reduce healthcare costs.", CategoryType.HEALTH, "Tamil Nadu", GenderType.ALL, CasteType.ALL, 0, 100, null, false, false, false, false, "https://tnhsp.gov.in/"),
                new Scheme(null, "Amma Water", "Free or subsidized safe drinking water supply to all households in Tamil Nadu.", CategoryType.OTHER, "Tamil Nadu", GenderType.ALL, CasteType.ALL, 0, 100, null, false, false, false, false, "https://twad.gov.in/"),
                new Scheme(null, "Chief Minister Comprehensive Health Insurance Scheme", "Insurance up to Rs.5 lakh for government and private sector low-income employees.", CategoryType.HEALTH, "Tamil Nadu", GenderType.ALL, CasteType.ALL, 0, 100, 72000.0, false, false, false, false, "https://cmchistn.com/"),
                new Scheme(null, "Free Laptop Scheme", "Free laptops to students scoring 60%+ in Class 12 state government schools.", CategoryType.EDUCATION, "Tamil Nadu", GenderType.ALL, CasteType.ALL, 15, 20, null, false, false, false, false, "https://tnschools.gov.in/"),
                new Scheme(null, "Marriage Assistance Scheme Tamil Nadu", "Rs.50000 assistance for first marriage of women from poor families via TAHDCO.", CategoryType.SOCIAL_WELFARE, "Tamil Nadu", GenderType.FEMALE, CasteType.SC, 18, 30, 100000.0, false, false, false, false, "https://tahdco.tn.gov.in/"),
                new Scheme(null, "Girl Child Protection Scheme", "Rs.50000 deposited in fixed deposit at birth of girl child; matures at age 21 on completion of education.", CategoryType.WOMEN_CHILD_WELFARE, "Tamil Nadu", GenderType.FEMALE, CasteType.ALL, 0, 1, 100000.0, false, false, false, false, "https://tngovernment.in/"),
                new Scheme(null, "Free Bus Pass for Women Tamil Nadu", "Free bus passes for all women for unrestricted travel on state-run buses.", CategoryType.SOCIAL_WELFARE, "Tamil Nadu", GenderType.FEMALE, CasteType.ALL, 18, 100, null, false, false, false, false, "https://tnstc.in/"),
                new Scheme(null, "Noon Meal Scheme", "Free nutritious meals and snacks for students in government schools daily.", CategoryType.FOOD, "Tamil Nadu", GenderType.ALL, CasteType.ALL, 2, 18, null, false, false, false, false, "https://tnschools.gov.in/"),
                new Scheme(null, "Kalaignar Housing Scheme", "Subsidized pucca houses for SC/ST and BPL families in rural Tamil Nadu.", CategoryType.HOUSING, "Tamil Nadu", GenderType.ALL, CasteType.SC, 18, 70, 200000.0, false, true, false, false, "https://tnhb.gov.in/")
            );
            tn.forEach(s -> save(repo, s));

            // KERALA
            List<Scheme> kl = Arrays.asList(
                new Scheme(null, "Karunya Health Scheme", "Financial assistance up to Rs.2 lakh for critical illnesses to BPL families.", CategoryType.HEALTH, "Kerala", GenderType.ALL, CasteType.ALL, 0, 100, null, false, true, false, false, "https://karunyakerala.org/"),
                new Scheme(null, "Kudumbashree", "Women empowerment through self-help groups and microfinance in Kerala.", CategoryType.WOMEN_CHILD_WELFARE, "Kerala", GenderType.FEMALE, CasteType.ALL, 18, 60, null, false, true, false, false, "https://kudumbashree.org/"),
                new Scheme(null, "LIFE Mission Housing Scheme", "Pucca housing for the homeless and landless in Kerala.", CategoryType.HOUSING, "Kerala", GenderType.ALL, CasteType.ALL, 18, 100, null, false, true, false, false, "https://lifemission.kerala.gov.in/"),
                new Scheme(null, "Kerala Pension Scheme", "Monthly pension for elderly, widows, unmarried women and differently-abled.", CategoryType.SOCIAL_WELFARE, "Kerala", GenderType.ALL, CasteType.ALL, 60, 100, null, true, false, true, false, "https://kerala.gov.in/"),
                new Scheme(null, "Medisep Health Scheme", "Group health insurance for state government employees and pensioners.", CategoryType.HEALTH, "Kerala", GenderType.ALL, CasteType.ALL, 0, 100, null, false, false, false, false, "https://medisep.kerala.gov.in/"),
                new Scheme(null, "Vidya Kiranam", "Scholarship for students from BPL families to continue higher education.", CategoryType.EDUCATION, "Kerala", GenderType.ALL, CasteType.ALL, 18, 26, null, false, true, false, false, "https://dcescholarship.kerala.gov.in/"),
                new Scheme(null, "Kerala Startup Mission", "Support for tech startups including funding, mentoring, and incubation facilities.", CategoryType.ENTREPRENEURSHIP, "Kerala", GenderType.ALL, CasteType.ALL, 18, 45, null, false, false, false, false, "https://startupmission.kerala.gov.in/"),
                new Scheme(null, "Kerala Farmer Pension", "Monthly pension of Rs.1500 for farmers aged 60+ in Kerala.", CategoryType.AGRICULTURE, "Kerala", GenderType.ALL, CasteType.ALL, 60, 100, null, false, false, false, false, "https://agri.kerala.gov.in/"),
                new Scheme(null, "Women Empowerment Scheme Kerala", "Training, credit and market linkage for women entrepreneurs from BPL families.", CategoryType.WOMEN_CHILD_WELFARE, "Kerala", GenderType.FEMALE, CasteType.ALL, 18, 55, null, false, true, false, false, "https://wcd.kerala.gov.in/"),
                new Scheme(null, "Kerala Ration Subsidy", "Subsidized food grains through PDS for BPL and Antyodaya families.", CategoryType.FOOD, "Kerala", GenderType.ALL, CasteType.ALL, 0, 100, null, false, true, false, false, "https://civilsupplieskerala.gov.in/")
            );
            kl.forEach(s -> save(repo, s));

            System.out.println("South India schemes initialization completed!");
        };
    }
}
