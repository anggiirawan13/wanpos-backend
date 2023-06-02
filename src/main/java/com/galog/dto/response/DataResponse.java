package com.galog.dto.response;

import java.io.Serializable;

public interface DataResponse extends Serializable {

    String getError_code();
    String getDesc_error();

}
