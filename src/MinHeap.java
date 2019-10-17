public class MinHeap {
    private int[] Heap;
    private int size;
    private int maxsize;
    private int k;

    private static final int FRONT = 0;

    public MinHeap(int maxsize, int k) {
        this.maxsize = maxsize;
        this.size = 0;
        Heap = new int[this.maxsize];
        this.k = k;
    }

    private int parent(int pos) {
        return pos/k;
    }


    private boolean isLeaf(int pos) {
        if (pos >= (size/k) && pos <= size) {
            return true;
        }
        return false;
    }

    //swaps 2 nodes of the heap
    private void swap(int fpos, int spos) {
        int temp;
        temp = Heap[fpos];
        Heap[fpos] = Heap[spos];
        Heap[spos] = temp;
    }

    private void minHeapify(int pos) {
        //if node is nonleaf & greater than
        //any of its children
        int[] child = new int[k];
        int minChild;
        int minChildIndex = -1; //says otherwise it might not have been initialized

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

    public void insert(int x) {
        if (size >= maxsize) {
            return;
        }
        Heap[size] = x;
        int curr = size;
        size++;
//        System.out.print(size);
        minHeapify(size--);
        while (Heap[curr] < Heap[parent(curr)]) {
            swap(curr, parent(curr));
            curr = parent(curr);
       }
    }

    public int extractMin() {
        int popped = Heap[FRONT];
//        System.out.print(size+" "+FRONT);
        Heap[FRONT] = Heap[--size];  //keep getting error here; size is staying at 1
        size--;
        minHeapify(FRONT);
        return popped;
    }

}