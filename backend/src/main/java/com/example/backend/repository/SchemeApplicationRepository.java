package com.example.backend.repository;

import com.example.backend.entity.SchemeApplication;
import com.example.backend.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface SchemeApplicationRepository extends JpaRepository<SchemeApplication, Long> {
    List<SchemeApplication> findByUser(User user);
    boolean existsByUserAndScheme(User user, com.example.backend.entity.Scheme scheme);
}
