package org.jason.mapmaker2.service;

import org.jason.mapmaker2.dao.SubCodeDao;
import org.jason.mapmaker2.model.StateCode;
import org.jason.mapmaker2.model.SubCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Jason Ferguson
 */
@Service("subCodeService")
public class SubCodeServiceImpl implements SubCodeService {

    private StateCodeService stateCodeService;

    @Autowired
    public void setStateCodeService(StateCodeService stateCodeService) {
        this.stateCodeService = stateCodeService;
    }

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

    public List<SubCode> getByStateCodeAndFeatureType(Integer stateId, String featureClass) {
        StateCode stateCode = stateCodeService.getByStateCode(stateId);

        return getByStateCodeAndFeatureType(stateCode, featureClass);
    }

    public List<SubCode> getByStateCodeAndFeatureType(StateCode stateCode, String featureType) {

        SubCode example = new SubCode();
        example.setStateCode(stateCode);
        example.setSubCodeType(featureType);

        return subCodeDao.queryByExample(example);
    }

    public List<String> getUniqueSubCodeTypesByStateCode(Integer stateCodeId) {

        StateCode stateCode = stateCodeService.getById(stateCodeId);

        return getUniqueSubCodeTypesByStateCode(stateCode);
    }

    public List<String> getUniqueSubCodeTypesByStateCode(StateCode stateCode) {
        return subCodeDao.getUniqueSubCodeTypesByStateCode(stateCode);
    }

}
