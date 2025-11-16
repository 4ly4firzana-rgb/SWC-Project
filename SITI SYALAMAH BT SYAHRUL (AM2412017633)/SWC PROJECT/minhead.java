
/**
 * Write a description of class minhead here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class minhead
{
    static class MinHeap {
        private PriorityQueue<Integer> heap = new PriorityQueue<>();
        
        public void insert(int value){
            heap.add(value);
        }
        
         public int extractMin(){
             if (heap.isEmpty()){
                 throw new NoSuchElementException("Heap is empty");
             }
             return heap.poll();
         }
        public int size() {
            return heap.size();
        }
    }
    
    static class SplayTree {
        
        private Set<Integer> treeData = new HashSet<>();
        
        public void insert(int key){
            treeData.add(key);
        }
        
        public boolean search(int key) {
            
            if (treeData.contains(key)) {
                return true;
            }
            return false;
        }
        
        public String getKeys() {
            return treeData.toString();
        }
        
         public static void main(String[] args) {
              MinHeap heap = new MinHeap();
        heap.insert(10);
        heap.insert(3);
        heap.insert(15);
        System.out.println("Min-Heap Extract Min: " + heap.extractMin());
        
        SplayTree tree = new SplayTree();
        tree.insert(20);
        tree.insert(10);
        tree.insert(30);
        System.out.println("Splay Tree Search (10 found): " + tree.search(10));
    }
}
}