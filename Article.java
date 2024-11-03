import java.io.Serializable;

public class Article implements Serializable {
    private static final long serialVersionUID = 1L;
    private String nomArticle;
    private double quantitat;
    private String unitats;
    private double preu;

    public Article(String nomArticle, double quantitat, String unitats, double preu) {
        this.nomArticle = nomArticle;
        this.quantitat = quantitat;
        this.unitats = unitats;
        this.preu = preu;
    }

    public String getNomArticle() {
        return nomArticle;
    }

    public double getQuantitat() {
        return quantitat;
    }

    public String getUnitats() {
        return unitats;
    }

    public void setNomArticle(String nomArticle) {
        this.nomArticle = nomArticle;
    }

    public void setQuantitat(double quantitat) {
        this.quantitat = quantitat;
    }

    public void setUnitats(String unitats) {
        this.unitats = unitats;
    }

    public double getPreu() {
        return preu;
    }

    public double getTotalPrice() {
        return quantitat * preu;
    }

    @Override
    public String toString() {
        return "Article: " + nomArticle + ", Quantitat: " + quantitat + " " + unitats + ", Preu unitari: " + preu;
    }

    public String toCSV() {
        return nomArticle + ";" + quantitat + ";" + unitats + ";" + preu + ";";
    }
}