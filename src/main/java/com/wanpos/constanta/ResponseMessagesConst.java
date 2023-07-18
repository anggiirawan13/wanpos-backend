package com.wanpos.constanta;

public enum ResponseMessagesConst {

    INSERT_SUCCESS {
        @Override
        public String toString() {
            return "INSERT_SUCCESS";
        }
    },
    INSERT_FAILED {
        @Override
        public String toString() {
            return "INSERT_FAILED";
        }
    },
    UPDATE_SUCCESS {
        @Override
        public String toString() {
            return "UPDATE_SUCCESS";
        }
    },
    UPDATE_FAILED {
        @Override
        public String toString() {
            return "UPDATE_FAILED";
        }
    },
    DATA_FOUND {
        @Override
        public String toString() {
            return "DATA_FOUND";
        }
    },
    DATA_NOT_FOUND {
        @Override
        public String toString() {
            return "DATA_NOT_FOUND";
        }
    },
    ALREADY_EXIST {
        @Override
        public String toString() {
            return "ALREADY_EXIST";
        }
    }

}
