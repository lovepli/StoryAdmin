package com.story.storyadmin.basic.RegularExpression.Test;


import com.story.storyadmin.basic.RegularExpression.RegularDemo2;
import org.junit.Test;

import static org.junit.Assert.*;

public class RegularDemo2Test {

    @Test
    public void isValidTel() {
        assertTrue(RegularDemo2.isValidTel("010-123456"));
        assertTrue(RegularDemo2.isValidTel("010-12345678"));
        assertTrue(RegularDemo2.isValidTel("0123-123456"));
        assertTrue(RegularDemo2.isValidTel("0123-12345678"));
        assertTrue(RegularDemo2.isValidTel("123-12345678"));
        assertTrue(RegularDemo2.isValidTel("123-0123456"));

        assertFalse(RegularDemo2.isValidTel("010#12345678"));
        assertFalse(RegularDemo2.isValidTel("010X12345678"));
        assertFalse(RegularDemo2.isValidTel("01-12345678"));
        assertFalse(RegularDemo2.isValidTel("01234-12345678"));
        assertFalse(RegularDemo2.isValidTel("01A-12345678"));
        assertFalse(RegularDemo2.isValidTel("0123-12345"));
        assertFalse(RegularDemo2.isValidTel("0123-12345X"));
        assertFalse(RegularDemo2.isValidTel("0123-123456789"));
        assertFalse(RegularDemo2.isValidTel("0123-12345678X"));
    }
}