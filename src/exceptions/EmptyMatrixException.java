package exceptions;

public class EmptyMatrixException extends Exception {
    public EmptyMatrixException() {
        super("Матрица пуста.");
    }
}

