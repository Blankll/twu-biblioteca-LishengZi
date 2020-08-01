package com.twu.biblioteca;

import com.twu.biblioteca.entities.Library;
import com.twu.biblioteca.entities.User;
import com.twu.biblioteca.services.UserService;
import com.twu.biblioteca.services.impls.UserServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.when;

/**
 * @author: Blank
 * @description: com.twu.biblioteca
 * @date: 8/1/20
 * @version: 1.0
 */
@RunWith(MockitoJUnitRunner.class)
public class UserServiceTest {
    private UserService userService;
    @Mock
    private Library library;
    private List<User> users = new ArrayList<>();
    @Before
    public void prepareInjection() {
        users.add(new User(1, "test-01", "test&01", "test01@thoughtworks.com", "13191818181"));
        users.add(new User(1, "test-02", "test&02", "test02@thoughtworks.com", "13191818182"));
        when(library.getUsers()).thenReturn(users);
        userService = new UserServiceImpl(library);
    }

    @Test
    public void givenUsernameWhenFindByNameThenReturnUser() {
        String username = "test-01";
        User user = userService.findByName(username);
        assertThat(user.getUsername(), equalTo(username));
    }
    @Test
    public void givenWrongUsernameWhenFindByNameThenReturnNull() {
        String username = "test";
        User user = userService.findByName(username);
        assertThat(user, nullValue());
    }

    @Test
    public void givenUsernameAndPasswordWhenCustomerLoginTenReturnTrue() {
        Boolean res = userService.login("test-01", "test&01");
        assertThat(res, equalTo(true));
    }
    @Test
    public void givenWrongPasswordWhenCustomerLoginTenReturnFalse() {
        Boolean res = userService.login("test-01", "t");
        assertThat(res, equalTo(false));
    }
    @Test
    public void givenWrongUsernameWhenCustomerLoginTenReturnFalse() {
        Boolean res = userService.login("01", "test&01");
        assertThat(res, equalTo(false));
    }

    @Test
    public void givenUnLoginWhenIsLoginThenReturnFalse() {
        assertThat(userService.isLogin(), nullValue());
    }
    @Test
    public void givenLoginWhenIsLoginThenReturnTrue() {
        User user = new User(1, "test-01", "test&01", "test01@thoughtworks.com", "13191818181");
        when(library.getSession()).thenReturn(user);
        assertThat(userService.isLogin(), equalTo(user));
    }
}
