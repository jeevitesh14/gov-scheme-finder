package com.example.backend.service;

import com.example.backend.dto.SchemeDTO;
import com.example.backend.entity.Scheme;
import com.example.backend.entity.User;
import com.example.backend.entity.UserBookmark;
import com.example.backend.repository.SchemeRepository;
import com.example.backend.repository.UserBookmarkRepository;
import com.example.backend.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserBookmarkService {

    private final UserBookmarkRepository bookmarkRepository;
    private final UserRepository userRepository;
    private final SchemeRepository schemeRepository;

    @Transactional
    public void addBookmark(String email, Long schemeId) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));
        Scheme scheme = schemeRepository.findById(schemeId)
                .orElseThrow(() -> new RuntimeException("Scheme not found"));

        if (bookmarkRepository.findByUserAndScheme(user, scheme).isEmpty()) {
            UserBookmark bookmark = UserBookmark.builder()
                    .user(user)
                    .scheme(scheme)
                    .build();
            bookmarkRepository.save(bookmark);
        }
    }

    @Transactional
    public void removeBookmark(String email, Long schemeId) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));
        Scheme scheme = schemeRepository.findById(schemeId)
                .orElseThrow(() -> new RuntimeException("Scheme not found"));

        bookmarkRepository.deleteByUserAndScheme(user, scheme);
    }

    public List<SchemeDTO> getBookmarkedSchemes(String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));
        
        return bookmarkRepository.findByUser(user).stream()
                .map(bookmark -> convertToDTO(bookmark.getScheme()))
                .collect(Collectors.toList());
    }

    private SchemeDTO convertToDTO(Scheme scheme) {
        return SchemeDTO.builder()
                .id(scheme.getId())
                .name(scheme.getName())
                .description(scheme.getDescription())
                .category(scheme.getCategory())
                .state(scheme.getState())
                .gender(scheme.getGender())
                .caste(scheme.getCaste())
                .ageMin(scheme.getAgeMin())
                .ageMax(scheme.getAgeMax())
                .incomeLimit(scheme.getIncomeLimit())
                .disability(scheme.getDisability())
                .bpl(scheme.getBpl())
                .widow(scheme.getWidow())
                .minority(scheme.getMinority())
                .applyLink(scheme.getApplyLink())
                .build();
    }
}
