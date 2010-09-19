package org.jason.mapmaker2.service;

import org.jason.mapmaker2.dao.StateDao;
import org.jason.mapmaker2.model.State;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Jason Ferguson
 */
@Service("stateService")
public class StateServiceImpl implements StateService {

    private StateDao stateDao;

    @Autowired
    public void setStateDao(StateDao stateDao) {
        this.stateDao = stateDao;
    }

    public State save(State obj) {

        return stateDao.save(obj);
    }

    public void update(State obj) {
        stateDao.update(obj);
    }

    public void delete(State obj) {
        stateDao.delete(obj);
    }

    public List<State> getAll() {
        return stateDao.getAll();
    }

    public State getById(int id) {
        return stateDao.getById(id);
    }

    public State getByName(String name) {

        State example = new State();
        example.setStateName(name);

        List<State> results = stateDao.queryByExample(example);

        if (results.size() > 0) {
        return results.get(0);
        }

        return null;

    }
}
