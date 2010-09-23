package org.jason.mapmaker2.web.action;

import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.convention.annotation.*;
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
    private String stateCode;

    public String getStateName() {
        return stateName;
    }

    public void setStateName(String stateName) {
        this.stateName = stateName;
    }

    public String getStateAbbr() {
        return stateAbbr;
    }

    public void setStateAbbr(String stateAbbr) {
        this.stateAbbr = stateAbbr;
    }

    public String getStateCode() {
        return stateCode;
    }

    public void setStateCode(String stateCode) {
        this.stateCode = stateCode;
    }

    List<StateCode> stateCodeList;

    @Action("")
    public String execute() throws Exception {

        stateCodeList = stateCodeService.getAll();
        return SUCCESS;
    }

    @Action("showCreate")
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
