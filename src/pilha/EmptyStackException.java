package pilha;

public class EmptyStackException extends RuntimeException {
    public EmptyStackException(String err) {
        super(err);
    }
}