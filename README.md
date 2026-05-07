# IFRN - EDL

## Sumário

### 1. [Pilha](#pilha)

### 2. [Fila](#fila)

### 3. [Vetor](#vetor)

### 4. [Lista](#lista)

## Pilha

### Introdução

Inserções e remoções seguem o esquema LIFO (Last In, First Out)

- Principais operações:
    - `push(Object o)`: insere um elemento
    - `Object pop()`: remove e returna o último elemento inserido

- Operações auxiliares:
    - `object top()`: retorna o último elemento inserido sem removê-lo
    - `int size()`: retorna o número de elementos armazenados
    - `boolean isEmpty()`: indica se há ou não elementos na Pilha

### Implementações

#### [Array](src/pilha/PilhaArray.java)

### Atividades

#### [Pilha Rubro Negra](src/pilha/rubronegra/README.md)

## Fila

### Introdução

Inserções e remoções seguem o esquema FIFO (First In, First Out)

- Operações principais:
    - `enqueue(Object o)`: insere um elemento no fim da fila;
    - `Object dequeue()`: remove e retorna o elemento do início da fila.

- Operações auxiliares:
    - `Object first()`: retorna o elemento do início sem removê-lo;
    - `int size()`: retorna o número de elementos armazenados;
    - `boolean isEmpty()`: indica se há elementos na fila.

### Implementações

#### [Array circular](src/fila/FilaArray.java)

#### [Lista simplesmente encadeada](src/fila/FilaEncadeada.java)

### Atividades

#### [Reversão em O(1)](src/fila/reverse/README.md)

## Vetor

### Introduçao

O TAD Vetor extende a noção de arranjo(array) armazenando sequências de objetos arbitrários;

Um elemento pode ser acessado, inserido ou removido através da especificação de sua colocação (rank).

- Principais operações:
    - `Object elemAtRank(int r)`: retorna o elemento na colocação r, sem removê-lo
    - `Object replaceAtRank(int r, Object o)`: substitui o elemento na colocação r por o e retorna o antigo elemento
    - `insertAtRank(int r, Object o)`: insere um novo elemento o na colocação r
    - `Object removeAtRank(int r)`: remove e retorna o elemento na colocação r
- Operações adicionais `size()` e `isEmpty()`

### Implementações

#### [Array circular](src/vetor/VectorArray.java)

#### [Lista duplamente encadeada](src/vetor/VectorLinked.java)

## Lista

### Introduçao

O TAD Lista modela um sequência de posições armazenando objetos quaisquer;

Ele estabelece uma relação antes/depois entre posições.

Nota: `T` -> Tipo genérico

- Métodos genéricos
    - `int size()`;
    - `boolean isEmpty()`.
- Métodos de fila:
    - `boolean isFirst(T e)`;
    - `boolean isLast(T e)`;
- Métodos para acessar:
    - `T first()`;
    - `T last()`;
    - `T before(T e)`;
    - `T after(T e)`.
- Métodos para atualizar:
    - `void replaceElement(T n, T e`);
    - `void swapElements(T n, T q)`;
    - `void insertBefore(int n, T e)`;
    - `void insertAfter(int n, T e)`;
    - `void insertFirst(T e)`;
    - `void insertLast(T e)`;
    - `T remove(int n)`.

### Implementações

#### [Array circular](src/lista/ListArray.java)

#### [Lista duplamente encadeada](src/lista/ListLinked.java)