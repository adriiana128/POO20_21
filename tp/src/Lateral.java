import java.util.Objects;
import java.util.Set;

public class Lateral extends Jogador{
    //Variaveis de instância
    private int cruzamento;

    // Construtores
    public Lateral(){
        super();
        this.cruzamento = -1;
    }

    public Lateral(String name, int nr, int vel, int res, int dest, int imp, int cab, int rem, int pass, double hab, Set<String> hist, int cruz){
        super(name,nr,vel,res,dest,imp,cab,rem,pass,hab,hist);
        this.cruzamento = cruz;
    }

    public Lateral(Lateral l){
        super(l);
        this.cruzamento = l.getCruzamento();
    }

    public int getCruzamento() {
        return cruzamento;
    }

    public void setCruzamento(int cruzamento) {
        this.cruzamento = cruzamento;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Lateral lateral = (Lateral) o;
        return cruzamento == lateral.cruzamento;
    }

    @Override
    public String toString() {
        String str = super.toString();
        StringBuffer sb = new StringBuffer();
        sb.append(str).append("Cruzamento: ").append((this.cruzamento)).append(("\n"));
        return sb.toString();
    }

    public Lateral clone(){
        return new Lateral(this);
    }
}


