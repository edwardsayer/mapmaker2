package org.jason.mapmaker2.web.action;

import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.jason.mapmaker2.model.StateCode;
import org.jason.mapmaker2.service.StateCodeService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Collections;
import java.util.List;

/**
 * @author Jason Ferguson
 */
@ParentPackage("json-default")
@Namespace("/")
@Result(name = "success", location = "/WEB-INF/content/makeMap.jsp")
public class DefaultAction extends ActionSupport {

    private StateCodeService stateCodeService;

    @Autowired
    public void setStateCodeService(StateCodeService stateCodeService) {
        this.stateCodeService = stateCodeService;
    }

    List<StateCode> stateCodeList;

    public List<StateCode> getStateCodeList() {
        return stateCodeList;
    }

    public void setStateCodeList(List<StateCode> stateCodeList) {
        this.stateCodeList = stateCodeList;
    }

    @Action("")
    public String execute() throws Exception {

        stateCodeList = stateCodeService.getAll();
        Collections.sort(stateCodeList);

        return SUCCESS;
    }

    @Action(value = "menu", results =
    @Result(name = "success", location = "/WEB-INF/content/admin/menu.jsp")
    )
    public String showMenu() throws Exception {

        return SUCCESS;
    }

}
