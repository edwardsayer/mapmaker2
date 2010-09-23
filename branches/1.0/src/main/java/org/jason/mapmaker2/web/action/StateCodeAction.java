package org.jason.mapmaker2.web.action;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.validator.annotations.RequiredFieldValidator;
import com.opensymphony.xwork2.validator.annotations.RequiredStringValidator;
import org.apache.struts2.convention.annotation.*;
import org.apache.struts2.interceptor.validation.SkipValidation;
import org.jason.mapmaker2.model.StateCode;
import org.jason.mapmaker2.service.StateCodeService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @author Jason Ferguson
 */
@ParentPackage("struts-default")
@Namespace("/stateCode")
@Results({
        @Result(name="success", location = "/WEB-INF/content/admin/stateCode/list.jsp"),
        @Result(name="input", location = "/WEB-INF/content/admin/stateCode/create.jsp")
})
public class StateCodeAction extends ActionSupport {

    private StateCodeService stateCodeService;

    @Autowired
    public void setStateCodeService(StateCodeService stateCodeService) {
        this.stateCodeService = stateCodeService;
    }

    private String stateName;
    private String stateAbbr;
    private Integer stateCode;

    @RequiredStringValidator(message = "You must provide the name of the state!")
    public String getStateName() {
        return stateName;
    }

    public void setStateName(String stateName) {
        this.stateName = stateName;
    }

    @RequiredStringValidator(message = "You must provide the state abbreviation!")
    public String getStateAbbr() {
        return stateAbbr;
    }

    public void setStateAbbr(String stateAbbr) {
        this.stateAbbr = stateAbbr;
    }

    @RequiredFieldValidator(message = "You must provide the state FIPS code!")
    public Integer getStateCode() {
        return stateCode;
    }

    public void setStateCode(Integer stateCode) {
        this.stateCode = stateCode;
    }

    List<StateCode> stateCodeList;

    @Action("")
    @SkipValidation
    public String execute() throws Exception {

        stateCodeList = stateCodeService.getAll();
        return SUCCESS;
    }

    @Action("showCreate")
    @SkipValidation
    public String showCreate() throws Exception {

        return INPUT;
    }

    @Action("create")
    public String create() throws Exception {

        StateCode result = stateCodeService.save(new StateCode(stateName, stateAbbr, stateCode));

        if (result==null) {
            addActionError("Unable to save State Code!");
            return INPUT;
        }
        
        return SUCCESS;
    }
}
