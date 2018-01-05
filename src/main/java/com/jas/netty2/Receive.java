package com.jas.netty2;

import java.io.Serializable;
import java.util.Arrays;

/**
 * Created by Administrator on 2017/12/21.
 */
public class Receive implements Serializable {

    /**
     * serialVersionUID:TODO（用一句话描述这个变量表示什么）
     * @since 1.0.0
     */

    private static final long serialVersionUID = 1L;
    private Integer id;
    private String name;
    private String message;
    private byte[] sss;

    public byte[] getSss() {
        return sss;
    }
    public void setSss(byte[] sss) {
        this.sss = sss;
    }
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }
    @Override
    public String toString() {
        return "Receive [id=" + id + ", name=" + name + ", message=" + message + ", sss=" + Arrays.toString(sss) + "]";
    }

}

