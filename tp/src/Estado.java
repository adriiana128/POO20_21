import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;

public class Estado implements Serializable {
    // Variáveis de instância
    private Map<String,Equipa> equipas;
    private Map<String,Jogador> jogadores;
    private List<Jogo> jogos;

    // Construtores
    public Estado(){
        this.equipas = new TreeMap<String,Equipa>(); //Nome, Equipa
        this.jogadores = new TreeMap<String,Jogador>(); //Nome, Jogador
        this.jogos = new ArrayList<Jogo>();
    }

    public Estado(Map<String,Equipa> equipas, Map<String,Jogador> jogadores, List<Jogo> jogos){
        setEquipas(equipas);
        setJogadores(jogadores);
        setJogos(jogos);
    }

    public Estado(Estado e){
        setEquipas(e.getEquipas());
        setJogadores(e.getJogadores());
        setJogos(e.getJogos());
    }

    // Leitura do ficheiro de inicialização do jogo
    public void parse() throws LinhaIncorretaException {
        List<String> linhas = lerFicheiro("logs.txt");
        Equipa ultima = null;
        Jogador j = null;
        Map<String, Equipa> equipas = new TreeMap<String,Equipa>(); // Nome, Equipa
        Map<String, Jogador> jogadores = new TreeMap<String,Jogador>(); // Nome, Jogador
        List<Jogo> jogos = new ArrayList<>();
        String[] linhaPartida;

        for (String linha : linhas) {
            linhaPartida = linha.split(":", 2);
            switch(linhaPartida[0]){
                case "Equipa":
                    Equipa e = Equipa.parse(linhaPartida[1]);
                    equipas.put(e.getNome(), e);
                    ultima = e;
                    break;
                case "Guarda-Redes":
                    j = GuardaRedes.parse(linhaPartida[1]);
                    if (ultima == null) throw new LinhaIncorretaException();
                    j.addEquipa(ultima.getNome());
                    jogadores.put(j.getNome(), j.clone());
                    ultima.adicionaJogador(j.clone());
                    break;
                case "Defesa":
                    j = Defesa.parse(linhaPartida[1]);
                    if (ultima == null) throw new LinhaIncorretaException();
                    j.addEquipa(ultima.getNome());
                    jogadores.put(j.getNome(), j.clone());
                    ultima.adicionaJogador(j.clone());
                    break;
                case "Medio":
                    j = Medio.parse(linhaPartida[1]);
                    if (ultima == null) throw new LinhaIncorretaException();
                    j.addEquipa(ultima.getNome());
                    jogadores.put(j.getNome(), j.clone());
                    ultima.adicionaJogador(j.clone());
                    break;
                case "Lateral":
                    j = Lateral.parse(linhaPartida[1]);
                    if (ultima == null) throw new LinhaIncorretaException();
                    j.addEquipa(ultima.getNome());
                    jogadores.put(j.getNome(), j.clone());
                    ultima.adicionaJogador(j.clone());
                    break;
                case "Avancado":
                    j = Avancado.parse(linhaPartida[1]);
                    if (ultima == null) throw new LinhaIncorretaException();
                    j.addEquipa(ultima.getNome());
                    jogadores.put(j.getNome(), j.clone());
                    ultima.adicionaJogador(j.clone());
                    break;
                case "Jogo":
                    Jogo jo = Jogo.parse(linhaPartida[1]);
                    jogos.add(jo);
                    break;
                default:
                    throw new LinhaIncorretaException();
            }
        }
        setJogadores(jogadores);
        setEquipas(equipas);
        setJogos(jogos);
    }

    public static List<String> lerFicheiro(String nomeFich) {
        List<String> lines;
        try { lines = Files.readAllLines(Paths.get(nomeFich), StandardCharsets.UTF_8); }
        catch(IOException exc) { lines = new ArrayList<>(); }
        return lines;
    }

    // Getters e Setters
    public Map<String, Equipa> getEquipas() {
        Map<String,Equipa> res = new TreeMap<>();
        for(Equipa e : this.equipas.values()) res.put(e.getNome(),e.clone());
        return res;
    }

    public void setEquipas(Map<String, Equipa> equipas) {
        for(Equipa e : equipas.values()) this.equipas.put(e.getNome(),e.clone());
    }

    public Map<String, Jogador> getJogadores() {
        Map<String,Jogador> res = new TreeMap<>();
        for(Jogador j : this.jogadores.values()) res.put(j.getNome(),j.clone());
        return res;
    }

    public void setJogadores(Map<String, Jogador> jogadores) {
        for(Jogador j : jogadores.values()) this.jogadores.put(j.getNome(),j.clone());
    }

    public List<Jogo> getJogos() {
        return jogos.stream().map(Jogo::clone).collect(Collectors.toList());
    }

    public void setJogos(List<Jogo> jogos) {
        this.jogos = jogos.stream().map(Jogo::clone).collect(Collectors.toList());
    }

    // Equals
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Estado estado = (Estado) o;
        return this.equipas.equals(estado.equipas) && this.jogadores.equals(estado.jogadores) && this.jogos.equals(estado.jogos);
    }

    // Clone
    public Estado clone(){
        return new Estado(this);
    }

    // toString
    @Override
    public String toString() {
        StringBuffer sb = new StringBuffer();
        sb.append(" *** EQUIPAS ***").append("\n");
        for (Equipa e : this.equipas.values()) sb.append(e.toString()).append("\n");
        sb.append("\n").append(" *** JOGADORES ***").append("\n");
        for (Jogador j : this.jogadores.values()) sb.append(j.toString());
        sb.append("\n").append(" *** JOGOS ***").append("\n");
        for (Jogo jog : this.jogos) sb.append(jog.toString());
        return sb.toString();
    }

    // Método para adicionar um jogador ao estado atual da aplicação
    public void addJogador(Jogador j){
        this.jogadores.put(j.getNome(),j.clone());
    }

    // Método para adicionar um jogo ao estado atual da aplicação
    public void addJogo(Jogo j){
        this.jogos.add(j.clone());
    }

    // Método para adicionar uma equipa ao estado atual da aplicação
    public void addEquipa(Equipa e){
        this.equipas.put(e.getNome(),e.clone());
    }

    // Método para gravar o estado do jogo em ficheiro
    public void saveEstado() throws IOException {
        FileOutputStream file = new FileOutputStream("Estado.obj");
        ObjectOutputStream out = new ObjectOutputStream(file);
        out.writeObject(this);
        out.flush();
        out.close();
    }

    // Método para remover um jogador
    public void removeJogador(String nome){
        this.jogadores.remove(nome);
    }

    // Método para remover uma equipa
    public void removeEquipa(String nome){
        this.equipas.remove(nome);
    }

    // Método para carregar o estado do jogo de um ficheiro
    public void loadEstadoObj(String file) throws IOException, ClassNotFoundException {
        FileInputStream f = new FileInputStream(file);
        ObjectInputStream in = new ObjectInputStream(f);
        Estado e = (Estado) in.readObject();
        in.close();
        setJogadores(e.getJogadores());
        setJogos(e.getJogos());
        setEquipas(e.getEquipas());
    }
}
