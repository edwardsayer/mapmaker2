package org.jason.mapmaker2.web.action;

import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.convention.annotation.*;
import org.apache.struts2.interceptor.validation.SkipValidation;
import org.jason.mapmaker2.service.CustomFeatureService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author Jason Ferguson
 */
@ParentPackage("json-default")
@Namespace("/customFeature")
@Results({
        @Result(name = "success", location = "/WEB-INF/content/admin/customFeature/list.jsp"),
        @Result(name = "input", location = "/WEB-INF/content/admin/customFeature/create.jsp")
})
public class CustomFeatureAction extends ActionSupport {

    private CustomFeatureService customFeatureService;

    @Autowired
    public void setCustomFeatureService(CustomFeatureService customFeatureService) {
        this.customFeatureService = customFeatureService;
    }

    @Action("")
    @SkipValidation
    public String execute() throws Exception {
        return SUCCESS;
    }

    @Action("showCreate")
    public String showCreate() throws Exception {
        return INPUT;
    }

    @Action("create")
    public String create() throws Exception {

        return SUCCESS;
    }
}
