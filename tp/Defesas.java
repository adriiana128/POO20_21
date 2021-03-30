import java.util.List;
import java.util.ArrayList;
import java.util.stream.Collectors;
import java.io.Serializable;

public class Defesas extends Jogador {
   
   //Variaveis de instância 
   
   /**
    * Construtor vazio
    */
   public Defesas(){
       super(0,0,0,0,0,0,0,0,0,null,"");
   }

   /**
    * Construtor parametrizado 
    */
   public Defesas(long id, double velocidade, double resistencia,double destreza,
   double impulsao, double jogoCabeca, double remate, double capacidadePasse,
   double habilidade, List<Equipa> historicoEquipa, String nomeJ){
       super(id, velocidade, resistencia, destreza, impulsao, jogoCabeca, remate, 
       capacidadePasse, habilidade, historicoEquipa ,nomeJ);
   }
    
   /**
    * Construtor de cópia 
    */
   public Defesas(Defesas d){
       super(d);
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
        
       Defesas d = (Defesas) o;
        
       return super.equals(d);
   }
   
   //Clone
      
   public Defesas clone (){
       return new Defesas(this);    
   }
    
}