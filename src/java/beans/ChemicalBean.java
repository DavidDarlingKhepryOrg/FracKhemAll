package beans;

import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import org.apache.commons.codec.digest.DigestUtils;

/**
 *
 * @author Khepry Quixote <fracking.analysis@gmail.com>
 */
@ManagedBean
@RequestScoped
public class ChemicalBean implements Serializable {
    
    @ManagedProperty("#{uniqueId}")
    private String uniqueId = "";
    @ManagedProperty("#{pdfSeqId}")
    private String pdfSeqId = "";
    @ManagedProperty("#{api}")
    private String api = "";
    @ManagedProperty("#{fractureDate}")
    private String fractureDate = "";
    @ManagedProperty("#{row}")
    private String row = "";
    @ManagedProperty("#{tradeName}")
    private String tradeName = "";
    @ManagedProperty("#{supplier}")
    private String supplier = "";
    @ManagedProperty("#{purpose}")
    private String purpose = "";
    @ManagedProperty("#{ingredients}")
    private String ingredients = "";
    @ManagedProperty("#{casNumber}")
    private String casNumber = "";
    @ManagedProperty("#{additiveConcentration}")
    private String additiveConcentration = "";
    @ManagedProperty("#{hfFluidConcentration}")
    private String hfFluidConcentration = "";
    @ManagedProperty("#{comments}")
    private String comments = "";
    @ManagedProperty("#{casType}")
    private String casType = "";
    @ManagedProperty("#{toxRecognized}")
    private String toxRecognized = "";
    @ManagedProperty("#{toxSuspected}")
    private String toxSuspected = "";
    @ManagedProperty("#{md5Digest}")
    private String md5Digest = "";

    /**
     * Creates a new instance of DisclosureBean
     */
    public ChemicalBean() {
    }
    
    @Override
    public String toString() {
        return api + "::" + row + "::" + casNumber + "::" + tradeName;
    }
    
    public String calcMd5DigestString(String value) {
        return DigestUtils.md5Hex(value);
    }
    
    public String calcMd5DigestOfThis(String joiner) {
        StringBuilder sb = new StringBuilder();
        sb.append(pdfSeqId);
        sb.append(joiner);
        sb.append(api);
        sb.append(joiner);
        sb.append(fractureDate);
        sb.append(joiner);
        sb.append(row);
        sb.append(joiner);
        sb.append(tradeName);
        sb.append(joiner);
        sb.append(supplier);
        sb.append(joiner);
        sb.append(purpose);
        sb.append(joiner);
        sb.append(ingredients);
        sb.append(joiner);
        sb.append(casNumber);
        sb.append(joiner);
        sb.append(additiveConcentration);
        sb.append(joiner);
        sb.append(hfFluidConcentration);
        sb.append(joiner);
        sb.append(comments);
        sb.append(joiner);
        sb.append(casType);
        return DigestUtils.md5Hex(sb.toString());
    }


    /**
     * @return the uniqueId
     */
    public String getUniqueId() {
        return uniqueId;
    }

    /**
     * @param uniqueId the uniqueId to set
     */
    public void setUniqueId(String uniqueId) {
        this.uniqueId = uniqueId;
    }

    /**
     * @return the pdfSeqId
     */
    public String getPdfSeqId() {
        return pdfSeqId;
    }

    /**
     * @param pdfSeqId the pdfSeqId to set
     */
    public void setPdfSeqId(String pdfSeqId) {
        this.pdfSeqId = pdfSeqId;
    }

    /**
     * @return the api
     */
    public String getApi() {
        return api;
    }

    /**
     * @param api the api to set
     */
    public void setApi(String api) {
        this.api = api;
    }

    /**
     * @return the fractureDate
     */
    public String getFractureDate() {
        return fractureDate;
    }

    /**
     * @param fractureDate the fractureDate to set
     */
    public void setFractureDate(String fractureDate) {
        this.fractureDate = fractureDate;
    }

    /**
     * @return the row
     */
    public String getRow() {
        return row;
    }

    /**
     * @param row the row to set
     */
    public void setRow(String row) {
        this.row = row;
    }

    /**
     * @return the tradeName
     */
    public String getTradeName() {
        return tradeName;
    }

    /**
     * @param tradeName the tradeName to set
     */
    public void setTradeName(String tradeName) {
        this.tradeName = tradeName;
    }

    /**
     * @return the supplier
     */
    public String getSupplier() {
        return supplier;
    }

    /**
     * @param supplier the supplier to set
     */
    public void setSupplier(String supplier) {
        this.supplier = supplier;
    }

    /**
     * @return the purpose
     */
    public String getPurpose() {
        return purpose;
    }

    /**
     * @param purpose the purpose to set
     */
    public void setPurpose(String purpose) {
        this.purpose = purpose;
    }

    /**
     * @return the ingredients
     */
    public String getIngredients() {
        return ingredients;
    }

    /**
     * @param ingredients the ingredients to set
     */
    public void setIngredients(String ingredients) {
        this.ingredients = ingredients;
    }

    /**
     * @return the casNumber
     */
    public String getCasNumber() {
        return casNumber;
    }

    /**
     * @param casNumber the casNumber to set
     */
    public void setCasNumber(String casNumber) {
        this.casNumber = casNumber;
    }

    /**
     * @return the additiveConcentration
     */
    public String getAdditiveConcentration() {
        return additiveConcentration;
    }

    /**
     * @param additiveConcentration the additiveConcentration to set
     */
    public void setAdditiveConcentration(String additiveConcentration) {
        this.additiveConcentration = additiveConcentration;
    }

    /**
     * @return the hfFluidConcentration
     */
    public String getHfFluidConcentration() {
        return hfFluidConcentration;
    }

    /**
     * @param hfFluidConcentration the hfFluidConcentration to set
     */
    public void setHfFluidConcentration(String hfFluidConcentration) {
        this.hfFluidConcentration = hfFluidConcentration;
    }

    /**
     * @return the comments
     */
    public String getComments() {
        return comments;
    }

    /**
     * @param comments the comments to set
     */
    public void setComments(String comments) {
        this.comments = comments;
    }

    /**
     * @return the casType
     */
    public String getCasType() {
        return casType;
    }

    /**
     * @param casType the casType to set
     */
    public void setCasType(String casType) {
        this.casType = casType;
    }

    /**
     * @return the toxRecognized
     */
    public String getToxRecognized() {
        return toxRecognized.replace(",", ", ");
    }

    /**
     * @param toxRecognized the toxRecognized to set
     */
    public void setToxRecognized(String toxRecognized) {
        this.toxRecognized = toxRecognized;
    }

    /**
     * @return the toxSuspected
     */
    public String getToxSuspected() {
        return toxSuspected.replace(",", ", ");
    }

    /**
     * @param toxSuspected the toxSuspected to set
     */
    public void setToxSuspected(String toxSuspected) {
        this.toxSuspected = toxSuspected;
    }

    /**
     * @return the md5Digest
     */
    public String getMd5Digest() {
        return md5Digest;
    }

    /**
     * @param md5Digest the md5Digest to set
     */
    public void setMd5Digest(String md5Digest) {
        this.md5Digest = md5Digest;
    }
}
