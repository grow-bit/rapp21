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

    public static final String COLS_ATTRIBUTE = "cols";
    public static final String ROWS_ATTRIBUTE = "rows";

    private static final Log log = LogFactory.getLog(StatsApi.class.getName());


    @Override
    protected void _doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String chartId = req.getParameter("chartId");
        DataTable data;
        switch(chartId)
        { 
            case "projectTopicBadgeCount": 
                data = this.projectTopicBadgeCountDT();
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

    private DataTable projectTopicBadgeCountDT() {
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

    protected Boolean _requireLogin() {
        return false;
    }
}
