package org.jason.mapmaker2.service;

import org.jason.mapmaker2.dao.MTFCCDao;
import org.jason.mapmaker2.model.MTFCC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Jason Ferguson
 */
@Service("MTFCCService")
public class MTFCCServiceImpl implements MTFCCService {

    private MTFCCDao mtfccDao;

    @Autowired
    public void setMtfccDao(MTFCCDao mtfccDao) {
        this.mtfccDao = mtfccDao;
    }

    public MTFCC save(MTFCC obj) {
        return mtfccDao.save(obj);
    }

    public void update(MTFCC obj) {
        mtfccDao.update(obj);
    }

    public void delete(MTFCC obj) {
        mtfccDao.delete(obj);
    }

    public List<MTFCC> getAll() {
        return mtfccDao.getAll();
    }

    public MTFCC getById(int id) {
        return mtfccDao.getById(id);
    }
}
