class No {
    constructor(dado) {
        this.dado = dado;
        this.proximo = null;
    }
}

class ListaEncadeada {
    constructor() {
        this.cabeca = null;
    }

    adicionar(dado, posicao) {
        // Adiciona um nó com dado na posição especificada
        const novoNo = new No(dado);
        if (posicao === 0) {
            novoNo.proximo = this.cabeca;
            this.cabeca = novoNo;
            return;
        }

        // Percorrendo a lista para encontrar a posição válida
        let atual = this.cabeca;
        let contador = 0;

        while (atual && contador < posicao - 1) {
            atual = atual.proximo;
            contador++;
        }

        // Verifica se a posição é válida (se 'atual' existe e está no índice correto)
        if (atual === null) {
            return;
        }

        // Inserção do novo nó na posição especificada
        novoNo.proximo = atual.proximo;
        atual.proximo = novoNo;
    }

    remover(dado) {
        // Remove o primeiro nó encontrado com o valor dado
        let atual = this.cabeca;
        let anterior = null;

        while (atual) {
            if (atual.dado === dado) {
                if (anterior) {
                    anterior.proximo = atual.proximo;
                } else {
                    this.cabeca = atual.proximo;
                }

                // Remove referências ao nó
                atual.proximo = null;  // Remove referência ao próximo nó
                atual.dado = null;  // Remove o dado do nó
                return;  // Sai após remover o nó encontrado
            }
            anterior = atual;
            atual = atual.proximo;
        }
    }

    exibir() {
        // Exibe a lista encadeada
        let elementos = [];
        let atual = this.cabeca;
        while (atual) {
            elementos.push(atual.dado);
            atual = atual.proximo;
        }
        console.log("Lista:", elementos.join(' '));
    }
}

// Função principal
function main() {
    const fs = require('fs');

    // Lendo o arquivo de forma síncrona para garantir que os dados sejam lidos antes de processá-los
    fs.readFile('rsc/arq.txt', 'utf8', (err, data) => {
        if (err) {
            console.error("Erro ao ler o arquivo:", err);
            return;
        }

        const linhas = data.split('\n').filter(l => l.trim() !== ''); // Remover linhas vazias
        
        // Leitura da lista inicial
        const valoresIniciais = linhas[0].split(' ').map(Number);

        // Criando a lista encadeada e adicionando os elementos iniciais
        const listaEncadeada = new ListaEncadeada();
        for (let i = 0; i < valoresIniciais.length; i++) {
            listaEncadeada.adicionar(valoresIniciais[i], i);
        }

        // Leitura do número de ações
        const numAcoes = parseInt(linhas[1].trim());

        // Executando as ações
        for (let i = 2; i < 2 + numAcoes; i++) {
            const linhaAcao = linhas[i].trim().split(' ');
            const tipoAcao = linhaAcao[0];

            if (tipoAcao === 'A') {
                const numero = parseInt(linhaAcao[1]);
                const posicao = parseInt(linhaAcao[2]);
                listaEncadeada.adicionar(numero, posicao);
            } else if (tipoAcao === 'R') {
                const numero = parseInt(linhaAcao[1]);
                listaEncadeada.remover(numero);
            } else if (tipoAcao === 'P') {
                listaEncadeada.exibir();
            }
        }
    });
}

main();