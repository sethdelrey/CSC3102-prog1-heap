/*
Raquel Hodgeson (rhodg14@lsu.edu, 89-399-5177)
Seth Richard (sric111@lsu.edu, 89-053-2395)
Programming Project 1
CSC 3102 - Dr. Shah
10/18/19
 */

public class MinHeap {
    private int[] Heap;     //array representation of the minHeap
    private int size;       //number of current elements in the heap
    private int maxsize;    //maximum number of elements the heap can hold
    private int k;          //maximum number of children each parent element can hold

    //constructor for k-ary MinHeap with given maxsize
    //and given k
    public MinHeap(int maxsize, int k) {
        this.maxsize = maxsize;
        this.size = 0;
        Heap = new int[this.maxsize];
        this.k = k;
    }

    //returns the parent element of the element in the given index pos
    private int parent(int pos) {
        return pos/k;
    }

    //returns true if the element at index pos has no children
    private boolean isLeaf(int pos) {
        if (pos >= (size/k) && pos <= size) {
            return true;
        }
        return false;
    }

    //swaps 2 nodes of the heap at the indices fpos and spos
    private void swap(int fpos, int spos) {
        int temp;
        temp = Heap[fpos];
        Heap[fpos] = Heap[spos];
        Heap[spos] = temp;
    }

    //Method to restore the heap property of the nodes
    //of the subtree rooted at index pos
    private void minHeapify(int pos) {
        //if node is nonleaf & greater than
        //any of its children
        int[] child = new int[k];
        int minChild;
        int minChildIndex = -1;

        while (true) {
            for (int i = 1; i < k + 1; i++) {
                if (((k * pos + i) < size))
                    child[i - 1] = k * pos + i;
                else
                    child[i - 1] = Integer.MAX_VALUE;
            }

            minChild = Integer.MAX_VALUE;

            for (int i = 0; i < k; i++) {
                if (child[i] != Integer.MAX_VALUE && Heap[child[i]] < minChild) {
                    minChildIndex = child[i];
                    minChild = Heap[child[i]];
                }
            }
            //leaf node
            if (minChild == Integer.MAX_VALUE)
                break;

            //swap only if key of minChildIndex
            //is less than key of node
            if (Heap[pos] > Heap[minChildIndex])
                swap(pos, minChildIndex);

            pos = minChildIndex;
        }
    }

    //Method to insert a value in the heap
    //Parameter x is the key of the element
    public void insert(int x) {
        if (size >= maxsize) {
            return;
        }
        Heap[size] = x;
        int curr = size;
        size++;
        while (Heap[curr] < Heap[parent(curr)]) {
            swap(curr, parent(curr));
            curr = parent(curr);
       }
    }

    //Method that returns the key of the minimum value
    //of the heap and restores the heap property
    //of the remaining nodes
    public int extractMin() {
        int popped = Heap[0];
        Heap[0] = Heap[--size];
        minHeapify(0);
        return popped;
    }
}