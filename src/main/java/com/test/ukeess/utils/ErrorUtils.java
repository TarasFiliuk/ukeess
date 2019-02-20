package com.test.ukeess.utils;


import com.test.ukeess.error.Error;
import com.test.ukeess.error.Errors;

import static java.util.Arrays.asList;

public class ErrorUtils {
    public static Error newError(String field, String message) {
        return new Error(field, message);
    }

    public static Errors newErrorsList(Error... errors) {
        return new Errors(asList(errors));
    }
}


