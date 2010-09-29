package org.jason.mapmaker2.web.action;

import com.opensymphony.xwork2.ActionSupport;
import org.apache.commons.io.FileUtils;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.apache.struts2.util.ServletContextAware;
import org.geotools.data.FeatureSource;
import org.geotools.data.shapefile.ShapefileDataStore;
import org.geotools.feature.FeatureCollection;
import org.geotools.feature.FeatureIterator;
import org.opengis.feature.Feature;

import javax.servlet.ServletContext;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

/**
 * ShapeFileAction.java
 * <p/>
 * (Hot ShapeFile action... does that mean ActionSupport is the "wingman"?)
 * <p/>
 * Anyways, this is currently a throwaway action for me to use to figure out how to deal with shapefiles
 *
 * @author Jason Ferguson
 */
@Namespace("/shapefile")
@Results({
        @Result(name = "success", location = "/WEB-INF/content/admin/shapefile/uploadShapefile.jsp")
})
public class ShapeFileAction extends ActionSupport implements ServletContextAware {

    ServletContext servletContext;

    public void setServletContext(ServletContext servletContext) {
        this.servletContext = servletContext;
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

    @Action("")
    public String execute() throws Exception {

        return SUCCESS;
    }

    @Action(value = "/processShapefile", results =
            {
                    @Result(name = "success", location = "/WEB-INF/content/admin/shapefile/uploadShapefile.jsp"),
                    @Result(name = "input", location = "/WEB-INF/content/admin/shapefile/uploadShapefile.jsp")
            }
    )
    public String processShapefile() throws Exception {
        List<File> filenames = new ArrayList<File>();

        // get servlet temp directory
        File tempFileDir = (File) servletContext.getAttribute("javax.servlet.context.tempdir");

        // upload zip file and make sure it's valid
        File tempZipFile = new File(tempFileDir, fileUploadFileName);
        FileUtils.copyFile(fileUpload, tempZipFile);

        ZipFile zipFile = new ZipFile(tempZipFile, ZipFile.OPEN_READ);
        Enumeration zipFileEntries = zipFile.entries();

        String shpFilename = "";

        while (zipFileEntries.hasMoreElements()) {
            ZipEntry entry = (ZipEntry) zipFileEntries.nextElement();
            String entryFileName = entry.getName();

            // get the name of the actual shp file
            if (entryFileName.indexOf(".shp") > -1) {
                shpFilename = entryFileName;
            }
            File destFile = new File(tempFileDir, entryFileName);
            filenames.add(destFile);

            if (!entry.isDirectory()) {
                BufferedInputStream is = new BufferedInputStream(zipFile.getInputStream(entry));
                int currentByte;
                byte[] data = new byte[2048];

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


        ShapefileDataStore store = new ShapefileDataStore(shpFile.toURL());
        String[] typenames = store.getTypeNames();

        for (String name : typenames) {
            FeatureSource featureSource = store.getFeatureSource(name);
            FeatureCollection featureCollection = featureSource.getFeatures();
            FeatureIterator iterator = featureCollection.features();

            try {
                int counter = 0;
                while (iterator.hasNext()) {
                    Feature feature = iterator.next();
                    //GeometryAttribute geometryAttribute = feature.getDefaultGeometryProperty();
                    counter++;

                }
            }
            finally {
                featureCollection.close(iterator);
            }
        }

        System.gc();        // argh. Due to a bug in geotools 2.6.5 and previous, the prj file cannot be removed until
                            // garbage collection occurs. I _hate_ explicitly calling System.gc()
        
        for (File f : filenames) {

            boolean deleteResult = f.delete();
            System.out.println(f.getName() + ": " + deleteResult);
        }
        shpFile.delete();

        addActionMessage("Successfully processed shapefile");
        return SUCCESS;
    }
}
