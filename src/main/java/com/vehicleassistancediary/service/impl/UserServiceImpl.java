package com.vehicleassistancediary.service.impl;

import com.vehicleassistancediary.model.binding.RegisterUserBindingModel;
import com.vehicleassistancediary.model.entity.UserEntity;
import com.vehicleassistancediary.model.event.UserRegisteredEvent;
import com.vehicleassistancediary.repository.UserRepository;
import com.vehicleassistancediary.service.UserService;
import com.vehicleassistancediary.service.exeption.ObjectNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    private final UserDetailsService userDetailsService;
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;
    private final ApplicationEventPublisher applicationEventPublisher;

    public UserServiceImpl(UserDetailsService userDetailsService, UserRepository userRepository, ModelMapper modelMapper, PasswordEncoder passwordEncoder, ApplicationEventPublisher applicationEventPublisher) {
        this.userDetailsService = userDetailsService;
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
        this.passwordEncoder = passwordEncoder;
        this.applicationEventPublisher = applicationEventPublisher;
    }

    @Override
    public void registerUser(RegisterUserBindingModel registerUserBindingModel) {
        UserEntity user = modelMapper.map(registerUserBindingModel, UserEntity.class);
        user.setActive(false);
        user.setPassword(passwordEncoder.encode(registerUserBindingModel.getPassword()));
        userRepository.save(user);
        applicationEventPublisher.publishEvent(new UserRegisteredEvent(
                "UserService", registerUserBindingModel.getEmail(),
                registerUserBindingModel.fullName()));
    }

    @Override
    public UserEntity findByEmail(String userEmail) {

        return userRepository.findByEmail(userEmail).orElseThrow(() -> new ObjectNotFoundException("User with "+ userEmail + " not found!"));
    }



}
