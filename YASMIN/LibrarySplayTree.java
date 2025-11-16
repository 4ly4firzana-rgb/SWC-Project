public class LibrarySplayTree {

    // ------------------------------
    // BOOK CLASS
    // ------------------------------
    static class Book {
        String bookID;
        String title;
        String author;
        int quantity;

        public Book(String bookID, String title, String author, int quantity) {
            this.bookID = bookID;
            this.title = title;
            this.author = author;
            this.quantity = quantity;
        }

        @Override
        public String toString() {
            return "[" + bookID + " - " + title + " by " + author + " | Quantity: " + quantity + "]";
        }
    }

    // ------------------------------
    // NODE CLASS
    // ------------------------------
    static class Node {
        Book book;
        Node left, right;

        Node(Book book) {
            this.book = book;
        }
    }

    // ------------------------------
    // SPLAY TREE CLASS
    // ------------------------------
    static class SplayTree {
        private Node root;

        // Right rotation
        private Node rightRotate(Node x) {
            Node y = x.left;
            x.left = y.right;
            y.right = x;
            return y;
        }

        // Left rotation
        private Node leftRotate(Node x) {
            Node y = x.right;
            x.right = y.left;
            y.left = x;
            return y;
        }

        // Splay operation
        private Node splay(Node root, int quantity) {
            if (root == null || root.book.quantity == quantity)
                return root;

            // Key lies in left subtree
            if (quantity < root.book.quantity) {
                if (root.left == null) return root;

                // Zig-Zig (Left Left)
                if (quantity < root.left.book.quantity) {
                    root.left.left = splay(root.left.left, quantity);
                    root = rightRotate(root);
                }
                // Zig-Zag (Left Right)
                else if (quantity > root.left.book.quantity) {
                    root.left.right = splay(root.left.right, quantity);
                    if (root.left.right != null)
                        root.left = leftRotate(root.left);
                }

                return (root.left == null) ? root : rightRotate(root);
            }
            // Key lies in right subtree
            else {
                if (root.right == null) return root;

                // Zag-Zig (Right Left)
                if (quantity < root.right.book.quantity) {
                    root.right.left = splay(root.right.left, quantity);
                    if (root.right.left != null)
                        root.right = rightRotate(root.right);
                }
                // Zag-Zag (Right Right)
                else if (quantity > root.right.book.quantity) {
                    root.right.right = splay(root.right.right, quantity);
                    root = leftRotate(root);
                }

                return (root.right == null) ? root : leftRotate(root);
            }
        }

        // Insert a book
        public void insert(Book book) {
            if (root == null) {
                root = new Node(book);
                return;
            }

            root = splay(root, book.quantity);

            if (root.book.quantity == book.quantity) return; // duplicate quantities not allowed

            Node newNode = new Node(book);

            if (book.quantity < root.book.quantity) {
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

        // Search by quantity
        public Book search(int quantity) {
            root = splay(root, quantity);
            if (root != null && root.book.quantity == quantity)
                return root.book;
            return null;
        }

        // In-order traversal
        public void inOrder(Node node) {
            if (node != null) {
                inOrder(node.left);
                System.out.println(node.book);
                inOrder(node.right);
            }
        }

        public void display() {
            System.out.println("Library Splay Tree (In-order by quantity):");
            inOrder(root);
        }
    }

    // ------------------------------
    // MAIN METHOD
    // ------------------------------
    public static void main(String[] args) {
        SplayTree library = new SplayTree();

        library.insert(new Book("B001", "Data Structures", "ALI", 5));
        library.insert(new Book("B002", "Algorithms", "ISYA", 2));
        library.insert(new Book("B003", "Java Programming", "ADAM", 7));
        library.insert(new Book("B004", "Database Systems", "SARAH", 3));

        library.display();

        System.out.println("\nSearching for book with quantity 3:");
        System.out.println(library.search(3));

        System.out.println("\nSplay Tree after search (3 is now root):");
        library.display();
    }
}
