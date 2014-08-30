package temple.model;

import temple.sql.annotation.Table;

import java.util.Date;

/**
 * User: shenzhang
 * Date: 8/23/14
 * Time: 7:36 PM
 */
@Table("T_MEMBER")
public class Member {
    private int id;
    private String lastName;
    private String firstName;
    private String chineseLastName;
    private String chineseFirstName;
    private String introducerName;
    private Date membershipAcquisitionDate;
    private String membershipAcquisitionTempleCode;
    private String membershipAcquisitionCityCode;
    private Date memberPurificationDate;
    private Date memberFamiltyTempleDate;
    private String masterName;
    private String guarantorName;
    private int groupNumber;
    private Date dob;
    private int lastUpdateUserId;
    private Date updateDate;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getChineseLastName() {
        return chineseLastName;
    }

    public void setChineseLastName(String chineseLastName) {
        this.chineseLastName = chineseLastName;
    }

    public String getChineseFirstName() {
        return chineseFirstName;
    }

    public void setChineseFirstName(String chineseFirstName) {
        this.chineseFirstName = chineseFirstName;
    }

    public String getIntroducerName() {
        return introducerName;
    }

    public void setIntroducerName(String introducerName) {
        this.introducerName = introducerName;
    }

    public Date getMembershipAcquisitionDate() {
        return membershipAcquisitionDate;
    }

    public void setMembershipAcquisitionDate(Date membershipAcquisitionDate) {
        this.membershipAcquisitionDate = membershipAcquisitionDate;
    }

    public String getMembershipAcquisitionTempleCode() {
        return membershipAcquisitionTempleCode;
    }

    public void setMembershipAcquisitionTempleCode(String membershipAcquisitionTempleCode) {
        this.membershipAcquisitionTempleCode = membershipAcquisitionTempleCode;
    }

    public String getMembershipAcquisitionCityCode() {
        return membershipAcquisitionCityCode;
    }

    public void setMembershipAcquisitionCityCode(String membershipAcquisitionCityCode) {
        this.membershipAcquisitionCityCode = membershipAcquisitionCityCode;
    }

    public Date getMemberPurificationDate() {
        return memberPurificationDate;
    }

    public void setMemberPurificationDate(Date memberPurificationDate) {
        this.memberPurificationDate = memberPurificationDate;
    }

    public Date getMemberFamiltyTempleDate() {
        return memberFamiltyTempleDate;
    }

    public void setMemberFamiltyTempleDate(Date memberFamiltyTempleDate) {
        this.memberFamiltyTempleDate = memberFamiltyTempleDate;
    }

    public String getMasterName() {
        return masterName;
    }

    public void setMasterName(String masterName) {
        this.masterName = masterName;
    }

    public String getGuarantorName() {
        return guarantorName;
    }

    public void setGuarantorName(String guarantorName) {
        this.guarantorName = guarantorName;
    }

    public int getGroupNumber() {
        return groupNumber;
    }

    public void setGroupNumber(int groupNumber) {
        this.groupNumber = groupNumber;
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public int getLastUpdateUserId() {
        return lastUpdateUserId;
    }

    public void setLastUpdateUserId(int lastUpdateUserId) {
        this.lastUpdateUserId = lastUpdateUserId;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }
}
