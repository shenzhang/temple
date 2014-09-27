package temple.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import temple.dao.CityDao;
import temple.dao.StatisticDao;
import temple.model.City;

import java.util.Date;
import java.util.List;

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

    public int getFamilyTempleCound(String cityCode, Date begin, Date end) {
        return statisticDao.getFamilyTempleCound(begin, end, cityCode);
    }
}
