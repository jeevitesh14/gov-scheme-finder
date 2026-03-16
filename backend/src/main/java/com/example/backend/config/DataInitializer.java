package com.example.backend.config;

import com.example.backend.entity.*;
import com.example.backend.repository.SchemeRepository;
import com.example.backend.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
@Slf4j
public class DataInitializer implements CommandLineRunner {

        private final SchemeRepository schemeRepository;
        private final UserRepository userRepository;
        private final PasswordEncoder passwordEncoder;

        @Override
        public void run(String... args) throws Exception {
                try {
                        long userCount = userRepository.count();
                        long schemeCount = schemeRepository.count();
                        log.info("📊 Database Status - Users: {}, Schemes: {}", userCount, schemeCount);
                } catch (Exception e) {
                        log.warn("⚠️ Database not ready yet, skipping initial count: {}", e.getMessage());
                        return;
                }

                // Seed Admin User
                if (userRepository.findByEmail("admin@gov.in").isEmpty()) {
                        User admin = User.builder()
                                        .name("System Admin")
                                        .email("admin@gov.in")
                                        .password(passwordEncoder.encode("admin123"))
                                        .role(RoleType.ADMIN)
                                        .build();
                        userRepository.save(admin);
                        System.out.println("✅ Admin user seeded successfully!");
                }

                // Seed Schemes
                if (schemeRepository.count() < 10) {
                        log.info("🌱 Seeding government schemes...");
                        schemeRepository.deleteAll();
                        schemeRepository.saveAll(List.of(
                                        Scheme.builder()
                                                        .name("PM Kisan Samman Nidhi")
                                                        .description(
                                                                        "Financial assistance of Rs 6000/year to small and marginal farmers in three equal installments.")
                                                        .category(CategoryType.AGRICULTURE).state("All").gender(GenderType.ALL).caste(CasteType.ALL)
                                                        .ageMin(18).ageMax(null).incomeLimit(null)
                                                        .disability(false).bpl(false).widow(false).minority(false)
                                                        .applyLink("https://pmkisan.gov.in").build(),

                                        Scheme.builder()
                                                        .name("Pradhan Mantri Awas Yojana (Urban)")
                                                        .description(
                                                                        "Housing for All by 2022 – Credit linked subsidy scheme for home loan beneficiaries in urban areas.")
                                                        .category(CategoryType.HOUSING).state("All").gender(GenderType.ALL).caste(CasteType.ALL)
                                                        .ageMin(18).ageMax(null).incomeLimit(1800000.0)
                                                        .disability(false).bpl(false).widow(false).minority(false)
                                                        .applyLink("https://pmaymis.gov.in").build(),

                                        Scheme.builder()
                                                        .name("Beti Bachao Beti Padhao")
                                                        .description(
                                                                        "Scheme to promote girl child welfare – prevention of female foeticide and promotion of girl education.")
                                                        .category(CategoryType.EDUCATION).state("All").gender(GenderType.FEMALE)
                                                        .caste(CasteType.ALL)
                                                        .ageMin(0).ageMax(10).incomeLimit(null)
                                                        .disability(false).bpl(false).widow(false).minority(false)
                                                        .applyLink("https://wcd.nic.in/bbbp-schemes").build(),

                                        Scheme.builder()
                                                        .name("Ayushman Bharat PM-JAY")
                                                        .description(
                                                                        "Health insurance cover of Rs 5 lakh per family per year for secondary and tertiary hospitalisation.")
                                                        .category(CategoryType.HEALTH).state("All").gender(GenderType.ALL).caste(CasteType.ALL)
                                                        .ageMin(0).ageMax(null).incomeLimit(null)
                                                        .disability(false).bpl(true).widow(false).minority(false)
                                                        .applyLink("https://pmjay.gov.in").build(),

                                        Scheme.builder()
                                                        .name("PM Scholarship Scheme for Central Armed Police Forces")
                                                        .description(
                                                                        "Scholarship for wards of CAPF/RPF/RPSF personnel for professional degree courses.")
                                                        .category(CategoryType.EDUCATION).state("All").gender(GenderType.ALL).caste(CasteType.ALL)
                                                        .ageMin(17).ageMax(25).incomeLimit(null)
                                                        .disability(false).bpl(false).widow(false).minority(false)
                                                        .applyLink("https://scholarships.gov.in").build(),

                                        Scheme.builder()
                                                        .name("Sukanya Samriddhi Yojana")
                                                        .description(
                                                                        "Small savings scheme for girl child – high interest rate, tax benefits under Section 80C.")
                                                        .category(CategoryType.FINANCE).state("All").gender(GenderType.FEMALE).caste(CasteType.ALL)
                                                        .ageMin(0).ageMax(10).incomeLimit(null)
                                                        .disability(false).bpl(false).widow(false).minority(false)
                                                        .applyLink("https://www.indiapost.gov.in").build(),

                                        Scheme.builder()
                                                        .name("National Disability Scholarship")
                                                        .description(
                                                                        "Scholarships for students with benchmark disabilities for pursuing higher and technical education.")
                                                        .category(CategoryType.EDUCATION).state("All").gender(GenderType.ALL).caste(CasteType.ALL)
                                                        .ageMin(0).ageMax(35).incomeLimit(250000.0)
                                                        .disability(true).bpl(false).widow(false).minority(false)
                                                        .applyLink("https://scholarships.gov.in").build(),

                                        Scheme.builder()
                                                        .name("Maulana Azad National Fellowship")
                                                        .description(
                                                                        "Fellowship for minority community students to pursue M.Phil and PhD in Indian universities.")
                                                        .category(CategoryType.EDUCATION).state("All").gender(GenderType.ALL).caste(CasteType.ALL)
                                                        .ageMin(18).ageMax(40).incomeLimit(600000.0)
                                                        .disability(false).bpl(false).widow(false).minority(true)
                                                        .applyLink("https://manabadi.co.in/manf").build(),

                                        Scheme.builder()
                                                        .name("BPL Ration Card Scheme")
                                                        .description(
                                                                        "Subsidised food grains at Rs 2–3 per kg for households Below Poverty Line under NFSA.")
                                                        .category(CategoryType.FOOD).state("All").gender(GenderType.ALL).caste(CasteType.ALL)
                                                        .ageMin(0).ageMax(null).incomeLimit(null)
                                                        .disability(false).bpl(true).widow(false).minority(false)
                                                        .applyLink("https://nfsa.gov.in").build(),

                                        Scheme.builder()
                                                        .name("Indira Gandhi National Widow Pension Scheme")
                                                        .description("Monthly pension of Rs 300 for widows aged 40–79 years from BPL households.")
                                                        .category(CategoryType.SOCIAL_WELFARE).state("All").gender(GenderType.FEMALE)
                                                        .caste(CasteType.ALL)
                                                        .ageMin(40).ageMax(79).incomeLimit(null)
                                                        .disability(false).bpl(true).widow(true).minority(false)
                                                        .applyLink("https://nsap.nic.in").build(),

                                        Scheme.builder()
                                                        .name("PM Mudra Yojana")
                                                        .description(
                                                                        "Loans up to Rs 10 lakh for non-corporate small business entrepreneurs under Shishu, Kishore, Tarun categories.")
                                                        .category(CategoryType.ENTREPRENEURSHIP).state("All").gender(GenderType.ALL)
                                                        .caste(CasteType.ALL)
                                                        .ageMin(18).ageMax(65).incomeLimit(null)
                                                        .disability(false).bpl(false).widow(false).minority(false)
                                                        .applyLink("https://mudra.org.in").build(),

                                        Scheme.builder()
                                                        .name("YSR Kanti Velugu")
                                                        .description("Free eye screenings and surgeries for all citizens of Andhra Pradesh.")
                                                        .category(CategoryType.HEALTH).state("Andhra Pradesh").gender(GenderType.ALL).caste(CasteType.ALL)
                                                        .ageMin(null).ageMax(null).incomeLimit(null)
                                                        .disability(false).bpl(false).widow(false).minority(false)
                                                        .applyLink("http://ysrkantivelugu.ap.gov.in").build(),

                                        Scheme.builder()
                                                        .name("Mukhyamantri Kanya Utthan Yojana")
                                                        .description("Financial assistance for girls in Bihar from birth till graduation.")
                                                        .category(CategoryType.SOCIAL_WELFARE).state("Bihar").gender(GenderType.FEMALE).caste(CasteType.ALL)
                                                        .ageMin(0).ageMax(22).incomeLimit(null)
                                                        .disability(false).bpl(false).widow(false).minority(false)
                                                        .applyLink("http://edudbt.bih.nic.in").build()
                                        ));

                        log.info("✅ Seeded {} government schemes.", schemeRepository.count());
                }
        }
}
