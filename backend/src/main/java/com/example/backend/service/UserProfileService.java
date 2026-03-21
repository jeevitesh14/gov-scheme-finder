package com.example.backend.service;

import com.example.backend.dto.UserProfileDTO;
import com.example.backend.entity.User;
import com.example.backend.entity.UserProfile;
import com.example.backend.repository.UserProfileRepository;
import com.example.backend.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserProfileService {

    private final UserProfileRepository userProfileRepository;
    private final UserRepository userRepository;

    public UserProfileService(UserProfileRepository userProfileRepository, UserRepository userRepository) {
        this.userProfileRepository = userProfileRepository;
        this.userRepository = userRepository;
    }

    public UserProfileDTO getProfileByEmail(String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));
        
        UserProfile profile = userProfileRepository.findByUserId(user.getId())
                .orElseGet(() -> {
                    UserProfile newProfile = new UserProfile();
                    newProfile.setUser(user);
                    newProfile.setName(user.getName());
                    return userProfileRepository.save(newProfile);
                });

        return convertToDTO(profile);
    }

    @Transactional
    public UserProfileDTO updateProfile(String email, UserProfileDTO dto) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        UserProfile profile = userProfileRepository.findByUserId(user.getId())
                .orElseGet(() -> {
                    UserProfile newProfile = new UserProfile();
                    newProfile.setUser(user);
                    return newProfile;
                });

        profile.setName(dto.getName());
        profile.setAge(dto.getAge());
        profile.setGender(dto.getGender());
        profile.setAddress(dto.getAddress());
        profile.setMobileNumber(dto.getMobileNumber());
        profile.setState(dto.getState());
        profile.setIncome(dto.getIncome());
        profile.setCaste(dto.getCaste());
        profile.setOccupation(dto.getOccupation());

        return convertToDTO(userProfileRepository.save(profile));
    }

    private UserProfileDTO convertToDTO(UserProfile profile) {
        return new UserProfileDTO(
                profile.getName(),
                profile.getAge(),
                profile.getGender(),
                profile.getAddress(),
                profile.getMobileNumber(),
                profile.getState(),
                profile.getIncome(),
                profile.getCaste(),
                profile.getOccupation()
        );
    }
}
