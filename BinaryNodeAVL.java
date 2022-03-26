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
public class BinaryNodeAVL <T extends Comparable<T>> implements BinaryNodeAVLADT<T> {
    
    private T element;
    private BinaryNodeAVL<T> left;
    private BinaryNodeAVL<T> right;
    private BinaryNodeAVL<T> dad;
    private int eqFactor;
    private int height;
    
    public BinaryNodeAVL (){
        element = null;
        left = null;
        right = null;
        dad = null;
    }
    
    public BinaryNodeAVL (T element){
        this.element = element;
        left = null;
        right = null;
        dad = null;
        eqFactor = 0;
        height = 1;
    }
    
    public T getElement(){
        return element;
    }
    
    public void setElement (T data){
        element = data;
    }
    
    public BinaryNodeAVL<T> getLeft(){
        return left;
    }
    
    public BinaryNodeAVL<T> getRight(){
        return right;
    }
    
    public int getHeight(){
        return height;
    }
    
    public int getEqFactor(){
        return eqFactor;
    }
    
    public boolean isLeaf(){
        return (left == null && right == null);
    }
    
    public void setLeft(BinaryNodeAVL<T> node){
        left = node;
    }
    
    public void setRight(BinaryNodeAVL<T> node){
        right = node;
    }
 
    public void setDad(BinaryNodeAVL<T> node){
        dad = node;
    }
    
    public BinaryNodeAVL<T> getDad(){
      return dad;  
    } 
    
    public boolean hasOnlyOneChild (){
        return (right == null && left != null) || (right != null && left == null);
    }
    
    public boolean isFull (){
        return (right != null && left != null);
    }
    
    public void updateHeight (){
        if (this.isLeaf())
            this.height = 1;
        else {
            if (this.hasOnlyOneChild()){
                if (left == null)
                    this.height = right.getHeight() + 1;
                else
                    this.height = left.getHeight() + 1;
            }
            else {
                if (left.getHeight()>right.getHeight())
                    this.height = left.getHeight() + 1;
                else
                    this.height = right.getHeight() + 1;
            }
        }
    }
    
    public void updateEqFactor() {
         if (this.isLeaf())
             this.eqFactor = 0;
         else {
             if (this.hasOnlyOneChild()) {
                 if (left == null)
                     this.eqFactor = right.getHeight();
                 else
                     this.eqFactor = -1*left.getHeight();
             }
             else {
                 this.eqFactor = right.getHeight() - left.getHeight();
             }
         }
    }
}
