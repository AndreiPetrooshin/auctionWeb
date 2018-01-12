package com.petrushin.epam.auction.util;

import org.junit.Assert;
import org.junit.Test;


public class ResourceReaderUtilTest {

    private static final String CORRECT_RESOURCE_PATH = "resourceTest";
    private static final String CORRECT_KEY = "test";
    private static final String EXPECTED_VALUE="Hello World";

    @Test
    public void shouldReturnCorrectValueWhenResPathAndKeyIsCorrect() {
        String result = ResourceReaderUtil.getValue(CORRECT_RESOURCE_PATH, CORRECT_KEY);
        Assert.assertEquals(result,EXPECTED_VALUE);
    }


}