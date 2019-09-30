import build.TypeScriptBuilder;
import log.AppLogger;
import org.apache.log4j.Logger;

public class GenerateTypeScript {
    private static Logger log = AppLogger.logger();

    public static void main(String[] args) {
        TypeScriptBuilder.build();
    }
}
