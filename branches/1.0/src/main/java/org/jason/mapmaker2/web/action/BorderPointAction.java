package org.jason.mapmaker2.web.action;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.Preparable;
import org.apache.commons.io.FileUtils;
import org.apache.struts2.convention.annotation.*;
import org.apache.struts2.interceptor.validation.SkipValidation;
import org.apache.struts2.util.ServletContextAware;
import org.geotools.data.FeatureSource;
import org.geotools.data.shapefile.ShapefileDataStore;
import org.geotools.feature.FeatureCollection;
import org.geotools.feature.FeatureIterator;
import org.jason.mapmaker2.model.State;
import org.jason.mapmaker2.model.TigerFeatureType;
import org.jason.mapmaker2.service.BorderPointService;
import org.jason.mapmaker2.service.StateService;
import org.jason.mapmaker2.service.TigerFeatureTypeService;
import org.opengis.feature.Feature;
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
@ParentPackage("struts-default")
@Namespace("/borderPoint")
@Results({
        @Result(name = "success", location = "/WEB-INF/content/admin/borderPoint/list.jsp"),
        @Result(name = "input", location = "/WEB-INF/content/admin/borderPoint/create2.jsp")
})

public class BorderPointAction extends ActionSupport implements ServletContextAware, Preparable {

    ServletContext servletContext;

    public void setServletContext(ServletContext servletContext) {
        this.servletContext = servletContext;
    }

    private BorderPointService borderPointService;

    @Autowired
    public void setBorderPointService(BorderPointService borderPointService) {
        this.borderPointService = borderPointService;
    }

    private StateService stateService;
    private TigerFeatureTypeService tigerFeatureTypeService;

    @Autowired
    public void setStateService(StateService stateService) {
        this.stateService = stateService;
    }

    @Autowired
    public void setTigerFeatureTypeService(TigerFeatureTypeService tigerFeatureTypeService) {
        this.tigerFeatureTypeService = tigerFeatureTypeService;
    }

    private Integer stateId;
    private Integer featureTypeId;
    private File fileUpload;
    private String fileUploadContentType;
    private String fileUploadFileName;
    private String fileCaption;

    public Integer getStateId() {
        return stateId;
    }

    public void setStateId(Integer stateId) {
        this.stateId = stateId;
    }

    public Integer getFeatureTypeId() {
        return featureTypeId;
    }

    public void setFeatureTypeId(Integer featureTypeId) {
        this.featureTypeId = featureTypeId;
    }

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

    public String getFileCaption() {
        return fileCaption;
    }

    public void setFileCaption(String fileCaption) {
        this.fileCaption = fileCaption;
    }

    private List<State> statesList;
    private List<TigerFeatureType> tigerFeatureTypes;

    public List<State> getStatesList() {
        return statesList;
    }

    public void setStatesList(List<State> statesList) {
        this.statesList = statesList;
    }

    public List<TigerFeatureType> getTigerFeatureTypes() {
        return tigerFeatureTypes;
    }

    public void setTigerFeatureTypes(List<TigerFeatureType> tigerFeatureTypes) {
        this.tigerFeatureTypes = tigerFeatureTypes;
    }

    public void prepare() throws Exception {
        statesList = stateService.getAll();
        tigerFeatureTypes = tigerFeatureTypeService.getAll();
    }

    @Action("")
    @SkipValidation
    public String execute() throws Exception {
        return SUCCESS;
    }

    @Action("showCreate")
    @SkipValidation
    public String showCreate() throws Exception {

        Collections.sort(tigerFeatureTypes, new Comparator<TigerFeatureType>() {
            public int compare(TigerFeatureType o1, TigerFeatureType o2) {
                return o1.getLabel().compareTo(o2.getLabel());
            }
        });
        return INPUT;
    }

    @Action("create")
    public String create() throws Exception {

        // get the state
        State state = stateService.getById(stateId);

        // get the tiger feature type
        TigerFeatureType tft = tigerFeatureTypeService.getById(featureTypeId);

        // process the Shapefile
        // create list of filenames so we know what to delete later
        List<File> filenames = new ArrayList<File>();

        // get servlet temp directory
        File tempFileDir = (File) servletContext.getAttribute("javax.servlet.context.tempdir");

        // upload zip file and make sure it's valid
        File tempZipFile = new File(tempFileDir, fileUploadFileName);
        FileUtils.copyFile(fileUpload, tempZipFile);

        // open the zip file and get the entries
        ZipFile zipFile = new ZipFile(tempZipFile, ZipFile.OPEN_READ);
        Enumeration zipFileEntries = zipFile.entries();

        String shpFilename = "";

        // iterate through the entries in the zipfile
        while (zipFileEntries.hasMoreElements()) {

            // get an entry
            ZipEntry entry = (ZipEntry) zipFileEntries.nextElement();
            String entryFileName = entry.getName();

            // get the name of the actual shp file
            if (entryFileName.indexOf(".shp") > -1) {
                shpFilename = entryFileName;
            }

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

        }
        zipFile.close();

        File shpFile = new File(tempFileDir, shpFilename);

        // Create a ShapefileDataStore from the shapefile. This would be much easier if I could just use some sort
        // of stream and not have to deal with temporary files
        ShapefileDataStore store = new ShapefileDataStore(shpFile.toURL());
        String[] typenames = store.getTypeNames();

        for (String name : typenames) {
            FeatureSource featureSource = store.getFeatureSource(name);
            FeatureCollection featureCollection = featureSource.getFeatures();
            FeatureIterator iterator = featureCollection.features();

            try {
                int counter = 0;

                // FeatureCollection doesn't implement Iterator, so no forEach() loop for me. Jerks.
                while (iterator.hasNext()) {
                    Feature feature = iterator.next();
                    //GeometryAttribute geometryAttribute = feature.getDefaultGeometryProperty();

                    // TODO: CREATE THE BORDERPOINT HERE!!!
                    counter++;

                }
            }
            finally {
                featureCollection.close(iterator);
            }
        }
        System.gc();        // argh. Due to a bug in geotools 2.6.5 and previous, the prj file cannot be removed until
        // garbage collection occurs. I _hate_ explicitly calling System.gc()

        // delete the temporary files
        for (File f : filenames) {

            boolean deleteResult = f.delete();
            System.out.println(f.getName() + ": " + deleteResult);
        }

        shpFile.delete();
        return SUCCESS;
    }
}
