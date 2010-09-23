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
 * @author Jason Ferguson
 */
@ParentPackage("struts-default")
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
    private String subCodeDescription;
    private Integer code;

    @RequiredFieldValidator(message = "You must select a state!")
    public Integer getStateCodeId() {
        return stateCodeId;
    }

    public void setStateCodeId(Integer stateCodeId) {
        this.stateCodeId = stateCodeId;
    }

    @RequiredStringValidator(message = "You must provide a description!")
    public String getSubCodeDescription() {
        return subCodeDescription;
    }

    public void setSubCodeDescription(String subCodeDescription) {
        this.subCodeDescription = subCodeDescription;
    }

    @RequiredFieldValidator(message = "You must provide a FIPS code!")
    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
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
}
