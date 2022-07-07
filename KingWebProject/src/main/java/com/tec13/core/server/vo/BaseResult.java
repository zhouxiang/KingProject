package com.tec13.core.server.vo;

public class BaseResult<D> {
    private String msg;
    private boolean success;
    private D data;
    public static <D> BaseResult<D> success(){
        return success(null);
    }

    public static <D> BaseResult<D> success(D data){
        BaseResult<D> result = new BaseResult<>();
        result.setSuccess(true);
        result.setData(data);
        return result;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public D getData() {
        return data;
    }

    public void setData(D data) {
        this.data = data;
    }
}
