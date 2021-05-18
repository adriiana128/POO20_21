import java.util.Set;

public class Avancado extends Jogador{

    // Construtores
    public Avancado(){
        super();
    }

    public Avancado(String name, int nr, int vel, int res, int dest, int imp, int cab, int rem, int pass, double hab, Set<String> hist){
        super(name,nr,vel,res,dest,imp,cab,rem,pass,hab,hist);
    }

    public Avancado(Avancado a){
        super(a);
    }
}