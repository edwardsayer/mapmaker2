package org.jason.mapmaker2.web.action;

import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.jason.mapmaker2.model.FeatureTypeDescription;
import org.jason.mapmaker2.service.FeatureTypeDescriptionService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Collections;
import java.util.List;

/**
 * @author Jason Ferguson
 */
@ParentPackage("json-default")
public class FeatureTypeDescriptionJsonAction extends ActionSupport {

    private FeatureTypeDescriptionService featureTypeDescriptionService;

    @Autowired
    public void setFeatureTypeDescriptionService(FeatureTypeDescriptionService featureTypeDescriptionService) {
        this.featureTypeDescriptionService = featureTypeDescriptionService;
    }

    private List<FeatureTypeDescription> featureTypeDescriptions;

    public List<FeatureTypeDescription> getFeatureTypeDescriptions() {
        return featureTypeDescriptions;
    }

    public void setFeatureTypeDescriptions(List<FeatureTypeDescription> featureTypeDescriptions) {
        this.featureTypeDescriptions = featureTypeDescriptions;
    }

    public String execute() throws Exception {

        return SUCCESS;
    }

    @Action(value="featureTypeDescriptionsJson", results = {
            @Result(name="success", type = "json")
    })
    public String getFeatureTypeDescriptionsJson() throws Exception {

        featureTypeDescriptions = featureTypeDescriptionService.getAll();
        Collections.sort(featureTypeDescriptions);

        return SUCCESS;
    }
}
