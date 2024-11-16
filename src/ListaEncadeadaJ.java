import java.io.*;
import java.util.ArrayList;
import java.util.List;

class No {
    int dado;
    No proximo;

    public No(int dado) {
        this.dado = dado;
        this.proximo = null;
    }
}

class ListaEncadeada {
    private No cabeca;

    public ListaEncadeada() {
        this.cabeca = null;
    }

    public void adicionar(int dado, int posicao) {
        // Adiciona um nó com dado na posição especificada
        No novoNo = new No(dado);

        if (posicao == 0) {
            novoNo.proximo = cabeca;
            cabeca = novoNo;
            return;
        }

        // Percorrendo a lista para encontrar a posição válida
        No atual = cabeca;
        int contador = 0;

        while (atual != null && contador < posicao - 1) {
            atual = atual.proximo;
            contador++;
        }

        // Verifica se a posição é válida (se 'atual' existe e está no índice correto)
        if (atual == null) {
            return;
        }

        // Inserção do novo nó na posição especificada
        novoNo.proximo = atual.proximo;
        atual.proximo = novoNo;
    }

    public void remover(int dado) {
        No atual = cabeca;
        No anterior = null;
    
        while (atual != null) {
            if (atual.dado == dado) {
                if (anterior != null) {
                    anterior.proximo = atual.proximo; // Conecta o anterior ao próximo
                } else {
                    cabeca = atual.proximo; // Atualiza a cabeça se o nó removido for o primeiro
                }
    
                // Limpa as referências no nó removido
                atual.proximo = null; // Remove referência ao próximo
                atual.dado = 0;       // Zera o valor do dado 
                atual = null;         // Remove a referência ao nó atual
    
                return; 
            }
            anterior = atual;
            atual = atual.proximo;
        }
    }
    

    public void exibir() {
        // Exibe a lista encadeada
        List<Integer> elementos = new ArrayList<>();
        No atual = cabeca;

        while (atual != null) {
            elementos.add(atual.dado);
            atual = atual.proximo;
        }

        System.out.println("Lista: " + elementos);
    }
}

public class ListaEncadeadaJ {
    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new FileReader("rsc/arq.txt"))) {
            // Leitura da lista inicial
            String[] valoresIniciais = br.readLine().split(" ");
            ListaEncadeada listaEncadeada = new ListaEncadeada();

            for (int i = 0; i < valoresIniciais.length; i++) {
                listaEncadeada.adicionar(Integer.parseInt(valoresIniciais[i]), i);
            }

            // Leitura do número de ações
            int numAcoes = Integer.parseInt(br.readLine().trim());

            // Executando as ações
            for (int i = 0; i < numAcoes; i++) {
                String[] linhaAcao = br.readLine().trim().split(" ");
                String tipoAcao = linhaAcao[0];

                if ("A".equals(tipoAcao)) {
                    int numero = Integer.parseInt(linhaAcao[1]);
                    int posicao = Integer.parseInt(linhaAcao[2]);
                    listaEncadeada.adicionar(numero, posicao);
                } else if ("R".equals(tipoAcao)) {
                    int numero = Integer.parseInt(linhaAcao[1]);
                    listaEncadeada.remover(numero);
                } else if ("P".equals(tipoAcao)) {
                    listaEncadeada.exibir();
                }
            }
        } catch (IOException e) {
            System.err.println("Erro ao ler o arquivo: " + e.getMessage());
        }
    }
}
