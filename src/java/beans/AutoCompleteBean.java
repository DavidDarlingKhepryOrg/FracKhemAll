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
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import org.primefaces.event.SelectEvent;

/**
 *
 * @author David Darling <david.darling@khepry.com>
 */
@ManagedBean
@ViewScoped
public class AutoCompleteBean implements Serializable {

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
    
    private String notApplicableString = "*not applicable*";
    
    private String keyAPI = "rpt_api";
    private String keyAdditiveConcentration = "chm_additive_concentration";
    private String keyCasNumber = "chm_cas_number";
    private String keyCasType = "chm_cas_type";
    private String keyChmToxRecognized = "chm_tox_recognized";
    private String keyChmToxSuspected = "chm_tox_suspected";
    private String keyComments = "chm_comments";
    private String keyCountry = "rpt_country";
    private String keyCounty = "rpt_county";
    private String keyDatum = "rpt_datum";
    private String keyFractureDate = "rpt_fracture_date";
    private String keyHfFluidConcentration = "chm_hf_fluid_concentration";
    private String keyIngredients = "chm_ingredients";
    private String keyLatitude = "rpt_latitude";
    private String keyLongitude = "rpt_longitude";
    private String keyOperator = "rpt_operator";
    private String keyPdfSeqId = "rpt_pdf_seqid";
    private String keyProdType = "rpt_production_type";
    private String keyPurpose = "chm_purpose";
    private String keyTotalWaterVolume = "rpt_total_water_volume";
    private String keyTrueVerticalDepth = "rpt_true_vertical_depth";
    private String keyTradeName = "chm_trade_name";
    private String keyState = "rpt_state";
    private String keySupplier = "chm_supplier";
    private String keyWellName = "rpt_well_name";
    private String keyRow = "chm_row";
    private String keyPublishedDate = "rpt_published";

    private String keyChmToxicityRecognized = "rpt_tox_recognized";
    private String keyChmToxicitySuspected = "rpt_tox_suspected";
    private String keyChmToxicityRecognizedCancer = "tox_recognized_cancer";
    private String keyChmToxicitySuspectedCancer = "tox_suspected_cancer";
    private String keyChmToxicityRecognizedCardioBlood = "tox_recognized_cardio_blood";
    private String keyChmToxicitySuspectedCardioBlood = "tox_suspected_cardio_blood";
    private String keyChmToxicityRecognizedDevelopmental = "tox_recognized_developmental";
    private String keyChmToxicitySuspectedDevelopmental = "tox_suspected_developmental";
    private String keyChmToxicityRecognizedEndocrine = "tox_recognized_endocrine";
    private String keyChmToxicitySuspectedEndocrine = "tox_suspected_endocrine";
    private String keyChmToxicityRecognizedGastroLiver = "tox_recognized_gastro_liver";
    private String keyChmToxicitySuspectedGastroLiver = "tox_suspected_gastro_liver";
    private String keyChmToxicityRecognizedImmunotoxicity = "tox_recognized_immunotoxicity";
    private String keyChmToxicitySuspectedImmunotoxicity = "tox_suspected_immunotoxicity";
    private String keyChmToxicityRecognizedKidney = "tox_recognized_kidney";
    private String keyChmToxicitySuspectedKidney = "tox_suspected_kidney";
    private String keyChmToxicityRecognizedMusculoskeletal = "tox_recognized_musculoskeletal";
    private String keyChmToxicitySuspectedMusculoskeletal = "tox_suspected_musculoskeletal";
    private String keyChmToxicityRecognizedNeurotoxicity = "tox_recognized_neurotoxicity";
    private String keyChmToxicitySuspectedNeurotoxicity = "tox_suspected_neurotoxicity";
    private String keyChmToxicityRecognizedReproductive = "tox_recognized_reproductive";
    private String keyChmToxicitySuspectedReproductive = "tox_suspected_reproductive";
    private String keyChmToxicityRecognizedRespiratory = "tox_recognized_respiratory";
    private String keyChmToxicitySuspectedRespiratory = "tox_suspected_respiratory";
    private String keyChmToxicityRecognizedSkinSense = "tox_recognized_skin_sense";
    private String keyChmToxicitySuspectedSkinSense = "tox_suspected_skin_sense";

    private String keyRptToxicityRecognized = "rpt_tox_recognized";
    private String keyRptToxicitySuspected = "rpt_tox_suspected";
    private String keyRptToxicityRecognizedCancer = "tox_recognized_cancer";
    private String keyRptToxicitySuspectedCancer = "tox_suspected_cancer";
    private String keyRptToxicityRecognizedCardioBlood = "tox_recognized_cardio_blood";
    private String keyRptToxicitySuspectedCardioBlood = "tox_suspected_cardio_blood";
    private String keyRptToxicityRecognizedDevelopmental = "tox_recognized_developmental";
    private String keyRptToxicitySuspectedDevelopmental = "tox_suspected_developmental";
    private String keyRptToxicityRecognizedEndocrine = "tox_recognized_endocrine";
    private String keyRptToxicitySuspectedEndocrine = "tox_suspected_endocrine";
    private String keyRptToxicityRecognizedGastroLiver = "tox_recognized_gastro_liver";
    private String keyRptToxicitySuspectedGastroLiver = "tox_suspected_gastro_liver";
    private String keyRptToxicityRecognizedImmunotoxicity = "tox_recognized_immunotoxicity";
    private String keyRptToxicitySuspectedImmunotoxicity = "tox_suspected_immunotoxicity";
    private String keyRptToxicityRecognizedKidney = "tox_recognized_kidney";
    private String keyRptToxicitySuspectedKidney = "tox_suspected_kidney";
    private String keyRptToxicityRecognizedMusculoskeletal = "tox_recognized_musculoskeletal";
    private String keyRptToxicitySuspectedMusculoskeletal = "tox_suspected_musculoskeletal";
    private String keyRptToxicityRecognizedNeurotoxicity = "tox_recognized_neurotoxicity";
    private String keyRptToxicitySuspectedNeurotoxicity = "tox_suspected_neurotoxicity";
    private String keyRptToxicityRecognizedReproductive = "tox_recognized_reproductive";
    private String keyRptToxicitySuspectedReproductive = "tox_suspected_reproductive";
    private String keyRptToxicityRecognizedRespiratory = "tox_recognized_respiratory";
    private String keyRptToxicitySuspectedRespiratory = "tox_suspected_respiratory";
    private String keyRptToxicityRecognizedSkinSense = "tox_recognized_skin_sense";
    private String keyRptToxicitySuspectedSkinSense = "tox_suspected_skin_sense";
    
    private String searchCriteria = "";
    private Map<String, String> chosenValues = new TreeMap<>();
    private List<String> chemicalFields = new ArrayList<>();
    private List<String> reportFields = new ArrayList<>();
    private int maxResults = 25;
    private int maxRows = 500;
    private String dateOnlyFormat = "yyyy-MM-dd";
    private String dateTimeFormat = "yyyy-MM-dd HH:mm:ss";
    private SimpleDateFormat simpleDateOnlyFormat = new SimpleDateFormat(dateOnlyFormat);
    private SimpleDateFormat simpleDateTimeFormat = new SimpleDateFormat(dateTimeFormat);
    private List<String> validStates = new ArrayList<>();
    private String resultsClass = "resultsMessageNormal";
    private String resultsMessageClassNormal = "resultsMessageNormal";
    private String resultsMessageClassError = "resultsMessageError";
    
    private Map<String, String> themes;
    private String theme;

    private SettingsSessionBean settingsSessionBean = new SettingsSessionBean();
    private ServletContext servletContext = (ServletContext)FacesContext.getCurrentInstance().getExternalContext().getContext();
    private HttpServletRequest httpServletRequest = (HttpServletRequest)FacesContext.getCurrentInstance().getExternalContext().getRequest();

    /**
     * Creates a new instance of AutoCompleteBean
     */
    public AutoCompleteBean() {
        initialize();
    }

