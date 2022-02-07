package com.wackycodes.rest.entity.response;

public class ResponseObject<T> extends RootResponse {
    public ResponseObject() {
    }

    private T data;

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
