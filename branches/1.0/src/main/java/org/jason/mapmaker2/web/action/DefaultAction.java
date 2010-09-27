package org.jason.mapmaker2.web.action;

import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.interceptor.ParameterAware;
import org.jason.mapmaker2.model.StateCode;
import org.jason.mapmaker2.model.SubCode;
import org.jason.mapmaker2.service.StateCodeService;
import org.jason.mapmaker2.service.SubCodeService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * @author Jason Ferguson
 */
@ParentPackage("json-default")
@Namespace("/")
@Result(name = "success", location = "/WEB-INF/content/makeMap.jsp")
public class DefaultAction extends ActionSupport implements ParameterAware {

    Map<String,String[]> parameters;

    public void setParameters(Map<String, String[]> parameters) {
        this.parameters = parameters;
    }

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

    private int stateCodeId;
    private int subCodeId;

    public int getStateCodeId() {
        return stateCodeId;
    }

    public void setStateCodeId(int stateCodeId) {
        this.stateCodeId = stateCodeId;
    }

    public int getSubCodeId() {
        return subCodeId;
    }

    public void setSubCodeId(int subCodeId) {
        this.subCodeId = subCodeId;
    }

    List<StateCode> stateCodeList;
    List<SubCode> subCodeList;

    public List<StateCode> getStateCodeList() {
        return stateCodeList;
    }

    public void setStateCodeList(List<StateCode> stateCodeList) {
        this.stateCodeList = stateCodeList;
    }

    public List<SubCode> getSubCodeList() {
        return subCodeList;
    }

    public void setSubCodeList(List<SubCode> subCodeList) {
        this.subCodeList = subCodeList;
    }

    @Action("")
    public String execute() throws Exception {

        stateCodeList = stateCodeService.getAll();
        Collections.sort(stateCodeList);

        return SUCCESS;
    }

    @Action(value = "menu", results =
    @Result(name = "success", location = "/WEB-INF/content/admin/menu.jsp")
    )
    public String showMenu() throws Exception {

        return SUCCESS;
    }

    @Action(value = "getStateCodesJson", results = {
            @Result(name = "success", type = "json")
    })
    public String getStateCodesJson() throws Exception {
        stateCodeList = stateCodeService.getAll();
        Collections.sort(stateCodeList);

        return SUCCESS;
    }

    private List<String> distinctSubCodes;

    public List<String> getDistinctSubCodes() {
        return distinctSubCodes;
    }

    public void setDistinctSubCodes(List<String> distinctSubCodes) {
        this.distinctSubCodes = distinctSubCodes;
    }

    @Action(value = "getSubCodesJson", results = {
            @Result(name = "success", type = "json")
    })
    public String getSubCodesJson() throws Exception {

        if (parameters != null && parameters.get("id") != null) {
            stateCodeId = Integer.parseInt(parameters.get("id")[0]);
            StateCode stateCode = stateCodeService.getById(stateCodeId);
            distinctSubCodes = subCodeService.getUniqueSubCodeTypesByStateCode(stateCode);
        }

        return SUCCESS;
    }
}
