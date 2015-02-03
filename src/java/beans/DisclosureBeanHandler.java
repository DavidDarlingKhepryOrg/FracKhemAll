/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author David Darling <david.darling@khepry.com>
 */
@ManagedBean
@ViewScoped
public class DisclosureBeanHandler implements Serializable {

    private List<DisclosureBean> disclosureBeans = new ArrayList<>();
    private boolean showSQL = false;
    private boolean initialized = false;
    private String jdbcSQLiteDriverName = "";
    private String jdbcMySQL5DriverName = "";
    private String jdbcSQLiteConnString = "";
    private String jdbcMySQL5ConnString = "";
    private String jdbcUid = "";
    private String jdbcPwd = "";
    private String jdbcDriverName = "";
    private String jdbcConnString = "";
    private QueryResult queryResult = new QueryResult();
    private Integer rowsPerPage = 15;
    private Integer rowsDefault = 100;
    private Integer avgChemicalsPerDisclosure = 35;
    private String lastKey = "";
    private String sqlLoadDisclosureBeans = "select * from reports where api > ? order by api limit 0, ?";
    private String resultsClass = "resultsMessageNormal";
    private String resultsMessageClassNormal = "resultsMessageNormal";
    private String resultsMessageClassError = "resultsMessageError";
    private AutoCompleteBean autoCompleteBean = new AutoCompleteBean();
    private SettingsSessionBean settingsSessionBean = new SettingsSessionBean();
    
    private String searchListRptTableName = "reports_toxicities_flattened";
    private String searchListChmTableName = "chemicals_toxicities_grouped";

    private ServletContext servletContext = (ServletContext)FacesContext.getCurrentInstance().getExternalContext().getContext();
    private HttpServletRequest httpServletRequest = (HttpServletRequest)FacesContext.getCurrentInstance().getExternalContext().getRequest();

    /**
     * Creates a new instance of DisclosureBeanHandler
     */
    public DisclosureBeanHandler() {
        initialize();
    }

    public void initialize() {
        rowsDefault = rowsPerPage * 20;
        disclosureBeans.clear();
        if (!settingsSessionBean.isInitialized()) {
            settingsSessionBean.initialize(httpServletRequest, servletContext);
            initialized = settingsSessionBean.isInitialized();
        }
        else {
            initialized = true;
        }
        if (initialized) {
            jdbcDriverName = settingsSessionBean.getJdbcDriverName();
            jdbcConnString = settingsSessionBean.getJdbcConnString();
            jdbcMySQL5ConnString = settingsSessionBean.getJdbcMySQL5ConnString();
            jdbcMySQL5DriverName = settingsSessionBean.getJdbcMySQL5DriverName();
            jdbcSQLiteConnString = settingsSessionBean.getJdbcSQLiteConnString();
            jdbcSQLiteDriverName = settingsSessionBean.getJdbcSQLiteDriverName();
            jdbcUid = settingsSessionBean.getJdbcUID();
            jdbcPwd = settingsSessionBean.getJdbcPWD();
        }
    }
    
    public void clearChosenValues() {
        autoCompleteBean.clearChosenValues();
        disclosureBeans.clear();
        queryResult.setMessage("All selection criteria and search results cleared as requested!");
    }

    public void doSearchList() {
        doSearchList(searchListRptTableName, searchListChmTableName, "", "", true);
    }

    private void doSearchList(String key, String value) {
        doSearchList(searchListRptTableName, searchListChmTableName, key, value, false);
    }

    private void doSearchList(String key, String value, boolean exact) {
        doSearchList(searchListRptTableName, searchListChmTableName, key, value, exact);
    }

    private void doSearchList(String rptTableName, String chmTableName, String key, String value) {
        doSearchList(rptTableName, chmTableName, key, value, false);
    }

