public class Atribut {

    private String nume_atribut = null;
    private String tip_atribut = null;

    public Atribut(String nume_atribut, String tip_atribut){
        this.nume_atribut = nume_atribut;
        this.tip_atribut = tip_atribut;
    }

    public String getNume_atribut() {
        return nume_atribut;
    }

    public void setNume_atribut(String nume_atribut) {
        this.nume_atribut = nume_atribut;
    }

    public String getTip_atribut() {
        return tip_atribut;
    }

    public void setTip_atribut(String tip_atribut) {
        this.tip_atribut = tip_atribut;
    }
}
