import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.LinkedList;

public class SearchAndCount {

    private String caracteresParaBusca;

    private LinkedList<Palavra> listaParaBusca = new LinkedList<>();
    private LinkedList<Palavra> listaAux = new LinkedList<>();
    private long tempoDeBuscaLista;

    public SearchAndCount(String caracteres) {
        this.caracteresParaBusca = caracteres;
        this.tempoDeBuscaLista = 0;
        lerArquivoParaLista();
        buscarPalavrasNaLista();
    }

    public void lerArquivoParaLista() {
        Path path1 = Paths.get("dicionario.csv");

        try (BufferedReader reader = Files.newBufferedReader(path1, Charset.forName("UTF-8"))) {
            String line = reader.readLine();
            while (line != null) {
                String[] aux = line.split(";");
                Palavra p = new Palavra(aux[0], aux[1]);
                listaParaBusca.add(p);
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

    public int getListSize() {
        return listaAux.size();
    }

    public LinkedList<Palavra> getList() {
        return listaAux;
    }

    public long getTempoDeBuscaLista() {
        return tempoDeBuscaLista;
    }

}
