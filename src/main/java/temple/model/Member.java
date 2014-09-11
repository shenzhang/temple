package temple.model;

import temple.sql.annotation.Table;
import temple.util.lunar.Lunar;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * User: shenzhang
 * Date: 8/23/14
 * Time: 7:36 PM
 */
@Table("T_MEMBER")
public class Member {
    private Integer id;
    @Size(message = "Last Name is required", min = 1)
    private String lastName;
    @Size(message = "First Name is required", min = 1)
    private String firstName;
    @Size(message = "Chinese Last Name is required", min = 1)
    private String chineseLastName;
    @Size(message = "Chinese First Name is required", min = 1)
    private String chineseFirstName;
    @Size(message = "Introducer Name is required", min = 1)
    private String introducerName;
    @NotNull(message = "Membership Acquisition Date is required")
    private Date membershipAcquisitionDate;
    @Size(message = "Membership Acquisition Temple is required", min = 1)
    private String membershipAcquisitionTempleCode;
    private String membershipAcquisitionCityCode;
    private Date memberPurificationDate;
    private Date memberFamilyTempleDate;
    @Size(message = "Master Name is required", min = 1)
    private String masterName;
    @Size(message = "Guarantor Name is required", min = 1)
    private String guarantorName;
    @NotNull(message = "Group Number is required")
    @Min(0)
    private Integer groupNumber;
    @NotNull(message = "DOB is required")
    private Date dob;
    private int lastUpdateUserId;
    private Date lastUpdateDate;

    // member contact
    private MemberContact memberContact;
    // member notes
    private List<MemberNote> memberNotes;
    // last modify user
    private User lastModifyUser;
    // acquisition temple
    private Temple acquisitionTemple;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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

    public String getLunarDate() {
        if (membershipAcquisitionDate == null) {
            return null;
        }

        Calendar now = Calendar.getInstance();
        now.setTime(membershipAcquisitionDate);
        Lunar lunar = new Lunar(now);
        return lunar.toString();
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

    public Date getMemberFamilyTempleDate() {
        return memberFamilyTempleDate;
    }

    public void setMemberFamilyTempleDate(Date memberFamilyTempleDate) {
        this.memberFamilyTempleDate = memberFamilyTempleDate;
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

    public Integer getGroupNumber() {
        return groupNumber;
    }

    public void setGroupNumber(Integer groupNumber) {
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

    public Date getLastUpdateDate() {
        return lastUpdateDate;
    }

    public void setLastUpdateDate(Date lastUpdateDate) {
        this.lastUpdateDate = lastUpdateDate;
    }

    public MemberContact getMemberContact() {
        return memberContact;
    }

    public void setMemberContact(MemberContact memberContact) {
        this.memberContact = memberContact;
    }

    public List<MemberNote> getMemberNotes() {
        return memberNotes;
    }

    public void setMemberNotes(List<MemberNote> memberNotes) {
        this.memberNotes = memberNotes;
    }

    public User getLastModifyUser() {
        return lastModifyUser;
    }

    public void setLastModifyUser(User lastModifyUser) {
        this.lastModifyUser = lastModifyUser;
    }

    public Temple getAcquisitionTemple() {
        return acquisitionTemple;
    }

    public void setAcquisitionTemple(Temple acquisitionTemple) {
        this.acquisitionTemple = acquisitionTemple;
    }
}
