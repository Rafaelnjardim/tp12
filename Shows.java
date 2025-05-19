import java.util.Scanner;
import java.util.UUID;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.text.ParseException;
import java.util.Locale;

public class Shows {

    // Variáveis globais estáticas
    static SimpleDateFormat ddf = new SimpleDateFormat("MMMM d, yyyy", Locale.ENGLISH);
    public static ArrayList<Shows> allShows = new ArrayList<Shows>();

    // -----------------------------------------------------------------------------------------------------------------------------------------------------------------------------//
    // Atributos privados
    private String SHOW_ID;
    private String TYPE;
    private String TITLE;
    private String DIRECTOR;
    private String[] CAST;
    private String COUNTRY;
    private Date DATE_ADDED;
    private int RELEASE_YEAR;
    private String RATING;
    private String DURATION;
    private String[] LISTED_IN;

    // -----------------------------------------------------------------------------------------------------------------------------------------------------------------------------//
    // Construtor vazio
    public Shows() {
        this.SHOW_ID = "NaN";
        this.TYPE = "NaN";
        this.TITLE = "NaN";
        this.DIRECTOR = "NaN";
        this.COUNTRY = "NaN";
        this.DURATION = "NaN";
        try {
            this.DATE_ADDED = ddf.parse("March 1, 1900");
        } catch (ParseException e) {
            this.DATE_ADDED = null;
        }
        this.RELEASE_YEAR = 0;
        this.RATING = "NaN";
        this.CAST = new String[0];
        this.LISTED_IN = new String[0];
    }

    // -----------------------------------------------------------------------------------------------------------------------------------------------------------------------------//
    // Getters
    public String getSHOW_ID() { return this.SHOW_ID; }
    public String getTYPE() { return this.TYPE; }
    public String getTITLE() { return this.TITLE; }
    public String getDIRECTOR() { return this.DIRECTOR; }
    public String getCOUNTRY() { return this.COUNTRY; }
    public Date getDATE_ADDED() { return this.DATE_ADDED; }
    public int getRELEASE_YEAR() { return this.RELEASE_YEAR; }
    public String getRATING() { return this.RATING; }
    public String getDURATION() { return this.DURATION; }
    public String[] getCAST() { return this.CAST; }
    public String[] getLISTED_IN() { return this.LISTED_IN; }

    // -----------------------------------------------------------------------------------------------------------------------------------------------------------------------------//
    // Setters
    public void setSHOW_ID(String SHOW_ID) { this.SHOW_ID = SHOW_ID; }
    public void setTYPE(String TYPE) { this.TYPE = TYPE; }
    public void setTITLE(String TITLE) { this.TITLE = TITLE; }
    public void setDIRECTOR(String DIRECTOR) { this.DIRECTOR = DIRECTOR; }
    public void setCOUNTRY(String COUNTRY) { this.COUNTRY = COUNTRY; }
    public void setDATE_ADDED(Date DATE_ADDED) { this.DATE_ADDED = DATE_ADDED; }
    public void setRELEASE_YEAR(int RELEASE_YEAR) { this.RELEASE_YEAR = RELEASE_YEAR; }
    public void setRATING(String RATING) { this.RATING = RATING; }
    public void setDURATION(String DURATION) { this.DURATION = DURATION; }

    public void setCAST(String[] CAST) {
        String[] cleaned = cleanArray(CAST);
        Arrays.sort(cleaned);
        this.CAST = cleaned;
    }

    public void setLISTED_IN(String[] LISTED_IN) {
        String[] cleaned = cleanArray(LISTED_IN);
        Arrays.sort(cleaned);
        this.LISTED_IN = cleaned;
    }

