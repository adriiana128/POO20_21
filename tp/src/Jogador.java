import java.io.Serializable;
import java.text.DecimalFormat;
import java.util.HashSet;
import java.util.Set;

public abstract class Jogador implements Serializable {
    //Variaveis de instância
    private String nome; // nome do jogador
    private int nrCamisola; // número da camisola do jogador
    private int velocidade; // velocidade do jogador
    private int resistencia; // resistência do jogador
    private int destreza; // destreza do jogador
    private int impulsao; // impulsão do jogador
    private int cabeca; // jogo de cabeça do jogador
    private int remate; // remate do jogador
    private int passe; // capacidade de passe do jogador
    private double habilidade; // habilidade geral do jogador
    private Set<String> historicoEquipas; // lista com nome das equipas às quais o jogador já pertenceu

    // Construtores
    public Jogador(){
        this.nome = "n/a";
        this.nrCamisola = -1;
        this.velocidade = 0;
        this.resistencia = 0;
        this.destreza = 0;
        this.impulsao = 0;
        this.cabeca = 0;
        this.remate = 0;
        this.passe = 0;
        this.habilidade = 0;
        this.historicoEquipas = new HashSet<>();
    }

    public Jogador(String name, int nr, int vel, int res, int dest, int imp, int cab, int rem, int pass){
        this.nome = name;
        this.nrCamisola = nr;
        this.velocidade = vel;
        this.resistencia = res;
        this.destreza = dest;
        this.impulsao = imp;
        this.cabeca = cab;
        this.remate = rem;
        this.passe = pass;
        this.habilidade = this.calculaHabilidade();
        this.historicoEquipas = new HashSet<>();
    }

    public Jogador(Jogador j){
        this.nome = j.getNome();
        this.nrCamisola = j.getNrCamisola();
        this.velocidade = j.getVelocidade();
        this.resistencia = j.getResistencia();
        this.destreza = j.getDestreza();
        this.impulsao = j.getImpulsao();
        this.cabeca = j.getCabeca();
        this.remate = j.getRemate();
        this.passe = j.getPasse();
        this.habilidade = j.getHabilidade();
        this.historicoEquipas = new HashSet<>();
        setHistoricoEquipas(j.getHistoricoEquipas());
    }

    // Getters e Setters
    public String getNome() {
        return nome;
    }

    public int getNrCamisola() {
        return nrCamisola;
    }

    public int getVelocidade() {
        return velocidade;
    }

    public int getResistencia() {
        return resistencia;
    }

    public int getDestreza() {
        return destreza;
    }

    public int getImpulsao() {
        return impulsao;
    }

    public int getCabeca() {
        return cabeca;
    }

    public int getRemate() {
        return remate;
    }

    public int getPasse() {
        return passe;
    }

    public double getHabilidade() {
        return habilidade;
    }

    public Set<String> getHistoricoEquipas() {
        return new HashSet<>(this.historicoEquipas);
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setNrCamisola(int nrCamisola) {
        this.nrCamisola = nrCamisola;
    }

    public void setVelocidade(int velocidade) {
        this.velocidade = velocidade;
    }

    public void setResistencia(int resistencia) {
        this.resistencia = resistencia;
    }

    public void setDestreza(int destreza) {
        this.destreza = destreza;
    }

    public void setImpulsao(int impulsao) {
        this.impulsao = impulsao;
    }

    public void setCabeca(int cabeca) {
        this.cabeca = cabeca;
    }

    public void setRemate(int remate) {
        this.remate = remate;
    }

    public void setPasse(int passe) {
        this.passe = passe;
    }

    public void setHabilidade(double habilidade) {
        this.habilidade = habilidade;
    }

    public void setHistoricoEquipas(Set<String> histEq) {
        for(String nome : histEq) this.historicoEquipas.add(nome);
    }

    public void addEquipa(String eq){
        this.historicoEquipas.add(eq);
    }

    // Equals
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Jogador jogador = (Jogador) o;
        return nrCamisola == jogador.nrCamisola && velocidade == jogador.velocidade
                && resistencia == jogador.resistencia && destreza == jogador.destreza
                && impulsao == jogador.impulsao && cabeca == jogador.cabeca && remate == jogador.remate
                && passe == jogador.passe && habilidade == jogador.habilidade
                && nome.equals(jogador.nome) && historicoEquipas.equals(jogador.historicoEquipas);
    }

    // toString
    @Override
    public String toString() {
        DecimalFormat df2 = new DecimalFormat("#.##");
        StringBuffer sb = new StringBuffer();
        sb.append(this.nome).append(" | ")
                .append("Velocidade: ").append(this.velocidade).append(" | ")
                .append("Resistência: ").append(this.resistencia).append(" | ")
                .append("Destreza: ").append(this.destreza).append(" | ")
                .append("Impulsão: ").append(this.impulsao).append(" | ")
                .append("Cabeça: ").append(this.cabeca).append(" | ")
                .append("Remate: ").append(this.remate).append(" | ")
                .append("Passe: ").append(this.passe).append(" | ");
        return sb.toString();
    }

    // toString para apresentação da informação básica do jogador
    public String toStringJogadorSimples() {
        DecimalFormat df2 = new DecimalFormat("#.##");
        StringBuffer sb = new StringBuffer();
        sb.append("#").append(nrCamisola).append(" | ")
                .append(this.nome).append(" | ")
                .append("Habilidade: ").append(df2.format(this.habilidade));
        return sb.toString();
    }

    // toString para as equipas
    public String toStringEquipas(){
        StringBuffer sb = new StringBuffer();
        sb.append("Histórico: ").append(this.getHistoricoEquipas()).append("\n");
        return sb.toString();
    }

    // Assinatura de método abstrato clone
    public abstract Jogador clone();

    // Assinatura de método abstrato de cálculo da habilidade dos jogadores
    public abstract double calculaHabilidade();

    // Método para verificar o tipo de jogador
    public int tipoJogador(Jogador j){
        int res = -1;
        if (j instanceof Avancado) res = 1;
        else if (j instanceof Defesa) res = 2;
             else if (j instanceof GuardaRedes) res = 3;
                  else if (j instanceof Lateral) res = 4;
                       else if (j instanceof Medio) res = 5;
        return res;
    }
}
