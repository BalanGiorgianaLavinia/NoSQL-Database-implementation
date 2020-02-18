import java.util.ArrayList;
import java.util.Collections;

public class Nod {

    //capacitatea maxima a nodului
    private int MaxCapacity = 0;

    //id-ul nodului
    private String id = null;

    //creez o lista de instante pentru fiecare nod
    ArrayList<Instanta> lista_instante = new ArrayList<Instanta>();


    //constructor pentru nod
    public Nod(String id, int MaxCapacity) {
        this.id = id;
        this.MaxCapacity = MaxCapacity;
    }


    //metoda care verifica daca un nod este plin
    public boolean estePlin() {
        return lista_instante.size() == MaxCapacity;
    }


    //metoda care verifica daca nodul contine instanta cu cheia primara cautata
    public boolean contineInstanta(String cheiePrimara) {

        //daca lista e goala, ies din metoda
        if(lista_instante.size() == 0){
            return false;
        }else{

            //parcurg lista de instante si daca gasesc cheia primara cautata returnez true
            for(int i = 0; i < lista_instante.size(); i++){
                if(lista_instante.get(i).getCheiePrimara().equals(cheiePrimara)){
                    return true;
                }
            }
        }
        return false;
    }


    //metoda care adauga o instanta in nod
    public void adaugaInstanta(Instanta instanta) {
        try {
            lista_instante.add(0, (Instanta)instanta.clone());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public int getMaxCapacity() {
        return MaxCapacity;
    }

    public String getId() {
        return id;
    }

    public ArrayList<Instanta> getLista_instante() {
        return lista_instante;
    }

    public boolean esteGol() {
        return lista_instante.size() == 0;
    }

    public void sort() {
        Collections.sort(lista_instante);
    }
}
