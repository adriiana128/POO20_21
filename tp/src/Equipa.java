import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class Equipa {
    // Variaveis de instancia
    private String nome;
    private Set<Jogador> jogadores;

    // Construtores
    public Equipa(){
        this.nome = "n/a";
        this.jogadores = new HashSet<>();
    }

    public Equipa(String nome, Set<Jogador> jog){
        this.nome = nome;
        for(Jogador j : jog) this.jogadores.add(j.clone());
    }

    public Equipa(Equipa e){
        this.nome = e.getNome();
        setJogadores(e.getJogadores());
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
        this.jogadores = jogadores.stream().map(Jogador::clone).collect(Collectors.toSet());
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