    private void doSearchList(String rptTableName, String chmTableName, String key, String value, boolean exact) {
        queryResult = new QueryResult();
        queryResult.setShowSQL(showSQL);
        Long bgnMillis = new Date().getTime();
        disclosureBeans.clear();
        Connection jdbcConnection = null;
        PreparedStatement preparedStatement = null;
        ResultSet rs = null;
        String sql = "";
        try {
            DisclosureBean old_value = new DisclosureBean();
            Class.forName(jdbcDriverName);
            jdbcConnection = !jdbcDriverName.equals(jdbcSQLiteDriverName) ? DriverManager.getConnection(jdbcConnString, jdbcUid, jdbcPwd) : DriverManager.getConnection(jdbcConnString);
            String rptSQLFields = "r.rpt_seqid,r.rpt_pdf_seqid,r.rpt_api,r.rpt_fracture_date,r.rpt_state,r.rpt_county,r.rpt_operator,r.rpt_well_name,r.rpt_production_type,r.rpt_latitude,r.rpt_longitude,r.rpt_datum,r.rpt_true_vertical_depth,r.rpt_total_water_volume,r.rpt_published,r.rpt_geo_lat_lng";
            String chmSQLFields = "c.chm_seqid,c.chm_pdf_seqid,c.chm_row,c.chm_supplier,c.chm_trade_name,c.chm_purpose,c.chm_ingredients,c.chm_cas_number,c.chm_additive_concentration,c.chm_hf_fluid_concentration,c.chm_cas_type,c.chm_comments,c.rpt_tox_recognized,c.rpt_tox_suspected";
            sql = autoCompleteBean.getAnySQLSearchString("SELECT " + rptSQLFields + "," + chmSQLFields + " FROM " + rptTableName + " r INNER JOIN " + chmTableName + " c ON r.rpt_pdf_seqid = c.chm_pdf_seqid", "x", " AND ", autoCompleteBean.getNotApplicableString(), key, value, exact, false, autoCompleteBean.getKeyAPI(), rowsDefault * 30);
            preparedStatement = jdbcConnection.prepareStatement(sql);
            //preparedStatement.setInt(1, rowsDefault);
            rs = preparedStatement.executeQuery();
            while (rs.next()) {
                ChemicalBean chemicalBean = new ChemicalBean();
                chemicalBean.setAdditiveConcentration(getStringValue(rs.getString(autoCompleteBean.getKeyAdditiveConcentration()), ""));
                chemicalBean.setApi(getStringValue(rs.getString(autoCompleteBean.getKeyAPI()), ""));
                chemicalBean.setCasNumber(getStringValue(rs.getString(autoCompleteBean.getKeyCasNumber()), ""));
                chemicalBean.setCasType(getStringValue(rs.getString(autoCompleteBean.getKeyCasType()), ""));
                chemicalBean.setComments(getStringValue(rs.getString(autoCompleteBean.getKeyComments()), ""));
                chemicalBean.setFractureDate(getStringValue(rs.getString(autoCompleteBean.getKeyFractureDate()), ""));
                chemicalBean.setHfFluidConcentration(getStringValue(rs.getString(autoCompleteBean.getKeyHfFluidConcentration()), ""));
                chemicalBean.setIngredients(getStringValue(rs.getString(autoCompleteBean.getKeyIngredients()), ""));
                chemicalBean.setPdfSeqId(getStringValue(rs.getString(autoCompleteBean.getKeyPdfSeqId()), ""));
                chemicalBean.setPurpose(getStringValue(rs.getString(autoCompleteBean.getKeyPurpose()), ""));
                chemicalBean.setRow(getStringValue(rs.getString(autoCompleteBean.getKeyRow()), ""));
                chemicalBean.setSupplier(getStringValue(rs.getString(autoCompleteBean.getKeySupplier()), ""));
                chemicalBean.setToxRecognized(getStringValue(rs.getString(autoCompleteBean.getKeyChmToxicityRecognized()), ""));
                chemicalBean.setToxSuspected(getStringValue(rs.getString(autoCompleteBean.getKeyChmToxicitySuspected()), ""));
                chemicalBean.setTradeName(getStringValue(rs.getString(autoCompleteBean.getKeyTradeName()), ""));
                if (!old_value.getPdfSeqId().equals(chemicalBean.getPdfSeqId())) {
                    if (!old_value.getPdfSeqId().equals("")) {
                        disclosureBeans.add(old_value);
                    }
                    old_value = getDisclosureBean(rs);
                }
                old_value.addChemical(chemicalBean);
            }
            if (!old_value.getPdfSeqId().equals("")) {
                disclosureBeans.add(old_value);
            }
            queryResult.setRecords(disclosureBeans.size());
            queryResult.setSql(sql);
            resultsClass = resultsMessageClassNormal;
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(DisclosureBeanHandler.class.getName()).log(Level.SEVERE, null, ex);
            queryResult.setMessage(ex.getLocalizedMessage() + ", SQL: " + sql);
            resultsClass = resultsMessageClassError;
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
                if (jdbcConnection != null) {
                    jdbcConnection.close();
                }
            } catch (SQLException ex) {
                Logger.getLogger(DisclosureBeanHandler.class.getName()).log(Level.SEVERE, null, ex);
                queryResult.setMessage(ex.getLocalizedMessage());
                resultsClass = resultsMessageClassError;
            }
        }
        Long endMillis = new Date().getTime();
        Long duration = endMillis - bgnMillis;
        queryResult.setDuration(duration);
    }

    private DisclosureBean getDisclosureBean(ResultSet rs) throws SQLException {
        DisclosureBean disclosureBean = new DisclosureBean();
        disclosureBean.setApi(getStringValue(rs.getString(autoCompleteBean.getKeyAPI()), ""));
        disclosureBean.setCountry("USA");
        disclosureBean.setCounty(getStringValue(rs.getString(autoCompleteBean.getKeyCounty()), ""));
        disclosureBean.setDatum(getStringValue(rs.getString(autoCompleteBean.getKeyDatum()), ""));
        disclosureBean.setFractureDate(getStringValue(rs.getString(autoCompleteBean.getKeyFractureDate()), ""));
        disclosureBean.setLatitude(getDoubleValue(rs.getString(autoCompleteBean.getKeyLatitude()), null));
        disclosureBean.setLongitude(getDoubleValue(rs.getString(autoCompleteBean.getKeyLongitude()), null));
        disclosureBean.setOperator(getStringValue(rs.getString(autoCompleteBean.getKeyOperator()), ""));
        disclosureBean.setPdfSeqId(getStringValue(rs.getString(autoCompleteBean.getKeyPdfSeqId()), ""));
        disclosureBean.setProdType(getStringValue(rs.getString(autoCompleteBean.getKeyProdType()), ""));
        disclosureBean.setPublishedDate(getStringValue(rs.getString(autoCompleteBean.getKeyPublishedDate()), ""));
        disclosureBean.setState(getStringValue(rs.getString(autoCompleteBean.getKeyState()), ""));
        disclosureBean.setTotalWaterVolume(getStringValue(rs.getString(autoCompleteBean.getKeyTotalWaterVolume()), ""));
        disclosureBean.setTrueVerticalDepth(getStringValue(rs.getString(autoCompleteBean.getKeyTrueVerticalDepth()), ""));
        disclosureBean.setWellName(getStringValue(rs.getString(autoCompleteBean.getKeyWellName()), ""));
        lastKey = disclosureBean.getApi();
        return disclosureBean;
    }

    public void doSearchByAPI(ActionEvent e) {
        String value = e.getComponent().getAttributes().get("value").toString().trim();
        doSearchList(autoCompleteBean.getKeyAPI(), value, true);
    }

    public void doSearchByState(ActionEvent e) {
        String value = e.getComponent().getAttributes().get("value").toString().trim();
        doSearchList(autoCompleteBean.getKeyState(), value, true);
    }

    public void doSearchByCounty(ActionEvent e) {
        String value = e.getComponent().getAttributes().get("value").toString().trim();
        doSearchList(autoCompleteBean.getKeyCounty(), value, true);
    }

    public void doSearchByOperator(ActionEvent e) {
        String value = e.getComponent().getAttributes().get("value").toString().trim();
        doSearchList(autoCompleteBean.getKeyOperator(), value, true);
    }

    public void doSearchByWellName(ActionEvent e) {
        String value = e.getComponent().getAttributes().get("value").toString().trim();
        doSearchList(autoCompleteBean.getKeyWellName(), value, true);
    }

    public void doSearchByProdType(ActionEvent e) {
        String value = e.getComponent().getAttributes().get("value").toString().trim();
        doSearchList(autoCompleteBean.getKeyProdType(), value, true);
    }

    public void doSearchByFractureDate(ActionEvent e) {
        String value = e.getComponent().getAttributes().get("value").toString().trim();
        doSearchList(autoCompleteBean.getKeyFractureDate(), value, true);
    }

    public void doSearchByPdfSeqId(ActionEvent e) {
        String value = e.getComponent().getAttributes().get("value").toString().trim();
        doSearchList(autoCompleteBean.getKeyPdfSeqId(), value, true);
    }

    public void doSearchByPublishedDate(ActionEvent e) {
        String value = e.getComponent().getAttributes().get("value").toString().trim();
        doSearchList(autoCompleteBean.getKeyPublishedDate(), value, true);
    }

    public void doSearchByRptToxRecognized(ActionEvent e) {
        String value = e.getComponent().getAttributes().get("value").toString().trim();
        doSearchList(autoCompleteBean.getKeyRptToxicityRecognized(), value, true);
    }

    public void doSearchByRptToxSuspected(ActionEvent e) {
        String value = e.getComponent().getAttributes().get("value").toString().trim();
        doSearchList(autoCompleteBean.getKeyRptToxicitySuspected(), value, true);
    }

    private String getStringValue(String value, String nullReplacementValue) {
        return value != null ? value : nullReplacementValue;
    }

    private Date getDateValue(String value, Date nullReplacementValue) {
        Date result = nullReplacementValue;
        if (value != null) {
            try {
                result = new Date(value);
            } catch (IllegalArgumentException ex) {
            }
        }
        return result;
    }

    private Double getDoubleValue(String value, Double nullReplacementValue) {
        Double result = nullReplacementValue;
        if (value != null) {
            try {
                result = Double.parseDouble(value);
            } catch (NumberFormatException ex) {
            }
        }
        return result;
    }

    private Integer getIntegerValue(String value, Integer nullReplacementValue) {
        Integer result = nullReplacementValue;
        if (value != null) {
            try {
                result = Integer.parseInt(value);
            } catch (NumberFormatException ex) {
            }
        }
        return result;
    }

    /**
     * @return the disclosureBeans
     */
    public List<DisclosureBean> getDisclosureBeans() {
        return disclosureBeans;
    }

    /**
     * @param disclosureBeans the disclosureBeans to set
     */
    public void setDisclosureBeans(List<DisclosureBean> disclosureBeans) {
        this.disclosureBeans = disclosureBeans;
    }

    /**
     * @return the queryResult
     */
    public QueryResult getQueryResult() {
        return queryResult;
    }

    /**
     * @param queryResult the queryResult to set
     */
    public void setQueryResult(QueryResult queryResult) {
        this.queryResult = queryResult;
    }

    /**
     * @return the rowsPerPage
     */
    public Integer getRowsPerPage() {
        return rowsPerPage;
    }

    /**
     * @param rowsPerPage the rowsPerPage to set
     */
    public void setRowsPerPage(Integer rowsPerPage) {
        this.rowsPerPage = rowsPerPage;
    }

    /**
     * @return the rowsDefault
     */
    public Integer getRowsDefault() {
        return rowsDefault;
    }

    /**
     * @param rowsDefault the rowsDefault to set
     */
    public void setRowsDefault(Integer rowsDefault) {
        this.rowsDefault = rowsDefault;
    }

    /**
     * @return the lastKey
     */
    public String getLastKey() {
        return lastKey;
    }

    /**
     * @param lastKey the lastKey to set
     */
    public void setLastKey(String lastKey) {
        this.lastKey = lastKey;
    }

    /**
     * @return the resultsClass
     */
    public String getResultsClass() {
        return resultsClass;
    }

    /**
     * @param resultsClass the resultsClass to set
     */
    public void setResultsClass(String resultsClass) {
        this.resultsClass = resultsClass;
    }

    /**
     * @return the autoCompleteBean
     */
    public AutoCompleteBean getAutoCompleteBean() {
        return autoCompleteBean;
    }

    /**
     * @param autoCompleteBean the autoCompleteBean to set
     */
    public void setAutoCompleteBean(AutoCompleteBean autoCompleteBean) {
        this.autoCompleteBean = autoCompleteBean;
    }

    /**
     * @return the clientIpAddress
     */
    public String getClientIpAddress() {
        if (!initialized) {
            initialize();
        }
        return settingsSessionBean.getClientIpAddress();
    }

    /**
     * @return the hostIpAddress
     */
    public String getHostIpAddress() {
        if (!initialized) {
            initialize();
        }
        return settingsSessionBean.getHostIpAddress();
    }

    /**
     * @return the clientUserName
     */
    public String getClientUserName() {
        if (!initialized) {
            initialize();
        }
        return settingsSessionBean.getClientUserName();
    }

    /**
     * @return the settingsSessionBean
     */
    public SettingsSessionBean getSettingsSessionBean() {
        return settingsSessionBean;
    }

    /**
     * @param settingsSessionBean the settingsSessionBean to set
     */
    public void setSettingsSessionBean(SettingsSessionBean settingsSessionBean) {
        this.settingsSessionBean = settingsSessionBean;
    }
}
