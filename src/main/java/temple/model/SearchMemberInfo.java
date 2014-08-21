package temple.model;

import java.util.Date;

/**
 * User: shenzhang
 * Date: 8/21/14
 * Time: 9:52 PM
 */
public class SearchMemberInfo {
    private String name;
    private String introducerName;
    private Date acquisitionDate;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIntroducerName() {
        return introducerName;
    }

    public void setIntroducerName(String introducerName) {
        this.introducerName = introducerName;
    }

    public Date getAcquisitionDate() {
        return acquisitionDate;
    }

    public void setAcquisitionDate(Date acquisitionDate) {
        this.acquisitionDate = acquisitionDate;
    }
}
