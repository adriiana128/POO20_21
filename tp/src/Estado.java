import java.io.IOException;
import java.io.Serializable;
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
        this.equipas = new HashMap<String,Equipa>(); //Nome, Equipa
        this.jogadores = new HashMap<String,Jogador>(); //Nome, Jogador
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
        List<String> linhas = lerFicheiro("input.txt");
        Equipa ultima = null;
        Jogador j = null;
        Map<String, Equipa> equipas = new HashMap<>(); // Nome, Equipa
        Map<String, Jogador> jogadores = new HashMap<>(); // Nome, Jogador
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
                    if (ultima == null) throw new LinhaIncorretaException(); //we need to insert the player into the team
                    j.addEquipa(ultima.getNome());
                    jogadores.put(j.getNome(), j.clone());
                    ultima.adicionaJogador(j.clone()); //if no team was parsed previously, file is not well-formed
                    break;
                case "Defesa":
                    j = Defesa.parse(linhaPartida[1]);
                    if (ultima == null) throw new LinhaIncorretaException(); //we need to insert the player into the team
                    j.addEquipa(ultima.getNome());
                    jogadores.put(j.getNome(), j.clone());
                    ultima.adicionaJogador(j.clone()); //if no team was parsed previously, file is not well-formed
                    break;
                case "Medio":
                    j = Medio.parse(linhaPartida[1]);
                    if (ultima == null) throw new LinhaIncorretaException(); //we need to insert the player into the team
                    j.addEquipa(ultima.getNome());
                    jogadores.put(j.getNome(), j.clone());
                    ultima.adicionaJogador(j.clone()); //if no team was parsed previously, file is not well-formed
                    break;
                case "Lateral":
                    j = Lateral.parse(linhaPartida[1]);
                    if (ultima == null) throw new LinhaIncorretaException(); //we need to insert the player into the team
                    j.addEquipa(ultima.getNome());
                    jogadores.put(j.getNome(), j.clone());
                    ultima.adicionaJogador(j.clone()); //if no team was parsed previously, file is not well-formed
                    break;
                case "Avancado":
                    j = Avancado.parse(linhaPartida[1]);
                    if (ultima == null) throw new LinhaIncorretaException(); //we need to insert the player into the team
                    j.addEquipa(ultima.getNome());
                    jogadores.put(j.getNome(), j.clone());
                    ultima.adicionaJogador(j.clone()); //if no team was parsed previously, file is not well-formed
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
        return this.equipas.values().stream().collect(Collectors.toMap(Equipa::getNome, Equipa::clone));
    }

    public void setEquipas(Map<String, Equipa> equipas) {
        this.equipas = equipas.values().stream().collect(Collectors.toMap(Equipa::getNome,Equipa::clone));
    }

    public Map<String, Jogador> getJogadores() {
        return jogadores.values().stream().collect(Collectors.toMap(Jogador::getNome, Jogador::clone));
    }

    public void setJogadores(Map<String, Jogador> jogadores) {
        this.jogadores = jogadores.values().stream().collect(Collectors.toMap(Jogador::getNome, Jogador::clone));
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
}
