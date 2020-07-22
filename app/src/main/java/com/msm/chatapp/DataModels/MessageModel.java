package com.msm.chatapp.DataModels;

import java.io.Serializable;
import java.util.Date;

public class MessageModel implements Serializable {

    private String msg;
    private boolean myMsg;
    private Date date;

    public boolean isMyMsg() {
        return myMsg;
    }

    public Date getDate() {
        return date;
    }

    public String getMsg() {
        return msg;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public void setMyMsg(boolean myMsg) {
        this.myMsg = myMsg;
    }
}
