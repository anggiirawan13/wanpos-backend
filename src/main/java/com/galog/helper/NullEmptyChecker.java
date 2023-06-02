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
            } else if (value instanceof Collection) {
                return ((Collection) value).isEmpty();
            } else if (value instanceof AbstractMap) {
                return ((AbstractMap) value).isEmpty();
            } else if (value instanceof AbstractCollection) {
                return ((AbstractCollection) value).isEmpty();
            } else if (value instanceof AbstractList) {
                return ((AbstractList) value).isEmpty();
            } else if (value instanceof AbstractSet) {
                return ((AbstractSet) value).isEmpty();
            } else if (value instanceof AbstractSequentialList) {
                return ((AbstractSequentialList) value).isEmpty();
            }
        }

        return false;
    }
}
