package Lesson7.HW.SingleThreadDijkstra;

import java.util.*;

/**
 * Created with IntelliJ IDEA.
 * User: andrew
 * Date: 4/16/14
 * Time: 2:14 PM
 */

public class Dijkstra{

	static PriorityQueue<Node> nodeQueue = Main.initialize();

	public static void calculate(Node source){
		source.setMinimumWeight(0);

		while (!nodeQueue.isEmpty()){
			Node eachInQueue = nodeQueue.poll();
			for (Map.Entry<Node, Integer> eachNeighbor : eachInQueue.getNeighbors().entrySet()){
				Node v = eachNeighbor.getKey();
				if (eachInQueue.getMinimumWeight() + eachNeighbor.getValue() < v.getMinimumWeight()){
					nodeQueue.remove(v);
					v.setMinimumWeight(eachInQueue.getMinimumWeight() + eachNeighbor.getValue());
					v.setParent(eachInQueue);
					nodeQueue.add(v);
				}
			}
		}
	}

	public static List<Node> getShortestPathTo(Node target){
		List<Node> path = new ArrayList<>();
		for (Node node = target; node != null; node = node.parent){
			path.add(node);
		}
		Collections.reverse(path);
		return path;
	}

	static class Node implements Comparable{
		private int id;									// Ідентифікатор вузла (номер)
		private TreeMap<Node, Integer> neighbors;		// TreeSet сусідів
		private int minimumWeight;						// Мінімальна вага гілки в якої this є листям
		private Node parent;							// Посилання на вузол з якого прийшли

		Node(int id) {
			this.id = id;
			this.neighbors = new TreeMap<>();
			this.minimumWeight = Integer.MAX_VALUE;
		}

		public void becomeNeighbors(Node that, Integer weight){
			this.neighbors.put(that, weight);
			that.addNeighbor(this, weight);
		}

		private void addNeighbor(Node that, Integer weight){
			this.neighbors.put(that, weight);
		}

		public int getId() {
			return id;
		}
		public TreeMap<Node, Integer> getNeighbors() {
			return neighbors;
		}
		public int getMinimumWeight() {
			return minimumWeight;
		}

		public void setMinimumWeight(int minimumWeight) {
			this.minimumWeight = minimumWeight;
		}
		public void setParent(Node parent) {
			this.parent = parent;
		}

		@Override
		public int compareTo(Object o) {
			return this.id - ((Node)o).getId();
		}

		@Override
		public String toString() {
			StringBuilder neighborsIDs = new StringBuilder();
			for (Node each : neighbors.keySet()) {
				neighborsIDs.append("#").append(each.getId()).append(" ");
			}
			return new Formatter().format("▼ #%d :: %2d$ :: [ %s]", id, minimumWeight, neighborsIDs.toString()).toString();
		}

		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (!(o instanceof Node)) return false;

			Node node = (Node) o;

			return id == node.id;
		}
	}
}