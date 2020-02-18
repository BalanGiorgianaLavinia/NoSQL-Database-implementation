import java.text.DecimalFormat;

public class FloatAtribut extends Atribut implements Cloneable{

    private float floatAtribut = 0f;

    //constructor
    public FloatAtribut(String nume_atribut, String tip_atribut) {
        super(nume_atribut, tip_atribut);
        floatAtribut = Float.parseFloat(nume_atribut);
    }

    //metoda care cloneaza un atribut de tip float
    public FloatAtribut clone(){
        FloatAtribut clonaAtribut = null;

        try{
            clonaAtribut = (FloatAtribut) super.clone();
        }catch (CloneNotSupportedException e){
            System.out.println(e.getMessage());
        }

        return clonaAtribut;
    }


    public float getFloatAtribut() {
        return floatAtribut;
    }

    public void setFloatAtribut(float floatAtribut) {
        this.floatAtribut = floatAtribut;
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

    public String afisare() {
        DecimalFormat format = new DecimalFormat("#.##");
        return format.format((float) this.floatAtribut);
    }
}
