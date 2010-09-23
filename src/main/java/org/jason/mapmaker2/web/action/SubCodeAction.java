package org.jason.mapmaker2.web.action;

import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.jason.mapmaker2.service.SubCodeService;

/**
 * @author Jason Ferguson
 */
@ParentPackage("struts-default")
@Namespace("/subCode")
@Results({
        @Result(name="success", location = "/WEB-INF/content/admin/subCode/list.jsp"),
        @Result(name="input", location = "/WEB-INF/content/admin/subCode/create.jsp")
})
public class SubCodeAction extends ActionSupport {

    private SubCodeService subCodeService;

    public void setSubCodeService(SubCodeService subCodeService) {
        this.subCodeService = subCodeService;
    }

    public String execute() throws Exception {
        return SUCCESS;
    }

    public String showCreate() throws Exception {
        return INPUT;
    }

    public String create() throws Exception {
        return SUCCESS;
    }
}
