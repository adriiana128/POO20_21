import java.util.Set;

public class GuardaRedes extends Jogador{

    //Variaveis de instância
    private int elasticidade; //elasticidade do GR
    private int defesa; // capacidade de defesa da baliza

    // Construtores

    public GuardaRedes(){
        super();
        this.elasticidade = -1;
        this.defesa = -1;
    }

    public GuardaRedes(String name, int nr, int vel, int res, int dest, int imp, int cab, int rem, int pass, int fin, String eq, int elas, int def){
        super(name,nr,vel,res,dest,imp,cab,rem,pass,fin,eq);
        this.elasticidade = elas;
        this.defesa = def;
    }

    public GuardaRedes(GuardaRedes gr){
        super(gr);
        this.elasticidade = gr.getElasticidade();
        this.defesa = gr.getDefesa();
    }

    // Getters e Setters
    public int getElasticidade() {
        return elasticidade;
    }

    public int getDefesa() {
        return defesa;
    }

    public void setDefesa(int defesa) {
        this.defesa = defesa;
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
        return elasticidade == gr.elasticidade && defesa == gr.defesa;
    }

    @Override
    public String toString() {
        String str = super.toString();
        StringBuffer sb = new StringBuffer();
        sb.append(str).append("Elasticidade: ").append(this.elasticidade).append("\n")
                .append("Defesa: ").append(this.defesa).append("\n");
        return sb.toString();
    }

    public GuardaRedes clone(){
        return new GuardaRedes(this);
    }

    public double calculaHabilidade() {
        return 0.2*super.getVelocidade() + 0.5*super.getResistencia() + 2*super.getDestreza() + 0.5*super.getImpulsao()
                + 0.3*super.getCabeca() + 0.2*super.getRemate() + 0.8*super.getPasse() + 0.4*super.getFinta() + 2*this.elasticidade + 3*this.defesa;
    }
}
