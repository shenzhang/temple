package temple.model;

/**
 * User: shenzhang
 * Date: 8/21/14
 * Time: 9:52 PM
 */
public class SearchMemberInfo {
    private String name;
    private String introducerName;
    private Integer acquisitionYear;

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

    public Integer getAcquisitionYear() {
        return acquisitionYear;
    }

    public void setAcquisitionYear(Integer acquisitionYear) {
        this.acquisitionYear = acquisitionYear;
    }
}
