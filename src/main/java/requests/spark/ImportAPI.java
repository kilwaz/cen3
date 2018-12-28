package requests.spark;

import data.imports.ExcelImporter;
import data.model.objects.json.JSONContainer;
import error.Error;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;
import requests.annotations.RequestName;
import requests.pages.Import;
import spark.Request;
import utils.AppParams;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@RequestName("import")
public class ImportAPI extends SparkRequest {
    private static Logger log = Logger.getLogger(Import.class);

    public JSONContainer post(Request request) {
        File importFile = null;

        try {
            List<FileItem> items = new ServletFileUpload(new DiskFileItemFactory()).parseRequest(request.raw());
            log.info("File size = " + items.size());

            for (FileItem item : items) {
                if (!item.isFormField()) {
                    String fileName = FilenameUtils.getName(item.getName());
                    InputStream fileContent = item.getInputStream();

                    importFile = new File(AppParams.getUploadFileTmpLocation() + fileName);

                    try (FileOutputStream fop = new FileOutputStream(importFile)) {
                        IOUtils.copy(fileContent, fop);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    new ExcelImporter(importFile);

                    log.info("Saved file '" + fileName + "'");
                }
            }
        } catch (FileUploadException | IOException ex) {
            Error.FILE_UPLOAD_ERROR.record().create(ex);
        }

        return new JSONContainer();
    }
}
