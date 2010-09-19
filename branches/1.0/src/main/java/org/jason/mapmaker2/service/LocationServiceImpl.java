package org.jason.mapmaker2.service;

import org.jason.mapmaker2.dao.LocationDao;
import org.jason.mapmaker2.model.FeatureClass;
import org.jason.mapmaker2.model.Location;
import org.jason.mapmaker2.model.State;
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

    private StateService stateService;

    @Autowired
    public void setStateService(StateService stateService) {
        this.stateService = stateService;
    }

    private FeatureClassService featureClassService;

    @Autowired
    public void setFeatureClassService(FeatureClassService featureClassService) {
        this.featureClassService = featureClassService;
    }

    public Location save(Location obj) {
        return locationDao.save(obj);
    }

    public void update(Location obj) {
        locationDao.update(obj);
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

    public Location createLocation(Integer stateId, String name, String fipsCode, Integer featureClassId) throws ServiceException{

        Location location = new Location();

        State state = stateService.getById(stateId);
        if (state == null) {
            throw new ServiceException("No state found for given state id");
        }

        FeatureClass featureClass = featureClassService.getById(featureClassId);
        if (featureClass == null) {
            throw new ServiceException("No feature class found for given feature class id");
        }

        location.setState(state);
        location.setLocationName(name);
        location.setLocationFIPS(fipsCode);
        location.setFeatureClass(featureClass);

        return location;
    }
}
