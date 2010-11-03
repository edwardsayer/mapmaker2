package org.jason.mapmaker2.web.action;

import com.opensymphony.xwork2.ActionSupport;
import com.vividsolutions.jts.geom.MultiPolygon;
import org.apache.commons.compress.archivers.ArchiveInputStream;
import org.apache.commons.compress.archivers.ArchiveStreamFactory;
import org.apache.commons.compress.archivers.zip.ZipArchiveEntry;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;
import org.apache.struts2.convention.annotation.*;
import org.apache.struts2.interceptor.validation.SkipValidation;
import org.apache.struts2.util.ServletContextAware;
import org.geotools.data.FeatureSource;
import org.geotools.data.shapefile.ShapefileDataStore;
import org.geotools.feature.FeatureCollection;
import org.geotools.feature.FeatureIterator;
import org.geotools.feature.simple.SimpleFeatureImpl;
import org.jason.mapmaker2.model.StateCode;
import org.jason.mapmaker2.service.StateCodeService;
import org.opengis.feature.GeometryAttribute;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.ServletContext;
import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author Jason Ferguson
 */
@ParentPackage("json-default")
@Namespace("/stateCode")
@Results({
        @Result(name = "success", location = "/WEB-INF/content/admin/stateCode/list.jsp"),
        @Result(name = "input", location = "/WEB-INF/content/admin/stateCode/create.jsp")
})
@SuppressWarnings("unused")
public class StateCodeAction extends ActionSupport implements ServletContextAware {

    // ServletContextAware interface
    private ServletContext servletContext;

    public void setServletContext(ServletContext servletContext) {
        this.servletContext = servletContext;
    }

    // Service
    private StateCodeService stateCodeService;

    @Autowired
    public void setStateCodeService(StateCodeService stateCodeService) {
        this.stateCodeService = stateCodeService;
    }

    private File fileUpload;
    private String fileUploadContentType;
    private String fileUploadFileName;
    private String fileCaption;

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

    public List<StateCode> getStateCodeList() {
        return stateCodeList;
    }

    public void setStateCodeList(List<StateCode> stateCodeList) {
        this.stateCodeList = stateCodeList;
    }

    private List<StateCode> stateCodeList;

    @Action("")
    @SkipValidation
    public String execute() throws Exception {

        stateCodeList = stateCodeService.getAll();
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

        // process the Shapefile
        // create list of filenames so we know what to delete later
        List<File> filenames = new ArrayList<File>();

        // get servlet temp directory
        File tempFileDir = (File) servletContext.getAttribute("javax.servlet.context.tempdir");

        // upload zip file and make sure it's valid
        File tempZipFile = new File(tempFileDir, fileUploadFileName);
        FileUtils.copyFile(fileUpload, tempZipFile);
        fileUpload.delete();

        String shpFilename = "";
        final InputStream inputStream = new FileInputStream(tempZipFile);
        ArchiveInputStream in = new ArchiveStreamFactory().createArchiveInputStream("zip", inputStream);
        ZipArchiveEntry entry = (ZipArchiveEntry) in.getNextEntry();
        while (entry != null) {

            String entryFileName = entry.getName();
            if (FilenameUtils.getExtension(entryFileName).equals("shp")) {
                shpFilename = entryFileName;
            }

            File outputFile = new File(tempFileDir, entryFileName);
            filenames.add(outputFile);

            OutputStream outputStream = new FileOutputStream(outputFile);
            IOUtils.copy(in, outputStream);
            outputStream.close();
            entry = (ZipArchiveEntry) in.getNextEntry();
        }

        in.close();

//        // open the zip file and get the entries
//        ZipFile zipFile = new ZipFile(tempZipFile, ZipFile.OPEN_READ);
//        Enumeration zipFileEntries = zipFile.entries();
//        //String shpFilename = "";
//
//        // iterate through the entries in the zipfile
//        while (zipFileEntries.hasMoreElements()) {
//
//            // get an entry
//            ZipEntry entry = (ZipEntry) zipFileEntries.nextElement();
//            String entryFileName = entry.getName();
//
//            // get the name of the actual shp file
//            if (entryFileName.indexOf(".shp") > -1) {
//                shpFilename = entryFileName;
//            }
//
//            // add the file to the list. It has to be a File so I can call File().delete()
//            File destFile = new File(tempFileDir, entryFileName);
//            filenames.add(destFile);
//
//            // make sure the entry isn't a directory
//            if (!entry.isDirectory()) {
//
//                // get the zipentry as a BIS
//                BufferedInputStream is = new BufferedInputStream(zipFile.getInputStream(entry));
//                int currentByte;
//                byte[] data = new byte[2048];
//
//                // create a FOS and BOS for transferring the input into an output file
//                FileOutputStream fos = new FileOutputStream(destFile);
//                BufferedOutputStream dest = new BufferedOutputStream(fos, 2048);
//
//                // read and write until last byte is encountered
//                while ((currentByte = is.read(data, 0, 2048)) != -1) {
//                    dest.write(data, 0, currentByte);
//                }
//                dest.flush();
//                dest.close();
//                is.close();
//            }
//
//        }
//        zipFile.close();

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
                    // process a state
                    StateCode stateCode = new StateCode();
                    stateCode.setStateCode(Integer.parseInt((String) feature.getAttribute(3)));
                    stateCode.setStateAbbr((String) feature.getAttribute(5));
                    stateCode.setStateName((String) feature.getAttribute(6));

                    StateCode result = stateCodeService.save(stateCode);

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

    @Action(value = "getStateCodes", results = {
            @Result(name = "success", type = "json")
    })
    public String getStateCodes() throws Exception {
        stateCodeList = stateCodeService.getAll();
        Collections.sort(stateCodeList);

        return SUCCESS;
    }
//    @Action(value = "getStateCodesJson", results = {
//            @Result(name = "success", type = "json")
//    })
//    public String getStateCodesJson() throws Exception {
//        stateCodeList = stateCodeService.getAll();
//        Collections.sort(stateCodeList);
//
//        if (stateCodeId != null) {
//            StateCode stateCode = stateCodeService.getById(stateCodeId);
//            subCodeList = subCodeService.getByStateCode(stateCode);
//        }
//
//        return SUCCESS;
//    }
}
