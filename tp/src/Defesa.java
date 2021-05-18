import java.util.Set;

public class Defesa extends Jogador{
    // Construtores
    public Defesa(){
        super();
    }

    public Defesa(String name, int nr, int vel, int res, int dest, int imp, int cab, int rem, int pass, double hab, Set<String> hist){
        super(name,nr,vel,res,dest,imp,cab,rem,pass,hab,hist);
    }

    public Defesa(Defesa d){
        super(d);
    }
}
