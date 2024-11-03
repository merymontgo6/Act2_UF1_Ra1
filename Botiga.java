import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.RandomAccessFile;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class Botiga {
    private static ArrayList<Encarrecs> encarrecs = new ArrayList<>(); // Llista de clients amb els seus encàrrecs
    private static File dir = new File ("C:\\Users\\karolayn\\DAM\\M06\\Act2_UF1_Ra1\\fitxers2"); //path

    public static void main(String[] args) throws Exception {
        
        boolean sortir = false;

        while (!sortir) {
             System.out.println("Què vols fer?\n1. Generar un nou encàrrec\n2. Mostrar encàrrec\n3. Sortir");
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            try{
               
                String option = reader.readLine();
                int opcio = Integer.parseInt(option);

                switch (opcio) {
                    case 1: 
                        infoClient(reader);
                        break;
                    case 2: 
                        mostrarEncarrecs(reader);
                        break;
                    case 3: 
                        sortir = true;
                        break;
                    default: 
                        System.out.println("Opció no vàlida. Si us plau, tria una opció correcta.");
                        break;
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public static void infoClient(BufferedReader reader) throws IOException {
        boolean afegirMesEncarrecs;
    
        do {
            // S'introdueix tota la informació del client
            System.out.println("Introdueix el nom del client:");
            String nomClient = reader.readLine();
            System.out.println("Introdueix el telèfon del client:");
            Integer telClient = Integer.parseInt(reader.readLine());
    
            System.out.println("Introdueix la data (dd/MM/yyyy):");
            String dataEncarrec = reader.readLine();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            LocalDate dataLliurament = LocalDate.parse(dataEncarrec, formatter);
    
            ArrayList<Article> articles = demanarArticles(reader); // Array per afegir articles
    
            Encarrecs nouEncarrec = new Encarrecs(nomClient, telClient, dataLliurament, articles);

            encarrecs.add(nouEncarrec);
            System.out.println("Encàrrec generat amb èxit!");
    
            // Pregunta si es vol afegir més encàrrecs
            System.out.println("Voleu afegir més Encarrecs? (si/no)");
            String resposta = reader.readLine();
            afegirMesEncarrecs = resposta.equalsIgnoreCase("si");
    
        } while (afegirMesEncarrecs);
    
        // Opcions per generar el fitxer després de finalitzar tots els encàrrecs
        System.out.println("Com es vol generar el fitxer?\n1. Generar un fitxer de text amb format albarà\n2. Generar un fitxer de text csv\n3. Generar un fitxer binari\n4. Generar Serial de Encarrecs\n5.Generar fitxer aleatori");
        int opcio = Integer.parseInt(reader.readLine());
        //Client clientFinal = encarrecs.get(encarrecs.size() - 1); // Obtenim l'últim encàrrec afegit
    
        switch (opcio) {
            case 1 -> { //generarFitxerAlbara(clientFinal);
            }
            case 2 -> {//enerarFitxerCSV(clientFinal);
            }
            case 3 -> {
                //generarFitxerBinari(clientFinal);
            }
            case 4 -> { generarSerEnc(encarrecs);
            }
            case 5 -> { generarFitxerAleatori(encarrecs);
            }
            default -> System.out.println("Opció no vàlida. Si us plau, tria una opció correcta.");
            }
        }

    public static ArrayList<Article> demanarArticles(BufferedReader reader) throws IOException {
        ArrayList<Article> articles = new ArrayList<>();
        boolean afegirMesArticles = true; //bool per saber si l'usuari vol afegir més articles

        while (afegirMesArticles) {
            System.out.println("Introdueix el nom de l'article:");
            String nomArticle = reader.readLine();
            System.out.println("Introdueix la quantitat:");
            double quantitat = Double.parseDouble(reader.readLine());
            System.out.println("Introdueix les unitats:");
            String unitats = reader.readLine();
            System.out.println("Introdueix el preu unitari:");
            double unitPrice = Double.parseDouble(reader.readLine());
            Article nouArticle = new Article(nomArticle, quantitat, unitats, unitPrice);
            articles.add(nouArticle);

            System.out.println("Voleu afegir més articles? (si/no)");
            String resposta = reader.readLine();
            if (!resposta.equalsIgnoreCase("si")) {
                afegirMesArticles = false;
            }
        }
        return articles;
    }

    /*public static void generarFitxerAlbara(Encarrecs encarrecs)  throws IOException {
        String nomCli = encarrecs.getNomClient().replace(" ", "_");
        // path on es guardarà els fitxers
        String fileName = "C:\\Users\\karolayn\\DAM\\M06\\Act1_UF1_Ra1\\fitxers\\encarrecs_albara_client_" + nomCli + "_" + System.currentTimeMillis() + ".txt";
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            // bufferwritter per recollir les dades i que es guarden dins del fitxer
            writer.write("Nom del client: " + encarrecs.getNomClient());
            writer.newLine();
            writer.write("Telefon del client: " + encarrecs.getTelClient());
            writer.newLine();
            
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            writer.write("Data de l'encarrec: " + encarrecs.getDataEncarrec().format(formatter));
            writer.newLine();
            writer.newLine();
            
            writer.write("Quantitat\tUnitats\t\tArticle");
            writer.newLine();
            writer.write("=============== ========== ===============");
            writer.newLine();

            for (Articles article : client.getArticles()) {
                writer.write(String.format("%-15.1f %-10s %-15s", (float) article.getQuantitat(), article.getUnitats(), article.getNomArticle()));
                writer.newLine();
            }
            writer.newLine();
            System.out.println("Albarà generat correctament.");
        } catch (IOException e) {
            System.out.println("Error al generar l'albarà.");
        }
    }*/

    /*public static void generarFitxerCSV(Client client) throws IOException {
    String nomCli = client.getNomClient().replace(" ", "_");
    String fileName = "C:\\Users\\karolayn\\DAM\\M06\\Act1_UF1_Ra1\\fitxers\\encarrecs_csv_client_" + nomCli + "_" + System.currentTimeMillis() + ".csv";
    
    try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
        // Escribir la cabecera del archivo CSV
        writer.write("Nom Client;Telèfon Client;Data Encarrec;Quantitat;Unitats;Article");
        writer.newLine();

        // Formatear la fecha
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        // Escribir los artículos
        for (Article article : client.getArticles()) {
            writer.write(client.getNomClient() + ";" + 
                         client.getTelClient() + ";" + 
                         client.getDataEncarrec().format(formatter) + ";" +
                         article.getQuantitat() + ";" + 
                         article.getUnitats() + ";" + 
                         article.getNomArticle() + ";");
            writer.newLine(); // Añadir nueva línea después de cada artículo
        }

        System.out.println("Fitxer CSV generat correctament.");
    } catch (IOException e) {
        System.out.println("Error al generar el fitxer CSV.");
        e.printStackTrace(); // Es útil imprimir la traza de error para depuración
    }
}*/

    /*public static void generarFitxerBinari(Client client, String dataEncarrec, ArrayList<Article> articles) throws IOException {
        String nomCli = client.getNomClient().replace(" ", "_");
        String fileName = "C:\\Users\\karolayn\\DAM\\M06\\Act1_UF1_Ra1\\fitxers\\encarrecs_binari_client_" + nomCli + "_" + System.currentTimeMillis() + ".dat";
        
       try (DataOutputStream str1 = new DataOutputStream(new FileOutputStream(fileName))) {
        // Escribir la información del cliente
        str1.writeUTF(client.getNomClient());
        str1.writeInt(client.getTelClient());
        str1.writeUTF(dataEncarrec);
        
        // Escribir el número de artículos
        str1.writeInt(articles.size());

        // Escribir cada artículo
        for (Article article : articles) {
            str1.writeUTF(article.getNomArticle());
            str1.writeInt((int) article.getQuantitat());
            str1.writeUTF(article.getUnitats());
            }
        } catch (FileNotFoundException e) {
            System.out.println("Error al generar el fitxer binari.");
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("Error al generar el fitxer binari: " + e.getMessage());
        }

    }*/

    public static void generarSerEnc(ArrayList<Encarrecs> encarrecs) {
        try (ObjectOutputStream serializador = new ObjectOutputStream(new FileOutputStream(dir + "\\encarrec_" + System.currentTimeMillis() + ".bin"))) {
            serializador.writeObject(encarrecs); // Guarda tot l'ArrayList de encàrrecs
            System.out.println("Encàrrecs serialitzats correctament.");
        } catch (IOException e) {
            System.out.println("Error durant la serialització dels encàrrecs: " + e.getMessage());
        }
    }

    public static void generarFitxerAleatori(ArrayList<Encarrecs> encarrecs) throws FileNotFoundException, IOException {
        RandomAccessFile raw1 = new RandomAccessFile(dir + "\\encarrec_" + System.currentTimeMillis() + ".txt", "rw");
        for (Encarrecs encarrec:encarrecs){

            int longRecord = 0;
            StringBuffer sbf1 = null;

            sbf1 = new StringBuffer(encarrec.getNomClient());
            sbf1.setLength(50);
            raw1.writeChars(sbf1.toString());
            //2 bytes per cada char escrit.
            longRecord += sbf1.toString().length() * 2; 
            // resta de codi a implementar, on anirem repetint i afegint les longituds de cada //camp
            //al final escrivim la longitud
            sbf1 = new StringBuffer(String.valueOf(encarrec.getTelClient()));
            sbf1.setLength(10);
            raw1.writeChars(sbf1.toString());
            longRecord += sbf1.toString().length() * 2;

            sbf1 = new StringBuffer(encarrec.getDataLliurament().toString());
            sbf1.setLength(10);
            raw1.writeChars(sbf1.toString());
            longRecord += sbf1.toString().length() * 2;

            sbf1 = new StringBuffer(String.valueOf(encarrec.getPreuTotalEncarrec()));
            raw1.writeChars(sbf1.toString());
            longRecord += sbf1.toString().length() * 2;

           raw1.writeInt(longRecord);
       }

    }

    public static void mostrarEncarrecs(BufferedReader reader) throws IOException {
        System.out.println("Com es vol obrir el fitxer?\n1. Fitxer de text CSV\n2. Fitxer binari\n3. Fitxer de text serializat");
        String option = reader.readLine();
        int opcio = Integer.parseInt(option);

        // Pregunta la ruta del fitxer
        System.out.println("Introdueix la ruta (path) del fitxer:");
        String filePath = reader.readLine();

        // Mostrar contingut en funció de l'opció escollida
        switch (opcio) {
            case 1 -> mostrarEncarrecCSV(filePath);
            case 2 -> mostrarEncarrecBinari(filePath);
            case 3 -> mostrarDerEnc(filePath);
            default -> System.out.println("Opció no vàlida. Si us plau, tria una opció correcta.");
        }
    }

    public static void mostrarEncarrecCSV(String filePath) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            // Llegeix la informació del client
            String line = reader.readLine(); // Ignorar la capçalera
            String[] dadesClient = reader.readLine().split(";");
            String nomClient = dadesClient[0];
            String telClient = dadesClient[1];
            String dataEncarrec = dadesClient[2];

            System.out.println("Nom del client: " + nomClient);
            System.out.println("Telefon del client: " + telClient);
            System.out.println("Data de l'encarrec: " + dataEncarrec);
            System.out.println("Quantitat\tUnitats\t\tArticle");
            System.out.println("=============== ========== ===============");

            // Llegeix els articles
            while ((line = reader.readLine()) != null) {
            String[] datos = line.split(";");
            if (datos.length >= 3) {
                String quantitat = datos[3];
                String unitats = datos[4];
                String nomArticle = datos[5];

                // Mostrar los artículos leídos
                System.out.printf("%-15s %-10s %-15s%n", quantitat, unitats, nomArticle);
            }
        }
        } catch (IOException e) {
            System.out.println("Error al llegir el fitxer CSV.");
            e.printStackTrace();
        }
    }


    public static void mostrarEncarrecBinari(String filePath) {
        try (DataInputStream dis = new DataInputStream(new FileInputStream(filePath))) {
            // Leer la información del cliente
            String nomClient = dis.readUTF();
            int telClient = dis.readInt();
            String dataEncarrec = dis.readUTF();

            System.out.println("Nom del client: " + nomClient);
            System.out.println("Telefon del client: " + telClient);
            System.out.println("Data de l'encarrec: " + dataEncarrec);
            System.out.println();
            System.out.println("Quantitat\tUnitats\t\tArticle");

            // Leer el número de artículos
            int numArticles = dis.readInt();
            for (int i = 0; i < numArticles; i++) {
                String nomArticle = dis.readUTF();
                int quantitat = dis.readInt();
                String unitats = dis.readUTF();
                
                System.out.printf("%-15d %-10s %-15s%n", quantitat, unitats, nomArticle);
            }
        } catch (IOException e) {
            System.out.println("Error al llegir el fitxer binari: " + e.getMessage());
            e.printStackTrace();
        }
    }
    
    @SuppressWarnings("unchecked")
    public static void mostrarDerEnc(String filePath) {
        try (ObjectInputStream deserialitzador = new ObjectInputStream(new FileInputStream(filePath))) {
            encarrecs = (ArrayList<Encarrecs>) deserialitzador.readObject(); // Carrega l'ArrayList de encàrrecs
            System.out.println("Encàrrecs deserialitzats correctament.");
            
            // Mostra els encàrrecs carregats
            for (Encarrecs enc : encarrecs) {
                System.out.println(enc);
            }
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error durant la deserialització dels encàrrecs: " + e.getMessage());
        }
    }
}