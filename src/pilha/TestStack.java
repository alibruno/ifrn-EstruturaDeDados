package pilha;

public class TestStack {

    public static void main(String[] args) {
        Stack<Integer> stackArray = new ArrayStack<>(1, 0);

        System.out.println("Inserindo");
        for (int i = 0; i < 10; i++) {
            System.out.print(i + " ");
            stackArray.push(i);
        }
        System.out.println();
        System.out.println("Elementos atuais: " + stackArray);

        System.out.println();
        System.out.println("Retirando");
        for (int i = 0; i < 10; i++) {
            System.out.println("Indice: " + i + " - Elemento removido: " + stackArray.pop());
        }
    }
}
