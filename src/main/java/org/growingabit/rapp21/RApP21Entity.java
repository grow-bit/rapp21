package org.growingabit.rapp21;

import com.google.appengine.api.datastore.*;
import com.google.appengine.api.datastore.Query.*;

public abstract class RApP21Entity {

    private DatastoreService _ds;

    protected DatastoreService ds() {
        if (this._ds == null) {
            this._ds = DatastoreServiceFactory.getDatastoreService();
        }

        return this._ds;
    }
}

