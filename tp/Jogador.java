import java.util.List;
import java.util.ArrayList;
import java.util.stream.Collectors;
import java.lang.Object;
import java.io.Serializable;
import java.util.Arrays; 

public class Jogador {
    
   //Variaveis de instância
   private long id; //nº/id do jogador
   private double velocidade; //velocidade do jogador
   private double resistencia; //resistência do jogador
   private double destreza; //destreza do jogador
   private double impulsao; //impulsão do jogador
   private double jogoCabeca; //jogo de cabeça do jogador
   private double remate; //remate do jogador
   private double capacidadePasse; //capacidade de passe do jogador
   private double habilidade; //
   private List<Equipa> historicoEquipa; 
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
        this.historicoEquipa = new ArrayList<Equipa>();
        this.nomeJ = "";
   }
    
   /**
    * Construtor parametrizado 
    */
    public Jogador(long id, double velocidade, double resistencia, double destreza,
    double impulsao, double jogoCabeca, double remate, double capacidadePasse, 
    double habilidade, List<Equipa> hist, String nomeJ){
        this.id = id;
        this.velocidade = velocidade;
        this.resistencia = resistencia;
        this.destreza = destreza;
        this.impulsao = impulsao;
        this.jogoCabeca = jogoCabeca;
        this.remate = remate;
        this.capacidadePasse = capacidadePasse;
        this.habilidade = habilidade;
        this.historicoEquipa = hist.stream().collect(Collectors.toList());
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
        this.historicoEquipa = j.getHist();
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
   
   public List<Equipa> getHist(){
       if(this.historicoEquipa == null) return new ArrayList<Equipa>();
       else return this.historicoEquipa;
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
   
   public void setHist(ArrayList<Equipa> hist){
       this.historicoEquipa = hist.stream().collect(Collectors.toList());
   }
   
   public void setNome(String nj){
        this.nomeJ = nj;
   }
   
   /**
    * Metodo Equals
    */
   public boolean equals(Object o){
      if (this == o)
            return true;
            
      if (o == null || this.getClass() != o.getClass())
            return false;
            
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
        sb.append("Nº Jogador: ").append(id).append("\n");
        sb.append("Velocidade: " + this.getVelocidade() + "\n");
        sb.append("Resistência: " + this.getResistencia() + "\n");
        sb.append("Destreza: " + this.getDestreza() + "\n");
        sb.append("Impulsão: " + this.getImpulsao() + "\n");
        sb.append("Jogo de Cabeça: " + this.getJogoCabeca() + "\n");
        sb.append("Remate: " + this.getRemate() + "\n");
        sb.append("Capacidade de Passe: " + this.getCapacidadePasse() + "\n");
        sb.append("Habilidade: " + this.getHabilidade() + "\n");
        sb.append("Histórico de Equipas: "+ this.getHist() + "\n");
        sb.append("Nome do Jogador: " + this.getNomeJ() + "\n");
        return sb.toString();
   }

   public Jogador clone(){
       return new Jogador(this);
   }
}