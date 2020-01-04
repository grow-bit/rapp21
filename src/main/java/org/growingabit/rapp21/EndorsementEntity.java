package org.growingabit.rapp21;

import com.google.appengine.api.datastore.*;
import com.google.appengine.api.datastore.Query.*;

import java.io.IOException;

import java.util.*; 

public class EndorsementEntity extends RApP21Entity {

    public static final String ID_TOPIC = "id_topic";

    private Entity endorsement;
    protected Entity e() {
        return this.endorsement;
    }

    public EndorsementEntity() {
    }

    public Integer countByTopic(Long idTopic) {
        com.google.appengine.api.datastore.Query.Filter idTopicFilter = new FilterPredicate(ID_TOPIC, FilterOperator.EQUAL, idTopic);
        Query query = new Query("RApP21Endorsement")
            .setFilter(idTopicFilter)
            .setKeysOnly();
        PreparedQuery preparedQuery = this.ds().prepare(query);
        return preparedQuery.countEntities(FetchOptions.Builder.withDefaults());
    }
}