    public final void initialize() {
        
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
        
        themes = new TreeMap<String, String>();
        themes.put("Aristo", "aristo");
        themes.put("Black-Tie", "black-tie");
        themes.put("Blitzer", "blitzer");
        themes.put("Bluesky", "bluesky");
        themes.put("Casablanca", "casablanca");
        themes.put("Cupertino", "cupertino");
        themes.put("Dark-Hive", "dark-hive");
        themes.put("Dot-Luv", "dot-luv");
        themes.put("Eggplant", "eggplant");
        themes.put("Excite-Bike", "excite-bike");
        themes.put("Flick", "flick");
        themes.put("Glass-X", "glass-x");
        themes.put("Hot-Sneaks", "hot-sneaks");
        themes.put("Humanity", "humanity");
        themes.put("Le-Frog", "le-frog");
        themes.put("Midnight", "midnight");
        themes.put("Mint-Choc", "mint-choc");
        themes.put("Overcast", "overcast");
        themes.put("Pepper-Grinder", "pepper-grinder");
        themes.put("Redmond", "redmond");
        themes.put("Rocket", "rocket");
        themes.put("Sam", "sam");
        themes.put("Smoothness", "smoothness");
        themes.put("South-Street", "south-street");
        themes.put("Start", "start");
        themes.put("Sunny", "sunny");
        themes.put("Swanky-Purse", "swanky-purse");
        themes.put("Trontastic", "trontastic");
        themes.put("UI-Darkness", "ui-darkness");
        themes.put("UI-Lightness", "ui-lightness");
        themes.put("Vader", "vader");

        chemicalFields.add(keyCasNumber);
        chemicalFields.add(keyIngredients);
        chemicalFields.add(keyPdfSeqId);
        chemicalFields.add(keyPurpose);
        chemicalFields.add(keyTradeName);
        chemicalFields.add(keySupplier);
        chemicalFields.add(keyRow);
        chemicalFields.add(keyChmToxicityRecognized);
        chemicalFields.add(keyChmToxicitySuspected);
        chemicalFields.add(keyChmToxicityRecognizedCancer);
        chemicalFields.add(keyChmToxicitySuspectedCancer);
        chemicalFields.add(keyChmToxicityRecognizedCardioBlood);
        chemicalFields.add(keyChmToxicitySuspectedCardioBlood);
        chemicalFields.add(keyChmToxicityRecognizedDevelopmental);
        chemicalFields.add(keyChmToxicitySuspectedDevelopmental);
        chemicalFields.add(keyChmToxicityRecognizedEndocrine);
        chemicalFields.add(keyChmToxicitySuspectedEndocrine);
        chemicalFields.add(keyChmToxicityRecognizedGastroLiver);
        chemicalFields.add(keyChmToxicitySuspectedGastroLiver);
        chemicalFields.add(keyChmToxicityRecognizedImmunotoxicity);
        chemicalFields.add(keyChmToxicitySuspectedImmunotoxicity);
        chemicalFields.add(keyChmToxicityRecognizedKidney);
        chemicalFields.add(keyChmToxicitySuspectedKidney);
        chemicalFields.add(keyChmToxicityRecognizedMusculoskeletal);
        chemicalFields.add(keyChmToxicitySuspectedMusculoskeletal);
        chemicalFields.add(keyChmToxicityRecognizedNeurotoxicity);
        chemicalFields.add(keyChmToxicitySuspectedNeurotoxicity);
        chemicalFields.add(keyChmToxicityRecognizedReproductive);
        chemicalFields.add(keyChmToxicitySuspectedReproductive);
        chemicalFields.add(keyChmToxicityRecognizedRespiratory);
        chemicalFields.add(keyChmToxicitySuspectedRespiratory);
        chemicalFields.add(keyChmToxicityRecognizedSkinSense);
        chemicalFields.add(keyChmToxicitySuspectedSkinSense);

        reportFields.add(keyAPI);
        reportFields.add(keyCountry);
        reportFields.add(keyCounty);
        reportFields.add(keyFractureDate);
        reportFields.add(keyOperator);
        reportFields.add(keyPdfSeqId);
        reportFields.add(keyProdType);
        reportFields.add(keyState);
        reportFields.add(keyWellName);
        reportFields.add(keyRptToxicityRecognized);
        reportFields.add(keyRptToxicitySuspected);
        reportFields.add(keyRptToxicityRecognizedCancer);
        reportFields.add(keyRptToxicitySuspectedCancer);
        reportFields.add(keyRptToxicityRecognizedCardioBlood);
        reportFields.add(keyRptToxicitySuspectedCardioBlood);
        reportFields.add(keyRptToxicityRecognizedDevelopmental);
        reportFields.add(keyRptToxicitySuspectedDevelopmental);
        reportFields.add(keyRptToxicityRecognizedEndocrine);
        reportFields.add(keyRptToxicitySuspectedEndocrine);
        reportFields.add(keyRptToxicityRecognizedGastroLiver);
        reportFields.add(keyRptToxicitySuspectedGastroLiver);
        reportFields.add(keyRptToxicityRecognizedImmunotoxicity);
        reportFields.add(keyRptToxicitySuspectedImmunotoxicity);
        reportFields.add(keyRptToxicityRecognizedKidney);
        reportFields.add(keyRptToxicitySuspectedKidney);
        reportFields.add(keyRptToxicityRecognizedMusculoskeletal);
        reportFields.add(keyRptToxicitySuspectedMusculoskeletal);
        reportFields.add(keyRptToxicityRecognizedNeurotoxicity);
        reportFields.add(keyRptToxicitySuspectedNeurotoxicity);
        reportFields.add(keyRptToxicityRecognizedReproductive);
        reportFields.add(keyRptToxicitySuspectedReproductive);
        reportFields.add(keyRptToxicityRecognizedRespiratory);
        reportFields.add(keyRptToxicitySuspectedRespiratory);
        reportFields.add(keyRptToxicityRecognizedSkinSense);
        reportFields.add(keyRptToxicitySuspectedSkinSense);

        queryResult = new QueryResult();

        initialized = true;

        validStates = chooseViaReportsJDBC(keyState, "");

        chosenValues.clear();
    }

    public void clearChosenValues() {
        for (String key : chosenValues.keySet()) {
            chosenValues.put(key, "");
        }
        //searchCriteria = getSearchCriteria();
    }

    public String getSearchCriteria() {
        return getSearchCriteria("", "");
    }

    public String getSearchCriteria(
            String fieldName,
            String queryText) {
        StringBuilder sb = new StringBuilder();
        boolean toxFieldEncountered = false;
        if (queryText.trim().equals(notApplicableString)) {
            queryText = "";
        }
        for (Map.Entry<String, String> entry : chosenValues.entrySet()) {
            String value = entry.getValue().trim().equals(notApplicableString) ? "" : entry.getValue().trim();
            if (!value.equals("") || entry.getKey().equals(fieldName)) {
                //sb.append("+");
                if (!toxFieldEncountered && entry.getKey().toLowerCase().trim().startsWith("tox_")) {
                    sb.append("(");
                    toxFieldEncountered = true;
                }
                sb.append(entry.getKey());
                sb.append(":");
                if (entry.getKey().equals(fieldName)) {
                    sb.append(queryText);
                    sb.append("*");
                } else {
                    sb.append("\"");
                    sb.append(entry.getValue());
                    sb.append("\"");
                }
                sb.append(entry.getKey().toLowerCase().trim().startsWith("tox_") ? " OR " : " AND ");
            }
        }
        if (sb.length() >= 4) {
            sb.setLength(sb.length() - 4);
        }
        if (toxFieldEncountered) {
            sb.append(")");
        }
        return sb.toString().trim();
    }

    public String getSearchCriteriaExact(
            String fieldName,
            String queryText) {
        StringBuilder sb = new StringBuilder();
        if (queryText.trim().equals(notApplicableString)) {
            queryText = "";
        }
        if (!queryText.trim().equals("") && !fieldName.equals("")) {
            //sb.append("+");
            sb.append(fieldName.trim());
            sb.append(":");
            sb.append("\"");
            sb.append(queryText.trim());
            sb.append("\"");
        }
        return sb.toString().trim();
    }
    
    public String getAnySQLWhereClause(boolean exact, String fixedValue, String fieldAbbrev, String logicalOperand, String notApplicableString, String[] prefixes, String... notPrefixes) {
        StringBuilder sb = new StringBuilder();
        boolean paranthesesWritten = false;
        for (String chosenKey : chosenValues.keySet()) {
            boolean matchFound = false;
            // does the key prefix match a DESIRED prefix?
            for (String prefix : prefixes) {
                if (chosenKey.startsWith(prefix)) {
                    matchFound = true;
                    break;
                }
            }
            // does the key prefix match an UNDESIRED prefix?
            for (String notPrefix : notPrefixes) {
                if (chosenKey.startsWith(notPrefix)) {
                    matchFound = false;
                    break;
                }
            }
            if (matchFound) {
                String value = chosenValues.get(chosenKey);
                value = value.equals(notApplicableString) ? "" : value;
                if (!value.equals("") && !value.equals(notApplicableString)) {
                    if (!paranthesesWritten) {
                        sb.append("(");
                        paranthesesWritten = true;
                    }
                    sb.append(fieldAbbrev.equals("") ? chosenKey : fieldAbbrev + "." + chosenKey);
                    sb.append(exact ? " = " : " LIKE ");
                    sb.append(fixedValue == null ? "'" : "");
                    sb.append(fixedValue == null ? value : fixedValue);
                    sb.append(fixedValue == null ? (exact ? "'" : "%'") : "");
                    sb.append(logicalOperand);
                }
            }
        }
        // trim away any trailing logical operand
        if (sb.length() >= logicalOperand.length()) {
            sb.setLength(sb.length() - logicalOperand.length());
            sb.append(")");
        }
        return sb.toString();
    }
    
    public String getAnySQLSearchString(String sqlPreface, String fieldAbbrev, String logicalOperand, String notApplicableString, String key, String value, boolean exact, boolean groupBy, String orderBy, int maxResults) {
        StringBuilder sb = new StringBuilder();
        StringBuilder sbWhereClauses = new StringBuilder();
        String sqlRptWhereClause = "";
        String sqlChmWhereClause = "";
        String sqlToxWhereClause = "";
        if (fieldAbbrev.equals("")) {
            sqlRptWhereClause = getAnySQLWhereClause(true, null, fieldAbbrev, " AND ", notApplicableString, new String[] {"rpt_"}, "rpt_tox_", "tox_");
            sqlChmWhereClause = getAnySQLWhereClause(true, null, fieldAbbrev, " AND ", notApplicableString, new String[] {"chm_"}, "chm_tox_", "tox_");
            sqlToxWhereClause = getAnySQLWhereClause(true, "1", fieldAbbrev, " OR ", notApplicableString, new String[] {"rpt_tox", "chm_tox", "tox_"});
        }
        else {
            sqlRptWhereClause = getAnySQLWhereClause(true, null, "r", " AND ", notApplicableString, new String[] {"rpt_"}, "rpt_tox_", "tox_");
            sqlChmWhereClause = getAnySQLWhereClause(true, null, "c", " AND ", notApplicableString, new String[] {"chm_"}, "chm_tox_", "tox_");
            sqlToxWhereClause = getAnySQLWhereClause(true, "1", "r", " OR ", notApplicableString, new String[] {"rpt_tox", "chm_tox", "tox_"});
        }
        sb.append(sqlPreface);
        sb.append(" ");
        sbWhereClauses.append(!sqlRptWhereClause.equals("")? sqlRptWhereClause + logicalOperand : "");
        sbWhereClauses.append(!sqlChmWhereClause.equals("")? sqlChmWhereClause + logicalOperand : "");
        sbWhereClauses.append(!sqlToxWhereClause.equals("")? sqlToxWhereClause + logicalOperand : "");
        if (key.equals("")) {
            if (sbWhereClauses.length() >= logicalOperand.length()) {
                sbWhereClauses.setLength(sbWhereClauses.length() - logicalOperand.length());
                sb.append(" WHERE ");
                sb.append(sbWhereClauses);
            }
        }
        else {
            sb.append(" WHERE ");
            if (sbWhereClauses.length() >= logicalOperand.length()) {
                sb.append(sbWhereClauses);
            }
            sb.append(exact ? key + " = '" + value + "'" : key + " LIKE '" + value + "%'");
            if (groupBy) {
                sb.append(" GROUP BY ");
                sb.append(key);
            }
        }
        sb.append(orderBy.equals("") ? "" : " ORDER BY " + orderBy);
        sb.append(maxResults > 0 ? " LIMIT 0," + maxResults : "");
        return sb.toString();
    }

