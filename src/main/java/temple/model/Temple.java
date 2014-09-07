package temple.model;

import temple.sql.annotation.Table;

/**
 * User: shenzhang
 * Date: 9/7/14
 * Time: 2:19 PM
 */
@Table("T_TEMPLE")
public class Temple {
    private String membershipAcquisitionTempleCode;
    private String membershipAcquisitionTempleName;

    public String getMembershipAcquisitionTempleCode() {
        return membershipAcquisitionTempleCode;
    }

    public void setMembershipAcquisitionTempleCode(String membershipAcquisitionTempleCode) {
        this.membershipAcquisitionTempleCode = membershipAcquisitionTempleCode;
    }

    public String getMembershipAcquisitionTempleName() {
        return membershipAcquisitionTempleName;
    }

    public void setMembershipAcquisitionTempleName(String membershipAcquisitionTempleName) {
        this.membershipAcquisitionTempleName = membershipAcquisitionTempleName;
    }
}
