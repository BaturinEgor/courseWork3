package com.nixsolutions.controller;

import static org.mockito.Matchers.anyObject;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import ua.khpi.baturin.controller.RegistrationController;
import ua.khpi.baturin.dto.UserDto;
import ua.khpi.baturin.service.contract.UserService;

@RunWith(MockitoJUnitRunner.class)
public class RegistrationControllerTest {
    private MockMvc mockMvc;

    @InjectMocks
    private RegistrationController registrationController;

    @Mock
    private UserService userService;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(registrationController)
                .build();
    }

    @Test
    public void registrationControllerGetMethodShouldDispatchToCorrectAddress()
            throws Exception {
        mockMvc.perform(get("/registration"))
                .andExpect(view().name("registrationPage"))
                .andExpect(status().isOk());
    }

    @Test
    public void registrationControllerGetMethodShouldSetUserAttribute()
            throws Exception {
        mockMvc.perform(get("/registration"))
                .andExpect(model().attribute("user", new UserDto()));
    }

    @Test
    public void registrationControllerPostMethodShouldCheckCaptchaResponse()
            throws Exception {
        UserDto userDto = new UserDto(1l, "login", "password", "password",
                "email", "Ivan", "Ivanov", "2000-02-02", "ADMIN", "");
        mockMvc.perform(post("/registration").flashAttr("user", userDto)
                .param("g-recaptcha-response", "response"))
                .andExpect(status().isOk())
                .andExpect(view().name("registrationPage"));
    }

    @Test
    public void failRegistrationControllerPostMethodShouldNotCreateUser()
            throws Exception {
        UserDto userDto = new UserDto(1l, "login", "password", "password",
                "email", "Ivan", "Ivanov", "2000-02-02", "ADMIN", "");
        mockMvc.perform(post("/registration").flashAttr("user", userDto)
                .param("g-recaptcha-response", "response"));
        verify(userService, times(0)).create(anyObject());
    }
}
