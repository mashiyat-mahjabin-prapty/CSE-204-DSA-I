public class BinarySearchTree
{
    protected static class Node<E>
    {
        private final E element;
        private Node<E> left;
        private Node<E> right;
        private Node<E> parent;
        public Node (E e, Node<E> par)
        {
            element = e;
            left = null;
            right = null;
            parent = par;
        }
        public E getElement()
        {
            return element;
        }

        public Node<E> getLeft() {
            return left;
        }

        public Node<E> getRight() {
            return right;
        }

        public Node<E> getParent() { return parent; }

        public void setLeft (Node<E> l)
        {
            left = l;
            if (l != null) l.parent = this;
        }
        public void setRight (Node<E> r)
        {
            right = r;
            if (r != null) r.parent = this;
        }

        public void setParent (Node<E> p)
        {
            parent = p;
        }
    }

    private static int nodeCount = 0;
    private Node<Integer> root;


    public BinarySearchTree ()
    {
        root = new Node<>(null, null);
    }

    public static int getNodeCount()
    {
        return nodeCount;
    }
    public Node<Integer> getRoot()
    {
        return root;
    }
    public Node<Integer> searchItem (Node<Integer> node, int key) throws NullPointerException
    {
        if (root == null)
        {
            return null;
        }
        while (node != null && key != node.getElement())
        {
            if (key < node.getElement())
            {
                node = node.getLeft();
            }
            else
            {
                node = node.getRight();
            }
        }
        if (node == null)
        {
            System.out.println(key + " not found!");
            System.out.println();
        }
        else
        {
            System.out.println(key + " found!");
            System.out.println();
        }
        return node;
    }

    private void transfer (Node<Integer> u, Node<Integer> v)
    {
        if (u.getParent() == null)
        {
            root = v;
        }
        else if (u == u.getParent().getLeft())
        {
            u.getParent().setLeft(v);
        }
        else
        {
            u.getParent().setRight(v);
        }
        if (v != null)
        {
            v.setParent(u.getParent());
        }
    }

    public void deleteItem (int element) throws NullPointerException
    {
        Node<Integer> node = searchItem(root, element);
        if (root == null)
        {
            System.out.println("Empty tree");
            System.out.println();
        }
        else if (node == null)
        {
            System.out.println("No such element found!");
            System.out.println();
        }
        else if (node.getRight() == null && node.getLeft() == null)
        {
            int p = node.getParent().getElement();
            if (p > node.getElement())
            {
                node.getParent().setLeft(null);
                System.out.println("Deletion successful!");
                System.out.println();
            }
            else
            {
                node.getParent().setRight(null);
                System.out.println("Deletion successful!");
                System.out.println();
            }
        }
        else if (node.getLeft() == null)
        {
            transfer (node, node.getRight());
            System.out.println("Deletion successful!");
            System.out.println();
        }
        else if (node.getRight() == null)
        {
            transfer (node, node.getLeft());
            System.out.println("Deletion successful!");
            System.out.println();
        }
        else
        {
            Node<Integer> y = getMinItem(node.getRight());
            if (y.getParent() != node)
            {
                transfer(y, y.getRight());
                y.getRight().setParent(y);
            }
            transfer(node, y);
            y.setLeft(node.getLeft());
            y.getLeft().setParent(y);
            System.out.println("Deletion successful!");
            System.out.println();
            nodeCount--;
        }
    }

    public void insertItem (int element)
    {
        Node<Integer> temp = new Node<>(element, null);
        if (root.getElement() == null)
        {
            root = temp;
        }
        else
        {
            Node<Integer> current = root;
            while (current != null)
            {
                if (current.getElement() > element)
                {
                    if (current.getLeft() != null)
                        current = current.getLeft();
                    else
                        break;
                }
                else
                {
                    if (current.getRight() != null)
                        current = current.getRight();
                    else
                        break;
                }
            }
            temp.setParent(current);
            if (temp.getElement() < current.getElement())
            {
                current.setLeft(temp);
            }
            else
            {
                current.setRight(temp);
            }
        }
        nodeCount++;
    }

    public Node<Integer> getMinItem (Node<Integer> current)
    {
        while (current.getLeft() != null)
        {
            current = current.getLeft();
        }
        return current;
    }

    public Node<Integer> getMaxItem (Node<Integer> current)
    {
        while (current.getRight() != null)
        {
            current = current.getRight();
        }
        return current;
    }

    public Node<Integer> getInorderSuccessor(int element) throws NullPointerException
    {
        Node<Integer> node = searchItem(root, element);
        if (node == null) return null;
        if (node.getRight() != null)
        {
            return getMinItem(node.getRight());
        }
        Node<Integer> temp = node.getParent();
        while (temp != null && node == temp.getRight())
        {
            node = temp;
            temp = temp.getParent();
        }
        return temp;
    }

    public Node<Integer> getInorderPredecessor(int element) throws NullPointerException
    {
        Node<Integer> node = searchItem(root, element);
        if (node == null) return null;
        if (node.getLeft() != null)
        {
            return getMaxItem(node.getLeft());
        }
        Node<Integer> temp = node.getParent();
        while (temp != null && node == temp.getLeft())
        {
            node = temp;
            temp = temp.getParent();
        }
        return temp;
    }

    public int getItemDepth(int element)
    {

        Node<Integer> current = root;
        int depth = 0;
        while (current.getElement() != element)
        {
            if (current.getElement() > element)
            {
                current = current.getLeft();
            }
            else
            {
                current = current.getRight();
            }
            depth++;

            if (current == null)
            {
                return -1;
            }
        }
        return depth;
    }

    public int getHeight (Node<Integer> node)
    {
        if (node.getElement() == null)
        {
            return -1;
        }
        else
        {
            BinarySearchTree Left = new BinarySearchTree();
            BinarySearchTree Right = new BinarySearchTree();

            if (node.getLeft() != null)
            {
                Left.root = node.getLeft();
            }
            if (node.getRight() != null)
            {
                Right.root = node.getRight();
            }
            int a = Left.getHeight(Left.getRoot());
            int b = Right.getHeight(Right.getRoot());
            if (a > b)
            {
                return a+1;
            }
            else
            {
                return b+1;
            }
        }
    }

    public int getSize (Node<Integer> node)
    {
        int a=0, b=0;
        if (node.getLeft() != null)
        {
            a = getSize(node.getLeft());
        }
        if (node.getRight() != null)
        {
            b = getSize(node.getRight());
        }
        return a+b+1;
    }

    public void printInOrder (Node<Integer> node)
    {
        if (node != null)
        {
            printInOrder(node.getLeft());
            System.out.println(node.getElement());
            printInOrder(node.getRight());
        }
    }

    public void printPreOrder (Node<Integer> node)
    {
        if (node != null)
        {
            System.out.println(node.getElement());
            printPreOrder(node.getLeft());
            printPreOrder(node.getRight());
        }
    }

    public void printPostOrder (Node<Integer> node)
    {
        if (node != null)
        {
            printPostOrder(node.getLeft());
            printPostOrder(node.getRight());
            System.out.println(node.getElement());
        }
    }
}
