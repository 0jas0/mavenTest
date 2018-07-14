package com.jas.jdbc;

public class FineDO {
    private Integer fid;
    private Double money;
    private String username;

    public Integer getFid() {
        return fid;
    }

    public void setFid(Integer fid) {
        this.fid = fid;
    }

    public Double getMoney() {
        return money;
    }

    public void setMoney(Double money) {
        this.money = money;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String toString() {
        return "FineDO{" +
                "fid=" + fid +
                ", money=" + money +
                ", username='" + username + '\'' +
                '}';
    }
}
