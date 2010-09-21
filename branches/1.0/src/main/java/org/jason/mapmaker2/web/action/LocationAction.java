package org.jason.mapmaker2.web.action;

import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.convention.annotation.*;
import org.jason.mapmaker2.model.FeatureTypeDescription;
import org.jason.mapmaker2.model.Location;
import org.jason.mapmaker2.model.State;
import org.jason.mapmaker2.service.FeatureTypeDescriptionService;
import org.jason.mapmaker2.service.LocationService;
import org.jason.mapmaker2.service.ServiceException;
import org.jason.mapmaker2.service.StateService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @author Jason Ferguson
 */
@ParentPackage("struts-default")
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

    private StateService stateService;

    @Autowired
    public void setStateService(StateService stateService) {
        this.stateService = stateService;
    }

    private FeatureTypeDescriptionService featureTypeDescriptionService;

    @Autowired
    public void setFeatureTypeDescriptionService(FeatureTypeDescriptionService featureTypeDescriptionService) {
        this.featureTypeDescriptionService = featureTypeDescriptionService;
    }

    private int stateId;
    private String locationName;
    private String fipsCode;
    private Integer featureClassId;

    private List<Location> locations;
    private List<FeatureTypeDescription> featureTypeDescriptions;

    public int getStateId() {
        return stateId;
    }

    public void setStateId(int stateId) {
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

    public List<FeatureTypeDescription> getFeatureTypeDescriptions() {
        return featureTypeDescriptions;
    }

    public void setFeatureTypeDescriptions(List<FeatureTypeDescription> featureTypeDescriptions) {
        this.featureTypeDescriptions = featureTypeDescriptions;
    }

    @Action("")
    public String execute() throws Exception {

        locations = locationService.getAll();
        return SUCCESS;
    }

    private List<State> states;

    public List<State> getStates() {
        return states;
    }

    public void setStates(List<State> states) {
        this.states = states;
    }

    @Action("showCreate")
    public String showCreateLocation() throws Exception {

        featureTypeDescriptions = featureTypeDescriptionService.getAll();
        states = stateService.getAll();
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
