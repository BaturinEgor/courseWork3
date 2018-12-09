package com.nixsolutions.controller.admin;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import ua.khpi.baturin.controller.AdminController;
import ua.khpi.baturin.entity.Role;
import ua.khpi.baturin.entity.User;
import ua.khpi.baturin.service.contract.RoleService;
import ua.khpi.baturin.service.contract.UserService;

@RunWith(MockitoJUnitRunner.class)
public class AdminControllerTest {

    private MockMvc mockMvc;

    @Mock
    private UserService userService;

    @Mock
    private RoleService roleService;

    @InjectMocks
    private AdminController adminController;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(adminController).build();
    }

    @Test
    public void adminControllerGetMethodShouldSetCorrectUsersModelAtribute()
            throws Exception {
        User user1 = new User(1l, "login1", "e544eeac3ac32212c8c2e2b15153875a",
                "email1@gmail.com", "Ivan", "Ivanov",
                Date.valueOf("1993-03-03"), new Role(1l, "ADMIN"));
        User user2 = new User(2l, "login", "e544eeac3ac32212c8c2e2b15153875a",
                "email2@gmail.com", "Petr", "Petrov",
                Date.valueOf("1995-05-05"), new Role(1l, "USER"));
        List<User> userList = new ArrayList<>();
        userList.add(user1);
        userList.add(user2);
        when(userService.findAll()).thenReturn(userList);
        mockMvc.perform(
                post("/admin").accept(MediaType.APPLICATION_FORM_URLENCODED))
                .andExpect(model().attribute("users", userService.findAll()))
                .andExpect(status().isOk());
    }

    @Test
    public void adminControllerGetMethodShouldHaveMessageParameter()
            throws Exception {
        mockMvc.perform(get("/admin").param("message", "Hello admin"))
                .andExpect(model().attribute("message", "Hello admin"));
    }

    @Test
    public void adminControllerGetMethodShouldDispatchToAdminCabinet()
            throws Exception {
        mockMvc.perform(post("/admin")).andExpect(view().name("admin_cabinet"));
    }

    @Test
    public void adminControllerPostMethodShouldSetCorrectUsersModelAtribute()
            throws Exception {
        User user1 = new User(1l, "login1", "e544eeac3ac32212c8c2e2b15153875a",
                "email1@gmail.com", "Ivan", "Ivanov",
                Date.valueOf("1993-03-03"), new Role(1l, "ADMIN"));
        User user2 = new User(2l, "login", "e544eeac3ac32212c8c2e2b15153875a",
                "email2@gmail.com", "Petr", "Petrov",
                Date.valueOf("1995-05-05"), new Role(1l, "USER"));
        List<User> userList = new ArrayList<>();
        userList.add(user1);
        userList.add(user2);
        when(userService.findAll()).thenReturn(userList);
        mockMvc.perform(
                post("/admin").accept(MediaType.APPLICATION_FORM_URLENCODED))
                .andExpect(model().attribute("users", userService.findAll()))
                .andExpect(status().isOk());

    }

    public void adminControllerPostMethodShouldSetCorrectUserModelAtribute()
            throws Exception {
        User user = new User();
        mockMvc.perform(post("/admin").flashAttr("user", user)
                .accept(MediaType.APPLICATION_FORM_URLENCODED))
                .andExpect(model().attribute("user", user))
                .andExpect(status().isOk());
    }

    @Test
    public void adminControllerPostMethodShouldRedirectToAdminCabinet()
            throws Exception {
        mockMvc.perform(post("/admin")).andExpect(view().name("admin_cabinet"));
    }

}
