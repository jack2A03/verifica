import java.util.ArrayList;

public class Farmaco {

    private int id = 0;
    private String nomeGenerico = null;
    private String nomeFarmaco = null;
    private String indicazioni = null;
    private String ditta = null;
    private int quantita = 0;
    private int scaffale = 0;
    private int cassetto = 0;

    public Farmaco() {
    }

    public Farmaco(int id, String nomeGenerico, String nomeFarmaco, String indicazioni, String ditta, int quantita, int scaffale, int cassetto) {
        this.id = id;
        this.nomeGenerico = nomeGenerico;
        this.nomeFarmaco = nomeFarmaco;
        this.indicazioni = indicazioni;
        this.ditta = ditta;
        this.quantita = quantita;
        this.scaffale = scaffale;
        this.cassetto = cassetto;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNomeGenerico() {
        return nomeGenerico;
    }

    public void setNomeGenerico(String nomeGenerico) {
        this.nomeGenerico = nomeGenerico;
    }

    public String getNomeFarmaco() {
        return nomeFarmaco;
    }

    public void setNomeFarmaco(String nomeFarmaco) {
        this.nomeFarmaco = nomeFarmaco;
    }

    public String getIndicazioni() {
        return indicazioni;
    }

    public void setIndicazioni(String indicazioni) {
        this.indicazioni = indicazioni;
    }

    public String getDitta() {
        return ditta;
    }

    public void setDitta(String ditta) {
        this.ditta = ditta;
    }

    public int getQuantita() {
        return quantita;
    }

    public void setQuantita(int quantita) {
        this.quantita = quantita;
    }

    public int getScaffale() {
        return scaffale;
    }

    public void setScaffale(int scaffale) {
        this.scaffale = scaffale;
    }

    public int getCassetto() {
        return cassetto;
    }

    public void setCassetto(int cassetto) {
        this.cassetto = cassetto;
    }

    @Override
    public String toString() {
        return "Farmaco{" +
                "id=" + id +
                ", nomeGenerico='" + nomeGenerico + '\'' +
                ", nomeFarmaco='" + nomeFarmaco + '\'' +
                ", indicazioni=" + indicazioni +
                ", ditta='" + ditta + '\'' +
                ", quantita=" + quantita +
                ", scaffale=" + scaffale +
                ", cassetto=" + cassetto +
                '}';
    }

    public String[] toRow() {
        String [] farmacoFields = {id+"",nomeGenerico,nomeFarmaco,indicazioni,ditta,quantita+"",scaffale+"",cassetto+""};
        return farmacoFields;
    }
}
