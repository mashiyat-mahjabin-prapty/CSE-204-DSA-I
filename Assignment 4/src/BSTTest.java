import java.util.Scanner;

public class BSTTest {
    public static void main(String[] args) {
        BinarySearchTree tree = new BinarySearchTree();
        Scanner scanner = new Scanner(System.in);

        while (true)
        {
            System.out.println("Welcome to menu:\nClick an option to continue or 0 to end");
            System.out.println("1. Insert Item\n2. Search Item\n3. Get In Order Successor\n" +
                    "4. Get In Order Predecessor\n5. Delete Item\n6. Get The Depth of an Item\n" +
                    "7. Get the Maximum Item\n8. Get the Minimum Item\n9. Get Height\n" +
                    "10. Print Inorder Traversal\n11. Print Preorder Traversal\n12. Print Postorder Traversal\n" +
                    "13. Get Size of the Tree\n0. End");
            int choice = scanner.nextInt();
            int element;
            switch (choice)
            {
                case 1:
                    System.out.println("Enter an integer to insert: ");
                    element = scanner.nextInt();
                    tree.insertItem(element);
                    System.out.println("Inserted element successfully!");
                    System.out.println();
                    break;
                case 2:
                    System.out.println("Enter the integer to search: ");
                    element = scanner.nextInt();
                    try
                    {
                        tree.searchItem(tree.getRoot(), element);
                    } catch (NullPointerException e)
                    {
                        e.printStackTrace();
                    }
                    System.out.println();
                    break;
                case 3:
                    System.out.println("Enter the element to find successor: ");
                    element = scanner.nextInt();
                    try
                    {
                        System.out.println(tree.getInorderSuccessor(element).getElement());
                    } catch (NullPointerException e)
                    {
                        System.out.println(Integer.MAX_VALUE);
                        System.out.println("No Successor found!");
                        //e.printStackTrace();
                    }
                    System.out.println();
                    break;
                case 4:
                    System.out.println("Enter the element to find predecessor: ");
                    element = scanner.nextInt();
                    try
                    {
                        System.out.println(tree.getInorderPredecessor(element).getElement());
                    } catch (NullPointerException e)
                    {
                        System.out.println(Integer.MIN_VALUE);
                        System.out.println("No predecessor found!");
                        //e.printStackTrace();
                    }
                    System.out.println();
                    break;
                case 5:
                    System.out.println("Enter the element to delete: ");
                    element = scanner.nextInt();
                    try {
                        tree.deleteItem(element);
                    } catch (NullPointerException e)
                    {
                        e.printStackTrace();
                    }
                    System.out.println();
                    break;
                case 6:
                    System.out.println("Enter the element to get depth: ");
                    element = scanner.nextInt();
                    try
                    {
                        System.out.println(tree.getItemDepth(element));
                    } catch (NullPointerException e)
                    {
                        e.printStackTrace();
                    }
                    System.out.println();
                    break;
                case 7:
                    System.out.println("The maximum element of the tree is: ");
                    System.out.println(tree.getMaxItem(tree.getRoot()).getElement());
                    System.out.println();
                    break;
                case 8:
                    System.out.println("The minimum element of the tree is: ");
                    System.out.println(tree.getMinItem(tree.getRoot()).getElement());
                    System.out.println();
                    break;
                case 9:
                    System.out.println("The height of the tree is: ");
                    System.out.println(tree.getHeight(tree.getRoot()));
                    System.out.println();
                    break;
                case 10:
                    System.out.println("Printing the tree in Inorder traversal:");
                    tree.printInOrder(tree.getRoot());
                    System.out.println();
                    break;
                case 11:
                    System.out.println("Printing the tree in Preorder traversal:");
                    tree.printPreOrder(tree.getRoot());
                    System.out.println();
                    break;
                case 12:
                    System.out.println("Printing the tree in Postorder traversal:");
                    tree.printPostOrder(tree.getRoot());
                    System.out.println();
                    break;
                case 13:
                    System.out.println("The number of nodes in the tree is: ");
                    System.out.println(tree.getSize(tree.getRoot()));
                    System.out.println();
                    break;
                case 0:
                    System.out.println("Thank you!");
                    System.out.println();
                    break;
                default:
                    System.out.println("Invalid input!\nTry again");
                    System.out.println();
                    break;
            }
            if (choice == 0)
            {
                break;
            }
        }
    }
}
