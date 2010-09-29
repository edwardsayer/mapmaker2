package org.jason.mapmaker2.web.action;

import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.convention.annotation.*;
import org.jason.mapmaker2.model.BorderPoint;
import org.jason.mapmaker2.model.composite.CustomMap;
import org.jason.mapmaker2.service.CustomMapService;

import java.util.List;

/**
 * @author Jason Ferguson
 */
@ParentPackage("json-default")
@Namespace("/customMap")
@Results({
        @Result(name="success"),
        @Result(name="input")
})
public class CustomMapAction extends ActionSupport {

    private CustomMapService customMapService;

    public void setCustomMapService(CustomMapService customMapService) {
        this.customMapService = customMapService;
    }

    private Integer stateId;
    private Integer subCodeId;
    private String featureName;
    
    private String caption;
    private List<BorderPoint> borderPoints;

    public String execute() throws Exception {

        return SUCCESS;
    }

    @Action(value = "getCustomMapJson", results = {
            @Result(name="success", type = "json")
    })
    public String getCustomMapJson() throws Exception {

        CustomMap map = customMapService.createMap(stateId, subCodeId);

        return SUCCESS;
    }
}
