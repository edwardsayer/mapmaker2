package org.jason.mapmaker2.web.action;

import com.opensymphony.xwork2.ActionSupport;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.convention.annotation.*;
import org.apache.struts2.interceptor.ParameterAware;
import org.jason.mapmaker2.model.SubCode;
import org.jason.mapmaker2.service.SubCodeService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

/**
 * SubCodeAction.java
 * <p/>
 * All actions relating to SubCode, normal and JSON.
 *
 * This class has been extensively rewritten to minimimize unnecessary code and coupling. In general,
 * Action classes should only be coupled to one equivalent Service.
 *
 * Since SubCodes are mostly created when a set of BorderPoints is imported, the basic Action methods
 * are not implemented. Currently, only JSON response methods are implemented.
 *
 * @author Jason Ferguson
 */
@ParentPackage("json-default")
@Namespace("/subCode")
@Results({
        @Result(name = "success", location = "/WEB-INF/content/admin/subCode/list.jsp"),
        @Result(name = "input", location = "/WEB-INF/content/admin/subCode/create.jsp")
})
public class SubCodeAction extends ActionSupport implements ParameterAware {

    private static final Log log = LogFactory.getLog(SubCodeAction.class);

    // ParameterAware Interface
    private Map<String, String[]> parameters;

    public void setParameters(Map<String, String[]> parameters) {
        this.parameters = parameters;
    }

    // Service definition
    private SubCodeService subCodeService;

    @Autowired
    public void setSubCodeService(SubCodeService subCodeService) {
        this.subCodeService = subCodeService;
    }

    // JSON returned data fields
    private Integer stateCodeId;
    private List<SubCode> subCodeList;
    private List<String> distinctSubCodes;

    public List<SubCode> getSubCodeList() {
        return subCodeList;
    }

    public Integer getStateCodeId() {
        return stateCodeId;
    }

    public void setStateCodeId(Integer stateCodeId) {
        this.stateCodeId = stateCodeId;
    }

    public void setSubCodeList(List<SubCode> subCodeList) {
        this.subCodeList = subCodeList;
    }

    public List<String> getDistinctSubCodes() {
        return distinctSubCodes;
    }

    public void setDistinctSubCodes(List<String> distinctSubCodes) {
        this.distinctSubCodes = distinctSubCodes;
    }

    public String execute() throws Exception {
        return SUCCESS;
    }

    public String showCreate() throws Exception {
        throw new UnsupportedOperationException();
    }

    public String create() throws Exception {
        throw new UnsupportedOperationException();
    }

    @Action(value = "getSubCodes", results = {
            @Result(name = "success", type = "json")
    })
    public String getSubCodes() {

        if (parameters.get("stateCodeId") == null) {
            addActionError("State Code Id is null");
            return INPUT;
        }

        if (parameters.get("featureClass") == null) {
            addActionError("Feature class is null");
            return INPUT;
        }

        // this returns the unique id of the statecode object, not the FIPS code!
        Integer stateId = Integer.parseInt(parameters.get("stateCodeId")[0]);
        String featureClass = parameters.get("featureClass")[0];

        subCodeList = subCodeService.getByStateCodeAndFeatureType(stateId, featureClass);
        Collections.sort(subCodeList, new Comparator<SubCode>() {
            public int compare(SubCode o1, SubCode o2) {
                return o1.getSubCodeDescription().compareTo(o2.getSubCodeDescription());
            }
        });

        return SUCCESS;
    }

    @Action(value = "getSubCodeTypes", results = {
            @Result(name = "success", type = "json")
    })
    public String getSubCodeTypes() {

        if (parameters.get("stateCodeId") == null) {
            addActionError("State Code Id is null");
            return INPUT;
        }

        // this returns the unique id of the statecode object, not the FIPS code!
        Integer stateId = Integer.parseInt(parameters.get("stateCodeId")[0]);

        distinctSubCodes = subCodeService.getUniqueSubCodeTypesByStateCode(stateId);
        
        return SUCCESS;
    }
}
