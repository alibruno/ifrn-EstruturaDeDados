package lista;

public class TestList {

    public static void main(String[] args) {
        System.out.println("===== TESTANDO LISTA COM ARRAY CIRCULAR =====");
        testList(new PositionListArray<>(3));

        System.out.println("\n\n=============================================");

        System.out.println("===== TESTANDO LISTA DUPLAMENTE LIGADA =====");
        testList(new PositionListLinked<>());
    }

    private static void testList(PositionList<String> list) {
        System.out.println("vazio? " + list.isEmpty());

        System.out.println("--- insertFirst / insertLast ---");
        list.insertFirst("B");
        list.insertLast("D");
        list.insertFirst("A");
        list.insertLast("E"); // Esperado: [A, B, D, E]

        System.out.println("Size: " + list.size());
        System.out.println(list);

        System.out.println("--- insertBefore / insertAfter ---");
        list.insertAfter(1, "C"); // Insere depois do B
        list.insertBefore(0, "Z"); // Insere antes do A (Esperado: [Z, A, B, C, D, E])

        System.out.println("Size: " + list.size());
        System.out.println(list);

        System.out.println("--- before / after ---");
        System.out.println("Antes de 'C': " + list.before("C"));
        System.out.println("Depois de 'C': " + list.after("C"));
        System.out.println("Primeiro: " + list.first() + " | Último: " + list.last());
        System.out.println("isFirst('Z')? " + list.isFirst("Z") + " | isLast('E')? " + list.isLast("E"));

        System.out.println("--- replaceElement ---");
        list.replaceElement("X", "C"); // Novo, Antigo
        System.out.print("Após replace (C por X): ");
        System.out.println(list);

        System.out.println("--- swapElements ---");
        list.swapElements("A", "D");
        System.out.print("Após swap (A e D): ");
        System.out.println(list);

        System.out.println("--- remove ---");
        String removedMid = list.remove(3); // Índice 3 agora é o 'A'
        System.out.println("Meio - 'A' -> '" + removedMid + "'");

        String removed0 = list.remove(0);
        System.out.println("Início - 'Z' -> '" + removed0 + "'");

        String removedEnd = list.remove(list.size() - 1);
        System.out.println("Final - 'E' -> '" + removedEnd + "'");

        System.out.println(list);
        System.out.println("Size: " + list.size());

        System.out.println("--- Exceções ---");
        testExceptions(list);
    }

    private static void testExceptions(PositionList<String> list) {
        System.out.println("Acesso inválido 1 (remover índice inexistente)");
        try {
            list.remove(50);
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Funcionou");
        }

        System.out.println("Acesso inválido 2 (before do primeiro)");
        try {
            list.before(list.first());
        } catch (IllegalArgumentException e) {
            System.out.println("Funcionou");
        }

        System.out.println("Acesso inválido 3 (elemento inexistente)");
        try {
            list.after("Fantasma");
        } catch (IllegalArgumentException e) {
            System.out.println("Funcionou");
        }
    }

}