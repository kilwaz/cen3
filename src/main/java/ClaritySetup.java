import clarity.Definition;
import log.AppLogger;
import org.apache.log4j.Logger;

public class ClaritySetup {
    private static Logger log = AppLogger.logger();

    public static void main(String[] args) {
        Definition definition = Definition.define().name("Hello").expression("1 + 2 + 3");
        Definition definition2 = Definition.define().name("Hello").expression("1=2");

        log.info(definition.calculate());
        log.info(definition2.calculate());
    }
}
