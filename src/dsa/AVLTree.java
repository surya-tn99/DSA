package dsa;

import java.util.*;

public class AVLTree{

    public static class AvlNode{
        int height , val ;
        AvlNode lftcld , rgtcld ;
        AvlNode(int val, AvlNode lftchild, AvlNode rgtchild , int height) {
            this.height = height;
            this.val = val;
            this.lftcld = lftchild;
            this.rgtcld = rgtchild;
        }

        void display(){
            System.out.println("\n node data visit \n val : " + this.val + " \n lft " + lftcld + "\n rgt : " + rgtcld);
        }
    }
    AvlNode root = null;

    public AVLTree(){

        this.root = null;
    }

    void inorderTraversal(AvlNode root){
        if(root == null){
            return;
        }
        inorderTraversal(root.lftcld);
//        System.out.print(root.val + ",");
        root.display();
        inorderTraversal(root.rgtcld);
    }
    public void inorderTraversal(){
        System.out.println("\n InOrder Traverse ");
        inorderTraversal(this.root);
        System.out.println();
    }
    boolean DfsHelp(int target , AvlNode root , List<Integer> list){
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
    boolean BfsHelp(int target , AvlNode root){
        if(root == null){
            return false;
        }

        List<Integer> list = new ArrayList<>();
        Queue<AvlNode> queue = new LinkedList<>();
        Map<AvlNode, AvlNode> map = new HashMap<>();

        queue.add(root);
        map.put(root , null);
        while(!queue.isEmpty()){
            AvlNode cur = queue.poll();
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
    public void bfs(int target){

        System.out.println("\n BFS ");

        if(root == null){
            System.out.println("\n Empty Tree");
            return;
        }
        if(!BfsHelp(target , this.root)){
            System.out.println("\n Element not found");
        }

    }


    int height(AvlNode root){
        if(root == null){
            return 0;
        }
        return root.height;
    }
    AvlNode leftRotate(AvlNode root){
        AvlNode rightnode = root.rgtcld;
        root.rgtcld = rightnode.lftcld;
        rightnode.lftcld = root;
        root.height = 1 + Math.max(height(root.lftcld) , height(root.rgtcld));
        rightnode.height = 1 + Math.max(height(rightnode.lftcld) , height(rightnode.rgtcld));
        return rightnode;
    }
    AvlNode rightRotate(AvlNode root){
        AvlNode leftnode = root.lftcld;
        root.lftcld = leftnode.rgtcld;
        leftnode.rgtcld = root;
        root.height = 1 + Math.max(height(root.lftcld) , height(root.rgtcld));
        leftnode.height = 1 + Math.max(height(leftnode.lftcld) , height(leftnode.rgtcld));
        return leftnode;
    }
    AvlNode insert(int val , AvlNode root){
        if(root == null){
            System.out.println("node is inserted on AVL tree");
            return new AvlNode(val , null , null , 1);
        }
        else if(root.val < val){
            root.rgtcld = insert(val , root.rgtcld);
            root.height = 1 + Math.max(height(root.lftcld) , height(root.rgtcld));
        }
        else {
            root.lftcld = insert(val, root.lftcld);
            root.height = 1 + Math.max(height(root.lftcld) , height(root.rgtcld));
        }
        int balance = height(root.lftcld) - height(root.rgtcld);
        if(balance > 1){
            // left tree

            if(val < root.lftcld.val){
                // left left case
                root = rightRotate(root);
            }
            else{
                // left right case
                root.lftcld = leftRotate(root.lftcld);
                root = rightRotate(root);
            }
        }
        else if(balance < -1){
            // right tree
            if(val < root.rgtcld.val){
                // right left case
                root.rgtcld = rightRotate(root.rgtcld);
                root = leftRotate(root);
            }
            else{
                // right right case
                root = leftRotate(root);
            }
        }
        else{
            // no balancing needed on tree
        }
        return root;
    }
    public void insert(int val) {
        this.root = insert(val, this.root);
    }

}
