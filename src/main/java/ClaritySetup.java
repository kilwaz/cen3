import clarity.Definition;
import log.AppLogger;
import org.apache.log4j.Logger;

public class ClaritySetup {
    private static Logger log = AppLogger.logger();

    public static void main(String[] args) {
        Definition definition = Definition.define().name("A").formula("1+12");
        Definition definition2 = Definition.define().name("B").formula("10+2");
        Definition definition3 = Definition.define().name("C").formula("[B]*[A]");

        log.info(definition.getFormula().getStrExpression() + " = " + definition.calculate().getStringRepresentation());
        log.info(definition2.getFormula().getStrExpression() + " = " + definition2.calculate().getStringRepresentation());
        log.info(definition3.getFormula().getStrExpression() + " = " + definition3.calculate().getStringRepresentation());
    }
}
