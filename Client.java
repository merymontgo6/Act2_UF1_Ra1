import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;

public class Client implements Serializable{
    private static final long serialVersionUID = 1L;  // Identificador per serialitzar la classe.

    private String nomClient;
    private Integer telClient;
    private LocalDate dataEncarrec;
    private ArrayList<Article> articles;

    public Client(String nomClient, Integer telClient, LocalDate dataEncarrec, ArrayList<Article> articles) {
        this.nomClient = nomClient;
        this.telClient = telClient;
        this.dataEncarrec = dataEncarrec;
        this.articles = articles;
    }

    public String getNomClient() {
        return nomClient;
    }

    public Integer getTelClient() {
        return telClient;
    }

    public LocalDate getDataEncarrec() {
        return dataEncarrec;
    }

    public ArrayList<Article> getArticles() {
        return articles;
    }

    public void setNomArticle(String nomClient) {
        this.nomClient = nomClient;
    }

    public void settelClient(Integer telClient) {
        this.telClient = telClient;
    }

    public void setDataEncarrec(LocalDate dataEncarrec) {
        this.dataEncarrec = dataEncarrec;
    }

    public void setArticles(ArrayList articles) {
        this.articles = articles;
    }
    @Override
    public String toString() {
    return "Nom del client: " + nomClient + ", Telèfon: " + telClient + ", Data de lliurament: " + dataEncarrec + ", Articles: " + articles;
    }
}
