package org.jason.mapmaker2.web.action;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.Preparable;
import com.opensymphony.xwork2.validator.annotations.RequiredFieldValidator;
import com.opensymphony.xwork2.validator.annotations.RequiredStringValidator;
import org.apache.struts2.convention.annotation.*;
import org.apache.struts2.interceptor.validation.SkipValidation;
import org.jason.mapmaker2.model.StateCode;
import org.jason.mapmaker2.service.StateCodeService;
import org.jason.mapmaker2.service.SubCodeService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * SubCodeAction.java
 *
 * All actions relating to SubCode, normal and JSON.
 * 
 * @author Jason Ferguson
 */
@ParentPackage("json-default")
@Namespace("/subCode")
@Results({
        @Result(name = "success", location = "/WEB-INF/content/admin/subCode/list.jsp"),
        @Result(name = "input", location = "/WEB-INF/content/admin/subCode/create.jsp")
})
public class SubCodeAction extends ActionSupport implements Preparable {

    private SubCodeService subCodeService;

    @Autowired
    public void setSubCodeService(SubCodeService subCodeService) {
        this.subCodeService = subCodeService;
    }

    private StateCodeService stateCodeService;

    @Autowired
    public void setStateCodeService(StateCodeService stateCodeService) {
        this.stateCodeService = stateCodeService;
    }

    private Integer stateCodeId;
    private Integer subCode;
    private String subCodeDescription;
    private String subCodeType;

    @RequiredFieldValidator(message = "You must select a state!")
    public Integer getStateCodeId() {
        return stateCodeId;
    }

    public void setStateCodeId(Integer stateCodeId) {
        this.stateCodeId = stateCodeId;
    }

    @RequiredFieldValidator(message = "You must enter a sub code!")
    public Integer getSubCode() {
        return subCode;
    }

    public void setSubCodeId(Integer subCode) {
        this.subCode = subCode;
    }

    @RequiredStringValidator(message = "You must enter a description!")
    public String getSubCodeDescription() {
        return subCodeDescription;
    }

    public void setSubCodeDescription(String subCodeDescription) {
        this.subCodeDescription = subCodeDescription;
    }

    @RequiredStringValidator(message = "You must enter a type (i.e. 'county')!")
    public String getSubCodeType() {
        return subCodeType;
    }

    public void setSubCodeType(String subCodeType) {
        this.subCodeType = subCodeType;
    }

    private List<StateCode> stateCodeList;

    public List<StateCode> getStateCodeList() {
        return stateCodeList;
    }

    public void setStateCodeList(List<StateCode> stateCodeList) {
        this.stateCodeList = stateCodeList;
    }

    public void prepare() throws Exception {
        stateCodeList = stateCodeService.getAll();
    }

    @Action("")
    @SkipValidation
    public String execute() throws Exception {

        return SUCCESS;
    }

    @Action("showCreate")
    @SkipValidation
    public String showCreate() throws Exception {

        return INPUT;
    }

    @Action("create")
    public String create() throws Exception {
        return SUCCESS;
    }

    @Action(value = "subCodeByStateJson", results = {
            @Result(name = "success", type = "json")
    })
    public String getSubCodesByStateJson() throws Exception {

        return SUCCESS;
    }
}
