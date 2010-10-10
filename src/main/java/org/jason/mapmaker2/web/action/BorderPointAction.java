package org.jason.mapmaker2.web.action;

import com.opensymphony.xwork2.ActionSupport;
import com.vividsolutions.jts.geom.Coordinate;
import com.vividsolutions.jts.geom.Geometry;
import com.vividsolutions.jts.geom.MultiPolygon;
import org.apache.commons.io.FileUtils;
import org.apache.struts2.convention.annotation.*;
import org.apache.struts2.interceptor.ParameterAware;
import org.apache.struts2.interceptor.validation.SkipValidation;
import org.apache.struts2.util.ServletContextAware;
import org.geotools.data.FeatureSource;
import org.geotools.data.shapefile.ShapefileDataStore;
import org.geotools.feature.FeatureCollection;
import org.geotools.feature.FeatureIterator;
import org.geotools.feature.simple.SimpleFeatureImpl;
import org.jason.mapmaker2.model.BorderPoint;
import org.jason.mapmaker2.model.StateCode;
import org.jason.mapmaker2.model.SubCode;
import org.jason.mapmaker2.model.TLFeatureType;
import org.jason.mapmaker2.service.BorderPointService;
import org.jason.mapmaker2.service.StateCodeService;
import org.jason.mapmaker2.service.SubCodeService;
import org.jason.mapmaker2.service.TLFeatureTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StopWatch;

import javax.servlet.ServletContext;
import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

/**
 * BorderPointAction.java
 * <p/>
 * Action class for dealing with BorderPoint objects
 * <p/>
 * Class implements the ServletContextAware interface so that it can obtain the location of the tmp directory
 *
 * @author Jason Ferguson
 */
@ParentPackage("struts-default")
@Namespace("/borderPoint")
@Results({
        @Result(name = "success", location = "/WEB-INF/content/admin/borderPoint/list.jsp"),
        @Result(name = "input", location = "/WEB-INF/content/admin/borderPoint/create2.jsp")
})
@SuppressWarnings("unused")
public class BorderPointAction extends ActionSupport implements ParameterAware, ServletContextAware {

    // Interface ServletContextAware
    private ServletContext servletContext;

    public void setServletContext(ServletContext servletContext) {
        this.servletContext = servletContext;
    }

    // Interface ParameterAware
    private Map<String, String[]> parameters;

    public void setParameters(Map<String, String[]> parameters) {
        this.parameters = parameters;
    }

    // Services
    private BorderPointService borderPointService;
    private StateCodeService stateCodeService;
    private SubCodeService subCodeService;
    private TLFeatureTypeService tlFeatureTypeService;

    @Autowired
    public void setBorderPointService(BorderPointService borderPointService) {
        this.borderPointService = borderPointService;
    }

    @Autowired
    public void setStateCodeService(StateCodeService stateCodeService) {
        this.stateCodeService = stateCodeService;
    }

    @Autowired
    public void setSubCodeService(SubCodeService subCodeService) {
        this.subCodeService = subCodeService;
    }

    @Autowired
    public void setTlFeatureTypeService(TLFeatureTypeService tlFeatureTypeService) {
        this.tlFeatureTypeService = tlFeatureTypeService;
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
                StateCode stateCode = null;
                Integer stateCodeId = null;
                while (iterator.hasNext()) {
                    SimpleFeatureImpl feature = (SimpleFeatureImpl) iterator.next();

                    Integer stateCodeId2 = Integer.parseInt((String) feature.getAttribute("STATE"));
                    stateCode = stateCodeService.getByStateCode(stateCodeId2);
                    Integer subCodeId = null;

                    String LSAD_TRANS = (String) feature.getAttribute("LSAD_TRANS");
                    Integer subCodeId3 = null;
                    if (LSAD_TRANS.equals("County")) {
                        String subCodeId2 = (String) feature.getAttribute("COUNTY");
                        subCodeId3 = Integer.parseInt(subCodeId2);
                    }

                    String subCodeDescription = (String) feature.getAttribute("NAME");

                    SubCode subCode = new SubCode(stateCode, subCodeId3, subCodeDescription, LSAD_TRANS);
                    subCodeService.save(subCode);

                    MultiPolygon mp = (MultiPolygon) feature.getDefaultGeometry();
                    Geometry g = mp.getBoundary();
                    Coordinate[] coordinates = g.getCoordinates();

                    List<BorderPoint> bpList = new ArrayList<BorderPoint>();
                    for (Coordinate c : coordinates) {

                        Float lng = new Float(c.x);
                        Float lat = new Float(c.y);

                        if (lat != null && lng != null) {
                            BorderPoint bp = new BorderPoint(stateCode, subCode, lat, lng);
                            bpList.add(bp);

                        }
                    }

                    borderPointService.saveAll(bpList);

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

    @Action("import")
    public String importShapeFileToBorderPoints() throws Exception {

        // get servlet temp directory
        File tempFileDir = (File) servletContext.getAttribute("javax.servlet.context.tempdir");

        if (parameters.get("tlftId")[0] == null) {
            addActionError("Cannot get id for TIGER Line Feature Type to import!");
            return SUCCESS;
        }
        Integer tlftId = Integer.parseInt(parameters.get("tlftId")[0]);
        TLFeatureType tlft = tlFeatureTypeService.getById(tlftId);

        URL url = null;
        File outputFile = new File(tempFileDir,tlft.getFilename());
        try {
            url = new URL(tlft.getImportUrl());
            URLConnection uc = url.openConnection();
            uc.connect();
            OutputStream os = new FileOutputStream(outputFile);
            InputStream is = url.openStream();
            byte[] bytes = new byte[2048];
            int l;
            while((l = is.read()) != -1) {
                os.write(l);
            }
            is.close();
            os.close();
        } catch (MalformedURLException e) {
            addActionError("Error with getting URL to import!");
            return SUCCESS;
        } catch (IOException e) {
            addActionError("IOException!");
        }

        tlft.setImported(true);
        tlFeatureTypeService.save(tlft);
        //outputFile.delete();

        return SUCCESS;
    }
}