    // -----------------------------------------------------------------------------------------------------------------------------------------------------------------------------//
    // Clone
    public Shows clone() {
        Shows clone = new Shows();
        clone.setSHOW_ID(this.SHOW_ID);
        clone.setTYPE(this.TYPE);
        clone.setTITLE(this.TITLE);
        clone.setDIRECTOR(this.DIRECTOR);
        clone.setCOUNTRY(this.COUNTRY);
        clone.setDATE_ADDED(this.DATE_ADDED);
        clone.setRELEASE_YEAR(this.RELEASE_YEAR);
        clone.setRATING(this.RATING);
        clone.setDURATION(this.DURATION);
        clone.setCAST(this.CAST.clone());
        clone.setLISTED_IN(this.LISTED_IN.clone());
        return clone;
    }

    //-------------------------------------------------------------------------------------------------------------------------------------------------------------------//
    // Lipa aspas
    private static String[] cleanArray(String[] arr) {
        String[] cleaned = new String[arr.length];
        for (int i = 0; i < arr.length; i++) {
            cleaned[i] = arr[i].replace("\"", "").trim();
        }
        return cleaned;
    }

    //-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------//
    // Print
    public void print() {
        System.out.print("=> "
            + this.getSHOW_ID() + " ## "
            + this.getTITLE().replace("\"", "") + " ## "
            + this.getTYPE().replace("\"", "") + " ## "
            + (this.getDIRECTOR().equals("NaN") ? "NaN" : this.getDIRECTOR().replace("\"", "")) + " ## "
            + (this.getCAST().length == 0 ? "[NaN]" : "[" + String.join(", ", this.getCAST()) + "]") + " ## "
            + this.getCOUNTRY().replace("\"", "") + " ## "
            + (this.getDATE_ADDED() == null ? "NaN" : ddf.format(this.getDATE_ADDED())) + " ## "
            + (this.getRELEASE_YEAR() == 0 ? "NaN" : this.getRELEASE_YEAR()) + " ## "
            + (this.getRATING().equals("NaN") ? "NaN" : this.getRATING()) + " ## "
            + this.getDURATION().replace("\"", "") + " ## "
            + (this.getLISTED_IN().length == 0 ? "[NaN]" : "[" + String.join(", ", this.getLISTED_IN()) + "]")
        );
        System.out.println(" ##");
    }

