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
@Order(6)
public class DataInitializerNorthEast {

    private void save(SchemeRepository r, Scheme s) {
        if (!r.existsByNameAndState(s.getName(), s.getState())) r.save(s);
    }

    @Bean("northEastRunner")
    CommandLineRunner initNorthEast(SchemeRepository repo) {
        return args -> {
            // TRIPURA
            List<Scheme> tr = Arrays.asList(
                new Scheme(null, "Mukhyamantri Swanirbhar Yojana", "Self-employment and enterprise support for educated unemployed youth in Tripura.", CategoryType.ENTREPRENEURSHIP, "Tripura", GenderType.ALL, CasteType.ALL, 18, 40, null, false, false, false, false, "https://tripura.gov.in/"),
                new Scheme(null, "Pension Scheme Tripura", "Monthly social security pension for aged, widows, and disabled persons in Tripura.", CategoryType.SOCIAL_WELFARE, "Tripura", GenderType.ALL, CasteType.ALL, 60, 100, null, true, false, true, false, "https://socialwelfare.tripura.gov.in/"),
                new Scheme(null, "Scholarship Scheme Tripura", "Post-matric scholarship for SC/ST and minority students pursuing higher education.", CategoryType.EDUCATION, "Tripura", GenderType.ALL, CasteType.ALL, 17, 28, 150000.0, false, false, false, false, "https://scholarships.gov.in/"),
                new Scheme(null, "Health Scheme Tripura", "Free health care under Ayushman Bharat for BPL households in Tripura.", CategoryType.HEALTH, "Tripura", GenderType.ALL, CasteType.ALL, 0, 100, null, false, true, false, false, "https://tripura.gov.in/"),
                new Scheme(null, "Housing Scheme Tripura", "PMAY Gramin implementation for homeless BPL families in Tripura.", CategoryType.HOUSING, "Tripura", GenderType.ALL, CasteType.ALL, 18, 65, null, false, true, false, false, "https://pmayg.nic.in/"),
                new Scheme(null, "Skill Development Scheme Tripura", "TSSDM free training in rubber processing, bamboo crafts, and IT for youth.", CategoryType.SKILL_DEVELOPMENT, "Tripura", GenderType.ALL, CasteType.ALL, 18, 35, null, false, false, false, false, "https://tripura.gov.in/"),
                new Scheme(null, "Women Welfare Scheme Tripura", "Maternity and child nutrition schemes under ICDS for Tripura tribal women.", CategoryType.WOMEN_CHILD_WELFARE, "Tripura", GenderType.FEMALE, CasteType.ALL, 18, 45, null, false, false, false, false, "https://tripura.gov.in/"),
                new Scheme(null, "Farmer Scheme Tripura", "Rubber cultivation subsidy and horticulture support for Tripura tribal farmers.", CategoryType.AGRICULTURE, "Tripura", GenderType.ALL, CasteType.ALL, 18, 65, null, false, false, false, false, "https://agri.tripura.gov.in/"),
                new Scheme(null, "Employment Scheme Tripura", "MGNREGS with 100-day employment and livelihood training for rural households.", CategoryType.EMPLOYMENT, "Tripura", GenderType.ALL, CasteType.ALL, 18, 60, null, false, false, false, false, "https://nrega.nic.in/"),
                new Scheme(null, "Ration Scheme Tripura", "Subsidized food grains for BPL and AAY households under NFSA in Tripura.", CategoryType.FOOD, "Tripura", GenderType.ALL, CasteType.ALL, 0, 100, null, false, true, false, false, "https://fcatripura.gov.in/")
            );
            tr.forEach(s -> save(repo, s));

            // MEGHALAYA
            List<Scheme> ml = Arrays.asList(
                new Scheme(null, "CM Farmer Scheme Meghalaya", "Meghalaya Integrated Basin Management Program support for hill farmers.", CategoryType.AGRICULTURE, "Meghalaya", GenderType.ALL, CasteType.ALL, 18, 70, null, false, false, false, false, "https://meghalaya.gov.in/"),
                new Scheme(null, "Pension Scheme Meghalaya", "Monthly social security pension for old age, widow, and disabled in Meghalaya.", CategoryType.SOCIAL_WELFARE, "Meghalaya", GenderType.ALL, CasteType.ALL, 60, 100, null, true, false, true, false, "https://meghalaya.gov.in/"),
                new Scheme(null, "Scholarship Scheme Meghalaya", "Merit scholarships for students from economically weak tribal families in Meghalaya.", CategoryType.EDUCATION, "Meghalaya", GenderType.ALL, CasteType.ST, 17, 28, 150000.0, false, false, false, false, "https://scholarships.gov.in/"),
                new Scheme(null, "Health Scheme Meghalaya", "Megha Health Insurance Scheme providing Rs.2 lakh coverage for state families.", CategoryType.HEALTH, "Meghalaya", GenderType.ALL, CasteType.ALL, 0, 100, null, false, false, false, false, "https://meghalaya.gov.in/"),
                new Scheme(null, "Housing Scheme Meghalaya", "Chief Minister's Housing Scheme for BPL and homeless tribal families in Meghalaya.", CategoryType.HOUSING, "Meghalaya", GenderType.ALL, CasteType.ALL, 18, 65, null, false, true, false, false, "https://meghalaya.gov.in/"),
                new Scheme(null, "Skill Development Scheme Meghalaya", "MSDSCoS training in tourism, organic farming, and IT for Meghalaya youth.", CategoryType.SKILL_DEVELOPMENT, "Meghalaya", GenderType.ALL, CasteType.ALL, 18, 35, null, false, false, false, false, "https://meghalaya.gov.in/"),
                new Scheme(null, "Women Welfare Scheme Meghalaya", "Support for women-led SHGs in horticulture and handicraft enterprises in Meghalaya.", CategoryType.WOMEN_CHILD_WELFARE, "Meghalaya", GenderType.FEMALE, CasteType.ALL, 18, 55, null, false, false, false, false, "https://meghalaya.gov.in/"),
                new Scheme(null, "Employment Scheme Meghalaya", "MGNREGS and CM Employment Generation Scheme for rural hill communities.", CategoryType.EMPLOYMENT, "Meghalaya", GenderType.ALL, CasteType.ALL, 18, 60, null, false, false, false, false, "https://nrega.nic.in/"),
                new Scheme(null, "Farmer Scheme Meghalaya", "Block Level Farm Scheme for horticulture, floriculture, and spice cultivation support.", CategoryType.AGRICULTURE, "Meghalaya", GenderType.ALL, CasteType.ALL, 18, 70, null, false, false, false, false, "https://agrimeghalaya.nic.in/"),
                new Scheme(null, "Ration Scheme Meghalaya", "Subsidized food grains under NFSA for BPL and Antyodaya families in Meghalaya.", CategoryType.FOOD, "Meghalaya", GenderType.ALL, CasteType.ALL, 0, 100, null, false, true, false, false, "https://meghalaya.gov.in/")
            );
            ml.forEach(s -> save(repo, s));

            // MANIPUR
            List<Scheme> mn = Arrays.asList(
                new Scheme(null, "CM Health Scheme Manipur", "Free health treatment for BPL households under Ayushman Bharat in Manipur.", CategoryType.HEALTH, "Manipur", GenderType.ALL, CasteType.ALL, 0, 100, null, false, true, false, false, "https://manipur.gov.in/"),
                new Scheme(null, "Pension Scheme Manipur", "Indira Gandhi National Old Age, Widow, and Disability Pension for Manipur residents.", CategoryType.SOCIAL_WELFARE, "Manipur", GenderType.ALL, CasteType.ALL, 60, 100, null, true, false, true, false, "https://manipur.gov.in/"),
                new Scheme(null, "Scholarship Scheme Manipur", "State merit scholarships and post-matric support for ST students in Manipur.", CategoryType.EDUCATION, "Manipur", GenderType.ALL, CasteType.ST, 17, 28, 150000.0, false, false, false, false, "https://scholarships.gov.in/"),
                new Scheme(null, "Housing Scheme Manipur", "PMAY Gramin for homeless BPL families and flood-affected households in Manipur.", CategoryType.HOUSING, "Manipur", GenderType.ALL, CasteType.ALL, 18, 65, null, false, true, false, false, "https://pmayg.nic.in/"),
                new Scheme(null, "Skill Development Scheme Manipur", "MSDS free vocational training in weaving, bamboo crafts, and IT for youth.", CategoryType.SKILL_DEVELOPMENT, "Manipur", GenderType.ALL, CasteType.ALL, 18, 35, null, false, false, false, false, "https://manipur.gov.in/"),
                new Scheme(null, "Women Welfare Scheme Manipur", "Ima market support and Manipuri handloom promotion for women weavers.", CategoryType.WOMEN_CHILD_WELFARE, "Manipur", GenderType.FEMALE, CasteType.ALL, 18, 55, null, false, false, false, false, "https://manipur.gov.in/"),
                new Scheme(null, "Farmer Scheme Manipur", "Organic farming support and horticulture input subsidy for hill and valley farmers.", CategoryType.AGRICULTURE, "Manipur", GenderType.ALL, CasteType.ALL, 18, 70, null, false, false, false, false, "https://agri.manipur.gov.in/"),
                new Scheme(null, "Employment Scheme Manipur", "Go To Hills and Go To Villages 2.0 employment and livelihood scheme for Manipur.", CategoryType.EMPLOYMENT, "Manipur", GenderType.ALL, CasteType.ALL, 18, 45, null, false, false, false, false, "https://manipur.gov.in/"),
                new Scheme(null, "Health Scheme Manipur 2", "State Illness Assistance Fund providing financial support for critical disease treatment.", CategoryType.HEALTH, "Manipur", GenderType.ALL, CasteType.ALL, 0, 100, null, false, true, false, false, "https://manipur.gov.in/"),
                new Scheme(null, "Ration Scheme Manipur", "Subsidized food grains for priority households under NFSA in Manipur.", CategoryType.FOOD, "Manipur", GenderType.ALL, CasteType.ALL, 0, 100, null, false, true, false, false, "https://manipur.gov.in/")
            );
            mn.forEach(s -> save(repo, s));

            // NAGALAND
            List<Scheme> nl = Arrays.asList(
                new Scheme(null, "Chief Minister Health Insurance Scheme Nagaland", "Health insurance providing cashless treatment for Nagaland residents up to Rs.2 lakh.", CategoryType.HEALTH, "Nagaland", GenderType.ALL, CasteType.ALL, 0, 100, null, false, false, false, false, "https://nagaland.gov.in/"),
                new Scheme(null, "Pension Scheme Nagaland", "Monthly pension for elderly, widows, and disabled tribal citizens in Nagaland.", CategoryType.SOCIAL_WELFARE, "Nagaland", GenderType.ALL, CasteType.ALL, 60, 100, null, true, false, true, false, "https://nagaland.gov.in/"),
                new Scheme(null, "Scholarship Scheme Nagaland", "Merit and minority scholarships for Naga students pursuing professional and technical education.", CategoryType.EDUCATION, "Nagaland", GenderType.ALL, CasteType.ST, 17, 28, 150000.0, false, false, false, false, "https://scholarships.gov.in/"),
                new Scheme(null, "Housing Scheme Nagaland", "PMAY implementation for BPL homeless families in Nagaland hill districts.", CategoryType.HOUSING, "Nagaland", GenderType.ALL, CasteType.ALL, 18, 65, null, false, true, false, false, "https://pmayg.nic.in/"),
                new Scheme(null, "Skill Development Scheme Nagaland", "NSSDM training in IT, construction, and hospitality for Nagaland youth.", CategoryType.SKILL_DEVELOPMENT, "Nagaland", GenderType.ALL, CasteType.ALL, 18, 35, null, false, false, false, false, "https://nagaland.gov.in/"),
                new Scheme(null, "Women Welfare Scheme Nagaland", "Nagaland women empowerment through SHG formation and market access programs.", CategoryType.WOMEN_CHILD_WELFARE, "Nagaland", GenderType.FEMALE, CasteType.ALL, 18, 55, null, false, false, false, false, "https://nagaland.gov.in/"),
                new Scheme(null, "Farmer Scheme Nagaland", "Organic and natural farming promotion with input subsidy for terrace farmers.", CategoryType.AGRICULTURE, "Nagaland", GenderType.ALL, CasteType.ALL, 18, 70, null, false, false, false, false, "https://agrinagaland.nic.in/"),
                new Scheme(null, "Employment Scheme Nagaland", "MGNREGS and CM Employment Scheme providing livelihood for rural households.", CategoryType.EMPLOYMENT, "Nagaland", GenderType.ALL, CasteType.ALL, 18, 60, null, false, false, false, false, "https://nrega.nic.in/"),
                new Scheme(null, "Health Scheme Nagaland 2", "State primary healthcare strengthening under National Health Mission in Nagaland.", CategoryType.HEALTH, "Nagaland", GenderType.ALL, CasteType.ALL, 0, 100, null, false, false, false, false, "https://nagaland.gov.in/"),
                new Scheme(null, "Ration Scheme Nagaland", "Free and subsidized food grains under NFSA for BPL tribal families in Nagaland.", CategoryType.FOOD, "Nagaland", GenderType.ALL, CasteType.ALL, 0, 100, null, false, true, false, false, "https://nagaland.gov.in/")
            );
            nl.forEach(s -> save(repo, s));

            // MIZORAM
            List<Scheme> mz = Arrays.asList(
                new Scheme(null, "Mizoram Health Scheme", "Universal health coverage under State Illness Assistance Fund for Mizoram families.", CategoryType.HEALTH, "Mizoram", GenderType.ALL, CasteType.ALL, 0, 100, null, false, false, false, false, "https://health.mizoram.gov.in/"),
                new Scheme(null, "Pension Scheme Mizoram", "IGNOAPS, IGNWPS, and IGNDPS pension schemes for eligible Mizoram residents.", CategoryType.SOCIAL_WELFARE, "Mizoram", GenderType.ALL, CasteType.ALL, 60, 100, null, true, false, true, false, "https://mizoram.gov.in/"),
                new Scheme(null, "Scholarship Scheme Mizoram", "ST scholarships and merit awards for students from Mizo tribal families in higher education.", CategoryType.EDUCATION, "Mizoram", GenderType.ALL, CasteType.ST, 17, 28, 150000.0, false, false, false, false, "https://scholarships.gov.in/"),
                new Scheme(null, "Housing Scheme Mizoram", "PMAY Gramin and state housing scheme for BPL and earthquake-affected families.", CategoryType.HOUSING, "Mizoram", GenderType.ALL, CasteType.ALL, 18, 65, null, false, true, false, false, "https://pmayg.nic.in/"),
                new Scheme(null, "Skill Development Scheme Mizoram", "MSDS training in bamboo crafts, weaving, and IT for Mizoram youth.", CategoryType.SKILL_DEVELOPMENT, "Mizoram", GenderType.ALL, CasteType.ALL, 18, 35, null, false, false, false, false, "https://mizoram.gov.in/"),
                new Scheme(null, "Women Welfare Scheme Mizoram", "Women SHG economic empowerment and nutrition support schemes under ICDS.", CategoryType.WOMEN_CHILD_WELFARE, "Mizoram", GenderType.FEMALE, CasteType.ALL, 18, 55, null, false, false, false, false, "https://mizoram.gov.in/"),
                new Scheme(null, "Farmer Scheme Mizoram", "Jhum cultivation modernization and horticulture subsidy for Mizo farmers.", CategoryType.AGRICULTURE, "Mizoram", GenderType.ALL, CasteType.ALL, 18, 70, null, false, false, false, false, "https://agri.mizoram.gov.in/"),
                new Scheme(null, "Employment Scheme Mizoram", "State Employment Exchange and MGNREGS livelihood program for rural households.", CategoryType.EMPLOYMENT, "Mizoram", GenderType.ALL, CasteType.ALL, 18, 60, null, false, false, false, false, "https://nrega.nic.in/"),
                new Scheme(null, "Health Scheme Mizoram 2", "Mizoram State Health Mission for subsidized medicines and diagnostics.", CategoryType.HEALTH, "Mizoram", GenderType.ALL, CasteType.ALL, 0, 100, null, false, false, false, false, "https://mizoram.gov.in/"),
                new Scheme(null, "Ration Scheme Mizoram", "Subsidized food grains for BPL and AAY households under NFSA in Mizoram.", CategoryType.FOOD, "Mizoram", GenderType.ALL, CasteType.ALL, 0, 100, null, false, true, false, false, "https://mizoram.gov.in/")
            );
            mz.forEach(s -> save(repo, s));

            // ARUNACHAL PRADESH
            List<Scheme> ar = Arrays.asList(
                new Scheme(null, "CM Health Scheme Arunachal Pradesh", "Comprehensive health coverage under Ayushman Arunachal for all state residents.", CategoryType.HEALTH, "Arunachal Pradesh", GenderType.ALL, CasteType.ALL, 0, 100, null, false, false, false, false, "https://arunachal.gov.in/"),
                new Scheme(null, "Pension Scheme Arunachal Pradesh", "Monthly social security pension for elderly, widow, and disabled tribal residents.", CategoryType.SOCIAL_WELFARE, "Arunachal Pradesh", GenderType.ALL, CasteType.ALL, 60, 100, null, true, false, true, false, "https://arunachal.gov.in/"),
                new Scheme(null, "Scholarship Scheme Arunachal Pradesh", "ST scholarships and merit awards for tribal students in professional courses.", CategoryType.EDUCATION, "Arunachal Pradesh", GenderType.ALL, CasteType.ST, 17, 28, 150000.0, false, false, false, false, "https://scholarships.gov.in/"),
                new Scheme(null, "Housing Scheme Arunachal Pradesh", "PMAY Gramin and natural calamity relief housing for BPL hill families.", CategoryType.HOUSING, "Arunachal Pradesh", GenderType.ALL, CasteType.ALL, 18, 65, null, false, true, false, false, "https://pmayg.nic.in/"),
                new Scheme(null, "Skill Development Scheme Arunachal Pradesh", "ASRLM vocational training in construction, weaving, and horticulture for youth.", CategoryType.SKILL_DEVELOPMENT, "Arunachal Pradesh", GenderType.ALL, CasteType.ALL, 18, 35, null, false, false, false, false, "https://arunachal.gov.in/"),
                new Scheme(null, "Women Welfare Scheme Arunachal Pradesh", "Women SHG promotion and ICDS nutrition programs for tribal mothers and children.", CategoryType.WOMEN_CHILD_WELFARE, "Arunachal Pradesh", GenderType.FEMALE, CasteType.ALL, 18, 45, null, false, false, false, false, "https://arunachal.gov.in/"),
                new Scheme(null, "Farmer Scheme Arunachal Pradesh", "Organic farming and horticulture development scheme for apple and kiwi cultivation.", CategoryType.AGRICULTURE, "Arunachal Pradesh", GenderType.ALL, CasteType.ALL, 18, 70, null, false, false, false, false, "https://agri.arunachal.gov.in/"),
                new Scheme(null, "Employment Scheme Arunachal Pradesh", "MGNREGS and state employment mission for livelihood of rural hill communities.", CategoryType.EMPLOYMENT, "Arunachal Pradesh", GenderType.ALL, CasteType.ALL, 18, 60, null, false, false, false, false, "https://nrega.nic.in/"),
                new Scheme(null, "Health Scheme AP 2", "Mobile health units and telemedicine services for remote tribal villages in AR.", CategoryType.HEALTH, "Arunachal Pradesh", GenderType.ALL, CasteType.ALL, 0, 100, null, false, false, false, false, "https://arunachal.gov.in/"),
                new Scheme(null, "Ration Scheme Arunachal Pradesh", "Free food grains for BPL and AAY tribal households under NFSA in Arunachal Pradesh.", CategoryType.FOOD, "Arunachal Pradesh", GenderType.ALL, CasteType.ALL, 0, 100, null, false, true, false, false, "https://arunachal.gov.in/")
            );
            ar.forEach(s -> save(repo, s));

            // SIKKIM
            List<Scheme> sk = Arrays.asList(
                new Scheme(null, "Sikkim Health Assurance Scheme", "Universal health assurance providing free OPD, IPD, and surgery to all Sikkim residents.", CategoryType.HEALTH, "Sikkim", GenderType.ALL, CasteType.ALL, 0, 100, null, false, false, false, false, "https://sikkim.gov.in/"),
                new Scheme(null, "Pension Scheme Sikkim", "Monthly pension for senior citizens, widows, and disabled residents of Sikkim.", CategoryType.SOCIAL_WELFARE, "Sikkim", GenderType.ALL, CasteType.ALL, 60, 100, null, true, false, true, false, "https://sikkim.gov.in/"),
                new Scheme(null, "Scholarship Scheme Sikkim", "Sikkim state scholarships and fee support for students from economically weaker families.", CategoryType.EDUCATION, "Sikkim", GenderType.ALL, CasteType.ALL, 17, 28, 200000.0, false, false, false, false, "https://scholarships.gov.in/"),
                new Scheme(null, "Housing Scheme Sikkim", "PMAY Gramin and state housing scheme for BPL homeless families in Sikkim.", CategoryType.HOUSING, "Sikkim", GenderType.ALL, CasteType.ALL, 18, 65, null, false, true, false, false, "https://pmayg.nic.in/"),
                new Scheme(null, "Skill Development Scheme Sikkim", "Skill development training for Sikkim youth in organic farming, tourism, and IT.", CategoryType.SKILL_DEVELOPMENT, "Sikkim", GenderType.ALL, CasteType.ALL, 18, 35, null, false, false, false, false, "https://sikkim.gov.in/"),
                new Scheme(null, "Women Welfare Scheme Sikkim", "Sikkim Women Development Scheme for empowerment, legal aid, and entrepreneurship.", CategoryType.WOMEN_CHILD_WELFARE, "Sikkim", GenderType.FEMALE, CasteType.ALL, 18, 55, null, false, false, false, false, "https://sikkim.gov.in/"),
                new Scheme(null, "Farmer Scheme Sikkim", "100% organic farming state program with input support and market linkage for farmers.", CategoryType.AGRICULTURE, "Sikkim", GenderType.ALL, CasteType.ALL, 18, 70, null, false, false, false, false, "https://agri.sikkim.gov.in/"),
                new Scheme(null, "Employment Scheme Sikkim", "MGNREGS and CM Employment assistance for rural and urban unemployed in Sikkim.", CategoryType.EMPLOYMENT, "Sikkim", GenderType.ALL, CasteType.ALL, 18, 60, null, false, false, false, false, "https://nrega.nic.in/"),
                new Scheme(null, "Health Scheme Sikkim 2", "National Health Mission strengthening of primary health centres in Sikkim.", CategoryType.HEALTH, "Sikkim", GenderType.ALL, CasteType.ALL, 0, 100, null, false, false, false, false, "https://sikkim.gov.in/"),
                new Scheme(null, "Ration Scheme Sikkim", "Subsidized food grains for BPL and AAY households registered under NFSA in Sikkim.", CategoryType.FOOD, "Sikkim", GenderType.ALL, CasteType.ALL, 0, 100, null, false, true, false, false, "https://sikkim.gov.in/")
            );
            sk.forEach(s -> save(repo, s));

            System.out.println("Northeast India schemes initialization completed!");
        };
    }
}
