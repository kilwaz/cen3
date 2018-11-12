package requests.spark;

import data.model.objects.json.JSONContainer;
import net.glxn.qrgen.core.image.ImageType;
import net.glxn.qrgen.javase.QRCode;
import org.apache.log4j.Logger;
import requests.annotations.RequestName;
import spark.Request;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

@RequestName("qrcode")
public class QRCodeAPI extends SparkRequest {
    private static Logger log = Logger.getLogger(QRCodeAPI.class);

    public JSONContainer get(Request request) {
        String qrCodeText = request.queryParams("text");

        log.info("The value got is '" + qrCodeText + "'");

        if (qrCodeText != null && !qrCodeText.isEmpty()) {
            try {
                data.model.objects.QRCode qrCode = data.model.objects.QRCode.create(data.model.objects.QRCode.class);
                qrCode.setUrl("https://resources.kilwaz.me/" + qrCode.getUuidString() + ".jpg");

                File qrCodeFile = new File("/var/www/kilwaz/resources/" + qrCode.getUuidString() + ".jpg");
                FileOutputStream fop = new FileOutputStream(qrCodeFile);
                QRCode.from(qrCodeText).to(ImageType.JPG).writeTo(fop);

                log.info("QRCode has been generated and written");

                return new JSONContainer().dbDataItem(qrCode);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }

        return new JSONContainer().error("No text provided");
    }
}
