package org.jason.mapmaker2.service;

import org.jason.mapmaker2.dao.CustomFeatureDao;
import org.jason.mapmaker2.model.CustomFeature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Jason Ferguson
 */
@Service("customFeatureService")
public class CustomFeatureServiceImpl implements CustomFeatureService {

    private CustomFeatureDao customFeatureDao;

    @Autowired
    public void setCustomFeatureDao(CustomFeatureDao customFeatureDao) {
        this.customFeatureDao = customFeatureDao;
    }

    public CustomFeature save(CustomFeature obj) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public void update(CustomFeature obj) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    public void delete(CustomFeature obj) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    public List<CustomFeature> getAll() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public CustomFeature getById(int id) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }
}
