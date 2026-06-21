package sequencia;

public class TestSequence {
    public static void main(String[] args) {
        Sequence<String> seq = new LinkedSequence<>();

        System.out.println("=== TESTE TAD SEQUÊNCIA ===");
        System.out.println("A sequência está vazia? " + seq.isEmpty());
        System.out.println("Tamanho inicial: " + seq.size());
        System.out.println();

        System.out.println("--- 1. Inserções via métodos de Lista ---");
        seq.insertFirst("B");
        seq.insertFirst("A");
        seq.insertLast("D");

        Node<String> noB = seq.atRank(1);
        seq.insertAfter(noB, "C");

        imprimir(seq);

        System.out.println("\n--- 2. Inserções via métodos de Vetor (Rank) ---");
        seq.insertAtRank(0, "Z");
        seq.insertAtRank(3, "X");
        seq.insertAtRank(6, "Y");

        imprimir(seq);

        System.out.println("\n--- 3. Métodos Ponte ---");
        Node<String> noX = seq.atRank(3);
        System.out.println("Elemento no rank 3: " + noX.getElement());
        System.out.println("Qual é o rank do elemento 'X'? " + seq.rankOf(noX));

        System.out.println("\n--- 4. Remoções ---");
        String removido1 = seq.removeAtRank(0);
        System.out.println("Removido do rank 0: " + removido1);

        String removido2 = seq.remove(noX);
        System.out.println("Removido por Nó: " + removido2);

        imprimir(seq);

        System.out.println("\n--- 5. Substituição ---");
        seq.replaceAtRank(4, "E");
        imprimir(seq);
    }

    private static void imprimir(Sequence<String> seq) {
        System.out.print("Estado atual: [");
        for (int i = 0; i < seq.size(); i++) {
            System.out.print(seq.elemAtRank(i));
            if (i < seq.size() - 1) {
                System.out.print(", ");
            }
        }
        System.out.println("] (Tamanho: " + seq.size() + ")");
    }
}
