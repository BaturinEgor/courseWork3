package com.nixsolutions.controller.admin;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.anyObject;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
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
import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributesModelMap;

import ua.khpi.baturin.controller.CreateController;
import ua.khpi.baturin.dto.UserDto;
import ua.khpi.baturin.service.contract.UserService;

@RunWith(MockitoJUnitRunner.class)
public class CreateControllerTest {

    private MockMvc mockMvc;

    @Mock
    private UserService userService;

    @InjectMocks
    private CreateController createController;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(createController).build();
    }

    @Test
    public void createControllerGetMethodShouldDispatchToCorrectAddress()
            throws Exception {
        mockMvc.perform(get("/create")).andExpect(status().isOk())
                .andExpect(view().name("createUser"));
    }

    @Test
    public void createControllerGetMethodShouldSetNewUserDtoAsAttribute()
            throws Exception {
        mockMvc.perform(get("/create"))
                .andExpect(model().attribute("user", new UserDto()));
    }

    @Test
    public void successfulCreateControllerPostMethodShouldRedirectToCorrectAddress()
            throws Exception {
        UserDto userDto = new UserDto(1l, "login", "password", "password",
                "email", "Ivan", "Ivanov", "2000-02-02", "ADMIN", "");
        mockMvc.perform(post("/create").flashAttr("user", userDto))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/admin"));
    }

    @Test
    public void successfulCreateControllerPostMethodShouldSetCorrectMessageAttribute()
            throws Exception {
        UserDto userDto = new UserDto(1l, "login", "password", "password",
                "email", "Ivan", "Ivanov", "2000-02-02", "ADMIN", "");
        mockMvc.perform(post("/create").flashAttr("user", userDto)).andExpect(
                model().attribute("message", "User successfully created"));
    }

    @Test
    public void failCreateControllerPostMethodShouldDispatchToCorrectAddress()
            throws Exception {
        UserDto userDto = new UserDto(1l, "login", "password", "drowssap",
                "email", "Ivan", "Ivanov", "2000-02-02", "ADMIN", "");
        BindingResult result = mock(BindingResult.class);
        when(result.hasErrors()).thenReturn(true);
        String view = createController.createInsert(userDto, result, null);
        assertEquals("createUser", view);
    }

    @Test
    public void createPostMethodShouldCallHassErrorsMethod() throws Exception {
        UserDto userDto = new UserDto(1l, "login", "password", "drowssap",
                "email", "Ivan", "Ivanov", "2000-02-02", "ADMIN", "");
        BindingResult result = mock(BindingResult.class);
        when(result.hasErrors()).thenReturn(true);
        createController.createInsert(userDto, result, null);
        verify(result).hasErrors();
    }

    @Test
    public void successCreatePostMethodShouldCallCreateMethod()
            throws Exception {
        UserDto userDto = new UserDto(1l, "login", "password", "drowssap",
                "email", "Ivan", "Ivanov", "2000-02-02", "ADMIN", "");
        BindingResult result = mock(BindingResult.class);
        RedirectAttributes redirectAttributes = new RedirectAttributesModelMap();
        when(result.hasErrors()).thenReturn(false);
        createController.createInsert(userDto, result, redirectAttributes);
        verify(userService).create(anyObject());
    }

    @Test(expected = NullPointerException.class)
    public void createPostMethodShouldThrowNpeIfUserDtoIsNull()
            throws Exception {
        BindingResult result = mock(BindingResult.class);
        RedirectAttributes redirectAttributes = new RedirectAttributesModelMap();
        when(result.hasErrors()).thenReturn(false);
        createController.createInsert(null, result, redirectAttributes);
    }

    @Test(expected = NullPointerException.class)
    public void createPostMethodShouldThrowNpeIfBindingResultIsNull()
            throws Exception {
        UserDto userDto = new UserDto(1l, "login", "password", "drowssap",
                "email", "Ivan", "Ivanov", "2000-02-02", "ADMIN", "");
        RedirectAttributes redirectAttributes = new RedirectAttributesModelMap();
        createController.createInsert(userDto, null, redirectAttributes);
    }

    @Test(expected = NullPointerException.class)
    public void syccessCreatePostMethodShouldThrowNpeIfModelIsNull()
            throws Exception {
        UserDto userDto = new UserDto(1l, "login", "password", "drowssap",
                "email", "Ivan", "Ivanov", "2000-02-02", "ADMIN", "");
        BindingResult result = mock(BindingResult.class);
        when(result.hasErrors()).thenReturn(false);
        createController.createInsert(userDto, result, null);
    }
}
