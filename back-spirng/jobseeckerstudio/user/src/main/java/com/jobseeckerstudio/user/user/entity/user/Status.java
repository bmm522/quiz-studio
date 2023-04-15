package com.jobseeckerstudio.user.user.entity.user;

public enum Status {
    Y("Y"), N("N");

    private final String status;

    Status(final String status){
        this.status = status;
    }

    public String getStatus(){
        return status;
    }


}
