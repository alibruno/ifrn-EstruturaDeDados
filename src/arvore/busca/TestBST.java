package arvore.busca;

import arvore.Position;

public class TestBST {
    public static void main(String[] args) {
        BinarySearchTree<Integer> bst = new BinarySearchTree<>();

        System.out.println("=== Construindo a árvore inicial ===");
        int[] valoresIniciais = {10, 5, 15, 2, 8, 22};
        for (int val : valoresIniciais) {
            bst.insert(val);
        }
        imprimirInOrder(bst);
        // Esperado: 2 5 8 10 15 22

        System.out.println("\n=== Inserindo o 25 ===");
        bst.insert(25);
        imprimirInOrder(bst);
        // Esperado: 2 5 8 10 15 22 25

        System.out.println("\n=== Removendo o 5 (Nó com 2 filhos) ===");
        System.out.println("O sucessor do 5 na subárvore direita é o 8. O 8 tomará o lugar do 5.");
        bst.remove(5);
        imprimirInOrder(bst);
        // Esperado: 2 8 10 15 22 25

    }

    // Método auxiliar para print da árvore
    private static void imprimirInOrder(BinarySearchTree<?> bst) {
        System.out.print("In-Order (Ordem Crescente): ");
        for (Position<?> p : bst.inOrder()) {
            System.out.print(p.getElement() + " ");
        }
        System.out.println();
    }
}
