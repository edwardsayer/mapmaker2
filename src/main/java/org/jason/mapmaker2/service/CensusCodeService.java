package org.jason.mapmaker2.service;

import org.jason.mapmaker2.dao.CensusCodeDao;
import org.jason.mapmaker2.model.CensusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Jason Ferguson
 */
@Service("censusCodeService")
public class CensusCodeService implements BasicServiceOperations<CensusCode> {

    private CensusCodeDao censusCodeDao;

    @Autowired
    public void setCensusCodeDao(CensusCodeDao censusCodeDao) {
        this.censusCodeDao = censusCodeDao;
    }

    public CensusCode save(CensusCode obj) {

        CensusCode result = censusCodeDao.save(obj);
        return result;
    }

    public void update(CensusCode obj) {

        censusCodeDao.update(obj);
    }

    public void delete(CensusCode obj) {

        censusCodeDao.delete(obj);
    }

    public List<CensusCode> getAll() {

        return censusCodeDao.getAll();
    }

    public CensusCode getById(int id) {

        return censusCodeDao.getById(id);
    }
}
