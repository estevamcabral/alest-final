
import java.util.Scanner;

public class App {
    public static void main(String[] args) {

        String caracteresParaBusca;
        Scanner sc = new Scanner(System.in);

        while (true) {
            // Criando variável para busca
            System.out.print("Digite caracteres para busca: ");
            caracteresParaBusca = sc.nextLine();
            while (caracteresParaBusca.length() < 3) {
                System.out.println("Digite no minimo 3 caracteres!");
                caracteresParaBusca = sc.nextLine();
            }
            SearchAndCount search = new SearchAndCount(caracteresParaBusca);
            System.out.println("Aguarde... A busca esta sendo feita na lista encadeada!");

            System.out.println("Busca realizada! foram encontradas " +
                    search.getListSize() + " palavra(s).");
            System.out.println("Palavras encontradas: ");

            for (Palavra palavra : search.getList()) {
                System.out.println(palavra.getPalavra() + " | " + palavra.getSignificado());
            }
            System.out.println("Tempo para busca de palavras na lista encadeada: " +
                    search.getTempoDeBuscaLista()
                    + " nanosegundos.");

            System.out.println("");
            System.out.println("-------------------------------------------------");
            System.out.println("");

            System.out.println("Aguarde... A busca esta sendo feita na arovere binaria de pesquisa!");

            System.out.println("Busca realizada! foram encontradas " + search.getArvoreSize() + " palavra(s).");
            if (search.getArvoreSize() > 0)
                System.out.println("Palavras encontradas: ");

            for (String palavra : search.getListArvore()) {
                System.out.println(palavra);
            }

            System.out.println(
                    "Tempo para busca de palavras na arvore binaria de pesquisa: " + search.getTempoDeBuscaArvore()
                            + " nanosegundos.");
            System.out.println("");
            System.out.println("-------------------------------------------------");
            System.out.println("");
            caracteresParaBusca = "";
        }
    }
}
