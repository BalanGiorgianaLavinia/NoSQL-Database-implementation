import java.util.ArrayList;

public class Entitate {

    private String nume_entitate = null;
    private int factor_replicare = 0;

    //numarul de atribute ale entitatii
    private int No_Attributes = 0;

    private String cheie_primara = null;

    //creez lista de atribute
    private ArrayList<Atribut> lista_atribute = new ArrayList<Atribut>();


    //constructor
    public Entitate(String Entity, int RF, int No_Attributes, String[] vec) {

        this.nume_entitate = Entity;
        this.factor_replicare = RF;
        this.No_Attributes = No_Attributes;
        this.cheie_primara = vec[4];

        for(int i = 4; i < vec.length; i += 2){
            Atribut atribut = new Atribut(vec[i],vec[i+1]);
            lista_atribute.add(atribut);
        }
    }

    public String getNume_entitate() { return nume_entitate; }

    public int getFactor_replicare() { return factor_replicare; }

    public int getNo_Attributes() { return No_Attributes; }

    public String getCheie_primara() { return cheie_primara; }

    public ArrayList<Atribut> getLista_atribute() {
        return lista_atribute;
    }
}
