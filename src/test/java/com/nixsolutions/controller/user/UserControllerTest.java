package com.nixsolutions.controller.user;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
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

import ua.khpi.baturin.controller.UserController;

@RunWith(MockitoJUnitRunner.class)
public class UserControllerTest {

    private MockMvc mockMvc;

    @InjectMocks
    private UserController userController;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(userController).build();
    }

    @Test
    public void userControllerPostMethodShouldDispatchToCorrectAddress()
            throws Exception {
        mockMvc.perform(post("/user")).andExpect(view().name("user_cabinet"))
                .andExpect(status().isOk());
    }

    @Test
    public void userControllerGetMethodShouldDispatchToCorrectAddress()
            throws Exception {
        mockMvc.perform(get("/user")).andExpect(view().name("user_cabinet"))
                .andExpect(status().isOk());
    }
}
