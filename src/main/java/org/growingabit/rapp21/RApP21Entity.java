package org.growingabit.rapp21;

import com.google.appengine.api.datastore.*;
import com.google.appengine.api.datastore.Query.*;

import java.util.*;

public abstract class RApP21Entity {

    private DatastoreService _ds;
    private AsyncDatastoreService _ads;

    protected DatastoreService ds() {
        if (this._ds == null) {
            this._ds = DatastoreServiceFactory.getDatastoreService();
        }

        return this._ds;
    }

    protected AsyncDatastoreService ads() {
        if (this._ads == null) {
            this._ads = DatastoreServiceFactory.getAsyncDatastoreService();
        }

        return this._ads;
    }

    protected Map<String, Object> toMap(Entity e) {
        return e.getProperties();
    }

    protected abstract Entity e();

    public Map<String, Object> toMap() {
        return this.toMap(this.e());
    }
}

