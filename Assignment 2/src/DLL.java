public class DLL <E>
{
    protected static class Node<E>
    {
        private E element;
        private int serial;
        private Node<E> prev;
        private Node<E> next;
        public Node (E e, Node<E> p, Node<E> n)
        {
            element = e;
            prev = p;
            next = n;
        }
        public E getElement()
        {
            return element;
        }

        public Node<E> getPrev() {
            return prev;
        }

        public Node<E> getNext() {
            return next;
        }

        public void setPrev(Node<E> prev) {
            this.prev = prev;
        }

        public void setNext(Node<E> next) {
            this.next = next;
        }
        public void setSerial(int serial)
        {
            this.serial = serial;
        }

        public int getSerial()
        {
            return serial;
        }
    }

    private Node<E> header;
    private Node<E> trailer;
    private int size = 0;

    public DLL()
    {
        header = new Node<>(null, null, null);
        trailer = new Node<>(null, header, null);
        header.setNext(trailer);
        //trailer.setNext(header);
    }
    public int getSize()
    {
        return size;
    }


    public Node<E> getHeader()
    {
        return header;
    }
    public Node<E> getTrailer()
    {
        return trailer;
    }

    public boolean isEmpty()
    {
        return size == 0;
    }
    public Node<E> first()
    {
        if (isEmpty()) return null;
        return header.getNext();
    }
    public Node<E> last()
    {
        if (isEmpty()) return null;
        return trailer.getPrev();
    }
    public void addBetween(E e, int i, Node<E> predecessor, Node<E> successor)
    {
        Node<E> newest = new Node<>(e, predecessor, successor);
        predecessor.setNext(newest);
        successor.setPrev(newest);
        newest.setSerial(i);
        size++;
        //System.out.println("Element added successfully!");
    }
    public E remove(Node<E> node)
    {
        Node<E> predecessor = node.getPrev();
        Node<E> successor = node.getNext();
        predecessor.setNext(successor);
        successor.setPrev(predecessor);
        size--;
        return node.getElement();
    }


}
