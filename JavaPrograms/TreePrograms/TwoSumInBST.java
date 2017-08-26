package TreePrograms;

import java.util.Stack;

public class TwoSumInBST {
	
	public TreeNode initializeInputs() {
		
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
		return root;
	}
	
	public boolean find(TreeNode root, int sum) {
		
		Stack<TreeNode> st1 = new Stack<TreeNode>();
		Stack<TreeNode> st2 = new Stack<TreeNode>();
		boolean check1 = true;
		boolean check2 = true;
		boolean last = true;
		TreeNode curr1 = root;
		TreeNode curr2 = root;
		st1.push(curr1);
		st2.push(curr2);
		int a = 0,b = 0;
		
		curr1 = curr1.left;
		curr2 = curr2.right;
		
		
		while (last) {
			while(check1) {
				if (st1.isEmpty() && curr1 == null){
					last = false;
					break;
				}
				
				if (curr1 != null) {
					st1.push(curr1);
					curr1 = curr1.left;
				} else {
					curr1 = st1.pop();
					a = curr1.value;
					curr1 = curr1.right;
					check1 = false;
				}
			}
			
			while(check2) {
				if (st2.isEmpty() && curr2 == null) {
					last = false;
					break;
				}
				
				if (curr2 != null) {
					st2.push(curr2);
					curr2 = curr2.right;
				} else {
					curr2 = st2.pop();
					b = curr2.value;
					curr2 = curr2.left;
					check2 = false;
				}
			}
			
			if (a + b == sum)
				return true;
			else if(a + b > sum) {
				check2 = true;
			} else {
				check1 = true;
			}
		}
		return false;
	}
	
	public static void main(String[] args) {
		TwoSumInBST tsb = new TwoSumInBST();
		TreeNode root = tsb.initializeInputs();
		System.out.println(tsb.find(root, 38));
	}

}
