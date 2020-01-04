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

import org.json.simple.*;

import java.util.*;

@WebServlet(
    name = "StatsApi",
    description = "StatsApi: rapp21 stats api",
    urlPatterns = "/api/stats"
)
public class StatsApi extends RApP21Servlet {

    public static final String COLS_ATTRIBUTE = "cols";
    public static final String ROWS_ATTRIBUTE = "rows";

    @Override
    protected void _doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<TopicEntity> topics = this.topic.findAll();
        List<List<Object>> stats = new ArrayList<List<Object>>();
        // intestazione
        stats.add(Arrays.asList("Percorso", "#OpenBadge"));

        for (final TopicEntity topic : topics) {
          String topicTitle = topic.getTitle();
          Integer topicSkillCount = this.skill.countByTopic(topic.getId());
          stats.add(Arrays.asList(topicTitle, topicSkillCount));
        }

        List<Map<String,String>> cols = new ArrayList<Map<String, String>>();
        Map<String, String> col = new HashMap<String, String>();
        col.put("id", "A");
        col.put("label", "Percorso");
        col.put("type", "string");
        cols.add(col);
        col = new HashMap<String, String>();
        col.put("id", "B");
        col.put("label", "#OpenBadge");
        col.put("type", "number");
        cols.add(col);

        List<Map<String, Object>> rowl = new ArrayList<Map<String, Object>>();
        Map<String, Object> row = new HashMap<String, Object>();
        row.put("v", "Etica IA");
        rowl.add(row);
        row = new HashMap<String, Object>();
        row.put("v", 10);
        rowl.add(row);
        
        Map<String, List<Map<String, Object>>> roww = new HashMap<String, List<Map<String, Object>>>();
        roww.put("c", rowl);
        List<Map<String,List<Map<String, Object>>>> rows = new ArrayList<Map<String, List<Map<String, Object>>>>();
        rows.add(roww);

         String jsonText = JSONValue.toJSONString(stats);

         req.setAttribute(COLS_ATTRIBUTE, JSONValue.toJSONString(cols));
         req.setAttribute(ROWS_ATTRIBUTE, JSONValue.toJSONString(rows));

         resp.setContentType("application/javascript; charset=utf-8");
    }

    @Override
    protected String _getViewName() {
        return "StatsApi";
    }

    protected Boolean _requireLogin() {
        return false;
    }
}
