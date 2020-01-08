/*
 * Copyright 2017 Google Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.growingabit.rapp21;

import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;

import com.google.appengine.api.utils.SystemProperty;

import java.io.*;
import java.util.*;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import javax.servlet.*;
import com.google.appengine.api.datastore.*;

import org.json.simple.*;
import org.json.simple.parser.*;

// With @WebServlet annotation the webapp/WEB-INF/web.xml is no longer required.
@WebServlet(
    name = "StudentsAPI",
    description = "StudentsAPI: show all the rapp21 Students ready for endorsment",
    urlPatterns = "/students"
)
public class Students extends RApP21Servlet {

    @Override
    protected void _doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long id_topic = Long.parseLong(req.getParameter("id_topic"));
        req.setAttribute("id_topic", id_topic);
        req.setAttribute("topic_title", this.getTopicTitle(id_topic));

        Long id_skill = Long.parseLong(req.getParameter("id_skill"));
        req.setAttribute("id_skill", id_skill);
        req.setAttribute("skill_title", this.getSkillTitle(id_skill));

        List<StudentEntity> students = this.student.findAll();
        if (students.size() == 0) {
            try {
                this.initStudents();
                students = this.student.findAll();
            } catch (Exception e) {
                throw new IOException(e);
            }
        }

        req.setAttribute("students", students);
    }

    protected String _getViewName() {
        return "Students";
    }

    private String getTopicTitle(Long id_topic) {
        DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
        try {
            Entity topic = datastore.get(KeyFactory.createKey("RApP21Topic", id_topic));
            return (String) topic.getProperty("title");
        } catch (EntityNotFoundException e) {
            return "";
        }
    }

    private  void initStudents() throws ParseException, FileNotFoundException, IOException {
        JSONParser parser = new JSONParser();

        JSONObject studentsDump = (JSONObject) parser.parse(
            new InputStreamReader(this.getServletContext().getResourceAsStream("/WEB-INF/classes/Students.json"))
        );

        JSONArray students = (JSONArray) studentsDump.get("students");

        List<Entity> entities = new ArrayList<Entity>();
        Entity entity = null;
        for (Object s : students) {
            JSONObject student = (JSONObject) s;
            Long id = (Long) student.get("id");
            String name = (String) student.get("name");
            String surname = (String) student.get("surname");
            String email = (String) student.get("email");
            String classroom = (String) student.get("classroom");
            entity = new Entity("RApP21Student", id);
            entity.setProperty("id_student", id);
            entity.setProperty("email", email);
            entity.setProperty("name", name);
            entity.setProperty("surname", surname);
            entity.setProperty("classroom", classroom);
            entities.add(entity);
        }

        DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
        datastore.put(entities);
    }

    private String getSkillTitle(Long id_skill) {
        DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
        try {
            Entity skill = datastore.get(KeyFactory.createKey("RApP21Skill", id_skill));
            return (String) skill.getProperty("title");
        } catch (EntityNotFoundException e) {
            return "";
        }
    }
}
