import java.io.Serializable;

public class Article implements Serializable{
    private static final long serialVersionUID = 1L;  // Serial version UID per a garantir la compatibilitat de la classe amb versions anteriors
    private String nomArticle;
    private double quantitat;
    private String unitats;
    private double unitPrice;

    public Article(String nomArticle, double quantitat, String unitats, double unitPrice) {
        this.nomArticle = nomArticle;
        this.quantitat = quantitat;
        this.unitats = unitats;
        this.unitPrice = unitPrice;
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

    public double getUnitPrice() {
        return unitPrice;
    }

    public double getTotalPrice() {
        return quantitat * unitPrice;
    }

    @Override
    public String toString() {
        return "Article: " + nomArticle + ", Quantitat: " + quantitat + " " + unitats;
    }
    
    public String toCSV() {
        return nomArticle + ";" + quantitat + ";" + unitats + ";";
    }
}
