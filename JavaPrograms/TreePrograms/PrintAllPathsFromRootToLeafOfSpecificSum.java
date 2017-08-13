package TreePrograms;


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
		TreeNode root = new TreeNode(5);
		TreeNode node1 = new TreeNode(1);
		TreeNode node2 = new TreeNode(7);
		TreeNode node3 = new TreeNode(-2);
		TreeNode node4 = new TreeNode(10);
		TreeNode node5 = new TreeNode(3);
		TreeNode node6 = new TreeNode(7);
		TreeNode node7 = new TreeNode(-3);
		TreeNode node8 = new TreeNode(4);
		TreeNode node9 = new TreeNode(-4);
		TreeNode node10 = new TreeNode(6);
		
		root.left = node1;
		root.right = node2;
		node1.left = node3;
		node2.right = node4;
		node3.left = node5;
		node5.left = node6;
		node6.right = node7;
		node1.right = node8;
		node5.right = node9;
		node2.left = node10;
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
	
	public TreeNode perform() {
		int sum = 0;
		preOrder(this.root, sum, "");
		return null;
	}
	
	public static void main(String args[]) {
		PrintAllPathsFromRootToLeaf obj = new PrintAllPathsFromRootToLeaf();
		obj.initializeInputs();
		obj.perform();
	}
}
