/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Arboles;

/**
 *
 * @author Jose Luis
 */
public class BinaryNode <T extends Comparable<T>> implements BinaryNodeADT<T>{
    
    private T element;
    private BinaryNode<T> right;
    private BinaryNode<T> left;
    private BinaryNode<T> dad;
    
    public BinaryNode (){
        this.dad = null;
        this.left = null;
        this.right = null;
        this.element = null;
    }
    
    public BinaryNode (T object){
        this.element = object;
        this.right = null;
        this.left = null;
    }
    
    public int numChildren() {
        int resp = 0;
        if (this.left != null)
            resp = resp + 1 + left.numChildren();
        if (this.right!= null)
            resp = resp + 1 + right.numChildren();
        return resp;
    }

    public T getElement() {
        return this.element;
    }

    public void setElement(T value) {
        this.element = value;
    }

    public BinaryNode<T> getRight() {
        return this.right;
    }

    public void setRight(BinaryNode<T> value) {
        this.right = value;
    }

    public BinaryNode<T> getLeft() {
        return this.left;
    }

    public void setLeft(BinaryNode<T> value) {
        this.left = value;
    }

    public boolean isLeaf() {
        return (this.left == null && this.right == null);
    }

    public void hang(BinaryNode<T> value) {
        if(value==null)
            return;
        if(value.getElement().compareTo(element) < 0)
            this.left = value;
        else
            right = value;
        value.setDad(this);
    }

    public BinaryNode<T> getDad() {
        return this.dad;
    }
    
    public void setDad(BinaryNode<T> value){
        this.dad = value;
    } 
    
    public boolean hasOnlyOneChild(){
        return (this.left != null && this.right == null) || (this.left == null && this.right != null);
    }
    
    public boolean isFull(){
        return this.right != null && this.left != null;
    }
    
}
