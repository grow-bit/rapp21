package org.growingabit.rapp21;

import com.google.appengine.api.datastore.*;
import com.google.appengine.api.datastore.Query.*;

import java.io.IOException;

import java.util.*;

public class SkillEntity extends RApP21Entity {

    public static final String TITLE = "title";
    public static final String DESCRIPTION = "description";
    public static final String ID_TOPIC = "id_topic";
    public static final String IMAGE = "image";

    private Entity skill;
    protected Entity e() {
        return this.skill;
    }

    public SkillEntity() {
    }

    public Integer countByTopic(Long idTopic) {
        com.google.appengine.api.datastore.Query.Filter idTopicFilter = new FilterPredicate("id_topic", FilterOperator.EQUAL, idTopic);
        Query query = new Query("RApP21Skill")
            .setFilter(idTopicFilter)
            .setKeysOnly();
        PreparedQuery preparedQuery = this.ds().prepare(query);
        return preparedQuery.countEntities(FetchOptions.Builder.withDefaults());
    }
}

