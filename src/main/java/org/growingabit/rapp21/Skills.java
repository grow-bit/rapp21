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

// [START example]
import com.google.appengine.api.utils.SystemProperty;

import java.io.IOException;
import java.util.Properties;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import javax.servlet.*;

import java.io.*;
import com.google.appengine.api.datastore.*;
import com.google.appengine.api.datastore.Query.*;
import java.util.*;

import org.json.simple.*;
import org.json.simple.parser.*;

// With @WebServlet annotation the webapp/WEB-INF/web.xml is no longer required.
@WebServlet(
    name = "SkillsAPI",
    description = "SkillsAPI: show all the rapp21 skills for a topic",
    urlPatterns = "/skills"
)
public class Skills extends HttpServlet {

  @Override
  public void doGet(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
        UserService userService = UserServiceFactory.getUserService();

        String thisUrl = req.getRequestURI();

        resp.setContentType("text/html");
        if (req.getUserPrincipal() != null) {
            req.setAttribute("username", req.getUserPrincipal().getName());
            req.setAttribute("logoutUrl", userService.createLogoutURL(thisUrl));
        } else {
            resp.sendRedirect(userService.createLoginURL(thisUrl));
            return;
        }


        DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();

        Long id_topic = Long.parseLong(req.getParameter("id_topic"));
        req.setAttribute("id_topic", id_topic);
        req.setAttribute("topic_title", this.getTopicTitle(id_topic));

        req.setAttribute("skills", this.getSkills(id_topic));

        RequestDispatcher rd = req.getRequestDispatcher("Skills.jsp");
        rd.forward(req, resp);
    }

    private void initSkills() throws ParseException, FileNotFoundException, IOException {
        JSONParser parser = new JSONParser();

        JSONObject topicsDump = (JSONObject) parser.parse(
            new InputStreamReader(this.getServletContext().getResourceAsStream("/WEB-INF/classes/Skills.json"))
        );

        JSONArray topics = (JSONArray) topicsDump.get("skills");

        List<Entity> entities = new ArrayList<Entity>();
        Entity entity = null;
        for (Object t : topics) {
            JSONObject topic = (JSONObject) t;
            String title = (String) topic.get("title");
            String description = (String) topic.get("description");
            String image = (String) topic.get("image");
            String id_esco = (String) topic.get("id_esco");
            Long id_topic = (Long) topic.get("id_topic");
            Long id_skill = (Long) topic.get("id_skill");
            entity = new Entity("RApP21Skill", id_skill);
            entity.setProperty("title", title);
            entity.setProperty("description", description);
            entity.setProperty("image", image);
            entity.setProperty("id_topic", id_topic);
            entity.setProperty("id_skill", id_topic);
            entity.setProperty("id_esco", id_esco);
            entities.add(entity);
        }

        DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
        datastore.put(entities);
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

    private List<Map<String, Object>> getSkills(Long id_topic) throws IOException {
        DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();

        com.google.appengine.api.datastore.Query.Filter idTopicFilter = new FilterPredicate("id_topic", FilterOperator.EQUAL, id_topic);
        Query query = new Query("RApP21Skill")
            .setFilter(idTopicFilter)
            .addSort("title", SortDirection.ASCENDING);
        PreparedQuery preparedQuery = datastore.prepare(query);
        QueryResultIterator<Entity> results = preparedQuery.asQueryResultIterator();
        if (!results.hasNext()) {
            try {
                this.initSkills();
            } catch (Exception e) {
                throw new IOException(e);
            }
            preparedQuery = datastore.prepare(query);
            results = preparedQuery.asQueryResultIterator();
        }

        List<Map<String,Object>> skills = new ArrayList<Map<String,Object>>();
        Map<String, Object> skill = null;
        Entity entity = null;

        while (results.hasNext()) {  // We still have data
            entity = results.next();      // Add the Book to the List
            skill = new HashMap<String, Object>();
            Map<String, Object> props = entity.getProperties();
            for (Map.Entry<String, Object> prop : props.entrySet()) {
                skill.put(prop.getKey(), prop.getValue());
            }

            skills.add(skill);
        }

        return skills;
    }
}
// [END example]
