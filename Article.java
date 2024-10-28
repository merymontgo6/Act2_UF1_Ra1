import java.io.Serializable;

public class Articles implements Serializable{
    private static final long serialVersionUID = 1L;  // Serial version UID per a garantir la compatibilitat de la classe amb versions anteriors
    private String nomArticle;
    private int quantitat;
    private String unitats;

    public Articles(String nomArticle, int quantitat, String unitats) {
        this.nomArticle = nomArticle;
        this.quantitat = quantitat;
        this.unitats = unitats;
    }
    
    public String getNomArticle() {
        return nomArticle;
    }

    public int getQuantitat() {
        return quantitat;
    }

    public String getUnitats() {
        return unitats;
    }

    public void setNomArticle(String nomArticle) {
        this.nomArticle = nomArticle;
    }

    public void setQuantitat(int quantitat) {
        this.quantitat = quantitat;
    }

    public void setUnitats(String unitats) {
        this.unitats = unitats;
    }

    @Override
    public String toString() {
        return "Article: " + nomArticle + ", Quantitat: " + quantitat + " " + unitats;
    }
    
    public String toCSV() {
        return nomArticle + ";" + quantitat + ";" + unitats + ";";
    }
}
