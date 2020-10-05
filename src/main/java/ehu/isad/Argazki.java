package ehu.isad;

public class Argazki {
    private String izena;
    private String argazkia;

    public Argazki(String pIzena,String pArgazkia){
        izena=pIzena;
        argazkia=pArgazkia;
    }

    public String getFitx(){
        return this.argazkia;
    }

    @Override
    public String toString() {
        return izena;
    }
}
