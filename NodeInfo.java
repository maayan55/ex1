package Ex1;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class NodeInfo implements node_info {


	private static int counter = 0;
	private int key;
	private Map<Integer, node_info> ni;
	private Map<Integer, Double> edgeWeight;
	private String info;
	private double tag;

	public NodeInfo() {
		this.key = counter++;
		this.ni = new HashMap<Integer, node_info>();
		this.edgeWeight = new HashMap<Integer, Double>();
	}
	
	public NodeInfo(int k) {
		this.key = k;
		this.ni = new HashMap<Integer, node_info>();
		this.edgeWeight = new HashMap<Integer, Double>();
	}

	public int getKey() {
		return key;
	}

	public Collection<node_info> getNi() {
		return new ArrayList<>(this.ni.values());
	}
	
	public Collection<Double> getWeights() {
		return new ArrayList<>(this.edgeWeight.values());
	}
	 public double getEdgeWeight (node_info n) {
		 if (!(ni.containsKey(n.getKey()))) return -1;
		 return this.edgeWeight.get(n.getKey());
	 }

	public boolean hasNi(int key) {
		return ni.containsKey(key);
	}

	public void addNi(node_info t) {
		ni.put(t.getKey(), t);
	}
	
	public void addEdge(node_info node, double w) {
		edgeWeight.put(node.getKey(), w);
	}

	public void removeNode(node_info node) {
		ni.remove(node.getKey());
		edgeWeight.remove(node.getKey());
	}

	public String getInfo() {
		return this.info;
	}

	public void setInfo(String s) {
		this.info = s;
	}

	public double getTag() {
		return this.tag;
	}

	public void setTag(double t) {
		this.tag=t;	
	}

}