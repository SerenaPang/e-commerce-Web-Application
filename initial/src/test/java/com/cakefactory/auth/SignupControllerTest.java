package com.cakefactory.auth;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.security.web.context.SecurityContextRepository;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class SignupControllerTest {

    private SignupService signupService;
    private SecurityContextRepository securityContextRepository;
    private SignupController signupController;

    @BeforeEach
    void setUp() {
        signupService = mock(SignupService.class);
        securityContextRepository = mock(SecurityContextRepository.class);
        signupController = new SignupController(signupService, securityContextRepository);

        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));
    }

    @Test
    void registersUser() {
        signupController.signup("user", "password", "line1", "line2", "P1 CD");
        verify(signupService).register("user", "password", "line1", "line2", "P1 CD");
    }

    @Test
    void redirectsToHomepage() {
        String signupResponse = signupController.signup("user", "password", "line1", "line2", "P1 CD");
        assertThat(signupResponse).isEqualTo("redirect:/");
    }

    @Test
    void redirectsToLoginIfEmailIsTaken() {
        String email = "user@example.com";
        when(signupService.accountExists(email)).thenReturn(true);

        String signupResponse = signupController.signup(email, "password", "line1", "line2", "P1 CD");
        assertThat(signupResponse).isEqualTo("redirect:/login");
    }
}