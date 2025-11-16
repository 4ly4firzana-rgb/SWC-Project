public class SplayTree {
    private Node root;

    static class Node {
        int value;
        Node left, right;

        public Node(int value) {
            this.value = value;
            left = right = null;
        }
    }

    public void insert(int value) {
        root = insert(root, value);
        splay(value);
    }

    private Node insert(Node node, int value) {
        if (node == null) {
            return new Node(value);
        }
        if (value < node.value) {
            node.left = insert(node.left, value);
        } else {
            node.right = insert(node.right, value);
        }
        return node;
    }

    private void splay(int value) {
        root = splay(root, value);
    }

    private Node splay(Node root, int value) {
        if (root == null || root.value == value) {
            return root;
        }

        if (root.value > value) {
            if (root.left == null) return root;

            if (root.left.value > value) {
                root.left = splay(root.left, value);
                root = rotateRight(root);
            } else if (root.left.value < value) {
                root.left = rotateLeft(root.left);
                root = rotateRight(root);
            }
        } else { // Zag and Zag-Zig cases
            if (root.right == null) return root;

            if (root.right.value < value) {
                root.right = splay(root.right, value);
                root = rotateLeft(root);
            } else if (root.right.value > value) {
                root.right = rotateRight(root.right);
                root = rotateLeft(root);
            }
        }
        return root;
    }

    private Node rotateRight(Node node) {
        Node left = node.left;
        node.left = left.right;
        left.right = node;
        return left;
    }

    private Node rotateLeft(Node node) {
        Node right = node.right;
        node.right = right.left;
        right.left = node;
        return right;
    }

    public boolean search(int value) {
        root = splay(root, value);
        return root != null && root.value == value;
    }

    public static void main(String[] args) {
        SplayTree tree = new SplayTree();
        tree.insert(10);  // Shipping route 10 (RM 100)
        tree.insert(20);  // Shipping route 20 (RM 200)
        tree.insert(5);   // Shipping route 5 (RM 50)
        tree.insert(30);  // Shipping route 30 (RM 300)

        // Search for route 10 (frequently accessed)
        System.out.println("Search 10: " + tree.search(10)); // True, 10 is now at the root
        System.out.println("Search 40: " + tree.search(40)); // False, route not in the tree
    }
}
