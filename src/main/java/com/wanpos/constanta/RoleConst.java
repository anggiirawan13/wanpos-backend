package com.wanpos.constanta;

public enum RoleConst {
    ADMIN {
        @Override
        public String toString() {
            return "admin";
        }
    },
    CLIENT {
        @Override
        public String toString() {
            return "client";
        }
    }
}
