import java.util.Set;

public class Defesa extends Jogador{

    // Construtores
    public Defesa(){
        super();
    }

    public Defesa(String name, int nr, int vel, int res, int dest, int imp, int cab, int rem, int pass, int fin, String eq){
        super(name,nr,vel,res,dest,imp,cab,rem,pass,fin,eq);
    }

    public Defesa(Defesa d){
        super(d);
    }

    public Defesa clone(){
        return new Defesa(this);
    }

    public double calculaHabilidade() {
        return 0.7*super.getVelocidade() + super.getResistencia() + 2*super.getDestreza() + 0.7*super.getImpulsao()
                + 0.5*super.getCabeca() + 0.4*super.getRemate() + super.getPasse() + 0.8*super.getFinta();
    }
}
