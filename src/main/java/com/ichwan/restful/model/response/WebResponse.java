package com.ichwan.restful.model.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class WebResponse<T> {

    private T data;

    private String error;
}
