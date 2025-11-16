class SplayTree {
    class Node {
        int key;
        Node left, right;
        Node(int key) { this.key = key; }
    }

    private Node root;

    private Node rightRotate(Node x) {
        Node y = x.left;
        x.left = y.right;
        y.right = x;
        return y;
    }

    private Node leftRotate(Node x) {
        Node y = x.right;
        x.right = y.left;
        y.left = x;
        return y;
    }

    private Node splay(Node r, int key) {
        if (r == null || r.key == key) return r;
        if (key < r.key) {
            if (r.left == null) return r;
            if (key < r.left.key) {
                r.left.left = splay(r.left.left, key);
                r = rightRotate(r);
            } else {
                r.left.right = splay(r.left.right, key);
                if (r.left.right != null) r.left = leftRotate(r.left);
            }
            return (r.left == null) ? r : rightRotate(r);
        } else {
            if (r.right == null) return r;
            if (key > r.right.key) {
                r.right.right = splay(r.right.right, key);
                r = leftRotate(r);
            } else {
                r.right.left = splay(r.right.left, key);
                if (r.right.left != null) r.right = rightRotate(r.right);
            }
            return (r.right == null) ? r : leftRotate(r);
        }
    }

    public void insert(int key) {
        root = splay(root, key);
        if (root != null && root.key == key) return;
        Node newNode = new Node(key);
        if (root == null) {
            root = newNode;
        } else if (key < root.key) {
            newNode.right = root;
            newNode.left = root.left;
            root.left = null;
            root = newNode;
        } else {
            newNode.left = root;
            newNode.right = root.right;
            root.right = null;
            root = newNode;
        }
        System.out.println("Inserted item code: " + key);
    }

    public boolean search(int key) {
        root = splay(root, key);
        boolean found = (root != null && root.key == key);
        System.out.println("Search for item " + key + ": " + found);
        return found;
    }
}

public class NewSplayTree {
    public static void main(String[] args) {
        SplayTree inventory = new SplayTree();
        inventory.insert(10); // Tent
        inventory.insert(30); // Medicine
        inventory.insert(20); // Bottled water
        inventory.search(30); // Medicine accessed again
        inventory.search(20); // Bottled water accessed again
    }
}
