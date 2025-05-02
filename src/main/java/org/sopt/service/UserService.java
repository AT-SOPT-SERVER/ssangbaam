package org.sopt.service;

import org.sopt.domain.User;
import org.sopt.dto.request.UserCreateRequest;
import org.sopt.dto.response.UserResponse;
import org.sopt.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Transactional
    public UserResponse createUser(UserCreateRequest request) {
        User newUser = new User(request.name(), request.email());

        userRepository.save(newUser);

        return UserResponse.from(newUser);
    }

    public User getUserById(Long id){
        return userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 사용자입니다."));
    }
}
