package org.jason.mapmaker2.web.action;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.validator.annotations.RequiredStringValidator;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.interceptor.validation.SkipValidation;
import org.jason.mapmaker2.model.CensusCode;
import org.jason.mapmaker2.service.CensusCodeService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @author TSgt Jason Ferguson
 */
@Namespace("censusCode")
@Result(name="success", location = "/WEB-INF/admin/censusCode/list.jsp")
public class CensusCodeAction extends ActionSupport {

    // Services

    private CensusCodeService censusCodeService;

    @Autowired
    public void setCensusCodeService(CensusCodeService censusCodeService) {
        this.censusCodeService = censusCodeService;
    }

    // field values

    private String censusCode;
    private String description;
    private List<CensusCode> censusCodeList;

    @RequiredStringValidator(message = "You must provide a census code")
    public String getCensusCode() {
        return censusCode;
    }

    public void setCensusCode(String censusCode) {
        this.censusCode = censusCode;
    }

    @RequiredStringValidator(message = "You must provide a description of the census code")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<CensusCode> getCensusCodeList() {
        return censusCodeList;
    }

    public void setCensusCodeList(List<CensusCode> censusCodeList) {
        this.censusCodeList = censusCodeList;
    }

    @SkipValidation
    @Action("/")
    public String execute() throws Exception {

        censusCodeList = censusCodeService.getAll();

        return SUCCESS;
    }

    @Action("/create")
    public String create() throws Exception {

        return SUCCESS;
    }
}
