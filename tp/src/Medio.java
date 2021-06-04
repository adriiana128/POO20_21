public class Medio extends Jogador{
    // Variaveis de instancia
    private int recuperacao;

    // Construtores
    public Medio(){
        super();
        this.recuperacao = 0;
    }
    public Medio(String name, int nr, int vel, int res, int dest, int imp, int cab, int rem, int pass, int rec){
        super(name,nr,vel,res,dest,imp,cab,rem,pass);
        this.recuperacao = rec;
    }

    public Medio(Medio m){
        super(m);
        this.recuperacao = m.getRecuperacao();
    }

    // Getters e Setters
    public int getRecuperacao() {
        return recuperacao;
    }

    public void setRecuperacao(int recuperacao) {
        this.recuperacao = recuperacao;
    }

    // Equals
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Medio medio = (Medio) o;
        return recuperacao == medio.recuperacao;
    }

    // toString
    @Override
    public String toString() {
        String str = super.toString();
        StringBuffer sb = new StringBuffer();
        sb.append(str).append("Recuperação: ").append(this.recuperacao).append("\n");
        return sb.toString();
    }

    // Clone
    public Medio clone(){
        return new Medio(this);
    }

    // Método para cálculo da habilidade de um médio
    public double calculaHabilidade() {
        return super.getVelocidade() + 0.7*super.getResistencia() + 2*super.getDestreza() + 0.6*super.getImpulsao()
                + 0.3*super.getCabeca() + 0.4*super.getRemate() + 1.5*super.getPasse() + 2*this.recuperacao;
    }
}