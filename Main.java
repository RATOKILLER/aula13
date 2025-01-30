import java.util.Random;

class No {
    int valor;
    No anterior, proximo;

    public No(int valor) {
        this.valor = valor;
        this.anterior = null;
        this.proximo = null;
    }
}

class ListaDuplamenteEncadeada {
    No inicio, fim;

    public ListaDuplamenteEncadeada() {
        this.inicio = null;
        this.fim = null;
    }

    public void inserirOrdenado(int valor) {
        No novoNo = new No(valor);
        if (inicio == null) {
            inicio = fim = novoNo;
        } else if (valor <= inicio.valor) {
            novoNo.proximo = inicio;
            inicio.anterior = novoNo;
            inicio = novoNo;
        } else if (valor >= fim.valor) {
            fim.proximo = novoNo;
            novoNo.anterior = fim;
            fim = novoNo;
        } else {
            No atual = inicio;
            while (atual != null && atual.valor < valor) {
                atual = atual.proximo;
            }
            novoNo.anterior = atual.anterior;
            novoNo.proximo = atual;
            atual.anterior.proximo = novoNo;
            atual.anterior = novoNo;
        }
        
        // Impressão progressiva da lista
        imprimirListaParcial();
    }

    private void imprimirListaParcial() {
        No atual = inicio;
        System.out.print("Lista => ");
        while (atual != null) {
            System.out.print(atual.valor + " ");
            atual = atual.proximo;
        }
        System.out.println();
    }

    public void exibir(boolean crescente) {
        No atual = crescente ? inicio : fim;
        while (atual != null) {
            System.out.print(atual.valor + " ");
            atual = crescente ? atual.proximo : atual.anterior;
        }
        System.out.println();
    }

    public boolean isPrimo(int num) {
        if (num < 2) return false;
        if (num == 2) return true;
        if (num % 2 == 0) return false;
        for (int i = 3; i * i <= num; i += 2) {
            if (num % i == 0) return false;
        }
        return true;
    }

    public void removerPrimos() {
        No atual = inicio;
        while (atual != null) {
            No proximo = atual.proximo;
            if (isPrimo(atual.valor)) {
                if (atual.anterior != null) atual.anterior.proximo = atual.proximo;
                if (atual.proximo != null) atual.proximo.anterior = atual.anterior;
                if (atual == inicio) inicio = atual.proximo;
                if (atual == fim) fim = atual.anterior;
            }
            atual = proximo; // Avança corretamente sem pular elementos
        }
    }
}

public class Main {
    public static void main(String[] args) {
        Random rand = new Random();
        int[] numeros = new int[1000];
        ListaDuplamenteEncadeada lista = new ListaDuplamenteEncadeada();

        System.out.println("Vetor:");
        for (int i = 0; i < 1000; i++) {
            numeros[i] = rand.nextInt(19999) - 9999;  // Correto
            System.out.print(numeros[i] + " ");
        }

        System.out.println("\n\nInserindo na lista:");
        for (int i = 0; i < 1000; i++) {
            lista.inserirOrdenado(numeros[i]);
        }

        System.out.println("\nLista em ordem crescente:");
        lista.exibir(true);
        
        System.out.println("\nLista em ordem decrescente:");
        lista.exibir(false);
        
        lista.removerPrimos();
        System.out.println("\nLista após remoção dos números primos:");
        lista.exibir(true);
    }
}
