public class Medio extends Jogador{
    // Variaveis de instancia
    private int recuperacao;

    // Construtores
    public Medio(){
        super();
        this.recuperacao = -1;
    }
    public Medio(String name, int nr, int vel, int res, int dest, int imp, int cab, int rem, int pass, int fin, String eq, int rec){
        super(name,nr,vel,res,dest,imp,cab,rem,pass,fin,eq);
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
        sb.append(str).append("Recuperação: ").append(this.recuperacao).append("\n");
        return sb.toString();
    }

    public Medio clone(){
        return new Medio(this);
    }

    public double calculaHabilidade() {
        return super.getVelocidade() + 0.7*super.getResistencia() + 2*super.getDestreza() + 0.6*super.getImpulsao()
                + 0.3*super.getCabeca() + 0.4*super.getRemate() + 1.5*super.getPasse() + 0.6*super.getFinta() + 2*this.recuperacao;
    }
}