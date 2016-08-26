package beans;

import crypto.PbeEncryption;
import java.io.Serializable;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author Khepry Quixote <fracking.analysis@gmail.com>
 */
@ManagedBean
@SessionScoped
public class SettingsSessionBean implements Serializable {
    
    private String jdbcMySQL5ConnString = "";
    private String jdbcMySQL5DriverName = "";
    private String jdbcSQLiteConnString = "";
    private String jdbcSQLiteDriverName = "";

    private String jdbcConnString = "";
    private String jdbcDriverName = "";
    private String jdbcUID = "";
    private String jdbcPWD = "";
    
    private int pbeIterations = 0;
    private String pbeSalt = ""; // must be 8 characters
    
    private String clientIpAddress = "";
    private String hostIpAddress = "";
    private String clientUserName = "";
    
    private String errorMsg = "";
    
    private boolean initialized = false;

    /**
     * Creates a new instance of SettingsSessionBean
     */
    public SettingsSessionBean() {
    }

    public final void initialize(
            HttpServletRequest request,
            ServletContext servletContext) {
        errorMsg = "";
        clientIpAddress = request.getRemoteAddr();
        hostIpAddress = request.getRemoteHost();
        clientUserName = request.getUserPrincipal() == null ? "" : request.getUserPrincipal().getName();
        getContextParameters(servletContext);
        PbeEncryption passwordEncryptionService = null;
        try {
            if (this.jdbcPWD.startsWith("***")) {
                passwordEncryptionService = new PbeEncryption();
                String pbeKey = passwordEncryptionService.getNewKey();
                String pbePwd = this.jdbcPWD.substring(3);
                this.jdbcPWD = pbePwd;
                passwordEncryptionService = new PbeEncryption(pbeKey, pbeIterations, pbeSalt);
                pbePwd = passwordEncryptionService.encrypt(pbePwd);
            } else {
                String[] pieces = this.jdbcPWD.split("\t");
                String pbeKey = this.jdbcPWD.split("\t")[0];
                passwordEncryptionService = new PbeEncryption(new String(pbeKey), pbeIterations, pbeSalt);
                if (pieces.length > 1) {
                    String pbePwd = this.jdbcPWD.split("\t")[1];
                    this.jdbcPWD = passwordEncryptionService.decrypt(pbePwd);
                }
            }
            initialized = true;
        } catch (IllegalBlockSizeException ex) {
            errorMsg = ex.getLocalizedMessage();
            Logger.getLogger(SettingsSessionBean.class.getName()).log(Level.SEVERE, null, ex);
        } catch (BadPaddingException ex) {
            errorMsg = ex.getLocalizedMessage();
            Logger.getLogger(SettingsSessionBean.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NoSuchAlgorithmException ex) {
            errorMsg = ex.getLocalizedMessage();
            Logger.getLogger(SettingsSessionBean.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InvalidKeySpecException ex) {
            errorMsg = ex.getLocalizedMessage();
            Logger.getLogger(SettingsSessionBean.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NoSuchPaddingException ex) {
            errorMsg = ex.getLocalizedMessage();
            Logger.getLogger(SettingsSessionBean.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InvalidKeyException ex) {
            errorMsg = ex.getLocalizedMessage();
            Logger.getLogger(SettingsSessionBean.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InvalidAlgorithmParameterException ex) {
            errorMsg = ex.getLocalizedMessage();
            Logger.getLogger(SettingsSessionBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void getContextParameters(ServletContext servletContext) {
        this.jdbcMySQL5ConnString = servletContext.getInitParameter("jdbcMySQL5ConnString").trim();
        this.jdbcMySQL5DriverName = servletContext.getInitParameter("jdbcMySQL5DriverName").trim();
        this.jdbcSQLiteConnString = servletContext.getInitParameter("jdbcSQLiteConnString").trim();
        this.jdbcSQLiteDriverName = servletContext.getInitParameter("jdbcSQLiteDriverName").trim();
        this.jdbcDriverName = servletContext.getInitParameter("jdbcDriverName").trim();
        this.jdbcConnString = !jdbcDriverName.equals(jdbcSQLiteDriverName) ? jdbcMySQL5ConnString : jdbcSQLiteConnString;
        this.jdbcUID = servletContext.getInitParameter("jdbcUID").trim();
        this.jdbcPWD = servletContext.getInitParameter("jdbcPWD").trim();
        this.pbeIterations = Integer.parseInt(servletContext.getInitParameter("pbeIterations").trim());
        this.pbeSalt = servletContext.getInitParameter("pbeSalt").trim();
    }
    
    @Override
    public String toString() {
        return jdbcDriverName + ";" + jdbcConnString + ";" + jdbcUID;
    }

    /**
     * @return the jdbcDriverName
     */
    public String getJdbcDriverName() {
        return jdbcDriverName;
    }

    /**
     * @param jdbcDriverName the jdbcDriverName to set
     */
    public void setJdbcDriverName(String jdbcDriverName) {
        this.jdbcDriverName = jdbcDriverName;
    }

    /**
     * @return the jdbcConnString
     */
    public String getJdbcConnString() {
        return jdbcConnString;
    }

    /**
     * @param jdbcConnString the jdbcConnString to set
     */
    public void setJdbcConnString(String jdbcConnString) {
        this.jdbcConnString = jdbcConnString;
    }

    /**
     * @return the jdbcUID
     */
    public String getJdbcUID() {
        return jdbcUID;
    }

    /**
     * @param jdbcUID the jdbcUID to set
     */
    public void setJdbcUID(String jdbcUID) {
        this.jdbcUID = jdbcUID;
    }

    /**
     * @return the jdbcPWD
     */
    public String getJdbcPWD() {
        return jdbcPWD;
    }

    /**
     * @param jdbcPWD the jdbcPWD to set
     */
    public void setJdbcPWD(String jdbcPWD) {
        this.jdbcPWD = jdbcPWD;
    }

    /**
     * @return the initialized
     */
    public boolean isInitialized() {
        return initialized;
    }

    /**
     * @param initialized the initialized to set
     */
    public void setInitialized(boolean initialized) {
        this.initialized = initialized;
    }

    /**
     * @return the errorMsg
     */
    public String getErrorMsg() {
        return errorMsg;
    }

    /**
     * @param errorMsg the errorMsg to set
     */
    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    /**
     * @return the clientIpAddress
     */
    public String getClientIpAddress() {
        return clientIpAddress;
    }

    /**
     * @param clientIpAddress the clientIpAddress to set
     */
    public void setClientIpAddress(String clientIpAddress) {
        this.clientIpAddress = clientIpAddress;
    }

    /**
     * @return the hostIpAddress
     */
    public String getHostIpAddress() {
        return hostIpAddress;
    }

    /**
     * @param hostIpAddress the hostIpAddress to set
     */
    public void setHostIpAddress(String hostIpAddress) {
        this.hostIpAddress = hostIpAddress;
    }

    /**
     * @return the clientUserName
     */
    public String getClientUserName() {
        return clientUserName;
    }

    /**
     * @param clientUserName the clientUserName to set
     */
    public void setClientUserName(String clientUserName) {
        this.clientUserName = clientUserName;
    }

    /**
     * @return the jdbcMySQL5ConnString
     */
    public String getJdbcMySQL5ConnString() {
        return jdbcMySQL5ConnString;
    }

    /**
     * @param jdbcMySQL5ConnString the jdbcMySQL5ConnString to set
     */
    public void setJdbcMySQL5ConnString(String jdbcMySQL5ConnString) {
        this.jdbcMySQL5ConnString = jdbcMySQL5ConnString;
    }

    /**
     * @return the jdbcMySQL5DriverName
     */
    public String getJdbcMySQL5DriverName() {
        return jdbcMySQL5DriverName;
    }

    /**
     * @param jdbcMySQL5DriverName the jdbcMySQL5DriverName to set
     */
    public void setJdbcMySQL5DriverName(String jdbcMySQL5DriverName) {
        this.jdbcMySQL5DriverName = jdbcMySQL5DriverName;
    }

    /**
     * @return the jdbcSQLiteConnString
     */
    public String getJdbcSQLiteConnString() {
        return jdbcSQLiteConnString;
    }

    /**
     * @param jdbcSQLiteConnString the jdbcSQLiteConnString to set
     */
    public void setJdbcSQLiteConnString(String jdbcSQLiteConnString) {
        this.jdbcSQLiteConnString = jdbcSQLiteConnString;
    }

    /**
     * @return the jdbcSQLiteDriverName
     */
    public String getJdbcSQLiteDriverName() {
        return jdbcSQLiteDriverName;
    }

    /**
     * @param jdbcSQLiteDriverName the jdbcSQLiteDriverName to set
     */
    public void setJdbcSQLiteDriverName(String jdbcSQLiteDriverName) {
        this.jdbcSQLiteDriverName = jdbcSQLiteDriverName;
    }
}
