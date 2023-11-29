
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Queue;

public class WordTree {

    // Classe interna
    private class CharNode {
        private char character;
        private String significado;
        private boolean isFinal;
        private CharNode father;
        private List<CharNode> children;

        public CharNode(char character) {
            this.character = character;
            this.significado = null;
            this.isFinal = false;
            this.father = null;
            this.children = new ArrayList<>();
        }

        public CharNode(char character, boolean isFinal) {
            this.character = character;
            this.significado = null;
            this.isFinal = isFinal;
            this.father = null;
            this.children = new ArrayList<>();
        }

        /**
         * Adiciona um filho (caracter) no nodo. Não pode aceitar caracteres repetidos.
         * 
         * @param character - caracter a ser adicionado
         * @param isfinal   - se é final da palavra ou não
         */
        public CharNode addChild(char character, boolean isfinal) {
            for (CharNode child : children) {
                if (child.character == character) {
                    return child;
                }
            }
            CharNode newChild = new CharNode(character, isfinal);
            newChild.father = this;
            children.add(newChild);
            return newChild;
        }

        public int getNumberOfChildren() {
            return children.size();
        }

        public CharNode getChild(int index) {
            return children.get(index);
        }

        /**
         * Obtém a palavra correspondente a este nodo, subindo até a raiz da árvore
         * 
         * @return a palavra
         */
        private String getWord() {
            if (father == null) {
                return String.valueOf(character);
            }
            return father.getWord() + character;
        }

        /**
         * Encontra e retorna o nodo que tem determinado caracter.
         * 
         * @param character - caracter a ser encontrado.
         */
        public CharNode findChildChar(char character) {
            for (CharNode child : children) {
                if (Character.toLowerCase(child.character) == Character.toLowerCase(character)) {
                    return child;
                }
            }
            return null;
        }

    }

    // Atributos
    private CharNode root;
    private int totalNodes = 0;
    private int totalWords = 0;

    // Construtor
    public WordTree() {
        root = null;
    }

    // Metodos
    public int getTotalWords() {
        return totalWords;
    }

    public int getTotalNodes() {
        return totalNodes;
    }

    /**
     * Adiciona palavra na estrutura em árvore
     * 
     * @param word
     */
    public void addWord(Palavra word) {
        if (root == null) {
            root = new CharNode(word.getPalavra().charAt(0));
            totalNodes++;
        }
        Map<Character, CharNode> nodeMap = new HashMap<>();
        nodeMap.put(root.character, root);
        CharNode currentNode = root;
        for (int i = 0; i < word.getPalavra().length(); i++) {
            char currentChar = word.getPalavra().charAt(i);
            CharNode nextNode = currentNode.addChild(currentChar, i == word.getPalavra().length() - 1);
            totalNodes++;
            nodeMap.put(currentChar, nextNode);
            currentNode = nodeMap.get(currentChar);
            if (i == word.getPalavra().length() - 1) {
                currentNode.significado = word.getSignificado();
            }
        }
        totalWords++;
    }

    /**
     * Vai descendo na árvore até onde conseguir encontrar a palavra
     * 
     * @param word
     * @return o nodo final encontrado
     */
    private CharNode findCharNodeForWord(String word) {
        CharNode currentNode = root;
        for (int i = 0; i < word.length(); i++) {
            char currentChar = word.charAt(i);
            currentNode = currentNode.findChildChar(currentChar);
            if (currentNode == null) {
                return null;
            }
        }
        return currentNode;
    }

    /**
     * Retorna o significado da palavra
     * 
     * @param word
     * @return o significado da palavra
     */
    public String getSignificado(String word) {
        CharNode node = findCharNodeForWord(word);
        if (node != null && node.isFinal) {
            return node.significado;
        }
        return null;
    }

    /**
     * Verifica se a palavra está na árvore
     * 
     * @param word
     * @return se a palavra está na árvore
     */
    public boolean containsWord(String word) {
        CharNode node = findCharNodeForWord(word);
        return node != null && node.isFinal;
    }

    public List<Palavra> searchAll(String prefix) {
        List<Palavra> result = new ArrayList<>();

        // Encontrar o nodo correspondente ao último caracter do prefixo
        CharNode prefixNode = findCharNodeForWord(prefix);

        // Realizar busca em largura para obter palavras com o prefixo
        if (prefixNode != null) {
            Queue<CharNode> queue = new ArrayDeque<>();
            queue.offer(prefixNode);

            while (!queue.isEmpty()) {
                CharNode currentNode = queue.poll();

                if (currentNode.isFinal) {
                    result.add(new Palavra(currentNode.getWord(), currentNode.significado));
                }

                for (int i = 0; i < currentNode.getNumberOfChildren(); i++) {
                    queue.offer(currentNode.getChild(i));
                }
            }
        }

        return result;
    }

}
