package com.nixsolutions.controller;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import ua.khpi.baturin.controller.IndexController;

@RunWith(MockitoJUnitRunner.class)
public class IndexControllerTest {

    private MockMvc mockMvc;

    @InjectMocks
    private IndexController indexController;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(indexController).build();
    }

    @Test
    public void indexControllerGetMethodShouldBeMappedOnIndex()
            throws Exception {
        mockMvc.perform(get("/index")).andExpect(view().name("indexPage"));
    }

    @Test
    public void indexControllerGetMethodShouldBeMappedOnRoot()
            throws Exception {
        mockMvc.perform(get("/")).andExpect(view().name("indexPage"));
    }
}
