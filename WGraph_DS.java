package Ex1;


import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;


public class WGraph_DS implements weighted_graph {

	private int mc;
	private int edgesCount;
	private Map<Integer, node_info> nodes;

	public WGraph_DS() {
		this.mc = 0;
		this.edgesCount = 0;
		this.nodes = new HashMap<Integer, node_info>();
	}
	@Override
	public node_info getNode(int key) {
		if (nodes.containsKey(key)) {
			return nodes.get(key);
		}
		return null;
	}

	@Override
	public boolean hasEdge(int node1, int node2) {
		return nodes.containsKey(node1) && nodes.containsKey(node2) && (((NodeInfo) nodes.get(node1)).hasNi(node2) || ((NodeInfo) nodes.get(node2)).hasNi(node1));
	}

	@Override
	public double getEdge(int node1, int node2) {
		if (node1 == node2) {
			return -1;
		}
		return ((NodeInfo) nodes.get(node1)).getEdgeWeight(nodes.get(node2));


	}

	@Override
	public void addNode(int key) {
		// TODO Auto-generated method stub
		mc++;
		node_info n= new NodeInfo(key);
		this.nodes.put(key ,n);

	}

	@Override
	public void connect(int node1, int node2, double w) { 
		// TODO Auto-generated method stub
		((NodeInfo)this.nodes.get(node1)).addEdge(nodes.get(node2), w);
		

	}

	@Override
	public Collection<node_info> getV() {
		// TODO Auto-generated method stub
		return new ArrayList<>(this.nodes.values());
	}

	@Override
	public Collection<node_info> getV(int node_id) {
		// TODO Auto-generated method stub
		return ((NodeInfo) nodes.get(node_id)).getNi();
	}

	@Override
	public node_info removeNode(int key) {
		// TODO Auto-generated method stub
		if (nodes.containsKey(key)) {
			mc++;
			node_info node = nodes.get(key);		
			nodes.remove(key);
			for (Entry<Integer, node_info> entry : nodes.entrySet()) {
				if (((NodeInfo) entry.getValue()).hasNi(key)) {
					edgesCount--;
					((NodeInfo) entry.getValue()).removeNode(node);
				}
			}
		}
		return null;
	}

	@Override
	public void removeEdge(int node1, int node2) {
		// TODO Auto-generated method stub
		if (node1 == node2) {
			return;
		}
		if (nodes.containsKey(node1) && nodes.containsKey(node2)) {
			node_info nodeInfo1 = (node_info) nodes.get(node1);
			node_info nodeInfo2 = nodes.get(node2);
			if (((NodeInfo) nodeInfo1).hasNi(node2)) {
				mc++;
				edgesCount--;
				((NodeInfo) nodeInfo1).removeNode(nodeInfo2);
			}
			if (((NodeInfo) nodeInfo2).hasNi(node1)) {
				((NodeInfo) nodeInfo2).removeNode(nodeInfo1);
			}
		}
	}

	@Override
	public int nodeSize() {
		// TODO Auto-generated method stub
		return nodes.size();
	}

	@Override
	public int edgeSize() {
		// TODO Auto-generated method stub
		return this.edgesCount;
	}

	@Override
	public int getMC() {
		return this.mc;
	}

}
