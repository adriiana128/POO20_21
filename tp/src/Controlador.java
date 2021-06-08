import java.io.IOException;

public class Controlador {
    // Variáveis de instância
    private Estado estado; // estado da aplicação

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
                "Jogadores", "Equipas", "Jogos","Carregar estado de um ficheiro", "Guardar estado num ficheiro", "Carregar logs"
        };
        Menu inicial = new Menu(ops);
        do {
            inicial.executa();
            switch (inicial.getOpcao()) {
                case 1: // Jogadores
                    inicial.limpa();
                    runJogadores(); // incompleto
                    break;
                case 2: // Equipas
                    inicial.limpa();
                    System.out.println("equipa"); // incompleto
                    break;
                case 3: // Jogos
                    inicial.limpa();
                    System.out.println("jogo");// incompleto
                    break;
                case 4: // Carregar estado de um ficheiro
                    this.estado.loadEstadoObj("Estado.obj");
                    break;
                case 5: // Guardar estado num ficheiro
                    inicial.limpa();
                    this.estado.saveEstado();
                    break;
                case 6: // Carregar logs
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
                "Adicionar jogador", "Remover jogador", "Consultar todos os jogadores", "Calcular habilidade de um jogador"
        };
        Menu jogadores = new Menu(ops);
        do {
            jogadores.executa();
            switch (jogadores.getOpcao()) {
                case 1: // Adicionar jogador
                    jogadores.limpa();
                    runAddJog();
                    // incompleto
                    break;
                case 2: // Remover jogador
                    jogadores.limpa();
                    // incompleto
                    break;
                case 3: // Consultar todos os jogadores
                    jogadores.limpa();
                    for(Jogador j : this.estado.getJogadores().values()) System.out.print(j.toString());
                    break;
                case 4: // Calcular habilidade de um jogador
                    jogadores.limpa();
                    // incompleto
                    break;
                case 0:
                    jogadores.limpa();
                    break;
                default:
                    break;
            }
        } while (jogadores.getOpcao() != 0);
    }

    public void runAddJog() { // incompleto
        String[] ops = {
                "Avançado", "Defesa", "Guarda-Redes", "Lateral", "Médio"
        };
        Menu jogadores = new Menu(ops);
        do {
            jogadores.executa();
            switch (jogadores.getOpcao()) {
                case 1: // Avançado
                    jogadores.limpa();
                    // incompleto
                    break;
                case 2: // Defesa
                    jogadores.limpa();
                    // incompleto
                    break;
                case 3: // Guarda-Redes
                    break;
                case 4: // Lateral
                    jogadores.limpa();
                    // incompleto
                    break;
                case 5: // Médio
                    jogadores.limpa();
                    // incompleto
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
