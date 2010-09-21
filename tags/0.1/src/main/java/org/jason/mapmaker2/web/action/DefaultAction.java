package org.jason.mapmaker2.web.action;

import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;

/**
 * @author Jason Ferguson
 */
@Namespace("/")
@Result(name = "success", location = "/WEB-INF/content/makeMap.jsp")
public class DefaultAction extends ActionSupport {

    @Action("")
    public String execute() throws Exception {

        return SUCCESS;
    }

    @Action(value = "menu", results =
        @Result(name="success", location = "/WEB-INF/content/admin/menu.jsp")
    )
    public String showMenu() throws Exception {

        return SUCCESS;
    }
}
