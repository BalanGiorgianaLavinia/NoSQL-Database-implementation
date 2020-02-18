public class StringAtribut extends Atribut implements Cloneable{

    //constructor
    public StringAtribut(String nume_atribut, String tip_atribut) {
        super(nume_atribut, tip_atribut);
    }


    //metoda care cloneaza un atribut de tip string
    public StringAtribut clone(){
        StringAtribut clonaAtribut = null;

        try{
            clonaAtribut = (StringAtribut) super.clone();
        }catch (CloneNotSupportedException e){
            System.out.println(e.getMessage());
        }

        return clonaAtribut;
    }


    public String afisare() { return getNume_atribut(); }

    @Override
    public String getNume_atribut() { return super.getNume_atribut(); }

    @Override
    public void setNume_atribut(String nume_atribut) { super.setNume_atribut(nume_atribut); }

    @Override
    public String getTip_atribut() { return super.getTip_atribut(); }

    @Override
    public void setTip_atribut(String tip_atribut) { super.setTip_atribut(tip_atribut); }

}
