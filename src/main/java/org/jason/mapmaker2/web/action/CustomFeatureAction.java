package org.jason.mapmaker2.web.action;

import com.opensymphony.xwork2.ActionSupport;
import org.jason.mapmaker2.service.CustomFeatureService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author Jason Ferguson
 */
public class CustomFeatureAction extends ActionSupport {

    private CustomFeatureService customFeatureService;

    @Autowired
    public void setCustomFeatureService(CustomFeatureService customFeatureService) {
        this.customFeatureService = customFeatureService;
    }

    public String execute() throws Exception {
        return SUCCESS;
    }
}
