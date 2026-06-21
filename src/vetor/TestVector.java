package vetor;

public class TestVector {

    public static void main(String[] args) {
        System.out.println("===== TESTANDO ARRAY CIRCULAR =====");
        testVector(new ArrayVector<>(3, 2));

        System.out.println("===================================");

        System.out.println("===== TESTANDO LISTA LIGADA =====");
        testVector(new LinkedVector<>());
    }

    private static void testVector(Vector<String> v) {
        System.out.println("vazio? " + v.isEmpty());

        System.out.println("--- insertAtRank ---");
        v.insertAtRank(0, "A");
        v.insertAtRank(1, "C");
        v.insertAtRank(1, "B");
        v.insertAtRank(3, "D");
        v.insertAtRank(0, "Z"); // Esperado: [Z, A, B, C, D]

        System.out.println("Size: " + v.size());
        System.out.println(v);

        System.out.println("--- replaceAtRank ---");
        Object replaced = v.replaceAtRank(3, "X");
        System.out.println("Rank 3 - 'C' -> '" + replaced + "'");
        System.out.print("Vetor após replace (Esperado: [Z, A, B, X, D]): ");
        System.out.println(v);

        System.out.println("--- removeAtRank ---");
        Object removed0 = v.removeAtRank(0);
        System.out.println("Início - 'Z' -> '" + removed0 + "'");

        Object removedEnd = v.removeAtRank(v.size() - 1);
        System.out.println("Final - 'D' -> '" + removedEnd + "'");

        Object removedMid = v.removeAtRank(1);
        System.out.println("Meio - 'B' -> '" + removedMid + "'");

        System.out.println(v);
        System.out.println("Size: " + v.size());

        System.out.println("--- Exceções ---");
        testExceptions(v);
    }

    private static void testExceptions(Vector<String> v) {
        System.out.println("Acesso inválido 1");
        try {
            v.elemAtRank(50);
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Funcionou");
        }

        System.out.println("Acesso inválido 2");
        try {
            v.removeAtRank(-1);
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Funcionou");
        }
    }
}