package org.jason.mapmaker2.service;

import org.jason.mapmaker2.dao.LocationDao;
import org.jason.mapmaker2.model.Location;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Jason Ferguson
 */
@Service("locationService")
public class LocationServiceImpl implements LocationService {

    private LocationDao locationDao;

    @Autowired
    public void setLocationDao(LocationDao locationDao) {
        this.locationDao = locationDao;
    }

    public Location save(Location obj) {

        return locationDao.save(obj);
    }

    public void update(Location obj) {

        locationDao.save(obj);
    }

    public void delete(Location obj) {
        locationDao.delete(obj);
    }

    public List<Location> getAll() {
        return locationDao.getAll();
    }

    public Location getById(int id) {
        return locationDao.getById(id);
    }
}
