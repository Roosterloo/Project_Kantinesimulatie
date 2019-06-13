public class Student extends Persoon{

    private int studentnummer;
    private String studierichting;

    public Student(int BSN, String voornaam, String achternaam, Datum geboortedatum, char geslacht, int studentnummer, String studierichting){
        super(BSN, voornaam, achternaam, geboortedatum, geslacht);
        this.studentnummer = studentnummer;
        this.studierichting = studierichting;
    }


    public void setStudentnummer(int studentnummer) {
        this.studentnummer = studentnummer;
    }

    public void setStudierichting(String studierichting) {
        this.studierichting = studierichting;
    }

    public int getStudentnummer() {
        return studentnummer;
    }

    public String getStudierichting() {
        return studierichting;
    }
}