
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class App {
    public static void main(String[] args) {

        String caracteresParaBusca;
        Scanner sc = new Scanner(System.in);

        List<String> listaParaVerificarSignificados = new ArrayList<>();

        while (true) {
            // Criando variável para busca
            System.out.print("Digite caracteres para busca: ");
            caracteresParaBusca = sc.nextLine();
            while (caracteresParaBusca.length() < 3) {
                System.out.println("Digite no minimo 3 caracteres!");
                caracteresParaBusca = sc.nextLine();
            }
            SearchAndCount search = new SearchAndCount(caracteresParaBusca);
            System.out.println("");
            System.out.println("-------------------------------------------------");
            System.out.println("");
            System.out.println("Aguarde... A busca esta sendo feita na lista encadeada!");

            System.out.println("Busca realizada! foram encontradas " +
                    search.getListSize() + " palavra(s).");
            System.out.println("Palavras encontradas: ");

            for (Palavra palavra : search.getList()) {
                System.out.println(palavra.getPalavra());
                listaParaVerificarSignificados.add(palavra.getPalavra());
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

            for (Palavra palavra : search.getListArvore()) {
                System.out.println(palavra.getPalavra());
                listaParaVerificarSignificados.add(palavra.getPalavra());
            }

            System.out.println(
                    "Tempo para busca de palavras na arvore binaria de pesquisa: " + search.getTempoDeBuscaArvore()
                            + " nanosegundos.");
            System.out.println("");
            System.out.println("-------------------------------------------------");
            System.out.println("");

            String palavraParaSignificado;
            System.out.print("Escolha uma palavra da lista retornada: ");
            palavraParaSignificado = sc.nextLine();
            while (!listaParaVerificarSignificados.contains(palavraParaSignificado)) {
                System.out.print("Esta palavra nao esta na lista, por favor digite outra: ");
                palavraParaSignificado = sc.nextLine();
            }
            System.out.println("");
            System.out.println("-------------------------------------------------");
            System.out.println("");
            System.out.println("Significado buscado na estrutura lista! " + "Significado: "
                    + search.getSignificadoLista(palavraParaSignificado));
            System.out.println("Tempo de busca: " + search.getTempoDeBuscaListaSignificado() + " nanosegundos");
            System.out.println("");
            System.out.println("-------------------------------------------------");
            System.out.println("");
            System.out.println("Significado buscado na estrutura arvore! " + "Significado: "
                    + search.getSignificadoArvore(palavraParaSignificado));
            System.out.println("Tempo de busca: " + search.getTempoDeBuscaArvoreSignificado() + " nanosegundos");
            System.out.println("");
            System.out.println("-------------------------------------------------");
            System.out.println("");
            palavraParaSignificado = "";
            caracteresParaBusca = "";
        }
    }
}
