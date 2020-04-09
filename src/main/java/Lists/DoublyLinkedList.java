package Lists;

public class DoublyLinkedList {
    private class Node {

        private int value;
        private Node next;
        private Node previous;

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

        public Node getPrevious() {
            return previous;
        }

        public void setPrevious(Node previous) {
            this.previous = previous;
        }

        public String toString() {
            return Integer.toString(value);
        }
    }

    private Node head;
    private Node tail;
    private int size;

    public void addToFront(int value) {
        Node node = new Node(value);

        if (head == null) {
            tail = node;
        } else {
            head.setPrevious(node);
            node.setNext(head);
        }

        head = node;
        size++;
    }

    public void addToEnd(int value) {
        Node node = new Node(value);
        if (tail == null) {
            head = node;
        } else {
            tail.setNext(node);
            node.setPrevious(tail);
        }

        tail = node;
        size++;
    }

    public boolean addBefore(int newValue, int existingValue) {
        // return true if you were able to successfully add the value
        // into the list before the existing value. Return false
        // if the existing value doesn't exist in the list

        // add your code here
        if (head == null) {
            return false;
        }

        Node current = head;
        while (current != null && current.getValue() != existingValue) {
            current = current.getNext();
        }

        if (current == null) { return false; }

        Node newNode = new Node(newValue);
        newNode.setPrevious(current.getPrevious());
        newNode.setNext(current);
        current.setPrevious(newNode);
        if (current == head) {
            head = newNode;
        }

        size++;
        return true;
    }

    public Node removeFromFront() {
        if (isEmpty()) {
            return null;
        }

        Node removedNode = head;

        if (head.getNext() == null) {
            tail = null;
        } else {
            head.getNext().setPrevious(null);
        }

        head = head.getNext();
        size--;
        removedNode.setNext(null);
        return removedNode;
    }

    public Node removeFromEnd() {
        if (isEmpty()) {
            return null;
        }

        Node removedNode = tail;

        if (tail.getPrevious() == null) {
            head = null;
        } else {
            tail.getPrevious().setNext(null);
        }

        tail = tail.getPrevious();
        size--;
        removedNode.setPrevious(null);
        return removedNode;
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
