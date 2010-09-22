package org.jason.mapmaker2.web.action;

import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.jason.mapmaker2.model.State;
import org.jason.mapmaker2.service.StateService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Collections;
import java.util.List;

/**
 * @author Jason Ferguson
 */
@ParentPackage("json-default")
@Namespace("/state")
public class StateJsonAction extends ActionSupport {

    private StateService stateService;

    @Autowired
    public void setStateService(StateService stateService) {
        this.stateService = stateService;
    }

    public String execute() throws Exception {
        return SUCCESS;
    }

    List<State> states;

    public List<State> getStates() {
        return states;
    }

    public void setStates(List<State> states) {
        this.states = states;
    }

    @Action(value = "statesJson", results = {
            @Result(name="success", type = "json")
    })
    public String getStatesJson() throws Exception {

        states = stateService.getAll();
        Collections.sort(states);
        return SUCCESS;
    }
}
