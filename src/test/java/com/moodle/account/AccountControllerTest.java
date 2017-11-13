package com.moodle.account;


import com.moodle.account.controller.AccountController;
import com.moodle.account.model.Error;
import com.moodle.account.model.Account;
import com.moodle.account.model.AccountDTO;
import com.moodle.account.service.AccountsService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.BDDMockito.given;

/**
 * Created by andrewlarsen on 11/4/17.
 */
public class AccountControllerTest {


    @Mock
    private AccountsService service;
    private AccountDTO account;
    private AccountController accountController;
    private Account response;
    @Before
    public void setup(){
        MockitoAnnotations.initMocks(this);

        account = new AccountDTO();
        account.setEmail("EMAIL");
        account.setFirstName("FIRSTNAME");
        account.setLastName("LASTNAME");
        account.setPhoneNumber("PHONENUMBER");
        account.setCompanyName("COMPANYNAME");
        accountController = new AccountController(service);
        response = new Account();

    }
    @Test
    public void createAccount201() throws Exception {
        given(service.save(account)).willReturn(response);
        ResponseEntity response = accountController.createAccount(this.account);
        assertThat(response.getStatusCode(), is(HttpStatus.CREATED));

    }

    @Test
    public void createAccountIdPopulated() throws Exception {
        given(service.save(account)).willReturn(response);
        ResponseEntity response = accountController.createAccount(this.account);
        Account responseAccount = (Account) response.getBody();
        assertThat(responseAccount,is(responseAccount));

    }

    @Test
    public void createAccountNoCompanyName() throws Exception {
        account.setCompanyName(null);
        ResponseEntity response = accountController.createAccount(this.account);
        assertThat(response.getStatusCode(), is(HttpStatus.UNPROCESSABLE_ENTITY));

    }
    @Test
    public void createAccountNoFirstName422() throws Exception {
        account.setFirstName(null);
        ResponseEntity response = accountController.createAccount(this.account);
        assertThat(response.getStatusCode(), is(HttpStatus.UNPROCESSABLE_ENTITY));

    }
    @Test
    public void createAccountNoLastName422() throws Exception {
        account.setLastName(null);
        ResponseEntity response = accountController.createAccount(this.account);
        assertThat(response.getStatusCode(), is(HttpStatus.UNPROCESSABLE_ENTITY));

    }
    @Test
    public void createAccountNoLastNameMessage() throws Exception {
        account.setLastName(null);
        ResponseEntity<Error> response = accountController.createAccount(this.account);
        assertThat(response.getBody().getMessage(), is("Missing Required Fields"));

    }
    @Test
    public void createAccountNullaccount() throws Exception {
        ResponseEntity<Error> response = accountController.createAccount(null);
        assertThat(response.getBody().getMessage(), is("Missing Required Fields"));

    }
    @Test
    public void createAccountNoFirstNameMessage() throws Exception {
        account.setFirstName(null);
        ResponseEntity<Error> response = accountController.createAccount(this.account);
        assertThat(response.getBody().getMessage(), is("Missing Required Fields"));

    }
    @Test
    public void createAccountHeadersExist() throws Exception {
        account.setFirstName(null);
        ResponseEntity<Error> response = accountController.createAccount(this.account);
        assertThat(response.getHeaders().size(), is(1));

    }
    @Test
    public void createAccountHeaders() throws Exception {
        given(service.save(account)).willReturn(response);
        ResponseEntity response = accountController.createAccount(this.account);
        assertThat(response.getHeaders().size(), is(1));

    }
    @Test
    public void createAccount422HeadersExistString() throws Exception {
        account.setFirstName(null);
        ResponseEntity<Error> response = accountController.createAccount(this.account);
        assertThat(response.getHeaders().get("Access-Control-Allow-Origin").get(0), is("*"));

    }
    @Test
    public void createAccount202HeadersExistString() throws Exception {
        ResponseEntity response = accountController.createAccount(this.account);
        assertThat(response.getHeaders().get("Access-Control-Allow-Origin").get(0), is("*"));

    }
    @Test
    public void createAccount500() throws Exception {
        given(service.save(account)).willThrow(new RuntimeException());
        ResponseEntity<Error> response = accountController.createAccount(this.account);
        assertThat(response.getBody().getMessage(), is(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase()));

    }
}