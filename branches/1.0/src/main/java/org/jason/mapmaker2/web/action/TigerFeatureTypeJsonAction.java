package org.jason.mapmaker2.web.action;

import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.jason.mapmaker2.model.TigerFeatureType;
import org.jason.mapmaker2.service.TigerFeatureTypeService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Collections;
import java.util.List;

/**
 * @author TSgt Jason Ferguson
 */
@ParentPackage("json-default")
@Namespace("/tigerFeatureType")
public class TigerFeatureTypeJsonAction extends ActionSupport {

    private TigerFeatureTypeService tigerFeatureTypeService;

    @Autowired
    public void setTigerFeatureTypeService(TigerFeatureTypeService tigerFeatureTypeService) {
        this.tigerFeatureTypeService = tigerFeatureTypeService;
    }

    public String execute() throws Exception {
        return SUCCESS;
    }

    private List<TigerFeatureType> tigerFeatureTypes;

    public List<TigerFeatureType> getTigerFeatureTypes() {
        return tigerFeatureTypes;
    }

    public void setTigerFeatureTypes(List<TigerFeatureType> tigerFeatureTypes) {
        this.tigerFeatureTypes = tigerFeatureTypes;
    }

    @Action("tigerFeatureTypesJson")
    public String getTigerFeatureTypesJson() throws Exception {

        tigerFeatureTypes = tigerFeatureTypeService.getAll();
        Collections.sort(tigerFeatureTypes);
        return SUCCESS;
    }
}
