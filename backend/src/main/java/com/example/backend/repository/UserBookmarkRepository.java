package com.example.backend.repository;

import com.example.backend.entity.UserBookmark;
import com.example.backend.entity.User;
import com.example.backend.entity.Scheme;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserBookmarkRepository extends JpaRepository<UserBookmark, Long> {
    List<UserBookmark> findByUser(User user);
    Optional<UserBookmark> findByUserAndScheme(User user, Scheme scheme);
    void deleteByUserAndScheme(User user, Scheme scheme);
}
