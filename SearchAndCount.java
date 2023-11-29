import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class SearchAndCount {

    private String caracteresParaBusca;

    private LinkedList<Palavra> listaParaBusca = new LinkedList<>();
    private LinkedList<Palavra> listaAux = new LinkedList<>();
    private long tempoDeBuscaLista;
    private long tempoDeBuscaListaSignificado;

    private WordTree arvoreDePesquisa = new WordTree();
    private List<Palavra> listaArvoreAux = new ArrayList<>();
    private long tempoDeBuscaArvore;
    private long tempoDeBuscaArvoreSignificado;

    public long getTempoDeBuscaArvoreSignificado() {
        return tempoDeBuscaArvoreSignificado;
    }

    public long getTempoDeBuscaListaSignificado() {
        return tempoDeBuscaListaSignificado;
    }

    public SearchAndCount(String caracteres) {
        this.caracteresParaBusca = caracteres;
        this.tempoDeBuscaLista = 0;
        this.tempoDeBuscaArvore = 0;
        this.tempoDeBuscaArvoreSignificado = 0;
        this.tempoDeBuscaListaSignificado = 0;
        this.lerArquivo();
        buscarPalavrasNaLista();
        buscarPalavrasNaArvore();
    }

    public void lerArquivo() {
        Path path1 = Paths.get("dicionario.csv");

        try (BufferedReader reader = Files.newBufferedReader(path1, Charset.forName("UTF-8"))) {
            String line = reader.readLine();
            while (line != null) {
                String[] aux = line.split(";");
                Palavra p = new Palavra(aux[0], aux[1]);
                listaParaBusca.add(p);
                arvoreDePesquisa.addWord(p);
                line = reader.readLine();
            }
        } catch (IOException e) {
            System.err.format("Erro na leitura do arquivo: %s%n", e);
        }
    }

    public void buscarPalavrasNaLista() {
        long inicio = System.nanoTime(); // Marca o início da execução
        for (Palavra p : listaParaBusca) {
            if (p.getPalavra().toLowerCase().startsWith(caracteresParaBusca.toLowerCase()))
                listaAux.add(p);
        }
        long fim = System.nanoTime(); // Marca o fim da execução
        long intervalo = fim - inicio;
        this.tempoDeBuscaLista = intervalo;
    }

    public void buscarPalavrasNaArvore() {
        long inicio = System.nanoTime(); // Marca o início da execução
        this.listaArvoreAux = arvoreDePesquisa.searchAll(caracteresParaBusca.toLowerCase());
        long fim = System.nanoTime(); // Marca o fim da execução
        long intervalo = fim - inicio;
        this.tempoDeBuscaArvore = intervalo;
    }

    public int getListSize() {
        return listaAux.size();
    }

    public LinkedList<Palavra> getList() {
        return listaAux;
    }

    public long getTempoDeBuscaLista() {
        return tempoDeBuscaLista;
    }

    public long getTempoDeBuscaArvore() {
        return tempoDeBuscaArvore;
    }

    public List<Palavra> getListArvore() {
        return listaArvoreAux;
    }

    public int getArvoreSize() {
        return listaArvoreAux.size();
    }

    public String getSignificadoLista(String palavra) {
        String significado = "";
        long inicio = 0;
        long fim = 0;
        long intervalo = 0;
        inicio = System.nanoTime();
        for (Palavra p : listaParaBusca) {
            if (p.getPalavra().equals(palavra)) {
                fim = System.nanoTime();
                significado = p.getSignificado();
            }
            intervalo = fim - inicio;
            this.tempoDeBuscaListaSignificado = intervalo;
        }
        return significado;
    }

    public String getSignificadoArvore(String palavra) {
        String significado = "";
        long inicio = System.nanoTime(); // Marca o início da execução
        significado = arvoreDePesquisa.getSignificado(palavra);
        long fim = System.nanoTime(); // Marca o fim da execução\
        long intervalo = fim - inicio;
        this.tempoDeBuscaArvoreSignificado = intervalo;
        return significado;
    }
}
