import core.builders.requests.RequestMapper;
import org.apache.log4j.Logger;
import utils.AppManager;

public class HelloWorld {
    private static Logger log = Logger.getLogger(HelloWorld.class);

    public static void main(String[] args) {
        AppManager.init();
        RequestMapper.buildMappings();
    }
}