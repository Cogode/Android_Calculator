package com.example.calculator.domain;

import java.util.List;

public class MoneyInfomation {
    private String code;
    private String msg;
    private List<Money> newslist;

    public static class Money {
        private String money;

        public String getMoney() {
            return money;
        }

        public void setMoney(String money) {
            this.money = money;
        }
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public List<Money> getNewslist() {
        return newslist;
    }

    public void setNewslist(List<Money> newslist) {
        this.newslist = newslist;
    }
}
