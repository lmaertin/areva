package areva.evaluationPerspective;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import areva.impl.CategoryImpl;
import areva.impl.ElementImpl;
import areva.impl.EvaluationImpl;

/**
 * Provides the Nodes and Connections for the EvaulationGraph
 * 
 * @author Sven
 * @version 1.0
 */
public class NodeModelContentProvider {
	private List<MyConnection> connections;
	private List<MyNode> nodes;
	//numeration/id for the nodes
	int counter = 0;
	//numeration/id for the connections 
	int counterCon = 0;

	public NodeModelContentProvider() {
		nodes = new ArrayList<MyNode>();
		connections = new ArrayList<MyConnection>();
	}

	/**
	 * adds an Element to the Graph (Element is leef)
	 * 
	 * @param e
	 * @param number
	 * @param parent
	 */
	public void addElement(ElementImpl e, int number, int parent) {
		String s = "";
		s += e.getName() + "\n";
		s += "\n";
		s += "Percentage: " + e.getPercentage() + " %\n";
		s += "Must Have: " + e.getMustHave() + " %\n";
		s += "\n";
		s += "Rating: " + e.getRating() + " " + e.getUnit() + "\n";
		s += "Quality Rating: " + this.format(e.getQuality()) + " %\n";
		MyNode tmp = new MyNode("" + number, s);
		nodes.add(tmp);

		connections.add(new MyConnection("" + counterCon, "", getNodeByName(""
				+ parent), tmp));
		counter++;
		counterCon++;

	}

	/**
	 * Search for a node by name
	 * 
	 * @param name
	 * @return
	 */
	private MyNode getNodeByName(String name) {
		MyNode node = null;
		for (int i = 0; i < nodes.size(); i++) {
			node = nodes.get(i);
			if (node.getId().equals(name)) {
				break;
			}
		}
		return node;
	}

	/**
	 * adds an Category to the Graph
	 * 
	 * @param c
	 * @param number
	 * @param parent
	 */
	public void addCategory(CategoryImpl c, int number, int parent) {
		String s = "";
		s += c.getName() + "\n";
		s += "\n";
		s += "Percentage: " + c.getPercentage() + " %\n";
		s += "Must Have: " + c.getMustHave() + " %\n";
		s += "\n";
		s += "Quality: " + this.format(c.getRating());
		MyNode tmp = new MyNode("" + number, s);
		nodes.add(tmp);
		connections.add(new MyConnection("" + counterCon, "", getNodeByName(""
				+ parent), tmp));
		counter++;
		counterCon++;
	}

	/**
	 * adds an evaluation to the graph
	 * 
	 * @param e
	 * @param number
	 * @param parent
	 */
	public void addEvaluation(EvaluationImpl e, int number, int parent) {
		String s = "";
		s += e.getName() + "\n";
		s += "\n";
		s += "Quality: " + this.format(e.getRating());
		nodes.add(new MyNode("" + number, s));
		if (parent != -1) {
			connections.add(new MyConnection("" + counterCon, "", nodes
					.get(parent), nodes.get(number)));
			counterCon++;
		}
		counter++;
	}

	/**
	 * adds the node with title evaluations to the graph
	 */
	public void addEvaluations() {
		nodes.add(new MyNode("", "Evaluations"));
		counter++;
	}

	/**
	 * see name
	 */
	public void deleteAll() {
		nodes.removeAll(nodes);
		connections.removeAll(connections);
		counter = 0;
		counterCon = 0;
	}

	/**
	 * Use connections list to connect all nodes
	 */
	public void connectNodes() {
		for (MyConnection connection : connections) {
			connection.getSource().getConnectedTo()
					.add(connection.getDestination());
		}
	}

	public List<MyNode> getNodes() {
		return nodes;
	}

	public String format(double i) {
		DecimalFormat f = new DecimalFormat("#0.00");
		double toFormat = ((double) Math.round(i * 100)) / 100;
		return f.format(toFormat);
	}
}