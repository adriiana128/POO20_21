import java.util.List;
import java.util.ArrayList;
import java.util.stream.Collectors;
import java.io.Serializable;

public class Medios extends Jogador {
   
   //Variaveis de instância 
   
   /**
    * Construtor vazio
    */
   public Medios(){
       super(0,0,0,0,0,0,0,0,0,null,"");
   }

   /**
    * Construtor parametrizado 
    */
   public Medios(long id, double velocidade, double resistencia,double destreza,double impulsao, double jogoCabeca, double remate, double capacidadePasse,
                 double habilidade, List<String> historicoEquipa, String nomeJ){
       super(id, velocidade, resistencia, destreza, impulsao, jogoCabeca, remate,capacidadePasse, habilidade, historicoEquipa ,nomeJ);
   }
    
   /**
    * Construtor de cópia 
    */
   public Medios(Medios m){
       super(m);
   }
    
}