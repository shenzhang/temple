package temple.controller;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import temple.model.City;
import temple.model.statistic.YearResult;
import temple.service.StatisticService;

import java.util.List;
import java.util.Map;

import static com.google.common.collect.Lists.newArrayList;
import static com.google.common.collect.Maps.newHashMap;

/**
 * User: shenzhang
 * Date: 9/23/14
 * Time: 8:11 PM
 */
@Controller
@RequestMapping("/statistic")
public class StatisticController {
    @Autowired
    private StatisticService statisticService;

    @ModelAttribute("cities")
    public List<City> getAllCities() {
        return statisticService.getAllCities();
    }

    @RequestMapping(value = "/memberInTotal", method = RequestMethod.GET)
    public String memberInTotal(Model model) {
        List<City> cities = getAllCities();

        List<Map<String, Integer>> resultList = newArrayList();

        // No. of people acquired membership
        Map<String, Integer> result = newHashMap();
        resultList.add(result);
        for (City city : cities) {
            String cityCode = city.getMembershipAcquisitionCityCode();
            result.put(cityCode, statisticService.getAcquiredMembershipCount(cityCode, null, null));
        }

        // No. of purified members
        result = newHashMap();
        resultList.add(result);
        for (City city : cities) {
            String cityCode = city.getMembershipAcquisitionCityCode();
            result.put(cityCode, statisticService.getPurifiedMemberCount(cityCode, null, null));
        }

        // No. of member with family temple
        result = newHashMap();
        resultList.add(result);
        for (City city : cities) {
            String cityCode = city.getMembershipAcquisitionCityCode();
            result.put(cityCode, statisticService.getFamilyTempleCount(cityCode, null, null));
        }

        caculateTotal(resultList, model);

        model.addAttribute("results", resultList);

        return "memberStatistic";
    }

    @RequestMapping(value = "/memberByYear", method = RequestMethod.GET)
    public String memberByYear() {
        return "memberStatisticForYear";
    }

    @RequestMapping(value = "/memberByYear/{year}", method = RequestMethod.GET)
    public String memberByYear(@PathVariable("year") int year, Model model) {
        model.addAttribute("year", year);
        List<City> cities = getAllCities();

        List<Map<String, Integer>> resultList = newArrayList();

        DateTime begin = new DateTime(year, 1, 1, 0, 0);
        DateTime end = begin.plusYears(1);

        // No. of people acquired membership
        Map<String, Integer> result = newHashMap();
        resultList.add(result);
        for (City city : cities) {
            String cityCode = city.getMembershipAcquisitionCityCode();
            result.put(cityCode, statisticService.getAcquiredMembershipCount(cityCode, begin.toDate(), end.toDate()));
        }

        // No. of purified members
        result = newHashMap();
        resultList.add(result);
        for (City city : cities) {
            String cityCode = city.getMembershipAcquisitionCityCode();
            result.put(cityCode, statisticService.getPurifiedMemberCount(cityCode, begin.toDate(), end.toDate()));
        }

        // No. of member with family temple
        result = newHashMap();
        resultList.add(result);
        for (City city : cities) {
            String cityCode = city.getMembershipAcquisitionCityCode();
            result.put(cityCode, statisticService.getFamilyTempleCount(cityCode, begin.toDate(), end.toDate()));
        }

        caculateTotal(resultList, model);

        model.addAttribute("results", resultList);

        return "memberStatisticForYear";
    }

    @RequestMapping("/acquisition")
    public String acquisition(Model model) {
        int beginYear = 1999;
        int endYear = DateTime.now().getYear();

        List<YearResult> yearResultList = statisticService.getStatisticYearResultListForAcquisitionMember(beginYear, endYear);
        model.addAttribute("results", yearResultList);

        Map<String, Integer> cityTotal = newHashMap();
        int totalTotal = 0;
        List<City> cities = getAllCities();
        for (City city : cities) {
            int total = 0;
            for (YearResult result : yearResultList) {
                total += result.getResult().get(city.getCode());
            }

            cityTotal.put(city.getCode(), total);
            totalTotal += total;
        }

        model.addAttribute("cityTotal", cityTotal);
        model.addAttribute("total", totalTotal);

        return "acquisition";
    }

    @RequestMapping("/purified")
    public String purified(Model model) {
        int beginYear = 1999;
        int endYear = DateTime.now().getYear();

        List<YearResult> yearResultList = statisticService.getStatisticYearResultListForPurifiedMember(beginYear, endYear);
        model.addAttribute("results", yearResultList);

        Map<String, Integer> cityTotal = newHashMap();
        int totalTotal = 0;
        List<City> cities = getAllCities();
        for (City city : cities) {
            int total = 0;
            for (YearResult result : yearResultList) {
                total += result.getResult().get(city.getCode());
            }

            cityTotal.put(city.getCode(), total);
            totalTotal += total;
        }

        model.addAttribute("cityTotal", cityTotal);
        model.addAttribute("total", totalTotal);

        return "purified";
    }

    @RequestMapping("/familyTemple")
    public String familyTemple(Model model) {
        int beginYear = 1999;
        int endYear = DateTime.now().getYear();

        List<YearResult> yearResultList = statisticService.getStatisticYearResultListForFamilyTemple(beginYear, endYear);
        model.addAttribute("results", yearResultList);

        Map<String, Integer> cityTotal = newHashMap();
        int totalTotal = 0;
        List<City> cities = getAllCities();
        for (City city : cities) {
            int total = 0;
            for (YearResult result : yearResultList) {
                total += result.getResult().get(city.getCode());
            }

            cityTotal.put(city.getCode(), total);
            totalTotal += total;
        }

        model.addAttribute("cityTotal", cityTotal);
        model.addAttribute("total", totalTotal);

        return "familyTemple";
    }

    private void caculateTotal(List<Map<String, Integer>> results, Model model) {
        if (results.isEmpty()) {
            return;
        }

        Map<String, Integer> firstRecord = results.get(0);
        if (firstRecord.isEmpty()) {
            return;
        }

        Map<String, Integer> total = newHashMap();
        for (String key : firstRecord.keySet()) {
            int totalValue = 0;
            for (Map<String, Integer> result : results) {
                totalValue += result.get(key);
            }

            total.put(key, totalValue);
        }

        results.add(total);

        // row total
        List<Integer> rowTotalList = newArrayList();
        for (Map<String, Integer> result : results) {
            int value = 0;
            for (Integer i : result.values()) {
                if (i != null) {
                    value += i;
                }
            }

            rowTotalList.add(value);
        }
        model.addAttribute("rowTotalList", rowTotalList);
    }
}
