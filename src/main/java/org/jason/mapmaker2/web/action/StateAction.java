package org.jason.mapmaker2.web.action;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.validator.annotations.RequiredStringValidator;
import org.apache.struts2.convention.annotation.*;
import org.apache.struts2.interceptor.validation.SkipValidation;
import org.jason.mapmaker2.model.State;
import org.jason.mapmaker2.service.StateService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @author Jason Ferguson
 */
@ParentPackage("struts-default")
@Namespace("/state")
@Results({
        @Result(name="success", location = "/WEB-INF/content/admin/state/list.jsp"),
        @Result(name="input", location = "/WEB-INF/content/admin/state/create.jsp")
})
public class StateAction extends ActionSupport {

    private StateService stateService;

    @Autowired
    public void setStateService(StateService stateService) {
        this.stateService = stateService;
    }

    // state form fields
    private String stateName;
    private String stateAbbr;
    private String stateFIPS;

    @RequiredStringValidator(message = "You must provide the State name!")
    public String getStateName() {
        return stateName;
    }

    public void setStateName(String stateName) {
        this.stateName = stateName;
    }

    public String getStateAbbr() {
        return stateAbbr;
    }

    @RequiredStringValidator(message = "You must provide the State FIPS code!")
    public void setStateAbbr(String stateAbbr) {
        this.stateAbbr = stateAbbr;
    }

    public String getStateFIPS() {
        return stateFIPS;
    }

    public void setStateFIPS(String stateFIPS) {
        this.stateFIPS = stateFIPS;
    }

    private List<State> states;

    @Action("list")
    @SkipValidation
    public String execute() throws Exception {

        states = stateService.getAll();
        return SUCCESS;
    }

    @Action("showCreate")
    @SkipValidation
    public String showCreateState() throws Exception {

        return INPUT;
    }

    @Action("create")
    public String createState() throws Exception {

        State result = stateService.save(new State(stateName, stateAbbr, stateFIPS));
        if (result == null) {
            addActionError("Unable to save state");
            return INPUT;
        }

        addActionMessage("State saved!");
        return SUCCESS;
    }
}
