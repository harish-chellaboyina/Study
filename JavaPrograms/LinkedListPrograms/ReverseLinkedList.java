package LinkedListPrograms;

public class ReverseLinkedList {
	public LinkedListNode createLinkedList() {
		int a[] = {1,2,3,4,5,6};
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
	
	public static void main(String args[]) {
		ReverseLinkedList obj = new ReverseLinkedList();
		LinkedListNode head = obj.createLinkedList();
		head = obj.reverseLinkedList(head);
		obj.printList(head);
	}
}
