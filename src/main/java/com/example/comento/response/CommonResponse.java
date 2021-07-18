package com.example.comento.response;

public class CommonResponse<T> extends BasicResponse{
    private T data;

    public CommonResponse(T data) {
        this.data = data;
    }

    public CommonResponse() {
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
