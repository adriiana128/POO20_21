import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class Equipa {
    // Variaveis de instancia
    private String nome; // Nome da equipa
    private Set<Jogador> jogadores; // jogadores que integram a equipa
    private double habilidadeGlobal; // habilidade total da equipa

    // Construtores
    public Equipa(){
        this.nome = "n/a";
        this.jogadores = new HashSet<>();
        this.habilidadeGlobal = 0;
    }

    public Equipa(String nome, Set<Jogador> jog){
        this.nome = nome;
        this.jogadores = new HashSet<>();
        for(Jogador j : jog){
            j.addEquipa(this.nome);
            this.jogadores.add(j.clone());
        }
        this.atualizaHabilidade();
    }

    public Equipa(String nome){
        this.nome = nome;
        this.jogadores = new HashSet<>();
        this.habilidadeGlobal = 0;
    }

    public void atualizaHabilidade(){
        for(Jogador j : this.jogadores) this.habilidadeGlobal += j.getHabilidade();
    }

    public Equipa(Equipa e){
        this.nome = e.getNome();
        setJogadores(e.getJogadores());
        this.habilidadeGlobal = e.getHabilidadeGlobal();
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Set<Jogador> getJogadores() {
        return jogadores.stream().map(Jogador::clone).collect(Collectors.toSet());
    }

    public void setJogadores(Set<Jogador> jogadores) {
        for(Jogador j : jogadores) j.addEquipa(this.nome);
        this.jogadores = jogadores.stream().map(Jogador::clone).collect(Collectors.toSet());
        this.atualizaHabilidade();
    }

    public double getHabilidadeGlobal() {
        return habilidadeGlobal;
    }

    public void setHabilidadeGlobal(double habilidadeGlobal) {
        this.habilidadeGlobal = habilidadeGlobal;
    }

    public void adicionaJogador(Jogador j){
        j.addEquipa(this.nome);
        this.jogadores.add(j.clone());
        this.atualizaHabilidade();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Equipa equipa = (Equipa) o;
        return this.nome.equals(equipa.getNome()) && this.jogadores.equals(equipa.getJogadores());
    }

    @Override
    public String toString() {
        StringBuffer sb = new StringBuffer();
        String str = this.jogadores.toString();
        sb.append("Equipa: ").append(nome).append("\n").append(str);
        return sb.toString();
    }

    public Equipa clone(){
        return new Equipa(this);
    }
}
