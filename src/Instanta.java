import java.util.ArrayList;

import static java.lang.System.nanoTime;

public class Instanta implements Cloneable, Comparable<Instanta>{

    private String numeEntitate = null;
    private long timestamp = 0l;
    private Entitate entitate = null;
    private String cheiePrimara = null;

    //creez lista de atribute pentru instanta
    private ArrayList<Atribut> lista_atribute = new ArrayList<Atribut>();


    //constructor pentru instanta
    public Instanta(BazaDeDate bazaDeDate, String numeEntitate, String[] vec) {
        this.timestamp = nanoTime();
        this.numeEntitate = numeEntitate;

        Entitate entitate = bazaDeDate.obtineEntitate(numeEntitate);
        this.entitate = entitate;

        this.cheiePrimara = vec[2];

        //adaug fiecare atribut in lista de atribute a instantei
        for(int i = 0, j = 2; i < entitate.getLista_atribute().size(); i++, j++){
            Atribut atribut = entitate.getLista_atribute().get(i);

            switch(atribut.getTip_atribut()){

                case "Integer":
                    lista_atribute.add(new IntegerAtribut(vec[j], "Integer"));
                    break;

                case "Float":
                    lista_atribute.add(new FloatAtribut(vec[j], "Float"));
                    break;

                case "String":
                    lista_atribute.add(new StringAtribut(vec[j], "String"));
                    break;
            }
        }
    }


    //suprascriu metoda clone care imi cloneaza o instanta
    @Override
    public Instanta clone() {
        Instanta instantaNoua = null;

        //clonez instanta cu atributele ei
        try{
            instantaNoua = (Instanta)super.clone();
            instantaNoua.lista_atribute = (ArrayList<Atribut>)this.lista_atribute.clone();

        //tratez exceptia
        }catch (CloneNotSupportedException e){
            System.out.println(e.getMessage());
            System.exit(1);
        }
        return instantaNoua;
    }


    //metoda care imi actualizeaza instanta
    public void update(ArrayList<String> numeAtribute, ArrayList<String> valoriNoi) {
        this.setTimestamp(nanoTime());

        for(int i = 0; i < numeAtribute.size(); i++){
            for(int j = 0; j < entitate.getLista_atribute().size(); j++){
                if(numeAtribute.get(i).equals(entitate.getLista_atribute().get(j).getNume_atribut())){

                    String tipAtribut = entitate.getLista_atribute().get(j).getTip_atribut();
                    Atribut atribut = lista_atribute.get(j);

                    switch (tipAtribut){
                        case "Integer":
                            ((IntegerAtribut)atribut).setIntegerAtribut(Integer.parseInt(valoriNoi.get(i)));
                            atribut.setNume_atribut(valoriNoi.get(i));
                            break;

                        case "Float":
                            ((FloatAtribut)atribut).setFloatAtribut(Float.parseFloat(valoriNoi.get(i)));
                            atribut.setNume_atribut(valoriNoi.get(i));
                            break;

                        case "String":
                            atribut.setNume_atribut(valoriNoi.get(i));
                            break;
                    }

                    break;
                }
            }
        }

        //setez cheia primara ca fiind primul atribut din lista de atribute
        this.setCheiePrimara(lista_atribute.get(0).getNume_atribut());
    }


    //suprascriu metoda compareTo care compara timestampuri
    @Override
    public int compareTo(Instanta o) {
        if(this.getTimestamp() > o.getTimestamp()){
            return -1;
        }else if(this.getTimestamp() < o.getTimestamp()){
            return 1;
        }
        return 0;
    }


    public String getNumeEntitate() {
        return numeEntitate;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public Entitate getEntitate() {
        return entitate;
    }

    public String getCheiePrimara() {
        return cheiePrimara;
    }

    public ArrayList<Atribut> getLista_atribute() {
        return lista_atribute;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public void setCheiePrimara(String cheiePrimara) {
        this.cheiePrimara = cheiePrimara;
    }

}

