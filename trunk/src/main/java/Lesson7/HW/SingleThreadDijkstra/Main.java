package Lesson7.HW.SingleThreadDijkstra;

/**
 * Created with IntelliJ IDEA.
 * User: andrew
 * Date: 4/15/14
 * Time: 3:53 PM
 */

import java.util.*;

/**
 * Распараллелить поиск минимального пути во взвешенном графе на n-потоков.
 * И показать эффективность вашего алгоритма (по времени) на
 * многопроцессорной системе.
 *
 * Однопоточное решение!
 */

public class Main {

	static Dijkstra.Node n1 = new Dijkstra.Node(1), n2 = new Dijkstra.Node(2), n3 = new Dijkstra.Node(3),
			n4 = new Dijkstra.Node(4), n5 = new Dijkstra.Node(5), n6 = new Dijkstra.Node(6);

	public static PriorityQueue<Dijkstra.Node> initialize(){
		PriorityQueue<Dijkstra.Node> result = new PriorityQueue<>();

		n1.becomeNeighbors(n2, 7); n1.becomeNeighbors(n3, 9); n1.becomeNeighbors(n6, 14);
		n2.becomeNeighbors(n3, 10); n2.becomeNeighbors(n4, 15); n3.becomeNeighbors(n4, 11);
		n3.becomeNeighbors(n6, 2); n4.becomeNeighbors(n5, 6); n6.becomeNeighbors(n5, 9);

		result.add(n1); result.add(n2); result.add(n3);
		result.add(n4); result.add(n5); result.add(n6);

		return result;
	}

	public static void main(String[] args) {

		Dijkstra.calculate(n1);
		List<Dijkstra.Node> path = Dijkstra.getShortestPathTo(n6);

		for (Dijkstra.Node each : path){
//			System.out.println("#" + each.getId() + " (" + each.getMinimumWeight() + ") ");
			System.out.println(each);
		}
	}
}

