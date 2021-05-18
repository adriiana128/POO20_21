import java.util.Map;

public class Equipa {
    
    //Variaveis de instância
    private String nomeE; //nome da equipa
    private Map<String,Jogador> jogador; //jogadores de uma equipa
    

    /**
     * Construtor vazio
     */
    public Equipa(){
       this.nomeE = "";
       this.jogador = null;
    }

    
    /**
     * Construtor por parametrizado
     */
    public Equipa(String nomeE, Jogador jogador){
        this.nomeE = nomeE;
        this.jogador = jogador;
    }

    /**
     * Construtor por cópia
     */
    public Equipa(Equipa e){
        this.nomeE = e.getNomeE();
        this.jogador = e.getJogador();
       
    }

    //Getters

    public String getNomeE(){
        return this.nomeE;
    }

    public Jogador getJogador(){
        return this.jogador;
    }
    
    //Setters

    public void setNomeE(String ne){
        this.nomeE = ne;
    }

    public void setJogador(Jogador j){
        this.jogador = j;
    }
    
    /**
     * Metodo Equals
     */
    public boolean equals(Object o){
        if (this == o) 
            return true;
    
        if (o == null || o.getClass() != this.getClass()) 
            return false;
    
        Equipa e = (Equipa) o;

        return this.nomeE.equals(e.getNomeE());
    }
  
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("Nome da Equipa: " + this.getNomeE() + "\n");
        sb.append("Jogador: " + this.getJogador().getNomeJ() + "\n");
        return sb.toString();
    }
    
    //Clone
    
    public Equipa clone(){
        return new Equipa(this);
    }
    
} 