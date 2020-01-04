package org.growingabit.rapp21;

import com.google.appengine.api.datastore.*;
import com.google.appengine.api.datastore.Query.*;

import java.io.IOException;

import java.util.*;

public class SkillEntity extends RApP21Entity {

    public static final String TITLE = "title";
    public static final String DESCRIPTION = "description";
    public static final String ID_TOPIC = "id_topic";
    public static final String ID_SKILL = "id_skill";
    public static final String ID_ESCO = "id_esco";
    public static final String IMAGE = "image";

    private Entity skill;
    protected Entity e() {
        return this.skill;
    }

    public SkillEntity() {
    }

    private Long idTopic;
    private Long idSkill;
    private String title;
    public SkillEntity(Entity t) {
        this.idSkill = t.getKey().getId();
        this.title = (String) t.getProperty(TITLE);
    }

    public Long getId() {
        return this.idSkill;
    }
    
    public String getTitle() {
        return this.title;
    }

    public Map<Long, SkillEntity> findByIds(Set<Long> skillIds) {
        List<Key> keys = new ArrayList<Key>();
        for(Long skillId : skillIds) {
            keys.add(KeyFactory.createKey("RApP21Skill", skillId));
        }
        Map<Key, Entity> skillsEntities = this.ds().get(keys);

        Map<Long, SkillEntity> skills = new HashMap<Long, SkillEntity>();
        for (Map.Entry<Key, Entity> skillEntry : skillsEntities.entrySet()) {
          skills.put(skillEntry.getKey().getId(), new SkillEntity(skillEntry.getValue()));
        }

        return skills;
    }
}

