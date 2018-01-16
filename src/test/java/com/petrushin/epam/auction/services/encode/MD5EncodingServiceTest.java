package com.petrushin.epam.auction.services.encode;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;



@RunWith(Parameterized.class)
public class MD5EncodingServiceTest {

    private static String PASSWORD_VALUE;
    private static String EXPECTED_HASH;

    @Parameterized.Parameters
    public static Collection data(){
        return Arrays.asList(new Object[][]{
                {"123123","4297f44b13955235245b2497399d7a93"},
                {"321321","3d186804534370c3c817db0563f0e461"},
                {"qweqwe","efe6398127928f1b2e9ef3207fb82663"},
                {"parolparol","a3b4249d28808859e24922d62acd632d"}
        });
    }

    public MD5EncodingServiceTest(String value, String result) {
        PASSWORD_VALUE = value;
        EXPECTED_HASH = result;
    }

    @Test
    public void shouldCorrectlyEncodePasswordToHash(){
        String result = MD5EncodingService.encode(PASSWORD_VALUE);
        Assert.assertEquals(EXPECTED_HASH,result);
    }
}