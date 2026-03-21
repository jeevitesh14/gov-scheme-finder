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
@Order(5)
public class DataInitializerEast {

    private void save(SchemeRepository r, Scheme s) {
        if (!r.existsByNameAndState(s.getName(), s.getState())) r.save(s);
    }

    @Bean("eastRunner")
    CommandLineRunner initEast(SchemeRepository repo) {
        return args -> {
            // WEST BENGAL
            List<Scheme> wb = Arrays.asList(
                new Scheme(null, "Kanyashree Prakalpa", "Annual scholarship Rs.750 and one-time Rs.25000 for unmarried girl students aged 13-18.", CategoryType.EDUCATION, "West Bengal", GenderType.FEMALE, CasteType.ALL, 13, 18, 120000.0, false, false, false, false, "https://wbkanyashree.gov.in/"),
                new Scheme(null, "Sabuj Sathi", "Free bicycles for students in classes 9-12 at government schools to reduce dropout.", CategoryType.EDUCATION, "West Bengal", GenderType.ALL, CasteType.ALL, 13, 18, null, false, false, false, false, "https://sabujsathi.wb.gov.in/"),
                new Scheme(null, "Swasthya Sathi", "Rs.5 lakh family health insurance for all West Bengal residents including hospitalization.", CategoryType.HEALTH, "West Bengal", GenderType.ALL, CasteType.ALL, 0, 100, null, false, false, false, false, "https://swasthyasathi.gov.in/"),
                new Scheme(null, "Rupashree Prakalpa", "One-time Rs.25000 grant for marriage of economically weaker girls from WB families.", CategoryType.SOCIAL_WELFARE, "West Bengal", GenderType.FEMALE, CasteType.ALL, 18, 30, 150000.0, false, false, false, false, "https://wb.gov.in/"),
                new Scheme(null, "Krishak Bandhu", "Rs.10000/year per acre financial support and Rs.2 lakh death benefit for WB farmers.", CategoryType.AGRICULTURE, "West Bengal", GenderType.ALL, CasteType.ALL, 18, 70, null, false, false, false, false, "https://krishakbandhu.net/"),
                new Scheme(null, "Pension Scheme West Bengal", "Monthly pension for elderly (60+), widows, and differently-abled living in West Bengal.", CategoryType.SOCIAL_WELFARE, "West Bengal", GenderType.ALL, CasteType.ALL, 60, 100, null, true, false, true, false, "https://wb.gov.in/"),
                new Scheme(null, "Housing Scheme West Bengal", "Geetanjali and Nijashree pucca housing schemes for homeless BPL families in WB.", CategoryType.HOUSING, "West Bengal", GenderType.ALL, CasteType.ALL, 18, 65, null, false, true, false, false, "https://wb.gov.in/"),
                new Scheme(null, "Scholarship Scheme West Bengal", "Aikyashree scholarship for minority students and Oasis for SC/ST students in WB.", CategoryType.EDUCATION, "West Bengal", GenderType.ALL, CasteType.ALL, 17, 30, 200000.0, false, false, false, false, "https://wbmdfcscholarship.com/"),
                new Scheme(null, "Skill Development Scheme West Bengal", "Utkarsh Bangla vocational training for youth in 30+ trades across WB districts.", CategoryType.SKILL_DEVELOPMENT, "West Bengal", GenderType.ALL, CasteType.ALL, 18, 35, null, false, false, false, false, "https://wbssdm.gov.in/"),
                new Scheme(null, "Ration Scheme West Bengal", "Free or subsidized food grains under NFSA for AAY, BPL/PHH households in West Bengal.", CategoryType.FOOD, "West Bengal", GenderType.ALL, CasteType.ALL, 0, 100, null, false, true, false, false, "https://wb.gov.in/")
            );
            wb.forEach(s -> save(repo, s));

            // BIHAR
            List<Scheme> br = Arrays.asList(
                new Scheme(null, "Mukhyamantri Kanya Utthan Yojana", "Rs.50000 total in installments for girl child from birth to graduation in Bihar.", CategoryType.WOMEN_CHILD_WELFARE, "Bihar", GenderType.FEMALE, CasteType.ALL, 0, 21, 150000.0, false, false, false, false, "https://medhasoft.bih.nic.in/"),
                new Scheme(null, "Student Credit Card Scheme", "Education loan up to Rs.4 lakh at 4% interest for higher education for Bihar students.", CategoryType.EDUCATION, "Bihar", GenderType.ALL, CasteType.ALL, 17, 25, null, false, false, false, false, "https://7nishchay-yuvaupmission.bihar.gov.in/"),
                new Scheme(null, "Bicycle Scheme Bihar", "Free bicycles for class 9 girls to reduce school dropout rates in rural Bihar.", CategoryType.EDUCATION, "Bihar", GenderType.FEMALE, CasteType.ALL, 13, 16, null, false, false, false, false, "https://state.bihar.gov.in/"),
                new Scheme(null, "Scholarship Scheme Bihar", "Post-matric scholarship for SC/ST/OBC/EBC students for professional courses in Bihar.", CategoryType.EDUCATION, "Bihar", GenderType.ALL, CasteType.ALL, 17, 30, 200000.0, false, false, false, false, "https://scholarships.gov.in/"),
                new Scheme(null, "Pension Scheme Bihar", "Mukhyamantri Vridhjan Pension Yojana of Rs.400-500/month for elderly 60+ in Bihar.", CategoryType.SOCIAL_WELFARE, "Bihar", GenderType.ALL, CasteType.ALL, 60, 100, null, false, false, false, false, "https://sspmis.in/"),
                new Scheme(null, "Health Scheme Bihar", "Ayushman Bharat and Bihar Rajya Fasal Sahayata Yojana health and crop assistance.", CategoryType.HEALTH, "Bihar", GenderType.ALL, CasteType.ALL, 0, 100, null, false, false, false, false, "https://nha.gov.in/"),
                new Scheme(null, "Housing Scheme Bihar", "Bihar Rural Housing Scheme for homeless rural BPL families with financial assistance.", CategoryType.HOUSING, "Bihar", GenderType.ALL, CasteType.ALL, 18, 65, null, false, true, false, false, "https://pmayg.nic.in/"),
                new Scheme(null, "Skill Development Scheme Bihar", "Bihar Skill Development Mission free training for 15-35 age youth in manufacturing and services.", CategoryType.SKILL_DEVELOPMENT, "Bihar", GenderType.ALL, CasteType.ALL, 15, 35, null, false, false, false, false, "https://skillmissionbihar.org/"),
                new Scheme(null, "Women Welfare Scheme Bihar", "Jeeviaka – Bihar Rural Livelihoods Project for women SHG economic empowerment.", CategoryType.WOMEN_CHILD_WELFARE, "Bihar", GenderType.FEMALE, CasteType.ALL, 18, 55, null, false, false, false, false, "https://brlp.in/"),
                new Scheme(null, "Ration Scheme Bihar", "Free 5 kg food grain per person/month for priority and AAY households under NFSA.", CategoryType.FOOD, "Bihar", GenderType.ALL, CasteType.ALL, 0, 100, null, false, true, false, false, "https://sfc.bihar.gov.in/")
            );
            br.forEach(s -> save(repo, s));

            // ODISHA
            List<Scheme> od = Arrays.asList(
                new Scheme(null, "KALIA Scheme", "Rs.25000/year income support for Odisha farmers and Rs.12500 for landless cultivators.", CategoryType.AGRICULTURE, "Odisha", GenderType.ALL, CasteType.ALL, 18, 70, null, false, false, false, false, "https://kalia.odisha.gov.in/"),
                new Scheme(null, "Biju Swasthya Kalyan Yojana", "Free healthcare for all Odisha residents; women get Rs.10 lakh/year coverage.", CategoryType.HEALTH, "Odisha", GenderType.ALL, CasteType.ALL, 0, 100, null, false, false, false, false, "https://bsky.odisha.gov.in/"),
                new Scheme(null, "Mission Shakti", "Livelihood support, microcredit, and economic empowerment for women SHG members in Odisha.", CategoryType.WOMEN_CHILD_WELFARE, "Odisha", GenderType.FEMALE, CasteType.ALL, 18, 55, null, false, false, false, false, "https://missionshakti.odisha.gov.in/"),
                new Scheme(null, "Pension Scheme Odisha", "Madhu Babu Pension of Rs.500/month for elderly, widows including TB and HIV patients.", CategoryType.SOCIAL_WELFARE, "Odisha", GenderType.ALL, CasteType.ALL, 60, 100, null, false, false, true, false, "https://ssepd.gov.in/"),
                new Scheme(null, "Scholarship Scheme Odisha", "Prerana, Medhabruti and other state scholarships for SC/ST/OBC meritorious students.", CategoryType.EDUCATION, "Odisha", GenderType.ALL, CasteType.ALL, 17, 30, 200000.0, false, false, false, false, "https://scholarships.gov.in/"),
                new Scheme(null, "Housing Scheme Odisha", "Biju Pucca Ghar Yojana providing Rs.1.3 lakh for pucca house to BPL households.", CategoryType.HOUSING, "Odisha", GenderType.ALL, CasteType.ALL, 18, 65, null, false, true, false, false, "https://rhd.odisha.gov.in/"),
                new Scheme(null, "Skill Development Scheme Odisha", "Odisha Skill Development Authority training in technology, construction, and services.", CategoryType.SKILL_DEVELOPMENT, "Odisha", GenderType.ALL, CasteType.ALL, 15, 35, null, false, false, false, false, "https://osda.in/"),
                new Scheme(null, "Farmer Scheme Odisha", "Crop insurance and compensation for Odisha farmers under PMFBY for kharif and rabi.", CategoryType.AGRICULTURE, "Odisha", GenderType.ALL, CasteType.ALL, 18, 70, null, false, false, false, false, "https://agriodisha.nic.in/"),
                new Scheme(null, "Women Welfare Scheme Odisha", "Mamata maternity benefit of Rs.5000 to all pregnant women in Odisha.", CategoryType.WOMEN_CHILD_WELFARE, "Odisha", GenderType.FEMALE, CasteType.ALL, 18, 45, null, false, false, false, false, "https://odisha.gov.in/"),
                new Scheme(null, "Ration Scheme Odisha", "Free 5 kg rice per person per month under Odisha Food Security Scheme.", CategoryType.FOOD, "Odisha", GenderType.ALL, CasteType.ALL, 0, 100, null, false, true, false, false, "https://food.odisha.gov.in/")
            );
            od.forEach(s -> save(repo, s));

            // JHARKHAND
            List<Scheme> jh = Arrays.asList(
                new Scheme(null, "Mukhyamantri Krishi Ashirwad Yojana", "Rs.5000/acre per year financial assistance for paddy farmers in Jharkhand.", CategoryType.AGRICULTURE, "Jharkhand", GenderType.ALL, CasteType.ALL, 18, 70, null, false, false, false, false, "https://mmkay.jharkhand.gov.in/"),
                new Scheme(null, "Sarva Shiksha Scholarship", "Free education and scholarships for tribal children in government schools in Jharkhand.", CategoryType.EDUCATION, "Jharkhand", GenderType.ALL, CasteType.ST, 6, 18, null, false, false, false, false, "https://jac.jharkhand.gov.in/"),
                new Scheme(null, "Pension Scheme Jharkhand", "Monthly pension for old age, widow, and disabled with Rs.1000/month in Jharkhand.", CategoryType.SOCIAL_WELFARE, "Jharkhand", GenderType.ALL, CasteType.ALL, 60, 100, null, true, false, true, false, "https://jharkhand.gov.in/"),
                new Scheme(null, "Health Scheme Jharkhand", "Mukhyamantri Swasthya Bima Yojana providing Rs.5 lakh coverage for Jharkhand families.", CategoryType.HEALTH, "Jharkhand", GenderType.ALL, CasteType.ALL, 0, 100, null, false, true, false, false, "https://jharkhand.gov.in/"),
                new Scheme(null, "Housing Scheme Jharkhand", "Abua Awas Yojana for three-room pucca houses for homeless BPL families in Jharkhand.", CategoryType.HOUSING, "Jharkhand", GenderType.ALL, CasteType.ALL, 18, 65, null, false, true, false, false, "https://jharkhand.gov.in/"),
                new Scheme(null, "Skill Development Scheme Jharkhand", "JSDMS training in mining, construction, and hospitality sectors for rural youth.", CategoryType.SKILL_DEVELOPMENT, "Jharkhand", GenderType.ALL, CasteType.ALL, 18, 35, null, false, false, false, false, "https://jsdms.jharkhand.gov.in/"),
                new Scheme(null, "Women Welfare Scheme Jharkhand", "Savitribai Phule Kishori Samridhi Yojana providing incentives for girl education in JH.", CategoryType.WOMEN_CHILD_WELFARE, "Jharkhand", GenderType.FEMALE, CasteType.ALL, 0, 21, 80000.0, false, false, false, false, "https://jharkhand.gov.in/"),
                new Scheme(null, "Farmer Scheme Jharkhand", "Fasal Rahat Yojana crop compensation for natural disaster-affected farmers in JH.", CategoryType.AGRICULTURE, "Jharkhand", GenderType.ALL, CasteType.ALL, 18, 70, null, false, false, false, false, "https://jrfry.jharkhand.gov.in/"),
                new Scheme(null, "Ration Scheme Jharkhand", "Free 5 kg grain per person under NFSA for Priority and AAY card holders in JH.", CategoryType.FOOD, "Jharkhand", GenderType.ALL, CasteType.ALL, 0, 100, null, false, true, false, false, "https://aahar.jharkhand.gov.in/"),
                new Scheme(null, "Employment Scheme Jharkhand", "MGNREGS with 100-day guaranteed employment for rural households in Jharkhand.", CategoryType.EMPLOYMENT, "Jharkhand", GenderType.ALL, CasteType.ALL, 18, 60, null, false, false, false, false, "https://nrega.nic.in/")
            );
            jh.forEach(s -> save(repo, s));

            // ASSAM
            List<Scheme> as = Arrays.asList(
                new Scheme(null, "Orunodoi Scheme", "Rs.830/month direct benefit transfer to women from below-threshold families in Assam.", CategoryType.WOMEN_CHILD_WELFARE, "Assam", GenderType.FEMALE, CasteType.ALL, 18, 60, 200000.0, false, false, false, false, "https://orunodoi.assam.gov.in/"),
                new Scheme(null, "Arunodoi 2.0", "Enhanced Orunodoi with Rs.1250/month for eligible women below economic threshold.", CategoryType.WOMEN_CHILD_WELFARE, "Assam", GenderType.FEMALE, CasteType.ALL, 18, 60, 200000.0, false, false, false, false, "https://orunodoi.assam.gov.in/"),
                new Scheme(null, "Swasthya Sathi Scheme", "Health insurance up to Rs.5 lakh under Ayushman Bharat for Assam BPL families.", CategoryType.HEALTH, "Assam", GenderType.ALL, CasteType.ALL, 0, 100, null, false, true, false, false, "https://nha.gov.in/"),
                new Scheme(null, "Pension Scheme Assam", "Monthly social security pension for aged, widows, and handicapped in Assam.", CategoryType.SOCIAL_WELFARE, "Assam", GenderType.ALL, CasteType.ALL, 60, 100, null, true, false, true, false, "https://assam.gov.in/"),
                new Scheme(null, "Scholarship Scheme Assam", "Pragyan Bharati scholarship for meritorious poor students and SC/ST stipends in Assam.", CategoryType.EDUCATION, "Assam", GenderType.ALL, CasteType.ALL, 17, 30, 200000.0, false, false, false, false, "https://scholarships.gov.in/"),
                new Scheme(null, "Housing Scheme Assam", "Amar Ghar scheme providing Rs.2.1 lakh for homeless BPL families to build pucca houses.", CategoryType.HOUSING, "Assam", GenderType.ALL, CasteType.ALL, 18, 65, null, false, true, false, false, "https://assam.gov.in/"),
                new Scheme(null, "Skill Development Scheme Assam", "Assam Skill Development Mission free training for 15-35 year unemployed youth in Assam.", CategoryType.SKILL_DEVELOPMENT, "Assam", GenderType.ALL, CasteType.ALL, 15, 35, null, false, false, false, false, "https://asdm.assam.gov.in/"),
                new Scheme(null, "Women Welfare Scheme Assam", "Mukhya Mantri Mahila Udyamita Abhiyan for women entrepreneurship support in Assam.", CategoryType.WOMEN_CHILD_WELFARE, "Assam", GenderType.FEMALE, CasteType.ALL, 18, 55, null, false, false, false, false, "https://assam.gov.in/"),
                new Scheme(null, "Farmer Scheme Assam", "Assam Bhavantar Bhugtan Yojana price support for farmers with crop price protection.", CategoryType.AGRICULTURE, "Assam", GenderType.ALL, CasteType.ALL, 18, 70, null, false, false, false, false, "https://agri.assam.gov.in/"),
                new Scheme(null, "Ration Scheme Assam", "Free 5 kg food grain per person/month under NFSA for AAY and PHH in Assam.", CategoryType.FOOD, "Assam", GenderType.ALL, CasteType.ALL, 0, 100, null, false, true, false, false, "https://fcsca.assam.gov.in/")
            );
            as.forEach(s -> save(repo, s));

            // CHHATTISGARH
            List<Scheme> cg = Arrays.asList(
                new Scheme(null, "Rajiv Gandhi Kisan Nyay Yojana", "Input subsidy support up to Rs.9000/acre for paddy, maize, and sugarcane farmers in CG.", CategoryType.AGRICULTURE, "Chhattisgarh", GenderType.ALL, CasteType.ALL, 18, 70, null, false, false, false, false, "https://rgkny.cg.nic.in/"),
                new Scheme(null, "Godhan Nyay Yojana", "Rs.2/kg purchase of cow dung from farmers and cattle owners for vermicompost production.", CategoryType.AGRICULTURE, "Chhattisgarh", GenderType.ALL, CasteType.ALL, 18, 70, null, false, false, false, false, "https://godhannyay.cgstate.gov.in/"),
                new Scheme(null, "Chief Minister Health Scheme CG", "Universal health coverage up to Rs.5 lakh for all CG families via Dr. Khubchand Scheme.", CategoryType.HEALTH, "Chhattisgarh", GenderType.ALL, CasteType.ALL, 0, 100, null, false, false, false, false, "https://dkbssy.cg.nic.in/"),
                new Scheme(null, "Pension Scheme Chhattisgarh", "Sukhad Sahara and Mukhyamantri Pensions for elderly, widow, and disabled in CG.", CategoryType.SOCIAL_WELFARE, "Chhattisgarh", GenderType.ALL, CasteType.ALL, 60, 100, null, true, false, true, false, "https://sw.cg.gov.in/"),
                new Scheme(null, "Scholarship Scheme Chhattisgarh", "Pre and post-matric scholarships for SC/ST students pursuing professional courses in CG.", CategoryType.EDUCATION, "Chhattisgarh", GenderType.ALL, CasteType.ALL, 17, 30, 200000.0, false, false, false, false, "https://scholarships.gov.in/"),
                new Scheme(null, "Housing Scheme Chhattisgarh", "Mukhyamantri Awas scheme for BPL homeless families in CG with pucca house support.", CategoryType.HOUSING, "Chhattisgarh", GenderType.ALL, CasteType.ALL, 18, 65, null, false, true, false, false, "https://pmayg.nic.in/"),
                new Scheme(null, "Skill Development Scheme CG", "Kaushalya Ek Yojana free skill training and employment placement for CG youth.", CategoryType.SKILL_DEVELOPMENT, "Chhattisgarh", GenderType.ALL, CasteType.ALL, 18, 35, null, false, false, false, false, "https://cgsdm.in/"),
                new Scheme(null, "Women Welfare Scheme CG", "Mukhyamantri Kanyadan Yojana providing Rs.25000 for marriage of poor girls in CG.", CategoryType.WOMEN_CHILD_WELFARE, "Chhattisgarh", GenderType.FEMALE, CasteType.ALL, 18, 30, 100000.0, false, false, false, false, "https://cgstate.gov.in/"),
                new Scheme(null, "Farmer Support Scheme CG", "Crop insurance, minimum support price, and input subsidy for CG paddy and oilseed farmers.", CategoryType.AGRICULTURE, "Chhattisgarh", GenderType.ALL, CasteType.ALL, 18, 70, null, false, false, false, false, "https://agri.cg.gov.in/"),
                new Scheme(null, "Ration Scheme Chhattisgarh", "2 kg rice at Rs.1 per kg for AAY, BPL, and APL card holders under Chhattisgarh FCS.", CategoryType.FOOD, "Chhattisgarh", GenderType.ALL, CasteType.ALL, 0, 100, null, false, true, false, false, "https://khadya.cg.nic.in/")
            );
            cg.forEach(s -> save(repo, s));

            System.out.println("East India schemes initialization completed!");
        };
    }
}
