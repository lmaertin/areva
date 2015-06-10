package areva.diagram.navigator;

import org.eclipse.gmf.runtime.common.ui.services.parser.IParser;
import org.eclipse.gmf.runtime.common.ui.services.parser.ParserOptions;
import org.eclipse.gmf.runtime.emf.core.util.EObjectAdapter;
import org.eclipse.gmf.runtime.emf.type.core.IElementType;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.resource.ImageRegistry;
import org.eclipse.jface.viewers.ITreePathLabelProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.TreePath;
import org.eclipse.jface.viewers.ViewerLabel;
import org.eclipse.swt.graphics.Image;
import org.eclipse.ui.IMemento;
import org.eclipse.ui.navigator.ICommonContentExtensionSite;
import org.eclipse.ui.navigator.ICommonLabelProvider;

import areva.diagram.edit.parts.EvaluationEditPart;
import areva.diagram.edit.parts.EvaluationNameEditPart;
import areva.diagram.edit.parts.EvaluationsEditPart;
import areva.diagram.part.ArevaDiagramEditorPlugin;
import areva.diagram.part.ArevaVisualIDRegistry;
import areva.diagram.providers.ArevaElementTypes;
import areva.diagram.providers.ArevaParserProvider;

/**
 * @generated
 */
public class ArevaNavigatorLabelProvider extends LabelProvider implements
		ICommonLabelProvider, ITreePathLabelProvider {

	/**
	 * @generated
	 */
	static {
		ArevaDiagramEditorPlugin
				.getInstance()
				.getImageRegistry()
				.put("Navigator?UnknownElement", ImageDescriptor.getMissingImageDescriptor()); //$NON-NLS-1$
		ArevaDiagramEditorPlugin
				.getInstance()
				.getImageRegistry()
				.put("Navigator?ImageNotFound", ImageDescriptor.getMissingImageDescriptor()); //$NON-NLS-1$
	}

	/**
	 * @generated
	 */
	public void updateLabel(ViewerLabel label, TreePath elementPath) {
		Object element = elementPath.getLastSegment();
		if (element instanceof ArevaNavigatorItem
				&& !isOwnView(((ArevaNavigatorItem) element).getView())) {
			return;
		}
		label.setText(getText(element));
		label.setImage(getImage(element));
	}

	/**
	 * @generated
	 */
	public Image getImage(Object element) {
		if (element instanceof ArevaNavigatorGroup) {
			ArevaNavigatorGroup group = (ArevaNavigatorGroup) element;
			return ArevaDiagramEditorPlugin.getInstance().getBundledImage(
					group.getIcon());
		}

		if (element instanceof ArevaNavigatorItem) {
			ArevaNavigatorItem navigatorItem = (ArevaNavigatorItem) element;
			if (!isOwnView(navigatorItem.getView())) {
				return super.getImage(element);
			}
			return getImage(navigatorItem.getView());
		}

		return super.getImage(element);
	}

	/**
	 * @generated
	 */
	public Image getImage(View view) {
		switch (ArevaVisualIDRegistry.getVisualID(view)) {
		case EvaluationsEditPart.VISUAL_ID:
			return getImage(
					"Navigator?Diagram?http:///tubs/ips/areva.ecore?Evaluations", ArevaElementTypes.Evaluations_1000); //$NON-NLS-1$
		case EvaluationEditPart.VISUAL_ID:
			return getImage(
					"Navigator?TopLevelNode?http:///tubs/ips/areva.ecore?Evaluation", ArevaElementTypes.Evaluation_2001); //$NON-NLS-1$
		}
		return getImage("Navigator?UnknownElement", null); //$NON-NLS-1$
	}

	/**
	 * @generated
	 */
	private Image getImage(String key, IElementType elementType) {
		ImageRegistry imageRegistry = ArevaDiagramEditorPlugin.getInstance()
				.getImageRegistry();
		Image image = imageRegistry.get(key);
		if (image == null && elementType != null
				&& ArevaElementTypes.isKnownElementType(elementType)) {
			image = ArevaElementTypes.getImage(elementType);
			imageRegistry.put(key, image);
		}

		if (image == null) {
			image = imageRegistry.get("Navigator?ImageNotFound"); //$NON-NLS-1$
			imageRegistry.put(key, image);
		}
		return image;
	}

	/**
	 * @generated
	 */
	public String getText(Object element) {
		if (element instanceof ArevaNavigatorGroup) {
			ArevaNavigatorGroup group = (ArevaNavigatorGroup) element;
			return group.getGroupName();
		}

		if (element instanceof ArevaNavigatorItem) {
			ArevaNavigatorItem navigatorItem = (ArevaNavigatorItem) element;
			if (!isOwnView(navigatorItem.getView())) {
				return null;
			}
			return getText(navigatorItem.getView());
		}

		return super.getText(element);
	}

	/**
	 * @generated
	 */
	public String getText(View view) {
		if (view.getElement() != null && view.getElement().eIsProxy()) {
			return getUnresolvedDomainElementProxyText(view);
		}
		switch (ArevaVisualIDRegistry.getVisualID(view)) {
		case EvaluationsEditPart.VISUAL_ID:
			return getEvaluations_1000Text(view);
		case EvaluationEditPart.VISUAL_ID:
			return getEvaluation_2001Text(view);
		}
		return getUnknownElementText(view);
	}

	/**
	 * @generated
	 */
	private String getEvaluations_1000Text(View view) {
		return ""; //$NON-NLS-1$
	}

	/**
	 * @generated
	 */
	private String getEvaluation_2001Text(View view) {
		IParser parser = ArevaParserProvider
				.getParser(ArevaElementTypes.Evaluation_2001,
						view.getElement() != null ? view.getElement() : view,
						ArevaVisualIDRegistry
								.getType(EvaluationNameEditPart.VISUAL_ID));
		if (parser != null) {
			return parser.getPrintString(new EObjectAdapter(
					view.getElement() != null ? view.getElement() : view),
					ParserOptions.NONE.intValue());
		} else {
			ArevaDiagramEditorPlugin.getInstance().logError(
					"Parser was not found for label " + 5001); //$NON-NLS-1$
			return ""; //$NON-NLS-1$
		}
	}

	/**
	 * @generated
	 */
	private String getUnknownElementText(View view) {
		return "<UnknownElement Visual_ID = " + view.getType() + ">"; //$NON-NLS-1$  //$NON-NLS-2$
	}

	/**
	 * @generated
	 */
	private String getUnresolvedDomainElementProxyText(View view) {
		return "<Unresolved domain element Visual_ID = " + view.getType() + ">"; //$NON-NLS-1$  //$NON-NLS-2$
	}

	/**
	 * @generated
	 */
	public void init(ICommonContentExtensionSite aConfig) {
	}

	/**
	 * @generated
	 */
	public void restoreState(IMemento aMemento) {
	}

	/**
	 * @generated
	 */
	public void saveState(IMemento aMemento) {
	}

	/**
	 * @generated
	 */
	public String getDescription(Object anElement) {
		return null;
	}

	/**
	 * @generated
	 */
	private boolean isOwnView(View view) {
		return EvaluationsEditPart.MODEL_ID.equals(ArevaVisualIDRegistry
				.getModelID(view));
	}

}
