package areva.evaluationPerspective;

import java.util.ArrayList;
import java.util.List;

/**
 * Node in the EvaluationGraph
 * 
 * @author Sven
 * @version 1.0
 * 
 */
public class MyNode {
	private final String id;
	private final String name;
	private List<MyNode> connections;

	public MyNode(String id, String name) {
		this.id = id;
		this.name = name;
		this.connections = new ArrayList<MyNode>();
	}

	public String getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public List<MyNode> getConnectedTo() {
		return connections;
	}

}
