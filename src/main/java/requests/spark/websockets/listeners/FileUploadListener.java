package requests.spark.websockets.listeners;

import clarity.Clarity;
import log.AppLogger;
import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.Logger;
import org.eclipse.jetty.websocket.api.Session;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketClose;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketConnect;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketMessage;
import org.eclipse.jetty.websocket.api.annotations.WebSocket;
import requests.annotations.RequestName;

import java.io.File;
import java.io.IOException;

@WebSocket
@RequestName("upload")
public class FileUploadListener {
    private static Logger log = AppLogger.logger();

    private String directory = "C:\\Users\\Alex\\Downloads\\UploadedFiles\\";
    private String fileName = "";

    // New connections trigger here
    @OnWebSocketConnect
    public void connected(Session session) {
        //log.info("UPLOAD New connected session " + session.getRemoteAddress());
    }

    // Closed connections trigger here
    @OnWebSocketClose
    public void closed(Session session, int statusCode, String reason) {
        try {
            File uploadedFile = new File(directory + fileName);

            if (uploadedFile.exists() && uploadedFile.getName().endsWith(".xlsx")) {
                Clarity.load()
                        .excel()
                        .file(uploadedFile)
                        .process();
            }
        } catch (Exception ex) {
            log.error(ex);
        }
    }

    // Incoming messages if it is a byte buffer
    @OnWebSocketMessage
    public void handleBinaryMessage(Session session, byte[] buffer, int offset, int length) throws IOException {
        if (!fileName.isEmpty()) {
            FileUtils.writeByteArrayToFile(new File(directory + fileName), buffer, true);
        } else {
            //TODO: Make this an error that is handled
            log.info("Filename not defined");
        }
    }

    // Incoming messages reach the server via this method
    @OnWebSocketMessage
    public void message(Session session, String rawMessage) {
        fileName = rawMessage;
        try {
            session.getRemote().sendString("Message back from server");
        } catch (IOException ex) {
            //TODO: Make actual error message for this
            log.info("Error");
        }
    }
}