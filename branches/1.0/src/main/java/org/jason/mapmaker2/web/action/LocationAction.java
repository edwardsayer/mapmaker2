package org.jason.mapmaker2.web.action;

import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.jason.mapmaker2.model.Location;
import org.jason.mapmaker2.service.LocationService;
import org.jason.mapmaker2.service.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @author Jason Ferguson
 */
@Namespace("/location")
@Results({
        @Result(name="success", location = "/WEB-INF/content/admin/location/locationOverview.jsp"),
        @Result(name="input", location = "/WEB-INF/content/admin/location/create.jsp")
})
public class LocationAction extends ActionSupport {

    private LocationService locationService;

    @Autowired
    public void setLocationService(LocationService locationService) {
        this.locationService = locationService;
    }

    private Integer stateId;
    private String locationName;
    private String fipsCode;
    private Integer featureClassId;

    private List<Location> locations;

    public Integer getStateId() {
        return stateId;
    }

    public void setStateId(Integer stateId) {
        this.stateId = stateId;
    }

    public String getLocationName() {
        return locationName;
    }

    public void setLocationName(String locationName) {
        this.locationName = locationName;
    }

    public String getFipsCode() {
        return fipsCode;
    }

    public void setFipsCode(String fipsCode) {
        this.fipsCode = fipsCode;
    }

    public Integer getFeatureClassId() {
        return featureClassId;
    }

    public void setFeatureClassId(Integer featureClassId) {
        this.featureClassId = featureClassId;
    }

    public List<Location> getLocations() {
        return locations;
    }

    public void setLocations(List<Location> locations) {
        this.locations = locations;
    }

    @Action("/")
    public String execute() throws Exception {

        locations = locationService.getAll();
        return SUCCESS;
    }

    @Action("showCreate")
    public String showCreateLocation() throws Exception {

        return INPUT;
    }

    @Action("create")
    public String createLocation() throws Exception {

        Location location;
        try {
            location = locationService.createLocation(stateId, locationName, fipsCode, featureClassId);

        } catch (ServiceException e) {
            addActionError("Unable to create location: " + e.getMessage());
            return INPUT;
        }

        Location result = locationService.save(location);
        if (result == null) {
            addActionError("Unable to save new location!");
            return INPUT;
        }
        return SUCCESS;
    }
}
