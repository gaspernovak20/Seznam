import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

public class Drevo23<T extends Comparable<T>> implements Seznam<T> {
    Element23 root;

    public Drevo23() {
    }

    class Element23 {
        public T a;
        public T b;
        public Element23 parent;
        public Element23 childrenL;
        public Element23 childrenS;
        public Element23 childrenD;

        public Element23(T a) {
            this.a = a;
        }

        @Override
        public String toString() {
            return b == null ? "(" + a + ")" : "(" + a + "," + b + ")";
        }
    }

    private class InsertResult {
        Element23 newNode;      // Novo vozlišče, če ni bilo razcepa
        boolean splitOccurred;  // Ali je bil razcep
        T promotedKey;          // Ključ, ki se dvigne navzgor
        Element23 leftChild;    // Levi otrok, če je bil razcep
        Element23 rightChild;   // Desni otrok, če je bil razcep

        InsertResult(Element23 node) {
            this.newNode = node;
            this.splitOccurred = false;
        }

        InsertResult(T promotedKey, Element23 left, Element23 right) {
            this.promotedKey = promotedKey;
            this.leftChild = left;
            this.rightChild = right;
            this.splitOccurred = true;
        }
    }

    @Override
    public void add(T e) {
        if (root == null) {
            root = new Element23(e);
            return;
        }

        InsertResult result = add(root, e);

        if (result.splitOccurred) {
            Element23 newRoot = new Element23(result.promotedKey);
            newRoot.childrenL = result.leftChild;
            newRoot.childrenS = result.rightChild;

            result.leftChild.parent = newRoot;
            result.rightChild.parent = newRoot;

            root = newRoot;
        } else {
            root = result.newNode;
        }
    }

    private InsertResult add(Element23 root, T e) {
        if (root.childrenL == null) {
            int cmpA = e.compareTo(root.a);

            if (root.b == null) {
                if (cmpA < 0) {
                    root.b = root.a;
                    root.a = e;
                } else if (cmpA > 0) {
                    root.b = e;
                } else {
//                        TODO: IllegalOperationException
                    throw new NoSuchElementException(); // Duplicate
                }
                return new InsertResult(root);
            } else {
                int cmpB = e.compareTo(root.b);

                if (cmpA == 0 || cmpB == 0) {
//                        TODO: IllegalOperationException
                    throw new NoSuchElementException(); // Duplicate
                }

                T small, middle, large;

                if (cmpA < 0) {
                    small = e;
                    middle = root.a;
                    large = root.b;
                } else {
                    if (cmpB < 0) {
                        small = root.a;
                        middle = e;
                        large = root.b;
                    } else {
                        small = root.a;
                        middle = root.b;
                        large = e;
                    }
                }

                // Ustvari novo notranje vozlišče
                Element23 left = new Element23(small);
                Element23 right = new Element23(large);


                return new InsertResult(middle, left, right);
            }
        }

//        inner node

        // inner node
        InsertResult result;
        if (e.compareTo(root.a) < 0) {
            result = add(root.childrenL, e);
            if (result.splitOccurred) {
                return handleChildSplit(root, result, 0);
            }
        } else if (root.b == null || e.compareTo(root.b) < 0) {
            result = add(root.childrenS, e);
            if (result.splitOccurred) {
                return handleChildSplit(root, result, 1);
            }
        } else {
            result = add(root.childrenD, e);
            if (result.splitOccurred) {
                return handleChildSplit(root, result, 2);
            }
        }

        return new InsertResult(root);
    }

