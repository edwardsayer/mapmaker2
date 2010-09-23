package org.jason.mapmaker2.web.action;

import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.convention.annotation.*;
import org.jason.mapmaker2.service.StateCodeService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author Jason Ferguson
 */
@ParentPackage("struts-default")
@Namespace("/stateCode")
@Results({
        @Result(name="success", location = "/WEB-INF/content/admin/stateCode/list.jsp"),
        @Result(name="input", location = "/WEB-INF/content/admin/stateCode/create.jsp")
})
public class StateCodeAction extends ActionSupport {

    private StateCodeService stateCodeService;

    @Autowired
    public void setStateCodeService(StateCodeService stateCodeService) {
        this.stateCodeService = stateCodeService;
    }

    @Action("")
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
