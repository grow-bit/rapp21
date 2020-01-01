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

import java.io.*;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import javax.servlet.*;
import com.google.appengine.api.datastore.*;
import com.google.appengine.api.datastore.Query.*;
import java.util.*;

import org.json.simple.*;
import org.json.simple.parser.*;

// With @WebServlet annotation the webapp/WEB-INF/web.xml is no longer required.
@WebServlet(
    name = "TopicsAPI",
    description = "TopicsAPI: show all the rapp21 topics",
    urlPatterns = "/topics"
)
public class Topics extends HttpServlet {

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

        req.setAttribute("topics", this.getTopics());

        RequestDispatcher rd = req.getRequestDispatcher("Topics.jsp");
        rd.forward(req, resp);
    }

    private void initTopics() throws ParseException, FileNotFoundException, IOException {
        JSONParser parser = new JSONParser();

        JSONObject topicsDump = (JSONObject) parser.parse(
            new InputStreamReader(this.getServletContext().getResourceAsStream("/WEB-INF/classes/Topics.json"))
        );

        JSONArray topics = (JSONArray) topicsDump.get("topics");

        List<Entity> entities = new ArrayList<Entity>();
        Entity entity = null;
        for (Object t : topics) {
            JSONObject topic = (JSONObject) t;
            String title = (String) topic.get("title");
            String description = (String) topic.get("description");
            String image = (String) topic.get("image");
            Long id_topic = (Long) topic.get("id_topic");
            entity = new Entity("RApP21Topic", id_topic);
            entity.setProperty("title", title);
            entity.setProperty("description", description);
            entity.setProperty("image", image);
            entity.setProperty("id_topic", id_topic);
            entities.add(entity);
        }

        DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
        datastore.put(entities);
    }

    private List<Map<String, Object>> getTopics() throws IOException {
        DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();

        Query query = new Query("RApP21Topic").addSort("title", SortDirection.ASCENDING);
        PreparedQuery preparedQuery = datastore.prepare(query);
        QueryResultIterator<Entity> results = preparedQuery.asQueryResultIterator();
        if (!results.hasNext()) {
            try {
                this.initTopics();
            } catch (Exception e) {
                throw new IOException(e);
            }
            preparedQuery = datastore.prepare(query);
            results = preparedQuery.asQueryResultIterator();
        }

        List<Map<String,Object>> topics = new ArrayList<Map<String,Object>>();
        Map<String, Object> topic = null;
        Entity entity = null;

        while (results.hasNext()) {  // We still have data
            entity = results.next();      // Add the Book to the List
            topic = new HashMap<String, Object>();
            Map<String, Object> props = entity.getProperties();
            for (Map.Entry<String, Object> prop : props.entrySet()) {
                topic.put(prop.getKey(), prop.getValue());
            }

            topics.add(topic);
        }

        return topics;
    }
}
// [END example]
