package com.gsm.jupjup.model.response;

import lombok.Getter;
import lombok.Setter;

@Getter
public class SingleResult<T> extends CommonResult {
    private T data;
}