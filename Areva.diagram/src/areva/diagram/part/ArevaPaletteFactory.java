package areva.diagram.part;

import java.util.Collections;
import java.util.List;

import org.eclipse.gef.Tool;
import org.eclipse.gef.palette.PaletteContainer;
import org.eclipse.gef.palette.PaletteGroup;
import org.eclipse.gef.palette.PaletteRoot;
import org.eclipse.gef.palette.ToolEntry;
import org.eclipse.gmf.runtime.diagram.ui.tools.UnspecifiedTypeConnectionTool;
import org.eclipse.gmf.runtime.diagram.ui.tools.UnspecifiedTypeCreationTool;
import org.eclipse.gmf.runtime.emf.type.core.IElementType;

import areva.diagram.providers.ArevaElementTypes;

/**
 * @generated
 */
public class ArevaPaletteFactory {

	/**
	 * @generated
	 */
	public void fillPalette(PaletteRoot paletteRoot) {
		paletteRoot.add(createAreva1Group());
	}

	/**
	 * Creates "areva" palette tool group
	 * @generated
	 */
	private PaletteContainer createAreva1Group() {
		PaletteGroup paletteContainer = new PaletteGroup(
				Messages.Areva1Group_title);
		paletteContainer.setId("createAreva1Group"); //$NON-NLS-1$
		paletteContainer.add(createEvaluation1CreationTool());
		paletteContainer.add(createEvaluationCategories2CreationTool());
		paletteContainer.add(createCategory3CreationTool());
		paletteContainer.add(createElement4CreationTool());
		paletteContainer.add(createEvaluations5CreationTool());
		paletteContainer.add(createEvaluationsEvaluations6CreationTool());
		return paletteContainer;
	}

	/**
	 * @generated
	 */
	private ToolEntry createEvaluation1CreationTool() {
		NodeToolEntry entry = new NodeToolEntry(
				Messages.Evaluation1CreationTool_title,
				Messages.Evaluation1CreationTool_desc,
				Collections.singletonList(ArevaElementTypes.Evaluation_2001));
		entry.setId("createEvaluation1CreationTool"); //$NON-NLS-1$
		entry.setSmallIcon(ArevaElementTypes
				.getImageDescriptor(ArevaElementTypes.Evaluation_2001));
		entry.setLargeIcon(entry.getSmallIcon());
		return entry;
	}

	/**
	 * @generated
	 */
	private ToolEntry createEvaluationCategories2CreationTool() {
		LinkToolEntry entry = new LinkToolEntry(
				Messages.EvaluationCategories2CreationTool_title,
				Messages.EvaluationCategories2CreationTool_desc,
				Collections.singletonList(ArevaElementTypes.Category_4001));
		entry.setId("createEvaluationCategories2CreationTool"); //$NON-NLS-1$
		entry.setSmallIcon(ArevaElementTypes
				.getImageDescriptor(ArevaElementTypes.Category_4001));
		entry.setLargeIcon(entry.getSmallIcon());
		return entry;
	}

	/**
	 * @generated
	 */
	private ToolEntry createCategory3CreationTool() {
		ToolEntry entry = new ToolEntry(Messages.Category3CreationTool_title,
				Messages.Category3CreationTool_desc, null, null) {
		};
		entry.setId("createCategory3CreationTool"); //$NON-NLS-1$
		return entry;
	}

	/**
	 * @generated
	 */
	private ToolEntry createElement4CreationTool() {
		ToolEntry entry = new ToolEntry(Messages.Element4CreationTool_title,
				Messages.Element4CreationTool_desc, null, null) {
		};
		entry.setId("createElement4CreationTool"); //$NON-NLS-1$
		return entry;
	}

	/**
	 * @generated
	 */
	private ToolEntry createEvaluations5CreationTool() {
		ToolEntry entry = new ToolEntry(
				Messages.Evaluations5CreationTool_title,
				Messages.Evaluations5CreationTool_desc, null, null) {
		};
		entry.setId("createEvaluations5CreationTool"); //$NON-NLS-1$
		return entry;
	}

	/**
	 * @generated
	 */
	private ToolEntry createEvaluationsEvaluations6CreationTool() {
		ToolEntry entry = new ToolEntry(
				Messages.EvaluationsEvaluations6CreationTool_title,
				Messages.EvaluationsEvaluations6CreationTool_desc, null, null) {
		};
		entry.setId("createEvaluationsEvaluations6CreationTool"); //$NON-NLS-1$
		return entry;
	}

	/**
	 * @generated
	 */
	private static class NodeToolEntry extends ToolEntry {

		/**
		 * @generated
		 */
		private final List<IElementType> elementTypes;

		/**
		 * @generated
		 */
		private NodeToolEntry(String title, String description,
				List<IElementType> elementTypes) {
			super(title, description, null, null);
			this.elementTypes = elementTypes;
		}

		/**
		 * @generated
		 */
		public Tool createTool() {
			Tool tool = new UnspecifiedTypeCreationTool(elementTypes);
			tool.setProperties(getToolProperties());
			return tool;
		}
	}

	/**
	 * @generated
	 */
	private static class LinkToolEntry extends ToolEntry {

		/**
		 * @generated
		 */
		private final List<IElementType> relationshipTypes;

		/**
		 * @generated
		 */
		private LinkToolEntry(String title, String description,
				List<IElementType> relationshipTypes) {
			super(title, description, null, null);
			this.relationshipTypes = relationshipTypes;
		}

		/**
		 * @generated
		 */
		public Tool createTool() {
			Tool tool = new UnspecifiedTypeConnectionTool(relationshipTypes);
			tool.setProperties(getToolProperties());
			return tool;
		}
	}
}
