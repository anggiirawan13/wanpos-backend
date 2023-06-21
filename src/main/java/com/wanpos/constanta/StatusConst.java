package com.wanpos.constanta;

public enum StatusConst {
    ACTIVE {
        @Override
        public String toString() {
            return "active";
        }
    },
    INACTIVE {
        @Override
        public String toString() {
            return "inactive";
        }
    }
}
