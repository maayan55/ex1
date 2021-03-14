package Ex1;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

public class WGraph_Algo implements weighted_graph_algorithms {

	private weighted_graph g;


	public void init(weighted_graph g) {
		this.g=g;

	}

	@Override
	public weighted_graph getGraph() {
		// TODO Auto-generated method stub
		return g;
	}

	@Override
	public weighted_graph copy() { //
		// TODO Auto-generated method stub
		return g;
	}

	private void traverse(node_info firstNode) {
		Queue<node_info> q = new LinkedList<>();
		firstNode.setTag(1);
		q.add(firstNode);
		while(!q.isEmpty()) {
			node_info currentNode = q.peek();
			q.remove();
			Collection<node_info> nodes = ((NodeInfo)currentNode).getNi();
			for (node_info node: nodes) {
				if (node.getTag() == 1) {
					continue;
				} else {
					node.setTag(1);
					q.add(node);
				}
			}
		}
	}


	@Override
	public boolean isConnected() {
		// TODO Auto-generated method stub
		Collection<node_info> nodes = g.getV();
		if (nodes.size() == 0) {
			return true;
		}
		for (node_info node: nodes) {
			node.setTag(0);
		}

		// start scanning from first node:
		traverse(nodes.iterator().next());

		for (node_info node: nodes) {
			if (node.getTag() == 0) {
				return false;
			}
		}

		return true;
	}

	@Override
	public double shortestPathDist(int src, int dest) {
		// TODO Auto-generated method stub
		List<node_info> sp = shortestPath(src, dest);
		return sp == null ? -1 : sp.size();
	}

	@Override
	public List<node_info> shortestPath(int src, int dest) {
		Map<Integer, Integer> pred = new HashMap<Integer, Integer>();
		Map<Integer, Integer> dist = new HashMap<Integer, Integer>();
		//Map<Integer, Boolean> visited = new HashMap<Integer, Boolean>();
		LinkedList<node_info> queue = new LinkedList<node_info>();

		Collection<node_info> nodes = g.getV();
		for (node_info node: nodes) {
			// visited.put(node.getKey(), false);
			node.setTag(0);
			dist.put(node.getKey(), Integer.MAX_VALUE);
			pred.put(node.getKey(), -1);
		}

		//visited.put(src, true);
		g.getNode(src).setTag(1);
		dist.put(src, 0);
		queue.add((node_info) g.getNode(src));

		boolean foundDest = false;

		while (!queue.isEmpty() && !foundDest) {
			node_info u = queue.remove();
			Collection<node_info> nis = ((NodeInfo) u).getNi();
			for (node_info ni : nis) {
				//if (visited.get(ni.getKey()) == false) {
				if (ni.getTag() == 0) {
					//visited.put(ni.getKey(), true);
					ni.setTag(1);
					dist.put(ni.getKey(), dist.get(u.getKey()) + 1);
					pred.put(ni.getKey(), u.getKey());
					queue.add(ni);

					if (ni.getKey() == dest) {
						foundDest = true;
						break;
					}
				}
			}
		}

		if (!foundDest) {
			return null;
		}

		List<node_info> path = new LinkedList<node_info>();
		int crawl = dest;
		path.add(g.getNode(dest));
		while (pred.get(crawl) != -1) {
			path.add(g.getNode(pred.get(crawl)));
			crawl = pred.get(crawl);
		}

		Collections.reverse(path);

		return path;
	}

	@Override
	public boolean save(String file) {
		// TODO Auto-generated method stub
		try {
			FileOutputStream fos = new FileOutputStream(file);
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(this.g);
			oos.close();
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	@Override
	public boolean load(String file) {
		ObjectInputStream ois = null;
		try {
			FileInputStream fin = new FileInputStream(file);
			ois = new ObjectInputStream(fin);
			this.g = (weighted_graph) ois.readObject();
			ois.close();
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

}
