package org.jason.mapmaker2.service;

import org.jason.mapmaker2.dao.SubCodeDao;
import org.jason.mapmaker2.model.SubCode;
import org.jason.mapmaker2.model.StateCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Jason Ferguson
 */
@Service("subCodeService")
public class SubCodeServiceImpl implements SubCodeService {

    private SubCodeDao subCodeDao;

    @Autowired
    public void setSubCodeDao(SubCodeDao subCodeDao) {
        this.subCodeDao = subCodeDao;
    }

    public SubCode save(SubCode obj) {
        return subCodeDao.save(obj);
    }

    public void update(SubCode obj) {
        subCodeDao.update(obj);
    }

    public void delete(SubCode obj) {
        subCodeDao.delete(obj);
    }

    public List<SubCode> getAll() {
        return subCodeDao.getAll();
    }

    public SubCode getById(int id) {
        return subCodeDao.getById(id);
    }

    public List<SubCode> getByStateCode(StateCode stateCode) {

        SubCode subCode = new SubCode();
        subCode.setStateCode(stateCode);

        return subCodeDao.queryByExample(subCode);
    }

    public List<SubCode> getByStateCodeAndFeatureType(StateCode stateCode, String featureType) {

        SubCode example = new SubCode();
        example.setStateCode(stateCode);
        example.setSubCodeType(featureType);

        return subCodeDao.queryByExample(example);
    }

    public List<String> getUniqueSubCodeTypesByStateCode(StateCode stateCode) {
        return subCodeDao.getUniqueSubCodeTypesByStateCode(stateCode);
        //return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public List<String> getUniqueDescriptionsByFeatureType(String featureType) {
        return subCodeDao.getUniqueDescriptionsByFeatureType(featureType);
    }
}