    private InsertResult handleChildSplit(Element23 node, InsertResult childSplit, int position) {
        if (node.b == null) {
            // Notranje vozlišče z enim ključem
            if (position == 0) {
                node.b = node.a;
                node.a = childSplit.promotedKey;
                node.childrenD = node.childrenS;
                node.childrenL = childSplit.leftChild;
                node.childrenS = childSplit.rightChild;
            } else {
                node.b = childSplit.promotedKey;
                node.childrenS = childSplit.leftChild;
                node.childrenD = childSplit.rightChild;
            }

            node.childrenL.parent = node;
            node.childrenS.parent = node;
            if (node.childrenD != null) {
                node.childrenD.parent = node;
            }
            return new InsertResult(node);
        } else {
            // Notranje vozlišče z dvema ključema – razcep
            T a = node.a, b = node.b;
            T middle;
            Element23 left, right;

            if (position == 0) {
                middle = a;
                left = new Element23(childSplit.promotedKey);
                left.childrenL = childSplit.leftChild;
                left.childrenS = childSplit.rightChild;

                right = new Element23(b);
                right.childrenL = node.childrenS;
                right.childrenS = node.childrenD;
            } else if (position == 1) {
                middle = childSplit.promotedKey;
                left = new Element23(a);
                left.childrenL = node.childrenL;
                left.childrenS = childSplit.leftChild;

                right = new Element23(b);
                right.childrenL = childSplit.rightChild;
                right.childrenS = node.childrenD;
            } else {
                middle = b;
                left = new Element23(a);
                left.childrenL = node.childrenL;
                left.childrenS = node.childrenS;

                right = new Element23(childSplit.promotedKey);
                right.childrenL = childSplit.leftChild;
                right.childrenS = childSplit.rightChild;
            }

            left.childrenL.parent = left;
            left.childrenS.parent = left;
            right.childrenL.parent = right;
            right.childrenS.parent = right;

            return new InsertResult(middle, left, right);
        }
    }


    @Override
    public T removeFirst() {
        return root != null? remove(root.a): null;
    }

    @Override
    public T getFirst() {
        return root != null ? root.a : null;
    }

    @Override
    public int size() {
        return size(root);
    }

    private int size(Element23 node) {
        if (node == null) return 0;
        int numVr = node.b == null ? 1 : 2;
        return numVr + size(node.childrenL) + size(node.childrenS) + size(node.childrenD);
    }

    @Override
    public int depth() {
        return depth(root);
    }

    private int depth(Element23 node) {
        if (node == null) return 0;
        return 1 + depth(node.childrenL);
    }

    @Override
    public boolean isEmpty() {
        return root == null;
    }

    @Override
    public T remove(T e) {
        Wrapper<T> removed = new Wrapper<>();
        root = remove(root, e, removed);

        if (root != null && root.a == null && root.childrenL != null) {
            root = root.childrenL;
            root.parent = null;
        }

        if (root != null && root.a == null && root.childrenL == null) {
            root = null;
        }

        return removed.value;
    }

    private Element23 remove(Element23 node, T e, Wrapper<T> removed) {
        if (node == null) return null;

        // Notranje vozlišče
        if (node.childrenL != null) {
            if (e.equals(node.a) || (node.b != null && e.equals(node.b))) {
                Element23 succ = getMinNode(e.equals(node.a) ? node.childrenS : node.childrenD);
                T succValue = succ.a;

                if (e.equals(node.a)) {
                    removed.value = node.a;
                    node.a = succValue;
                    remove(node.childrenS, succValue, new Wrapper<>());
                } else {
                    removed.value = node.b;
                    node.b = succValue;
                    remove(node.childrenD, succValue, new Wrapper<>());
                }

            } else {
                if (e.compareTo(node.a) < 0) {
                    remove(node.childrenL, e, removed);
                } else if (node.b == null || e.compareTo(node.b) < 0) {
                    remove(node.childrenS, e, removed);
                } else {
                    remove(node.childrenD, e, removed);
                }
            }

        } else {
            // List
            if (e.equals(node.a)) {
                removed.value = node.a;
                if (node.b != null) {
                    node.a = node.b;
                    node.b = null;
                } else {
                    node.a = null;
                    uravnotezi(node);
                }
            } else if (e.equals(node.b)) {
                removed.value = node.b;
                node.b = null;
            }
        }

        return node;
    }

