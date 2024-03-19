package com.zhaoyani.myapp.bean;

import org.json.JSONArray;
import org.json.JSONObject;

public class CommonResult {
    private String state;//状态
    private String msg;//描述
    private String token;//临时标记
    private JSONObject data;//数据对象
    private JSONArray rows;//列表
    private JSONArray value;//列表
    private JSONArray values;//列表
    private int total;//array的数量



    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public JSONObject getData() {
        return data;
    }

    public void setData(JSONObject data) {
        this.data = data;
    }

    public JSONArray getRows() {
        return rows;
    }

    public void setRows(JSONArray rows) {
        this.rows = rows;
    }

    public JSONArray getValue() {
        return value;
    }

    public void setValue(JSONArray value) {
        this.value = value;
    }

    public JSONArray getValues() {
        return values;
    }

    public void setValues(JSONArray values) {
        this.values = values;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }
}
