package org.jason.mapmaker2.web.action.json.featureClass;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.Preparable;
import org.jason.mapmaker2.model.FeatureClass;
import org.jason.mapmaker2.service.FeatureClassService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * All JSON Actions related to work with the FeatureClass object
 *
 * @author Jason Ferguson
 */
public class FeatureClassJsonAction extends ActionSupport implements Preparable {

    private FeatureClassService featureClassService;

    @Autowired
    public void setFeatureClassService(FeatureClassService featureClassService) {
        this.featureClassService = featureClassService;
    }

    private List<FeatureClass> featureClasses;

    public List<FeatureClass> getFeatureClasses() {
        return featureClasses;
    }

    public void setFeatureClasses(List<FeatureClass> featureClasses) {
        this.featureClasses = featureClasses;
    }

    public void prepare() throws Exception {
        featureClasses = featureClassService.getAll();
    }

    public String execute() throws Exception {
        return INPUT;
    }

    public String getFeatureClassesJSON() throws Exception {
        return SUCCESS;
    }
}
