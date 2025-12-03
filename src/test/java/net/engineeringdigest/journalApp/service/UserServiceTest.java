package net.engineeringdigest.journalApp.service;


import net.engineeringdigest.journalApp.entity.User;
import net.engineeringdigest.journalApp.repositry.UserEntryRepository;

import net.engineeringdigest.journalApp.services.UserService;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class UserServiceTest {

    @Autowired
    private UserEntryRepository userRepository;

    @Autowired
    private UserService userService;

//    @ParameterizedTest
//    @ArgumentsSource(UserArgumentProvider.class)
//    public void testSaveNewUser(User user){
//
//
//        assertTrue(userService.saveNewUser(user));
//
//    }



    @Disabled
    @ParameterizedTest
    @CsvSource({
            "2,3,5",
            "2,4,6",
            "1,2,1"
    })
    public void test(int a, int b, int expected){
        assertEquals(expected, a+b);
    }
}
