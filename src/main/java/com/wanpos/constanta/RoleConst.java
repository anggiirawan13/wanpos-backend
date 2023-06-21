package com.wanpos.constanta;

public enum RoleConst {
    ADMIN {
        @Override
        public String toString() {
            return "admin";
        }
    },
    CASHIER {
        @Override
        public String toString() {
            return "cashier";
        }
    },
    EMPLOYEE {
        @Override
        public String toString() {
            return "employee";
        }
    }
}
