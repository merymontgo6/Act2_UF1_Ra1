import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class Botiga {
    private static ArrayList<Client> encarrecs = new ArrayList<>(); // Llista de clients amb els seus encàrrecs
    private static File dir = new File ("C:\\Users\\karolayn\\DAM\\M06\\Act1_UF1_Ra1\\fitxers"); //path

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

    public static Integer infoClient(BufferedReader reader) throws IOException { 
        boolean afegirMesEncarrecs = true;
        
         // s'introdueix tota la informació del client
        System.out.println("Introdueix el nom del client:");
        String nomClient = reader.readLine();
        System.out.println("Introdueix el telèfon del client:");
        Integer telClient = Integer.parseInt(reader.readLine());

        System.out.println("Introdueix la data (dd/MM/aaaa):");
        String dataEncarrec = reader.readLine();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate dataLliurament = LocalDate.parse(dataEncarrec, formatter);
        
        ArrayList<Articles> articles = demanarArticles(reader); //array per afegir articles

        Client nouClient = new Client(nomClient, telClient, dataLliurament, articles);
        encarrecs.add(nouClient);
        System.out.println("Encàrrec generat amb èxit!");
        System.out.println("Voleu afegir més Encarrecs? (si/no)");
            String resposta = reader.readLine();
            if (!resposta.equalsIgnoreCase("si")) {
                afegirMesEncarrecs = false;
            }

        System.out.println("Com es vol generar el fitxer?\n1. Generar un fitxer de text amb format albarà\n2. Generar un fitxer de text csv\n3. Generar un fitxer binari");
        int opcio = Integer.parseInt(reader.readLine());
        switch (opcio) {
            case 1:
                generarFitxerAlbara(nouClient);
                break;
            case 2:
                generarFitxerCSV(nouClient);
                break;
            case 3:
                generarFitxerBinari(nouClient, dataEncarrec, articles);
                break;
            default:
                System.out.println("Opció no vàlida. Si us plau, tria una opció correcta.");
                break;
        }
        return opcio;
    }
    

    public static ArrayList<Articles> demanarArticles(BufferedReader reader) throws IOException {
        ArrayList<Articles> articles = new ArrayList<>();
        boolean afegirMesArticles = true; //bool per saber si l'usuari vol afegir més articles

        while (afegirMesArticles) {
            System.out.println("Introdueix el nom de l'article:");
            String nomArticle = reader.readLine();
            System.out.println("Introdueix la quantitat:");
            int quantitat = Integer.parseInt(reader.readLine());
            System.out.println("Introdueix les unitats:");
            String unitats = reader.readLine();
            Articles nouArticle = new Articles(nomArticle, quantitat, unitats);
            articles.add(nouArticle);

            System.out.println("Voleu afegir més articles? (si/no)");
            String resposta = reader.readLine();
            if (!resposta.equalsIgnoreCase("si")) {
                afegirMesArticles = false;
            }
        }
        return articles;
    }

    public static void generarFitxerAlbara(Client client)  throws IOException {
        String nomCli = client.getNomClient().replace(" ", "_");
        // path on es guardarà els fitxers
        String fileName = "C:\\Users\\karolayn\\DAM\\M06\\Act1_UF1_Ra1\\fitxers\\encarrecs_albara_client_" + nomCli + "_" + System.currentTimeMillis() + ".txt";
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            // bufferwritter per recollir les dades i que es guarden dins del fitxer
            writer.write("Nom del client: " + client.getNomClient());
            writer.newLine();
            writer.write("Telefon del client: " + client.getTelClient());
            writer.newLine();
            
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            writer.write("Data de l'encarrec: " + client.getDataEncarrec().format(formatter));
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
    }

    public static void generarFitxerCSV(Client client) throws IOException {
    String nomCli = client.getNomClient().replace(" ", "_");
    String fileName = "C:\\Users\\karolayn\\DAM\\M06\\Act1_UF1_Ra1\\fitxers\\encarrecs_csv_client_" + nomCli + "_" + System.currentTimeMillis() + ".csv";
    
    try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
        // Escribir la cabecera del archivo CSV
        writer.write("Nom Client;Telèfon Client;Data Encarrec;Quantitat;Unitats;Article");
        writer.newLine();

        // Formatear la fecha
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        // Escribir los artículos
        for (Articles article : client.getArticles()) {
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
}

    public static void generarFitxerBinari(Client client, String dataEncarrec, ArrayList<Articles> articles) throws IOException {
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
        for (Articles article : articles) {
            str1.writeUTF(article.getNomArticle());
            str1.writeInt(article.getQuantitat());
            str1.writeUTF(article.getUnitats());
            }
        } catch (FileNotFoundException e) {
            System.out.println("Error al generar el fitxer binari.");
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("Error al generar el fitxer binari: " + e.getMessage());
        }

    }

    public static void mostrarEncarrecs(BufferedReader reader) throws IOException {
        System.out.println("Com es vol obrir el fitxer?\n1. Fitxer de text CSV\n2. Fitxer binari");
        String option = reader.readLine();
        int opcio = Integer.parseInt(option);

        // Pregunta la ruta del fitxer
        System.out.println("Introdueix la ruta (path) del fitxer:");
        String filePath = reader.readLine();

        // Mostrar contingut en funció de l'opció escollida
        switch (opcio) {
            case 1:
                mostrarEncarrecCSV(filePath);
                break;
            case 2:
                mostrarEncarrecBinari(filePath);
                break;
            default:
                System.out.println("Opció no vàlida. Si us plau, tria una opció correcta.");
                break;
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

    public static void SerEnc(String[] args) {
        Encarrecs[] encarrecs = new Encarrecs[1000];
        
        Articles[] articles = new Articles[1000];
        articles[0] = new Articles("Arroz", 10, "kg");
        articles[1] = new Articles("Llet", 2, "kg");
        ObjectOutputStream serializador = null;

        try {
            serializador = new ObjectOutputStream(new FileOutputStream("EncarrecesSer.dat"));
            serializador.writeObject(articles);
            serializador.close();
        } catch (Exception e) {
            System.out.print(e.getMessage());
        }
    }

    public static void DerEnc(String[] args) {

        ObjectInputStream deserialitzador = null;
        try {
            deserialitzador = new ObjectInputStream(new FileInputStream("EncarrecesDer.dat"));
            Encarrecs[] listaEncarrecs = (Encarrecs[]) deserialitzador.readObject();

            for(Encarrecs enc:listaEncarrecs) {
                System.out.println(enc);
        }
        deserialitzador.close();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}