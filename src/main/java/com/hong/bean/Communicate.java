package com.hong.bean;

import java.util.Date;

public class Communicate {
  
    private Integer id;

  
    private String fromUser;

   
    private String toUser;

    
    
    private String message;

   
    private Date sendtime;

  
    public Integer getId() {
        return id;
    }

  
    public void setId(Integer id) {
        this.id = id;
    }

    
    public String getfromUser() {
        return fromUser;
    }

   
    public void setfromUser(String fromUser) {
        this.fromUser = fromUser;
    }

   
    public String gettoUser() {
        return toUser;
    }

   
    public void settoUser(String toUser) {
        this.toUser = toUser;
    }

    
    public String getMessage() {
        return message;
    }

    
    public void setMessage(String message) {
        this.message = message;
    }

   
    public Date getSendtime() {
        return sendtime;
    }

    public void setSendtime(Date sendtime) {
        this.sendtime = sendtime;
    }
}