package Lists;


public class LinkedList {
    class Node {
        private int value;
        private Node next;

        public Node(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }

        public void setValue(int value) {
            this.value = value;
        }

        public Node getNext() {
            return next;
        }

        public void setNext(Node next) {
            this.next = next;
        }

        public String toString() {
            return Integer.toString(value);
        }
    }

    private Node head;
    private int size;

    public void addToFront(int value) {
        Node node = new Node(value);
        node.setNext(head);
        head = node;
        size++;
    }

    public void addTo(int value) {
        if (head == null || head.getValue() >= value ) {
            addToFront(value);
            return;
        }

        Node current = head.getNext();
        Node previous = head;
        while (current != null && current.getValue() < value) {
            previous = current;
            current = current.getNext();
        }

        Node node = new Node(value);
        node.setNext(current);
        previous.setNext(node);
        size++;
    }

    public int getSize() {
        return size;
    }

    public boolean isEmpty() {
        return head == null;
    }

    public void printList() {
        Node current = head;
        System.out.print("HEAD -> ");
        while (current != null) {
            System.out.print(current);
            System.out.print(" <=> ");
            current = current.getNext();
        }
        System.out.println("null");
    }
}

