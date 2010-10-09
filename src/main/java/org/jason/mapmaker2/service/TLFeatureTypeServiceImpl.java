package org.jason.mapmaker2.service;

import org.jason.mapmaker2.dao.TLFeatureTypeDao;
import org.jason.mapmaker2.model.TLFeatureType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Jason Ferguson
 */
@Service("TLFeatureTypeService")
public class TLFeatureTypeServiceImpl implements TLFeatureTypeService {

    private TLFeatureTypeDao tlFeatureTypeDao;

    @Autowired
    public void setTlFeatureTypeDao(TLFeatureTypeDao tlFeatureTypeDao) {
        this.tlFeatureTypeDao = tlFeatureTypeDao;
    }

    public TLFeatureType save(TLFeatureType obj) {

        return tlFeatureTypeDao.save(obj);
    }

    public void update(TLFeatureType obj) {
        tlFeatureTypeDao.update(obj);
    }

    public void delete(TLFeatureType obj) {
        tlFeatureTypeDao.delete(obj);
    }

    public List<TLFeatureType> getAll() {
        return tlFeatureTypeDao.getAll();
    }

    public TLFeatureType getById(int id) {
        return tlFeatureTypeDao.getById(id);
    }
}
