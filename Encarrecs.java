import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;

public class Encarrecs implements Serializable{
    private static final long serialVersionUID = 1L;
    private int idEncarrec;
    private String nomClient;
    private int telClient;
    private LocalDate dataEncarrec;
    private ArrayList<Articles> articles;
    private int preuTotalEncarrec;
    
    public Encarrecs() {}
    
    public Encarrecs(Client client, ArrayList<Articles> articles) {
        this.nomClient = client.getNomClient();
        this.telClient = client.getTelClient();
        this.dataEncarrec = client.getDataEncarrec();
        this.articles = articles;
    }
    
    public int getIdEncarrec() {
        return idEncarrec;
    }

    public void setIdEncarrec(int idEncarrec) {
        this.idEncarrec = idEncarrec;
    }
    public String getNomClient() {
        return nomClient;
    }

    public void setNomClient(String nomClient) {
        this.nomClient = nomClient;
    }
    public int getTelClient() {
        return telClient;
    }

    public void setTelClient(int telClient) {
        this.telClient = telClient;
    }

    public LocalDate getDataEncarrec() {
        return dataEncarrec;
    }

    public void setDataEncarrec(LocalDate dataEncarrec) {
        this.dataEncarrec = dataEncarrec;
    }

    // Getter y Setter para articles
    public ArrayList<Articles> getArticles() {
        return articles;
    }

    public void setArticles(ArrayList<Articles> articles) {
        this.articles = articles;
    }

    public int getPreuTotalEncarrec() {
        return preuTotalEncarrec;
    }

    public void setPreuTotalEncarrec(int preuTotalEncarrec) {
        this.preuTotalEncarrec = preuTotalEncarrec;
    }
    
    @Override
    public String toString() {
        return "Encarrec [client=" + client.getNomClient() + ", articles=" + articles.toString();
    }
}
