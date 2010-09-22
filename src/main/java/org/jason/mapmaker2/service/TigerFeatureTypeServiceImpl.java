package org.jason.mapmaker2.service;

import org.jason.mapmaker2.dao.TigerFeatureTypeDao;
import org.jason.mapmaker2.model.TigerFeatureType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author TSgt Jason Ferguson
 */
@Service("tigerFeatureTypeService")
public class TigerFeatureTypeServiceImpl implements TigerFeatureTypeService {

    private TigerFeatureTypeDao tigerFeatureTypeDao;

    @Autowired
    public void setTigerFeatureTypeDao(TigerFeatureTypeDao tigerFeatureTypeDao) {
        this.tigerFeatureTypeDao = tigerFeatureTypeDao;
    }

    public TigerFeatureType save(TigerFeatureType obj) {
        return tigerFeatureTypeDao.save(obj);
    }

    public void update(TigerFeatureType obj) {
        tigerFeatureTypeDao.update(obj);
    }

    public void delete(TigerFeatureType obj) {
        tigerFeatureTypeDao.delete(obj);
    }

    public List<TigerFeatureType> getAll() {
        return tigerFeatureTypeDao.getAll();
    }

    public TigerFeatureType getById(int id) {
        return tigerFeatureTypeDao.getById(id);
    }
}
