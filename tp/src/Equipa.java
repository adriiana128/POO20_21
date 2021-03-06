import java.io.Serializable;
import java.text.DecimalFormat;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class Equipa implements Serializable {
    // Variaveis de instancia
    private String nome; // nome da equipa
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

    public Equipa(Equipa e){
        this.nome = e.getNome();
        setJogadores(e.getJogadores());
        this.habilidadeGlobal = e.getHabilidadeGlobal();
    }

    // Getters e Setters
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

    // Método para atualizar a habilidade da equipa
    public void atualizaHabilidade(){
        this.habilidadeGlobal = 0;
        for(Jogador j : this.jogadores) this.habilidadeGlobal += j.calculaHabilidade();
    }

    // Método para adicionar um jogador a uma equipa
    public void adicionaJogador(Jogador j){
        j.addEquipa(this.nome);
        this.jogadores.add(j.clone());
        this.atualizaHabilidade();
    }

    // Equals
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Equipa equipa = (Equipa) o;
        return this.habilidadeGlobal == equipa.habilidadeGlobal && this.nome.equals(equipa.nome)
                && this.jogadores.equals(equipa.jogadores);
    }

    // toString
    @Override
    public String toString() {
        DecimalFormat df2 = new DecimalFormat("#.##");
        StringBuffer sb = new StringBuffer();
        sb.append("Equipa: ").append(nome).append("\n");
        for(Jogador jog : this.jogadores) sb.append(jog.toStringJogadorSimples()).append("\n");
        return sb.toString();
    }

    // toString para a habilidade global formatada
    public String toStringHabilidadeGlobal(){
        DecimalFormat df2 = new DecimalFormat("#.##");
        StringBuffer sb = new StringBuffer();
        sb.append("Habilidade global da equipa: ").append(df2.format(this.habilidadeGlobal));
        return sb.toString();
    }

    // Clone
    public Equipa clone(){
        return new Equipa(this);
    }

    // Parser para equipa
    public static Equipa parse(String input){
        String[] campos = input.split(",");
        return new Equipa(campos[0]);
    }
}
