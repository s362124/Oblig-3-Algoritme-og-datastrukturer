# Obligatorisk oppgave 3 i Algoritmer og Datastrukturer

Denne oppgaven er en innlevering i Algoritmer og Datastrukturer. 
Oppgaven er levert av følgende student:
* Joulia Soussi, S362124, s362124@oslomet.no


# Oppgavebeskrivelse

I oppgave 1 så kopierte jeg koden 5.2.3) fra kompendiet, og i tillegg gjorde jeg de endringene for at referansen forelder får korrekt verdi i
hver node.

I oppgave 2 så lagde jeg koden til metoden ```public
int antall(T verdi)```, og inn i metoden brukte jeg while løkke og if- setningen. Metoden skal returneres antall forekomster av en verdi i treet. Og det skal returneres 0 dersom det er null verdi i treet.

I oppgave 3 så lagde jeg koden til metoden ```private static <T> Node<T> førstePostorden(Node<T> p)```
og den skal returnere første node post orden med p som rot.  og den metoden ```private static <T> Node<T> nestePostorden(Node<T> p)```, og den skal returneres  den noden som kommer etter p i postorden. 

I oppgave 4 så brukte jeg funksjonen nestePostorden fra forrige oppgave. Finner den første noden p i postorden, og lager deretter en while-løkke hvor jeg tar i bruk setningen fra oppgaven, ```p = nestePostorden(p)```.

I oppgave 5 så lagde jeg metoden ```public ArrayList<T> serialize()``` som gjør om binærtreet til et array og brukte en kø til å traversere treet i nivå orden. I tillegg lagde jeg koden til metoden ```static <K> SBinTre<K> deserialize(ArrayList<K> data,
Comparator<? super K> c)``` som tar et array og gjør om til et binært søketre.  

I oppgave 6 så kopierte jeg programkode 5.2.8 d) og gjorde endringene for at pekeren forelder får korrekt verdi i alle noder etter en fjerning. Også lagde koden til metoden ```public int fjernAlle(T verdi)``` for at den skal fjerne alle forekomstene av verdi i treet, og metoden skal returnere antallet som ble fjernet og hvis
treet er tomt, skal 0 returneres. Jeg lagde også koden til metoden ```public void nullstill()```, den skal
traversere treet i en eller annen rekkefølge og sørge for at samtlige
pekere og nodeverdier i treet blir nullet. Det er med andre ord ikke tilstrekkelig å
sette rot til null og antall til 0.
