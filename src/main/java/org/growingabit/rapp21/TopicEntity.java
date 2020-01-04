package org.growingabit.rapp21;

import com.google.appengine.api.datastore.*;
import com.google.appengine.api.datastore.Query.*;

import java.io.IOException;

import java.util.*;

public class TopicEntity extends RApP21Entity {

    public static final String TITLE = "title";
    public static final String DESCRIPTION = "description";
    public static final String ID_TOPIC = "id_topic";
    public static final String IMAGE = "image";

    private Entity topic;
    protected Entity e() {
        return this.topic;
    }

    public TopicEntity() {
    }

    private Long idTopic;
    private String title;
    public TopicEntity(Entity t) {
        this.idTopic = t.getKey().getId();
        this.title = (String) t.getProperty(TITLE);
        this.topic = t;
    }

    public Long getId() {
        return this.idTopic;
    }
    
    public String getTitle() {
        return this.title;
    }

    public List<TopicEntity> findAll() {
        Query query = new Query("RApP21Topic")
            .addSort("title", SortDirection.ASCENDING);
        PreparedQuery preparedQuery = this.ds().prepare(query);
        QueryResultIterator<Entity> results = preparedQuery.asQueryResultIterator();

        List<TopicEntity> all = new ArrayList<TopicEntity>();
        while (results.hasNext()) {  
            Entity entity = results.next();
            all.add(new TopicEntity(entity));
        }

        return all;
    }
}

