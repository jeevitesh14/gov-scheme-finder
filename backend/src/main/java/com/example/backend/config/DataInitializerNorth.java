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
@Order(4)
public class DataInitializerNorth {

    private void save(SchemeRepository r, Scheme s) {
        if (!r.existsByNameAndState(s.getName(), s.getState())) r.save(s);
    }

    @Bean("northRunner")
    CommandLineRunner initNorth(SchemeRepository repo) {
        return args -> {
            // UTTAR PRADESH
            List<Scheme> up = Arrays.asList(
                new Scheme(null, "UP Kisan Samman Nidhi", "Rs.6000/year direct income support for small and marginal farmers in UP.", CategoryType.AGRICULTURE, "Uttar Pradesh", GenderType.ALL, CasteType.ALL, 18, 70, null, false, false, false, false, "https://pmkisan.gov.in/"),
                new Scheme(null, "UP Free Laptop Scheme", "Free laptops for students passing class 10 and 12 with 65%+ marks from government schools.", CategoryType.EDUCATION, "Uttar Pradesh", GenderType.ALL, CasteType.ALL, 14, 20, null, false, false, false, false, "https://upcmo.up.nic.in/"),
                new Scheme(null, "UP Scholarship Scheme", "Pre and post-matric scholarships for SC/ST/OBC/minority students in higher education.", CategoryType.EDUCATION, "Uttar Pradesh", GenderType.ALL, CasteType.ALL, 6, 28, 250000.0, false, false, false, false, "https://scholarship.up.gov.in/"),
                new Scheme(null, "UP Pension Scheme", "Monthly pension for elderly, widows, differently-abled and leprosy patients in UP.", CategoryType.SOCIAL_WELFARE, "Uttar Pradesh", GenderType.ALL, CasteType.ALL, 60, 100, null, true, true, true, false, "https://sspy-up.gov.in/"),
                new Scheme(null, "UP Housing Scheme", "Pradhan Mantri Awas Yojana implementation for homeless BPL families in UP.", CategoryType.HOUSING, "Uttar Pradesh", GenderType.ALL, CasteType.ALL, 18, 65, null, false, true, false, false, "https://pmayg.nic.in/"),
                new Scheme(null, "UP Skill Development Mission", "Free vocational training in 150+ sectors for unemployed urban and rural youth.", CategoryType.SKILL_DEVELOPMENT, "Uttar Pradesh", GenderType.ALL, CasteType.ALL, 14, 35, null, false, false, false, false, "https://upsdm.gov.in/"),
                new Scheme(null, "UP Health Scheme", "Free treatment for BPL families under Atal Ayushman UP scheme up to Rs.5 lakh.", CategoryType.HEALTH, "Uttar Pradesh", GenderType.ALL, CasteType.ALL, 0, 100, null, false, true, false, false, "https://atalayushmanup.org/"),
                new Scheme(null, "UP Women Empowerment Scheme", "Mission Shakti program for safety, security, and economic empowerment of women in UP.", CategoryType.WOMEN_CHILD_WELFARE, "Uttar Pradesh", GenderType.FEMALE, CasteType.ALL, 18, 55, null, false, false, false, false, "https://mahilakalyan.up.nic.in/"),
                new Scheme(null, "UP Ration Scheme", "Subsidized food grains at Rs.2/kg wheat and Rs.3/kg rice under NFSA for BPL families.", CategoryType.FOOD, "Uttar Pradesh", GenderType.ALL, CasteType.ALL, 0, 100, null, false, true, false, false, "https://fcs.up.gov.in/"),
                new Scheme(null, "UP Farmer Insurance Scheme", "Crop insurance for wheat, paddy, and sugarcane farmers under PMFBY in UP.", CategoryType.AGRICULTURE, "Uttar Pradesh", GenderType.ALL, CasteType.ALL, 18, 70, null, false, false, false, false, "https://pmfby.gov.in/")
            );
            up.forEach(s -> save(repo, s));

            // DELHI
            List<Scheme> dl = Arrays.asList(
                new Scheme(null, "Delhi Health Scheme", "Free OPD, IPD, and medicines at government hospitals under Mohalla Clinic scheme.", CategoryType.HEALTH, "Delhi", GenderType.ALL, CasteType.ALL, 0, 100, null, false, false, false, false, "https://health.delhigovt.nic.in/"),
                new Scheme(null, "Delhi Free Electricity Scheme", "0 bill for consumption up to 200 units; 50% subsidy for 201-400 units for domestic consumers.", CategoryType.OTHER, "Delhi", GenderType.ALL, CasteType.ALL, 18, 100, null, false, false, false, false, "https://bsesdelhi.com/"),
                new Scheme(null, "Delhi Free Water Scheme", "20,000 litres of free water per month for all Delhi households.", CategoryType.OTHER, "Delhi", GenderType.ALL, CasteType.ALL, 0, 100, null, false, false, false, false, "https://delhijalboard.nic.in/"),
                new Scheme(null, "Delhi Education Scheme", "Free quality education at Delhi government schools with modern infrastructure.", CategoryType.EDUCATION, "Delhi", GenderType.ALL, CasteType.ALL, 5, 18, null, false, false, false, false, "https://edudel.nic.in/"),
                new Scheme(null, "Delhi Skill Development Scheme", "Free skill training in 75+ trades under Delhi Skill and Entrepreneurship University.", CategoryType.SKILL_DEVELOPMENT, "Delhi", GenderType.ALL, CasteType.ALL, 15, 35, null, false, false, false, false, "https://dseu.ac.in/"),
                new Scheme(null, "Delhi Women Safety Scheme", "Women helpline 181, safety apps, and safe transport initiatives for women of Delhi.", CategoryType.WOMEN_CHILD_WELFARE, "Delhi", GenderType.FEMALE, CasteType.ALL, 18, 100, null, false, false, false, false, "https://wcd.delhigovt.nic.in/"),
                new Scheme(null, "Delhi Pension Scheme", "Monthly pension for elderly, widow, and differently-abled Delhi residents.", CategoryType.SOCIAL_WELFARE, "Delhi", GenderType.ALL, CasteType.ALL, 60, 100, null, true, false, true, false, "https://delhi.gov.in/"),
                new Scheme(null, "Delhi Housing Scheme", "Affordable flats for EWS/LIG families under Delhi Development Authority housing scheme.", CategoryType.HOUSING, "Delhi", GenderType.ALL, CasteType.ALL, 21, 65, 300000.0, false, false, false, false, "https://dda.org.in/"),
                new Scheme(null, "Delhi Startup Policy", "Seed funding, mentoring, and incubation support for tech and social startups in Delhi.", CategoryType.ENTREPRENEURSHIP, "Delhi", GenderType.ALL, CasteType.ALL, 18, 45, null, false, false, false, false, "https://startupindia.gov.in/"),
                new Scheme(null, "Delhi Ration Scheme", "5kg subsidized grains per person under NFSA for AAY and Priority Household families.", CategoryType.FOOD, "Delhi", GenderType.ALL, CasteType.ALL, 0, 100, null, false, true, false, false, "https://ration.jantasamvad.org/")
            );
            dl.forEach(s -> save(repo, s));

            // MADHYA PRADESH
            List<Scheme> mp = Arrays.asList(
                new Scheme(null, "Ladli Laxmi Yojana MP", "Rs.1.18 lakh financial benefit for girl child in installments from birth to graduation.", CategoryType.WOMEN_CHILD_WELFARE, "Madhya Pradesh", GenderType.FEMALE, CasteType.ALL, 0, 21, 80000.0, false, false, false, false, "https://ladlilaxmi.mp.gov.in/"),
                new Scheme(null, "Sambal Yojana", "Comprehensive social security for unorganized workers including health, education and death benefits.", CategoryType.SOCIAL_WELFARE, "Madhya Pradesh", GenderType.ALL, CasteType.ALL, 18, 60, null, false, false, false, false, "https://sambal.mp.gov.in/"),
                new Scheme(null, "Farmer Loan Waiver MP", "Crop loan waiver up to Rs.2 lakh for farmers under debt in Madhya Pradesh.", CategoryType.AGRICULTURE, "Madhya Pradesh", GenderType.ALL, CasteType.ALL, 18, 70, null, false, false, false, false, "https://mp.gov.in/"),
                new Scheme(null, "Pension Scheme MP", "Rs.600/month pension for old age, widows, and differently-abled in Madhya Pradesh.", CategoryType.SOCIAL_WELFARE, "Madhya Pradesh", GenderType.ALL, CasteType.ALL, 60, 100, null, true, true, true, false, "https://socialsecurity.mp.gov.in/"),
                new Scheme(null, "Scholarship Scheme MP", "Post-matric scholarship for SC/ST/OBC students for maintaining continuity in education.", CategoryType.EDUCATION, "Madhya Pradesh", GenderType.ALL, CasteType.ALL, 17, 30, 200000.0, false, false, false, false, "https://scholarshipportal.mp.nic.in/"),
                new Scheme(null, "Health Scheme MP", "Deen Dayal Antyodaya Upchar Yojana providing free treatment to BPL families.", CategoryType.HEALTH, "Madhya Pradesh", GenderType.ALL, CasteType.ALL, 0, 100, null, false, true, false, false, "https://mp.gov.in/"),
                new Scheme(null, "Housing Scheme MP", "Free pucca houses for homeless BPL and SC/ST families in Madhya Pradesh.", CategoryType.HOUSING, "Madhya Pradesh", GenderType.ALL, CasteType.ALL, 18, 65, null, false, true, false, false, "https://pmayg.nic.in/"),
                new Scheme(null, "Skill Development Scheme MP", "Mukhyamantri Kaushal Samvardhan Yojana for free skill training for unemployed youth.", CategoryType.SKILL_DEVELOPMENT, "Madhya Pradesh", GenderType.ALL, CasteType.ALL, 15, 30, null, false, false, false, false, "https://mpskills.gov.in/"),
                new Scheme(null, "Women Welfare Scheme MP", "Tejaswini scheme for self-employment and livelihood support for women in rural MP.", CategoryType.WOMEN_CHILD_WELFARE, "Madhya Pradesh", GenderType.FEMALE, CasteType.ALL, 18, 50, null, false, false, false, false, "https://mp.gov.in/"),
                new Scheme(null, "Ration Scheme MP", "Subsidized food grains for AAY/BPL families under the National Food Security Act.", CategoryType.FOOD, "Madhya Pradesh", GenderType.ALL, CasteType.ALL, 0, 100, null, false, true, false, false, "https://food.mp.gov.in/")
            );
            mp.forEach(s -> save(repo, s));

            // PUNJAB
            List<Scheme> pb = Arrays.asList(
                new Scheme(null, "Shagun Scheme", "Rs.51000 grant for marriage of girls from BPL SC families in Punjab.", CategoryType.SOCIAL_WELFARE, "Punjab", GenderType.FEMALE, CasteType.SC, 18, 30, 100000.0, false, true, false, false, "https://punjab.gov.in/"),
                new Scheme(null, "Atta Dal Scheme", "Free 5 kg atta and 1 kg dal per person per month to BPL families in Punjab.", CategoryType.FOOD, "Punjab", GenderType.ALL, CasteType.ALL, 0, 100, null, false, true, false, false, "https://punjab.gov.in/"),
                new Scheme(null, "Health Insurance Scheme Punjab", "Sarbat Sehat Bima Yojana providing Rs.5 lakh health cover to all Punjab families.", CategoryType.HEALTH, "Punjab", GenderType.ALL, CasteType.ALL, 0, 100, null, false, false, false, false, "https://sha.punjab.gov.in/"),
                new Scheme(null, "Pension Scheme Punjab", "Monthly pension for old age, widow, and differently-abled beneficiaries in Punjab.", CategoryType.SOCIAL_WELFARE, "Punjab", GenderType.ALL, CasteType.ALL, 58, 100, null, true, true, true, false, "https://punjab.gov.in/"),
                new Scheme(null, "Scholarship Scheme Punjab", "Merit-cum-means scholarship for SC/OBC students in professional and technical courses.", CategoryType.EDUCATION, "Punjab", GenderType.ALL, CasteType.ALL, 17, 30, 200000.0, false, false, false, false, "https://scholarship.punjab.gov.in/"),
                new Scheme(null, "Housing Scheme Punjab", "PMAY implementation for homeless and BPL rural and urban families in Punjab.", CategoryType.HOUSING, "Punjab", GenderType.ALL, CasteType.ALL, 18, 65, null, false, true, false, false, "https://housing.punjab.gov.in/"),
                new Scheme(null, "Skill Development Scheme Punjab", "Punjab Skill Development Mission free training and certification for unemployed youth.", CategoryType.SKILL_DEVELOPMENT, "Punjab", GenderType.ALL, CasteType.ALL, 15, 35, null, false, false, false, false, "https://psdm.gov.in/"),
                new Scheme(null, "Farmer Support Scheme Punjab", "Crop insurance, input subsidies, and debt relief schemes for Punjab farmers.", CategoryType.AGRICULTURE, "Punjab", GenderType.ALL, CasteType.ALL, 18, 70, null, false, false, false, false, "https://agripb.gov.in/"),
                new Scheme(null, "Women Welfare Scheme Punjab", "Bibi Bhani scheme for skill development, microcredit, and economic empowerment of women.", CategoryType.WOMEN_CHILD_WELFARE, "Punjab", GenderType.FEMALE, CasteType.ALL, 18, 55, null, false, false, false, false, "https://punjab.gov.in/"),
                new Scheme(null, "Ration Scheme Punjab", "Subsidized food grains under NFSA for AAY, BPL, and priority households in Punjab.", CategoryType.FOOD, "Punjab", GenderType.ALL, CasteType.ALL, 0, 100, null, false, true, false, false, "https://punjab.gov.in/")
            );
            pb.forEach(s -> save(repo, s));

            // HARYANA
            List<Scheme> hr = Arrays.asList(
                new Scheme(null, "Ladli Scheme", "Rs.5000 deposited at birth and annually up to class 10 for girl children in Haryana.", CategoryType.WOMEN_CHILD_WELFARE, "Haryana", GenderType.FEMALE, CasteType.ALL, 0, 18, 200000.0, false, false, false, false, "https://wcdhry.gov.in/"),
                new Scheme(null, "Saksham Yuva Yojana", "Rs.9000/month honorarium for postgraduate unemployed youth on work assignments in Haryana.", CategoryType.EMPLOYMENT, "Haryana", GenderType.ALL, CasteType.ALL, 21, 35, null, false, false, false, false, "https://hreyahs.gov.in/"),
                new Scheme(null, "Pension Scheme Haryana", "Old age samman allowance, widow, and disabled pension for eligible Haryana residents.", CategoryType.SOCIAL_WELFARE, "Haryana", GenderType.ALL, CasteType.ALL, 60, 100, null, true, false, true, false, "https://socialjusticehry.gov.in/"),
                new Scheme(null, "Scholarship Scheme Haryana", "Fee waivers and scholarships for SC/BC students for professional courses in Haryana.", CategoryType.EDUCATION, "Haryana", GenderType.ALL, CasteType.ALL, 17, 30, 200000.0, false, false, false, false, "https://haryanascbc.gov.in/"),
                new Scheme(null, "Health Scheme Haryana", "Ayushman Bharat and state top-up for health insurance up to Rs.5 lakh for Haryana families.", CategoryType.HEALTH, "Haryana", GenderType.ALL, CasteType.ALL, 0, 100, null, false, false, false, false, "https://nha.gov.in/"),
                new Scheme(null, "Housing Scheme Haryana", "Mukhyamantri Awas Yojana for affordable housing for EWS/LIG urban families in Haryana.", CategoryType.HOUSING, "Haryana", GenderType.ALL, CasteType.ALL, 21, 65, 300000.0, false, false, false, false, "https://hsvp.org.in/"),
                new Scheme(null, "Skill Development Scheme Haryana", "Haryana Kaushal Rozgar Nigam training in 30+ trades for unemployed youth.", CategoryType.SKILL_DEVELOPMENT, "Haryana", GenderType.ALL, CasteType.ALL, 18, 35, null, false, false, false, false, "https://hkrn.gov.in/"),
                new Scheme(null, "Farmer Scheme Haryana", "Crop compensation and input subsidies for farmers affected by flood/drought in Haryana.", CategoryType.AGRICULTURE, "Haryana", GenderType.ALL, CasteType.ALL, 18, 70, null, false, false, false, false, "https://agriharyana.gov.in/"),
                new Scheme(null, "Women Welfare Scheme Haryana", "Kishori Shakti Yojana and Mukhyamantri Mahila Shramik Samman scheme for women.", CategoryType.WOMEN_CHILD_WELFARE, "Haryana", GenderType.FEMALE, CasteType.ALL, 18, 55, null, false, false, false, false, "https://wcdhry.gov.in/"),
                new Scheme(null, "Ration Scheme Haryana", "Antyodaya ration of wheat at Rs.2/kg and rice at Rs.3/kg for BPL families.", CategoryType.FOOD, "Haryana", GenderType.ALL, CasteType.ALL, 0, 100, null, false, true, false, false, "https://haryanafood.gov.in/")
            );
            hr.forEach(s -> save(repo, s));

            // HIMACHAL PRADESH
            List<Scheme> hp = Arrays.asList(
                new Scheme(null, "HIMCARE Scheme", "Health insurance up to Rs.5 lakh per annum for families not covered under PMJAY in HP.", CategoryType.HEALTH, "Himachal Pradesh", GenderType.ALL, CasteType.ALL, 0, 100, null, false, false, false, false, "https://hpsbys.in/"),
                new Scheme(null, "Pension Scheme Himachal Pradesh", "Social security pension for old age (Rs.1500/month), widow, and differently-abled in HP.", CategoryType.SOCIAL_WELFARE, "Himachal Pradesh", GenderType.ALL, CasteType.ALL, 60, 100, null, true, false, true, false, "https://edistrict.hp.gov.in/"),
                new Scheme(null, "Scholarship Scheme Himachal Pradesh", "Thakur Sen Negi scholarship for SC/ST/OBC students for higher education in HP.", CategoryType.EDUCATION, "Himachal Pradesh", GenderType.ALL, CasteType.ALL, 17, 28, 200000.0, false, false, false, false, "https://scholarships.gov.in/"),
                new Scheme(null, "Housing Scheme Himachal Pradesh", "PMAY Gramin implementation for homeless rural BPL families in Himachal Pradesh.", CategoryType.HOUSING, "Himachal Pradesh", GenderType.ALL, CasteType.ALL, 18, 65, null, false, true, false, false, "https://pmayg.nic.in/"),
                new Scheme(null, "Skill Development Scheme Himachal Pradesh", "HIMIRA scheme for skill training and entrepreneurship for mountain communities.", CategoryType.SKILL_DEVELOPMENT, "Himachal Pradesh", GenderType.ALL, CasteType.ALL, 18, 35, null, false, false, false, false, "https://himachal.nic.in/"),
                new Scheme(null, "Women Welfare Scheme Himachal Pradesh", "Shakti Doot scheme for women safety and empowerment in remote mountain areas.", CategoryType.WOMEN_CHILD_WELFARE, "Himachal Pradesh", GenderType.FEMALE, CasteType.ALL, 18, 55, null, false, false, false, false, "https://himachal.nic.in/"),
                new Scheme(null, "Farmer Scheme Himachal Pradesh", "Natural farming promotion and horticulture support for apple and vegetable farmers in HP.", CategoryType.AGRICULTURE, "Himachal Pradesh", GenderType.ALL, CasteType.ALL, 18, 70, null, false, false, false, false, "https://hpagriculture.com/"),
                new Scheme(null, "Health Scheme HP", "Sahara scheme providing Rs.3000/month to families managing chronic disease patients at home.", CategoryType.HEALTH, "Himachal Pradesh", GenderType.ALL, CasteType.ALL, 18, 100, null, false, true, false, false, "https://himachal.nic.in/"),
                new Scheme(null, "Employment Scheme Himachal Pradesh", "MGNREGS implementation with 150 days employment guarantee for rural HP households.", CategoryType.EMPLOYMENT, "Himachal Pradesh", GenderType.ALL, CasteType.ALL, 18, 60, null, false, false, false, false, "https://nrega.nic.in/"),
                new Scheme(null, "Ration Scheme Himachal Pradesh", "Free essential food grains and subsidized kerosene under NFSA for BPL households in HP.", CategoryType.FOOD, "Himachal Pradesh", GenderType.ALL, CasteType.ALL, 0, 100, null, false, true, false, false, "https://hpfood.gov.in/")
            );
            hp.forEach(s -> save(repo, s));

            // UTTARAKHAND
            List<Scheme> uk = Arrays.asList(
                new Scheme(null, "Mukhyamantri Swarojgar Yojana", "Subsidized loans up to Rs.25 lakh for self-employment and micro-enterprise setup in Uttarakhand.", CategoryType.ENTREPRENEURSHIP, "Uttarakhand", GenderType.ALL, CasteType.ALL, 18, 45, null, false, false, false, false, "https://msy.uk.gov.in/"),
                new Scheme(null, "Pension Scheme Uttarakhand", "Rs.1200/month pension for elderly (60+), widows, and disabled in Uttarakhand.", CategoryType.SOCIAL_WELFARE, "Uttarakhand", GenderType.ALL, CasteType.ALL, 60, 100, null, true, false, true, false, "https://ssp.uk.gov.in/"),
                new Scheme(null, "Scholarship Scheme Uttarakhand", "Scholarships for SC/ST/OBC and GEN-EWS students pursuing technical and professional courses.", CategoryType.EDUCATION, "Uttarakhand", GenderType.ALL, CasteType.ALL, 17, 30, 200000.0, false, false, false, false, "https://scholarship.uk.gov.in/"),
                new Scheme(null, "Health Scheme Uttarakhand", "Atal Ayushman Uttarakhand providing Rs.5 lakh free health cover for all state families.", CategoryType.HEALTH, "Uttarakhand", GenderType.ALL, CasteType.ALL, 0, 100, null, false, false, false, false, "https://atalayushmanuk.gov.in/"),
                new Scheme(null, "Housing Scheme Uttarakhand", "Mukhyamantri Awas Yojana for pucca houses for BPL and homeless hill families.", CategoryType.HOUSING, "Uttarakhand", GenderType.ALL, CasteType.ALL, 18, 65, null, false, true, false, false, "https://uk.gov.in/"),
                new Scheme(null, "Skill Development Scheme Uttarakhand", "USDC vocational training programs for youth in hospitality, tourism, and construction.", CategoryType.SKILL_DEVELOPMENT, "Uttarakhand", GenderType.ALL, CasteType.ALL, 18, 35, null, false, false, false, false, "https://uksdm.uk.gov.in/"),
                new Scheme(null, "Women Welfare Scheme Uttarakhand", "Nanda Gaura scheme with Rs.51000 for girls from BPL families on marriage.", CategoryType.WOMEN_CHILD_WELFARE, "Uttarakhand", GenderType.FEMALE, CasteType.ALL, 18, 30, 100000.0, false, true, false, false, "https://uk.gov.in/"),
                new Scheme(null, "Farmer Scheme Uttarakhand", "Crop insurance and natural farming support for mountain and terrace farmers in Uttarakhand.", CategoryType.AGRICULTURE, "Uttarakhand", GenderType.ALL, CasteType.ALL, 18, 70, null, false, false, false, false, "https://agriculture.uk.gov.in/"),
                new Scheme(null, "Employment Scheme Uttarakhand", "Mukhyamantri Kamgar Yojana providing employment card and benefits for informal workers.", CategoryType.EMPLOYMENT, "Uttarakhand", GenderType.ALL, CasteType.ALL, 18, 60, null, false, false, false, false, "https://uk.gov.in/"),
                new Scheme(null, "Ration Scheme Uttarakhand", "Free food grains under NFSA for BPL and AAY households in Uttarakhand.", CategoryType.FOOD, "Uttarakhand", GenderType.ALL, CasteType.ALL, 0, 100, null, false, true, false, false, "https://fcs.uk.gov.in/")
            );
            uk.forEach(s -> save(repo, s));

            System.out.println("North India schemes initialization completed!");
        };
    }
}