    private List<String> chooseViaChemicalsJDBC(String key, String value) {
        return chooseViaJDBC("chemicals_toxicities_flattened", key, value);
    }

    private List<String> chooseViaReportsJDBC(String key, String value) {
        return chooseViaJDBC("chemicals_toxicities_flattened", key, value);
    }

    private List<String> chooseViaToxicitiesJDBC(String key, String value) {
        return chooseViaJDBC("chemicals_toxicities_flattened", key, value);
    }
    
    //validStates = chooseViaJDBC("state","*");
    private List<String> chooseViaJDBC(String tableName, String key, String value) {
        List<String> results = new ArrayList<>();
        if (!initialized) {
            initialize();
        }
        chosenValues.put(key, value);
        if (chosenValues.size() == 1) {
            if (chosenValues.containsKey(keyState) && value.equals("")) {
                results = validStates;
            }
        }
        if (results.isEmpty()) {
            Connection jdbcConnection = null;
            PreparedStatement preparedStatement = null;
            ResultSet rs = null;
            String sql = "";
            try {
                Class.forName(jdbcDriverName);
                jdbcConnection = !jdbcDriverName.equals(jdbcSQLiteDriverName) ? DriverManager.getConnection(jdbcConnString, jdbcUid, jdbcPwd) : DriverManager.getConnection(jdbcConnString);
                // sql = "SELECT " + key + " FROM " + tableName + rptSQLWhereClause + chmSQLWhereClause.replace("WHERE","AND") + " GROUP BY " + key + " LIMIT 0,?";
                sql = getAnySQLSearchString("SELECT " + key + " FROM " + tableName, "", " AND ", notApplicableString, key, value, false, true, key, maxResults);
                preparedStatement = jdbcConnection.prepareStatement(sql);
                //preparedStatement.setInt(1, maxResults);
                rs = preparedStatement.executeQuery();
                results.add(notApplicableString);
                while (rs.next()) {
                    results.add(getStringValue(rs.getString(key), ""));
                }
                queryResult.setRecords(results.size());
                resultsClass = resultsMessageClassNormal;
            } catch (ClassNotFoundException | SQLException ex) {
                Logger.getLogger(DisclosureBeanHandler.class
                        .getName()).log(Level.SEVERE, sql, ex);
                resultsClass = resultsMessageClassError;
                queryResult.setMessage(ex.getLocalizedMessage());
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
                    Logger.getLogger(DisclosureBeanHandler.class
                            .getName()).log(Level.SEVERE, null, ex);
                    resultsClass = resultsMessageClassError;

                    queryResult.setMessage(ex.getLocalizedMessage());
                }
            }
        }
        return results;
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

    private String concatenateList(List<String> list, String delimiter) {
        StringBuilder sb = new StringBuilder();
        for (String value : list) {
            sb.append(value);
            sb.append(",");
        }
        if (sb.length() > 0) {
            sb.setLength(sb.length() - delimiter.length());
        }
        return sb.toString();
    }

    private String concatenateSet(Set<String> set, String delimiter) {
        StringBuilder sb = new StringBuilder();
        for (String value : set) {
            sb.append(value);
            sb.append(",");
        }
        if (sb.length() > 0) {
            sb.setLength(sb.length() - delimiter.length());
        }
        return sb.toString();
    }

    public List<String> chooseState(String query) {
        return chooseViaReportsJDBC(keyState, query);
    }

    public List<String> chooseCountry(String query) {
        return chooseViaReportsJDBC(keyCountry, query);
    }

    public List<String> chooseCounty(String query) {
        return chooseViaReportsJDBC(keyCounty, query);
    }

    public List<String> chooseAPI(String query) {
        return chooseViaReportsJDBC(keyAPI, query);
    }

    public List<String> chooseOperator(String query) {
        return chooseViaReportsJDBC(keyOperator, query);
    }

    public List<String> chooseWellName(String query) {
        return chooseViaReportsJDBC(keyWellName, query);
    }

    public List<String> chooseProdType(String query) {
        return chooseViaReportsJDBC(keyProdType, query);
    }

    public List<String> chooseFractureDate(String query) {
        return chooseViaReportsJDBC(keyFractureDate, query);
    }

    public List<String> choosePdfSeqId(String query) {
        return chooseViaReportsJDBC(keyPdfSeqId, query);
    }

    public List<String> chooseCasNumber(String query) {
        return chooseViaChemicalsJDBC(keyCasNumber, query);
    }

    public List<String> chooseIngredients(String query) {
        return chooseViaChemicalsJDBC(keyIngredients, query);
    }

    public List<String> chooseTradeName(String query) {
        return chooseViaChemicalsJDBC(keyTradeName, query);
    }

    public List<String> choosePurpose(String query) {
        return chooseViaChemicalsJDBC(keyPurpose, query);
    }

    public List<String> chooseSupplier(String query) {
        return chooseViaChemicalsJDBC(keySupplier, query);
    }

    public List<String> chooseRptToxRecognized(String query) {
        return chooseViaToxicitiesJDBC(keyRptToxicityRecognized, query);
    }

    public List<String> chooseRptToxSuspected(String query) {
        return chooseViaToxicitiesJDBC(keyRptToxicitySuspected, query);
    }

    public void handleCountrySelect(SelectEvent event) {
        chosenValues.put(keyCountry, event.getObject().toString().trim());
    }

    public void handleStateSelect(SelectEvent event) {
        chosenValues.put(keyState, event.getObject().toString().trim());
    }

    public void handleCountySelect(SelectEvent event) {
        chosenValues.put(keyCounty, event.getObject().toString().trim());
    }

    public void handleAPISelect(SelectEvent event) {
        chosenValues.put(keyAPI, event.getObject().toString().trim());
    }

    public void handleOperatorSelect(SelectEvent event) {
        chosenValues.put(keyOperator, event.getObject().toString().trim());
    }

    public void handleWellNameSelect(SelectEvent event) {
        chosenValues.put(keyWellName, event.getObject().toString().trim());
    }

    public void handleFractureDateSelect(SelectEvent event) {
        chosenValues.put(keyFractureDate, event.getObject().toString().trim());
    }

    public void handlePdfSeqIdSelect(SelectEvent event) {
        chosenValues.put(keyPdfSeqId, event.getObject().toString().trim());
    }

    public void handleProductionTypeSelect(SelectEvent event) {
        chosenValues.put(keyProdType, event.getObject().toString().trim());
    }

    public void handleCasNumberSelect(SelectEvent event) {
        chosenValues.put(keyCasNumber, event.getObject().toString().trim());
    }

    public void handleIngredientsSelect(SelectEvent event) {
        chosenValues.put(keyIngredients, event.getObject().toString().trim());
    }

    public void handlePurposeSelect(SelectEvent event) {
        chosenValues.put(keyPurpose, event.getObject().toString().trim());
    }

    public void handleSupplierSelect(SelectEvent event) {
        chosenValues.put(keySupplier, event.getObject().toString().trim());
    }

    public void handleTradeNameSelect(SelectEvent event) {
        chosenValues.put(keyTradeName, event.getObject().toString().trim());
    }

    public void handleToxicityRecognized(ValueChangeEvent event) {
        handleValueChangeOfBoolean(keyRptToxicityRecognized, event);
    }

    public void handleToxicitySuspected(ValueChangeEvent event) {
        handleValueChangeOfBoolean(keyRptToxicitySuspected, event);
    }

    public void handleToxicityRecognizedCancer(ValueChangeEvent event) {
        handleValueChangeOfBoolean(keyRptToxicityRecognizedCancer, event);
    }

    public void handleToxicitySuspectedCancer(ValueChangeEvent event) {
        handleValueChangeOfBoolean(keyRptToxicitySuspectedCancer, event);
    }

    public void handleToxicityRecognizedCardioBlood(ValueChangeEvent event) {
        handleValueChangeOfBoolean(keyRptToxicityRecognizedCardioBlood, event);
    }

    public void handleToxicitySuspectedCardioBlood(ValueChangeEvent event) {
        handleValueChangeOfBoolean(keyRptToxicitySuspectedCardioBlood, event);
    }

    public void handleToxicityRecognizedDevelopmental(ValueChangeEvent event) {
        handleValueChangeOfBoolean(keyRptToxicityRecognizedDevelopmental, event);
    }

    public void handleToxicitySuspectedDevelopmental(ValueChangeEvent event) {
        handleValueChangeOfBoolean(keyRptToxicitySuspectedDevelopmental, event);
    }

    public void handleToxicityRecognizedEndocrine(ValueChangeEvent event) {
        handleValueChangeOfBoolean(keyRptToxicityRecognizedEndocrine, event);
    }

    public void handleToxicitySuspectedEndocrine(ValueChangeEvent event) {
        handleValueChangeOfBoolean(keyRptToxicitySuspectedEndocrine, event);
    }

    public void handleToxicityRecognizedGastroLiver(ValueChangeEvent event) {
        handleValueChangeOfBoolean(keyRptToxicityRecognizedGastroLiver, event);
    }

    public void handleToxicitySuspectedGastroLiver(ValueChangeEvent event) {
        handleValueChangeOfBoolean(keyRptToxicitySuspectedGastroLiver, event);
    }

    public void handleToxicityRecognizedImmunotoxicity(ValueChangeEvent event) {
        handleValueChangeOfBoolean(keyRptToxicityRecognizedImmunotoxicity, event);
    }

