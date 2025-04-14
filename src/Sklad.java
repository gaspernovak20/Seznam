//package ltpo.seznam;

import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTimeoutPreemptively;

public class Sklad<T> implements Seznam<T> {
    private Element<T> vrh;

    class Element<T> {
        public T vrednost;
        public Element<T> vezava;

        public Element(T e) {
            vrednost = e;
        }
    }

    public Sklad() {
    }

    public void push(T e) {
        Element<T> novVrh = new Element<>(e);
        novVrh.vezava = vrh;
        vrh = novVrh;
    }

    public T pop() {
        if (null == vrh) {
            throw new java.util.NoSuchElementException();
        }
        T vr = vrh.vrednost;
        vrh = vrh.vezava;
        return vr;
    }

    public T top() {
        if (vrh == null) throw new NoSuchElementException();
        return vrh.vrednost;
    }

    public int search(T element) {
        if (vrh == null) throw new NoSuchElementException();

        int index = 0;
        Element<T> temp = vrh;

        while (temp != null) {
            if (temp.vrednost.equals(element)) {  // Compare data, not the node itself
                return index;
            }
            temp = temp.vezava;
            index++;
        }

        return -1;  // Element not found
    }

    public boolean isTop(T el) {
        if (vrh == null) throw new NoSuchElementException();

        return top().equals(el);
    }

    @Override
    public boolean isEmpty() {
        return vrh == null;
    }

    @Override
    public T remove(T e) {
        if (this.isEmpty()) throw new
                java.util.NoSuchElementException();

        if (vrh.vrednost.equals(e)) {
            T removed = (T) vrh.vrednost;
            vrh = vrh.vezava;
            return removed;
        }
        Element<T> prevEl = vrh;
        Element<T> currEl = prevEl.vezava;

        while (currEl != null) {
            if (currEl.vrednost.equals(e)) {
                T removed = (T) currEl.vrednost;
                prevEl.vezava = currEl.vezava;
                return removed;
            }
            prevEl = currEl;
            currEl = currEl.vezava;
        }

        return null;
    }

    @Override
    public boolean exists(T e) {
        Element<T> temp = vrh;
        while (temp != null) {
            if (temp.vrednost.equals(e)) return true;
            temp = temp.vezava;
        }
        return false;
    }

    @Override
    public int size() {
        int result = 0;
        Element<T> temp = vrh;
        while (null != temp) {
            ++result;
            temp = temp.vezava;
        }
        return result;
    }

    @Override
    public void add(T e) {
        push(e);
    }

    @Override
    public T removeFirst() {
        return pop();
    }

    @Override
    public T getFirst() {
        return top();
    }

    @Override
    public int depth() {
        return size();
    }

}
