import java.util.List;
import java.util.ArrayList;
import java.util.stream.Collectors;
import java.io.Serializable;

public class Laterais extends Jogador {
   
   //Variaveis de instância 
   
   /**
    * Construtor vazio
    */
   public Laterais(){
       super(0,0,0,0,0,0,0,0,0,null,"");
   }

   /**
    * Construtor parametrizado 
    */
   public Laterais(long id, double velocidade, double resistencia,double destreza,
   double impulsao, double jogoCabeca, double remate, double capacidadePasse,
   double habilidade, List<Equipa> historicoEquipa, String nomeJ){
       super(id, velocidade, resistencia, destreza, impulsao, jogoCabeca, remate, 
       capacidadePasse, habilidade, historicoEquipa ,nomeJ);
   }
    
   /**
    * Construtor de cópia 
    */
   public Laterais(Laterais l){
       super(l);
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
        
       Laterais l = (Laterais) o;
        
       return super.equals(l);
   }
   
   //Clone
      
   public Laterais clone (){
       return new Laterais(this);    
   }
    
}