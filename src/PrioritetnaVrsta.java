
public class PrioritetnaVrsta<T extends Comparable> implements Seznam<T> {
    private Object[] heap;
    private int end = 0;

    public PrioritetnaVrsta() {
        this(100);
    }

    public PrioritetnaVrsta(int maxSize) {
        heap = new Object[maxSize];
    }

    private void swap(int a, int b) {
        Object tmp = heap[a];
        heap[a] = heap[b];
        heap[b] = tmp;
    }

    private void bubbleUp() {
        int eltIdx = end - 1;
        while (eltIdx >= 0) {
            T elt = (T) heap[eltIdx];
            int childIdx = eltIdx * 2 + 1;
            if (childIdx < end) {
                T child = (T) heap[childIdx];
                if (childIdx + 1 < end && child.compareTo(heap[childIdx + 1]) < 0)
                    child = (T) heap[++childIdx];
                if (elt.compareTo(child) >= 0)
                    return;
                swap(eltIdx, childIdx);
            }
            eltIdx = eltIdx % 2 == 1 ? (eltIdx - 1) / 2 : (eltIdx - 2) / 2;
        }
    }

    private void bubbleDown(int start) {
        int eltIdx = start;
        int childIdx = eltIdx * 2 + 1;
        while (childIdx < end) {
            T elt = (T) heap[eltIdx];
            T child = (T) heap[childIdx];
            if (childIdx + 1 < end && child.compareTo(heap[childIdx + 1]) < 0)
                child = (T) heap[++childIdx];
            if (elt.compareTo(child) >= 0)
                return;
            swap(eltIdx, childIdx);
            eltIdx = childIdx;
            childIdx = eltIdx * 2 + 1;
        }
    }

    @Override
    public void add(T e) {
        if (end >= heap.length) {
            int currentSize = heap.length;
            Object[] newHeap = new Object[currentSize * 2];
            System.arraycopy(heap, 0, newHeap, 0, currentSize);
            heap = newHeap;
        }
        heap[end++] = e;
        bubbleUp();
    }

    @Override
    public T removeFirst() {
        if (this.isEmpty())
            throw new java.util.NoSuchElementException();
        T elt = (T) heap[0];
        swap(0, --end);
        bubbleDown(0);
        return elt;
    }

    @Override
    public T getFirst() {
        if (this.isEmpty()) throw new
                java.util.NoSuchElementException();
        return (T) heap[0];

    }

    @Override
    public int size() {
        return end;
    }

    @Override
    public int depth() {
        if (end == 0) return 0;
        return (int)
                (Math.log(end) / Math.log(2)) + 1;
    }

    @Override
    public boolean isEmpty() {
        return end == 0;
    }

    @Override
    public T remove(T e) {
        if (this.isEmpty()) throw new
                java.util.NoSuchElementException();
        if (this.exists(e)) {
            int eltIdx = end - 1;
            while (eltIdx >= 0) {
                T elt = (T) heap[eltIdx];
                if (elt.equals(e)) {
                    swap(eltIdx, --end);
                    bubbleDown(0);
                    return elt;
                }
                eltIdx--;
            }
        }
        return null;
    }

    @Override
    public boolean exists(T e) {
        for (int i = 0; i < end; i++) {
            Object elt = heap[i];
            if (elt.equals(e)) return true;
        }
        return false;
    }
}