package utils;

import requests.spark.websockets.objects.messages.dataitems.DownloadFileDataItem;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.Base64;

public class DownloadFileHelper {

    private File file;
    private String downloadFileName;
    private String content;

    public DownloadFileHelper file(File file) {
        this.file = file;
        return this;
    }

    public DownloadFileHelper downloadFileName(String downloadFileName) {
        this.downloadFileName = downloadFileName;
        return this;
    }

    public DownloadFileHelper content(String content) {
        this.content = content;
        return this;
    }

    public DownloadFileDataItem buildDownloadFileDataItem() {
        String encodedString = "";
        if (file != null) {
            try {
                byte[] fileContent = Files.readAllBytes(file.toPath());
                encodedString = Base64.getEncoder().encodeToString(fileContent);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } else if (content != null) {
            encodedString = Base64.getEncoder().encodeToString(content.getBytes(StandardCharsets.UTF_8));
        }

        return new DownloadFileDataItem(encodedString, downloadFileName);
    }
}