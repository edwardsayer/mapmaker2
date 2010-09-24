package org.jason.mapmaker2.web.action;

import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.jason.mapmaker2.model.StateCode;
import org.jason.mapmaker2.model.SubCode;
import org.jason.mapmaker2.service.StateCodeService;
import org.jason.mapmaker2.service.SubCodeService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Collections;
import java.util.List;

/**
 * @author Jason Ferguson
 */
@ParentPackage("json-default")
@Namespace("/stateCode")
public class StateCodeJsonAction extends ActionSupport {

    private StateCodeService stateCodeService;

    @Autowired
    public void setStateCodeService(StateCodeService stateCodeService) {
        this.stateCodeService = stateCodeService;
    }

    private SubCodeService subCodeService;

    @Autowired
    public void setSubCodeService(SubCodeService subCodeService) {
        this.subCodeService = subCodeService;
    }

    public String execute() throws Exception {

        return SUCCESS;
    }

    private Integer stateCodeId;

    public Integer getStateCodeId() {
        return stateCodeId;
    }

    public void setStateCodeId(Integer stateCodeId) {
        this.stateCodeId = stateCodeId;
    }

    private List<StateCode> stateCodeList;

    public List<StateCode> getStateCodeList() {
        return stateCodeList;
    }

    public void setStateCodeList(List<StateCode> stateCodeList) {
        this.stateCodeList = stateCodeList;
    }

    private List<SubCode> subCodeList;

    public List<SubCode> getSubCodeList() {
        return subCodeList;
    }

    public void setSubCodeList(List<SubCode> subCodeList) {
        this.subCodeList = subCodeList;
    }

    @Action(value="getStateCodesJson", results={
            @Result(name="success", type = "json")
    })
    public String getStateCodesJson() throws Exception {
        stateCodeList = stateCodeService.getAll();
        Collections.sort(stateCodeList);

        if (stateCodeId != null) {
            StateCode stateCode = stateCodeService.getById(stateCodeId);
            subCodeList = subCodeService.getByStateCode(stateCode);
        }
        
        return SUCCESS;
    }

}
