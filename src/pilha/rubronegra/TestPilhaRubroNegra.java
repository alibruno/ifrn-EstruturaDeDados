package pilha.rubronegra;

import pilha.EmptyStackException;

public class TestPilhaRubroNegra {
    public static void main(String[] args) {
        
        System.out.println();
        System.out.println(" ============================================= Teste push() =================================================");
        System.out.println();
        testPush();
        System.out.println();
        System.out.println("==============================================================================================================");
        System.out.println();

        System.out.println("======================================== Teste increaseCapacity() ============================================");
        System.out.println();
        testIncreaseCapacity();
        System.out.println();
        System.out.println("==============================================================================================================");
        System.out.println();

        System.out.println("=============================================== Teste top() ===================================================");
        testTop();
        System.out.println();
        System.out.println("===============================================================================================================");
        System.out.println();

        System.out.println("=============================================== Teste pop() ===================================================");
        testPop();
        System.out.println("===============================================================================================================");
        System.out.println();

    }

    private static void testPush() {
        PilhaRubroNegra pilha = new PilhaRubroNegraArray(10);
        for (int i = 0; i < 3; i++) {
            pilha.pushRed(i);
        }
        for (int i = 0; i < 3; i++) {
            pilha.pushBlack(i);
        }
        System.out.println(pilha);
    }

    private static void testIncreaseCapacity() {
        System.out.println("Capacidade = 3, Inserções = 4");
        PilhaRubroNegra pilha = new PilhaRubroNegraArray(3);
        pilha.pushRed('r');
        pilha.pushRed('r');
        pilha.pushBlack('b');
        pilha.pushBlack('b');
        System.out.println(pilha);
        System.out.println("Capacidade = 10, Inserções = 15");
        pilha = new PilhaRubroNegraArray(10);
        for (int i = 0; i < 5; i++) {
            pilha.pushRed('r');
        }
        for (int i = 5; i < 10; i++) {
            pilha.pushBlack('b');
        }
        System.out.println(pilha);

        for (int i = 0; i < 2; i++) {
            pilha.pushRed('R');
        }
        for (int i = 0; i < 3; i++) {
            pilha.pushBlack('B');
        }
        System.out.println(pilha);
    }


    private static void testTop() {
        PilhaRubroNegra pilha = new PilhaRubroNegraArray(10);
        System.out.println(pilha);

        System.out.println("topRed() sem inserção");
        try {
            pilha.topRed();
        } catch (EmptyStackException e) {
            System.out.println(e.getMessage());
        }

        System.out.println("topBlack() sem inserção");
        try {
            pilha.topBlack();
        } catch (EmptyStackException e) {
            System.out.println(e.getMessage());
        }

        System.out.println("Inserção somente na pilha red");
        for (int i = 0; i < 5; i++) {
            pilha.pushRed(i);
        }

        System.out.println();

        System.out.println(pilha);

        System.out.println("tentativa de topBlack()");
        try {
            pilha.topBlack();
        } catch (EmptyStackException e) {
            System.out.println(e.getMessage());
        }

        System.out.println("Inserção na pilha black");
        for (int i = 9; i > 4; i--) {
            pilha.pushBlack(i);
        }

        System.out.println();

        System.out.println(pilha);
        System.out.println("topRed = " + pilha.topRed() + " topBlack = " + pilha.topBlack());

        System.out.println("Mais inserções - increaseCapacity()");
        for (int i = 20; i < 22; i++) {
            pilha.pushRed(i);
        }
        for (int i = 30; i < 32; i++) {
            pilha.pushBlack(i);
        }
        System.out.println(pilha);
        System.out.println("topRed = " + pilha.topRed() + " topBlack = " + pilha.topBlack());
    }

    private static void testPop() {
        System.out.println("Capacidade = 10, Inserções = 6");
        PilhaRubroNegra pilha = new PilhaRubroNegraArray(10);
        for (int i = 1; i < 4; i++) {
            pilha.pushRed(i);
        }
        for (int i = 5; i < 8; i++) {
            pilha.pushBlack(i);
        }
        System.out.println(pilha);

        System.out.println();
        System.out.println("Remoção pilha vermelha");
        System.out.println("Elemento removido = " + pilha.popRed());
        System.out.println("Topo atual = " + pilha.topRed());
        System.out.println("Size Red = " + pilha.sizeRed());
        System.out.println("Size All = " + pilha.sizeAll());
        System.out.println(pilha);

        System.out.println();
        System.out.println("Remoção pilha preta");
        System.out.println("Elemento removido = " + pilha.popBlack());
        System.out.println("Topo atual = " + pilha.topBlack());
        System.out.println("Size Black = " + pilha.sizeBlack());
        System.out.println("Size All = " + pilha.sizeAll());
        System.out.println(pilha);

        System.out.println();

        System.out.println("=============================================== Teste reduceCapacity() ===================================================");
        System.out.println("Remoção de mais um elemento da pilha preta");
        System.out.println("Elemento removido = " + pilha.popBlack());
        System.out.println("Topo atual = " + pilha.topBlack());
        System.out.println("Size Black = " + pilha.sizeBlack());
        System.out.println("Size All = " + pilha.sizeAll());
        System.out.println(pilha);

        System.out.println();

        System.out.println("REMOÇÃO DE TODOS OS ELEMENTOS VERMELHOS");
        System.out.println("Elemento removido = " + pilha.popRed());
        System.out.println("Topo atual = " + pilha.topRed());
        System.out.println("Size Red = " + pilha.sizeRed());
        System.out.println("Size All = " + pilha.sizeAll());
        System.out.println(pilha);
        System.out.println("Elemento removido = " + pilha.popRed());
        System.out.println("Size Red = " + pilha.sizeRed());
        System.out.println("Size All = " + pilha.sizeAll());
        System.out.println(pilha);

        System.out.println();

        System.out.println("ADICÃO DE MAIS UM ELEMENTO PARA A PILHA PRETA");
        pilha.pushBlack(9);
        System.out.println(pilha);
        System.out.println("Topo atual = " + pilha.topBlack());
        System.out.println("Size Black = " + pilha.sizeBlack());
        System.out.println("Size All = " + pilha.sizeAll());
        System.out.println(pilha);

        System.out.println();

        System.out.println("REMOÇÃO DE TODOS OS ELEMENTOS PRETOS");
        System.out.println("Elemento removido = " + pilha.popBlack());
        System.out.println("Size Black = " + pilha.sizeBlack());
        System.out.println("Size All = " + pilha.sizeAll());
        System.out.println(pilha);
        System.out.println("Elemento removido = " + pilha.popBlack());
        System.out.println("Size Black = " + pilha.sizeBlack());
        System.out.println("Size All = " + pilha.sizeAll());
        System.out.println(pilha);
    }

}
