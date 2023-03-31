package requests.spark.websockets.objects.messages.dataobjects;

import requests.spark.websockets.objects.messages.mapping.WSDataIncoming;
import requests.spark.websockets.objects.messages.mapping.WSDataOutgoing;

public class FileUploadData extends WebSocketData {
    @WSDataIncoming
    private String fileName = null;

    @WSDataIncoming
    private String fileType = null;

    @WSDataIncoming
    private Integer fileSize = null;

    @WSDataOutgoing
    private Integer numberOfPieces = null;

    @WSDataOutgoing
    private Integer frameSize = null;

    @WSDataOutgoing
    private Integer fileReference = null;

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public Integer getNumberOfPieces() {
        return numberOfPieces;
    }

    public void setNumberOfPieces(Integer numberOfPieces) {
        this.numberOfPieces = numberOfPieces;
    }

    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    public Integer getFileReference() {
        return fileReference;
    }

    public void setFileReference(Integer fileReference) {
        this.fileReference = fileReference;
    }

    public Integer getFileSize() {
        return fileSize;
    }

    public void setFileSize(Integer fileSize) {
        this.fileSize = fileSize;
    }

    public Integer getFrameSize() {
        return frameSize;
    }

    public void setFrameSize(Integer frameSize) {
        this.frameSize = frameSize;
    }
}
