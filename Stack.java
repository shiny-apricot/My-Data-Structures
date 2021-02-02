
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