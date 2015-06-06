package temple.model;

/**
 * User: shenzhang
 * Date: 9/7/14
 * Time: 2:37 PM
 */
public class City {
    private String membershipAcquisitionCityCode;
    private String membershipAcquisitionCityName;

    public String getMembershipAcquisitionCityCode() {
        return membershipAcquisitionCityCode;
    }

    public void setMembershipAcquisitionCityCode(String membershipAcquisitionCityCode) {
        this.membershipAcquisitionCityCode = membershipAcquisitionCityCode;
    }

    public String getMembershipAcquisitionCityName() {
        return membershipAcquisitionCityName;
    }

    public void setMembershipAcquisitionCityName(String membershipAcquisitionCityName) {
        this.membershipAcquisitionCityName = membershipAcquisitionCityName;
    }

    public String getCode() {
        return membershipAcquisitionCityCode;
    }

    public String getName() {
        return membershipAcquisitionCityName;
    }
}
