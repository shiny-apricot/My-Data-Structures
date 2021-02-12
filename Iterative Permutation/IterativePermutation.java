class IterativePermutation<E> {

    Stack<String> stack = new Stack<>();

    public void permute(E n) {
        /* * * * * * * * * * * * * * * * * * * * * * * * * * *  //
        * If the input is n, create an array like [1,2,...,n]   //
        * by 'initializeArray' function                         */
        char[] arr = initializeArray((Integer) n);              //
        /* * * * * * * * * * * * * * * * * * * * * * * * * * *  */

        // Cast the n to Integer
        int length = (Integer) n;

        while (true)
        {
            // Cast the char array to String to be able to push the array to stack.
            // (it will be changing in every while loop)
            String value = String.valueOf(arr);
            stack.push(value);

            int i = length - 1;

            /* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *  */
            // The code below determines if the array is                            //
            // sorted backwards (like 4321)                                         */
            while (arr[i - 1] >= arr[i])                                            //
            {                                                                       //
                // If you can reach the first index,                                //
                // it means that this is a reverse sorted array.                    //
                // So, you've reached to the end of permutation (like 4321)         //
                // and the loop can be stopped.                                     //
                if (i - 1 == 0) {                                                   //
                    // IT IS OVER, PRINT THE STACK                                  //
                    String stackPrint = stack.printStack();                         //
                    System.out.println(stackPrint);                                 //
                    return;                                                         //
                }                                                                   //
                i--;                                                                //
            }                                                                       //
            /* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *  * */

            // THE CODE BELOW IS A STANDARD PERMUTATION PROCESS

            // Find a substring of the array such that
            // s[i..n-1] is sorted in reverse order.
            // After finding 'i', 'j' becomes the right
            // index of the 'i' automatically.
            int j = length - 1;
            while (j > i && arr[j] <= arr[i - 1]) {
                j--;
            }

            // Swap index i-1 with index j
            swap(arr, i - 1, j);

            // Reverse the substring s[i..n-1]
            reverseArray(arr, i, length - 1);
        }
    }

    // If the input is n, create an array like [1,2,...,n]
    private char[] initializeArray(int n) {
        char[] arr = new char[n];
        for(int i=0; i<n; i++) {
            String value = String.valueOf(i+1);
            arr[i] = value.charAt(0);
        }
        return arr;
    }


    // This function, basically, swaps two index of an array
    private void swap(char[] arr, int i, int j) {
        char c = arr[j];
        arr[j] = arr[i];
        arr[i] = c;
    }

    // Reverse a part of an array between two indices.
    private void reverseArray(char[] arr, int i, int j) {
        // swap the two end-point until they meet at the middle.
        while (i < j) {
            swap(arr, i++, j--);
        }
    }
	
}


/* Yasin İnal */

////////////////////////////////////////////////////////////////////////////
//////////////////////////////  STACK   ////////////////////////////////////
////////////////////////////////////////////////////////////////////////////
    
    public static class Stack<E> {
        private Node<E> trailer;  //top point
        private int size = 0;

        public Stack() {
            trailer = new Node<>(null, null, null);
        }

        /* DEFINE THE STRUCTURE OF A SINGLE NODE */
        private static class Node<E>
        {
            private E element;      //hold the element
            private Node<E> prev;   //hold the reference to the previous node
            private Node<E> next;   //hold the reference to the next node
            /* NODE CONSTRUCTOR */
            public Node(E element, Node<E> prev, Node<E> next) {
                this.element = element;
                this.prev = prev;
                this.next = next;
            }
            /* SETTER GETTER FUNCTIONS */
            public E        getElement()            {return element;}
            public void     setElement(E element)   {this.element = element; }
            public Node<E> getPrev()               {return prev; }
            public void     setPrev(Node<E> prev)   {this.prev = prev; }
            public Node<E> getNext()               {return next;}
            public void     setNext(Node<E> next)   {this.next = next;}
        }

        /* Return the size of the stack */
        public int size() {
            return size;
        }

        void push(E e){
            if(size == 0) {
                // Special function for adding the first element.
                addFirst(e);
                return;
            }
            ///////////// STANDARD PUSH PROCESS ///////////////
            /* * * * * * * * * * * * * * * * * * * * * * * * * * * * *
             * Take a copy of the trailer(top element) to the 'lastNode',
             * and make the 'newNode' as the top element of the stack (trailer)...
             *  * * * * * * * * * * * * * * * * * * * * * * * * * * */
            Node<E> lastNode = trailer;
            Node<E> newNode = new Node<>(e, lastNode, null);
            lastNode.setNext(newNode);      // Set the next element of the old top as newNode...
            trailer = newNode;
            size++;                         // Increase the size...
        }

        E pop(){
            if(size == 0) return null;
            /* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * /
            /* Get the element of the trailer (top) unless the stack is empty */
            E value = trailer.getElement();                                   //
            /* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *  */
            if(size == 1){
                trailer.element = null;
                size--;
                return value;
            }
            ///////////// STANDARD POP PROCESS ///////////////
            /*  * * * * * * * * * * * * * * * * * * * * * * * * *
			 * If the size of the stack is bigger than 1,
             * Reach the previous node of the top
             * and make the trailer show that.
             * So, the new 'top' of the stack is the previous node...
             * * * * * * * * * * * * * * * * * * * * * * * * * */

            Node<E> previousNode = trailer.getPrev();
            previousNode.setNext(null);     // Clean the old top of the stack...
            trailer = previousNode;
            size--;     // Reduce the size...
            return value;
        }

        /* * * * * * * * * * * * * * * * * *  *
        * PRINT ALL THE STACK                 *
        * * * * * * * * * * * * * * * * * * * */
        public String printStack(){
            StringBuilder sb = new StringBuilder();
            sb.append("( ");

            /* Cursor is at the trailer,(top of the stack) initially... */
            Node<E> cursor = trailer;
            for(int i=0; i<size; i++)
            {
                /* * * * * * * * * * * * * * * * * * * /
                /* APPEND THE CURRENT ELEMENT         */
                sb.append(cursor.element);            //
                /* * * * * * * * * * * * * * * * * * **/
                cursor = cursor.getPrev(); // Move the cursor to the previous element of the stack...

                /* Don't add comma to the last block... */
                if(i<size-1) {
                    sb.append(", ");
                }
                /* Go next line after 20 output... */
                if(i%20==19) {
                    sb.append(" ==> next line\n");
                }
            }
            /* Close the parenthesis... */
            sb.append(" )");
            return sb.toString();
        }

        // Special function for adding the first element.
        private void addFirst(E e) {
            trailer = new Node<>(e, null, null);
            size++;
        }

    }

