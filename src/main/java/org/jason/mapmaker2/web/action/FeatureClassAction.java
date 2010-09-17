package org.jason.mapmaker2.web.action;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.validator.annotations.RequiredStringValidator;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.apache.struts2.interceptor.validation.SkipValidation;
import org.jason.mapmaker2.model.FeatureClass;
import org.jason.mapmaker2.service.FeatureClassService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @author Jason Ferguson
 */
@Namespace("/featureClass")
@Results({
        @Result(name="success", location = "/WEB-INF/content/admin/featureClass/list.jsp"),
        @Result(name="input", location = "/WEB-INF/content/admin/featureClass/create.jsp")
})
public class FeatureClassAction extends ActionSupport {

    private FeatureClassService featureClassService;

    @Autowired
    public void setFeatureClassService(FeatureClassService featureClassService) {
        this.featureClassService = featureClassService;
    }

    private String classCode;
    private String classCodeShortDesc;
    private String classCodeDesc;

    @RequiredStringValidator(message="You must provide the feature class code!")
    public String getClassCode() {
        return classCode;
    }

    public void setClassCode(String classCode) {
        this.classCode = classCode;
    }

    @RequiredStringValidator(message="You must provide a short description for the description")
    public String getClassCodeShortDesc() {
        return classCodeShortDesc;
    }

    public void setClassCodeShortDesc(String classCodeShortDesc) {
        this.classCodeShortDesc = classCodeShortDesc;
    }

    public String getClassCodeDesc() {
        return classCodeDesc;
    }

    public void setClassCodeDesc(String classCodeDesc) {
        this.classCodeDesc = classCodeDesc;
    }

    List<FeatureClass> featureClasses;

    public List<FeatureClass> getFeatureClasses() {
        return featureClasses;
    }

    public void setFeatureClasses(List<FeatureClass> featureClasses) {
        this.featureClasses = featureClasses;
    }

    @Action("")
    @SkipValidation
    public String execute() throws Exception {

        featureClasses = featureClassService.getAll();

        return SUCCESS;
    }

    @Action("create")
    public String create() throws Exception {

        FeatureClass featureClass = new FeatureClass();
        featureClass.setClassCode(classCode);
        featureClass.setClassCodeDesc(classCodeDesc);
        featureClass.setClassCodeShortDesc(classCodeShortDesc);

        featureClassService.save(featureClass);

        return SUCCESS;

    }
}
