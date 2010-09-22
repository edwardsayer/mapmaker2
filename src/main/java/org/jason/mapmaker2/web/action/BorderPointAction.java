package org.jason.mapmaker2.web.action;

import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.convention.annotation.*;
import org.apache.struts2.interceptor.validation.SkipValidation;
import org.jason.mapmaker2.service.BorderPointService;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.File;

/**
 * @author Jason Ferguson
 */
@ParentPackage("struts-default")
@Namespace("/borderPoint")
@Results({
        @Result(name = "success", location = "/WEB-INF/content/admin/borderPoint/list.jsp"),
        @Result(name = "input", location = "/WEB-INF/content/admin/borderPoint/create.jsp")
})

public class BorderPointAction extends ActionSupport {

    private BorderPointService borderPointService;

    @Autowired
    public void setBorderPointService(BorderPointService borderPointService) {
        this.borderPointService = borderPointService;
    }

    private Integer stateId;
    private Integer featureTypeId;
    private File fileUpload;
    private String fileUploadContentType;
    private String fileUploadFileName;
    private String fileCaption;

    public Integer getStateId() {
        return stateId;
    }

    public void setStateId(Integer stateId) {
        this.stateId = stateId;
    }

    public Integer getFeatureTypeId() {
        return featureTypeId;
    }

    public void setFeatureTypeId(Integer featureTypeId) {
        this.featureTypeId = featureTypeId;
    }

    public File getFileUpload() {
        return fileUpload;
    }

    public void setFileUpload(File fileUpload) {
        this.fileUpload = fileUpload;
    }

    public String getFileUploadContentType() {
        return fileUploadContentType;
    }

    public void setFileUploadContentType(String fileUploadContentType) {
        this.fileUploadContentType = fileUploadContentType;
    }

    public String getFileUploadFileName() {
        return fileUploadFileName;
    }

    public void setFileUploadFileName(String fileUploadFileName) {
        this.fileUploadFileName = fileUploadFileName;
    }

    public String getFileCaption() {
        return fileCaption;
    }

    public void setFileCaption(String fileCaption) {
        this.fileCaption = fileCaption;
    }

    @Action("/")
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
