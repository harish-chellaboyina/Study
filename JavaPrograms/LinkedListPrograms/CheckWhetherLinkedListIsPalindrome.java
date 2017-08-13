package LinkedListPrograms;

import ProgramInterfaces.LinkedListProgram;

public class CheckWhetherLinkedListIsPalindrome implements LinkedListProgram{

	public LinkedListNode createLinkedList() {
		int a[] = {1,2,3,3,2, 1};
		LinkedListNode node1 = new LinkedListNode(a[0]);
		LinkedListNode node2 = new LinkedListNode(a[1]);
		LinkedListNode node3 = new LinkedListNode(a[2]);
		LinkedListNode node4 = new LinkedListNode(a[3]);
		LinkedListNode node5 = new LinkedListNode(a[4]);
		LinkedListNode node6 = new LinkedListNode(a[5]);
		
		node1.next = node2;
		node2.next = node3;
		node3.next = node4;
		node4.next = node5;
		node5.next = node6;
		
		return node1;
	}
	
	public void printList(LinkedListNode head) {
		LinkedListNode temp = head;
		while (temp != null) {
			System.out.println(temp.value);
			temp = temp.next;
		}
	}
	
	public LinkedListNode reverseLinkedList(LinkedListNode head) {
		
		if (head == null || head.next == null)
			return head;
		
		LinkedListNode headNode = head;
		LinkedListNode lastNode = head;
		LinkedListNode temp = null;
		
		while (lastNode.next != null) {
			temp = lastNode.next;
			if (temp != null && temp.next != null)
				lastNode.next = temp.next;
			else
				lastNode.next = null;
			temp.next = headNode;
			headNode = temp;
		}
		return headNode;
	}
	
	public boolean check(LinkedListNode head) {
		//Reverse the second half of the list and compare
		
		if (head == null)
			return false;
			
		if (head.next == null)
			return true;
		
		LinkedListNode slowPtr = head;
		LinkedListNode fastPtr = head.next;
		
		while (true) {
			if (fastPtr == null || fastPtr.next == null)
				break;
			slowPtr = slowPtr.next;
			fastPtr = fastPtr.next.next;
		}
		
		slowPtr = slowPtr.next;
		slowPtr = this.reverseLinkedList(slowPtr);
		boolean isPalindrome = true;
		fastPtr = head;
		while (slowPtr != null) {
			if (fastPtr.value != slowPtr.value) {
				isPalindrome = false;
				break;
			}
			fastPtr = fastPtr.next;
			slowPtr = slowPtr.next;
		}
		return isPalindrome;

	}
	
	public static void main(String args[]) {
		CheckWhetherLinkedListIsPalindrome obj = new CheckWhetherLinkedListIsPalindrome();
		LinkedListNode head = obj.createLinkedList();
		System.out.println(obj.check(head));
	}
	
}
