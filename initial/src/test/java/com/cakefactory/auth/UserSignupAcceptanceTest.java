package com.cakefactory.auth;

import com.cakefactory.AcceptanceTest;
import com.cakefactory.client.BrowserClient;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

public class UserSignupAcceptanceTest extends AcceptanceTest {

    @Test
    void userIsAutomaticallyLoggedIn() {
        String email = randomEmail();
        String password = "passw0rd!";

        client.goToSignupPage();
        client.fillInDetails(email, password, "address line 1", "address line 2", "P1 ST");
        client.completeSignup();

        assertThat(client.getCurrentUserEmail()).isEqualTo(email);
    }

    @Test
    void userCanLoginAfterSignup() {
        String email = randomEmail();
        String password = "passw0rd!";

        client.goToSignupPage();
        client.fillInDetails(email, password, "address line 1", "address line 2", "P1 ST");
        client.completeSignup();

        BrowserClient newClient = new BrowserClient(mockMvc);
        newClient.goToLoginPage();
        newClient.fillInLogin(email, password);
        newClient.clickPrimaryButton();

        assertThat(newClient.getCurrentUserEmail()).isEqualTo(email);
    }

    private String randomEmail() {
        return UUID.randomUUID().toString() + "@example.com";
    }
}
