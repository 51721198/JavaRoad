package com.soapsnake.algorithms.structures.list;

public class ListNode {
    public int val;
    public ListNode next;

//    public ListNode tail;

    public ListNode() {

    }

    public ListNode(int val) {
        this.val = val;
    }

    public static void printListNode(ListNode root) {
        if (root == null) {
            System.out.println("null list!!!!");
            return;
        }
        int size = 1;
        System.out.print(root.val + " , ");
        while (root.next != null) {
            root = root.next;
            size++;
            if (root.next == null) {
                System.out.print(root.val);
            } else {
                System.out.print(root.val + " , ");
            }
        }
        System.out.println("   ->ListSize is: " + size);
    }

    public static ListNode revertList(ListNode head) {

        return head;
    }

    public static void main(String[] args) {

//        node.next = new ListNode(2);
//
//        node.next.next = new ListNode(3);
//        printListNode(node);
//        System.out.println(" ");
//
//        printListNode(null);
//        ListNode node1 = new ListNode(2);
//        node.addNode(node1);
//        printListNode(node);

//        ListNode node2 = new ListNode();
//        node2.addNode(new ListNode(1));
//        node2.addNode(new ListNode(2));
//        node2.addNode(new ListNode(3));
//        node2.addNode(new ListNode(4));
//        ListNode.printListNode(node2.head);
//        ListNode.printListNode(ListNode.revertList(node2.head));
////        node2.deleteNode(new ListNode(2));
//        ListNode.printListNode(node2);
    }

    public static ListNode makeTestList() {
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(6);
        head.next.next.next = new ListNode(6);
        head.next.next.next.next = new ListNode(4);
        head.next.next.next.next.next = new ListNode(5);
        head.next.next.next.next.next.next = new ListNode(7);
        return head;
    }

    public static ListNode makeTestListFor725() {
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(4);
        head.next.next.next.next = new ListNode(5);
        head.next.next.next.next.next = new ListNode(6);
        head.next.next.next.next.next.next = new ListNode(7);
        head.next.next.next.next.next.next.next = new ListNode(8);
        return head;
    }

    public static ListNode makeTestListFor82() {
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(3);
        head.next.next.next.next = new ListNode(4);
        head.next.next.next.next.next = new ListNode(4);
        head.next.next.next.next.next.next = new ListNode(5);
        head.next.next.next.next.next.next.next = new ListNode(8);
        return head;
    }

    public static ListNode makeTestListFor61() {
        ListNode head = new ListNode(0);
        head.next = new ListNode(1);
        head.next.next = new ListNode(2);
        head.next.next.next = new ListNode(3);
        head.next.next.next.next = new ListNode(4);
        return head;
    }

    public static ListNode makeTestListFor234() {
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(2);
        head.next.next.next.next = new ListNode(1);
        return head;
    }

    public static ListNode makeTestListFor147() {
        ListNode head = new ListNode(-1);
        head.next = new ListNode(5);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(4);
        head.next.next.next.next = new ListNode(0);
        return head;
    }

    public static ListNode makeTestListFor86() {
        ListNode head = new ListNode(1);
        head.next = new ListNode(4);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(2);
        head.next.next.next.next = new ListNode(5);
        head.next.next.next.next.next = new ListNode(2);
        return head;
    }


    public void addNode(ListNode node) {
//        if (node == null) {
//            return;
//        }
//        if (this.head == null) {
//            this.head = node;
//        }
//        if (this.tail == null) {
//            this.tail = node;
//        }
//
//        this.tail.next = node;
//        this.tail = node;
        if (node == null) {
            throw new NullPointerException("节点不能位null");
        }
//        if (this.tail != null) {
//            this.tail.next = node;
//        }
//        this.tail = node;
    }

    //
    @Override
    public String toString() {
        return "ListNode{" +
                "val=" + val +
                ", next=" + next +
                '}';
    }

    //删除目标节点,入参为目标节点
//    public void deleteNode(ListNode node) {
//        ListNode list = this.head;  //list现在指向该链表,避免使用this指针
//        while (list != null) {
//            //如果要删除的是头节点
//            if (node.equals(list)) {
//                list.head = list.next;
//            }
//            //如果下一个节点就是要删除的节点
//            if (node.equals(list.next)) {
//                if (list.next.next != null) {
//                    list.next = list.next.next;
//                } else {
//                    //下一个节点就是尾节点
//                    list.next = null;
//                    list.tail = list;
//                }
//            }
//            list = list.next;
//        }
//    }


    @Override
    public boolean equals(Object o) {
        if (!(o instanceof ListNode)) {
            return false;
        }
        return this.val == ((ListNode) o).val;
    }
}
