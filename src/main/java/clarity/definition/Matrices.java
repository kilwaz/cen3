package clarity.definition;

import clarity.load.store.DefinedMatrix;
import log.AppLogger;
import org.apache.log4j.Logger;

import java.util.HashMap;

public class Matrices {
    private static Logger log = AppLogger.logger();
    private static Matrices instance;

    private HashMap<String, DefinedMatrix> matrixHashMap = new HashMap<>();

    public DefinedMatrix findDefinedMatrix(String reference) {
        return matrixHashMap.get(reference);
    }

    public void updateDefinedMatrixName(DefinedMatrix definedMatrix, String oldName) {
        matrixHashMap.put(definedMatrix.getName(), definedMatrix);
        matrixHashMap.remove(oldName);
    }

    public void addDefinedMatrix(DefinedMatrix definedMatrix) {
        matrixHashMap.put(definedMatrix.getName(), definedMatrix);
    }

    public static Matrices getInstance() {
        if (instance == null) {
            instance = new Matrices();
        }
        return instance;
    }
}
