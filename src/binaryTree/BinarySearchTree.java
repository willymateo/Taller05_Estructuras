/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package binaryTree;

/**
 *
 * @author Willy Mateo
 * @param <E>
 */
public class BinarySearchTree<E extends Comparable<E>> {
    private Node<E> root;

    public BinarySearchTree() {
    }
    
    public BinarySearchTree(E element) {
        root = new Node(element);
    }
    
    private BinarySearchTree(Node<E> root) {
        this.root = root;
    }
    
    public E root() {
        return root.getElement();
    }
    
    public boolean isEmpty(){
        return root == null;
    }
    
    public int altura(){
        BinarySearchTree<E> rightTree = new BinarySearchTree(root.getRightSon());
        BinarySearchTree<E> leftTree = new BinarySearchTree(root.getLeftSon());
        return Math.max(altura(rightTree), altura(leftTree));
    }
    
    private int altura(BinarySearchTree<E> binarySearchTree){
        if (!binarySearchTree.isEmpty()) {
            BinarySearchTree<E> rightTree = new BinarySearchTree(root.getRightSon());
            BinarySearchTree<E> leftTree = new BinarySearchTree(root.getLeftSon());
        return Math.max(altura(rightTree), altura(leftTree)) + 1;
        }
        return 0;
    }
    
    public boolean add(E element){
        if (element != null) {
            root = add(root, element);
            return true;
        }
        return false;
    }
    
    private Node<E> add(Node<E> p, E element) {
        if (p == null) {
            return new Node(element);
        } else if (element.compareTo(p.getElement()) == 0) {
            return p;
        } else if (element.compareTo(p.getElement()) > 0) {
            Node<E> newNode = add(p.getRightSon(), element);
            newNode.setParent(p);
            p.setRightSon(newNode);
        } else {
            Node<E> newNode = add(p.getLeftSon(), element);
            newNode.setParent(p);
            p.setLeftSon(newNode);
        }
        return p;
    }
    
    public void preOrden(){
        if (!isEmpty()) {
            System.out.println(root);
            new BinarySearchTree(root.getLeftSon()).preOrden();
            new BinarySearchTree(root.getRightSon()).preOrden();
        }
    }
    
    public void inOrden(){
        if (!isEmpty()) {
            new BinarySearchTree(root.getLeftSon()).preOrden();
            System.out.println(root);
            new BinarySearchTree(root.getRightSon()).preOrden();
        }
    }
    
    public void postOrden(){
        if (!isEmpty()) {
            new BinarySearchTree(root.getLeftSon()).preOrden();
            new BinarySearchTree(root.getRightSon()).preOrden();
            System.out.println(root);
        }
    }
    
    public BinarySearchTree<E> espejo(){
        BinarySearchTree<E> tree = new BinarySearchTree();
        tree.root = espejo(root);
        return tree;
    }
    
    private Node<E> espejo(Node<E> p) {
        if (p == null) return null;
        Node<E> temp = p.leftSon;
        Node<E> nodo = espejo(p.rightSon);
        p.leftSon = nodo;
        if(nodo != null)
            nodo.parent=p;
        nodo = espejo(temp);
        p.rightSon=nodo;
        if(nodo != null)
            nodo.parent = p;
        return p;
    }
    
    public boolean isMirror(BinarySearchTree<E> tree) {
        return this.equals(tree.espejo());
    }
    
    public int sumaPar() {
        return sumaPar((Node<Integer>) root, 0);
    }
    
    private int sumaPar(Node<Integer> node, int acum){
        if (node == null) {
            return 0;
        }
        if (node.element % 2 == 0) {
            acum += node.element; 
        }
        return acum + sumaPar(node.leftSon, acum) + sumaPar(node.rightSon, acum);
    }
    
    public int maxPar() {
        return maxPar((Node<Integer>) root, Integer.MIN_VALUE);
    }
    
    private int maxPar(Node<Integer> node, int max){
        if (node == null) {
            return max;
        }
        boolean esPar = (node.element % 2) == 0;
        if (esPar && node.element > max) {
            max = node.element;
        }
        return Math.max(maxPar(node.rightSon, max), maxPar(node.leftSon, max));
    }
    
    /**
     * Inner class of nodes of the Binary Search Tree
     * @param <E> Element
     */
    private class Node<E>{
        private E element;
        private Node<E> parent, rightSon, leftSon;

        public Node() {
        }

        public Node(E element) {
            this.element = element;
        }

        public Node(E element, Node<E> parent) {
            this(element);
            this.parent = parent;
        }
        
        public E getElement() {
            return element;
        }

        public Node<E> getParent() {
            return parent;
        }

        public Node<E> getLeftSon() {
            return leftSon;
        }

        public Node<E> getRightSon() {
            return rightSon;
        }

        public void setElement(E element) {
            this.element = element;
        }

        public void setParent(Node<E> parent) {
            this.parent = parent;
        }

        public void setLeftSon(Node<E> leftSon) {
            this.leftSon = leftSon;
        }

        public void setRightSon(Node<E> rightSon) {
            this.rightSon = rightSon;
        }
        
        public boolean isLeaf(){
            return rightSon == null && leftSon == null;
        }

        @Override
        public String toString() {
            return element.toString();
        }
    }
}
