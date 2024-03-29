package org.jason.mapmaker2.web.action;

import com.opensymphony.xwork2.ActionSupport;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.struts2.convention.annotation.*;
import org.apache.struts2.interceptor.ParameterAware;
import org.apache.struts2.interceptor.validation.SkipValidation;
import org.apache.struts2.util.ServletContextAware;
import org.jason.mapmaker2.model.CustomFeature;
import org.jason.mapmaker2.model.StateCode;
import org.jason.mapmaker2.model.util.Geonames;
import org.jason.mapmaker2.service.BorderPointService;
import org.jason.mapmaker2.service.CustomFeatureService;
import org.jason.mapmaker2.service.StateCodeService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.ServletContext;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

/**
 * @author Jason Ferguson
 */
@ParentPackage("json-default")
@Namespace("/customFeature")
@Results({
        @Result(name = "success", location = "/WEB-INF/content/admin/customFeature/list.jsp"),
        @Result(name = "input", location = "/WEB-INF/content/admin/customFeature/create.jsp")
})
@SuppressWarnings("unused")
public class CustomFeatureAction extends ActionSupport implements ServletContextAware, ParameterAware {

    // ServletContextAware interface
    private ServletContext servletContext;

    public void setServletContext(ServletContext servletContext) {
        this.servletContext = servletContext;
    }

    // ParameterAware interface
    private Map<String, String[]> parameters;

    public void setParameters(Map<String, String[]> parameters) {
        this.parameters = parameters;
    }

    // Services
    private CustomFeatureService customFeatureService;
    private StateCodeService stateCodeService;
    private BorderPointService borderPointService;

    @Autowired
    public void setCustomFeatureService(CustomFeatureService customFeatureService) {
        this.customFeatureService = customFeatureService;
    }

    @Autowired
    public void setStateCodeService(StateCodeService stateCodeService) {
        this.stateCodeService = stateCodeService;
    }

    @Autowired
    public void setBorderPointService(BorderPointService borderPointService) {
        this.borderPointService = borderPointService;
    }

    // File Upload form fields
    private File fileUpload;
    private String fileUploadContentType;
    private String fileUploadFileName;

    public File getFileUpload() {
        return fileUpload;
    }

    public void setFileUpload(File fileUpload) {
        this.fileUpload = fileUpload;
    }

    public String getFileUploadContentType() {
        return fileUploadContentType;
    }

    public void setFileUploadContentType(String fileUploadContentType) {
        this.fileUploadContentType = fileUploadContentType;
    }

    public String getFileUploadFileName() {
        return fileUploadFileName;
    }

    public void setFileUploadFileName(String fileUploadFileName) {
        this.fileUploadFileName = fileUploadFileName;
    }

    // JSON form fields
    private List<String> cmbFeatureTypes;

    public List<String> getCmbFeatureTypes() {
        return cmbFeatureTypes;
    }

    public void setCmbFeatureTypes(List<String> cmbFeatureTypes) {
        this.cmbFeatureTypes = cmbFeatureTypes;
    }

    @Action("")
    @SkipValidation
    public String execute() throws Exception {
        return SUCCESS;
    }

    @Action("showCreate")
    public String showCreate() throws Exception {
        return INPUT;
    }

