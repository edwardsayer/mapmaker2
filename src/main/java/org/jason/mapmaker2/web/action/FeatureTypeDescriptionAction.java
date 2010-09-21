package org.jason.mapmaker2.web.action;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.validator.annotations.RequiredStringValidator;
import org.apache.struts2.convention.annotation.*;
import org.apache.struts2.interceptor.validation.SkipValidation;
import org.jason.mapmaker2.model.FeatureTypeDescription;
import org.jason.mapmaker2.service.FeatureTypeDescriptionService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @author Jason Ferguson
 */
@ParentPackage("struts-default")
@Namespace("/featureTypeDescription")
@Results({
        @Result(name="success", location = "/WEB-INF/content/admin/featureTypeDescription/list.jsp"),
        @Result(name="input", location = "/WEB-INF/content/admin/featureTypeDescription/create.jsp")
})
public class FeatureTypeDescriptionAction extends ActionSupport {

    private FeatureTypeDescriptionService featureTypeDescriptionService;

    @Autowired
    public void setFeatureTypeDescriptionService(FeatureTypeDescriptionService featureTypeDescriptionService) {
        this.featureTypeDescriptionService = featureTypeDescriptionService;
    }

    private String ftName;
    private String ftCensusCode;

    @RequiredStringValidator(message="You must provide a name for the feature type!")
    public String getFtName() {
        return ftName;
    }

    public void setFtName(String ftName) {
        this.ftName = ftName;
    }

    @RequiredStringValidator(message="You must provide the census code!")
    public String getFtCensusCode() {
        return ftCensusCode;
    }

    public void setFtCensusCode(String ftCensusCode) {
        this.ftCensusCode = ftCensusCode;
    }

    private List<FeatureTypeDescription> featureTypeDescriptions;

    public List<FeatureTypeDescription> getFeatureTypeDescriptions() {
        return featureTypeDescriptions;
    }

    public void setFeatureTypeDescriptions(List<FeatureTypeDescription> featureTypeDescriptions) {
        this.featureTypeDescriptions = featureTypeDescriptions;
    }

    @Action("/")
    @SkipValidation
    public String execute() throws Exception {

        featureTypeDescriptions = featureTypeDescriptionService.getAll();
        return SUCCESS;
    }

    @Action("showCreate")
    @SkipValidation
    public String showCreate() throws Exception {
        return INPUT;
    }

    @Action("create")
    public String create() throws Exception {

        FeatureTypeDescription ftd = new FeatureTypeDescription();
        ftd.setFeatureClassCode(ftCensusCode);
        ftd.setFeatureTypeName(ftName);

        FeatureTypeDescription result = featureTypeDescriptionService.save(ftd);
        if (result == null) {
            addActionError("Unable to save new FeatureTypeDescription!");
            return INPUT;
        }

        return SUCCESS;
    }
}
