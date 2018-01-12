package com.petrushin.epam.auction.util;

import static org.junit.Assert.*;
import org.junit.Test;


public class ValidatorUtilTest {

    private static final boolean EXPECTED_TRUE = true;
    private static final boolean EXPECTED_FALSE = false;
    private static final String INCORRECT_EMAIL = "anovalid.ru";
    private static final String INCORRECT_LOGIN = "f$dima";
    private static final String INCORRECT_PASSWORD = "qwerty123123";
    private static final String CORRECT_EMAIL = "as@valid.ru";
    private static final String CORRECT_LOGIN = "username";
    private static final String CORRECT_PASSWORD = "Qwerty123123";


    @Test
    public void shouldReturnFalseWhenEmailIsNotValid(){
        boolean result = ValidatorUtil.validateEmail(INCORRECT_EMAIL);
        assertEquals(EXPECTED_FALSE, result);

    }


    @Test
    public void shouldReturnTrueWhenEmailIsValid(){
        boolean result = ValidatorUtil.validateEmail(CORRECT_EMAIL);
        assertEquals(EXPECTED_TRUE, result);

    }


    @Test
    public void shouldReturnFalseWhenLoginIsNotValid(){
        boolean result = ValidatorUtil.validateLogin(INCORRECT_LOGIN);
        assertEquals(EXPECTED_FALSE, result);

    }


    @Test
    public void shouldReturnTrueWhenLoginIsValid(){
        boolean result = ValidatorUtil.validateLogin(CORRECT_LOGIN);
        assertEquals(EXPECTED_TRUE, result);

    }


    @Test
    public void shouldReturnFalseWhenPasswordIsNotValid(){
        boolean result = ValidatorUtil.validatePassword(INCORRECT_PASSWORD);
        assertEquals(EXPECTED_FALSE, result);

    }


    @Test
    public void shouldReturnTrueWhenPasswordIsValid(){
        boolean result = ValidatorUtil.validatePassword(CORRECT_PASSWORD);
        assertEquals(EXPECTED_TRUE, result);

    }


}