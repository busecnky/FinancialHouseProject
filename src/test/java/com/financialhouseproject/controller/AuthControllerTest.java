package com.financialhouseproject.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.financialhouseproject.dto.request.AuthRequestDto;
import com.financialhouseproject.dto.response.AuthResponseDto;
import com.financialhouseproject.service.AuthService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;



@WebMvcTest(controllers = AuthController.class)
public class AuthControllerTest {

    private MockMvc mockMvc;

    @MockBean
    private AuthService authService;
    @Autowired
    private WebApplicationContext webApplicationContext;

    @BeforeEach
    public void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }
    @Test
    public void testLogin() throws Exception {
        AuthRequestDto request = new AuthRequestDto("username", "password");
        AuthResponseDto response = new AuthResponseDto("token", "status");

        Mockito.when(authService.authenticate(request)).thenReturn(response);

        mockMvc.perform(post("/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.token").value("token"));

        Mockito.verify(authService, Mockito.times(1)).authenticate(request);
    }
}
