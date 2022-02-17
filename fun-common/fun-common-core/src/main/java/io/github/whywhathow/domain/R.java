package io.github.whywhathow.domain;

/**
 * @program: fun-project
 * @description: service统一返回对象 Result ->R
 * @author: WhyWhatHow
 * @create: 2022-02-17 12:58
 **/
public class R <T> {

    /**
     * 响应码
     */
    private Integer code;
    /**
     * 返回信息
     */
    private String msg;
    /**
     * 响应数据
     */
    private T data;

    public R() {
    }

    /**
     * 利用 Resultcodes 枚举类创建
     * @param codes
     * @param data
     */
    public R(RCode codes, T data){
        this.code = codes.code;
        this.msg = codes.msg;
        this.data = data;
    }
    public R(Integer code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
