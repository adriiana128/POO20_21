public class GuardaRedes extends Jogador{
    //Variaveis de instância
    private int elasticidade; //elasticidade do GR
    private int defesa; // capacidade de defesa da baliza

    // Construtores
    public GuardaRedes(){
        super();
        this.elasticidade = 0;
        this.defesa = 0;
    }

    public GuardaRedes(String name, int nr, int vel, int res, int dest, int imp, int cab, int rem, int pass, int elas, int def){
        super(name,nr,vel,res,dest,imp,cab,rem,pass);
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

    // Equals
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        GuardaRedes gr = (GuardaRedes) o;
        return elasticidade == gr.elasticidade && defesa == gr.defesa;
    }

    // toString
    @Override
    public String toString() {
        String jog = super.toString();
        String eq = super.toStringEquipas();
        StringBuffer sb = new StringBuffer();
        sb.append(jog).append("Elasticidade: ").append(this.elasticidade).append(" | ")
                .append("Defesa: ").append(this.defesa).append(" | ")
                .append("Posição: ").append(this.getClass().getSimpleName()).append(" | ")
                .append(eq);
        return sb.toString();
    }

    // Clone
    public GuardaRedes clone(){
        return new GuardaRedes(this);
    }

    // Método para cálculo da habilidade de um guarda-redes
    public double calculaHabilidade() {
        return 0.2*super.getVelocidade() + 0.5*super.getResistencia() + 2*super.getDestreza() + 0.5*super.getImpulsao()
                + 0.3*super.getCabeca() + 0.2*super.getRemate() + 0.8*super.getPasse() + 2*this.elasticidade + 3*this.defesa;
    }

    // Parser para guarda-redes
    public static GuardaRedes parse(String input){
        String[] campos = input.split(",");
        return new GuardaRedes(campos[0], Integer.parseInt(campos[1]),
                Integer.parseInt(campos[2]),
                Integer.parseInt(campos[3]),
                Integer.parseInt(campos[4]),
                Integer.parseInt(campos[5]),
                Integer.parseInt(campos[6]),
                Integer.parseInt(campos[7]),
                Integer.parseInt(campos[8]),
                Integer.parseInt(campos[9]),
                50);
    }
}
