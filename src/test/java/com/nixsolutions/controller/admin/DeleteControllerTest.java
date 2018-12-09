package com.nixsolutions.controller.admin;

import static org.mockito.Matchers.anyLong;
import static org.mockito.Matchers.anyObject;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.sql.Date;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import ua.khpi.baturin.controller.DeleteController;
import ua.khpi.baturin.entity.Role;
import ua.khpi.baturin.entity.User;
import ua.khpi.baturin.service.contract.UserService;

@RunWith(MockitoJUnitRunner.class)
public class DeleteControllerTest {

    private MockMvc mockMvc;

    @Mock
    private UserService userService;

    @InjectMocks
    private DeleteController deleteController;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(deleteController).build();
    }

    @Test
    public void successDeleteControllerPostMethodShouldRedirectToCorrectAddress()
            throws Exception {
        mockMvc.perform(post("/delete").param("id", "1"))
                .andExpect(view().name("redirect:/admin"))
                .andExpect(status().is3xxRedirection());
    }

    @Test
    public void failDeleteControllerPostMethodShouldRedirectToCorrectAddress()
            throws Exception {
        mockMvc.perform(post("/delete").param("id", "1"))
                .andExpect(view().name("redirect:/admin"))
                .andExpect(status().is3xxRedirection());
    }

    @Test
    public void successDeleteControllerPostMethodShouldSetCorrectMessageAttribute()
            throws Exception {
        mockMvc.perform(post("/delete").param("id", "1")).andExpect(
                model().attribute("message", "User successfully deleted"));
    }

    @Test
    public void failDeleteControllerPostMethodShouldSetCorrectMessageAttribute()
            throws Exception {
        mockMvc.perform(post("/delete").param("id", "")).andExpect(
                model().attribute("message", "User to delete is not found"));
    }

    @Test
    public void deleteControllerPostMethodShouldCallRemoveMethod()
            throws Exception {
        deleteController.delete("1");
        verify(userService).remove(anyObject());
    }

    @Test
    public void deleteControllerPostMethodShouldCallFindByIdMethod()
            throws Exception {
        deleteController.delete("1");
        verify(userService).findById(anyObject());
    }

    @Test
    public void deleteControllerPostMethodShouldRemoveCorrectUser()
            throws Exception {
        User user = new User(1l, "login1", "e544eeac3ac32212c8c2e2b15153875a",
                "email1@gmail.com", "Ivan", "Ivanov",
                Date.valueOf("1993-03-03"), new Role(1l, "ADMIN"));
        when(userService.findById(anyLong())).thenReturn(user);
        deleteController.delete("1");
        verify(userService).remove(user);
    }

    @Test
    public void successfulSeleteControllerPostMethodShouldCallRemoveOnlyOnce()
            throws Exception {
        deleteController.delete("1");
        verify(userService, times(1)).remove(anyObject());
    }

    @Test
    public void failDeleteControllerPostMethodShouldNotCallRemoveMethod()
            throws Exception {
        deleteController.delete("");
        verify(userService, times(0)).remove(anyObject());
    }
}
