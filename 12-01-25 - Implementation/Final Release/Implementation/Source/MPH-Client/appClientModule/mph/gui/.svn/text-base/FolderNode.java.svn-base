package mph.gui;

import javax.swing.tree.DefaultMutableTreeNode;

/**
 * Class used to display a folder icon for professor and course nodes in the JTree even if they haven't got children.<br/>
 * It extends {@link DefaultMutableTreeNode}.
 */
public class FolderNode extends DefaultMutableTreeNode {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * See {@link DefaultMutableTreeNode#DefaultMutableTreeNode(Object)}
	 */
	public FolderNode(String theString) {
		super(theString);
	}
	
	/**
	 * See {@link DefaultMutableTreeNode#DefaultMutableTreeNode(Object)}
	 */
	public FolderNode(TreeEntity theTreeEntity) {
		super(theTreeEntity);
	}

	@Override
	public boolean isLeaf() {
		/* This object will show with the folder icon because it is never considered a leaf of the tree */
		return false;
	}

	@Override
	public boolean getAllowsChildren() {
		/* Professor and Course nodes can always have children */
		return true;
	}
}
