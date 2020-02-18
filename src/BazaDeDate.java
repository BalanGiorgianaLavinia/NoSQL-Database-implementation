import java.io.BufferedWriter;
import java.io.IOException;
import java.util.ArrayList;

public class BazaDeDate {
    private String Db_Name = null;

    //numarul de noduri
    private int No_Nodes = 0;

    //capacitatea maxima a unui nod
    private int Max_Capacity = 0;

    //nrInstante e numarul total de instante din baza de date
    private int nrInstante = 0;

    //fac o lista care contine elemente de tip entitate
    private ArrayList<Entitate> lista_entitati = new ArrayList<Entitate>();

    //fac o lista care contine elemente de tip nod
    private ArrayList<Nod> lista_noduri = new ArrayList<Nod>();


    //constructor baza de date
    public BazaDeDate(String Db_Name, int No_Nodes, int Max_Capacity){
        this.Db_Name = Db_Name;
        this.No_Nodes = No_Nodes;
        this.Max_Capacity = Max_Capacity;

        //creez No_Nodes noduri cu id-ul corespunzator(numarul de ordine) si capacitatea maxima
        for(int i = 0; i < No_Nodes; i++){
            String id = Integer.toString(i+1);
            Nod nod = new Nod(id ,this.Max_Capacity);

            //adaug nodul creat in lista de noduri
            lista_noduri.add(nod);
        }
    }


    //metoda care adauga o entitate in lista de entitati din baza de date
    public void adaugaEntitate(Entitate entitate) {
        lista_entitati.add(entitate);
    }


    //metoda care returneaza o entitate cautata pe baza numelui
    public Entitate obtineEntitate(String entitate) {

        //iau fiecare entitate
        for(int i = 0; i < lista_entitati.size(); i++){

            //daca numele este acelasi cu cel cautat returnez entitatea
            if(lista_entitati.get(i).getNume_entitate().equals(entitate)){
                return lista_entitati.get(i);
            }
        }
        return null;
    }


    //metoda care adauga o instanta in noduri
    public void adaugaInstanta(Instanta instanta) {
        int factor_replicare = instanta.getEntitate().getFactor_replicare();

        //parcurg toate nodurile cat timp mai e nevoie sa inserez instanta
        for(int i = 0; (i < No_Nodes) && (factor_replicare > 0); i++) {
            Nod nod = lista_noduri.get(i);

            //daca nodul este plin sau contine deja instanta trec mai departe
            if(nod.estePlin()){
                continue;
            }else if(nod.contineInstanta(instanta.getCheiePrimara())){
                continue;

            //altfel, o adaug in nod, scad factorul de replicare si cresc numarul de instante din baza de date
            }else{
                nod.adaugaInstanta(instanta);
                factor_replicare--;
                this.nrInstante++;
            }
        }

        //Bonus 2
        while(factor_replicare-- > 0){
            Nod nodNou = new Nod(Integer.toString(lista_noduri.size() + 1), this.Max_Capacity);
            nodNou.adaugaInstanta(instanta);
            lista_noduri.add(nodNou);
            this.No_Nodes++;
        }
    }


    //metoda pentru afisarea bazei de date
    public void snapshot(BufferedWriter writer) throws IOException {

        //daca nu am nicio instanta in baza de date
        if(this.nrInstante == 0){
            writer.write("EMPTY DB\n");
            return;
        }

        //parcurg lista de noduri
        for(int i = 0; i < lista_noduri.size(); i++){
            Nod nod = lista_noduri.get(i);

            //daca nodul este gol, merg mai departe
            if(nod.esteGol()){
                continue;
            }else{
                writer.write("Nod" + nod.getId() + "\n");

                //parcurg lista de instante din fiecare nod si afisez instanta
                for(int j = 0; j < nod.lista_instante.size(); j++){
                    writer.write(nod.lista_instante.get(j).getNumeEntitate());

                    //parcurg lista de atribute pentru fiecare instanta si le afisez
                    for(int k = 0; k < nod.lista_instante.get(j).getLista_atribute().size(); k++){
                        Atribut atribut = nod.lista_instante.get(j).getLista_atribute().get(k);
                        Entitate entitate = nod.lista_instante.get(j).getEntitate();

                        writer.write(" " + entitate.getLista_atribute().get(k).getNume_atribut() + ":");

                        //fac afisarea atributelor in functie de tipul lor
                        switch(atribut.getTip_atribut()){
                            case "Integer":
                                writer.write( ((IntegerAtribut)atribut).afisare() );
                                break;

                            case "Float":
                                writer.write( ((FloatAtribut)atribut).afisare() );
                                break;

                            case "String":
                                writer.write( ((StringAtribut)atribut).afisare() );
                                break;
                        }
                    }
                    writer.write("\n");
                }
            }
        }
    }


