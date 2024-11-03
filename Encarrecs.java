import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;

public class Encarrecs implements Serializable {
    private static final long serialVersionUID = 1L;
    private int idEncarrec;
    private String nomClient;
    private int telClient;
    private LocalDate dataLliurament;
    private ArrayList<Article> articles;
    private double preuTotalEncarrec; // Canviat a double per a millor precisió

    public Encarrecs(String nomClient, int telClient, LocalDate dataLliurament, ArrayList<Article> articles) {
        this.nomClient = nomClient;
        this.telClient = telClient;
        this.dataLliurament = dataLliurament;
        this.articles = articles;
        this.preuTotalEncarrec = calcularTotalEncarrec(); // Opcional: calcular total a l'inici
    }
    public LocalDate getDataEncarrec() {
        return dataLliurament;
    }

    public ArrayList<Article> getArticles() {
        return articles;
    }

    public final double calcularTotalEncarrec() {
        double total = 0; // Inicialitzem a zero
        for (Article article : articles) {
            total += article.getTotalPrice();
        }
        return total;
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

    public LocalDate getDataLliurament() {
        return dataLliurament;
    }

    public void setDataLliurament(LocalDate dataLliurament) {
        this.dataLliurament = dataLliurament;
    }

    public void setArticles(ArrayList<Article> articles) {
        this.articles = articles;
        this.preuTotalEncarrec = calcularTotalEncarrec(); // Recalculant el preu total si els articles canvien
    }

    public double getPreuTotalEncarrec() {
        return preuTotalEncarrec; // Canviat a double
    }

    @Override
    public String toString() {
        return "Encarrec [idEncarrec=" + idEncarrec +
                ", nomClient=" + nomClient +
                ", telClient=" + telClient +
                ", dataEncarrec=" + dataLliurament +
                ", articles=" + articles +
                ", preuTotalEncarrec=" + preuTotalEncarrec + "]";
    }
}
