import java.util.List;
import java.util.ArrayList;
import java.util.stream.Collectors;
import java.io.Serializable;

public class GuardaRedes extends Jogador {
   
   //Variaveis de instância 
   private double elasticidade; //elasticidade do GR
   
   /**
    * Construtor vazio
    */
   public GuardaRedes(){
       super(0,0,0,0,0,0,0,0,0,null,"");
       this.elasticidade = 0;
   }

   /**
    * Construtor parametrizado 
    */
   public GuardaRedes(long id, double velocidade, double resistencia,double destreza,
   double impulsao, double jogoCabeca, double remate, double capacidadePasse,
   double habilidade, List<Equipa> historicoEquipa, String nomeJ, double elasticidade){
       super(id, velocidade, resistencia, destreza, impulsao, jogoCabeca, remate, 
       capacidadePasse, habilidade, historicoEquipa ,nomeJ);
       this.elasticidade = elasticidade;
   }
    
   /**
    * Construtor de cópia 
    */
   public GuardaRedes(GuardaRedes g){
       super(g);
       this.elasticidade = g.getElasticidade();
   }
    
   //Getters
    
   public double getElasticidade(){
       return this.elasticidade;
   }
   
   //Setters
    
   public void setElasticidade(double el){
       this.elasticidade = el;
   }
   
   /**
    * Metodo Equals
    */
   public boolean equals(Object o){
       if (this == o)
           return true;
        
       if (o == null || this.getClass() != o.getClass())
           return false;
        
       GuardaRedes g = (GuardaRedes) o;
        
       return super.equals(g) &&
       this.elasticidade == g.getElasticidade();
   }
   
   public String toString(){
       StringBuilder sb = new StringBuilder(); 
       sb.append("Elasticidade: " + this.getElasticidade() + "\n");
       return sb.toString();
   }
   
   //Clone
      
   public GuardaRedes clone (){
       return new GuardaRedes(this);    
   }
    
}