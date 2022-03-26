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
public class BinaryTreeAVL <T extends Comparable <T>> implements BinaryTreeAVLADT<T> {
    
    private BinaryNodeAVL<T> root;
    private int cont;
    
    public BinaryTreeAVL (){
        root = null;
        cont = 0;
    }
    
    public BinaryTreeAVL (T element){
        root = new BinaryNodeAVL<T>(element);
        cont = 1;
    }
    
    public BinaryTreeAVL (T element, BinaryNodeAVL<T> right, BinaryNodeAVL<T> left){
        root = new BinaryNodeAVL(element);
        root.setLeft(left);
        root.setRight(right);
        cont = 3;
    }

    public BinaryNodeAVL<T> getRootElement() {
        return this.root;
    }

    public boolean isEmpty() {
        return this.root == null;
    }

    public int size() {
        return cont;
    }

    public boolean contains(Comparable target) {
        BinaryNodeAVL<T> current  = findNode((T)target, root);
        return current != null;
    }

    public T find(Comparable target) {
        BinaryNodeAVL<T> current  = findNode((T)target, root);
        if (current == null)
                throw new RuntimeException ("Element not found");
        return current.getElement();
    }
    
    private BinaryNodeAVL<T> findNode (T target, BinaryNodeAVL<T> next){
        if (next == null)
            return null;
        if (next.getElement().equals(target))
            return next;
        
        BinaryNodeAVL<T> temp = findNode(target, next.getLeft());
        
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
    
    private void preOrder(ArrayList<T> list, BinaryNodeAVL<T> node){
        if (root == null)
            return;
        list.add(node.getElement());
        preOrder (list, node.getLeft());
        preOrder (list, node.getRight());
    }

    @Override
    public Iterator<T> iteratorPostOrder() {
        ArrayList<T> list = new ArrayList(); 
        postOrder (list, root);
        return list.iterator();
    }
    
    private void postOrder(ArrayList<T> list, BinaryNodeAVL<T> node){
        if (root == null)
            return;
        postOrder (list, node.getLeft());
        postOrder (list, node.getRight());
        list.add(node.getElement());
    }

    public Iterator<T> iteratorInOrder() {
        ArrayList<T> list = new ArrayList(); 
        inOrder (list, root);
        return list.iterator();
    }
    
    private void inOrder(ArrayList<T> list, BinaryNodeAVL<T> node){
        if (root == null)
            return;
        inOrder (list, node.getLeft());
        list.add(node.getElement());
        inOrder (list, node.getRight());
    }

    public Iterator<T> iteratorLevelOrder() {
        ArrayList<T> list = new ArrayList();
        Queue<BinaryNodeAVL<T>> queue = new LinkedList<BinaryNodeAVL<T>>();
        queue.add(root);
        BinaryNodeAVL<T> main;
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

    public BinaryNodeAVL<T> search(Comparable target) {
        BinaryNodeAVL<T> current  = findNode((T)target, root);
        if (current == null)
                throw new RuntimeException ("Element not found");
        return current;
    }

    public int getHeight() {
        return getHeight(root, 0);
    }
    
    private int getHeight(BinaryNodeAVL<T> node, int cont) {
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
    
    private void updateUpRotate (BinaryNodeAVL node){
        
        if (node == null)
            return;
        BinaryNodeAVL<T> current = node;
        while (current.getDad() != null){
            current.getDad().updateHeight();
            current = current.getDad();
        } 
        
        current = node;
        while (current.getDad() != null){
            current.getDad().updateEqFactor();
            current = current.getDad();
        }
        
        current = node;
        do {
            if (current.getEqFactor() == -2 || current.getEqFactor() == 2){
                rotate(current);
            }
            current = current.getDad();
        } while (current != null);
    }

    public void addElement(Comparable element) {
        if (this.isEmpty())
            root = new BinaryNodeAVL(element);
        
        else {
            if (element.compareTo(root.getElement())<0){
                if (root.getLeft()==null){
                    BinaryNodeAVL<T> newNode = new BinaryNodeAVL(element);
                    this.root.setLeft(newNode);
                    root.getLeft().setDad(root);
                    updateUpRotate(newNode);
                }
                else
                    addElement((T)element, root.getLeft());
            }
            else{
                if (root.getRight()==null){
                    BinaryNodeAVL<T> newNode = new BinaryNodeAVL(element);
                    this.root.setRight(newNode);
                    root.getRight().setDad(root);
                    updateUpRotate(newNode);
                }
                else
                    addElement((T)element, root.getRight());
            }
        }
        cont ++;
    }
    
    private void addElement (T element, BinaryNodeAVL node){
        if (element.compareTo((T)node.getElement())<0){
            if (node.getLeft() == null){
                BinaryNodeAVL<T> newNode = new BinaryNodeAVL(element);
                node.setLeft(newNode);
                node.getLeft().setDad(node);
                updateUpRotate(newNode);
            }
            else
                addElement(element, node.getLeft());
        }
        else {
            if (node.getRight() == null){
                BinaryNodeAVL<T> newNode = new BinaryNodeAVL(element);
                node.setRight(newNode);
                node.getRight().setDad(node);
                updateUpRotate(newNode);
            }
            else
                addElement(element, node.getRight());            
        }
    }

    public T removeElement(Comparable element) {
        T result = null;
        
        if (this.isEmpty())
            throw new RuntimeException ("Element not found");
        
        else{
            BinaryNodeAVL parent = null;
            if (element.equals(root.getElement())){
                result = root.getElement();
                BinaryNodeAVL temp = replacement(root);
                if (temp == null)
                    root = null;
                else{
                    root.setElement((T)temp.getElement());
                    root.setRight(temp.getRight());
                    temp.getRight().setDad(root);
                    root.setLeft(temp.getLeft());
                    temp.getLeft().setDad(root);
                }
                
                cont --;
                
                BinaryNodeAVL<T> aux;
                if (temp == null)
                    aux = parent;
                else
                    aux= findNextInOrder(temp);
                
                if (aux != null){
                    aux.updateHeight();
                    aux.updateEqFactor();
                }

                updateUpRotate(aux);
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
    
    private T removeElement (T element, BinaryNodeAVL node, BinaryNodeAVL parent){
        T result = null;
        if (node == null)
            throw new RuntimeException ("Element not found");
        else {
            if (element.equals(node.getElement())){
                result = (T)node.getElement();
                BinaryNodeAVL temp = replacement(node);
                if (parent.getRight() == node){
                    parent.setRight(temp);
                    if (temp != null)
                        temp.setDad(parent);
                }
                else{
                    parent.setLeft(temp);
                    if (temp != null)
                        temp.setDad(parent);
                }
                cont --;
                BinaryNodeAVL<T> aux;
                if (temp == null)
                    aux = parent;
                else
                    aux = findNextInOrder(temp);
                
                if (aux != null){
                    aux.updateHeight();
                    aux.updateEqFactor();
                }
                
                updateUpRotate(aux);
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
    
    private BinaryNodeAVL<T> replacement (BinaryNodeAVL node){
        BinaryNodeAVL<T> result  = null;
        
        if (node.getLeft() == null && node.getRight()== null)
            result = null;
        
        else if (node.getLeft() != null && node.getRight()== null)
            result = node.getLeft();
        
        else if (node.getLeft()== null && node.getRight() != null)
            result = node.getRight();
        
        else {
            BinaryNodeAVL current = node.getRight();
            BinaryNodeAVL parent = node;
            
            while (current.getLeft() != null){
                parent = current;
                current = current.getLeft();
            }
            
            current.setLeft(node.getLeft());
            if (current.getLeft() != null)
                current.getLeft().setDad(current);
            if (node.getRight() != current){
                parent.setLeft(current.getRight());
                if (parent.getRight() != null)
                    parent.getRight().setDad(parent);
                current.setRight(node.getRight());
                if (node.getRight() != null)
                    node.getRight().setDad(current);
            }
            
            result = current;
        }
        return result;
    }
    
    private BinaryNodeAVL<T> findNextInOrder (BinaryNodeAVL<T> node){
        BinaryNodeAVL<T> result = node;
        if (node.getRight() != null){
            BinaryNodeAVL current = node.getRight();          
            while (current.getLeft() != null)
                current = current.getLeft();
            result = current;
        }
        return result;
    } 
    
    public String toString(){
        int height = this.getHeight();
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= height; i++){
            printLevel (root, i, sb);
            sb.append('\n');
        }
        return sb.toString();
    }
    
    private void printLevel (BinaryNodeAVL<T> node, int level, StringBuilder sb) {
        if (node == null)
            return;
        if (level == 1){
            if (node.getDad() == null)
                sb.append(node.getElement()).append("(root,").append(node.getEqFactor()).append(",").append(node.getHeight()).append(") ");
            else{
                sb.append(node.getElement()).append("(").append(node.getDad().getElement()).append(",").append(node.getEqFactor());
                sb.append(",").append(node.getHeight()).append(") ");
            }
        }
        if (level > 1){
            printLevel(node.getLeft(), level-1, sb);
            printLevel(node.getRight(), level-1, sb);
        }
    }
    
    private void updateUp (BinaryNodeAVL<T> node){
        if (node == null)
            return;
        BinaryNodeAVL<T> current = node;
        while (current.getDad() != null){
            current.updateHeight();
            current = current.getDad();
        } 
        root.updateHeight();;
        current = node;
        while (current.getDad() != null){
            current.updateEqFactor();
            current = current.getDad();
        }
        root.updateEqFactor();
    }
    //Las rotaciones yo las hago como se explican en el libro
    //Que los nombres están inversos de como los vimos en clase
    //Ejemplo: rotación cargada izquierda, es una rotación derecha
    //Por que el elemento lo movemos a la izquierda
    private void rotate (BinaryNodeAVL<T> node){
        if (node.getEqFactor() == -2 && (node.getLeft().getEqFactor() == -1 || node.getLeft().getEqFactor() == 0)){
            rotateRight(node);
            updateUp(node);
        }
        else if (node.getEqFactor() == 2 && (node.getRight().getEqFactor() == 1 || node.getRight().getEqFactor() == 0)){
            rotateLeft(node);
            updateUp (node);
        }
        else if (node.getEqFactor() == -2 && node.getLeft().getEqFactor() == 1){
            BinaryNodeAVL<T> aux = node.getLeft();
            rotateLeft(node.getLeft());
            rotateRight(node);
            aux.updateHeight();
            aux.updateEqFactor();
            updateUp(node);
        }
        else if (node.getEqFactor() == 2 && node.getRight().getEqFactor() == -1){
            BinaryNodeAVL<T> aux = node.getRight();
            rotateRight(node.getRight());
            rotateLeft(node);
            aux.updateHeight();
            aux.updateEqFactor();           
            updateUp(node);
        }
    }  
    
    private void rotateLeft (BinaryNodeAVL<T> node){
        BinaryNodeAVL<T> newSubRoot = node.getRight();
        BinaryNodeAVL<T> aux = newSubRoot.getLeft();
        if (node != root){
            BinaryNodeAVL<T> parent = node.getDad();
            if (parent.getLeft() == node)
                parent.setLeft(newSubRoot);
            else
                parent.setRight(newSubRoot);
            newSubRoot.setDad(parent);
            node.setDad(newSubRoot);
            newSubRoot.setLeft(node);
            node.setRight(aux);
            if (aux != null)
                aux.setDad(node);
        }
        else {
            root = newSubRoot;
            newSubRoot.setDad(null);
            node.setDad(newSubRoot);
            newSubRoot.setLeft(node);
            node.setRight(aux);
            if (aux != null)
                aux.setDad(node);
        }
    }
    
    private void rotateRight (BinaryNodeAVL<T> node){
        BinaryNodeAVL<T> newSubRoot = node.getLeft();
        BinaryNodeAVL<T> aux = newSubRoot.getRight();
        if (node != root){
            BinaryNodeAVL<T> parent = node.getDad();
            if (parent.getLeft() == node)
                parent.setLeft(newSubRoot);
            else
               parent.setRight(newSubRoot);
            newSubRoot.setDad(parent);
            node.setDad(newSubRoot);
            newSubRoot.setRight(node);
            node.setLeft(aux);  
            if (aux != null)
                aux.setDad(node);
        }
        else {
            root = newSubRoot;
            newSubRoot.setDad(null);
            node.setDad(newSubRoot);
            newSubRoot.setRight(node);
            node.setLeft(aux);
            if (aux != null)
                aux.setDad(node);
        }
    }
    
}
