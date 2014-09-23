package temple.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import temple.dao.CityDao;
import temple.model.City;

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

    public List<City> getAllCities() {
        return cityDao.listAllCities();
    }


}
