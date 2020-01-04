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

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.text.StringEscapeUtils;
import java.util.stream.Collectors;

import com.google.visualization.datasource.DataSourceHelper;
import com.google.visualization.datasource.DataSourceRequest;
import com.google.visualization.datasource.base.DataSourceException;
import com.google.visualization.datasource.base.ReasonType;
import com.google.visualization.datasource.base.ResponseStatus;
import com.google.visualization.datasource.base.StatusType;
import com.google.visualization.datasource.base.TypeMismatchException;
import com.google.visualization.datasource.datatable.ColumnDescription;
import com.google.visualization.datasource.datatable.DataTable;
import com.google.visualization.datasource.datatable.value.ValueType;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


@WebServlet(
    name = "StatsApi",
    description = "StatsApi: rapp21 stats api",
    urlPatterns = "/api/stats"
)
public class StatsApi extends RApP21Servlet {

    private static final Log log = LogFactory.getLog(StatsApi.class.getName());


    @Override
    protected void _doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String chartId = req.getParameter("chartId");
        DataTable data;
        switch(chartId)
        { 
            case "RApPStats0": 
                data = this.projectTopicBadgeCount();
                break;
            case "RApPStats1": 
                data = this.badgeCountByTopic(1l);
                break;
            case "RApPStats2": 
                data = this.badgeCountByTopic(2l);
                break;
            case "RApPStats3": 
                data = this.badgeCountByTopic(3l);
                break;
            case "RApPStats4": 
                data = this.badgeCountByTopic(4l);
                break;
            case "RApPStats5": 
                data = this.badgeCountByTopic(5l);
                break;
            case "RApPStats6": 
                data = this.badgeCountByTopic(6l);
                break;
            default: 
               throw new IOException("chartId not configured"); 
        }

        // https://github.com/google/google-visualization-java/blob/master/examples/src/java/SimpleExampleServlet2.java#L58-L89
        
        DataSourceRequest dsRequest = null;

        try {
            // Extract the datasource request parameters.
            dsRequest = new DataSourceRequest(req);

            DataTable newData = DataSourceHelper.applyQuery(dsRequest.getQuery(), data,
                dsRequest.getUserLocale());

            // Set the response.
            DataSourceHelper.setServletResponse(newData, dsRequest, resp);
        } catch (RuntimeException rte) {
            log.error("A runtime exception has occured", rte);
            ResponseStatus status = new ResponseStatus(StatusType.ERROR, ReasonType.INTERNAL_ERROR,
                rte.getMessage());
            if (dsRequest == null) {
                dsRequest = DataSourceRequest.getDefaultDataSourceRequest(req);
            }
            DataSourceHelper.setServletErrorResponse(status, dsRequest, resp);
        } catch (DataSourceException e) {
            if (dsRequest != null) {
                DataSourceHelper.setServletErrorResponse(e, dsRequest, resp);
            } else {
                DataSourceHelper.setServletErrorResponse(e, req, resp);
            }
        }
         
    }

    private DataTable projectTopicBadgeCount() {
        // Create a data table,
        // https://github.com/google/google-visualization-java/blob/master/examples/src/java/SimpleExampleServlet2.java#L93-L113
        DataTable data = new DataTable();
        ArrayList<ColumnDescription> cd = new ArrayList<ColumnDescription>();
        cd.add(new ColumnDescription("percorso", ValueType.TEXT, "Percorso"));
        cd.add(new ColumnDescription("ob_count", ValueType.NUMBER, "#OpenBadge"));
        
        data.addColumns(cd);

        // Fill the data table.
        List<TopicEntity> topics = this.topic.findAll();
        for (final TopicEntity topic : topics) {
            String topicTitle = topic.getTitle();
            Integer topicEndorsementCount = this.endorsement.countByTopic(topic.getId());
            try {
                data.addRowFromValues(topicTitle, topicEndorsementCount);
            } catch (TypeMismatchException e) {
                log.warn("Invalid type for %s %d".format(topicTitle, topicEndorsementCount), e);
            }
        }
        
        return data;
    }

    private DataTable badgeCountByTopic(Long topicId) {
        // Create a data table,
        // https://github.com/google/google-visualization-java/blob/master/examples/src/java/SimpleExampleServlet2.java#L93-L113
        DataTable data = new DataTable();
        ArrayList<ColumnDescription> cd = new ArrayList<ColumnDescription>();
        cd.add(new ColumnDescription("ob_title", ValueType.TEXT, "Soft Skill"));
        cd.add(new ColumnDescription("ob_count", ValueType.NUMBER, "#OpenBadge"));
        
        data.addColumns(cd);

        // Fill the data table.
        List<EndorsementEntity> endorsements = this.endorsement.findByTopicId(topicId);
        Map<Long, Long> badgeCount = new HashMap<Long, Long>();
        for (final EndorsementEntity endorsement : endorsements) {
            Long skillId = endorsement.getSkillId();
            Long skillCount = badgeCount.get(skillId);
            if (skillCount == null) {
                skillCount = 0l;
            }
            skillCount += 1l;
            badgeCount.put(skillId, skillCount);
        }

        Map<Long, SkillEntity> skills = this.skill.findByIds(badgeCount.keySet());
        for (Map.Entry<Long, SkillEntity> skillEntry : skills.entrySet()) {
            Long badgeCountForSkill = badgeCount.get(skillEntry.getKey());
            String skillTitle = skillEntry.getValue().getTitle();
            try {
                data.addRowFromValues(skillTitle, badgeCountForSkill);
            } catch (TypeMismatchException e) {
                log.warn("Invalid type for %s %d".format(skillTitle, badgeCountForSkill), e);
            }
        }

        return data;
    }

    protected Boolean _requireLogin() {
        return false;
    }
}
