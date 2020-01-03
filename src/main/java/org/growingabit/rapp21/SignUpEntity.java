package org.growingabit.rapp21;

import com.google.appengine.api.datastore.*;
import com.google.appengine.api.datastore.Query.*;

import java.io.IOException;

public class SignUpEntity extends RApP21Entity {

    private String userEmail;

    public static final String PENDING = "pending";
    public static final String COMPLETED = "completed";
    private Entity signUp;
    protected Entity e() {
        return this.signUp;
    }

    private Key k;

    public SignUpEntity(String userPrincipalName) throws IOException {
        this.userEmail = userPrincipalName.toLowerCase();
        try {
            this.signUp = this.ds().get(this.k());
            return;
        } catch (EntityNotFoundException e) {
            this.signUp = new Entity(this.k());
            this.signUp.setProperty(PENDING, true);
            this.signUp.setProperty(COMPLETED, false);
            this.ds().put(this.signUp);
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
}

