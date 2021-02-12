
public class Node {

    LinkedList<Edge> list;

    public Node() {
        list = new LinkedList<>();
    }
}


class LinkedList<E> {
    private final Node<E> header;   //sentinel
    private final Node<E> trailer;  //sentinel
    private int size = 0;


    private static class Node<E> {
        private E element;
        private Node<E> prev;
        private Node<E> next;
        public Node(E e, Node<E> p, Node<E> n){
            element = e;
            prev = p;
            next = n;
        }
        public E        getElement()                {return element;}
        public void     setElement(E element)       {this.element = element; }
        public Node<E>  getPrev()                   {return prev; }
        public void     setPrev(Node<E> prev)       {this.prev = prev; }
        public Node<E>  getNext()                   {return next;}
        public void     setNext(Node<E> next)       {this.next = next;}
    }

    public LinkedList(){
        header = new Node<>(null,null,null);
        trailer = new Node<>(null,header,null);
        header.setNext(trailer);
    }

    public int length() {return size;}

    public void add(E e){
        if(size==0){
            addFirst(e);
            return;
        }
        Node<E> last = trailer.prev;
        Node<E> newNode = new Node<>(e,last,trailer);
        last.next = newNode;
        trailer.prev = newNode;
        size++;
    }

    //private
    private void addFirst(E e){
        Node<E> newNode = new Node<>(e,header,trailer);
        header.next = newNode;
        trailer.prev = newNode;
        size++;
    }


    public E get(int index){
        Node<E> cursor = header.next; //cursor at the first node
        for(int i=0; i<index;i++) {
            if(cursor != null)
                cursor = cursor.next;
        }
        if(cursor == null)
            return null;

        return cursor.element;
    }
}

