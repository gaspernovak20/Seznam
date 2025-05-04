import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;

import static java.lang.Math.max;

public class BST<T extends Comparable> implements Seznam<T> {
    private ElementBST root;

    public class ElementBST {
        public T vrednost;
        public ElementBST oce, levi, desni;

        public ElementBST(T vrednost) {
            this.vrednost = vrednost;
        }
    }

    public boolean member(T e, ElementBST node) {
        if (node == null) return false;

        int cmp = e.compareTo(node.vrednost);

        if (cmp == 0) {
            return true;
        } else if (cmp < 0) {
            return member(e, node.levi);
        } else {
            return member(e, node.desni);
        }

    }

    public ElementBST insert(T e, ElementBST node) {
        if (node == null) {
            ElementBST newNode = new ElementBST(e);
            return newNode;
        }

        int cmp = e.compareTo(node.vrednost);

        if (cmp < 0) {
            node.levi = insert(e, node.levi);
            node.levi.oce = node;
            return node;
        } else if (cmp > 0) {
            node.desni = insert(e, node.desni);
            node.desni.oce = node;
            return node;
        } else {
            throw new IllegalArgumentException("Element ne obstaja.");
        }
    }

    public ElementBST delete(T e, ElementBST node) {
        if (node == null) {
            throw new NoSuchElementException("Element ne obstaja.");
        }

        int cmp = e.compareTo(node.vrednost);

        if (cmp < 0) {
            node.levi = delete(e, node.levi);
        } else if (cmp > 0) {
            node.desni = delete(e, node.desni);
        } else {
            if (node.levi == null) {
                return node.desni;
            } else if (node.desni == null) {
                return node.levi;
            } else {
                node.vrednost = deleteMin(node, node.desni);
            }
        }
        return node;
    }


    public int countNodes(ElementBST node) {
        if (node == null) return 0;
        return 1 + countNodes(node.levi) + countNodes(node.desni);
    }

    public int getDepth(ElementBST node) {
        if (node == null) return 0;
        return 1 + max(getDepth(node.levi), getDepth(node.desni));
    }

    private T deleteMin(ElementBST parent, ElementBST node) {
        while (node.levi != null) {
            parent = node;
            node = node.levi;
        }

        if (parent.desni == node) parent.desni = node.desni;
        else parent.levi = node.levi;

        return node.vrednost;
    }

    @Override
    public void add(T e) {
        root = insert((T) e, root);
    }

    @Override
    public T removeFirst() {
        if (root == null) throw new NoSuchElementException();
        ElementBST firstEl = root;
        root = delete(root.vrednost, root);
        return firstEl.vrednost;
    }

    @Override
    public T getFirst() {
        if (root == null) throw new NoSuchElementException();
        return root.vrednost;
    }

    @Override
    public int size() {
        return countNodes(root);
    }

    @Override
    public int depth() {
        return getDepth(root);
    }

    @Override
    public boolean isEmpty() {
        return getDepth(root) == 0;
    }

    @Override
    public T remove(T e) {
        root = delete((T) e, root);
        return e;
    }

    @Override
    public boolean exists(T e) {
        return member((T) e, root);
    }

    @Override
    public List<T> asList() {
        List<T> result = new LinkedList<>();
        return asListRet(result, root);
    }

    private List<T> asListRet(List<T> list, ElementBST el) {
        if (el == null) return list;
        list = asListRet(list, el.levi);
        list.add(el.vrednost);
        list = asListRet(list, el.desni);
        return list;
    }
}