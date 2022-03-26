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
public interface BinaryNodeADT<T extends Comparable <T>> {
    
    public int numChildren();
    
    public T getElement();
    
    public void setElement(T value);
    
    public BinaryNode<T> getRight();
    
    public void setRight(BinaryNode<T> value);
    
    public BinaryNode<T> getLeft();
    
    public void setLeft(BinaryNode<T> value);
    
    public boolean isLeaf();
    
    public void hang(BinaryNode<T> value);
    
    public BinaryNode<T> getDad();
    
    public void setDad (BinaryNode<T> value);
}
