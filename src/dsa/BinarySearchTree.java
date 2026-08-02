package dsa;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.HashMap;

public class BinarySearchTree{

    public static class Node {
        int val;
        Node lftcld;
        Node rgtcld;

        Node(int val, Node lftchild, Node rgtchild) {
            this.val = val;
            this.lftcld = lftchild;
            this.rgtcld = rgtchild;
        }

        void display(){
            System.out.println("\n node data visit \n val : " + this.val + " \n lft " + lftcld + "\n rgt : " + rgtcld);
        }
    }

    Node root = null ;

    public BinarySearchTree(){
        root = null;
    }

    public void insert(int val) {
        root = insert(val, root);
    }

    Node insert(int val , Node root){
        if(root == null){
            System.out.println("node is inserted on binary search tree");
            return new Node(val , null , null);
        }
        else if(root.val < val){
            root.rgtcld = insert(val , root.rgtcld);

        }
        else {
            root.lftcld = insert(val, root.lftcld);
        }
        return root;
    }

     void inorderTraversal(Node root){
        if(root == null){
            return;
        }
//        root.display();
        inorderTraversal(root.lftcld);
        System.out.print(root.val + ",");
        inorderTraversal(root.rgtcld);
    }

    public void inorderTraversal(){
        System.out.println("\n InOrder Traverse ");
        inorderTraversal(this.root);
        System.out.println();
    }

    public void dfs(int target){
        System.out.println("\n DFS");

        List<Integer> list = new ArrayList<>();
        if(root == null){
            System.out.println("\n Empty Tree");
            return;
        }
        if(!DfsHelp(target , this.root , list)){
            System.out.println("\n Element not found");
        }
    }

    boolean DfsHelp(int target , Node root , List<Integer> list){
        if(root == null) {
            return false;
        }
        list.add(root.val);
        if(root.val == target){
            System.out.println(list);
            return true;
        }
        else if(root.val > target){
            return DfsHelp(target ,  root.lftcld , list);
        }
        else {
            return DfsHelp(target, root.rgtcld, list);
        }
    }

    public Node Dfs(int target){
        return DfsHelp( target ,root) ;
    }

     Node DfsHelp(int target , Node root){
         if(root == null) {
             return null;
         }

         if(root.val == target){
             return root;
         }
         else if(root.val > target){
             return DfsHelp(target ,  root.lftcld);
         }
         else {
             return DfsHelp(target, root.rgtcld);
         }
     }

     public void bfs(int target){

        System.out.println("\n BFS ");

        if(root == null){
            System.out.println("\n Empty Tree");
            return;
        }
        if(!BfsHelp(target)){
            System.out.println("\n Element not found");
        }

    }

    boolean BfsHelp(int target){
        if(root == null){
            return false;
        }

        List<Integer> list = new ArrayList<>();
        Queue<Node> queue = new LinkedList<>();
        Map<Node , Node> map = new HashMap<>();

        queue.add(root);
        map.put(root , null);
        while(!queue.isEmpty()){
            Node cur = queue.poll();
            list.add(cur.val);

            if(cur.val == target) {
                System.out.println("List of Visited Nodes");
                System.out.println(list);
                list.clear();
                System.out.println("Path");
                while(cur != null){
                    list.add(cur.val);
                    cur = map.get(cur);
                }
                for(int i = list.size()-1 ; i>-1 ; i--){
                    System.out.print(list.get(i) + ",");
                }
                System.out.println();
                return true;
            }
            if(cur.lftcld != null){
                queue.add(cur.lftcld);
                map.put(cur.lftcld , cur);
            }
            if(cur.rgtcld != null) {
                queue.add(cur.rgtcld);
                map.put(cur.rgtcld , cur);
            }
        }
        return false;
    }

    // deleting the target node using inorder successor
    public void delete(int target){
        Node targetNode = root;
        Node targetParentNode = null;
        while(targetNode != null && targetNode.val != target) {
            targetParentNode = targetNode;
            if (target > targetNode.val) {
                targetNode = targetNode.rgtcld;
            } else {
                targetNode = targetNode.lftcld;
            }
        }
        if(targetNode == null){
            System.out.println("Target Not Found\n");
            return;
        }
        // target with no child
        if(targetNode.lftcld == null && targetNode.rgtcld == null){
            System.out.println("target has no children");
            if(targetParentNode == null){
                root = null;
            }
            else if(targetParentNode.val > target){
                    targetParentNode.lftcld = null;
            }
            else{
                targetParentNode.rgtcld = null;
            }
        }
        // target with one child
        else if(targetNode.lftcld != null && targetNode.rgtcld == null){
            System.out.println("target has one left childs");
            if(targetParentNode == null){
                root = targetNode.lftcld;
            }
            else if(targetParentNode.val > target){
                targetParentNode.lftcld = targetNode.lftcld;
            }
            else{
                targetParentNode.rgtcld = targetNode.lftcld;
            }
        }
        else if(targetNode.lftcld == null && targetNode.rgtcld != null){
            System.out.println("target has one right childs");
            if(targetParentNode == null){
                root = targetNode.rgtcld;
            }
            else if(targetParentNode.val > target){
                targetParentNode.lftcld = targetNode.rgtcld;
            }
            else {
                targetParentNode.rgtcld = targetNode.rgtcld;
            }
        }
        // target with both childs
        else{
            System.out.println("target has two childs");
            // finding the inorder successor
            Node successor = targetNode.rgtcld;
            Node successorParentNode = targetNode;

            while(successor.lftcld != null){
                successorParentNode = successor;
                successor = successor.lftcld ;
            }
            // break successor from his parent
//            if(successorParentNode.val > successor.val){
//                successorParentNode.lftcld = null;
//            }
//            else{
//                successorParentNode.rgtcld = null;
//            }

            if(successorParentNode != targetNode){
                successorParentNode.lftcld = successor.rgtcld;
                successor.rgtcld = targetNode.rgtcld;
            }
            // update children of successor
            successor.lftcld = targetNode.lftcld;

            // replace target
            if(targetParentNode == null){
                root = successor;
            }
            else if(targetParentNode.val > target){
                targetParentNode.lftcld = successor;
            }
            else {
                targetParentNode.rgtcld = successor;
            }

        }
    }

}

