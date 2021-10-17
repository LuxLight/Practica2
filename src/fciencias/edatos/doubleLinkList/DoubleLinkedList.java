package fciencias.edatos.doubleLinkList;
import java.util.Iterator;


/**
 * Practica 2: Doubly Linked List.
 * @author Reyes Ramos Luz María 318211073
 * @author -----
 * @version 1.0 Octubre 2021
 * @since Estructuras de datos lab 2022-1
 */
public class DoubleLinkedList<T> implements TDAList<T> {
    /**Nodo de la lista */
    private class Node{
        /**Node element */
        private T element;
        /**Next Node */
        private Node next;
        /**Prev node */
        private Node prev;
        /**
         * Create Node
         * @param element Node´s element
         */
        public Node(T element){this.element = element;}
        /**
         * Modifies next node
         * @param newNode New next node.
         */
        public void setNext(Node newNode){this.next = newNode;}
        /**
         * Modifies prev Node
         * @param newPrev New prev Node
         */
        public void setPrev(Node newPrev){this.prev = newPrev;}
        /**
         * Access to node's data
         * @return element 
         */
        public T getElement(){return element;}
        /**
         * Modifies/changes a Node's element
         * @param newElement New element
         */
        public void setElement(T newElement){this.element = newElement;}
        /**
         * Access to the next node
         * @return next 
         */
        public Node getNext(){return next;}
        /**
         * Access to the prev node.
         * @return prev
         */
        public Node getPrev(){return prev;}




    }
    //--------------------------- L I S T -------------------------------------------------------------
    /**List's head */
    private Node head; 
    /**List's tail */
    private Node tail;
    /** List's size */
    private int size;

    @Override
    public void add(int i, T e) throws IndexOutOfBoundsException{
        //Indice fuera de rango definido
        if(i>size()+1 || i<0)
            throw new IndexOutOfBoundsException();
        
        Node newNode = new Node(e);
        //Si es vacia
        if(isEmpty() && i==0){
            head= tail = newNode;
            size++;
            return;
        }

        //Añadir al incio
        if(i==0){
            newNode.setNext(head);
            head.setPrev(newNode);
            head = newNode;
            size++;
            return;
        }
        //Añadir al final 
        if(i == size){
            tail.setNext(newNode);
            newNode.setPrev(tail);
            tail = newNode;
            size++;
            return;
        }
        
        //Cualquier otro índice dentro del rango
        Node iterador1 = head;
        Node iterador2= tail;
        int limit = size()/2;
        for(int n =0 , m=size()-1; n<size(); n++,m--){
            //Indice cerca de head , posicionarse 1 antes
            if(n == i-1){
                newNode.setNext(iterador1.getNext());
                newNode.setPrev(iterador1);
                iterador1.setNext(newNode);
                size++;
                return;
            }
            iterador1 = iterador1.getNext();
            
            //Indice cerca de tail
            if(m ==i-1){
                newNode.setNext(iterador2.getNext());
                newNode.setPrev(iterador2);
                iterador2.setNext(newNode);
                size++;
                return;
            }
            iterador2 = iterador2.getPrev();    
            
            if(n==m){
                return;
            }
            
        }


    }
    @Override
    public void clear(){
        head = null;

    }
    @Override
    public boolean contains(T e){
        Node iterador1 = head;
        Node iterador2 = tail;
        int limit = size()/2;
        for(int n =0, m = size()-1; n<limit; n++,m--){
            if(e.equals(iterador1.getElement())){
                return true;
            }
            iterador1 = iterador1.getNext();
            //Solo recorre la  mitad
            if(e.equals(iterador2.getElement())){
                return true;
            }
            iterador2 = iterador2.getPrev();
             //   A B C D
        }

        return false;
    }
    @Override
    public T get(int i) throws IndexOutOfBoundsException{
        Node iterador1 = head;
        Node iterador2 = tail;
        int limit = size()/2;
        if(i>size()-1 || i<0){
            throw new IndexOutOfBoundsException();

        }
        for(int n =0, m =size()-1; n<limit; n++,m--){
            if(n == i){
                return iterador1.getElement();

            }
            iterador1 = iterador1.getNext();
            if(m==i){
                return iterador2.getElement();
            }
            iterador2 = iterador2.getPrev();
        }
        return null;
    }
    @Override
    public boolean isEmpty(){
        boolean empty = (head == null) ? true : false;
        return empty;
    }
    public T remove(int i) throws IndexOutOfBoundsException{
        if(i>size()-1 || i<0){
            throw new IndexOutOfBoundsException();
        }
        //Si i es 0 
        Node eliminado = null;
        if( i ==0){
            eliminado = head;
            head = head.getNext();
            size--;
            return eliminado.getElement();
        }
        //  Si es el ultimo elemento
        if(i == size()-1){
            eliminado = tail;
            tail= tail.getPrev();
            tail.setNext(null);
            size--;
            return eliminado.getElement();
        }
        //Algun otro indice
        int limit = size()/2;
        Node iterador1 = head;
        Node iterador2 = tail;
        System.out.println("TAIL: "+ tail.getElement());
        System.out.println("TAILPREV: "+ tail.getPrev().getElement());
        System.out.println("TAILPREVPREV: "+ tail.getPrev().getPrev().getElement());
        for(int n =0, m = size()-1; n<limit; n++, m--){
            if(n == i ){
                eliminado = iterador1;
                //System.out.println("PREV "+ iterador1.getElement() );
                iterador1.getPrev().setNext(iterador1.getNext());
                iterador1.getNext().setPrev(iterador1.getPrev());
                System.out.println("------");
                size--;
                return  eliminado.getElement();
            }
            iterador1 = iterador1.getNext();

            if(m == i){

                System.out.println("------ + IT"+ iterador2.getElement());
                eliminado = iterador2; 
                System.out.println("PREV "+ iterador2.getPrev().getElement() + "NEXT  "+ iterador2.getNext().getElement() );
                iterador2.setNext(iterador2.getNext());
                iterador2.getNext().setPrev(iterador2.getPrev());
                System.out.println("------");
                size--;
                return eliminado.getElement();

            }
            iterador2 = iterador2.getPrev();
            System.out.println("IT2: "+ iterador2.getElement());
            
            
        }
        return eliminado.getElement();
    }
    @Override
    public int size(){
        return size;
    }
    @Override 
    public void revert(){

    }
    @Override
    public TDAList cut(boolean side){
        return null;
    }
    @Override
    public String toString(){
        String formato = "";

        Node iterador = head;
        while(iterador != null){
            formato += iterador.getElement() + "\n";
            iterador = iterador.getNext();
        }

        return formato;
    }
    @Override 
    public Iterator listIterador(){
        return null;
    }

    
}
