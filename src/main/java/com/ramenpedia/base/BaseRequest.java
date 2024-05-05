package com.ramenpedia.base;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.ramenpedia.exception.ArgumentException;

@JsonIgnoreProperties(ignoreUnknown = true)
public abstract class BaseRequest {
    public abstract void valid() throws ArgumentException;
}
