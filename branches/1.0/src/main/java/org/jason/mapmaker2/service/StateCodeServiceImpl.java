package org.jason.mapmaker2.service;

import org.jason.mapmaker2.dao.StateCodeDao;
import org.jason.mapmaker2.model.StateCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Jason Ferguson
 */
@Service("stateCodeService")
public class StateCodeServiceImpl implements StateCodeService {

    private StateCodeDao stateCodeDao;

    @Autowired
    public void setStateCodeDao(StateCodeDao stateCodeDao) {
        this.stateCodeDao = stateCodeDao;
    }

    public StateCode save(StateCode obj) {
        return stateCodeDao.save(obj);
    }

    public void update(StateCode obj) {
        stateCodeDao.save(obj);
    }

    public void delete(StateCode obj) {
        stateCodeDao.delete(obj);
    }

    public List<StateCode> getAll() {
        return stateCodeDao.getAll();
    }

    public StateCode getById(int id) {
        return stateCodeDao.getById(id);
    }

    public StateCode getByStateCode(Integer stateCode) {
        StateCode example = new StateCode();
        example.setStateCode(stateCode);

        List<StateCode> results = stateCodeDao.queryByExample(example);
        if (results.size() < 1) {
            return null;
        }
        return results.get(0);
    }
}