    // -----------------------------------------------------------------------------------------------------------------------------------------------------------------------------//
    // Método para carregar os dados do CSV
    public static void loadFromCSV(String filename) {
        String line = "";
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            br.readLine(); // pula cabeçalho
            while ((line = br.readLine()) != null) {
                String[] fields = line.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)", -1);

                String id = fields[0].isEmpty() ? "NaN" : fields[0];
                String type = fields[1].isEmpty() ? "NaN" : fields[1];
                String title = fields[2].isEmpty() ? "NaN" : fields[2];
                String director = fields[3].isEmpty() ? "NaN" : fields[3];
                String[] cast = fields[4].isEmpty() ? new String[0] : fields[4].split(", ");
                String country = fields[5].isEmpty() ? "NaN" : fields[5];

                String rawDate = fields[6].trim().replace("\"", "");
                Date dateAdded = rawDate.isEmpty() ? ddf.parse("March 1, 1900") : ddf.parse(rawDate);

                int releaseYear = fields[7].isEmpty() ? 0 : Integer.parseInt(fields[7]);
                String rating = fields[8].isEmpty() ? "NaN" : fields[8].replace("\"", "").trim();

                String duration = fields[9].isEmpty() ? "NaN" : fields[9];
                String[] listedIn = fields[10].isEmpty() ? new String[0] : fields[10].split(", ");

                Shows show = new Shows();
                show.setSHOW_ID(id);
                show.setTYPE(type);
                show.setTITLE(title);
                show.setDIRECTOR(director);
                show.setCOUNTRY(country);
                show.setDATE_ADDED(dateAdded);
                show.setRELEASE_YEAR(releaseYear);
                show.setRATING(rating);
                show.setDURATION(duration);
                show.setCAST(cast);
                show.setLISTED_IN(listedIn);

                allShows.add(show);
            }
        } catch (IOException | ParseException e) {
            System.err.println("Erro ao ler ou processar o CSV:");
            System.err.println("Linha problemática: " + line);
            e.printStackTrace();
        }
    }
    // -----------------------------------------------------------------------------------------------------------------------------------------------------------------------------//
    // Lista sequencial 0_0
    class Lista {
        private Shows[] array;
        private int n;

        public Lista () {
           this(6);
        }

        public Lista (int tamanho){
           array = new int[tamanho];
           n = 0;
        }
        // -----------------------------------------------------------------------------------------------------------------------------------------------------------------------------//
        //Métodos inserir $_$
        //Inicio
        public void inserirInicio(Shows show) throws Exception {
     
           //validar insercao
           if(n >= array.length){
              throw new Exception("Erro ao inserir!");
           } 
     
           //levar elementos para o fim do array
           for(int i = n; i > 0; i--){
              array[i] = array[i-1];
           }
     
           array[0] = show;
           n++;
        }
        //Fim
        public void inserirFim(Shows show) throws Exception {
     
           //validar insercao
           if(n >= array.length){
              throw new Exception("Erro ao inserir!");
           }
     
           array[n] = show;
           n++;
        }
        //Qualquer posição
        public void inserir(Shows show, int pos) throws Exception {
     
           //validar insercao
           if(n >= array.length || pos < 0 || pos > n){
              throw new Exception("Erro ao inserir!");
           }
     
           //levar elementos para o fim do array
           for(int i = n; i > pos; i--){
              array[i] = array[i-1];
           }
     
           array[pos] = show;
           n++;
        }
        // -----------------------------------------------------------------------------------------------------------------------------------------------------------------------------//
        //Métodos remover ^_^
        //Inicio
        public Shows removerInicio() throws Exception {

           if (n == 0) {
              throw new Exception("Erro ao remover!");
           }
     
           Shows resp = array[0];
           n--;
     
           for(int i = 0; i < n; i++){
              array[i] = array[i+1];
           }
     
           return resp;
        }
        //Fim
        public Shows removerFim() throws Exception {
     
           //Validar remocao
           if (n == 0) {
              throw new Exception("Erro ao remover!");
           }
     
           return array[--n];
        }
        //Qualquer posição
        public Shows remover(int pos) throws Exception {
     
           //validar remocao
           if (n == 0 || pos < 0 || pos >= n) {
              throw new Exception("Erro ao remover!");
           }
     
           Shows resp = array[pos];
           n--;
     
           for(int i = pos; i < n; i++){
              array[i] = array[i+1];
           }
     
           return resp;
        }
        // -----------------------------------------------------------------------------------------------------------------------------------------------------------------------------//
        //Print removidos x_x
        public void printR() {
            System.out.print("(R)"
                + this.getTITLE().replace("\"", "")
            );
        }

     }
    // -----------------------------------------------------------------------------------------------------------------------------------------------------------------------------//
    // Método main para entrada de SHOW_ID e impressão dos dados correspondentes
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        //Shows.loadFromCSV("C:\\Users\\rafae\\Downloads\\Disney\\disneyplus.csv");
        Shows.loadFromCSV("/tmp/disneyplus.csv");

        String input = "";
        int tamanho=50;
        Lista(tamanho);
        int i=0;
        int NI=0;
        String Inst;
        while (!input.equalsIgnoreCase("FIM")) {
            input = sc.nextLine().trim();
            Lista[i]=input;

            if (!input.equalsIgnoreCase("FIM")) {
                boolean found = false;

                for (Shows show : allShows) {
                    if (show.getSHOW_ID().equals(input)) {
                        show.print();
                        found = true;
                    }
                }

                if (!found) {
                    System.out.println("SHOW_ID não encontrado.");
                }
            }
        }

        sc.close();
    }
}