    private void uravnotezi(Element23 node) {
        if (node.parent == null) return;

        Element23 parent = node.parent;

        if (parent.childrenL == node) {
            Element23 sibling = parent.childrenS;
            if (sibling != null && sibling.b != null) {
                node.a = parent.a;
                parent.a = sibling.a;
                sibling.a = sibling.b;
                sibling.b = null;
            } else {
                node.a = parent.a;
                node.b = sibling.a;
                parent.a = parent.b;
                parent.b = null;
                parent.childrenL = node;
                parent.childrenS = parent.childrenD;
                parent.childrenD = null;
            }

        } else if (parent.childrenS == node) {
            Element23 sibling = parent.childrenL;
            if (sibling != null && sibling.b != null) {
                node.a = parent.a;
                parent.a = sibling.b;
                sibling.b = null;
            } else {
                if (node.a == null && node.childrenL != null && parent.childrenD != null){
                    sibling = parent.childrenD;
                    sibling.b = sibling.a;
                    sibling.a = parent.b;
                    parent.b = null;
                    sibling.childrenD = sibling.childrenS;
                    sibling.childrenS = sibling.childrenL;
                    sibling.childrenL = node.childrenL;
                    sibling.childrenL.parent = sibling;
                    parent.childrenS = sibling;
                    parent.childrenD = null;
                } else if (node.a == null && node.childrenL == null && parent.childrenD != null) {
                    sibling = parent.childrenD;
                    node.a = parent.b;
                    if(sibling.b != null) {
                        parent.b = sibling.a;
                        sibling.a = sibling.b;
                        sibling.b = null;
                    }else {
                        node.b = sibling.a;
                        parent.b = null;
                        parent.childrenD = null;
                    }
                } else {
                    sibling.b = parent.a;
                    parent.a = parent.b;
                    parent.b = null;
                    sibling.childrenD = node.childrenL;
                    parent.childrenS = parent.childrenD;
                    parent.childrenD = null;
                }
            }

        } else if (parent.childrenD == node) {
            Element23 sibling = parent.childrenS;
            if (sibling != null && sibling.b != null) {
                node.a = parent.b;
                parent.b = sibling.b;
                sibling.b = null;
            } else {
                sibling.b = parent.b;
                parent.b = null;
                parent.childrenD = null;
            }
        }

        if (parent.a == null) uravnotezi(parent);
    }

    private Element23 getMinNode(Element23 node) {
        while (node.childrenL != null) {
            node = node.childrenL;
        }
        return node;
    }



    private static class Wrapper<T> {
        T value;
    }


    @Override
    public boolean exists(T e) {
        return exists(root, e);
    }

    private boolean exists(Element23 node, T e) {
        if (node == null) return false;
        int cmpA = e.compareTo(node.a);

        if (cmpA == 0 || (node.b != null && e.compareTo(node.b) == 0)) {
            return true;
        } else if (cmpA < 0) {
            return exists(node.childrenL, e);
        } else if (node.b == null || e.compareTo(node.b) < 0) {
            return exists(node.childrenS, e);
        } else {
            return exists(node.childrenD, e);
        }
    }

    @Override
    public List<T> asList() {
        List<T> list = new ArrayList<>();
        asList(root, list);
        return list;
    }

    private void asList(Element23 node, List<T> list) {
        if (node == null) return;

        // Najprej dodamo podatke trenutnega vozlišča
        list.add(node.a);
        if (node.b != null) list.add(node.b);

        // Nato gremo rekurzivno čez otroke v vrstnem redu L-S-D
        asList(node.childrenL, list);
        asList(node.childrenS, list);
        asList(node.childrenD, list);
    }

    @Override
    public String toString() {
        return toString(root);
    }

    private String toString(Element23 node) {
        if (node == null) return "";

        return node + toString(node.childrenL) + toString(node.childrenS) + toString(node.childrenD);

    }
}
