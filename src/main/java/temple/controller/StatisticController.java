package temple.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import temple.model.City;
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

        List<Map<String, Integer>> results = newArrayList();

        for (int i = 0; i < 3; i++) {
            Map<String, Integer> result = newHashMap();
            results.add(result);

            for (City city : cities) {
                result.put(city.getMembershipAcquisitionCityCode(), i);
            }
        }

        caculateTotal(results);
        model.addAttribute("results", results);

        return "memberStatistic";
    }

    @RequestMapping(value = "/memberByYear", method = RequestMethod.GET)
    public String memberByYear() {
        return "memberStatisticForYear";
    }

    @RequestMapping(value = "/memberByYear/{year}", method = RequestMethod.GET)
    public String memberByYear(@PathVariable("year") int year, Model model) {
        List<City> cities = getAllCities();

        List<Map<String, Integer>> results = newArrayList();

        model.addAttribute("results", results);

        return "memberStatisticForYear";
    }

    private void caculateTotal(List<Map<String, Integer>> results) {
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
    }
}
