package TreePrograms;


/*
 * Print all paths from root to leaf along with sum
 */
public class PrintAllPathsFromRootToLeaf implements Program{
	
	TreeNode root;
	int minLevel = 1000, maxLevel = -1;
	

	public void initializeInputs() {
		TreeNode root = new TreeNode(0);
		TreeNode node1 = new TreeNode(1);
		TreeNode node2 = new TreeNode(2);
		TreeNode node3 = new TreeNode(3);
		TreeNode node4 = new TreeNode(4);
		TreeNode node5 = new TreeNode(5);
		TreeNode node6 = new TreeNode(6);
		TreeNode node7 = new TreeNode(7);
		TreeNode node8 = new TreeNode(8);
		TreeNode node9 = new TreeNode(9);
		TreeNode node10 = new TreeNode(0);
		TreeNode node11 = new TreeNode(1);
		TreeNode node12 = new TreeNode(2);
		
		root.left = node1;
		root.right = node2;
		node1.left = node3;
		node2.right = node4;
		node3.left = node5;
		node3.right = node6;
		node2.left = node7;
		node7.right = node8;
		node1.right = node9;
		node9.right = node10;
		node9.left = node11;
		node7.left = node12;
		
				
		this.root = root;
	}
	
	public void levelOrder(TreeNode root, int height) {
		if (root == null)
			return;
		if (height == 1) {
			System.out.print(root.value + " ");
		} else {
			System.out.print(root.value + " ");
			levelOrder(root.left, height - 1);
			levelOrder(root.right, height - 1);
		}
	}
	
	public void preOrder(TreeNode root, int sum, String path) {
		if (root == null) 
			return;
		if (root.left == null && root.right == null) {
			sum += root.value;
			System.out.println(sum + " " + path + ", " + root.value);
			return;
		}
		sum += root.value;
		 
		preOrder(root.left, sum, path + ", " + root.value);
		preOrder(root.right, sum, path + ", " + root.value);
	}
	
	public void findTrunk(final TreeNode root, int index) {
		if (root == null)
			return;
		if (index < this.minLevel)
			this.minLevel = index;
		if (index > this.maxLevel)
			this.maxLevel = index;
		findTrunk(root.left, index - 1);
		findTrunk(root.right, index + 1);
	}
	
	public int findHeight(TreeNode root, int height) {
		if (root == null)
			return height;
		
		return Math.max(this.findHeight(root.left, height + 1), this.findHeight(root.right, height + 1));
	}
	
	public void printChars(char character, int frequency) {
		for (int i = 0;i < frequency;i++)
			System.out.print(character);
	}
	
	public void printTree(TreeNode root, int height) {
		
		int size = (int) (Math.pow(2, height) - 1);
		
		TreeNode[] queue = new TreeNode[size];
		
		int startIndex = 0, endIndex = 1, levelCount = 0, nextLineBreakPoint = 1, spacesCount = size + 1, trailingSpaces = spacesCount /2, temp = trailingSpaces;
		queue[0] = root;
		
		
		this.printChars(' ', trailingSpaces);
		
		while (startIndex < size) {
			TreeNode presentElement = queue[startIndex++];
			if (presentElement != null)
				System.out.print(presentElement.value);
			else
				System.out.print(" ");
			
			this.printChars(' ', spacesCount - 1);
			
			if (endIndex < size) {
				if (presentElement == null){
					queue[endIndex ++] = null;
					queue[endIndex ++] = null;
				} else {
					queue[endIndex++] = presentElement.left;
					queue[endIndex++] = presentElement.right;
				}
			}
			if (startIndex == nextLineBreakPoint) {
				System.out.println("");
				nextLineBreakPoint = ((nextLineBreakPoint + 1 ) * 2) - 1;
				int temp1 = spacesCount; 
				spacesCount /= 2;
				temp = trailingSpaces/ 2;
				levelCount++;
				
				for (int i = 1;i <= temp; i++) {
					
					this.printChars(' ', trailingSpaces - 1);
					trailingSpaces --;
					
					for (int k = 0;k < Math.pow(2, levelCount - 1); k++) {
						
						if (queue[startIndex + (2*k)] != null)
							System.out.print("/");
						else
							System.out.print(" ");
						
						this.printChars(' ', i * 2 - 1);
						if (queue[startIndex + (2*k) + 1] != null)
							System.out.print("\\");
						else
							System.out.print(" ");
						this.printChars(' ', temp1 -3);
						
						
					}
					temp1 -= 2;
					
					System.out.println();
					
				}
				this.printChars(' ', trailingSpaces);
				
			}
			
		}
	
	}
	
	public void printTree(TreeNode root) {
		int height = this.findHeight(root, 0);
		this.printTree(root, height);
			
	}
	
	public TreeNode perform() {
		int sum = 0;
		this.printTree(root);
		//System.out.println(this.minLevel + " " + this.maxLevel);
		return null;
	}
	
	public static void main(String args[]) {
		PrintAllPathsFromRootToLeaf obj = new PrintAllPathsFromRootToLeaf();
		obj.initializeInputs();
		obj.perform();
	}
}
