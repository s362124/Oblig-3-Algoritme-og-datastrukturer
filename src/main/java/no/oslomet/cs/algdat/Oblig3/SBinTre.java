package no.oslomet.cs.algdat.Oblig3;


import java.util.*;

public class SBinTre<T> {
    private static final class Node<T>   // en indre nodeklasse
    {
        private T verdi;                   // nodens verdi
        private Node<T> venstre, høyre;    // venstre og høyre barn
        private Node<T> forelder;          // forelder

        // konstruktør
        private Node(T verdi, Node<T> v, Node<T> h, Node<T> forelder) {
            this.verdi = verdi;
            venstre = v;
            høyre = h;
            this.forelder = forelder;
        }

        private Node(T verdi, Node<T> forelder)  // konstruktør
        {
            this(verdi, null, null, forelder);
        }

        @Override
        public String toString() {
            return "" + verdi;
        }

    } // class Node

    private Node<T> rot;                            // peker til rotnoden
    private int antall;                             // antall noder
    private int endringer;                          // antall endringer

    private final Comparator<? super T> comp;       // komparator

    public SBinTre(Comparator<? super T> c)    // konstruktør
    {
        rot = null;
        antall = 0;
        comp = c;
    }

    public boolean inneholder(T verdi) {
        if (verdi == null) return false;

        Node<T> p = rot;

        while (p != null) {
            int cmp = comp.compare(verdi, p.verdi);
            if (cmp < 0) p = p.venstre;
            else if (cmp > 0) p = p.høyre;
            else return true;
        }

        return false;
    }

    public int antall() {
        return antall;
    }

    public String toStringPostOrder() {
        if (tom()) return "[]";

        StringJoiner s = new StringJoiner(", ", "[", "]");

        Node<T> p = førstePostorden(rot); // går til den første i postorden
        while (p != null) {
            s.add(p.verdi.toString());
            p = nestePostorden(p);
        }

        return s.toString();
    }

    public boolean tom() {
        return antall == 0;
    }

    public boolean leggInn(T verdi) {
        Objects.requireNonNull(verdi, "Ulovlig med nullverdier!");

        Node<T> p = rot, q = null;               // p starter i roten
        int cmp = 0;                             // hjelpevariabel

        while (p != null)       // fortsetter til p er ute av treet
        {
            q = p;                                 // q er forelder til p
            cmp = comp.compare(verdi,p.verdi);     // bruker komparatoren
            p = cmp < 0 ? p.venstre : p.høyre;     // flytter p
        }

        // p er nå null, dvs. ute av treet, q er den siste vi passerte

        p = new Node<>(verdi, q);                   // oppretter en ny node

        if (q == null) rot = p;                  // p blir rotnode
        else if (cmp < 0) q.venstre = p;         // venstre barn til q
        else q.høyre = p;                        // høyre barn til q

        antall++;                                // én verdi mer i treet
        return true;                             // vellykket innlegging

        // throw new UnsupportedOperationException("Ikke kodet ennå!");
    }

    public boolean fjern(T verdi) {
        throw new UnsupportedOperationException("Ikke kodet ennå!");
    }

    public int fjernAlle(T verdi) {
        throw new UnsupportedOperationException("Ikke kodet ennå!");
    }

    public int antall(T verdi) {
        int antForekomster = 0;
        if(verdi == null){
            return 0;
        }
        Node<T> r = rot;
        while (r!=null) {
            int komp = comp.compare(verdi, r.verdi);
            if (komp < 0)
                r = r.venstre;
            else {
                if (komp == 0)
                    antForekomster++;
                r = r.høyre;
            }
        }
        return antForekomster;
    }

    public void nullstill() {
        throw new UnsupportedOperationException("Ikke kodet ennå!");
    }

    private static <T> Node<T> førstePostorden(Node<T> p) {
        if (p == null){
            throw new NoSuchElementException("Dette treet er tomt!");
        }
        while (true){
            if (p.venstre != null)p = p.venstre;
            else if (p.høyre != null) p = p.høyre;
            else return p;
        }
    }

    private static <T> Node<T> nestePostorden(Node<T> p) {
        if (p == null) {
            throw new NoSuchElementException("Dette treet er tomt!");
        }

        else if (p.forelder == null) {
            p = null;
        }

        else if (p == p.forelder.høyre) {
            p = p.forelder;
        }

        else if (p == p.forelder.venstre) {
            if (p.forelder.høyre == null) {
            p = p.forelder;
        }
            else
            p = førstePostorden(p.forelder.høyre);
        }
        return p;
    }

    // Oppgave 4
    public void postorden(Oppgave<? super T> oppgave) {
        if (rot == null)
            return;
        Node<T> s = førstePostorden(rot);
        oppgave.utførOppgave(s.verdi);
        Node<T> p = nestePostorden(s);
        while (p!=null){
            oppgave.utførOppgave(p.verdi);
            p= nestePostorden(p);
        }
    }

    public void postordenRecursive(Oppgave<? super T> oppgave) {
        postordenRecursive(rot, oppgave);
    }

    private void postordenRecursive(Node<T> p, Oppgave<? super T> oppgave) {
        if (p == null){
            return;
        }
        if(p.venstre != null){
            postordenRecursive(p.venstre, oppgave);
        }
        if (p.høyre != null){
            postordenRecursive(p.høyre, oppgave);
        }
        oppgave.utførOppgave(p.verdi);
    }

    public ArrayList<T> serialize() {
        ArrayList<T> enListe = new ArrayList<>();
        ArrayDeque<Node> queue = new ArrayDeque<Node>();

        queue.addLast(rot);
        while ( !queue.isEmpty()){

            Node<T> existant = queue.removeFirst();

            if(existant.venstre!=null){
                queue.addLast(existant.venstre);
            }
            if(existant.høyre!=null){
                queue.addLast(existant.høyre);
            }
            enListe.add(existant.verdi);
        }
        return enListe;
    }

    static <K> SBinTre<K> deserialize(ArrayList<K> data, Comparator<? super K> c) {
        SBinTre<K> tre = new SBinTre<K>(c);
        for (int i = 0; i<data.size(); i++){
            tre.leggInn(data.get(i));
        }
        return tre;
    }


} // ObligSBinTre
