import java.util.Set;

public class Medio extends Jogador{
    // Variaveis de instancia
    private int recuperacao;


    // Construtores
    public Medio(){
        super();
        this.recuperacao = -1;
    }

    public Medio(String name, int nr, int vel, int res, int dest, int imp, int cab, int rem, int pass, double hab, Set<String> hist, int rec){
        super(name,nr,vel,res,dest,imp,cab,rem,pass,hab,hist);
        this.recuperacao = rec;
    }

    public Medio(Medio m){
        super(m);
        this.recuperacao = m.getRecuperacao();
    }

    public int getRecuperacao() {
        return recuperacao;
    }

    public void setRecuperacao(int recuperacao) {
        this.recuperacao = recuperacao;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Medio medio = (Medio) o;
        return recuperacao == medio.recuperacao;
    }

    @Override
    public String toString() {
        String str = super.toString();
        StringBuffer sb = new StringBuffer();
        sb.append(str).append("Recuperação: ").append((this.recuperacao)).append(("\n"));
        return sb.toString();
    }

    public Medio clone(){
        return new Medio(this);
    }
}