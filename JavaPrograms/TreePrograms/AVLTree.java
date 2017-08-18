package TreePrograms;

public class AVLTree {
	class Node {
		Node right;
		Node left;
		int height;
		int data;
		
		Node(int data) {
			this.data = data;
		}
	}
	
    private Node leftRotate(Node root){
        Node newRoot = root.right;
        root.right = root.right.left;
        newRoot.left = root;
        root.height = setHeight(root);
        newRoot.height = setHeight(newRoot);
        return newRoot;
    }
    
    private Node rightRotate(Node root){
        Node newRoot = root.left;
        root.left = root.left.right;
        newRoot.right = root;
        root.height = setHeight(root);
        newRoot.height = setHeight(newRoot);
        return newRoot;
    }

    private int setHeight(Node root){
        if(root == null){
            return 0;
        }
        return 1 + Math.max((root.left != null ? root.left.height : 0), (root.right != null ? root.right.height : 0));
    }
    
    private int height(Node root){
        if(root == null){
            return 0;
        }else {
            return root.height;
        }
    }
    
    public Node insert(Node root, int data){
        if(root == null){
            return new Node(data);
        }
        if(root.data <= data){
            root.right = insert(root.right,data);
        }
        else{
            root.left = insert(root.left,data);
        }
        int balance = balance(root.left, root.right);
        if(balance > 1){
            if(height(root.left.left) >= height(root.left.right)){
                root = rightRotate(root);
            }else{
                root.left = leftRotate(root.left);
                root = rightRotate(root);
            }
        }else if(balance < -1){
            if(height(root.right.right) >= height(root.right.left)){
                root = leftRotate(root);
            }else{
                root.right = rightRotate(root.right);
                root = leftRotate(root);
            }
        }
        else{
            root.height = setHeight(root);
        }
        return root;
    }
    
    private int balance(Node rootLeft, Node rootRight){
        return height(rootLeft) - height(rootRight);
    }
    
    public static void main(String args[]){
        AVLTree avlTree = new AVLTree();
        Node root = null;
        root = avlTree.insert(root, -10);
        root = avlTree.insert(root, 2);
        root = avlTree.insert(root, 13);
        root = avlTree.insert(root, -13);
        root = avlTree.insert(root, -15);
        root = avlTree.insert(root, 15);
        root = avlTree.insert(root, 17);
        root = avlTree.insert(root, 20);
        
        avlTree.printInorder(root);
        
//        root = avlTree.insert(root, 5);
//        root = avlTree.insert(root, 2);
//        root = avlTree.insert(root, 1);
//        root = avlTree.insert(root, 6);
//        root = avlTree.insert(root, 9);
//        root = avlTree.insert(root, 7);
//        root = avlTree.insert(root, 3);
//        root = avlTree.insert(root, 8);
//        avlTree.printTree(root);
    }
    
    private void printInorder(Node root) {
    	if (root == null)
    		return;
    	printInorder(root.left);
    	System.out.print(root.data + " ");
    	printInorder(root.right);
    }
    
    public void printChars(char character, int frequency) {
		for (int i = 0;i < frequency;i++)
			System.out.print(character);
	}
    
    public void printTreeAsTree(Node root) {
		
		int height = root.height;
		int size = (int) (Math.pow(2, height) - 1);
		
		Node[] queue = new Node[size];
		
		int startIndex = 0, endIndex = 1, levelCount = 0, nextLineBreakPoint = 1, spacesCount = size + 1, trailingSpaces = spacesCount /2, temp = trailingSpaces;
		queue[0] = root;
		
		this.printChars(' ', trailingSpaces);
		
		while (startIndex < size) {
			Node presentElement = queue[startIndex++];
			if (presentElement != null)
				System.out.print(presentElement.data);
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
}