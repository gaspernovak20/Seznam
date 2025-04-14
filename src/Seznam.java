public interface Seznam<Tip> {
    // Dodajanje elementa v podatkovno strukturo
    void add(Tip e);

    // Odstranjevanje (in vračanje) prvega elementa iz pod. struk.
    Tip removeFirst();

    // Vračanje prvega elementa iz pod. struk.
    Tip getFirst();

    // Število elementov v podatkovni strukturi
    int size();

    // Globina podatkovne strukture
    int depth();

    // Ali je podakovna struktura prazna
    boolean isEmpty();

    // odstranjevanje (in vracanje) dolocenega elementa iz strukture
    Tip remove(Tip e);

    // ali element obstaja v strukturi
    boolean exists(Tip e);
}