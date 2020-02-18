public class IntegerAtribut extends Atribut implements Cloneable{

    private int integerAtribut = 0;

    //constructor
    public IntegerAtribut(String nume_atribut, String tip_atribut) {
        super(nume_atribut, tip_atribut);
        integerAtribut = Integer.parseInt(nume_atribut);
    }

    //metoda care cloneaza un atribut de tip integer
    public IntegerAtribut clone(){
        IntegerAtribut clonaAtribut = null;

        try{
            clonaAtribut = (IntegerAtribut) super.clone();
        }catch (CloneNotSupportedException e){
            System.out.println(e.getMessage());
        }

        return clonaAtribut;
    }


    public int getIntegerAtribut() {
        return integerAtribut;
    }

    public void setIntegerAtribut(int integerAtribut) {
        this.integerAtribut = integerAtribut;
    }

    @Override
    public String getNume_atribut() {
        return super.getNume_atribut();
    }

    @Override
    public void setNume_atribut(String nume_atribut) {
        super.setNume_atribut(nume_atribut);
    }

    @Override
    public String getTip_atribut() {
        return super.getTip_atribut();
    }

    @Override
    public void setTip_atribut(String tip_atribut) {
        super.setTip_atribut(tip_atribut);
    }

    public String afisare() { return Integer.toString(this.integerAtribut); }

}