    public void handleToxicitySuspectedImmunotoxicity(ValueChangeEvent event) {
        handleValueChangeOfBoolean(keyRptToxicitySuspectedImmunotoxicity, event);
    }

    public void handleToxicityRecognizedKidney(ValueChangeEvent event) {
        handleValueChangeOfBoolean(keyRptToxicityRecognizedKidney, event);
    }

    public void handleToxicitySuspectedKidney(ValueChangeEvent event) {
        handleValueChangeOfBoolean(keyRptToxicitySuspectedKidney, event);
    }

    public void handleToxicityRecognizedMusculoskeletal(ValueChangeEvent event) {
        handleValueChangeOfBoolean(keyRptToxicityRecognizedMusculoskeletal, event);
    }

    public void handleToxicitySuspectedMusculoskeletal(ValueChangeEvent event) {
        handleValueChangeOfBoolean(keyRptToxicitySuspectedMusculoskeletal, event);
    }

    public void handleToxicityRecognizedNeurotoxicity(ValueChangeEvent event) {
        handleValueChangeOfBoolean(keyRptToxicityRecognizedNeurotoxicity, event);
    }

    public void handleToxicitySuspectedNeurotoxicity(ValueChangeEvent event) {
        handleValueChangeOfBoolean(keyRptToxicitySuspectedNeurotoxicity, event);
    }

    public void handleToxicityRecognizedReproductive(ValueChangeEvent event) {
        handleValueChangeOfBoolean(keyRptToxicityRecognizedReproductive, event);
    }

    public void handleToxicitySuspectedReproductive(ValueChangeEvent event) {
        handleValueChangeOfBoolean(keyRptToxicitySuspectedReproductive, event);
    }

    public void handleToxicityRecognizedRespiratory(ValueChangeEvent event) {
        handleValueChangeOfBoolean(keyRptToxicityRecognizedRespiratory, event);
    }

    public void handleToxicitySuspectedRespiratory(ValueChangeEvent event) {
        handleValueChangeOfBoolean(keyRptToxicitySuspectedRespiratory, event);
    }

    public void handleToxicityRecognizedSkinSense(ValueChangeEvent event) {
        handleValueChangeOfBoolean(keyRptToxicityRecognizedSkinSense, event);
    }

    public void handleToxicitySuspectedSkinSense(ValueChangeEvent event) {
        handleValueChangeOfBoolean(keyRptToxicitySuspectedSkinSense, event);
    }

    private void handleValueChangeOfBoolean(String key, ValueChangeEvent event) {
        String newValue = event.getNewValue().toString();
        chosenValues.put(key, newValue.equals("true") ? newValue : "");
    }

    /**
     * @return the chosenCountry
     */
    public String getChosenCountry() {
        return chosenValues.get(keyCountry);
    }

    /**
     * @param chosenCountry the chosenCountry to set
     */
    public void setChosenCountry(String value) {
        chosenValues.put(keyCountry, value);
    }

    /**
     * @return the chosenState
     */
    public String getChosenState() {
        return chosenValues.get(keyState);
    }

    /**
     * @param chosenState the chosenState to set
     */
    public void setChosenState(String value) {
        chosenValues.put(keyState, value);
    }

    /**
     * @return the chosenCounty
     */
    public String getChosenCounty() {
        return chosenValues.get(keyCounty);
    }

    /**
     * @param chosenCounty the chosenCounty to set
     */
    public void setChosenCounty(String value) {
        chosenValues.put(keyCounty, value);
    }

    /**
     * @return the chosenOperator
     */
    public String getChosenOperator() {
        return chosenValues.get(keyOperator);
    }

    /**
     * @param chosenOperator the chosenOperator to set
     */
    public void setChosenOperator(String value) {
        chosenValues.put(keyOperator, value);
    }

    /**
     * @return the chosenWellName
     */
    public String getChosenWellName() {
        return chosenValues.get(keyWellName);
    }

    /**
     * @param chosenWellName the chosenWellName to set
     */
    public void setChosenWellName(String value) {
        chosenValues.put(keyWellName, value);
    }

    /**
     * @return the chosenAPI
     */
    public String getChosenAPI() {
        return chosenValues.get(keyAPI);
    }

    /**
     * @param chosenAPI the chosenAPI to set
     */
    public void setChosenAPI(String value) {
        chosenValues.put(keyAPI, value);
    }

    /**
     * @return the chosenFractureDate
     */
    public String getChosenFractureDate() {
        return chosenValues.get(keyFractureDate);
    }

    /**
     * @param chosenFractureDate the chosenFractureDate to set
     */
    public void setChosenFractureDate(String value) {
        chosenValues.put(keyFractureDate, value);
    }

    /**
     * @return the chosenPdfSeqId
     */
    public String getChosenPdfSeqId() {
        return chosenValues.get(keyPdfSeqId);
    }

    /**
     * @param chosenPdfSeqId the chosenPdfSeqId to set
     */
    public void setChosenPdfSeqId(String value) {
        chosenValues.put(keyPdfSeqId, value);
    }

    /**
     * @return the chosenProdType
     */
    public String getChosenProdType() {
        return chosenValues.get(keyProdType);
    }

    /**
     * @param chosenProdType the chosenProdType to set
     */
    public void setChosenProdType(String value) {
        chosenValues.put(keyProdType, value);
    }

    /**
     * @return the chosenCasNumber
     */
    public String getChosenCasNumber() {
        return chosenValues.get(keyCasNumber);
    }

    /**
     * @param chosenCasNumber the chosenCasNumber to set
     */
    public void setChosenCasNumber(String value) {
        chosenValues.put(keyCasNumber, value);
    }

    /**
     * @return the chosenIngredients
     */
    public String getChosenIngredients() {
        return chosenValues.get(keyIngredients);
    }

    /**
     * @param chosenIngredients the chosenIngredients to set
     */
    public void setChosenIngredients(String value) {
        chosenValues.put(keyIngredients, value);
    }

    /**
     * @return the chosenTradeName
     */
    public String getChosenTradeName() {
        return chosenValues.get(keyTradeName);
    }

    /**
     * @param chosenTradeName the chosenTradeName to set
     */
    public void setChosenTradeName(String value) {
        chosenValues.put(keyTradeName, value);
    }

    /**
     * @return the chosenPurpose
     */
    public String getChosenPurpose() {
        return chosenValues.get(keyPurpose);
    }

    /**
     * @param chosenPurpose the chosenPurpose to set
     */
    public void setChosenPurpose(String value) {
        chosenValues.put(keyPurpose, value);
    }

    /**
     * @return the chosenSupplier
     */
    public String getChosenSupplier() {
        return chosenValues.get(keySupplier);
    }

    /**
     * @param chosenSupplier the chosenSupplier to set
     */
    public void setChosenSupplier(String value) {
        chosenValues.put(keySupplier, value);
    }

    public Boolean getChosenRptToxicityRecognized() {
        return Boolean.parseBoolean(chosenValues.get(keyRptToxicityRecognized));
    }
    public void setChosenRptToxicityRecognized(Boolean value) {
        chosenValues.put(keyRptToxicityRecognized, value ? value.toString() : "");
    }

    public Boolean getChosenRptToxicitySuspected() {
        return Boolean.parseBoolean(chosenValues.get(keyRptToxicitySuspected));
    }
    public void setChosenRptToxicitySuspected(Boolean value) {
        chosenValues.put(keyRptToxicitySuspected, value ? value.toString() : "");
    }

    public Boolean getChosenRptToxicityRecognizedCancer() {
        return Boolean.parseBoolean(chosenValues.get(keyRptToxicityRecognizedCancer));
    }
    public void setChosenRptToxicityRecognizedCancer(Boolean value) {
        chosenValues.put(keyRptToxicityRecognizedCancer, value ? value.toString() : "");
    }

    public Boolean getChosenRptToxicitySuspectedCancer() {
        return Boolean.parseBoolean(chosenValues.get(keyRptToxicitySuspectedCancer));
    }
    public void setChosenRptToxicitySuspectedCancer(Boolean value) {
        chosenValues.put(keyRptToxicitySuspectedCancer, value ? value.toString() : "");
    }

    public Boolean getChosenRptToxicityRecognizedCardioBlood() {
        return Boolean.parseBoolean(chosenValues.get(keyRptToxicityRecognizedCardioBlood));
    }
    public void setChosenRptToxicityRecognizedCardioBlood(Boolean value) {
        chosenValues.put(keyRptToxicityRecognizedCardioBlood, value ? value.toString() : "");
    }

    public Boolean getChosenRptToxicitySuspectedCardioBlood() {
        return Boolean.parseBoolean(chosenValues.get(keyRptToxicitySuspectedCardioBlood));
    }
    public void setChosenRptToxicitySuspectedCardioBlood(Boolean value) {
        chosenValues.put(keyRptToxicitySuspectedCardioBlood, value ? value.toString() : "");
    }

    public Boolean getChosenRptToxicityRecognizedDevelopmental() {
        return Boolean.parseBoolean(chosenValues.get(keyRptToxicityRecognizedDevelopmental));
    }
    public void setChosenRptToxicityRecognizedDevelopmental(Boolean value) {
        chosenValues.put(keyRptToxicityRecognizedDevelopmental, value ? value.toString() : "");
    }

    public Boolean getChosenRptToxicitySuspectedDevelopmental() {
        return Boolean.parseBoolean(chosenValues.get(keyRptToxicitySuspectedDevelopmental));
    }
    public void setChosenRptToxicitySuspectedDevelopmental(Boolean value) {
        chosenValues.put(keyRptToxicitySuspectedDevelopmental, value ? value.toString() : "");
    }

    public Boolean getChosenRptToxicityRecognizedEndocrine() {
        return Boolean.parseBoolean(chosenValues.get(keyRptToxicityRecognizedEndocrine));
    }
    public void setChosenRptToxicityRecognizedEndocrine(Boolean value) {
        chosenValues.put(keyRptToxicityRecognizedEndocrine, value ? value.toString() : "");
    }

