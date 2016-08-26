package beans;

import java.io.Serializable;
import java.text.DecimalFormat;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

/**
 *
 * @author Khepry Quixote <fracking.analysis@gmail.com>
 */
@ManagedBean
@RequestScoped
public class QueryResult implements Serializable {
    
    private Long duration = 0L;
    private Integer records = 0;
    private String message = "";
    private String sql = "";
    private boolean showSQL = false;
    
    private DecimalFormat dfN0 = new DecimalFormat("#,###,###,##0");
    private DecimalFormat dfN4 = new DecimalFormat("#,###,###,##0.0000");

    /**
     * Creates a new instance of QueryResult
     */
    public QueryResult() {
    }
    
    @Override
    public String toString() {
        String results = message;
        if (message.equals("")) {
            String rcdsPerSecond = duration > 0 ? dfN0.format(records * 1000.0 / duration) : "0";
            results = records + " records returned in " + dfN4.format(duration * 1.0 / 1000) + " seconds at " + rcdsPerSecond + " records/second." + (showSQL ? " SQL: " + sql : "");
        }
        return results;
    }

    /**
     * @return the duration
     */
    public Long getDuration() {
        return duration;
    }

    /**
     * @param duration the duration to set
     */
    public void setDuration(Long duration) {
        this.duration = duration;
    }

    /**
     * @return the records
     */
    public Integer getRecords() {
        return records;
    }

    /**
     * @param records the records to set
     */
    public void setRecords(Integer records) {
        this.records = records;
    }

    /**
     * @return the message
     */
    public String getMessage() {
        String results = message;
        if (message.equals("")) {
            String rcdsPerSecond = duration > 0 ? dfN0.format(records * 1000.0 / duration) : "0";
            results = records + " records returned in " + dfN4.format(duration * 1.0 / 1000) + " seconds at " + rcdsPerSecond + " records/second." + (showSQL ? " SQL: " + sql : "");
        }
        return results;
    }

    /**
     * @param message the message to set
     */
    public void setMessage(String message) {
        this.message = message;
    }

    /**
     * @return the sql
     */
    public String getSql() {
        return sql;
    }

    /**
     * @param sql the sql to set
     */
    public void setSql(String sql) {
        this.sql = sql;
    }

    /**
     * @return the showSQL
     */
    public boolean isShowSQL() {
        return showSQL;
    }

    /**
     * @param showSQL the showSQL to set
     */
    public void setShowSQL(boolean showSQL) {
        this.showSQL = showSQL;
    }
}
