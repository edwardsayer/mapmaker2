package org.jason.mapmaker2.service;

import org.jason.mapmaker2.dao.BorderPointDao;
import org.jason.mapmaker2.model.BorderPoint;
import org.jason.mapmaker2.model.StateCode;
import org.jason.mapmaker2.model.SubCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.List;

/**
 * @author Jason Ferguson
 */
@Service("borderPointService")
public class BorderPointServiceImpl implements BorderPointService {

    private BorderPointDao borderPointDao;

    @Autowired
    public void setBorderPointDao(BorderPointDao borderPointDao) {
        this.borderPointDao = borderPointDao;
    }

    public BorderPoint save(BorderPoint obj) {
        return borderPointDao.save(obj);
    }

    public void update(BorderPoint obj) {
        borderPointDao.update(obj);
    }

    public void delete(BorderPoint obj) {
        borderPointDao.delete(obj);
    }

    public List<BorderPoint> getAll() {
        return borderPointDao.getAll();
    }

    public BorderPoint getById(int id) {
        return borderPointDao.getById(id);
    }

    @Transactional
    public void saveAll(Collection<BorderPoint> bpCollection) {
        borderPointDao.saveAll(bpCollection);
    }

    public List<BorderPoint> getByStateCodeAndSubCode(StateCode stateCode, SubCode subCode) {

//        BorderPoint example = new BorderPoint();
//        example.setStateCode(stateCode);
//        example.setSubCode(subCode);
//
//        return borderPointDao.queryByExample(example);

        return borderPointDao.getByStateCodeAndSubCode(stateCode, subCode);
    }

    public Float getMinimumLatitude(StateCode stateCode, SubCode subCode) {

        return borderPointDao.getMinimumLatitude(stateCode, subCode);
    }

    public Float getMaximumLatitude(StateCode stateCode, SubCode subCode) {
        return borderPointDao.getMaximumLatitude(stateCode, subCode);
    }

    public Float getMinimumLongitude(StateCode stateCode, SubCode subCode) {
        return borderPointDao.getMinimumLongitude(stateCode, subCode);
    }

    public Float getMaximumLongitude(StateCode stateCode, SubCode subCode) {
        return borderPointDao.getMaximumLongitude(stateCode, subCode);
    }
}
