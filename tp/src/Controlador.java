import java.io.IOException;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;

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

    // Execução do menu principal
    public void run() throws LinhaIncorretaException, IOException, ClassNotFoundException {
        String[] ops = {
                "Jogadores", "Equipas", "Jogos","Carregar estado de um ficheiro", "Guardar estado num ficheiro", "Carregar logs"
                , "Sair"
        };
        Menu principal = new Menu(ops);
        do {
            principal.executa();
            switch(principal.getOpcao()) {
                case 1: // Jogadores
                    principal.limpa();
                    runJogadores();
                    break;
                case 2: // Equipas
                    principal.limpa();
                    runEquipa();
                    break;
                case 3: // Jogos
                    principal.limpa();
                    runJogos(); // incompleto
                    break;
                case 4: // Carregar estado de um ficheiro
                    this.estado.loadEstadoObj("Estado.obj");
                    break;
                case 5: // Guardar estado num ficheiro
                    principal.limpa();
                    this.estado.saveEstado();
                    break;
                case 6: // Carregar logs
                    principal.limpa();
                    try{
                        this.estado.parse();
                    } catch (LinhaIncorretaException e) {
                        throw new LinhaIncorretaException();
                    }
                    break;
                case 0:
                    principal.limpa();
                    System.out.println("\t\tSaindo da aplicação.");
                    break;
                default:
                    principal.limpa();
                    break;
            }
        } while (principal.getOpcao() != 0);
    }

    // Execução do menun referente aos jogos
    public void runJogos() {
        String[] ops = {
                "Adicionar jogo","Consultar todos os jogos", "Consultar um jogo",
                "Determinar o resultado de um jogo", "Simular jogo", "Voltar ao menu anterior"
        };
        Menu jogos = new Menu(ops);
        Jogo jo = null;
        do {
            jogos.executa();
            switch(jogos.getOpcao()) {
                case 1: // Adicionar jogo
                    jogos.limpa();
                    jo = runCriaJogo();
                    break;
                case 2: // Consultar todos os jogos
                    jogos.limpa();
                    for (Jogo j : this.estado.getJogos()) System.out.println(j.toString());
                    break;
                case 3: // Consultar um jogo
                    jogos.limpa();
                    runConsultaJogo();
                    break;
                case 4: // Determinar o resultado de um jogo
                    jogos.limpa();
                    if (jo != null) runResultado(jo);
                    else runResultado(runConsultaJogo());
                    break;
                case 5: // Simular um jogo
                    jogos.limpa();
                    if (jo != null && jo.getFimJogo() != 1) jo = simulaJogo(jo);// incompleto
                    else System.out.println("Criar um novo jogo primeiro.");
                    break;
                default:
                    jogos.limpa();
                    break;
            }
        } while (jogos.getOpcao() != 0);
    }

    // Método para simulação de um jogo
    private Jogo simulaJogo(Jogo jo) {
        this.estado.removeJogo(jo);
        StringBuffer sb = new StringBuffer();
        sb.append(jo.getEquipaCasa()).append(" vs ").append(jo.getEquipaVisitante()).append("\n");
        System.out.println(" --- INÍCIO DO JOGO ---");
        System.out.println(sb);
        jo = runParte(jo);
        System.out.println(" --- INTERVALO ---");
        jo = runParte(jo);
        jo.setFimJogo();
        this.estado.addJogo(jo);
        System.out.println(" --- FIM DO JOGO ---");
        System.out.println(sb);
        return jo;
    }

    // Método para executar as partes de um jogo
    private Jogo runParte(Jogo jo) {
        String[] ops = {
                "Substituir jogador da equipa da casa","Substituir jogador da equipa visitante",
                "Jogo", "Terminar parte"
        };
        Menu parte = new Menu(ops);
        Scanner in = new Scanner(System.in);
        Set<Jogador> troca = new HashSet<>();
        Set<Jogador> casa = new HashSet<>();
        Set<Jogador> visitante = new HashSet<>();
        Set<Jogador> todos;
        int golo;
        int entra, sai;
        do {
            parte.executa();
            switch(parte.getOpcao()) {
                case 1: // Substituir jogador da equipa da casa
                    parte.limpa();
                    System.out.println("Jogadores em campo");
                    todos = this.estado.getEquipas().get(jo.getEquipaCasa()).getJogadores();
                    for(int i : jo.getJogadoresCasa()) {
                        for (Jogador j : todos) {
                            if (j.getNrCamisola() == i) casa.add(j);
                        }
                    }
                    for(Jogador j : casa) System.out.println(j.toStringJogadorSimples());
                    System.out.println("\nJogadores disponíveis para trocar");
                    for (Jogador j : this.estado.getEquipas().get(jo.getEquipaCasa()).getJogadores()){
                        if (!jo.getJogadoresCasa().contains(j.getNrCamisola())
                                && j.tipoJogador() != 3
                                && !jo.getSubstituicoesCasa().containsKey(j.getNrCamisola())
                                && !jo.getSubstituicoesCasa().containsValue(j.getNrCamisola()))
                            troca.add(j);
                    }
                    for(Jogador j : troca) System.out.println(j.toStringJogadorSimples());
                    System.out.print("Selecionar jogador para sair: ");
                    sai = in.nextInt();
                    System.out.print("Selecionar jogador para entrar: ");
                    entra = in.nextInt();
                    try{
                        jo.efetuaSubstituicao(jo.getEquipaCasa(),sai,entra);
                        System.out.println("Substituição efetuada.");
                    } catch (SubstituicaoInvalidaException e){
                        System.out.println("Substituição inválida.");
                    }
                    break;
                case 2: // Substituir jogador da equipa visitante
                    parte.limpa();
                    System.out.println("Jogadores em campo");
                    todos = this.estado.getEquipas().get(jo.getEquipaVisitante()).getJogadores();
                    for(int i : jo.getJogadoresVisitante()) {
                        for (Jogador j : todos) {
                            if (j.getNrCamisola() == i) visitante.add(j);
                        }
                    }
                    for(Jogador j : visitante) System.out.println(j.toStringJogadorSimples());
                    System.out.println("\nJogadores disponíveis para trocar");
                    for (Jogador j : this.estado.getEquipas().get(jo.getEquipaVisitante()).getJogadores()){
                        if (!jo.getJogadoresVisitante().contains(j.getNrCamisola())
                                && j.tipoJogador() != 3
                                && !jo.getSubstituicoesVisitante().containsKey(j.getNrCamisola())
                                && !jo.getSubstituicoesVisitante().containsValue(j.getNrCamisola()))
                            troca.add(j);
                    }
                    for(Jogador j : troca) System.out.println(j.toStringJogadorSimples());
                    System.out.print("Selecionar jogador para sair: ");
                    sai = in.nextInt();
                    System.out.print("Selecionar jogador para entrar: ");
                    entra = in.nextInt();
                    try{
                        jo.efetuaSubstituicao(jo.getEquipaVisitante(),sai,entra);
                        System.out.println("Substituição efetuada.");
                    } catch (SubstituicaoInvalidaException e){
                        System.out.println("Substituição inválida.");
                    }
                    break;
                case 3: // Ataque
                    parte.limpa();
                    golo = marcaGolo(jo);
                    if (golo == 1){
                        System.out.println("Golo! Marcou a equipa da casa.");
                    }
                    else if(golo == 2) System.out.println("Golo! Marcou a equipa visitante.");
                    else System.out.println("Não foram marcados golos.");
                    break;
                default:
                    parte.limpa();
                    break;
            }
        } while (parte.getOpcao() != 0);
        return jo;
    }

    // Método para determinar se um golo é marcado
    private int marcaGolo(Jogo jo){
        if(System.currentTimeMillis() % 2 == 0){
            jo.marca(jo.getEquipaCasa());
            return 1;
        }
        else if(System.currentTimeMillis() % 2 == 0){
            jo.marca(jo.getEquipaVisitante());
            return 2;
        }
        else return 0;
    }

    // Execução de cálculo do resultado de um jogo
    private void runResultado(Jogo j) {
        if(this.estado.getJogos().contains(j)) {
            StringBuffer sb = new StringBuffer();
            String nomeCasa;
            String nomeVis;
            Equipa eCasa;
            Equipa eVis;
            double habCasa = 0;
            double habVis = 0;
            int subsCasa = -1;
            int subsVis = -1;
            if (j.getFimJogo() == 1) {
                sb.append("\n** Resultado do jogo **\n")
                        .append(j.getEquipaCasa()).append(" vs ").append(j.getEquipaVisitante()).append(": ")
                        .append(j.getGolosCasa()).append("-").append(j.getGolosVisitante()).append("\n");
                System.out.println(sb);
            }
            else {
                this.estado.removeJogo(j);
                nomeCasa = j.getEquipaCasa();
                eCasa = this.estado.getEquipas().get(nomeCasa);
                nomeVis = j.getEquipaVisitante();
                eVis = this.estado.getEquipas().get(nomeVis);
                if (System.currentTimeMillis() % 2 == 0){
                    for (Jogador jogaCasa : eCasa.getJogadores()) {
                        if (j.getJogadoresCasa().contains(jogaCasa.getNrCamisola()) && jogaCasa.tipoJogador() != 3) {
                            subsCasa = jogaCasa.getNrCamisola();
                            break;
                        }
                    }
                    for (Jogador jogaCasa : eCasa.getJogadores()){
                        if (!j.getJogadoresCasa().contains(jogaCasa.getNrCamisola()) && jogaCasa.tipoJogador() != 3){
                            try {
                                j.efetuaSubstituicao(nomeCasa, subsCasa,jogaCasa.getNrCamisola());
                            } catch (SubstituicaoInvalidaException e) {
                                System.out.println("Substituição inválida (Casa).");
                            }
                            break;
                        }
                    }
                }
                if (System.currentTimeMillis() % 2 == 0){
                    for (Jogador jogaVis : eVis.getJogadores()) {
                        if (j.getJogadoresVisitante().contains(jogaVis.getNrCamisola()) && jogaVis.tipoJogador() != 3) {
                            subsVis = jogaVis.getNrCamisola();
                            break;
                        }
                    }
                    for (Jogador jogaVis : eVis.getJogadores()){
                        if (!j.getJogadoresVisitante().contains(jogaVis.getNrCamisola()) && jogaVis.tipoJogador() != 3){
                            try {
                                j.efetuaSubstituicao(nomeVis, subsVis, jogaVis.getNrCamisola());
                            } catch (SubstituicaoInvalidaException e) {
                                System.out.println("Substituição inválida (Vis).");
                            }
                            break;
                        }
                    }
                }
                for (int i : j.getJogadoresCasa()) {
                    for (Jogador jo : eCasa.getJogadores()) {
                        if (jo.getNrCamisola() == i) habCasa += jo.calculaHabilidade();
                    }
                }
                for (int i : j.getJogadoresVisitante()) {
                    for (Jogador jo : eVis.getJogadores()) {
                        if (jo.getNrCamisola() == i) habVis += jo.calculaHabilidade();
                    }
                }
                j.setFimJogo();
                if (habCasa > habVis) {
                    j.setGolosCasa(2);
                    j.setGolosVisitante(0);
                }
                else if (habCasa < habVis) {
                    j.setGolosCasa(1);
                    j.setGolosVisitante(2);
                }
                    else {
                        j.setGolosCasa(1);
                        j.setGolosVisitante(1);
                    }
                sb.append("\n** Resultado do jogo **\n")
                        .append(j.getEquipaCasa()).append(" vs ").append(j.getEquipaVisitante()).append(": ")
                        .append(j.getGolosCasa()).append("-").append(j.getGolosVisitante()).append("\n");
                System.out.println(sb);
                this.estado.addJogo(j);
            }
        }
        else System.out.println("Jogo inávlido.");
    }

    // Execução de menu que permite consultar um jogo
    public Jogo runConsultaJogo() {
        Scanner in = new Scanner(System.in);
        Jogo jFim = null;
        String eqCasa;
        String eqVisitante;
        System.out.print("Inserir data jogo segundo o formato YYYY-MM-DD: ");
        String campos = in.nextLine();
        String[] data = campos.split("-");
        LocalDate d;
        try {
            d = LocalDate.of(Integer.parseInt(data[0]), Integer.parseInt(data[1]), Integer.parseInt(data[2]));
        } catch (DateTimeException e){
            d = LocalDate.now();
            System.out.println("A data inserida é inválida. A data definida para o jogo é: " + d);
        }
        System.out.print("Inserir nome da equipa da casa: ");
        eqCasa = in.nextLine();
        System.out.print("Inserir nome da equipa visitante: ");
        eqVisitante = in.nextLine();
        for(Jogo j : this.estado.getJogos()){
            if (j.getDataJogo().equals(d) && j.getEquipaCasa().equals(eqCasa) && j.getEquipaVisitante().equals(eqVisitante))
                jFim = j;
        }
        if (jFim == null) System.out.println("Jogo inválido.");
        return jFim;
    }

    // Execução de menu para criar um novo jogo
    public Jogo runCriaJogo() {
        Scanner in = new Scanner(System.in);
        Jogo j = new Jogo();
        String eqCasa;
        String eqVisitante;
        System.out.print("Inserir data jogo segundo o formato YYYY-MM-DD: ");
        String campos = in.nextLine();
        String[] data = campos.split("-");
        LocalDate d;
        try {
            d = LocalDate.of(Integer.parseInt(data[0]), Integer.parseInt(data[1]), Integer.parseInt(data[2]));
        } catch (DateTimeException e){
            d = LocalDate.now();
            System.out.println("A data inserida é inválida. A data definida para o jogo é: " + d);
        }
        System.out.print("Inserir nome da equipa da casa: ");
        eqCasa = in.nextLine();
        System.out.print("Inserir nome da equipa visitante: ");
        eqVisitante = in.nextLine();
        if (this.estado.getEquipas().containsKey(eqCasa) && this.estado.getEquipas().containsKey(eqVisitante)) {
            j.setDataJogo(d);
            j.setEquipaCasa(eqCasa);
            j.setJogadoresCasa(this.estado.convocaJogadores(eqCasa));
            j.setEquipaVisitante(eqVisitante);
            j.setJogadoresVisitante(this.estado.convocaJogadores(eqVisitante));
            this.estado.addJogo(j);
            return j;
        }
        else System.out.println("As Equipas selecionadas não são válidas.");
        return null;
    }

    // Execução do menu referente às equipas
    public void runEquipa() throws NullPointerException {
        String[] ops = {
                "Adicionar equipa", "Adicionar jogadores a uma equipa", "Consultar todas as equipas",
                "Consultar uma equipa", "Calcular habilidade de uma equipa", "Voltar ao menu anterior"
        };
        Menu equipa = new Menu(ops);
        Scanner in = new Scanner(System.in);
        String nomeEq;
        String nomeJog;
        Equipa eq;
        do {
            equipa.executa();
            switch (equipa.getOpcao()) {
                case 1: // Adicionar equipa
                    System.out.print("Inserir nome da Equipa: ");
                    nomeEq = in.nextLine();
                    eq = new Equipa(nomeEq);
                    this.estado.addEquipa(eq);
                    break;
                case 2: // Adicionar jogadores a uma equipa
                    System.out.print("Inserir nome da Equipa: ");
                    nomeEq = in.nextLine();
                    System.out.print("Inserir nome do Jogador: ");
                    nomeJog = in.nextLine();
                    if (this.estado.getEquipas().containsKey(nomeEq)){
                        if (this.estado.getJogadores().containsKey(nomeJog)){
                            eq = new Equipa(this.estado.getEquipas().get(nomeEq));
                            eq.adicionaJogador(this.estado.getJogadores().get(nomeJog));
                            this.estado.removeEquipa(nomeEq);
                            this.estado.addEquipa(eq);
                        }
                        else System.out.println("Jogador inválido.");
                    }
                    else System.out.println("Equipa inválida.");
                    break;
                case 3: // Consultar todas as equipas
                    for(Equipa e : this.estado.getEquipas().values()) System.out.println(e.toString());
                    break;
                case 4: // Consultar uma equipa
                    equipa.limpa();
                    System.out.print("Inserir nome da Equipa: ");
                    nomeEq = in.nextLine();
                    if (this.estado.getEquipas().containsKey(nomeEq))
                        System.out.println(this.estado.getEquipas().get(nomeEq).toString());
                    else System.out.println("Equipa inexistente.");
                    break;
                case 5: // Calcular habilidade de uma equipa
                    equipa.limpa();
                    System.out.print("Inserir nome da Equipa: ");
                    nomeEq = in.nextLine();
                    if (this.estado.getEquipas().containsKey(nomeEq))
                        System.out.println(this.estado.getEquipas().get(nomeEq).toStringHabilidadeGlobal());
                    else System.out.println("Equipa inexistente.");
                    break;
                default:
                    equipa.limpa();
                    break;
            }
        } while (equipa.getOpcao() != 0);
    }

    // Execução do menu referente aos jogadores
    public void runJogadores() throws NullPointerException { // incompleto
        String[] ops = {
                "Adicionar jogador", "Remover jogador", "Consultar todos os jogadores",
                "Consultar um jogador", "Calcular habilidade de um jogador", "Voltar ao menu anterior"
        };
        Menu jogadores = new Menu(ops);
        Scanner in = new Scanner(System.in);
        String nome;
        do {
            jogadores.executa();
            switch (jogadores.getOpcao()) {
                case 1: // Adicionar jogador
                    jogadores.limpa();
                    runAddJogador();
                    break;
                case 2: // Remover jogador
                    jogadores.limpa();
                    System.out.print("Inserir nome do Jogador: ");
                    nome = in.nextLine();
                    if (this.estado.getJogadores().containsKey(nome))
                        this.estado.removeJogador(nome);
                    else System.out.println("Jogador inexistente.");
                    break;
                case 3: // Consultar todos os jogadores
                    jogadores.limpa();
                    for(Jogador j : this.estado.getJogadores().values()) System.out.println(j.toStringJogadorSimples());
                    break;
                case 4: // Consultar um jogador
                    jogadores.limpa();
                    System.out.print("Inserir nome do Jogador: ");
                    nome = in.nextLine();
                    if (this.estado.getJogadores().containsKey(nome))
                        System.out.println(this.estado.getJogadores().get(nome).toString());
                    else System.out.println("Jogador inexistente.");
                    break;
                case 5: // Calcular habilidade de um jogador
                    jogadores.limpa();
                    System.out.print("Inserir nome do Jogador: ");
                    nome = in.nextLine();
                    if (this.estado.getJogadores().containsKey(nome))
                        System.out.println("Habilidade: "+this.estado.getJogadores().get(nome).getHabilidade());
                    else System.out.println("Jogador inexistente.");
                    break;
                default:
                    jogadores.limpa();
                    break;
            }
        } while (jogadores.getOpcao() != 0);
    }

    // Execução do menu referente à adição de jogadores
    public void runAddJogador() throws NullPointerException { // incompleto
        String[] ops = {
                "Avançado", "Defesa", "Guarda-Redes", "Lateral", "Médio", "Voltar ao menu anterior"
        };
        Menu jogadores = new Menu(ops);
        Jogador j;
        String[] infoJog;
        int[] infoAdicional;
        do {
            jogadores.executa();
            switch (jogadores.getOpcao()) {
                case 1: // Avançado
                    jogadores.limpa();
                    infoJog = scanInfoJogador();
                    infoAdicional = scanInfoGenerica(1);
                    j = new Avancado(infoJog[0],Integer.parseInt(infoJog[1]),Integer.parseInt(infoJog[2]),
                            Integer.parseInt(infoJog[3]),Integer.parseInt(infoJog[4]),Integer.parseInt(infoJog[5]),
                            Integer.parseInt(infoJog[6]),Integer.parseInt(infoJog[7]),Integer.parseInt(infoJog[8]),
                            infoAdicional[0]);
                    this.estado.addJogador(j);
                    break;
                case 2: // Defesa
                    jogadores.limpa();
                    infoJog = scanInfoJogador();
                    infoAdicional = scanInfoGenerica(2);
                    j = new Defesa(infoJog[0],Integer.parseInt(infoJog[1]),Integer.parseInt(infoJog[2]),
                            Integer.parseInt(infoJog[3]),Integer.parseInt(infoJog[4]),Integer.parseInt(infoJog[5]),
                            Integer.parseInt(infoJog[6]),Integer.parseInt(infoJog[7]),Integer.parseInt(infoJog[8]),
                            infoAdicional[0]);
                    this.estado.addJogador(j);
                    break;
                case 3: // Guarda-Redes
                    jogadores.limpa();
                    infoJog = scanInfoJogador();
                    infoAdicional = scanInfoGenerica(3);
                    j = new GuardaRedes(infoJog[0],Integer.parseInt(infoJog[1]),Integer.parseInt(infoJog[2]),
                            Integer.parseInt(infoJog[3]),Integer.parseInt(infoJog[4]),Integer.parseInt(infoJog[5]),
                            Integer.parseInt(infoJog[6]),Integer.parseInt(infoJog[7]),Integer.parseInt(infoJog[8]),
                            infoAdicional[0],infoAdicional[1]);
                    this.estado.addJogador(j);
                    break;
                case 4: // Lateral
                    jogadores.limpa();
                    infoJog = scanInfoJogador();
                    infoAdicional = scanInfoGenerica(4);
                    j = new Lateral(infoJog[0],Integer.parseInt(infoJog[1]),Integer.parseInt(infoJog[2]),
                            Integer.parseInt(infoJog[3]),Integer.parseInt(infoJog[4]),Integer.parseInt(infoJog[5]),
                            Integer.parseInt(infoJog[6]),Integer.parseInt(infoJog[7]),Integer.parseInt(infoJog[8]),
                            infoAdicional[0]);
                    this.estado.addJogador(j);
                    break;
                case 5: // Médio
                    jogadores.limpa();
                    infoJog = scanInfoJogador();
                    infoAdicional = scanInfoGenerica(5);
                    j = new Medio(infoJog[0],Integer.parseInt(infoJog[1]),Integer.parseInt(infoJog[2]),
                            Integer.parseInt(infoJog[3]),Integer.parseInt(infoJog[4]),Integer.parseInt(infoJog[5]),
                            Integer.parseInt(infoJog[6]),Integer.parseInt(infoJog[7]),Integer.parseInt(infoJog[8]),
                            infoAdicional[0]);
                    this.estado.addJogador(j);
                    break;
                default:
                    jogadores.limpa();
                    break;
            }
        } while (jogadores.getOpcao() != 0);
    }

    // Leitura de informação referente comum a todos os tipos de jogadores
    public int[] scanInfoGenerica(int numero) {
        int[] res = new int[3];
        Scanner in = new Scanner(System.in);
        if (numero == 1){ // Avançado
            System.out.print("Inserir capacidade de marcação de penálti (0-100): ");
            res[0] = in.nextInt();
        }
        if (numero == 2){ // Defesa
            System.out.print("Inserir capacidade de marcação dos jogadores adversários (0-100): ");
            res[0] = in.nextInt();
        }
        if (numero == 3){ // Guarda-Redes
            System.out.print("Inserir elasticidade (0-100): ");
            res[0] = in.nextInt();
            System.out.print("Inserir capacidade de defesa (0-100): ");
            res[1] = in.nextInt();
        }
        if (numero == 4){ // Lateral
            System.out.print("Inserir capacidade de cruzamento (0-100): ");
            res[0] = in.nextInt();
        }
        if (numero == 5){ // Medio
            System.out.print("Inserir capacidade de marcação (0-100): ");
            res[0] = in.nextInt();
        }
        return res;
    }

    // Leitura de informação específica de cada tipo de jogador
    public String[] scanInfoJogador(){
        String[] res = new String[9];
        Scanner inPut = new Scanner(System.in);
        System.out.print("Inserir nome do jogador: ");
        res[0] = inPut.nextLine();
        System.out.print("Inserir número da camisola: ");
        res[1] = String.valueOf(inPut.nextInt());
        System.out.print("Inserir velocidade (0-100): ");
        res[2] = String.valueOf(inPut.nextInt());
        System.out.print("Inserir resistência (0-100): ");
        res[3] = String.valueOf(inPut.nextInt());
        System.out.print("Inserir destreza (0-100): ");
        res[4] = String.valueOf(inPut.nextInt());
        System.out.print("Inserir impulsão (0-100): ");
        res[5] = String.valueOf(inPut.nextInt());
        System.out.print("Inserir jogo de cabeça (0-100): ");
        res[6] = String.valueOf(inPut.nextInt());
        System.out.print("Inserir capacidade de remate (0-100): ");
        res[7] = String.valueOf(inPut.nextInt());
        System.out.print("Inserir capacidade de passe (0-100): ");
        res[8] = String.valueOf(inPut.nextInt());
        return res;
    }
}
