package org.growingabit.rapp21;

import com.google.appengine.api.datastore.*;
import com.google.appengine.api.datastore.Query.*;

import java.io.IOException;

import java.util.*; 

public class EndorsementEntity extends RApP21Entity {

    public static final String ID_TOPIC = "id_topic";
    public static final String ID_SKILL = "id_skill";

    private Entity endorsement;
    protected Entity e() {
        return this.endorsement;
    }

    public EndorsementEntity() {
    }

    private Long idSkill;
    public EndorsementEntity(Entity t) {
        this.idSkill = (Long) t.getProperty(ID_SKILL);
    }

    public Integer countByTopic(Long idTopic) {
        com.google.appengine.api.datastore.Query.Filter idTopicFilter = new FilterPredicate(ID_TOPIC, FilterOperator.EQUAL, idTopic);
        Query query = new Query("RApP21Endorsement")
            .setFilter(idTopicFilter)
            .setKeysOnly();
        PreparedQuery preparedQuery = this.ds().prepare(query);
        return preparedQuery.countEntities(FetchOptions.Builder.withDefaults());
    }

    public List<EndorsementEntity> findByTopicId(Long idTopic) {
         com.google.appengine.api.datastore.Query.Filter idTopicFilter = new FilterPredicate(ID_TOPIC, FilterOperator.EQUAL, idTopic);
        Query query = new Query("RApP21Endorsement")
            .setFilter(idTopicFilter);
        PreparedQuery preparedQuery = this.ds().prepare(query);
        QueryResultIterator<Entity> results = preparedQuery.asQueryResultIterator();

        List<EndorsementEntity> all = new ArrayList<EndorsementEntity>();
        while (results.hasNext()) {  
            Entity entity = results.next();
            all.add(new EndorsementEntity(entity));
        }

        return all;
    }


    public Long getSkillId() {
        return this.idSkill;
    }
}

