package org.jason.mapmaker2.web.action;

import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.convention.annotation.*;
import org.jason.mapmaker2.service.LocationService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author Jason Ferguson
 */
@ParentPackage("struts-default")
@Namespace("/location")
@Results({
        @Result(name="success", location = "/WEB-INF/content/admin/location/list.jsp"),
        @Result(name="input", location = "/WEB-INF/content/admin/location/create.jsp")
})
public class LocationAction extends ActionSupport {

    private LocationService locationService;

    @Autowired
    public void setLocationService(LocationService locationService) {
        this.locationService = locationService;
    }

    @Action("/")
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
