package org.jason.mapmaker2.web.action;

import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.jason.mapmaker2.model.CensusCode;
import org.jason.mapmaker2.service.CensusCodeService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @author Jason Ferguson
 */
@ParentPackage("json-default")
@Namespace("/censusCode")
public class CensusCodeJsonAction extends ActionSupport {

    private CensusCodeService censusCodeService;

    @Autowired
    public void setCensusCodeService(CensusCodeService censusCodeService) {
        this.censusCodeService = censusCodeService;
    }

    private List<CensusCode> censusCodes;

    public List<CensusCode> getCensusCodes() {
        return censusCodes;
    }

    public void setCensusCodes(List<CensusCode> censusCodes) {
        this.censusCodes = censusCodes;
    }

    public String execute() throws Exception {
        return SUCCESS;
    }

    @Action(value = "censusCodesJson", results = {
            @Result(name="success", type = "json")
    })
    public String getCensusCodesJson() throws Exception {

        censusCodes = censusCodeService.getAll();
        return SUCCESS;

    }
}
