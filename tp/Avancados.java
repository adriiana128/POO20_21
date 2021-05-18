import java.util.List;

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
   public Avancados(long id, double velocidade, double resistencia,double destreza,double impulsao, double jogoCabeca, double remate, double capacidadePasse,
                    double habilidade, List<String> historicoEquipa, String nomeJ){
       super(id, velocidade, resistencia, destreza, impulsao, jogoCabeca, remate,capacidadePasse, habilidade, historicoEquipa ,nomeJ);
   }
    
   /**
    * Construtor de cópia 
    */
   public Avancados(Avancados a){
       super(a);
   }

}