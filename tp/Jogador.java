import java.lang.Object;
import java.util.ArrayList;
import java.util.List;

public class Jogador{
    
   //Variaveis de instância
   private long id; //nº/id do jogador
   private double velocidade; //velocidade do jogador
   private double resistencia; //resistência do jogador
   private double destreza; //destreza do jogador
   private double impulsao; //impulsão do jogador
   private double jogoCabeca; //jogo de cabeça do jogador
   private double remate; //remate do jogador
   private double capacidadePasse; //capacidade de passe do jogador
   private double habilidade; // habilidade geral do jogador
   private List<String> historicoEquipa; // lista com nome das equipas às quais o jogador já pertenceu
   private String nomeJ; //nome do jogador

   /**
    * Construtor vazio
    */
    public Jogador(){
        this.id = 0;
        this.velocidade = 0;
        this.resistencia = 0;
        this.destreza = 0;
        this.impulsao = 0;
        this.jogoCabeca = 0;
        this.remate = 0;
        this.capacidadePasse = 0;
        this.habilidade = 0;
        this.historicoEquipa = new ArrayList<>();
        this.nomeJ = "";
   }
    
   /**
    * Construtor parametrizado 
    */
    public Jogador(long id, double velocidade, double resistencia, double destreza,
    double impulsao, double jogoCabeca, double remate, double capacidadePasse, 
    double habilidade, List<String> hist, String nomeJ){
        this.id = id;
        this.velocidade = velocidade;
        this.resistencia = resistencia;
        this.destreza = destreza;
        this.impulsao = impulsao;
        this.jogoCabeca = jogoCabeca;
        this.remate = remate;
        this.capacidadePasse = capacidadePasse;
        this.habilidade = habilidade;
        this.setHist(hist);
        this.nomeJ = nomeJ;
   }
    
   /**
    * Construtor de cópia 
    */
    public Jogador(Jogador j){
        this.id = j.getId();
        this.velocidade = j.getVelocidade();
        this.resistencia = j.getResistencia();
        this.destreza = j.getDestreza();
        this.impulsao = j.getImpulsao();
        this.jogoCabeca = j.getJogoCabeca();
        this.remate = j.getRemate();
        this.capacidadePasse = j.getCapacidadePasse();
        this.habilidade = j.getHabilidade();
        this.setHist(j.getHist());
        this.nomeJ = j.getNomeJ();
   }
    
   //Getters
   
   public long getId(){
       return this.id;
   }
   
   public double getVelocidade(){
       return this.velocidade;
   }
    
   public double getResistencia(){
        return this.resistencia;
   }

   public double getDestreza(){
       return this.destreza;
   }
    
   public double getImpulsao(){
        return this.impulsao;
   }
    
   public double getJogoCabeca(){
        return this.jogoCabeca;
   }
   
   public double getRemate(){
        return this.remate;
   }

   public double getCapacidadePasse(){
        return this.capacidadePasse;
   }   
   
   public double getHabilidade(){
        return this.habilidade;
   }
   
   public List<String> getHist(){
        List<String> ret = new ArrayList<>();
        for (String equipa : historicoEquipa) ret.add(equipa);
        return ret;
    }
   
   public String getNomeJ(){
        return this.nomeJ;
   }
   
   //Setters
    
   public void setId(long id){
       this.id = id;
   }
   
   public void setVelocidade(double v){
       this.velocidade = v;
   }
    
   public void setResistencia(double r){
       this.resistencia = r;
   }
    
   public void setDestreza(double d){
        this.destreza = d;
   }
   
   public void setImpulsao(double i){
        this.impulsao = i;
   }
   
   public void setJogoCabeca(double jc){
       this.jogoCabeca = jc;
   }
   
   public void setRemate(double rm){
       this.remate = rm;
   }
   
   public void setCapacidadePasse(double cp){
       this.capacidadePasse = cp;
   }
   
   public void setHabilidade(double h){
       this.habilidade = h;
   }
   
   public void setHist(List<String> hist){
        for(String equipa : hist) this.historicoEquipa.add(equipa);
   }
   
   public void setNome(String nj){
        this.nomeJ = nj;
   }
   
   /**
    * Metodo Equals
    */
   public boolean equals(Object o){
      if (this == o) return true;
      if (o == null || this.getClass() != o.getClass()) return false;
            
      Jogador j = (Jogador) o;
      return this.id == j.getId() &&
                  this.velocidade == j.getVelocidade() &&
                  this.resistencia == j.getResistencia() &&
                  this.destreza == j.getDestreza() &&
                  this.impulsao == j.getImpulsao() &&
                  this.jogoCabeca == j.getJogoCabeca() &&
                  this.remate == j.getRemate() &&
                  this.capacidadePasse == j.getCapacidadePasse() &&
                  this.habilidade == j.getHabilidade() &&
                  j.getHist().equals(historicoEquipa) &&
                  this.nomeJ.equals(j.getNomeJ());
   }
    
   public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("Nº Jogador: ").append(id).append("\n")
                .append("Velocidade: ").append(this.getVelocidade()).append("\n")
                .append("Resistência: ").append(this.getResistencia()).append("\n")
                .append("Destreza: ").append(this.getDestreza()).append("\n")
                .append("Impulsão: ").append(this.getImpulsao()).append("\n")
                .append("Jogo de Cabeça: ").append(this.getJogoCabeca()).append("\n")
                .append("Remate: ").append(this.getRemate()).append("\n")
                .append("Capacidade de Passe: ").append(this.getCapacidadePasse()).append("\n")
                .append("Habilidade: ").append(this.getHabilidade()).append("\n")
                .append("Histórico de Equipas: ").append(this.getHist()).append("\n")
                .append("Nome do Jogador: ").append(this.getNomeJ()).append("\n");
        return sb.toString();
   }

   public Jogador clone(){
       return new Jogador(this);
   }
}