package com.wanpos.helper;

import java.util.Collection;
import java.util.Map;

public class NullEmptyChecker {

    public static boolean isNotNullOrEmpty(Object value) {
        return !isNullOrEmpty(value);
    }

    public static boolean isNullOrEmpty(Object value) {
        if (value == null) return true;
        else
            if (value instanceof Collection) return ((Collection<?>) value).isEmpty();
            else if (value instanceof Map)  return ((Map<?, ?>) value).isEmpty();
            else if (value instanceof String) return ((String) value).trim().isEmpty() || ((String) value).equalsIgnoreCase("null");
            else if (value instanceof Number) return ((Number) value).intValue() <= 0;

        return false;
    }

}
