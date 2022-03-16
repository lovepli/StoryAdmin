package com.story.storyadmin.basic.RegularExpression.Test;


import com.story.storyadmin.basic.RegularExpression.RegularDemo1;
import org.junit.Test;

import static org.junit.Assert.*;

public class RegularDemo1Test {

    @Test
    public void is19xx() {
        //true
        assertTrue(RegularDemo1.is19xx("1990"));
        assertTrue(RegularDemo1.is19xx("1991"));
        assertTrue(RegularDemo1.is19xx("1992"));
        assertTrue(RegularDemo1.is19xx("1999"));

        //false
        assertFalse(RegularDemo1.is19xx(null));
        assertFalse(RegularDemo1.is19xx(""));
        assertFalse(RegularDemo1.is19xx("19"));
        assertFalse(RegularDemo1.is19xx("199A"));
        assertFalse(RegularDemo1.is19xx("19901"));
        assertFalse(RegularDemo1.is19xx("1990s"));
        assertFalse(RegularDemo1.is19xx("2990"));
        assertFalse(RegularDemo1.is19xx("A990"));
    }
}