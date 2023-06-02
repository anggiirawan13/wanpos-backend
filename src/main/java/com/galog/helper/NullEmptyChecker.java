package com.galog.helper;

import java.util.*;

public class NullEmptyChecker {
    public static boolean isNotNullOrEmpty(Object value) {
        return !isNullOrEmpty(value);
    }
    public static boolean isNullOrEmpty(Object value) {
        if (value == null) {
            return true;
        } else {
            if (value instanceof String) {
                return String.valueOf(value).trim().isEmpty();
            }

            if (value instanceof Collection) {
                return ((Collection<?>) value).isEmpty();
            }

            if (value instanceof AbstractMap) {
                return ((AbstractMap<?, ?>) value).isEmpty();
            }
        }

        return false;
    }
}
