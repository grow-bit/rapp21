package org.growingabit.rapp21;

import com.google.appengine.api.datastore.*;
import com.google.appengine.api.datastore.Query.*;

import java.io.IOException;

import java.util.*;

public class StudentEntity extends RApP21Entity {

    public static final String NAME = "name";
    public static final String SURNAME = "surname";
    public static final String EMAIL = "email";
    public static final String CLASSROOM = "classroom";

    private Entity student;
    protected Entity e() {
        return this.student;
    }

    public StudentEntity() {
    }

    private Long id;
    private String name;
    private String surname;
    private String email;
    private String classroom;
    public StudentEntity(Entity t) {
        this.id = t.getKey().getId();
        this.email = (String) t.getProperty(EMAIL);
        this.name = (String) t.getProperty(NAME);
        this.surname = (String) t.getProperty(SURNAME);
        this.classroom = (String) t.getProperty(CLASSROOM);
    }

    public Long getId() {
        return this.id;
    }

    public String getEmail() {
        return this.email;
    }
    
    public String getSurname() {
        return this.surname;
    }

    public String getName() {
        return this.name;
    }

    public String getClassroom() {
        return this.classroom;
    }

    public List<StudentEntity> findAll() {
        Query query = new Query("RApP21Student").addSort("name", SortDirection.ASCENDING);
        PreparedQuery preparedQuery = this.ds().prepare(query);
        QueryResultIterator<Entity> results = preparedQuery.asQueryResultIterator();

        List<StudentEntity> all = new ArrayList<StudentEntity>();
        while (results.hasNext()) {  
            Entity entity = results.next();
            all.add(new StudentEntity(entity));
        }

        return all;
    }
}

