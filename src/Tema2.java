import java.io.*;

public class Tema2 {

    public static void main(String[] args) throws IOException {
        File file = new File(args[0]);

        //fisierele de input si output
        BufferedReader reader = null;
        BufferedWriter writer = null;

        //deschid fisierele de input si output
        try {
            reader = new BufferedReader(new FileReader(file));
        } catch (Exception e) {
            System.out.println("Nu a reusit deschiderea fisierului de input!!!");
        }

        try {
            writer = new BufferedWriter(new FileWriter(args[0] + "_out"));
        } catch (Exception e) {
            System.out.println("Nu a reusit deschiderea fisierului de output!!!");
        }

        BazaDeDate bazaDeDate = null;
        String linie = null;

        //parsarea inputului cu split
        while((linie = reader.readLine()) != null){

            String[] vec = linie.split(" ");
            String comanda = vec[0];
            String Entity = null;
            String cheie_primara = null;

            switch(comanda){

                case "CREATEDB":
                    String Db_Name = vec[1];
                    int No_Nodes = Integer.parseInt(vec[2]);
                    int Max_Capacity = Integer.parseInt(vec[3]);

                    //creez baza de date cu numele, numarul de noduri si capacitatea maxima date
                    bazaDeDate = new BazaDeDate(Db_Name, No_Nodes, Max_Capacity);

                    break;

                case "CREATE":
                    Entity = vec[1];
                    int RF = Integer.parseInt(vec[2]);
                    int No_Attributes = Integer.parseInt(vec[3]);

                    //creez o entitate cu numele, RF-ul si numarul de atribute date
                    //constructorul primeste ca parametru si vectorul ramas pentru a face restul parsarii
                    Entitate entitate = new Entitate(Entity, RF, No_Attributes, vec);

                    //adaug entitatea in baza de date
                    bazaDeDate.adaugaEntitate(entitate);

                    break;

                case "INSERT":
                    Entity = vec[1];

                    //creez o instanta noua in functie de numele bazei de date si numele entitatii
                    //constructorul primeste ca parametru si vectorul ramas pentru a face restul parsarii
                    Instanta instanta = new Instanta(bazaDeDate, Entity, vec);

                    //adaug instanta creata in baza de date
                    bazaDeDate.adaugaInstanta(instanta);

                    break;

                case "DELETE":
                    Entity = vec[1];
                    cheie_primara = vec[2];

                    //sterg instanta din baza de date
                    //functia primeste ca parametru si fisierul de output pentru a afisa mesajul corespunzator
                    bazaDeDate.delete(Entity, cheie_primara, writer);

                    break;

                case "UPDATE":
                    Entity = vec[1];
                    cheie_primara = vec[2];

                    //actualizez toate copiile instantei din toate nodurile in care a fost inserata
                    bazaDeDate.update(Entity, cheie_primara, vec);

                    break;

                case "GET":
                    Entity = vec[1];
                    cheie_primara = vec[2];

                    //returnez instanta cu toate nodurile in care se afla
                    //afisez in output mesajul corespunzator
                    bazaDeDate.get(Entity, cheie_primara, writer);

                    break;

                case "SNAPSHOTDB":
                    //afisez starea actuala a bazei de date
                    bazaDeDate.snapshot(writer);

                    break;

                case "CLEANUP":
                    String numeBazaDeDate = vec[1];
                    long timestamp = Long.parseLong(vec[2]);

                    //sterg toate instantele care au timestampul mai mic decat cel dat
                    bazaDeDate.cleanup(timestamp);
            }
        }

        //inchid fisierul de output
        writer.close();
    }

}
