package org.growingabit.rapp21;

import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;

// [START example]
import com.google.appengine.api.utils.SystemProperty;

import java.io.IOException;
import java.util.*;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import javax.servlet.*;
import org.json.simple.*;

@WebServlet(
    name = "Stats",
    description = "Stats: rapp21 stats",
    urlPatterns = "/stats"
)
public class Stats extends RApP21Servlet {

    public static final String TOPICS_JSON_ATTRIBUTE = "topicsJson";

    @Override
    protected void _doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<TopicEntity> topics = this.topic.findAll();
        List<Object> topicsJson = new ArrayList<Object>(); 
        for (TopicEntity topic : topics) {
            topicsJson.add(topic.toMap());
        }

        req.setAttribute(TOPICS_JSON_ATTRIBUTE, JSONValue.toJSONString(topicsJson));
    }

    @Override
    protected String _getViewName() {
        return "Stats";
    }

    protected Boolean _requireLogin() {
        return false;
    }
}