    public Boolean getChosenRptToxicitySuspectedEndocrine() {
        return Boolean.parseBoolean(chosenValues.get(keyRptToxicitySuspectedEndocrine));
    }
    public void setChosenRptToxicitySuspectedEndocrine(Boolean value) {
        chosenValues.put(keyRptToxicitySuspectedEndocrine, value ? value.toString() : "");
    }

    public Boolean getChosenRptToxicityRecognizedGastroLiver() {
        return Boolean.parseBoolean(chosenValues.get(keyRptToxicityRecognizedGastroLiver));
    }
    public void setChosenRptToxicityRecognizedGastroLiver(Boolean value) {
        chosenValues.put(keyRptToxicityRecognizedGastroLiver, value ? value.toString() : "");
    }

    public Boolean getChosenRptToxicitySuspectedGastroLiver() {
        return Boolean.parseBoolean(chosenValues.get(keyRptToxicitySuspectedGastroLiver));
    }
    public void setChosenRptToxicitySuspectedGastroLiver(Boolean value) {
        chosenValues.put(keyRptToxicitySuspectedGastroLiver, value ? value.toString() : "");
    }

    public Boolean getChosenRptToxicityRecognizedImmunotoxicity() {
        return Boolean.parseBoolean(chosenValues.get(keyRptToxicityRecognizedImmunotoxicity));
    }
    public void setChosenRptToxicityRecognizedImmunotoxicity(Boolean value) {
        chosenValues.put(keyRptToxicityRecognizedImmunotoxicity, value ? value.toString() : "");
    }

    public Boolean getChosenRptToxicitySuspectedImmunotoxicity() {
        return Boolean.parseBoolean(chosenValues.get(keyRptToxicitySuspectedImmunotoxicity));
    }
    public void setChosenRptToxicitySuspectedImmunotoxicity(Boolean value) {
        chosenValues.put(keyRptToxicitySuspectedImmunotoxicity, value ? value.toString() : "");
    }

    public Boolean getChosenRptToxicityRecognizedKidney() {
        return Boolean.parseBoolean(chosenValues.get(keyRptToxicityRecognizedKidney));
    }
    public void setChosenRptToxicityRecognizedKidney(Boolean value) {
        chosenValues.put(keyRptToxicityRecognizedKidney, value ? value.toString() : "");
    }

    public Boolean getChosenRptToxicitySuspectedKidney() {
        return Boolean.parseBoolean(chosenValues.get(keyRptToxicitySuspectedKidney));
    }
    public void setChosenRptToxicitySuspectedKidney(Boolean value) {
        chosenValues.put(keyRptToxicitySuspectedKidney, value ? value.toString() : "");
    }

    public Boolean getChosenRptToxicityRecognizedMusculoskeletal() {
        return Boolean.parseBoolean(chosenValues.get(keyRptToxicityRecognizedMusculoskeletal));
    }
    public void setChosenRptToxicityRecognizedMusculoskeletal(Boolean value) {
        chosenValues.put(keyRptToxicityRecognizedMusculoskeletal, value ? value.toString() : "");
    }

    public Boolean getChosenRptToxicitySuspectedMusculoskeletal() {
        return Boolean.parseBoolean(chosenValues.get(keyRptToxicitySuspectedMusculoskeletal));
    }
    public void setChosenRptToxicitySuspectedMusculoskeletal(Boolean value) {
        chosenValues.put(keyRptToxicitySuspectedMusculoskeletal, value ? value.toString() : "");
    }

    public Boolean getChosenRptToxicityRecognizedNeurotoxicity() {
        return Boolean.parseBoolean(chosenValues.get(keyRptToxicityRecognizedNeurotoxicity));
    }
    public void setChosenRptToxicityRecognizedNeurotoxicity(Boolean value) {
        chosenValues.put(keyRptToxicityRecognizedNeurotoxicity, value ? value.toString() : "");
    }

    public Boolean getChosenRptToxicitySuspectedNeurotoxicity() {
        return Boolean.parseBoolean(chosenValues.get(keyRptToxicitySuspectedNeurotoxicity));
    }
    public void setChosenRptToxicitySuspectedNeurotoxicity(Boolean value) {
        chosenValues.put(keyRptToxicitySuspectedNeurotoxicity, value ? value.toString() : "");
    }

    public Boolean getChosenRptToxicityRecognizedReproductive() {
        return Boolean.parseBoolean(chosenValues.get(keyRptToxicityRecognizedReproductive));
    }
    public void setChosenRptToxicityRecognizedReproductive(Boolean value) {
        chosenValues.put(keyRptToxicityRecognizedReproductive, value ? value.toString() : "");
    }

    public Boolean getChosenRptToxicitySuspectedReproductive() {
        return Boolean.parseBoolean(chosenValues.get(keyRptToxicitySuspectedReproductive));
    }
    public void setChosenRptToxicitySuspectedReproductive(Boolean value) {
        chosenValues.put(keyRptToxicitySuspectedReproductive, value ? value.toString() : "");
    }

    public Boolean getChosenRptToxicityRecognizedRespiratory() {
        return Boolean.parseBoolean(chosenValues.get(keyRptToxicityRecognizedRespiratory));
    }
    public void setChosenRptToxicityRecognizedRespiratory(Boolean value) {
        chosenValues.put(keyRptToxicityRecognizedRespiratory, value ? value.toString() : "");
    }

    public Boolean getChosenRptToxicitySuspectedRespiratory() {
        return Boolean.parseBoolean(chosenValues.get(keyRptToxicitySuspectedRespiratory));
    }
    public void setChosenRptToxicitySuspectedRespiratory(Boolean value) {
        chosenValues.put(keyRptToxicitySuspectedRespiratory, value ? value.toString() : "");
    }

    public Boolean getChosenRptToxicityRecognizedSkinSense() {
        return Boolean.parseBoolean(chosenValues.get(keyRptToxicityRecognizedSkinSense));
    }
    public void setChosenRptToxicityRecognizedSkinSense(Boolean value) {
        chosenValues.put(keyRptToxicityRecognizedSkinSense, value ? value.toString() : "");
    }

    public Boolean getChosenRptToxicitySuspectedSkinSense() {
        return Boolean.parseBoolean(chosenValues.get(keyRptToxicitySuspectedSkinSense));
    }
    public void setChosenRptToxicitySuspectedSkinSense(Boolean value) {
        chosenValues.put(keyRptToxicitySuspectedSkinSense, value ? value.toString() : "");
    }

    /**
     * @return the maxResults
     */
    public int getMaxResults() {
        return maxResults;
    }

    /**
     * @param maxResults the maxResults to set
     */
    public void setMaxResults(int maxResults) {
        this.maxResults = maxResults;
    }

    /**
     * @return the maxRows
     */
    public int getMaxRows() {
        return maxRows;
    }

