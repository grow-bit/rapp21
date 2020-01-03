package org.growingabit.rapp21.utils;

public class Acl {

    private String userEmail;

    public Acl() {
    }

    public Boolean check() {
        return true;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUserEmail() {
        return this.userEmail;
    }
}

