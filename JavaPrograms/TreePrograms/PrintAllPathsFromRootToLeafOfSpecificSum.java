package TreePrograms;

import java.util.Stack;


/*
 * @author: Harish
 * 
 * Print the paths from root to leaf which are having specific sum.
 * In this case 'k' is used to hold that sum. 
 */
public class PrintAllPathsFromRootToLeafOfSpecificSum {

	TreeNode root;
	int k;

	public void initializeInputs() {
		
		this.k = 22;
		TreeNode root = new TreeNode(15);
		TreeNode node1 = new TreeNode(10);
		TreeNode node2 = new TreeNode(3);
		TreeNode node3 = new TreeNode(7);
		TreeNode node4 = new TreeNode(18);
		TreeNode node5 = new TreeNode(25);
		TreeNode node6 = new TreeNode(20);
		
		root.left = node1;
		node1.left = node2;
		node2.right = node3;
		root.right = node4;
		node4.right = node5;
		node5.left = node6;
		this.root = root;
	}
	
	public void preOrder(TreeNode root, int sum, String path) {
		if (root == null) 
			return;
		if (root.left == null && root.right == null) {
			sum += root.value;
			if (sum == this.k)	
				System.out.println(sum + " " + path + ", " + root.value);
			return;
		}
		sum += root.value;
		 
		preOrder(root.left, sum, path + ", " + root.value);
		preOrder(root.right, sum, path + ", " + root.value);
	}
	
	
	public void iterativeInorder(TreeNode root) {
		Stack<TreeNode> st = new Stack<TreeNode>();
		boolean check = true;
		st.push(root);
		root = root.left;
		while (check) {
			if (st.isEmpty() && root == null)
				break;
			
			if (root != null) {
				st.push(root);
				root = root.left;
			} else {
				root = st.pop();
				System.out.print(root.value + " ");
				root = root.right;
			}
		}
	}
	
	public void iterativeReverseInorder(TreeNode root) {
		Stack<TreeNode> st = new Stack<TreeNode>();
		boolean check = true;
		st.push(root);
		root = root.right;
		while (check) {
			if (st.isEmpty() && root == null)
				break;
			
			if (root != null) {
				st.push(root);
				root = root.right;
			} else {
				root = st.pop();
				System.out.print(root.value + " ");
				root = root.left;
			}
		}
	}
	
	
	
	public void recursivePreorder(TreeNode root) {
		if (root == null)
			return;
		
		recursivePreorder(root.left);
		System.out.print(root.value + " ");
		recursivePreorder(root.right);
		
	}
	
	public TreeNode perform() {
		int sum = 0;
		preOrder(this.root, sum, "");
		return null;
	}
	
	public static void main(String args[]) {
		PrintAllPathsFromRootToLeafOfSpecificSum obj = new PrintAllPathsFromRootToLeafOfSpecificSum();
		obj.initializeInputs();
		obj.perform();
		
	}
}
