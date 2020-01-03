package org.growingabit.rapp21;

import com.google.appengine.api.datastore.*;
import com.google.appengine.api.datastore.Query.*;

public class AclEntity extends RApP21Entity {

    private String userEmail;

    public AclEntity() {
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

