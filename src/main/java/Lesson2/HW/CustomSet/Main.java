package Lesson2.HW.CustomSet;

import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA.
 * User: andrew
 * Date: 3/28/14
 * Time: 11:58 PM
 */

interface Container<T>{
    public void performPut(T element);
    public T performGet(int index);
    public void performRemove(int index);
    public boolean performContains(T element);
    public int getSize();
}

public class Main {
    public static void main(String[] args) {
		CustomSet<Integer> set = new CustomSet<Integer>(new CustomArrayList<Integer>());
		for (int i = 0; i < 5; i++){
			set.add((int)(Math.random() * 11));
		}
		set.showAllElements();

		set.remove(0);
		set.remove(set.size() - 1);

		set.showAllElements();

		set.add(set.get(set.size() - 1));

		set.showAllElements();

		System.out.println("Contains 5 ? - " + set.contains(5));
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

/*
class CustomLinkedList<T> extends LinkedList<T> implements Container<T>{

	@Override
	public void performPut(T element) {
		//Implemented
	}

	@Override
	public T performGet(int index) {
		return null;  //Implemented
	}

	@Override
	public void performRemove(int index) {
		//Implemented
	}

	@Override
	public boolean performContains(T element) {
		return false;  //Implemented
	}

	@Override
	public int getSize() {
		return 0;  //Implemented
	}
}*/
