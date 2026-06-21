package fila;

public class TestQueue {
    public static void main(String[] args) {
        System.out.println("ARRAY");
        Queue<Integer> queueArray = new ArrayQueue<>(10, 0);
        System.out.println("-------------------- Teste enqueue() --------------------");
        testEnqueue(queueArray);
        System.out.println("-------------------- Teste dequeue() --------------------");
        testDequeue(queueArray);

        System.out.println("==========================================================");

        System.out.println("ENCADEADA");
        Queue<Integer> queueEncadeada = new LinkedQueue<>();
        System.out.println("-------------------- Teste enqueue() --------------------");
        testEnqueue(queueEncadeada);
        System.out.println("-------------------- Teste dequeue() --------------------");
        testDequeue(queueEncadeada);
    }

    private static void testEnqueue(Queue<Integer> queue) {
        System.out.println("Is Empty? " + queue.isEmpty());
        System.out.println(queue);
        queue.enqueue(10);
        queue.enqueue(20);
        queue.enqueue(30);
        queue.enqueue(40);
        System.out.println(queue);
        System.out.println("Is Empty? " + queue.isEmpty());
        System.out.println("First? " + queue.first());
    }

    private static void testDequeue(Queue<Integer> queue) {
        queue.enqueue(10);
        queue.enqueue(20);
        queue.enqueue(30);
        System.out.println(queue);
        for (int i = 0; i < 3; i++) {
            System.out.println("Remoção " + queue.dequeue());
            System.out.println(queue);
        }
        try {
            queue.dequeue();
        } catch (EmptyQueueException e) {
            System.out.println(e.getMessage());
        }
    }
}
