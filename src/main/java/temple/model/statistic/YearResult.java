package temple.model.statistic;

import java.util.Map;

/**
 * User: shenzhang
 * Date: 9/27/14
 * Time: 1:56 PM
 */
public class YearResult {
    private int year;
    private Map<String, Integer> result;
    private int total;

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public Map<String, Integer> getResult() {
        return result;
    }

    public void setResult(Map<String, Integer> result) {
        this.result = result;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }
}
