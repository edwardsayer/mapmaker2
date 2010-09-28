package org.jason.mapmaker2.web.action;

import com.opensymphony.xwork2.ActionSupport;
import com.vividsolutions.jts.geom.Coordinate;
import com.vividsolutions.jts.geom.Geometry;
import com.vividsolutions.jts.geom.MultiPolygon;
import org.apache.commons.io.FileUtils;
import org.apache.commons.math.util.MathUtils;
import org.apache.struts2.convention.annotation.*;
import org.apache.struts2.interceptor.validation.SkipValidation;
import org.apache.struts2.util.ServletContextAware;
import org.geotools.data.FeatureSource;
import org.geotools.data.shapefile.ShapefileDataStore;
import org.geotools.feature.FeatureCollection;
import org.geotools.feature.FeatureIterator;
import org.geotools.feature.simple.SimpleFeatureImpl;
import org.jason.mapmaker2.model.BorderPoint;
import org.jason.mapmaker2.model.State;
import org.jason.mapmaker2.model.StateCode;
import org.jason.mapmaker2.model.SubCode;
import org.jason.mapmaker2.service.BorderPointService;
import org.jason.mapmaker2.service.StateCodeService;
import org.jason.mapmaker2.service.SubCodeService;
import org.opengis.feature.GeometryAttribute;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StopWatch;

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
@SuppressWarnings("unused")
public class BorderPointAction extends ActionSupport implements ServletContextAware {

    ServletContext servletContext;

    public void setServletContext(ServletContext servletContext) {
        this.servletContext = servletContext;
    }

    private BorderPointService borderPointService;

    @Autowired
    public void setBorderPointService(BorderPointService borderPointService) {
        this.borderPointService = borderPointService;
    }

    private StateCodeService stateCodeService;
    private SubCodeService subCodeService;

    @Autowired
    public void setStateCodeService(StateCodeService stateCodeService) {
        this.stateCodeService = stateCodeService;
    }

    @Autowired
    public void setSubCodeService(SubCodeService subCodeService) {
        this.subCodeService = subCodeService;
    }

    private Integer stateId;
    private Integer subCodeId;
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

    public Integer getSubCodeId() {
        return subCodeId;
    }

    public void setSubCodeId(Integer subCodeId) {
        this.subCodeId = subCodeId;
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

    public List<State> getStatesList() {
        return statesList;
    }

    public void setStatesList(List<State> statesList) {
        this.statesList = statesList;
    }

    @Action("")
    @SkipValidation
    public String execute() throws Exception {
        return SUCCESS;
    }

    @Action("showCreate")
    @SkipValidation
    public String showCreate() throws Exception {

        return INPUT;
    }

    @SuppressWarnings({"deprecation", "unchecked"})
    @Action("create")
    public String create() throws Exception {

        StopWatch timer = new StopWatch();
        timer.start();
        // get the subcode
        if (subCodeId > -1) {
            SubCode subCode = subCodeService.getById(subCodeId);
        }

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

                // FeatureCollection doesn't implement Iterator, so no forEach() loop for me. Jerks.
                while (iterator.hasNext()) {
                    SimpleFeatureImpl feature = (SimpleFeatureImpl) iterator.next();

                    GeometryAttribute geometryAttribute = feature.getDefaultGeometryProperty();
                    MultiPolygon multiPolygon = (MultiPolygon) geometryAttribute.getValue();
                    // TODO: CREATE THE BORDERPOINT HERE!!!
                    // process a county
                    String lineType = (String) feature.getAttribute(9);
                    if (lineType.equals("G4020")) {
                        // get the StateCode
                        int stateCodeId = Integer.parseInt((String) feature.getAttribute(1));
                        StateCode stateCode = stateCodeService.getByStateCode(stateCodeId);

                        int subCodeId = Integer.parseInt((String) feature.getAttribute(2));
                        String subCodeDescription = (String) feature.getAttribute(5);

                        SubCode subCode = new SubCode(stateCode, subCodeId, subCodeDescription, "County");
                        SubCode result = subCodeService.save(subCode);

                        MultiPolygon mp = (MultiPolygon) feature.getAttribute(0);
                        Geometry g = mp.getGeometryN(0);
                        Coordinate[] coordinates = g.getCoordinates();

                        Set<BorderPoint> bpSet = new HashSet<BorderPoint>();
                        for (Coordinate c : coordinates) {
                            Float lat = new Float(c.x);
                            Float lng = new Float(c.y);

                            lat = MathUtils.round(lat, 3);
                            lng = MathUtils.round(lng, 3);

                            if (lat != null && lng != null) {
                                BorderPoint bp = new BorderPoint(stateCode, subCode, lat, lng);
                                bpSet.add(bp);
                            }
                        }

                        borderPointService.saveAll(bpSet);

                    }
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
        }

        shpFile.delete();

        timer.stop();
        System.out.println("Executed import in " + timer.getTotalTimeSeconds() + " seconds");

        return SUCCESS;
    }
}
