package com.nixsolutions.controller;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import ua.khpi.baturin.controller.LoginController;

@RunWith(MockitoJUnitRunner.class)
public class LoginControllerTest {

    private MockMvc mockMvc;

    @InjectMocks
    private LoginController loginController;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(loginController).build();
    }

    @Test
    public void successfulLoginControllerGetMethodShouldDispatchToCorrectAddress()
            throws Exception {
        mockMvc.perform(get("/login")).andExpect(view().name("loginPage"))
                .andExpect(status().isOk());
    }

    @Test
    public void failLoginControllerGetMethodShouldDispatchToCorrectAddress()
            throws Exception {
        mockMvc.perform(get("/login").param("error", "error message"))
                .andExpect(view().name("loginPage")).andExpect(status().isOk());
    }

    @Test
    public void failLoginControllerGetMethodShouldSetCorrectErrorMessagethrows()
            throws Exception {
        mockMvc.perform(get("/login").param("error", "error message"))
                .andExpect(model().attribute("error",
                        "Login or password is incorrect"));
    }

    @Test
    public void testLoginControllerGetMethodShouldBeSuccessWithNullError()
            throws Exception {
        String error = null;
        mockMvc.perform(get("/login").param("error", error))
                .andExpect(view().name("loginPage")).andExpect(status().isOk());
    }
}
