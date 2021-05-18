import java.util.List;

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
   public Laterais(long id, double velocidade, double resistencia,double destreza,double impulsao, double jogoCabeca, double remate, double capacidadePasse,
                   double habilidade, List<String> historicoEquipa, String nomeJ){
       super(id, velocidade, resistencia, destreza, impulsao, jogoCabeca, remate,capacidadePasse, habilidade, historicoEquipa ,nomeJ);
   }
    
   /**
    * Construtor de cópia 
    */
   public Laterais(Laterais l){
       super(l);
   }
    
}