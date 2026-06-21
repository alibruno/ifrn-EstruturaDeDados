package pilha.rubronegra;

import pilha.EmptyStackException;

import java.util.Arrays;

public class PilhaRubroNegraArray implements PilhaRubroNegra {

    //Atributos da classe
    private Object[] data;
    private int redIndex;
    private int blackIndex;
    private int capacity;

    //Construtor
    public PilhaRubroNegraArray(int capacity) {

        // Se a pilha não pode ter uma capacidade menor que 0
        if (capacity <= 0) {
            throw new IllegalArgumentException("capacity must be greater than 0");
        }
        this.data = new Object[capacity];
        this.redIndex = -1;
        this.blackIndex = capacity;
        this.capacity = capacity;
    }

    @Override
    // Método para adicionar um objeto na pilha vermelha
    public void pushRed(Object o) {
        if (sizeRed() + sizeBlack() == capacity) {
            increaseCapacity();
        }
        data[++redIndex] = o;
    }

    @Override
    // Método para adiconar um objeto na pilha preta
    public void pushBlack(Object o) {
        if (sizeRed() + sizeBlack() == capacity) {
            increaseCapacity();
        }
        data[--blackIndex] = o;
    }

    @Override
    // Método para remoção de um objeto da pilha vermelha
    // A remoção ocorre no topo da pilha
    // Esse método pode gerar uma excessão, visto que não é possível remover elementos de uma pilha vazia
    public Object popRed() throws EmptyStackException {
        if (isEmptyRed()) {
            throw new EmptyStackException("Red Stack is empty");
        }

        Object poppedElement = data[redIndex--];
        if ((sizeRed() + sizeBlack()) * 3 <= capacity) {
            reduceCapacity();
        }

        return poppedElement;
    }

    @Override
    // Método para a remoção de um objeto da pilha preta
    // A remoção ocorre no topo da pilha (da esqueda para a direita)
    // Esse método pode gerar uma excessão, visto que não é possível remover elementos de uma pilha vazia
    public Object popBlack() throws EmptyStackException {

        if (isEmptyBlack()) {
            throw new EmptyStackException("Black Stack is empty");
        }

        Object poppedElement = data[blackIndex++];
        if ((sizeRed() + sizeBlack()) * 3 <= capacity) {
            reduceCapacity();
        }

        return poppedElement;
    }

    @Override
    // Mostra o elemento no topo da pilha VERMELHA
    // Esse método pode gerar uma excessão caso não haja elementos na pilha
    public Object topRed() throws EmptyStackException {
        if (isEmptyRed()) {
            throw new EmptyStackException("Red Stack is empty");
        }
        return data[redIndex];
    }

    @Override
    // Mostra o elemento no topo da pilha PRETA
    // Esse método pode gerar uma excessão caso não haja elementos na pilha
    public Object topBlack() throws EmptyStackException {
        if (isEmptyBlack()) {
            throw new EmptyStackException("Black Stack is empty");
        }
        return data[blackIndex];
    }

    @Override
    // Retorna o tamanho completo da pilha
    public int sizeAll() {
        return sizeRed() + sizeBlack();
    }

    @Override
    // Retorna o tamanho da pilha vermelha
    public int sizeRed() {
        return redIndex + 1;
    }

    @Override
    // Retorna o tamanho da pilha preta
    public int sizeBlack() {
        return capacity - blackIndex;
    }

    @Override
    // Verifica se o array completo está vazio
    public boolean isEmptyAll() {
        return (sizeRed() == 0 && sizeBlack() == 0);
    }

    @Override
    // Verifica se a pilha VERMELHA está vazia
    public boolean isEmptyRed() {
        return sizeRed() == 0;
    }

    @Override
    // Verifica se a pilha PRETA está vazia
    public boolean isEmptyBlack() {
        return sizeBlack() == 0;
    }

    // Método que aumente a capacidade do array
    // Usa a estratégia de crescimento de DUPLICAÇÃO
    private void increaseCapacity() {
        int newCapacity = capacity * 2;
        Object[] new_data = new Object[newCapacity];
        this.data = copyRedAndBlackStacksElementsToNewArray(this.data, new_data);
        capacity = newCapacity;
    }

    // Método de diminui a capacidade caso o tamanho de ambas as pilhas somadas sejam menou ou igual a 1/3
    private void reduceCapacity() {
        int newCapacity = capacity / 2;
        Object[] new_data = new Object[newCapacity];
        this.data = copyRedAndBlackStacksElementsToNewArray(this.data, new_data);
        capacity = newCapacity;
    }

    private Object[] copyRedAndBlackStacksElementsToNewArray(Object[] data, Object[] new_data) {
        // Copy elements from the red stack
        for (int i = 0; i < sizeRed(); i++) {
            new_data[i] = data[i];
        }
        // Copy elements from the black stack

        int auxIndex = new_data.length - 1;
        int oldIndex = capacity - 1;
        for (int i = 0; i < sizeBlack(); i++) {
            new_data[auxIndex--] = data[oldIndex--];
        }

        blackIndex = auxIndex + 1;
        return new_data;
    }

    @Override
    public String toString() {
        return "PilhaRubroNegraArray{" +
                "data=" + Arrays.toString(data) +
                '}';
    }
}
