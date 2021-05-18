import java.util.Set;

public class GuardaRedes extends Jogador{

    //Variaveis de instância
    private int elasticidade; //elasticidade do GR

    // Construtores

    public GuardaRedes(){
        super();
        this.elasticidade = -1;
    }

    public GuardaRedes(String name, int nr, int vel, int res, int dest, int imp, int cab, int rem, int pass, double hab, Set<String> hist, int elas){
        super(name,nr,vel,res,dest,imp,cab,rem,pass,hab,hist);
        this.elasticidade = elas;
    }

    public GuardaRedes(GuardaRedes gr){
        super(gr);
        this.elasticidade = gr.getElasticidade();

    }

    // Getters e Setters
    public int getElasticidade() {
        return elasticidade;
    }

    public void setElasticidade(int elasticidade) {
        this.elasticidade = elasticidade;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        GuardaRedes gr = (GuardaRedes) o;
        return elasticidade == gr.elasticidade;
    }

    @Override
    public String toString() {
        String str = super.toString();
        StringBuffer sb = new StringBuffer();
        sb.append(str).append("Elasticidade: ").append((this.elasticidade)).append(("\n"));
        return sb.toString();
    }

    public GuardaRedes clone(){
        return new GuardaRedes(this);
    }
}
