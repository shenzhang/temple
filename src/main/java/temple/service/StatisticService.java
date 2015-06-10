package temple.service;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import temple.dao.CityDao;
import temple.dao.StatisticDao;
import temple.model.City;
import temple.model.statistic.YearResult;

import java.util.Date;
import java.util.List;
import java.util.Map;

import static com.google.common.collect.Lists.newArrayList;
import static com.google.common.collect.Maps.newHashMap;

/**
 * User: shenzhang
 * Date: 9/23/14
 * Time: 8:12 PM
 */
@Service
public class StatisticService {
    @Autowired
    private CityDao cityDao;

    @Autowired
    private StatisticDao statisticDao;

    @Transactional(readOnly = true)
    public List<City> getAllCities() {
        return cityDao.listAllCities();
    }

    @Transactional(readOnly = true)
    public int getAcquiredMembershipCount(String cityCode, Date begin, Date end) {
        return statisticDao.getAcquiredMembershipCount(begin, end, cityCode);
    }

    @Transactional(readOnly = true)
    public int getPurifiedMemberCount(String cityCode, Date begin, Date end) {
        return statisticDao.getPurifiedMemberCount(begin, end, cityCode);
    }

    @Transactional(readOnly = true)
    public int getFamilyTempleCount(String cityCode, Date begin, Date end) {
        return statisticDao.getFamilyTempleCount(begin, end, cityCode);
    }

    @Transactional(readOnly = true)
    public List<YearResult> getStatisticYearResultListForAcquisitionMember(int beginYear, int endYear) {
        List<City> cities = getAllCities();

        List<YearResult> yearResultList = newArrayList();
        for (int year = endYear; year >= beginYear; year--) {
            YearResult yearResult = new YearResult();
            yearResult.setYear(year);

            DateTime begin = new DateTime(year, 1, 1, 0, 0);
            DateTime end = begin.plusYears(1);

            Map<String, Integer> result = newHashMap();
            int total = 0;
            for (City city : cities) {
                int count = statisticDao.getAcquiredMembershipCount(begin.toDate(), end.toDate(), city.getCode());
                result.put(city.getCode(), count);
                total += count;
            }

            yearResult.setResult(result);
            yearResult.setTotal(total);

            yearResultList.add(yearResult);
        }

        return yearResultList;
    }

    @Transactional(readOnly = true)
    public List<YearResult> getStatisticYearResultListForPurifiedMember(int beginYear, int endYear) {
        List<City> cities = getAllCities();

        List<YearResult> yearResultList = newArrayList();
        for (int year = endYear; year >= beginYear; year--) {
            YearResult yearResult = new YearResult();
            yearResult.setYear(year);

            DateTime begin = new DateTime(year, 1, 1, 0, 0);
            DateTime end = begin.plusYears(1);

            Map<String, Integer> result = newHashMap();
            int total = 0;
            for (City city : cities) {
                int count = statisticDao.getPurifiedMemberCount(begin.toDate(), end.toDate(), city.getCode());
                result.put(city.getCode(), count);
                total += count;
            }

            yearResult.setResult(result);
            yearResult.setTotal(total);

            yearResultList.add(yearResult);
        }

        return yearResultList;
    }

    @Transactional(readOnly = true)
    public List<YearResult> getStatisticYearResultListForFamilyTemple(int beginYear, int endYear) {
        List<City> cities = getAllCities();

        List<YearResult> yearResultList = newArrayList();
        for (int year = endYear; year >= beginYear; year--) {
            YearResult yearResult = new YearResult();
            yearResult.setYear(year);

            DateTime begin = new DateTime(year, 1, 1, 0, 0);
            DateTime end = begin.plusYears(1);

            Map<String, Integer> result = newHashMap();
            int total = 0;
            for (City city : cities) {
                int count = statisticDao.getFamilyTempleCount(begin.toDate(), end.toDate(), city.getCode());
                result.put(city.getCode(), count);
                total += count;
            }

            yearResult.setResult(result);
            yearResult.setTotal(total);

            yearResultList.add(yearResult);
        }

        return yearResultList;
    }

    @Transactional(readOnly = true)
    public Date getEariestAcquisitionDate() {
        return statisticDao.getEariestAcquisitionDate();
    }

    @Transactional(readOnly = true)
    public Date getEariestPurificationDate() {
        return statisticDao.getEariestPurificationDate();
    }

    @Transactional(readOnly = true)
    public Date getEariestFamilyTempleDate() {
        return statisticDao.getEariestFamilyTempleDate();
    }
}
