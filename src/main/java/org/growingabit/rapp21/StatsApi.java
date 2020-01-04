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
        String chartId = req.getParameter("chartId");

        Map<String, String> dataTable = new HashMap<String, String>();
        switch(chartId)
        { 
            case "projectTopicBadgeCount": 
                dataTable = this.projectTopicBadgeCount();
                break;
            default: 
               throw new IOException("chartId not configured"); 
        }

         req.setAttribute(COLS_ATTRIBUTE, dataTable.get("cols"));
         req.setAttribute(ROWS_ATTRIBUTE, dataTable.get("rows"));

         resp.setContentType("application/javascript; charset=utf-8");
    }

    private Map<String, String> projectTopicBadgeCount() {
        List<TopicEntity> topics = this.topic.findAll();

        List<Map<String,List<Map<String, Object>>>> rows = new ArrayList<Map<String, List<Map<String, Object>>>>();
        Map<String, List<Map<String, Object>>> roww = new HashMap<String, List<Map<String, Object>>>();
        List<Map<String, Object>> rowl = new ArrayList<Map<String, Object>>();
        Map<String, Object> row = new HashMap<String, Object>();
        for (final TopicEntity topic : topics) {
          roww = new HashMap<String, List<Map<String, Object>>>();
          rowl = new ArrayList<Map<String, Object>>();

          String topicTitle = topic.getTitle();
          Integer topicEndorsementCount = this.endorsement.countByTopic(topic.getId());

          row = new HashMap<String, Object>();
          row.put("v", topicTitle);
          rowl.add(row);

          row = new HashMap<String, Object>();
          row.put("v", topicEndorsementCount);
          rowl.add(row);

          roww.put("c", rowl);
          rows.add(roww);
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

        Map<String, String> dataTable = new HashMap<String, String>();
        dataTable.put("cols", JSONValue.toJSONString(cols));
        dataTable.put("rows", JSONValue.toJSONString(rows));

        return dataTable;
    }

    @Override
    protected String _getViewName() {
        return "StatsApi";
    }

    protected Boolean _requireLogin() {
        return false;
    }
}
