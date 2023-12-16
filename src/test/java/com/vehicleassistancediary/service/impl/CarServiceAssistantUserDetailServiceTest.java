package com.vehicleassistancediary.service.impl;

import com.vehicleassistancediary.model.entity.UserEntity;
import com.vehicleassistancediary.model.entity.UserRoleEntity;
import com.vehicleassistancediary.model.entity.enums.UserRoleEnum;
import com.vehicleassistancediary.repository.UserRepository;
import com.vehicleassistancediary.service.CarServiceAssistantUserDetailService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
 class CarServiceAssistantUserDetailServiceTest {
    private CarServiceAssistantUserDetailService serviceToTest;
    @Mock
    private UserRepository mockUserRepository;
    @BeforeEach
    void setUp(){
        serviceToTest = new CarServiceAssistantUserDetailService(
                mockUserRepository
        );
    }
    @Test
    void testUserNotFound(){
        Assertions.assertThrows(UsernameNotFoundException.class,
                () -> serviceToTest.loadUserByUsername("notfound@notfound.com"));
    }
    @Test
    void testUserFoundException(){
        //Arrange - тестови данни
        UserEntity testUserEntity = createTestUser();
        when(mockUserRepository.findByEmail(testUserEntity.getEmail()))
                .thenReturn(Optional.of(testUserEntity));
        //Act - взимаме това което тестваме и да го извикаме
        UserDetails userDetails = serviceToTest.loadUserByUsername(testUserEntity.getEmail());
        //Assert -
        assertNotNull(userDetails);
        assertEquals(
                testUserEntity.getEmail(),
                userDetails.getUsername(),
                "Username is not mapped to email.");

        assertEquals(testUserEntity.getPassword(), userDetails.getPassword());
        assertEquals(2, userDetails.getAuthorities().size());
        assertTrue(
                containsAuthority(userDetails, "ROLE_" + UserRoleEnum.ADMIN),
                "The user is not admin");
        assertTrue(
                containsAuthority(userDetails, "ROLE_" + UserRoleEnum.USER),
                "The user is not user");
    }
    private boolean containsAuthority(UserDetails userDetails, String expectedAuthority) {
        return userDetails
                .getAuthorities()
                .stream()
                .anyMatch(a -> expectedAuthority.equals(a.getAuthority()));
    }
//    private static UserEntity createTestUser() {
//        return new UserEntity()
//                .setFirstName("firstName")
//                .setLastName("lastName")
//                .setEmail("pesho@softuni.bg")
//                .setActive(false)
//                .setPassword("topsecret")
//                .setRoles(List.of(
//                        new UserRoleEntity().setRole(UserRoleEnum.ADMIN),
//                        new UserRoleEntity().setRole(UserRoleEnum.USER)
//                ));
//    }
}
