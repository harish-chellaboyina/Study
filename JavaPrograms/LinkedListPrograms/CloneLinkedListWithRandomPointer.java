package LinkedListPrograms;

import TreePrograms.Program;


/*
 * Linked List has another pointer pointing to some random node in the linked list
 * We have to clone the list along with the random pointers
 */
public class CloneLinkedListWithRandomPointer implements Program{
	
	LinkedListNodeWithRandomPointer head = null;

	public void initializeInputs() {
		LinkedListNodeWithRandomPointer node1 = new LinkedListNodeWithRandomPointer(10);
		LinkedListNodeWithRandomPointer node2 = new LinkedListNodeWithRandomPointer(63);
		LinkedListNodeWithRandomPointer node3 = new LinkedListNodeWithRandomPointer(92);
		LinkedListNodeWithRandomPointer node4 = new LinkedListNodeWithRandomPointer(65);
		LinkedListNodeWithRandomPointer node5 = new LinkedListNodeWithRandomPointer(31);
		LinkedListNodeWithRandomPointer node6 = new LinkedListNodeWithRandomPointer(7);
		LinkedListNodeWithRandomPointer node7 = new LinkedListNodeWithRandomPointer(3);
		LinkedListNodeWithRandomPointer node8 = new LinkedListNodeWithRandomPointer(65);
		LinkedListNodeWithRandomPointer node9 = new LinkedListNodeWithRandomPointer(14);
		LinkedListNodeWithRandomPointer node10 = new LinkedListNodeWithRandomPointer(13);
		
		node1.next = node2;
		node2.next = node3;
		node3.next = node4;
		node4.next = node5;
		node5.next = node6;
		node6.next = node7;
		node7.next = node8;
		node8.next = node9;
		node9.next = node10;
		
		node1.random = node6;
		node2.random = node7;
		node3.random = node2;
		node4.random = node9;
		node5.random = node1;
		node6.random = node4;
		node7.random = node8;
		node8.random = node10;
		node9.random = node3;
		node10.random = node2;
		this.head = node1;
		
	}
	
	public void printLinkedListWithRandomPointer(LinkedListNodeWithRandomPointer head) {
		LinkedListNodeWithRandomPointer temp = head;
		while (temp != null) {
			System.out.println(temp.value + " " + temp.random.value);
			temp = temp.next;
		}
	}
	
	public LinkedListNodeWithRandomPointer perform(LinkedListNodeWithRandomPointer head) {
		LinkedListNodeWithRandomPointer temp = head;
		LinkedListNodeWithRandomPointer temp1 = null;
		LinkedListNodeWithRandomPointer clonedListHead = null;
		
		while (temp != null) {
			LinkedListNodeWithRandomPointer clonedNode = new LinkedListNodeWithRandomPointer(temp.value);
			temp1 = temp.next;
			temp.next = clonedNode;
			clonedNode.next = temp1;
			temp = temp1;
		}
		
		temp = head;
		clonedListHead = head.next;
		
		while (temp != null) {
			temp.next.random = temp.random.next;
			temp1 = temp.next.next;
			
			if (temp1 != null)
				temp.next.next = temp1.next;
			else
				temp.next.next = null;

			temp.next = temp1;

			temp = temp.next;
		}
		
		return clonedListHead;
		
	}
	
	public static void main(String args[]) {
		CloneLinkedListWithRandomPointer obj = new CloneLinkedListWithRandomPointer();
		obj.initializeInputs();
		obj.head = obj.perform(obj.head);
		obj.printLinkedListWithRandomPointer(obj.head);
	}
	

}
