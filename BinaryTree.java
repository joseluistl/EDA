/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Arboles;

import java.util.*;

/**
 *
 * @author Jose Luis
 */
public class BinaryTree<T extends Comparable <T>> implements BinaryTreeADT {
    
    private BinaryNode<T> root;
    private int cont;
    
    public BinaryTree (){
        root = null;
        cont = 0;
    }
    
    public BinaryTree (T element){
        root = new BinaryNode<T>(element);
        cont = 1;
    }
    
    public BinaryTree (T element, BinaryNode<T> right, BinaryNode<T> left){
        root = new BinaryNode(element);
        root.setLeft(left);
        root.setRight(right);
        cont = 3;
    }

    public BinaryNode<T> getRootElement() {
        return this.root;
    }

    public boolean isEmpty() {
        return this.root == null;
    }

    public int size() {
        return cont;
    }

    public boolean contains(Comparable target) {
        BinaryNode<T> current  = findNode((T)target, root);
        return current != null;
    }

    public T find(Comparable target) {
        BinaryNode<T> current  = findNode((T)target, root);
        if (current == null)
                throw new RuntimeException ("Element not found");
        return current.getElement();
    }
    
    public BinaryNode<T> findNode (T target, BinaryNode<T> next){
        if (next == null)
            return null;
        if (next.getElement().equals(target))
            return next;
        
        BinaryNode<T> temp = findNode(target, next.getLeft());
        
        if (temp == null)
            temp = findNode(target, next.getRight());
        
        return temp;
    }

    @Override
    public Iterator<T> iteratorPreOrder() {
        ArrayList<T> list = new ArrayList(); 
        preOrder (list, root);
        return list.iterator();
    }
    
    private void preOrder(ArrayList<T> list, BinaryNode<T> node){
        if (root == null)
            return;
        list.add(node.getElement());
        inOrder (list, node.getLeft());
        inOrder (list, node.getRight());
    }

    @Override
    public Iterator<T> iteratorPostOrder() {
        ArrayList<T> list = new ArrayList(); 
        postOrder (list, root);
        return list.iterator();
    }
    
    private void postOrder(ArrayList<T> list, BinaryNode<T> node){
        if (root == null)
            return;
        inOrder (list, node.getLeft());
        inOrder (list, node.getRight());
        list.add(node.getElement());
    }

    public Iterator<T> iteratorInOrder() {
        ArrayList<T> list = new ArrayList(); 
        inOrder (list, root);
        return list.iterator();
    }
    
    private void inOrder(ArrayList<T> list, BinaryNode<T> node){
        if (root == null)
            return;
        inOrder (list, node.getLeft());
        list.add(node.getElement());
        inOrder (list, node.getRight());
    }

    public Iterator<T> iteratorLevelOrder() {
        ArrayList<T> list = new ArrayList();
        Queue<BinaryNode<T>> queue = new LinkedList<BinaryNode<T>>();
        queue.add(root);
        BinaryNode<T> main;
        while (!queue.isEmpty()){
            main = queue.remove();
            if (main != null){
                list.add(main.getElement());
                queue.add(main.getLeft());
                queue.add(main.getRight());
            }            
        }
        return list.iterator();
    }

    public BinaryNode<T> search(Comparable target) {
        BinaryNode<T> current  = findNode((T)target, root);
        if (current == null)
                throw new RuntimeException ("Element not found");
        return current;
    }

    public int getHeight() {
        return getHeight(root, 0);
    }
    
    private int getHeight(BinaryNode<T> node, int cont) {
        if(node == null)
            return cont;
        int izq = getHeight(node.getLeft(), cont+1);
        int der = getHeight(node.getRight(), cont+1);
        return maximum(izq, der);
    }
    
    private int maximum (int a, int b){
        if (a>b)
            return a;
        return b;
    }

    public void addElement(Comparable element) {
        if (this.isEmpty())
            root = new BinaryNode(element);
        
        else {
            if (element.compareTo(root.getElement())>0){
                if (root.getLeft()==null)
                    this.root.setLeft(new BinaryNode(element));
                else
                    addElement((T)element, root.getLeft());
            }
            else{
                if (root.getRight()==null)
                    this.root.setRight(new BinaryNode(element));
                else
                    addElement((T)element, root.getRight());
            }
        }
        cont ++;
    }
    
    private void addElement (T element, BinaryNode node){
        if (element.compareTo((T)node.getElement())<0){
            if (node.getLeft() == null)
                node.setLeft(new BinaryNode(element));
            else
                addElement(element, node.getLeft());
        }
        else {
            if (node.getRight() == null)
                node.setRight(new BinaryNode(element));
            else
                addElement(element, node.getRight());            
        }
    }

    public T removeElement(Comparable element) {
        T result = null;
        
        if (this.isEmpty())
            throw new RuntimeException ("Element not found");
        
        else{
            BinaryNode parent = null;
            if (element.equals(root.getElement())){
                result = root.getElement();
                BinaryNode temp = replacement(root);
                if (temp == null)
                    root = null;
                else{
                    root.setElement((T)temp.getElement());
                    root.setRight(temp.getRight());
                    root.setLeft(temp.getLeft());
                }
                
                cont --;
            }
            
            else{
                parent = root;
                if (element.compareTo(root.getElement())<0)
                    result = removeElement((T)element, root.getLeft(), parent);
                else
                    result = removeElement((T)element, root.getRight(), parent);
            }
        }
        
        return result;
    }
    
    private T removeElement (T element, BinaryNode node, BinaryNode parent){
        T result = null;
        if (node == null)
            throw new RuntimeException ("Element not found");
        else {
            if (element.equals(node.getElement())){
                result = (T)node.getElement();
                BinaryNode temp = replacement(node);
                if (parent.getRight() == node)
                    parent.setRight(temp);
                else
                    parent.setLeft(temp);
                cont --;
            }
            else {
                parent = node;
                if (element.compareTo((T)node.getElement())<0)
                    result = removeElement (element, node.getLeft(), parent);
                else
                    result = removeElement (element, node.getRight(), parent);
            }
        }
        return result;
    }
    
    private BinaryNode<T> replacement (BinaryNode node){
        BinaryNode<T> result  = null;
        
        if (node.getLeft() == null && node.getRight()== null)
            result = null;
        
        else if (node.getLeft() != null && node.getRight()== null)
            result = node.getLeft();
        
        else if (node.getLeft()== null && node.getRight() != null)
            result = node.getRight();
        
        else {
            BinaryNode current = node.getRight();
            BinaryNode parent = node;
            
            while (current.getLeft() != null){
                parent = current;
                current = current.getLeft();
            }
            
            current.setLeft(node.getLeft());
            if (node.getRight() != current){
                parent.setLeft(current.getRight());
                current.setRight(node.getRight());
            }
            
            result = current;
        }
        return result;
    }
    
    
    public BinaryNode<T> findNextInOrder(BinaryNode<T> root){
        if(root == null)
            return null;
        if(root.getRight() == null)
            return null;
        BinaryNode<T> aux = root.getRight();
        if(aux.getLeft() == null)
            return aux;
        else{
            BinaryNode<T> actual = aux;
            while(actual != null){
                aux = actual.getLeft();
                actual = actual.getLeft();
            }
            return aux;
        }
    }
    
}
