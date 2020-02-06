package clarity.load.store;

import clarity.definition.Matrices;

import java.util.HashMap;

public class DefinedMatrix {
    private String name;
    private HashMap<String, MatrixEntry> matrix;

    private DefinedMatrix() {
        matrix = new HashMap<>();
        name = "";
    }

    public static DefinedMatrix define() {
        DefinedMatrix definedMatrix = new DefinedMatrix();
        Matrices.getInstance().addDefinedMatrix(definedMatrix);
        return definedMatrix;
    }

    public MatrixEntry findItem(String reference) {
        return matrix.get(reference);
    }

    public DefinedMatrix addItem(MatrixEntry matrixEntry) {
        matrix.put(matrixEntry.getKey(), matrixEntry);
        return this;
    }

    public DefinedMatrix name(String name) {
        String oldName = this.name;
        this.name = name;
        Matrices.getInstance().updateDefinedMatrixName(this, oldName);
        return this;
    }

    public String getName() {
        return name;
    }
}
