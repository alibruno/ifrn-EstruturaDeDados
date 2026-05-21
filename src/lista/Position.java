package lista;

public interface Position<E> {
    /**
     * @return retorna o elemento armazenado nessa posição
     */
    E getElement() throws IllegalStateException;
}
