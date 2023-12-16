package com.vehicleassistancediary.service.impl;

import com.vehicleassistancediary.model.binding.RegisterCarServiceBindingModel;
import com.vehicleassistancediary.model.binding.RegisterUserBindingModel;
import com.vehicleassistancediary.model.entity.UserEntity;
import com.vehicleassistancediary.model.entity.UserRoleEntity;
import com.vehicleassistancediary.model.entity.enums.UserRoleEnum;
import com.vehicleassistancediary.model.event.UserRegisteredEvent;
import com.vehicleassistancediary.repository.UserRepository;
import com.vehicleassistancediary.service.UserRoleService;
import com.vehicleassistancediary.service.UserService;
import com.vehicleassistancediary.service.exeption.ObjectNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    private final UserDetailsService userDetailsService;
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;
    private final ApplicationEventPublisher applicationEventPublisher;
    private final UserRoleService userRoleService;

    public UserServiceImpl(UserDetailsService userDetailsService, UserRepository userRepository, ModelMapper modelMapper, PasswordEncoder passwordEncoder, ApplicationEventPublisher applicationEventPublisher, UserRoleService userRoleService) {
        this.userDetailsService = userDetailsService;
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
        this.passwordEncoder = passwordEncoder;
        this.applicationEventPublisher = applicationEventPublisher;
        this.userRoleService = userRoleService;
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

    @Override
    public void registerService(RegisterCarServiceBindingModel registerCarServiceBindingModel) {
        UserEntity service = modelMapper.map(registerCarServiceBindingModel, UserEntity.class);
        service.setPassword(passwordEncoder.encode(registerCarServiceBindingModel.getPassword()));
        service.setActive(false);
        service.setRoles(List.of(new UserRoleEntity().setRole(UserRoleEnum.SERVICE)));
        userRepository.save(service);
        applicationEventPublisher.publishEvent(new UserRegisteredEvent(
                "UserService", registerCarServiceBindingModel.getEmail(),
                registerCarServiceBindingModel.getName()));
    }

    @Override
    public List<UserEntity> findAllServices() {

        return userRepository.findAllByRoles().stream().collect(Collectors.toList());
    }

    @Override
    public UserEntity findService(String service) {

       return userRepository.findUserEntityByName(service).orElseThrow(() -> new ObjectNotFoundException("Service not found!"));
    }


}
