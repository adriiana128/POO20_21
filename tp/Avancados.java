import java.util.List;
import java.util.ArrayList;
import java.util.stream.Collectors;
import java.io.Serializable;

public class Avancados extends Jogador {
   
   //Variaveis de instância 
   
   /**
    * Construtor vazio
    */
   public Avancados(){
       super(0,0,0,0,0,0,0,0,0,null,"");
   }

   /**
    * Construtor parametrizado 
    */
   public Avancados(long id, double velocidade, double resistencia,double destreza,
   double impulsao, double jogoCabeca, double remate, double capacidadePasse,
   double habilidade, List<Equipa> historicoEquipa, String nomeJ){
       super(id, velocidade, resistencia, destreza, impulsao, jogoCabeca, remate, 
       capacidadePasse, habilidade, historicoEquipa ,nomeJ);
   }
    
   /**
    * Construtor de cópia 
    */
   public Avancados(Avancados a){
       super(a);
   }
    
   //Getters
   
   //Setters
   
   /**
    * Metodo Equals
    */
   public boolean equals(Object o){
       if (this == o)
           return true;
        
       if (o == null || this.getClass() != o.getClass())
           return false;
        
       Avancados a = (Avancados) o;
        
       return super.equals(a);
   }
   
   //Clone
      
   public Avancados clone (){
       return new Avancados(this);    
   }
    
}