    /**
     * @param maxRows the maxRows to set
     */
    public void setMaxRows(int maxRows) {
        this.maxRows = maxRows;
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
     * @return the keyAPI
     */
    public String getKeyAPI() {
        return keyAPI;
    }

    /**
     * @param keyAPI the keyAPI to set
     */
    public void setKeyAPI(String keyAPI) {
        this.keyAPI = keyAPI;
    }

    /**
     * @return the keyCountry
     */
    public String getKeyCountry() {
        return keyCountry;
    }

    /**
     * @return the keyCounty
     */
    public String getKeyCounty() {
        return keyCounty;
    }

    /**
     * @return the keyFractureDate
     */
    public String getKeyFractureDate() {
        return keyFractureDate;
    }

    /**
     * @return the keyOperator
     */
    public String getKeyOperator() {
        return keyOperator;
    }

    /**
     * @return the keyPdfSeqId
     */
    public String getKeyPdfSeqId() {
        return keyPdfSeqId;
    }

    /**
     * @return the keyProductionType
     */
    public String getKeyProdType() {
        return keyProdType;
    }

    /**
     * @return the keyState
     */
    public String getKeyState() {
        return keyState;
    }

    /**
     * @return the keyWellName
     */
    public String getKeyWellName() {
        return keyWellName;
    }

    /**
     * @return the keyPublishedDate
     */
    public String getKeyPublishedDate() {
        return keyPublishedDate;
    }

    /**
     * @param keyPublishedDate the keyPublishedDate to set
     */
    public void setKeyPublishedDate(String keyPublishedDate) {
        this.keyPublishedDate = keyPublishedDate;
    }

    /**
     * @return the theme
     */
    public String getTheme() {
        return theme;
    }

    /**
     * @param theme the theme to set
     */
    public void setTheme(String theme) {
        this.theme = theme;
    }

    /**
     * @return the themes
     */
    public Map<String, String> getThemes() {
        return themes;
    }

    /**
     * @param themes the themes to set
     */
    public void setThemes(Map<String, String> themes) {
        this.themes = themes;
    }

    /**
     * @return the keyAdditiveConcentration
     */
    public String getKeyAdditiveConcentration() {
        return keyAdditiveConcentration;
    }

    /**
     * @return the keyCasNumber
     */
    public String getKeyCasNumber() {
        return keyCasNumber;
    }

    /**
     * @return the keyComments
     */
    public String getKeyComments() {
        return keyComments;
    }

    /**
     * @return the keyHfFluidConcentration
     */
    public String getKeyHfFluidConcentration() {
        return keyHfFluidConcentration;
    }

    /**
     * @return the keyIngredients
     */
    public String getKeyIngredients() {
        return keyIngredients;
    }

    /**
     * @return the keyPurpose
     */
    public String getKeyPurpose() {
        return keyPurpose;
    }

    /**
     * @return the keyTradeName
     */
    public String getKeyTradeName() {
        return keyTradeName;
    }

    /**
     * @return the keySupplier
     */
    public String getKeySupplier() {
        return keySupplier;
    }

    /**
     * @return the keyRow
     */
    public String getKeyRow() {
        return keyRow;
    }

    /**
     * @return the keyCasType
     */
    public String getKeyCasType() {
        return keyCasType;
    }

    /**
     * @return the keyDatum
     */
    public String getKeyDatum() {
        return keyDatum;
    }

    /**
     * @return the keyLatitude
     */
    public String getKeyLatitude() {
        return keyLatitude;
    }

    /**
     * @return the keyLongitude
     */
    public String getKeyLongitude() {
        return keyLongitude;
    }

    /**
     * @return the keyTotalWaterVolume
     */
    public String getKeyTotalWaterVolume() {
        return keyTotalWaterVolume;
    }

    /**
     * @return the keyTrueVerticalDepth
     */
    public String getKeyTrueVerticalDepth() {
        return keyTrueVerticalDepth;
    }

    /**
     * @return the keyRptToxicityRecognized
     */
    public String getKeyRptToxicityRecognized() {
        return keyRptToxicityRecognized;
    }

    /**
     * @param keyRptToxicityRecognized the keyRptToxicityRecognized to set
     */
    public void setKeyRptToxicityRecognized(String keyRptToxicityRecognized) {
        this.keyRptToxicityRecognized = keyRptToxicityRecognized;
    }

    /**
     * @return the keyRptToxicitySuspected
     */
    public String getKeyRptToxicitySuspected() {
        return keyRptToxicitySuspected;
    }

    /**
     * @param keyRptToxicitySuspected the keyRptToxicitySuspected to set
     */
    public void setKeyRptToxicitySuspected(String keyRptToxicitySuspected) {
        this.keyRptToxicitySuspected = keyRptToxicitySuspected;
    }

    /**
     * @return the keyRptToxicityRecognizedCancer
     */
    public String getKeyRptToxicityRecognizedCancer() {
        return keyRptToxicityRecognizedCancer;
    }

    /**
     * @param keyRptToxicityRecognizedCancer the keyRptToxicityRecognizedCancer to set
     */
    public void setKeyRptToxicityRecognizedCancer(String keyRptToxicityRecognizedCancer) {
        this.keyRptToxicityRecognizedCancer = keyRptToxicityRecognizedCancer;
    }

    /**
     * @return the keyRptToxicitySuspectedCancer
     */
    public String getKeyRptToxicitySuspectedCancer() {
        return keyRptToxicitySuspectedCancer;
    }

    /**
     * @param keyRptToxicitySuspectedCancer the keyRptToxicitySuspectedCancer to set
     */
    public void setKeyRptToxicitySuspectedCancer(String keyRptToxicitySuspectedCancer) {
        this.keyRptToxicitySuspectedCancer = keyRptToxicitySuspectedCancer;
    }

    /**
     * @return the keyRptToxicityRecognizedCardioBlood
     */
    public String getKeyRptToxicityRecognizedCardioBlood() {
        return keyRptToxicityRecognizedCardioBlood;
    }

    /**
     * @param keyRptToxicityRecognizedCardioBlood the keyRptToxicityRecognizedCardioBlood to set
     */
    public void setKeyRptToxicityRecognizedCardioBlood(String keyRptToxicityRecognizedCardioBlood) {
        this.keyRptToxicityRecognizedCardioBlood = keyRptToxicityRecognizedCardioBlood;
    }

    /**
     * @return the keyRptToxicitySuspectedCardioBlood
     */
    public String getKeyRptToxicitySuspectedCardioBlood() {
        return keyRptToxicitySuspectedCardioBlood;
    }

    /**
     * @param keyRptToxicitySuspectedCardioBlood the keyRptToxicitySuspectedCardioBlood to set
     */
    public void setKeyRptToxicitySuspectedCardioBlood(String keyRptToxicitySuspectedCardioBlood) {
        this.keyRptToxicitySuspectedCardioBlood = keyRptToxicitySuspectedCardioBlood;
    }

    /**
     * @return the keyRptToxicityRecognizedDevelopmental
     */
    public String getKeyRptToxicityRecognizedDevelopmental() {
        return keyRptToxicityRecognizedDevelopmental;
    }

    /**
     * @param keyRptToxicityRecognizedDevelopmental the keyRptToxicityRecognizedDevelopmental to set
     */
    public void setKeyRptToxicityRecognizedDevelopmental(String keyRptToxicityRecognizedDevelopmental) {
        this.keyRptToxicityRecognizedDevelopmental = keyRptToxicityRecognizedDevelopmental;
    }

    /**
     * @return the keyRptToxicitySuspectedDevelopmental
     */
    public String getKeyRptToxicitySuspectedDevelopmental() {
        return keyRptToxicitySuspectedDevelopmental;
    }

    /**
     * @param keyRptToxicitySuspectedDevelopmental the keyRptToxicitySuspectedDevelopmental to set
     */
    public void setKeyRptToxicitySuspectedDevelopmental(String keyRptToxicitySuspectedDevelopmental) {
        this.keyRptToxicitySuspectedDevelopmental = keyRptToxicitySuspectedDevelopmental;
    }

    /**
     * @return the keyRptToxicityRecognizedEndocrine
     */
    public String getKeyRptToxicityRecognizedEndocrine() {
        return keyRptToxicityRecognizedEndocrine;
    }

    /**
     * @param keyRptToxicityRecognizedEndocrine the keyRptToxicityRecognizedEndocrine to set
     */
    public void setKeyRptToxicityRecognizedEndocrine(String keyRptToxicityRecognizedEndocrine) {
        this.keyRptToxicityRecognizedEndocrine = keyRptToxicityRecognizedEndocrine;
    }

    /**
     * @return the keyRptToxicitySuspectedEndocrine
     */
    public String getKeyRptToxicitySuspectedEndocrine() {
        return keyRptToxicitySuspectedEndocrine;
    }

    /**
     * @param keyRptToxicitySuspectedEndocrine the keyRptToxicitySuspectedEndocrine to set
     */
    public void setKeyRptToxicitySuspectedEndocrine(String keyRptToxicitySuspectedEndocrine) {
        this.keyRptToxicitySuspectedEndocrine = keyRptToxicitySuspectedEndocrine;
    }

    /**
     * @return the keyRptToxicityRecognizedGastroLiver
     */
    public String getKeyRptToxicityRecognizedGastroLiver() {
        return keyRptToxicityRecognizedGastroLiver;
    }

    /**
     * @param keyRptToxicityRecognizedGastroLiver the keyRptToxicityRecognizedGastroLiver to set
     */
    public void setKeyRptToxicityRecognizedGastroLiver(String keyRptToxicityRecognizedGastroLiver) {
        this.keyRptToxicityRecognizedGastroLiver = keyRptToxicityRecognizedGastroLiver;
    }

    /**
     * @return the keyRptToxicitySuspectedGastroLiver
     */
    public String getKeyRptToxicitySuspectedGastroLiver() {
        return keyRptToxicitySuspectedGastroLiver;
    }

    /**
     * @param keyRptToxicitySuspectedGastroLiver the keyRptToxicitySuspectedGastroLiver to set
     */
    public void setKeyRptToxicitySuspectedGastroLiver(String keyRptToxicitySuspectedGastroLiver) {
        this.keyRptToxicitySuspectedGastroLiver = keyRptToxicitySuspectedGastroLiver;
    }

    /**
     * @return the keyRptToxicityRecognizedImmunotoxicity
     */
    public String getKeyRptToxicityRecognizedImmunotoxicity() {
        return keyRptToxicityRecognizedImmunotoxicity;
    }

    /**
     * @param keyRptToxicityRecognizedImmunotoxicity the keyRptToxicityRecognizedImmunotoxicity to set
     */
    public void setKeyRptToxicityRecognizedImmunotoxicity(String keyRptToxicityRecognizedImmunotoxicity) {
        this.keyRptToxicityRecognizedImmunotoxicity = keyRptToxicityRecognizedImmunotoxicity;
    }

    /**
     * @return the keyRptToxicitySuspectedImmunotoxicity
     */
    public String getKeyRptToxicitySuspectedImmunotoxicity() {
        return keyRptToxicitySuspectedImmunotoxicity;
    }

    /**
     * @param keyRptToxicitySuspectedImmunotoxicity the keyRptToxicitySuspectedImmunotoxicity to set
     */
    public void setKeyRptToxicitySuspectedImmunotoxicity(String keyRptToxicitySuspectedImmunotoxicity) {
        this.keyRptToxicitySuspectedImmunotoxicity = keyRptToxicitySuspectedImmunotoxicity;
    }

    /**
     * @return the keyRptToxicityRecognizedKidney
     */
    public String getKeyRptToxicityRecognizedKidney() {
        return keyRptToxicityRecognizedKidney;
    }

    /**
     * @param keyRptToxicityRecognizedKidney the keyRptToxicityRecognizedKidney to set
     */
    public void setKeyRptToxicityRecognizedKidney(String keyRptToxicityRecognizedKidney) {
        this.keyRptToxicityRecognizedKidney = keyRptToxicityRecognizedKidney;
    }

    /**
     * @return the keyRptToxicitySuspectedKidney
     */
    public String getKeyRptToxicitySuspectedKidney() {
        return keyRptToxicitySuspectedKidney;
    }

    /**
     * @param keyRptToxicitySuspectedKidney the keyRptToxicitySuspectedKidney to set
     */
    public void setKeyRptToxicitySuspectedKidney(String keyRptToxicitySuspectedKidney) {
        this.keyRptToxicitySuspectedKidney = keyRptToxicitySuspectedKidney;
    }

    /**
     * @return the keyRptToxicityRecognizedMusculoskeletal
     */
    public String getKeyRptToxicityRecognizedMusculoskeletal() {
        return keyRptToxicityRecognizedMusculoskeletal;
    }

    /**
     * @param keyRptToxicityRecognizedMusculoskeletal the keyRptToxicityRecognizedMusculoskeletal to set
     */
    public void setKeyRptToxicityRecognizedMusculoskeletal(String keyRptToxicityRecognizedMusculoskeletal) {
        this.keyRptToxicityRecognizedMusculoskeletal = keyRptToxicityRecognizedMusculoskeletal;
    }

    /**
     * @return the keyRptToxicitySuspectedMusculoskeletal
     */
    public String getKeyRptToxicitySuspectedMusculoskeletal() {
        return keyRptToxicitySuspectedMusculoskeletal;
    }

    /**
     * @param keyRptToxicitySuspectedMusculoskeletal the keyRptToxicitySuspectedMusculoskeletal to set
     */
    public void setKeyRptToxicitySuspectedMusculoskeletal(String keyRptToxicitySuspectedMusculoskeletal) {
        this.keyRptToxicitySuspectedMusculoskeletal = keyRptToxicitySuspectedMusculoskeletal;
    }

    /**
     * @return the keyRptToxicityRecognizedNeurotoxicity
     */
    public String getKeyRptToxicityRecognizedNeurotoxicity() {
        return keyRptToxicityRecognizedNeurotoxicity;
    }

    /**
     * @param keyRptToxicityRecognizedNeurotoxicity the keyRptToxicityRecognizedNeurotoxicity to set
     */
    public void setKeyRptToxicityRecognizedNeurotoxicity(String keyRptToxicityRecognizedNeurotoxicity) {
        this.keyRptToxicityRecognizedNeurotoxicity = keyRptToxicityRecognizedNeurotoxicity;
    }

    /**
     * @return the keyRptToxicitySuspectedNeurotoxicity
     */
    public String getKeyRptToxicitySuspectedNeurotoxicity() {
        return keyRptToxicitySuspectedNeurotoxicity;
    }

    /**
     * @param keyRptToxicitySuspectedNeurotoxicity the keyRptToxicitySuspectedNeurotoxicity to set
     */
    public void setKeyRptToxicitySuspectedNeurotoxicity(String keyRptToxicitySuspectedNeurotoxicity) {
        this.keyRptToxicitySuspectedNeurotoxicity = keyRptToxicitySuspectedNeurotoxicity;
    }

    /**
     * @return the keyRptToxicityRecognizedReproductive
     */
    public String getKeyRptToxicityRecognizedReproductive() {
        return keyRptToxicityRecognizedReproductive;
    }

    /**
     * @param keyRptToxicityRecognizedReproductive the keyRptToxicityRecognizedReproductive to set
     */
    public void setKeyRptToxicityRecognizedReproductive(String keyRptToxicityRecognizedReproductive) {
        this.keyRptToxicityRecognizedReproductive = keyRptToxicityRecognizedReproductive;
    }

    /**
     * @return the keyRptToxicitySuspectedReproductive
     */
    public String getKeyRptToxicitySuspectedReproductive() {
        return keyRptToxicitySuspectedReproductive;
    }

    /**
     * @param keyRptToxicitySuspectedReproductive the keyRptToxicitySuspectedReproductive to set
     */
    public void setKeyRptToxicitySuspectedReproductive(String keyRptToxicitySuspectedReproductive) {
        this.keyRptToxicitySuspectedReproductive = keyRptToxicitySuspectedReproductive;
    }

    /**
     * @return the keyRptToxicityRecognizedRespiratory
     */
    public String getKeyRptToxicityRecognizedRespiratory() {
        return keyRptToxicityRecognizedRespiratory;
    }

    /**
     * @param keyRptToxicityRecognizedRespiratory the keyRptToxicityRecognizedRespiratory to set
     */
    public void setKeyRptToxicityRecognizedRespiratory(String keyRptToxicityRecognizedRespiratory) {
        this.keyRptToxicityRecognizedRespiratory = keyRptToxicityRecognizedRespiratory;
    }

    /**
     * @return the keyRptToxicitySuspectedRespiratory
     */
    public String getKeyRptToxicitySuspectedRespiratory() {
        return keyRptToxicitySuspectedRespiratory;
    }

    /**
     * @param keyRptToxicitySuspectedRespiratory the keyRptToxicitySuspectedRespiratory to set
     */
    public void setKeyRptToxicitySuspectedRespiratory(String keyRptToxicitySuspectedRespiratory) {
        this.keyRptToxicitySuspectedRespiratory = keyRptToxicitySuspectedRespiratory;
    }

    /**
     * @return the keyRptToxicityRecognizedSkinSense
     */
    public String getKeyRptToxicityRecognizedSkinSense() {
        return keyRptToxicityRecognizedSkinSense;
    }

    /**
     * @param keyRptToxicityRecognizedSkinSense the keyRptToxicityRecognizedSkinSense to set
     */
    public void setKeyRptToxicityRecognizedSkinSense(String keyRptToxicityRecognizedSkinSense) {
        this.keyRptToxicityRecognizedSkinSense = keyRptToxicityRecognizedSkinSense;
    }

    /**
     * @return the keyRptToxicitySuspectedSkinSense
     */
    public String getKeyRptToxicitySuspectedSkinSense() {
        return keyRptToxicitySuspectedSkinSense;
    }

    /**
     * @param keyRptToxicitySuspectedSkinSense the keyRptToxicitySuspectedSkinSense to set
     */
    public void setKeyRptToxicitySuspectedSkinSense(String keyRptToxicitySuspectedSkinSense) {
        this.keyRptToxicitySuspectedSkinSense = keyRptToxicitySuspectedSkinSense;
    }

    /**
     * @return the keyChmToxicityRecognized
     */
    public String getKeyChmToxicityRecognized() {
        return keyChmToxicityRecognized;
    }

    /**
     * @param keyChmToxicityRecognized the keyChmToxicityRecognized to set
     */
    public void setKeyChmToxicityRecognized(String keyChmToxicityRecognized) {
        this.keyChmToxicityRecognized = keyChmToxicityRecognized;
    }

    /**
     * @return the keyChmToxicitySuspected
     */
    public String getKeyChmToxicitySuspected() {
        return keyChmToxicitySuspected;
    }

    /**
     * @param keyChmToxicitySuspected the keyChmToxicitySuspected to set
     */
    public void setKeyChmToxicitySuspected(String keyChmToxicitySuspected) {
        this.keyChmToxicitySuspected = keyChmToxicitySuspected;
    }

    /**
     * @return the keyChmToxicityRecognizedCancer
     */
    public String getKeyChmToxicityRecognizedCancer() {
        return keyChmToxicityRecognizedCancer;
    }

    /**
     * @param keyChmToxicityRecognizedCancer the keyChmToxicityRecognizedCancer to set
     */
    public void setKeyChmToxicityRecognizedCancer(String keyChmToxicityRecognizedCancer) {
        this.keyChmToxicityRecognizedCancer = keyChmToxicityRecognizedCancer;
    }

    /**
     * @return the keyChmToxicitySuspectedCancer
     */
    public String getKeyChmToxicitySuspectedCancer() {
        return keyChmToxicitySuspectedCancer;
    }

    /**
     * @param keyChmToxicitySuspectedCancer the keyChmToxicitySuspectedCancer to set
     */
    public void setKeyChmToxicitySuspectedCancer(String keyChmToxicitySuspectedCancer) {
        this.keyChmToxicitySuspectedCancer = keyChmToxicitySuspectedCancer;
    }

    /**
     * @return the keyChmToxicityRecognizedCardioBlood
     */
    public String getKeyChmToxicityRecognizedCardioBlood() {
        return keyChmToxicityRecognizedCardioBlood;
    }

    /**
     * @param keyChmToxicityRecognizedCardioBlood the keyChmToxicityRecognizedCardioBlood to set
     */
    public void setKeyChmToxicityRecognizedCardioBlood(String keyChmToxicityRecognizedCardioBlood) {
        this.keyChmToxicityRecognizedCardioBlood = keyChmToxicityRecognizedCardioBlood;
    }

    /**
     * @return the keyChmToxicitySuspectedCardioBlood
     */
    public String getKeyChmToxicitySuspectedCardioBlood() {
        return keyChmToxicitySuspectedCardioBlood;
    }

    /**
     * @param keyChmToxicitySuspectedCardioBlood the keyChmToxicitySuspectedCardioBlood to set
     */
    public void setKeyChmToxicitySuspectedCardioBlood(String keyChmToxicitySuspectedCardioBlood) {
        this.keyChmToxicitySuspectedCardioBlood = keyChmToxicitySuspectedCardioBlood;
    }

    /**
     * @return the keyChmToxicityRecognizedDevelopmental
     */
    public String getKeyChmToxicityRecognizedDevelopmental() {
        return keyChmToxicityRecognizedDevelopmental;
    }

    /**
     * @param keyChmToxicityRecognizedDevelopmental the keyChmToxicityRecognizedDevelopmental to set
     */
    public void setKeyChmToxicityRecognizedDevelopmental(String keyChmToxicityRecognizedDevelopmental) {
        this.keyChmToxicityRecognizedDevelopmental = keyChmToxicityRecognizedDevelopmental;
    }

    /**
     * @return the keyChmToxicitySuspectedDevelopmental
     */
    public String getKeyChmToxicitySuspectedDevelopmental() {
        return keyChmToxicitySuspectedDevelopmental;
    }

    /**
     * @param keyChmToxicitySuspectedDevelopmental the keyChmToxicitySuspectedDevelopmental to set
     */
    public void setKeyChmToxicitySuspectedDevelopmental(String keyChmToxicitySuspectedDevelopmental) {
        this.keyChmToxicitySuspectedDevelopmental = keyChmToxicitySuspectedDevelopmental;
    }

    /**
     * @return the keyChmToxicityRecognizedEndocrine
     */
    public String getKeyChmToxicityRecognizedEndocrine() {
        return keyChmToxicityRecognizedEndocrine;
    }

    /**
     * @param keyChmToxicityRecognizedEndocrine the keyChmToxicityRecognizedEndocrine to set
     */
    public void setKeyChmToxicityRecognizedEndocrine(String keyChmToxicityRecognizedEndocrine) {
        this.keyChmToxicityRecognizedEndocrine = keyChmToxicityRecognizedEndocrine;
    }

    /**
     * @return the keyChmToxicitySuspectedEndocrine
     */
    public String getKeyChmToxicitySuspectedEndocrine() {
        return keyChmToxicitySuspectedEndocrine;
    }

    /**
     * @param keyChmToxicitySuspectedEndocrine the keyChmToxicitySuspectedEndocrine to set
     */
    public void setKeyChmToxicitySuspectedEndocrine(String keyChmToxicitySuspectedEndocrine) {
        this.keyChmToxicitySuspectedEndocrine = keyChmToxicitySuspectedEndocrine;
    }

    /**
     * @return the keyChmToxicityRecognizedGastroLiver
     */
    public String getKeyChmToxicityRecognizedGastroLiver() {
        return keyChmToxicityRecognizedGastroLiver;
    }

    /**
     * @param keyChmToxicityRecognizedGastroLiver the keyChmToxicityRecognizedGastroLiver to set
     */
    public void setKeyChmToxicityRecognizedGastroLiver(String keyChmToxicityRecognizedGastroLiver) {
        this.keyChmToxicityRecognizedGastroLiver = keyChmToxicityRecognizedGastroLiver;
    }

    /**
     * @return the keyChmToxicitySuspectedGastroLiver
     */
    public String getKeyChmToxicitySuspectedGastroLiver() {
        return keyChmToxicitySuspectedGastroLiver;
    }

    /**
     * @param keyChmToxicitySuspectedGastroLiver the keyChmToxicitySuspectedGastroLiver to set
     */
    public void setKeyChmToxicitySuspectedGastroLiver(String keyChmToxicitySuspectedGastroLiver) {
        this.keyChmToxicitySuspectedGastroLiver = keyChmToxicitySuspectedGastroLiver;
    }

    /**
     * @return the keyChmToxicityRecognizedImmunotoxicity
     */
    public String getKeyChmToxicityRecognizedImmunotoxicity() {
        return keyChmToxicityRecognizedImmunotoxicity;
    }

    /**
     * @param keyChmToxicityRecognizedImmunotoxicity the keyChmToxicityRecognizedImmunotoxicity to set
     */
    public void setKeyChmToxicityRecognizedImmunotoxicity(String keyChmToxicityRecognizedImmunotoxicity) {
        this.keyChmToxicityRecognizedImmunotoxicity = keyChmToxicityRecognizedImmunotoxicity;
    }

    /**
     * @return the keyChmToxicitySuspectedImmunotoxicity
     */
    public String getKeyChmToxicitySuspectedImmunotoxicity() {
        return keyChmToxicitySuspectedImmunotoxicity;
    }

    /**
     * @param keyChmToxicitySuspectedImmunotoxicity the keyChmToxicitySuspectedImmunotoxicity to set
     */
    public void setKeyChmToxicitySuspectedImmunotoxicity(String keyChmToxicitySuspectedImmunotoxicity) {
        this.keyChmToxicitySuspectedImmunotoxicity = keyChmToxicitySuspectedImmunotoxicity;
    }

    /**
     * @return the keyChmToxicityRecognizedKidney
     */
    public String getKeyChmToxicityRecognizedKidney() {
        return keyChmToxicityRecognizedKidney;
    }

    /**
     * @param keyChmToxicityRecognizedKidney the keyChmToxicityRecognizedKidney to set
     */
    public void setKeyChmToxicityRecognizedKidney(String keyChmToxicityRecognizedKidney) {
        this.keyChmToxicityRecognizedKidney = keyChmToxicityRecognizedKidney;
    }

    /**
     * @return the keyChmToxicitySuspectedKidney
     */
    public String getKeyChmToxicitySuspectedKidney() {
        return keyChmToxicitySuspectedKidney;
    }

    /**
     * @param keyChmToxicitySuspectedKidney the keyChmToxicitySuspectedKidney to set
     */
    public void setKeyChmToxicitySuspectedKidney(String keyChmToxicitySuspectedKidney) {
        this.keyChmToxicitySuspectedKidney = keyChmToxicitySuspectedKidney;
    }

    /**
     * @return the keyChmToxicityRecognizedMusculoskeletal
     */
    public String getKeyChmToxicityRecognizedMusculoskeletal() {
        return keyChmToxicityRecognizedMusculoskeletal;
    }

    /**
     * @param keyChmToxicityRecognizedMusculoskeletal the keyChmToxicityRecognizedMusculoskeletal to set
     */
    public void setKeyChmToxicityRecognizedMusculoskeletal(String keyChmToxicityRecognizedMusculoskeletal) {
        this.keyChmToxicityRecognizedMusculoskeletal = keyChmToxicityRecognizedMusculoskeletal;
    }

    /**
     * @return the keyChmToxicitySuspectedMusculoskeletal
     */
    public String getKeyChmToxicitySuspectedMusculoskeletal() {
        return keyChmToxicitySuspectedMusculoskeletal;
    }

    /**
     * @param keyChmToxicitySuspectedMusculoskeletal the keyChmToxicitySuspectedMusculoskeletal to set
     */
    public void setKeyChmToxicitySuspectedMusculoskeletal(String keyChmToxicitySuspectedMusculoskeletal) {
        this.keyChmToxicitySuspectedMusculoskeletal = keyChmToxicitySuspectedMusculoskeletal;
    }

    /**
     * @return the keyChmToxicityRecognizedNeurotoxicity
     */
    public String getKeyChmToxicityRecognizedNeurotoxicity() {
        return keyChmToxicityRecognizedNeurotoxicity;
    }

    /**
     * @param keyChmToxicityRecognizedNeurotoxicity the keyChmToxicityRecognizedNeurotoxicity to set
     */
    public void setKeyChmToxicityRecognizedNeurotoxicity(String keyChmToxicityRecognizedNeurotoxicity) {
        this.keyChmToxicityRecognizedNeurotoxicity = keyChmToxicityRecognizedNeurotoxicity;
    }

    /**
     * @return the keyChmToxicitySuspectedNeurotoxicity
     */
    public String getKeyChmToxicitySuspectedNeurotoxicity() {
        return keyChmToxicitySuspectedNeurotoxicity;
    }

    /**
     * @param keyChmToxicitySuspectedNeurotoxicity the keyChmToxicitySuspectedNeurotoxicity to set
     */
    public void setKeyChmToxicitySuspectedNeurotoxicity(String keyChmToxicitySuspectedNeurotoxicity) {
        this.keyChmToxicitySuspectedNeurotoxicity = keyChmToxicitySuspectedNeurotoxicity;
    }

    /**
     * @return the keyChmToxicityRecognizedReproductive
     */
    public String getKeyChmToxicityRecognizedReproductive() {
        return keyChmToxicityRecognizedReproductive;
    }

    /**
     * @param keyChmToxicityRecognizedReproductive the keyChmToxicityRecognizedReproductive to set
     */
    public void setKeyChmToxicityRecognizedReproductive(String keyChmToxicityRecognizedReproductive) {
        this.keyChmToxicityRecognizedReproductive = keyChmToxicityRecognizedReproductive;
    }

    /**
     * @return the keyChmToxicitySuspectedReproductive
     */
    public String getKeyChmToxicitySuspectedReproductive() {
        return keyChmToxicitySuspectedReproductive;
    }

    /**
     * @param keyChmToxicitySuspectedReproductive the keyChmToxicitySuspectedReproductive to set
     */
    public void setKeyChmToxicitySuspectedReproductive(String keyChmToxicitySuspectedReproductive) {
        this.keyChmToxicitySuspectedReproductive = keyChmToxicitySuspectedReproductive;
    }

    /**
     * @return the keyChmToxicityRecognizedRespiratory
     */
    public String getKeyChmToxicityRecognizedRespiratory() {
        return keyChmToxicityRecognizedRespiratory;
    }

    /**
     * @param keyChmToxicityRecognizedRespiratory the keyChmToxicityRecognizedRespiratory to set
     */
    public void setKeyChmToxicityRecognizedRespiratory(String keyChmToxicityRecognizedRespiratory) {
        this.keyChmToxicityRecognizedRespiratory = keyChmToxicityRecognizedRespiratory;
    }

    /**
     * @return the keyChmToxicitySuspectedRespiratory
     */
    public String getKeyChmToxicitySuspectedRespiratory() {
        return keyChmToxicitySuspectedRespiratory;
    }

    /**
     * @param keyChmToxicitySuspectedRespiratory the keyChmToxicitySuspectedRespiratory to set
     */
    public void setKeyChmToxicitySuspectedRespiratory(String keyChmToxicitySuspectedRespiratory) {
        this.keyChmToxicitySuspectedRespiratory = keyChmToxicitySuspectedRespiratory;
    }

    /**
     * @return the keyChmToxicityRecognizedSkinSense
     */
    public String getKeyChmToxicityRecognizedSkinSense() {
        return keyChmToxicityRecognizedSkinSense;
    }

    /**
     * @param keyChmToxicityRecognizedSkinSense the keyChmToxicityRecognizedSkinSense to set
     */
    public void setKeyChmToxicityRecognizedSkinSense(String keyChmToxicityRecognizedSkinSense) {
        this.keyChmToxicityRecognizedSkinSense = keyChmToxicityRecognizedSkinSense;
    }

    /**
     * @return the keyChmToxicitySuspectedSkinSense
     */
    public String getKeyChmToxicitySuspectedSkinSense() {
        return keyChmToxicitySuspectedSkinSense;
    }

    /**
     * @param keyChmToxicitySuspectedSkinSense the keyChmToxicitySuspectedSkinSense to set
     */
    public void setKeyChmToxicitySuspectedSkinSense(String keyChmToxicitySuspectedSkinSense) {
        this.keyChmToxicitySuspectedSkinSense = keyChmToxicitySuspectedSkinSense;
    }

    /**
     * @return the notApplicableString
     */
    public String getNotApplicableString() {
        return notApplicableString;
    }

    /**
     * @param notApplicableString the notApplicableString to set
     */
    public void setNotApplicableString(String notApplicableString) {
        this.notApplicableString = notApplicableString;
    }
}
