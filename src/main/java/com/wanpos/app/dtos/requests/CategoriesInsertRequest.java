package com.wanpos.app.dtos.requests;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NonNull;

import java.io.Serializable;

@Data
@NonNull
public class CategoriesInsertRequest implements Serializable {

    @JsonProperty(value = "category_code")
    private String categoryCode;

    @JsonProperty(value = "category_name")
    private String categoryName;

}
