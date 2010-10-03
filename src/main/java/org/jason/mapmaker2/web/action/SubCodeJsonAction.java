package org.jason.mapmaker2.web.action;

import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.jason.mapmaker2.service.SubCodeService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author TSgt Jason Ferguson
 */
@ParentPackage("json-default")
@Namespace("/subCode")
public class SubCodeJsonAction extends ActionSupport {

    private SubCodeService subCodeService;

    @Autowired
    public void setSubCodeService(SubCodeService subCodeService) {
        this.subCodeService = subCodeService;
    }

    public String execute() throws Exception {
        return SUCCESS;
    }

    @Action(value="subCodeByStateJson", results = {
            @Result(name="success", type = "json")
    })
    public String getSubCodesByStateJson() throws Exception {

        return SUCCESS;
    }
}
