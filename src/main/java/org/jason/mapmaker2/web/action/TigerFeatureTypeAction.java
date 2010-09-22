package org.jason.mapmaker2.web.action;

import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.convention.annotation.*;
import org.jason.mapmaker2.model.TigerFeatureType;
import org.jason.mapmaker2.service.TigerFeatureTypeService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author TSgt Jason Ferguson
 */
@ParentPackage("struts-default")
@Namespace("/tigerFeatureType")
@Results({
        @Result(name="success", location = "/WEB-INF/content/admin/tigerFeatureType/list.jsp"),
        @Result(name="input", location = "/WEB-INF/content/admin/tigerFeatureType/create.jsp")
})
public class TigerFeatureTypeAction extends ActionSupport {

    private TigerFeatureTypeService tigerFeatureTypeService;

    @Autowired
    public void setTigerFeatureTypeService(TigerFeatureTypeService tigerFeatureTypeService) {
        this.tigerFeatureTypeService = tigerFeatureTypeService;
    }

    @Action("/")
    public String execute() throws Exception {

        return SUCCESS;
    }

    private String tigerFeatureClassCode;
    private String tigerFeatureClassCodeDescription;

    public String getTigerFeatureClassCode() {
        return tigerFeatureClassCode;
    }

    public void setTigerFeatureClassCode(String tigerFeatureClassCode) {
        this.tigerFeatureClassCode = tigerFeatureClassCode;
    }

    public String getTigerFeatureClassCodeDescription() {
        return tigerFeatureClassCodeDescription;
    }

    public void setTigerFeatureClassCodeDescription(String tigerFeatureClassCodeDescription) {
        this.tigerFeatureClassCodeDescription = tigerFeatureClassCodeDescription;
    }

    @Action("showCreate")
    public String showCreate() throws Exception {

        return INPUT;
    }

    @Action("create")
    public String create() throws Exception {

        TigerFeatureType tft = new TigerFeatureType();
        tft.setTigerFeatureClassCode(tigerFeatureClassCode);
        tft.setDescription(tigerFeatureClassCodeDescription);

        TigerFeatureType result = tigerFeatureTypeService.save(tft);
        if (result == null) {
            addActionError("Unable to save new TIGER Feature Class Code");
            return INPUT;
        }

        addActionMessage("Successfully created new TIGER Feature Class Code");
        return SUCCESS;
    }
}
