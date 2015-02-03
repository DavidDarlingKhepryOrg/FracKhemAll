/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import org.apache.commons.codec.digest.DigestUtils;

/**
 *
 * @author David Darling <david.darling@khepry.com>
 */
@ManagedBean
@RequestScoped
public class DisclosureBean implements Serializable {
    
    @ManagedProperty("#{uniqueId}")
    private String uniqueId = "";
    @ManagedProperty("#{pdfSeqId}")
    private String pdfSeqId = "";
    @ManagedProperty("#{api}")
    private String api = "";
    @ManagedProperty("#{fractureDate}")
    private String fractureDate = "";
    @ManagedProperty("#{country}")
    private String country = "";
    @ManagedProperty("#{state}")
    private String state = "";
    @ManagedProperty("#{county}")
    private String county = "";
    @ManagedProperty("#{operator}")
    private String operator = "";
    @ManagedProperty("#{wellName}")
    private String wellName = "";
    @ManagedProperty("#{prodType}")
    private String prodType = "";
    @ManagedProperty("#{latitude}")
    private Double latitude = 0D;
    @ManagedProperty("#{longitude}")
    private Double longitude = 0D;
    @ManagedProperty("#{datum}")
    private String datum = "";
    @ManagedProperty("#{trueVerticalDepth}")
    private String trueVerticalDepth = "";
    @ManagedProperty("#{totalWaterVolume}")
    private String totalWaterVolume = "";
    @ManagedProperty("#{publishedDate}")
    private String publishedDate = "";
    @ManagedProperty("#{md5Digest}")
    private String md5Digest = "";
    
    private List<ChemicalBean> chemicals = new ArrayList<>();

    /**
     * Creates a new instance of DisclosureBean
     */
    public DisclosureBean() {
    }
    
    @Override
    public String toString() {
        return api + "::" + operator + "::" + wellName + "::" + uniqueId;
    }
    
    public void addChemical(ChemicalBean chemicalBean) {
        chemicals.add(chemicalBean);
    }
    
    public void clearChemicals() {
        chemicals.clear();
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
        sb.append(country);
        sb.append(joiner);
        sb.append(state);
        sb.append(joiner);
        sb.append(county);
        sb.append(joiner);
        sb.append(operator);
        sb.append(joiner);
        sb.append(wellName);
        sb.append(joiner);
        sb.append(prodType);
        sb.append(joiner);
        sb.append(latitude);
        sb.append(joiner);
        sb.append(longitude);
        sb.append(joiner);
        sb.append(datum);
        sb.append(joiner);
        sb.append(trueVerticalDepth);
        sb.append(joiner);
        sb.append(totalWaterVolume);
        sb.append(joiner);
        sb.append(publishedDate);
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
     * @return the country
     */
    public String getCountry() {
        return country;
    }

    /**
     * @param country the country to set
     */
    public void setCountry(String country) {
        this.country = country;
    }

    /**
     * @return the state
     */
    public String getState() {
        return state;
    }

    /**
     * @param state the state to set
     */
    public void setState(String state) {
        this.state = state;
    }

    /**
     * @return the county
     */
    public String getCounty() {
        return county;
    }

    /**
     * @param county the county to set
     */
    public void setCounty(String county) {
        this.county = county;
    }

    /**
     * @return the operator
     */
    public String getOperator() {
        return operator;
    }

    /**
     * @param operator the operator to set
     */
    public void setOperator(String operator) {
        this.operator = operator;
    }

    /**
     * @return the wellName
     */
    public String getWellName() {
        return wellName;
    }

    /**
     * @param wellName the wellName to set
     */
    public void setWellName(String wellName) {
        this.wellName = wellName;
    }

    /**
     * @return the prodType
     */
    public String getProdType() {
        return prodType;
    }

    /**
     * @param prodType the prodType to set
     */
    public void setProdType(String prodType) {
        this.prodType = prodType;
    }

    /**
     * @return the latitude
     */
    public Double getLatitude() {
        return latitude;
    }

    /**
     * @param latitude the latitude to set
     */
    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    /**
     * @return the longitude
     */
    public Double getLongitude() {
        return longitude;
    }

    /**
     * @param longitude the longitude to set
     */
    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    /**
     * @return the datum
     */
    public String getDatum() {
        return datum;
    }

    /**
     * @param datum the datum to set
     */
    public void setDatum(String datum) {
        this.datum = datum;
    }

    /**
     * @return the trueVerticalDepth
     */
    public String getTrueVerticalDepth() {
        return trueVerticalDepth;
    }

    /**
     * @param trueVerticalDepth the trueVerticalDepth to set
     */
    public void setTrueVerticalDepth(String trueVerticalDepth) {
        this.trueVerticalDepth = trueVerticalDepth;
    }

    /**
     * @return the totalWaterVolume
     */
    public String getTotalWaterVolume() {
        return totalWaterVolume;
    }

    /**
     * @param totalWaterVolume the totalWaterVolume to set
     */
    public void setTotalWaterVolume(String totalWaterVolume) {
        this.totalWaterVolume = totalWaterVolume;
    }

    /**
     * @return the publishedDate
     */
    public String getPublishedDate() {
        return publishedDate;
    }

    /**
     * @param publishedDate the publishedDate to set
     */
    public void setPublishedDate(String publishedDate) {
        this.publishedDate = publishedDate;
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

    /**
     * @return the chemicals
     */
    public List<ChemicalBean> getChemicals() {
        return chemicals;
    }

    /**
     * @param chemicals the chemicals to set
     */
    public void setChemicals(List<ChemicalBean> chemicals) {
        this.chemicals = chemicals;
    }
}
