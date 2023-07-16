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
    },
    WAITING {
        @Override
        public String toString() {
            return "waiting";
        }
    },
    IN_PROGRESS {
        @Override
        public String toString() {
            return "inprogress";
        }
    }
}
