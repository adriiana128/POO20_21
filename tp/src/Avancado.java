import java.util.Set;

public class Avancado extends Jogador{

    // Construtores
    public Avancado(){
        super();
    }

    public Avancado(String name, int nr, int vel, int res, int dest, int imp, int cab, int rem, int pass, int fin, String eq){
        super(name,nr,vel,res,dest,imp,cab,rem,pass,fin,eq);
    }

    public Avancado(Avancado a){
        super(a);
    }

    public Avancado clone(){
        return new Avancado(this);
    }

    public double calculaHabilidade() {
        return super.getVelocidade() + 0.8*super.getResistencia() + 0.7*super.getDestreza() + super.getImpulsao()
                + super.getCabeca() + super.getRemate() + 0.7*super.getPasse() + 2*super.getFinta();
    }

}