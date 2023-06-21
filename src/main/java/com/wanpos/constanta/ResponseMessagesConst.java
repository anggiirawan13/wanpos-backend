package com.wanpos.constanta;

public enum ResponseMessagesConst {

    INSERT_SUCCESS {
        @Override
        public String toString() {
            return "INSERT_SUCCESS";
        }
    },
    UPDATE_SUCCESS {
        @Override
        public String toString() {
            return "UPDATE_SUCCESS";
        }
    },
    DATA_FOUND {
        @Override
        public String toString() {
            return "DATA_FOUND";
        }
    }

}
