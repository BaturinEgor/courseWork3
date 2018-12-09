package com.nixsolutions.controller;

import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.util.ArrayList;
import java.util.Collection;

import javax.servlet.http.HttpSession;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextImpl;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import ua.khpi.baturin.controller.HomeController;

@RunWith(MockitoJUnitRunner.class)
public class HomeControllerTest {

    private static final String ADMIN_ROLE = "ADMIN";
    private static final String USER_ROLE = "USER";

    private MockMvc mockMvc;

    @InjectMocks
    private HomeController homeController;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(homeController).build();
    }

    @Test
    public void homeControllerGetMethodShoul1dRedirectToAdmin()
            throws Exception {
        HttpSession session = mock(HttpSession.class);
        SecurityContextImpl context = mock(SecurityContextImpl.class);
        Authentication authentication = mock(Authentication.class);
        UserDetails user = mock(UserDetails.class);
        GrantedAuthority grantedAuth = mock(GrantedAuthority.class);
        Collection<GrantedAuthority> auths = new ArrayList<>();
        auths.add(grantedAuth);

        initMock(session, context, authentication, user, grantedAuth, auths,
                ADMIN_ROLE);
        mockMvc.perform(
                get("/home").flashAttr("SPRING_SECURITY_CONTEXT", context))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/admin"));
    }

    @Test
    public void homeControllerGetMethodShoul1dRedirectToUser()
            throws Exception {
        HttpSession session = mock(HttpSession.class);
        SecurityContextImpl context = mock(SecurityContextImpl.class);
        Authentication authentication = mock(Authentication.class);
        UserDetails user = mock(UserDetails.class);
        GrantedAuthority grantedAuth = mock(GrantedAuthority.class);
        Collection<GrantedAuthority> auths = new ArrayList<>();
        auths.add(grantedAuth);

        initMock(session, context, authentication, user, grantedAuth, auths,
                USER_ROLE);

        mockMvc.perform(
                get("/home").flashAttr("SPRING_SECURITY_CONTEXT", context))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/user"));
    }

    @Test(expected = IllegalArgumentException.class)
    public void homeControllerGetMethodShoul1dRedirectToUs1er()
            throws Exception {
        mockMvc.perform(get("/home").flashAttr("SPRING_SECURITY_CONTEXT", null))
                .andExpect(status().isExpectationFailed());
    }

    @Test
    public void homeControllerGetMethodShouldCallGetAuthoritiesOnlyOnce()
            throws Exception {
        HttpSession session = mock(HttpSession.class);
        SecurityContextImpl context = mock(SecurityContextImpl.class);
        Authentication authentication = mock(Authentication.class);
        UserDetails user = mock(UserDetails.class);
        GrantedAuthority grantedAuth = mock(GrantedAuthority.class);
        Collection<GrantedAuthority> auths = new ArrayList<>();
        auths.add(grantedAuth);

        initMock(session, context, authentication, user, grantedAuth, auths,
                USER_ROLE);

        mockMvc.perform(
                get("/home").flashAttr("SPRING_SECURITY_CONTEXT", context))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/user"));
        verify(user, times(1)).getAuthorities();
    }

    @Test
    public void homeControllerGetMethodShouldCallGetAuthentificationOnlyOnce()
            throws Exception {
        HttpSession session = mock(HttpSession.class);
        SecurityContextImpl context = mock(SecurityContextImpl.class);
        Authentication authentication = mock(Authentication.class);
        UserDetails user = mock(UserDetails.class);
        GrantedAuthority grantedAuth = mock(GrantedAuthority.class);
        Collection<GrantedAuthority> auths = new ArrayList<>();
        auths.add(grantedAuth);

        initMock(session, context, authentication, user, grantedAuth, auths,
                USER_ROLE);

        mockMvc.perform(
                get("/home").flashAttr("SPRING_SECURITY_CONTEXT", context))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/user"));
        verify(context, times(1)).getAuthentication();
    }

    @Test
    public void homeControllerGetMethodShouldCallGetPrincipalOnlyOnce()
            throws Exception {
        HttpSession session = mock(HttpSession.class);
        SecurityContextImpl context = mock(SecurityContextImpl.class);
        Authentication authentication = mock(Authentication.class);
        UserDetails user = mock(UserDetails.class);
        GrantedAuthority grantedAuth = mock(GrantedAuthority.class);
        Collection<GrantedAuthority> auths = new ArrayList<>();
        auths.add(grantedAuth);

        initMock(session, context, authentication, user, grantedAuth, auths,
                USER_ROLE);

        mockMvc.perform(
                get("/home").flashAttr("SPRING_SECURITY_CONTEXT", context))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/user"));
        verify(authentication, times(1)).getPrincipal();
    }

    private void initMock(HttpSession session,
            SecurityContextImpl securityContext, Authentication authentication,
            UserDetails applicationUser, GrantedAuthority grantedAuth,
            Collection<GrantedAuthority> auths, String role) {
        when(session.getAttribute("SPRING_SECURITY_CONTEXT"))
                .thenReturn(securityContext);
        when(securityContext.getAuthentication()).thenReturn(authentication);
        when(authentication.getPrincipal()).thenReturn(applicationUser);
        doReturn(auths).when(applicationUser).getAuthorities();
        when(grantedAuth.getAuthority()).thenReturn(role);
    }

}
