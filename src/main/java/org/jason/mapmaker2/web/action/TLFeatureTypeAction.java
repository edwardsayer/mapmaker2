package org.jason.mapmaker2.web.action;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.Preparable;
import com.opensymphony.xwork2.validator.annotations.RequiredFieldValidator;
import com.opensymphony.xwork2.validator.annotations.RequiredStringValidator;
import org.apache.struts2.convention.annotation.*;
import org.apache.struts2.interceptor.validation.SkipValidation;
import org.jason.mapmaker2.model.StateCode;
import org.jason.mapmaker2.model.TLFeatureType;
import org.jason.mapmaker2.service.StateCodeService;
import org.jason.mapmaker2.service.TLFeatureTypeService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @author Jason Ferguson
 */
@ParentPackage("struts-default")
@Namespace("/tlFeatureType")
@Results({
        @Result(name = "success", location = "/WEB-INF/content/admin/tlFeatureType/list.jsp"),
        @Result(name = "input", location = "/WEB-INF/content/admin/tlFeatureType/create.jsp")
})
public class TLFeatureTypeAction extends ActionSupport implements Preparable {

    private TLFeatureTypeService tlFeatureTypeService;
    private StateCodeService stateCodeService;

    @Autowired
    public void setTlFeatureTypeService(TLFeatureTypeService tlFeatureTypeService) {
        this.tlFeatureTypeService = tlFeatureTypeService;
    }

    @Autowired
    public void setStateCodeService(StateCodeService stateCodeService) {
        this.stateCodeService = stateCodeService;
    }

    private Integer year;
    private Integer stateCodeId;
    private String postfix;
    private String description;
    private List<StateCode> stateCodeList;

    @RequiredFieldValidator(message = "You must set a year!")
    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public Integer getStateCodeId() {
        return stateCodeId;
    }

    public void setStateCodeId(Integer stateCodeId) {
        this.stateCodeId = stateCodeId;
    }

    @RequiredStringValidator(message = "You must provide the postfix!")
    public String getPostfix() {
        return postfix;
    }

    public void setPostfix(String postfix) {
        this.postfix = postfix;
    }

    @RequiredStringValidator(message = "You must provide a description!")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<StateCode> getStateCodeList() {
        return stateCodeList;
    }

    public void setStateCodeList(List<StateCode> stateCodeList) {
        this.stateCodeList = stateCodeList;
    }

    public void prepare() throws Exception {
        stateCodeList = stateCodeService.getAll();
    }

    @SkipValidation
    @Action("")
    public String execute() throws Exception {
        return SUCCESS;
    }

    @SkipValidation
    @Action("showCreate")
    public String showCreate() throws Exception {
        return INPUT;
    }

    @Action("create")
    public String create() throws Exception {

        for (StateCode sc : stateCodeList) {

            TLFeatureType tl = new TLFeatureType();
            tl.setYear(year);
            tl.setStateCode(sc);
            tl.setPostfix(postfix);
            tl.setDescription(description);

            tlFeatureTypeService.save(tl);

        }

        return SUCCESS;
    }
}
