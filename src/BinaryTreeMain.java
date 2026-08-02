import java.util.*;

class BinarySearchTree{

    private static class Node {
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

    BinarySearchTree(){
        root = null;
    }

    void insert(int val) {
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

    void inorderTraversal(){
        System.out.println("\n InOrder Traverse ");
        inorderTraversal(this.root);
        System.out.println();
    }

    void dfs(int target){
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

    void bfs(int target){

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
}

public class BinaryTreeMain {

    public static  void main(String[] args){
        Scanner sc = new Scanner(System.in);
        BinarySearchTree bst = new BinarySearchTree();
        byte choice ;
        // bst.insert(100);
        // bst.insert(150);
        // bst.insert(125);
        // bst.insert(175);
        // bst.insert(50);
        // bst.insert(25);
        // bst.insert(75);
        // bst.inorderTraversal();
        // bst.bfs(175);
        // bst.dfs(175);

       do{

           choice = 0;
           System.out.print("\n ()_() \n\n0.Exit\n1.Insert Node \n2.Display Node \n3.Depth First Search\n4.Breath First Search\nur Choice : ");
           choice = sc.nextByte();
           switch (choice){
               case 1 -> bst.insert(sc.nextInt());
               case 2 -> bst.inorderTraversal();
               case 3 -> bst.dfs(sc.nextInt());
               case 4 -> bst.bfs(sc.nextInt());
               default -> {
                   System.out.println("\n Bye Bye (^) + (^)");
                   choice = 0;
               }
           }
       }while(choice != 0 );
        sc.close();
        return;
    }
}

