import clarity.Definition;
import log.AppLogger;
import org.apache.log4j.Logger;

public class ClaritySetup {
    private static Logger log = AppLogger.logger();

    public static void main(String[] args) {
        //Definition definition = Definition.define().name("A").formula("1+12+3");
        //Definition definition2 = Definition.define().name("B").formula("10-2");
        Definition definition3 = Definition.define().name("C").formula("[3]");

        //log.info(definition.getFormula().getStrExpression() + " = " + definition.calculate());
        //log.info(definition2.getFormula().getStrExpression() + " = " + definition2.calculate());
        log.info(definition3.getFormula().getStrExpression() + " = " + definition3.calculate());
    }
}
