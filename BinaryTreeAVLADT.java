/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Arboles;

import java.util.Iterator;

/**
 *
 * @author Jose Luis
 */
public interface BinaryTreeAVLADT  <T extends Comparable <T>> {
    
    public BinaryNodeAVL<T> getRootElement(); 
    
    public boolean isEmpty();
    
    public int size();
    
    public boolean contains(T element);
    
    public T find(T target);   
    
    public String toString();
    
    public Iterator<T> iteratorPreOrder();
    
    public Iterator<T> iteratorPostOrder();
    
    public Iterator<T> iteratorInOrder();
    
    public Iterator<T> iteratorLevelOrder();
    
    public BinaryNodeAVL<T> search(T element);
    
    public int getHeight();
    
    public void addElement(T element);
    
    public T removeElement(T element);    
}
