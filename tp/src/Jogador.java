import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public abstract class Jogador {
    //Variaveis de instância
    private String id; //id do utilizador
    private String nome; // nome do jogador
    private int nrCamisola; // número da camisola do jogador
    private int velocidade; // velocidade do jogador
    private int resistencia; // resistência do jogador
    private int destreza; // destreza do jogador
    private int impulsao; // impulsão do jogador
    private int cabeca; // jogo de cabeça do jogador
    private int remate; // remate do jogador
    private int passe; // capacidade de passe do jogador
    private int finta; // capacidade de fintar do jogador
    private double habilidade; // habilidade geral do jogador
    private Set<String> historicoEquipas; // lista com nome das equipas às quais o jogador já pertenceu

    // Construtores
    public Jogador(){
        this.id = "";
        this.nome = "n/a";
        this.nrCamisola = -1;
        this.velocidade = -1;
        this.resistencia = -1;
        this.destreza = -1;
        this.impulsao = -1;
        this.cabeca = -1;
        this.remate = -1;
        this.passe = -1;
        this.finta = -1;
        this.habilidade = -1;
        this.historicoEquipas = new HashSet<>();
    }

    public Jogador(String iD, String name, int nr, int vel, int res, int dest, int imp, int cab, int rem, int pass, int fin, String eq){
        this.id = iD;
        this.nome = name;
        this.nrCamisola = nr;
        this.velocidade = vel;
        this.resistencia = res;
        this.destreza = dest;
        this.impulsao = imp;
        this.cabeca = cab;
        this.remate = rem;
        this.passe = pass;
        this.finta = fin;
        this.habilidade = this.calculaHabilidade();
        this.historicoEquipas = new HashSet<>();
        this.historicoEquipas.add(eq);
    }

    public Jogador(Jogador j){
        this.id = j.getId();
        this.nome = j.getNome();
        this.nrCamisola = j.getNrCamisola();
        this.velocidade = j.getVelocidade();
        this.resistencia = j.getResistencia();
        this.destreza = j.getDestreza();
        this.impulsao = j.getImpulsao();
        this.cabeca = j.getCabeca();
        this.remate = j.getRemate();
        this.passe = j.getPasse();
        this.finta = j.getFinta();
        this.habilidade = j.getHabilidade();
        this.historicoEquipas = new HashSet<>();
        setHistoricoEquipas(j.getHistoricoEquipas());
    }

    // Getters e Setters
    public String getId() { return this.id; }

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

    public int getFinta() {
        return finta;
    }

    public double getHabilidade() {
        return habilidade;
    }

    public Set<String> getHistoricoEquipas() {
        return new HashSet<>(this.historicoEquipas);
    }

    public void setId(String id) { this.id = id; }

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

    public void setFinta(int finta) {
        this.finta = finta;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Jogador jogador = (Jogador) o;
        return Objects.equals(id, jogador.id) && nrCamisola == jogador.nrCamisola
                && velocidade == jogador.velocidade && resistencia == jogador.resistencia
                && destreza == jogador.destreza && impulsao == jogador.impulsao
                && cabeca == jogador.cabeca && remate == jogador.remate
                && passe == jogador.passe && finta == jogador.finta && Double.compare(jogador.habilidade, habilidade) == 0
                && Objects.equals(nome, jogador.nome) && Objects.equals(historicoEquipas, jogador.historicoEquipas);
    }

    @Override
    public String toString() {
        StringBuffer sb = new StringBuffer();
        sb.append ("ID Jogador: ").append(id).append("\n")
                .append("Nº Jogador: ").append(nrCamisola).append("\n")
                .append("Nome do Jogador: ").append(this.getNome()).append("\n")
                .append("Velocidade: ").append(this.getVelocidade()).append("\n")
                .append("Resistência: ").append(this.getResistencia()).append("\n")
                .append("Destreza: ").append(this.getDestreza()).append("\n")
                .append("Impulsão: ").append(this.getImpulsao()).append("\n")
                .append("Jogo de Cabeça: ").append(this.getCabeca()).append("\n")
                .append("Remate: ").append(this.getRemate()).append("\n")
                .append("Capacidade de Passe: ").append(this.getPasse()).append("\n")
                .append("Capacidade de fintar: ").append(this.getFinta()).append("\n")
                .append("Habilidade: ").append(this.getHabilidade()).append("\n")
                .append("Histórico de Equipas: ").append(this.getHistoricoEquipas()).append("\n");
        return sb.toString();
    }

    public abstract Jogador clone();

    public abstract double calculaHabilidade();
}
