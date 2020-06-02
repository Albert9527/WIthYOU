package com.zcz1024.withyou.Presenter;


import com.zcz1024.withyou.pojoVO.BookTjVo;

import java.util.List;

public class ResultData<T>{
    /**
     * success : true
     * msg : 登陆成功
     * datalist : null
     * data : null
     */

    private boolean success;
    private String msg;
    private List<T> datalist;
    private T data;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public List<T> getDatalist() {
        return datalist;
    }

    public void setDatalist(List<T> datalist) {
        this.datalist = datalist;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
