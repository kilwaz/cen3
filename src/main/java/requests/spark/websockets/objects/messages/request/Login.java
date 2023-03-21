package requests.spark.websockets.objects.messages.request;

import clarity.Record;
import clarity.load.store.Records;
import log.AppLogger;
import org.apache.log4j.Logger;
import requests.spark.websockets.objects.Message;
import requests.spark.websockets.objects.MessageType;
import requests.spark.websockets.objects.messages.dataobjects.LoginData;
import requests.spark.websockets.objects.messages.mapping.WebSocketDataClass;

import java.util.List;

@MessageType("Login")
@WebSocketDataClass(LoginData.class)
public class Login extends Message {
    private static Logger log = AppLogger.logger();

    public void process() {
        LoginData loginData = (LoginData) this.getWebSocketData();

        List<Record> records = Records.getInstance().findRecords("USER_Username", loginData.getUsername());

        if(loginData.getPassword().equals("demo") && loginData.getUsername().equals("admin@demo.com")){
            loginData.setAcceptedAuth(true);
//            loginData.setErrorMessage("INVALID DETAILS");
        } else {
            loginData.setAcceptedAuth(false);
            loginData.setErrorMessage("INVALID DETAILS");
        }

        for (Record record : records) {
            if (loginData.getPassword() != null && loginData.getPassword().equals(record.get("USER_Password").get().getValue())) {
                loginData.setAcceptedAuth(true);
                loginData.setErrorMessage(record.get("USER_Username").get().getValue().toString());
            }
        }
    }
}
