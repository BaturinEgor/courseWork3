package com.nixsolutions.controller.admin;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.anyLong;
import static org.mockito.Matchers.anyObject;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
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
import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributesModelMap;

import ua.khpi.baturin.controller.UpdateController;
import ua.khpi.baturin.dto.UserDto;
import ua.khpi.baturin.entity.Role;
import ua.khpi.baturin.entity.User;
import ua.khpi.baturin.service.contract.RoleService;
import ua.khpi.baturin.service.contract.UserService;

@RunWith(MockitoJUnitRunner.class)
public class UpdateControllerTest {

    private MockMvc mockMvc;

    @InjectMocks
    UpdateController updateController;

    @Mock
    UserService userService;

    @Mock
    RoleService roleService;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(updateController).build();
    }

    @Test
    public void updateControllerGetMethodShouldDispatchToCorrectAddress()
            throws Exception {
        User user = new User(1l, "login1", "e544eeac3ac32212c8c2e2b15153875a",
                "email1@gmail.com", "Ivan", "Ivanov",
                Date.valueOf("1993-03-03"), new Role(1l, "ADMIN"));
        user.setRole(new Role(1l, "ADMIN"));
        when(userService.findById(anyLong())).thenReturn(user);
        mockMvc.perform(get("/update").param("id", "1"))
                .andExpect(view().name("updateUser"))
                .andExpect(status().isOk());
    }

    @Test
    public void updateControllerGetMethodShouldCallFindUserByIdMethod()
            throws Exception {
        User user = new User(1l, "login1", "e544eeac3ac32212c8c2e2b15153875a",
                "email1@gmail.com", "Ivan", "Ivanov",
                Date.valueOf("1993-03-03"), new Role(1l, "ADMIN"));
        user.setRole(new Role(1l, "ADMIN"));
        when(userService.findById(anyLong())).thenReturn(user);
        mockMvc.perform(get("/update").param("id", "1"))
                .andExpect(view().name("updateUser"))
                .andExpect(status().isOk());
        verify(userService, times(1)).findById(anyLong());
    }

    @Test
    public void updateControllerGetMethodShouldSetCorrectRoleAttribute()
            throws Exception {
        User user = new User(1l, "login1", "e544eeac3ac32212c8c2e2b15153875a",
                "email1@gmail.com", "Ivan", "Ivanov",
                Date.valueOf("1993-03-03"), new Role(1l, "ADMIN"));
        user.setRole(new Role(1l, "ADMIN"));
        when(userService.findById(anyLong())).thenReturn(user);
        RedirectAttributes model = mock(RedirectAttributes.class);
        updateController.initUpdating(1l, model);
        verify(model, times(1)).addAttribute("role", "ADMIN");

    }

    @Test
    public void updateControllerGetMethodShouldSetThreeAtributes()
            throws Exception {
        User user = new User(1l, "login1", "e544eeac3ac32212c8c2e2b15153875a",
                "email1@gmail.com", "Ivan", "Ivanov",
                Date.valueOf("1993-03-03"), new Role(1l, "ADMIN"));
        user.setRole(new Role(1l, "ADMIN"));
        when(userService.findById(anyLong())).thenReturn(user);
        RedirectAttributes model = mock(RedirectAttributes.class);
        updateController.initUpdating(1l, model);
        verify(model, times(3)).addAttribute(anyString(), anyString());
    }

    @Test
    public void successfulUpdateControllerPostMethodShouldDispatchToCorrectAddress()
            throws Exception {
        UserDto userDto = new UserDto(1l, "login", "password", "password",
                "email", "Ivan", "Ivanov", "2000-02-02", "ADMIN", "");
        BindingResult result = mock(BindingResult.class);
        RedirectAttributes model = new RedirectAttributesModelMap();
        when(result.hasErrors()).thenReturn(false);
        assertEquals("redirect:/admin",
                updateController.updateProcess(userDto, result, model));
    }

    @Test
    public void successfulUpdateControllerPostMethodShouldSetCorrectMessageAttribute()
            throws Exception {
        UserDto userDto = new UserDto(1l, "login", "password", "password",
                "email", "Ivan", "Ivanov", "2000-02-02", "ADMIN", "");
        BindingResult result = mock(BindingResult.class);
        RedirectAttributes model = mock(RedirectAttributes.class);
        when(result.hasErrors()).thenReturn(false);
        updateController.updateProcess(userDto, result, model);
        verify(model, times(1)).addAttribute("message",
                "User successfully updated");
    }

    @Test
    public void successfulUpdateControllerPostMethodShouldSetCorrectRoleAttribute()
            throws Exception {
        UserDto userDto = new UserDto(1l, "login", "password", "password",
                "email", "Ivan", "Ivanov", "2000-02-02", "ADMIN", "");
        BindingResult result = mock(BindingResult.class);
        RedirectAttributes model = mock(RedirectAttributes.class);
        when(result.hasErrors()).thenReturn(false);
        updateController.updateProcess(userDto, result, model);
        verify(model, times(1)).addAttribute("role", userDto.getRole());
    }

    @Test
    public void failUpdateControllerPostMethodShouldSetCorrectRoleAttribute()
            throws Exception {
        UserDto userDto = new UserDto(1l, "login", "password", "password",
                "email", "Ivan", "Ivanov", "2000-02-02", "ADMIN", "");
        BindingResult result = mock(BindingResult.class);
        RedirectAttributes model = mock(RedirectAttributes.class);
        when(result.hasErrors()).thenReturn(true);
        updateController.updateProcess(userDto, result, model);
        verify(model, times(1)).addAttribute("role", userDto.getRole());
    }

    @Test
    public void successfulUpdateControllerPostMethodShouldSetCorrectUserAttribute()
            throws Exception {
        UserDto userDto = new UserDto(1l, "login", "password", "password",
                "email", "Ivan", "Ivanov", "2000-02-02", "ADMIN", "");
        BindingResult result = mock(BindingResult.class);
        RedirectAttributes model = mock(RedirectAttributes.class);
        when(result.hasErrors()).thenReturn(false);
        updateController.updateProcess(userDto, result, model);
        verify(model, times(1)).addAttribute("role", userDto.getRole());
    }

    @Test
    public void failUpdateControllerPostMethodShouldSetCorrectUserAttribute()
            throws Exception {
        UserDto userDto = new UserDto(1l, "login", "password", "password",
                "email", "Ivan", "Ivanov", "2000-02-02", "ADMIN", "");
        BindingResult result = mock(BindingResult.class);
        RedirectAttributes model = mock(RedirectAttributes.class);
        when(result.hasErrors()).thenReturn(true);
        updateController.updateProcess(userDto, result, model);
        verify(model, times(1)).addAttribute("role", userDto.getRole());
    }

    @Test
    public void updateControllerPostMethodShouldCheckPasswords()
            throws Exception {
        UserDto userDto = new UserDto(1l, "login", "password", "password123",
                "email", "Ivan", "Ivanov", "2000-02-02", "ADMIN", "");
        BindingResult result = mock(BindingResult.class);
        RedirectAttributes model = mock(RedirectAttributes.class);
        when(result.hasErrors()).thenReturn(true);
        assertEquals("updateUser",
                updateController.updateProcess(userDto, result, model));
    }

    @Test
    public void updateControllerPostMethodShouldSetCorrectMessageIfPasswordsAreNotTheSame()
            throws Exception {
        UserDto userDto = new UserDto(1l, "login", "password", "password123",
                "email", "Ivan", "Ivanov", "2000-02-02", "ADMIN", "");
        BindingResult result = mock(BindingResult.class);
        RedirectAttributes model = mock(RedirectAttributes.class);
        when(result.hasErrors()).thenReturn(true);
        updateController.updateProcess(userDto, result, model);
        verify(model, times(1)).addAttribute("error",
                "Password are not the same");
    }

    @Test
    public void updateControllerPostMethodShouldDispatchToCorrectAddresIfHasErrors()
            throws Exception {
        UserDto userDto = new UserDto(1l, "login", "password", "password",
                "email", "Ivan", "Ivanov", "2000-02-02", "ADMIN", "");
        BindingResult result = mock(BindingResult.class);
        RedirectAttributes model = mock(RedirectAttributes.class);
        when(result.hasErrors()).thenReturn(true);
        assertEquals("updateUser",
                updateController.updateProcess(userDto, result, model));
    }

    @Test
    public void successfulUpdateControllerPostMethodShouldUpdateUser()
            throws Exception {
        UserDto userDto = new UserDto(1l, "login", "password", "password",
                "email", "Ivan", "Ivanov", "2000-02-02", "ADMIN", "");
        BindingResult result = mock(BindingResult.class);
        RedirectAttributes model = mock(RedirectAttributes.class);
        when(result.hasErrors()).thenReturn(false);
        updateController.updateProcess(userDto, result, model);
        verify(userService, times(1)).update(anyObject());
    }

    @Test
    public void updateControllerPostMethodShouldNotUpdateUserIfPasswordsAreNotTheSame()
            throws Exception {
        UserDto userDto = new UserDto(1l, "login", "password", "password123",
                "email", "Ivan", "Ivanov", "2000-02-02", "ADMIN", "");
        BindingResult result = mock(BindingResult.class);
        RedirectAttributes model = mock(RedirectAttributes.class);
        when(result.hasErrors()).thenReturn(false);
        updateController.updateProcess(userDto, result, model);
        verify(userService, times(0)).update(anyObject());
    }

    @Test
    public void updateControllerPostMethodShouldNotUpdateUserIfHasErrors()
            throws Exception {
        UserDto userDto = new UserDto(1l, "login", "password", "password",
                "email", "Ivan", "Ivanov", "2000-02-02", "ADMIN", "");
        BindingResult result = mock(BindingResult.class);
        RedirectAttributes model = mock(RedirectAttributes.class);
        when(result.hasErrors()).thenReturn(true);
        updateController.updateProcess(userDto, result, model);
        verify(userService, times(0)).update(anyObject());
    }

}