    //metoda care imi scrie in fisierul de output instanta cu atributele si nodurile in care se gaseste
    public void get(String entity, String cheie_primara, BufferedWriter writer) throws IOException {
        Instanta instanta = null;

        //parcurg lista de noduri
        for(int i = 0; i < lista_noduri.size(); i++){
            Nod nod = lista_noduri.get(i);

            //daca nodul curent contine instanta
            if( nod.contineInstanta(cheie_primara) ){

                //parcurg lista de instante din el
                for(int j = 0; j < nod.getLista_instante().size(); j++){

                    //daca gasesc instanta cu cheia primara cautata retin instanta si afisez nodul in care se afla
                    if(nod.getLista_instante().get(j).getNumeEntitate().equals(entity) &&
                       nod.getLista_instante().get(j).getCheiePrimara().equals(cheie_primara)){
                        instanta = nod.getLista_instante().get(j);
                        writer.write("Nod" + nod.getId() + " ");
                    }
                }
            }
        }

        //daca nu gasesc instanta in niciun nod, ies din metoda
        if(instanta == null){
            writer.write("NO INSTANCE FOUND\n");
            return;
        }

        //daca o gasesc, o afisez
        writer.write(instanta.getNumeEntitate());

        //parcurg lista de atribute si le afisez in functie de tipul lor
        for(int k = 0; k < instanta.getLista_atribute().size(); k++){
            Atribut atribut = instanta.getLista_atribute().get(k);
            Entitate entitate = instanta.getEntitate();

            writer.write(" " + entitate.getLista_atribute().get(k).getNume_atribut() + ":");

            switch(atribut.getTip_atribut()){
                case "Integer":
                    writer.write( ((IntegerAtribut)atribut).afisare() );
                    break;

                case "Float":
                    writer.write( ((FloatAtribut)atribut).afisare() );
                    break;

                case "String":
                    writer.write( ((StringAtribut)atribut).afisare() );
                    break;
            }
        }
        writer.write("\n");
    }


    //metoda care imi actualizeaza toate copiile instantei din toate nodurile in care a fost inserata
    public void update(String entity, String cheie_primara, String[] vec) {

        //creez o lista cu atributele si o lista cu noile lor valori
        ArrayList<String> numeAtribute = new ArrayList<String>();
        ArrayList<String> valoriNoi = new ArrayList<String>();

        //iau din fisierul de input atributele si noile lor valori
        for(int i = 3; i < vec.length; i += 2){
            numeAtribute.add(vec[i]);
            valoriNoi.add(vec[i+1]);
        }

        //parcurg lista de noduri
        for(int i = 0; i < lista_noduri.size(); i++){
            Nod nod = lista_noduri.get(i);

            //parcurg lista de instante pentru fiecare nod
            for(int j = 0; j < nod.getLista_instante().size(); j++){

                //daca gasesc instanta cu cheia primara cautata
                if(nod.getLista_instante().get(j).getNumeEntitate().equals(entity) &&
                   nod.getLista_instante().get(j).getCheiePrimara().equals(cheie_primara)){

                    //o actualizez cu metoda update din clasa Instanta care primeste atributele si noile valori
                    Instanta instanta = nod.getLista_instante().get(j);
                    instanta.update(numeAtribute, valoriNoi);

                    break;
                }
            }

            //sortez lista de instante din nod
            nod.sort();
        }
    }


    //metoda care sterge toate copiile instantei din toate nodurile in care a fost inserata
    public void delete(String entity, String cheie_primara, BufferedWriter writer) throws IOException {
        boolean deleted = false;

        //parcurg lista de noduri
        for(int i = 0; i < lista_noduri.size(); i++){
            Nod nod = lista_noduri.get(i);

            //parcurg lista de instante din fiecare nod
            for(int j = 0; j < nod.getLista_instante().size(); j++){

                //cand o gasesc, o sterg si setez "deleted" pe true pentru astii ca am gasit instanta
                if(nod.getLista_instante().get(j).getNumeEntitate().equals(entity) &&
                    nod.getLista_instante().get(j).getCheiePrimara().equals(cheie_primara)){

                    nod.getLista_instante().remove(j);
                    deleted = true;
                    break;
                }
            }

            //sortez lista de instante din nod dupa stergerea instantei
            nod.sort();
        }

        //daca deleted e setat pe false inseamna ca nu am gasit instanta cautata pentru a o sterge
        if(!deleted){
            writer.write("NO INSTANCE TO DELETE\n");
        }
    }


    //metoda care sterge toate instantele cu timestampul mai mic decat cel dat ca parametru
    public void cleanup(long timestamp) {

        //parcurg lista de noduri
        for(int i = 0; i < lista_noduri.size(); i++) {
            Nod temp = lista_noduri.get(i);

            //pentru fiecare nod parcurg lista de instante
            for (int j = 0; j < temp.getLista_instante().size(); ) {

                //daca instanta curenta are timestampul mai mic
                if (temp.getLista_instante().get(j).getTimestamp() < timestamp) {

                    //o sterg, sortez lista de instante din nodul curent
                    temp.getLista_instante().remove(j);
                    temp.sort();

                    //scad numarul total de instante din baza de date
                    this.nrInstante--;
                }else{
                    j++;
                }
            }
        }
    }
}
