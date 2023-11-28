package com.vehicleassistancediary.web;

import com.icegreen.greenmail.util.GreenMail;
import com.icegreen.greenmail.util.ServerSetup;
import jakarta.mail.internet.MimeMessage;
import jakarta.validation.Valid;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class UserRegisterControllerTestIT {
    @Autowired
    private MockMvc mockMvc;

    @Value("${mail.port}")
    private int port;
    @Value("${mail.host}")
    private String host;
    @Value("@{mail.username}")
    private String username;
    @Value("@{mail.password}")
    private String password;

    private GreenMail greenMail;

    @BeforeEach
    void setup() {
        greenMail = new GreenMail(new ServerSetup(port, host, "smtp"));
        greenMail.start();
        greenMail.setUser(username, password);

    }

    @AfterEach
    void tearDown() {
        greenMail.stop();
    }

    @Test
    void testRegistrationUser() throws Exception {
        mockMvc.perform(
                MockMvcRequestBuilders.post("/registration/user")
                        .param("firstName", "Test")
                        .param("lastName", "User")
                        .param("email", "test_user@testmail.com")
                        .param("address", "Sofia, Bulgaria")
                        .param("password", "testPass")
                        .param("confirmPassword", "testPass")
                        .with(csrf())
        ).andExpect(status().is3xxRedirection());
        greenMail.waitForIncomingEmail(1);
        MimeMessage[] receivedMessages
                = greenMail.getReceivedMessages();
        Assertions.assertEquals(1, receivedMessages.length);
        MimeMessage registrationEmail = receivedMessages[0];
        Assertions.assertTrue(registrationEmail.getContent().toString().contains("Test"));
        Assertions.assertEquals(1, registrationEmail.getAllRecipients().length);
        Assertions.assertEquals("test_user@testmail.com", registrationEmail.getAllRecipients()[0].toString());
    }

}