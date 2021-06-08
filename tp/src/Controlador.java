import java.io.IOException;

public class Controlador {
    // Variáveis de instância
    private Estado estado;

    // Construtores
    public Controlador(){
        this.estado = new Estado();
    }

    public Controlador(Estado e){
        setEstado(e);
    }

    public Controlador(Controlador c){
        setEstado(c.getEstado());
    }

    // Getters e Setters
    public Estado getEstado() {
        return estado.clone();
    }

    public void setEstado(Estado estado) {
        this.estado = new Estado(estado);
    }

    // Clone
    public Controlador clone(){
        return new Controlador(this);
    }

    // Equals
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Controlador c = (Controlador) o;
        return this.estado.equals(c.estado);
    }

    // toString
    @Override
    public String toString() {
        return this.estado.toString();
    }

    public void run() throws LinhaIncorretaException, IOException, ClassNotFoundException {
        String[] ops = {
                "Jogadores", "Equipas", "Jogos","Guardar estado", "Carregar estado", "Carregar logs"
        };
        Menu inicial = new Menu(ops);
        do {
            inicial.executa();
            switch (inicial.getOpcao()) {
                case 1:
                    inicial.limpa();
                    runJogadores(); // incompleto
                    break;
                case 2:
                    inicial.limpa();
                    System.out.println("equipa"); // incompleto
                    break;
                case 3:
                    inicial.limpa();
                    System.out.println("jogo");// incompleto
                    break;
                case 4:
                    this.estado.loadEstadoObj("Estado.obj");
                    break;
                case 5:
                    inicial.limpa();
                    this.estado.saveEstado();
                    break;
                case 6:
                    inicial.limpa();
                    try{
                        this.estado.parse();
                    } catch (LinhaIncorretaException e) {
                        throw new LinhaIncorretaException();
                    }
                    break;
                case 0:
                    inicial.limpa();
                    System.out.println("\t\tSaindo da aplicação.");
                    break;
                default:
                    break;
            }
        } while (inicial.getOpcao() != 0);
    }

    public void runJogadores() { // incompleto
        String[] ops = {
                "Adicionar jogador", "Remover jogador", "Consultar todos os jogadores","Consultar um jogador", "Calcular habilidade de um jogador"
        };
        Menu jogadores = new Menu(ops);
        do {
            jogadores.executa();
            switch (jogadores.getOpcao()) {
                case 1:
                    jogadores.limpa();
                    System.out.println("jogador");
                    break;
                case 2:
                    jogadores.limpa();
                    System.out.println("equipa");
                    break;
                case 3:
                    jogadores.limpa();
                    System.out.println("jogo");
                    break;
                case 4:
                    jogadores.limpa();
                    System.out.println("ler logs");
                    break;
                case 5:
                    jogadores.limpa();
                    System.out.println("guardar estado");
                    break;
                case 0:
                    jogadores.limpa();
                    break;
                default:
                    break;
            }
        } while (jogadores.getOpcao() != 0);
    }
}
