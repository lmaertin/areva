package areva.evaluationPerspective;

import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.zest.core.viewers.IGraphEntityContentProvider;

/**
 * class used for the evaluationGraph
 * @author Sven
 *
 */
public class ZestNodeContentProvider extends ArrayContentProvider  implements IGraphEntityContentProvider {

  @Override
  public Object[] getConnectedTo(Object entity) {
    if (entity instanceof MyNode) {
      MyNode node = (MyNode) entity;
      return node.getConnectedTo().toArray();
    }
    throw new RuntimeException("Type not supported");
  }
} 
