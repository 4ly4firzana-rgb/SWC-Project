
import java.util.*;
class ProductNode {
    int productID;
    ProductNode left, right;

    ProductNode(int id) {
        this.productID = id;
        this.left = null;
        this.right = null;
    }
}

// ---------------------------
// Warehouse Splay Tree Implementation
// ---------------------------
class WarehouseSplayTree {

    // Create a new warehouse product
    static ProductNode createProduct(int id) {
        return new ProductNode(id);
    }

    // Right rotation
    static ProductNode rightRotate(ProductNode x) {
        ProductNode y = x.left;
        x.left = y.right;
        y.right = x;
        return y;
    }

    // Left rotation
    static ProductNode leftRotate(ProductNode x) {
        ProductNode y = x.right;
        x.right = y.left;
        y.left = x;
        return y;
    }

    // Splay operation
    static ProductNode splay(ProductNode root, int id) {
        if (root == null || root.productID == id)
            return root;

        // Node lies in left subtree
        if (id < root.productID) {
            if (root.left == null)
                return root;

            // Zig-Zig (Left Left)
            if (id < root.left.productID) {
                root.left.left = splay(root.left.left, id);
                root = rightRotate(root);
            }
            // Zig-Zag (Left Right)
            else if (id > root.left.productID) {
                root.left.right = splay(root.left.right, id);
                if (root.left.right != null)
                    root.left = leftRotate(root.left);
            }

            return (root.left == null) ? root : rightRotate(root);
        }

        // Node lies in right subtree
        else {
            if (root.right == null)
                return root;

            // Zig-Zig (Right Right)
            if (id > root.right.productID) {
                root.right.right = splay(root.right.right, id);
                root = leftRotate(root);
            }
            // Zig-Zag (Right Left)
            else if (id < root.right.productID) {
                root.right.left = splay(root.right.left, id);
                if (root.right.left != null)
                    root.right = rightRotate(root.right);
            }

            return (root.right == null) ? root : leftRotate(root);
        }
    }

    // Insert product into warehouse system
    static ProductNode addProduct(ProductNode root, int id) {
        if (root == null)
            return createProduct(id);

        root = splay(root, id);

        if (root.productID == id)
            return root;

        ProductNode newNode = createProduct(id);

        if (id < root.productID) {
            newNode.right = root;
            newNode.left = root.left;
            root.left = null;
        } else {
            newNode.left = root;
            newNode.right = root.right;
            root.right = null;
        }

        return newNode;
    }

    // Worker accesses a product
    static ProductNode accessProduct(ProductNode root, int id) {
        System.out.println("\nWorker accessed product: " + id);
        return splay(root, id);
    }

    // Pre-order traversal to show product priority
    static void displayWarehouse(ProductNode root) {
        System.out.println("\nCurrent Warehouse Splay Tree (Preorder):");
        preorder(root);
    }

    static void preorder(ProductNode node) {
        if (node != null) {
            System.out.println("Product ID: " + node.productID);
            preorder(node.left);
            preorder(node.right);
        }
    }
}

public class WarehouseSplayDemo {
    public static void main(String[] args) {

        ProductNode warehouse = null;

        // Add products
        warehouse = WarehouseSplayTree.addProduct(warehouse, 50);
        warehouse = WarehouseSplayTree.addProduct(warehouse, 25);
        warehouse = WarehouseSplayTree.addProduct(warehouse, 15);
        warehouse = WarehouseSplayTree.addProduct(warehouse, 30);
        warehouse = WarehouseSplayTree.addProduct(warehouse, 60);

        WarehouseSplayTree.displayWarehouse(warehouse);

        // Workers access products
        warehouse = WarehouseSplayTree.accessProduct(warehouse, 60);
        WarehouseSplayTree.displayWarehouse(warehouse);

        warehouse = WarehouseSplayTree.accessProduct(warehouse, 30);
        WarehouseSplayTree.displayWarehouse(warehouse);

    }
}
