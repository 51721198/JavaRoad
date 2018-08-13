package com.ld.alib;

import java.util.Random;

public class ListTest {


    public static void main(String[] args) {
        Random random = new Random();
        NodeList nodeList = new NodeList();
        int size = 10;


        for (int i = 1; i<size;i++){
            NodeList.Node node =  new NodeList.Node(random.nextInt(10));
            nodeList.addNode(node);
        }
        System.out.println("原始链表");
        NodeList.printList(nodeList);
        //删除重复节点:
        nodeList.deleteDuplicate();
        System.out.println("清除重复节点后");
        NodeList.printList(nodeList);

        NodeList.reverse(nodeList);
        System.out.println("链表全反转后");
        NodeList.printList(nodeList);

//        NodeList.Node delTar = new NodeList.Node(2);
//        nodeList.delNode(delTar);

//        nodeList.deleteDuplicate();
//
//        nodeList.printList();

//        NodeList nodeList2 = new NodeList();
//        nodeList2.addNode(new NodeList.Node(1));
//        nodeList2.addNode(new NodeList.Node(7));
//        nodeList2.addNode(new NodeList.Node(8));
//        nodeList2.addNode(new NodeList.Node(7));
//        nodeList2.printList();
//
//        nodeList2.deleteDuplicate();
//        nodeList2.printList();


    }


}
