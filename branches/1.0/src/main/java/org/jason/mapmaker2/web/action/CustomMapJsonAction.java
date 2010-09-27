package org.jason.mapmaker2.web.action;

import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.jason.mapmaker2.service.CustomMapService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author Jason Ferguson
 */
@ParentPackage("json-default")
@Namespace("/customMap")
public class CustomMapJsonAction extends ActionSupport {

    private CustomMapService customMapService;

    @Autowired
    public void setCustomMapService(CustomMapService customMapService) {
        this.customMapService = customMapService;
    }

    @Action(value="getCustomMapJson", results = {
            @Result(name = "success", type = "json")
    })
    public String execute() throws Exception {

        return SUCCESS;

    }

}
