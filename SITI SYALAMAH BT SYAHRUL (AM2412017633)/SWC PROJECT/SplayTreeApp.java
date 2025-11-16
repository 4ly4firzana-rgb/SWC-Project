
/**
 * Write a description of class SplayTree here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
// ---SYALAMAH---
class Driver {
    String name;
    double rating; // Example: driver rating out of 5

    public Driver(String name, double rating) {
        this.name = name;
        this.rating = rating;
    }

    @Override
    public String toString() {
        return name + " (" + rating + ")";
    }
}

// Splay Tree Node
class SplayNode {
    Driver driver;
    SplayNode left, right;

    public SplayNode(Driver driver) {
        this.driver = driver;
        left = right = null;
    }
}

// Splay Tree
class SplayTree {
    private SplayNode root;

    // Right rotation
    private SplayNode rightRotate(SplayNode x) {
        SplayNode y = x.left;
        x.left = y.right;
        y.right = x;
        return y;
    }

    // Left rotation
    private SplayNode leftRotate(SplayNode x) {
        SplayNode y = x.right;
        x.right = y.left;
        y.left = x;
        return y;
    }

    // Splay operation: brings driver with rating key to root
    private SplayNode splay(SplayNode root, double key) {
        if (root == null || root.driver.rating == key)
            return root;

        // Key in left subtree
        if (key < root.driver.rating) {
            if (root.left == null) return root;

            // Zig-Zig (Left Left)
            if (key < root.left.driver.rating) {
                root.left.left = splay(root.left.left, key);
                root = rightRotate(root);
            } 
            // Zig-Zag (Left Right)
            else if (key > root.left.driver.rating) {
                root.left.right = splay(root.left.right, key);
                if (root.left.right != null) root.left = leftRotate(root.left);
            }

            return (root.left == null) ? root : rightRotate(root);
        } 
        else { // Key in right subtree
            if (root.right == null) return root;

            // Zag-Zig (Right Left)
            if (key < root.right.driver.rating) {
                root.right.left = splay(root.right.left, key);
                if (root.right.left != null) root.right = rightRotate(root.right);
            } 
            // Zag-Zag (Right Right)
            else if (key > root.right.driver.rating) {
                root.right.right = splay(root.right.right, key);
                root = leftRotate(root);
            }

            return (root.right == null) ? root : leftRotate(root);
        }
    }

    // Insert driver into tree
    public void insert(Driver driver) {
        if (root == null) {
            root = new SplayNode(driver);
            return;
        }
        root = splay(root, driver.rating);

        if (driver.rating == root.driver.rating) return; // duplicate

        SplayNode newNode = new SplayNode(driver);
        if (driver.rating < root.driver.rating) {
            newNode.right = root;
            newNode.left = root.left;
            root.left = null;
        } else {
            newNode.left = root;
            newNode.right = root.right;
            root.right = null;
        }
        root = newNode;
    }

    // Search driver by rating
    public Driver search(double rating) {
        root = splay(root, rating);
        if (root != null && root.driver.rating == rating) {
            return root.driver;
        }
        return null;
    }

    // Print tree inorder
    public void inorder(SplayNode node) {
        if (node != null) {
            inorder(node.left);
            System.out.println(node.driver);
            inorder(node.right);
        }
    }

    public void printInorder() {
        inorder(root);
    }
}

// Test Splay Tree
public class SplayTreeApp {
    public static void main(String[] args) {
        SplayTree tree = new SplayTree();

        tree.insert(new Driver("Aqil", 4.9));
        tree.insert(new Driver("Siti", 4.5));
        tree.insert(new Driver("Asyiqin", 4.2));
        tree.insert(new Driver("Wei Ming", 4.8));

        System.out.println("Inorder traversal of drivers:");
        tree.printInorder();

        System.out.println("\nSearching for driver with rating 4.8:");
        Driver found = tree.search(4.8);
        if (found != null) System.out.println("Driver found: " + found);
        else System.out.println("Driver not found.");
    }
}
