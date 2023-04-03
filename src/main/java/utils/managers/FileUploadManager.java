package utils.managers;

import log.AppLogger;
import org.apache.logging.log4j.Logger;
import requests.spark.websockets.objects.messages.dataobjects.FileUploadData;

import java.io.*;
import java.util.Arrays;
import java.util.HashMap;

public class FileUploadManager {
    private static FileUploadManager instance;
    private static HashMap<Integer, FileTransferStatus> transferStatusHashMap = new HashMap<>();

    private static final Integer FRAME_SIZE_BYTES = 50000; // 50 KBs

    private static final Logger log = AppLogger.logger();

    public FileUploadManager() {
        instance = this;
    }

    public static FileUploadManager getInstance() {
        if (instance == null) {
            new FileUploadManager();
        }
        return instance;
    }

    public void processNewFileRequest(FileUploadData fileUploadData) {
        fileUploadData.setFileReference(1);
        fileUploadData.setFrameSize(FRAME_SIZE_BYTES);
        fileUploadData.setNumberOfPieces(Math.abs(fileUploadData.getFileSize() / FRAME_SIZE_BYTES) + 1);

        log.info("File size is " + fileUploadData.getFileSize());

        FileTransferStatus fileTransferStatus = new FileTransferStatus(fileUploadData.getFileName(), fileUploadData.getFileType(), fileUploadData.getFileSize(), fileUploadData.getFileReference(), fileUploadData.getNumberOfPieces(), fileUploadData.getFrameSize());
        transferStatusHashMap.put(1, fileTransferStatus);
    }

    public void processBuffer(byte[] bytes) {
        byte[] referenceBytes = Arrays.copyOfRange(bytes, 0, 8);
        byte[] fileData = Arrays.copyOfRange(bytes, 8, bytes.length);

        Integer pieceNum = (Byte.toUnsignedInt(referenceBytes[1]) * 254) + Byte.toUnsignedInt(referenceBytes[0]);
        Integer fileReference = Byte.toUnsignedInt(referenceBytes[2]);

        FileTransferStatus fileTransferStatus = transferStatusHashMap.get(fileReference);

        if (fileTransferStatus != null) {
            try {
                RandomAccessFile file = new RandomAccessFile("C:\\Users\\alex\\Downloads\\Arup HR Roles 2023\\Uploads\\" + fileTransferStatus.getFileName(), "rw");
                long offset = (long) pieceNum * fileTransferStatus.getFrameSize();
                file.seek(offset);
                file.write(fileData);
                file.close();
            } catch (Exception ex) {
                log.error("Exception: ", ex);
            }
        } else {
            log.error("No file reference found for received file frame");
        }
    }

    private static class FileTransferStatus {
        private String fileName;
        private String fileType;
        private Integer fileSize; // 2.1GB max file size
        private Integer fileReference;
        private Integer numberOfPiece;
        private Integer frameSize;

        public FileTransferStatus(String fileName, String fileType, Integer fileSize, Integer fileReference, Integer numberOfPiece, Integer frameSize) {
            this.fileName = fileName;
            this.fileType = fileType;
            this.fileSize = fileSize;
            this.fileReference = fileReference;
            this.numberOfPiece = numberOfPiece;
            this.frameSize = frameSize;
        }

        public String getFileName() {
            return fileName;
        }

        public void setFileName(String fileName) {
            this.fileName = fileName;
        }

        public String getFileType() {
            return fileType;
        }

        public void setFileType(String fileType) {
            this.fileType = fileType;
        }

        public Integer getFileSize() {
            return fileSize;
        }

        public void setFileSize(Integer fileSize) {
            this.fileSize = fileSize;
        }

        public Integer getFileReference() {
            return fileReference;
        }

        public void setFileReference(Integer fileReference) {
            this.fileReference = fileReference;
        }

        public Integer getNumberOfPiece() {
            return numberOfPiece;
        }

        public void setNumberOfPiece(Integer numberOfPiece) {
            this.numberOfPiece = numberOfPiece;
        }

        public Integer getFrameSize() {
            return frameSize;
        }

        public void setFrameSize(Integer frameSize) {
            this.frameSize = frameSize;
        }
    }
}
