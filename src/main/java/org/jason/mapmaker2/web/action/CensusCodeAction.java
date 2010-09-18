package org.jason.mapmaker2.web.action;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.validator.annotations.RequiredStringValidator;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.apache.struts2.interceptor.validation.SkipValidation;
import org.jason.mapmaker2.model.CensusCode;
import org.jason.mapmaker2.service.CensusCodeService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @author TSgt Jason Ferguson
 */
@Namespace("/censusCode")
@Results({
        @Result(name = "success", location = "/WEB-INF/content/admin/censusCode/list.jsp"),
        @Result(name="input", location = "/WEB-INF/content/admin/censusCode/create.jsp")
})
public class CensusCodeAction extends ActionSupport {

    // Services

    private CensusCodeService censusCodeService;

    @Autowired
    public void setCensusCodeService(CensusCodeService censusCodeService) {
        this.censusCodeService = censusCodeService;
    }

    // field values

    private Integer stateCode;
    private String stateName;
    private Integer countyCode;
    private String countyName;

    private List<CensusCode> censusCodeList;

    public Integer getStateCode() {
        return stateCode;
    }

    public void setStateCode(Integer stateCode) {
        this.stateCode = stateCode;
    }

    public String getStateName() {
        return stateName;
    }

    public void setStateName(String stateName) {
        this.stateName = stateName;
    }

    public Integer getCountyCode() {
        return countyCode;
    }

    public void setCountyCode(Integer countyCode) {
        this.countyCode = countyCode;
    }

    public String getCountyName() {
        return countyName;
    }

    public void setCountyName(String countyName) {
        this.countyName = countyName;
    }

    public List<CensusCode> getCensusCodeList() {
        return censusCodeList;
    }

    public void setCensusCodeList(List<CensusCode> censusCodeList) {
        this.censusCodeList = censusCodeList;
    }

    @SkipValidation
    @Action("")
    public String execute() throws Exception {

        censusCodeList = censusCodeService.getAll();

        return SUCCESS;
    }

    @SkipValidation
    @Action("/showCreate")
    public String showCreate() throws Exception {

        return INPUT;
    }

    @Action("/create")
    public String create() throws Exception {

        CensusCode code = new CensusCode();
        code.setStateCode(stateCode);
        code.setStateName(stateName);
        code.setCountyCode(countyCode);
        code.setCountyName(countyName);

        CensusCode persistedCode = censusCodeService.save(code);
        if (persistedCode == null) {
            addActionError("Unable to save new census code");
            return INPUT;
        }
        return SUCCESS;
    }
}
