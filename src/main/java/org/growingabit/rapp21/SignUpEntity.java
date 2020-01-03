package org.growingabit.rapp21;

import com.google.appengine.api.datastore.*;
import com.google.appengine.api.datastore.Query.*;

import java.io.IOException;

import java.util.*;

public class SignUpEntity extends RApP21Entity {

    private String userEmail;

    private RApP21Mail mail;

    public static final String PENDING = "pending";
    public static final String COMPLETED = "completed";
    public static final String UPDATED_AT = "updatedAt";
    public static final String FIRST_NAME = "firstName";
    public static final String LAST_NAME = "lastName";
    public static final String SCHOOL = "school";

    private Entity signUp;
    protected Entity e() {
        return this.signUp;
    }

    private Key k;

    public SignUpEntity(String userPrincipalName, RApP21Mail mail) throws IOException {
        this.userEmail = userPrincipalName.toLowerCase();
        this.mail = mail;

        try {
            this.signUp = this.ds().get(this.k());
            return;
        } catch (EntityNotFoundException e) {
            this.signUp = new Entity(this.k());
            this.signUp.setProperty(PENDING, true);
            this.signUp.setProperty(COMPLETED, false);
            this.signUp.setProperty(UPDATED_AT, new Date());
            this.ds().put(this.signUp);
            this.mail.newSignUp(this.userEmail);
        }

        try {
            this.signUp = this.ds().get(this.k());
        } catch (EntityNotFoundException e) {
            throw new IOException(e);
        }
    }

    private Key k() {
        if (this.k == null) {
            this.k = KeyFactory.createKey("RApP21SignUp", this.userEmail);
        }

        return k;
    }
    
    public Boolean isPending() {
        return (Boolean) this.signUp.getProperty(PENDING);
    }

    public Boolean isCompleted() {
        return (Boolean) this.signUp.getProperty(COMPLETED);
    }

    public String getUserEmail() {
        return this.userEmail;
    }

    public void complete(String firstName, String lastName, String school) {
        this.signUp.setProperty(FIRST_NAME, firstName);
        this.signUp.setProperty(LAST_NAME, lastName);
        this.signUp.setProperty(SCHOOL, school);
        this.signUp.setProperty(COMPLETED, true);
        this.signUp.setProperty(UPDATED_AT, new Date());
        this.ds().put(this.signUp);
        this.mail.pendingSignUp(firstName, lastName, school, this.userEmail);
    }
}

