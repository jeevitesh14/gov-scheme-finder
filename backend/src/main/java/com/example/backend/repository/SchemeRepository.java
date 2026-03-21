package com.example.backend.repository;

import com.example.backend.entity.Scheme;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface SchemeRepository extends JpaRepository<Scheme, Long> {
    boolean existsByName(String name);
    boolean existsByNameAndState(String name, String state);

    @Query("SELECT s FROM Scheme s WHERE " +
            "(:keyword IS NULL OR LOWER(s.name) LIKE LOWER(CONCAT('%', :keyword, '%')) OR LOWER(s.description) LIKE LOWER(CONCAT('%', :keyword, '%'))) AND " +
            "(:category IS NULL OR s.category = :category) AND " +
            "(:state IS NULL OR s.state = :state OR s.state = 'All') AND " +
            "(:gender IS NULL OR s.gender = :gender OR s.gender = com.example.backend.entity.GenderType.ALL) AND " +
            "(:caste IS NULL OR s.caste = :caste OR s.caste = com.example.backend.entity.CasteType.ALL) AND " +
            "(:disability IS NULL OR s.disability = :disability) AND " +
            "(:bpl IS NULL OR s.bpl = :bpl) AND " +
            "(:widow IS NULL OR s.widow = :widow) AND " +
            "(:minority IS NULL OR s.minority = :minority) AND " +
            "(:income IS NULL OR s.incomeLimit IS NULL OR s.incomeLimit >= :income) AND " +
            "(:age IS NULL OR ((s.ageMin IS NULL OR s.ageMin <= :age) AND (s.ageMax IS NULL OR s.ageMax >= :age)))")
    org.springframework.data.domain.Page<Scheme> findFiltered(
            @Param("keyword") String keyword,
            @Param("category") com.example.backend.entity.CategoryType category,
            @Param("state") String state,
            @Param("gender") com.example.backend.entity.GenderType gender,
            @Param("caste") com.example.backend.entity.CasteType caste,
            @Param("disability") Boolean disability,
            @Param("bpl") Boolean bpl,
            @Param("widow") Boolean widow,
            @Param("minority") Boolean minority,
            @Param("income") Double income,
            @Param("age") Integer age,
            org.springframework.data.domain.Pageable pageable
    );

}
