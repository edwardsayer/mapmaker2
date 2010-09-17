package org.jason.mapmaker2.service;

import org.jason.mapmaker2.dao.BorderPointDao;
import org.jason.mapmaker2.model.BorderPoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author TSgt Jason Ferguson
 */
@Service("borderPointService")
public class BorderPointService implements BasicServiceOperations<BorderPoint> {

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
}
