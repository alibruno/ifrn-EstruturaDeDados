package pilha.rubronegra;

import pilha.EmptyStackException;

public interface PilhaRubroNegra {
    int sizeAll();

    int sizeRed();

    int sizeBlack();

    boolean isEmptyAll();

    boolean isEmptyRed();

    boolean isEmptyBlack();

    Object topRed() throws EmptyStackException;

    Object topBlack() throws EmptyStackException;

    void pushRed(Object o);

    void pushBlack(Object o);

    Object popRed() throws EmptyStackException;

    Object popBlack() throws EmptyStackException;
}
