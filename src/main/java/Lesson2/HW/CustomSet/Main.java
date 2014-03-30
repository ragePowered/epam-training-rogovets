package Lesson2.HW.CustomSet;

import java.util.ArrayList;
import java.util.LinkedList;

/**
 * Created with IntelliJ IDEA.
 * User: andrew
 * Date: 3/28/14
 * Time: 11:58 PM
 */

/**
 * Написать максимально расширяемый проект множество Set(множество может содержать различные типы,
 * может быть реализовано на базе списка, массива и т.д.)
 * */

interface Container<T>{
    public void performPut(T element);
    public T performGet(int index);
    public void performRemove(int index);
    public boolean performContains(T element);
    public int getSize();
}

public class Main {
    public static void main(String[] args) {
		System.out.println("==== CustomArrayList section ====");
		CustomSet<Integer> setOnArrayList = new CustomSet<Integer>(new CustomArrayList<Integer>());
		for (int i = 0; i < 5; i++){
			setOnArrayList.add((int) (Math.random() * 11));
		}
		setOnArrayList.showAllElements();
		setOnArrayList.remove(0);
		setOnArrayList.remove(setOnArrayList.size() - 1);
		setOnArrayList.showAllElements();
		setOnArrayList.add(setOnArrayList.get(setOnArrayList.size() - 1));
		setOnArrayList.showAllElements();
		System.out.println("Contains 5 ? - " + setOnArrayList.contains(5));

		System.out.println("==== CustomLinkedList section ====");
		CustomSet<Integer> setOnLinked = new CustomSet<Integer>(new CustomLinkedList<Integer>());
		for (int i = 0; i < 5; i++){
			setOnLinked.add((int) (Math.random() * 11));
		}
		setOnLinked.showAllElements();
		setOnLinked.remove(0);
		setOnLinked.remove(setOnLinked.size() - 1);
		setOnLinked.showAllElements();
		setOnLinked.add(setOnLinked.get(setOnLinked.size() - 1));
		setOnLinked.showAllElements();
		System.out.println("Contains 5 ? - " + setOnLinked.contains(5));

	}
}

class CustomSet<T>{
    private Container<T> container;

    CustomSet(Container<T> container) {
        this.container = container;
    }

    public void add(T element){
        this.container.performPut(element);
    }

    public T get(int index){
		return this.container.performGet(index);
    }

	public void remove(int index){
		this.container.performRemove(index);
	}

	public boolean contains(T element){
		return this.container.performContains(element);
	}

	public int size(){
		return this.container.getSize();
	}

	public void showAllElements(){
		for (int i = 0; i < container.getSize(); i++){
			System.out.print(container.performGet(i) + " ");
		}
		System.out.println();
	}
}

class CustomArrayList<T> extends ArrayList<T> implements Container<T>{
	private ArrayList<T> list = new ArrayList<T>();

	@Override
	public void performPut(T element) {
		list.add(element);
	}

	@Override
	public T performGet(int index) {
		return list.get(index);
	}

	@Override
	public void performRemove(int index) {
		list.remove(index);
	}

	@Override
	public boolean performContains(T element) {
		return list.contains(element);
	}

	@Override
	public int getSize() {
		return list.size();
	}
}

class CustomLinkedList<T> extends LinkedList<T> implements Container<T>{
	private LinkedList<T> list = new LinkedList<T>();

	@Override
	public void performPut(T element) {
		list.add(element);
	}

	@Override
	public T performGet(int index) {
		return list.get(index);
	}

	@Override
	public void performRemove(int index) {
		list.remove(index);
	}

	@Override
	public boolean performContains(T element) {
		return list.contains(element);
	}

	@Override
	public int getSize() {
		return list.size();
	}
}
