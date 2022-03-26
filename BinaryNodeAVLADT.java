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
public interface BinaryNodeAVLADT <T extends Comparable <T>> {
    public T getElement();
    
    public void setElement(T element);
    
    public BinaryNodeAVL<T> getLeft();
    
    public BinaryNodeAVL<T> getRight();
    
    public int getHeight();
    
    public int getEqFactor();
    
    public void updateHeight();
    
    public void updateEqFactor();
    
    public boolean isLeaf();
    
    public void setLeft(BinaryNodeAVL<T> node);
    
    public void setRight(BinaryNodeAVL<T> node);
 
    public void setDad(BinaryNodeAVL<T> node);
    
    public BinaryNodeAVL<T> getDad();    
}