    @Action("create")
    public String create() throws Exception {

        // get servlet temp directory
        File tempFileDir = (File) servletContext.getAttribute("javax.servlet.context.tempdir");

        // create list of filenames so we know what to delete later
        List<File> filenames = new ArrayList<File>();

        // upload zip file and make sure it's valid
        File tempZipFile = new File(tempFileDir, fileUploadFileName);
        FileUtils.copyFile(fileUpload, tempZipFile);
        fileUpload.delete();

        // open the zip file and get the entries
        ZipFile zipFile = new ZipFile(tempZipFile, ZipFile.OPEN_READ);
        Enumeration zipFileEntries = zipFile.entries();

        while (zipFileEntries.hasMoreElements()) {

            // get an entry
            ZipEntry entry = (ZipEntry) zipFileEntries.nextElement();
            String entryFileName = entry.getName();

            // add the file to the list. It has to be a File so I can call File().delete()
            File destFile = new File(tempFileDir, entryFileName);
            filenames.add(destFile);

            // make sure the entry isn't a directory
            // TODO: process this recursively if it IS a directory
            if (!entry.isDirectory()) {

                // get the zipentry as a BIS
                BufferedInputStream is = new BufferedInputStream(zipFile.getInputStream(entry));
                int currentByte;
                byte[] data = new byte[2048];

                // create a FOS and BOS for transferring the input into an output file
                FileOutputStream fos = new FileOutputStream(destFile);
                BufferedOutputStream dest = new BufferedOutputStream(fos, 2048);

                // read and write until last byte is encountered
                while ((currentByte = is.read(data, 0, 2048)) != -1) {
                    dest.write(data, 0, currentByte);
                }
                dest.flush();
                dest.close();
                is.close();
            }

            List<CustomFeature> customFeatureList = new ArrayList<CustomFeature>();

            Scanner scanner = new Scanner(new File(tempFileDir, entryFileName));

            while (scanner.hasNextLine()) {

                String line = scanner.nextLine();
                String[] lineArray = line.split("\\|");

                CustomFeature feature = new CustomFeature();

                // workaround, one of the files seems to have bad characters in it
                if (!StringUtils.isNumeric(lineArray[Geonames.FEATUREID])) {
                    continue;
                }
                feature.setFeatureId(Integer.parseInt(lineArray[Geonames.FEATUREID]));

                // just in case a description field is too long, chop it down a bit
                if (lineArray[1].length() > 100) {
                    feature.setFeatureName(lineArray[Geonames.DESCRIPTION].substring(0, 99));
                } else {
                    feature.setFeatureName(lineArray[Geonames.DESCRIPTION]);
                }
                feature.setFeatureClass(lineArray[Geonames.FEATURECLASS]);

                Integer stateCodeId = Integer.parseInt(lineArray[Geonames.STATEFIPS]);
                StateCode stateCode = stateCodeService.getByStateCode(stateCodeId);
                feature.setStateCode(stateCode);

                feature.setLatitude(Float.parseFloat(lineArray[Geonames.LATITUDE]));
                feature.setLongitude(Float.parseFloat(lineArray[Geonames.LONGITUDE]));

                //customFeatureService.save(feature);
                customFeatureList.add(feature);
            }
            scanner.close();


            customFeatureService.saveAll(customFeatureList);
            customFeatureList.clear();
        }
        zipFile.close();

        for (File f : filenames) {
            boolean deleteResult = f.delete();
        }


        return SUCCESS;
    }


    @Action(value = "/getFeaturesJSON", results = {
            @Result(name = "success", type = "json")
    })
    public String getFeatures() throws Exception {

        // get the state code id and the sub code id (from the featureName select box)
        Integer stateCodeId = Integer.parseInt(parameters.get("stateCodeId")[0]);
        Integer subCodeId = Integer.parseInt(parameters.get("subCodeId")[0]);

        Float minLat = borderPointService.getMinimumLatitude(stateCodeId, subCodeId);
        Float maxLat = borderPointService.getMaximumLatitude(stateCodeId, subCodeId);
        Float minLng = borderPointService.getMinimumLongitude(stateCodeId, subCodeId);
        Float maxLng = borderPointService.getMaximumLongitude(stateCodeId, subCodeId);

        List<CustomFeature> customFeatureList = customFeatureService.getCustomFeatures(minLat,
                maxLat,
                minLng,
                maxLng,
                cmbFeatureTypes);

        return SUCCESS;
    }

    @Action(value = "getFeatureTypesJSON", results = {
            @Result(name = "success", type = "json")
    })
    public String getFeatureTypes() throws Exception {

        // get the state code id and the sub code id (from the featureName select box)
        Integer stateCodeId = Integer.parseInt(parameters.get("stateCodeId")[0]);
        Integer subCodeId = Integer.parseInt(parameters.get("subCodeId")[0]);

        Map<String, Float> boundingBox = borderPointService.getBoundingBox(stateCodeId, subCodeId);
        cmbFeatureTypes = customFeatureService.getCustomFeatureTypes(boundingBox);
//        Float minLat = borderPointService.getMinimumLatitude(stateCodeId, subCodeId);
//        Float maxLat = borderPointService.getMaximumLatitude(stateCodeId, subCodeId);
//        Float minLng = borderPointService.getMinimumLongitude(stateCodeId, subCodeId);
//        Float maxLng = borderPointService.getMaximumLongitude(stateCodeId, subCodeId);
//
//        List<String> featureTypes = customFeatureService.getCustomFeatureTypes(minLat, maxLat, minLng, maxLng);


        //cmbFeatureTypes = customFeatureService.getCustomFeatureTypes(minLat, maxLat, minLng, maxLng);

        return SUCCESS;
    }
}
