import java.util.Scanner;

import dsa.AVLTree;
import dsa.BinarySearchTree;

public class Main {

    public static  void main(String[] args){
        Scanner sc = new Scanner(System.in);
//        BinarySearchTree bst = new BinarySearchTree();
        AVLTree bst = new AVLTree();
        byte choice ;
//        bst.insert(100);
//        bst.insert(150);
//        bst.insert(125);
//        bst.insert(175);
//        bst.insert(50);
//        bst.insert(25);
//        bst.insert(75);
//        bst.inorderTraversal();
        // bst.bfs(175);
        // bst.dfs(175);
//        bst.delete(150);

        bst.inorderTraversal();
        

        do{

            choice = 0;
            System.out.print("\n ()_() \n\n0.Exit\n1.Insert Node \n2.Display Node \n3.Depth First Search\n4.Breath First Search\n5.Delete\nur Choice : ");
            choice = sc.nextByte();
            switch (choice){
                case 1 -> bst.insert(sc.nextInt());
                case 2 -> bst.inorderTraversal();
                case 3 -> bst.dfs(sc.nextInt());
                case 4 -> bst.bfs(sc.nextInt());
//                case 5 -> bst.delete(sc.nextInt());
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
