
/* yasin inal */
/* shiny-apricot */

import java.util.ArrayList;
import java.util.Scanner;

public class DoublyLinkedListWithSentinels<E> {
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

    public DoublyLinkedListWithSentinels(){
        header = new Node<>(null,null,null);
        trailer = new Node<>(null,header,null);
        header.setNext(trailer);
    }

    public int length() {return size;}

    public E nodeAt(int index){
        if(index > size-1)
            System.out.println("Index is bigger than list size...");

        Node<E> cursor = header.next; //cursor at the first node
        for(int i=0; i<index;i++) {
            cursor = cursor.next;
        }

        return cursor.element;
    }

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
        System.out.println("INFO: Value '"+e+"' is added +++");
    }

    public void insert(E e, int row){
        if(row > size){
            System.out.println("ERROR: The number '"+row+"' is bigger than the array size...");
            return;
        }
        Node<E> cursor = header.next; //cursor at the first node
        for(int i=0; i<row;i++){
            cursor = cursor.next;
        }
        Node<E> prevCursor = cursor.prev;

        Node<E> newNode = new Node<>(e,prevCursor,cursor);
        prevCursor.next = newNode;
        cursor.prev = newNode;
        size++;
        System.out.println("INFO: Value '"+e+"' is inserted +++");
    }

    //private
    private void addFirst(E e){
        Node<E> newNode = new Node<>(e,header,trailer);
        header.next = newNode;
        trailer.prev = newNode;
        size++;
        System.out.println("INFO: Value '"+e+"' is added +++");
    }

    public boolean removeIndex(int index){
        if(index < 0 || index > size){
            System.out.println("ERROR: Inappropriate Index");
            return false;
        }

        Node<E> cursor = header.next; //cursor at the first node
        for(int i=0; i<index;i++){
            cursor = cursor.next;
        }
        System.out.println("INFO: Value "+cursor.element+" deleted at index = "+index+" ---");
        Node<E> prev = cursor.prev;
        Node<E> next = cursor.next;

        prev.next = cursor.next;
        next.prev = prev;

        size--;
        return true;
    }

    public void removeElement(E e){
        ArrayList<Node<E>> indexList = lookForElement(e);
        if(indexList.size() == 0){
            System.out.println("ERROR: No such value '"+e+"' found in the list...");
            return;
        }

        for (Node<E> node : indexList) {
            Node<E> prev = node.prev;
            Node<E> next = node.next;

            prev.next = node.next;
            next.prev = prev;

            System.out.println("INFO: Value '" + e + "' deleted ---");
            size--;
        }
    }

    //private
    private ArrayList<Node<E>> lookForElement(E e){
        ArrayList<Node<E>> indexList = new ArrayList<>();

        Node<E> cursor = header.next; //cursor at the first node
        for(int i=0; i<size;i++){
            if(cursor.element.equals(e)) {
                indexList.add(cursor);
            }
            cursor = cursor.next;

        }
        return indexList;
    }

    public void print(){
        if(size==0) {
            System.out.println("INFO: List is empty...");
            return;
        }
        System.out.println("List is being printed..");
        printAll();
    }


    //private
    private void printAll(){
        boolean isInteger = false;
        Node<E> cursor = header;
        if(cursor.next.element instanceof Integer){
            isInteger = true;
        }
        System.out.print("[");
        for (int i = 0; i < size; i++) {
            if(isInteger){
                if(i==0){
                    System.out.printf("%2d, ",(cursor.next.element));
                    cursor = cursor.next;
                    continue;
                }
                System.out.printf("%3d, ",(cursor.next.element));
                cursor = cursor.next;
            }
            else {
                System.out.print(cursor.next.element + ", ");
                cursor = cursor.next;
            }
            if (i % 20 == 19)
                System.out.println("   NEXT LINE ==>");
        }
        System.out.println("]");
    }


    public static void main(String[] args){

        DoublyLinkedListWithSentinels<Integer> dlls = new DoublyLinkedListWithSentinels<>();

        for (int i = 0; i < 110; i++)
            dlls.add(i);

        //Add 999 for 5 times
        for (int i = 0; i < 5; i++) {
            dlls.add(999);
        }

        dlls.removeIndex(5);
        dlls.removeElement(999);

        System.out.println();

        dlls.print();

        Scanner s = new Scanner(System.in);
        while(true){
            System.out.println();
            System.out.println("[1] Add Element");
            System.out.println("[2] Insert Element");
            System.out.println("[3] Remove Index");
            System.out.println("[4] Remove Element");
            System.out.println("[5] Search Index");
            System.out.println("[6] Print All Elements");
            System.out.println("[7] Print Array Size");
            System.out.println("[0] EXIT");
            System.out.println();

            int input = s.nextInt();

            if(input == 0) {
                s.close();
                break;
            }

            if(input == 1){
                System.out.println("Enter the ELEMENT you want to add... ");
                dlls.add(s.nextInt());
            }
            if(input == 2){
                System.out.println("Enter the ELEMENT you want to insert... ");
                int element = s.nextInt();
                System.out.println("Enter the INDEX you want to insert... ");
                int index = s.nextInt();
                dlls.insert(element, index);
            }
            if(input == 3){
                System.out.println("Enter the INDEX you want to remove... ");
                int index = s.nextInt();
                dlls.removeIndex(index);
            }
            if(input == 4){
                System.out.println("Enter the ELEMENT you want to remove... ");
                int element = s.nextInt();
                dlls.removeElement(element);
            }
            if(input == 5){
                System.out.println("Enter the INDEX you want to search... ");
                int index = s.nextInt();
                int element = dlls.nodeAt(index);
                System.out.println("INDEX "+ index + " = "+ element);
            }
            if(input == 6){
                dlls.print();
            }
            if(input == 7){
                System.out.println("Array Size = "+dlls.length());
            }
        }

    }
}
