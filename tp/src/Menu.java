import java.util.List;
import java.util.Arrays;
import java.util.Scanner;
import java.util.InputMismatchException;

public class Menu {
    // variáveis de instância
    private List<String> opcoes; // lista com as opções disponíveis no menu
    private int op; // opção selecionada

    //Construtor
    public Menu(String[] opcoes) {
        this.opcoes = Arrays.asList(opcoes);
        this.op = 0;
    }

    // Método para executar o menu
    public void executa() {
        do {
            showMenu();
            this.op = lerOpcao();
        } while (this.op == -1);
    }

    // Método para mostrar o menu
    private void showMenu() {
        for (int i=0; i<this.opcoes.size(); i++) {
            if(i != (this.opcoes.size()-1)) {
                System.out.print("(");
                System.out.print(i + 1);
                System.out.print(") ");
                System.out.println(this.opcoes.get(i));
            }
            if(i == (this.opcoes.size()-1)){
                System.out.print("(");
                System.out.print(0);
                System.out.print(") ");
                System.out.println(this.opcoes.get(i));
            }
        }
    }

    // Método para ler uma opção
    private int lerOpcao() {
        int op;
        Scanner is = new Scanner(System.in);

        System.out.print("\t\tSelecionar opção: ");
        try {
            op = is.nextInt();
        }
        catch (InputMismatchException e) {
            op = -1;
        }
        if (op<0 || op>this.opcoes.size()) {
            System.out.println("A opção selecionada é inválida.");
            op = -1;
        }
        return op;
    }

    // Getter
    public int getOpcao() {
        return this.op;
    }

    // Método para limpar o ecrã
    public void limpa(){
        for(int i = 0; i < 50; i++) System.out.println();
    }
}
