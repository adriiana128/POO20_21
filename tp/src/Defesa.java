public class Defesa extends Jogador{
    //Variaveis de instância
    private int marcacao; // capacidade de marcar devidamente os jogadores adversários

    // Construtores
    public Defesa(){
        super();
        this.marcacao = -1;
    }

    public Defesa(String name, int nr, int vel, int res, int dest, int imp, int cab, int rem, int pass, int fin, String eq, int mar){
        super(name,nr,vel,res,dest,imp,cab,rem,pass,fin,eq);
        this.marcacao = mar;
    }

    public Defesa(Defesa d){
        super(d);
        this.marcacao = d.getMarcacao();
    }

    public int getMarcacao() {
        return marcacao;
    }

    public void setMarcacao(int marcacao) {
        this.marcacao = marcacao;
    }

    public Defesa clone(){
        return new Defesa(this);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Defesa defesa = (Defesa) o;
        return marcacao == defesa.marcacao;
    }

    @Override
    public String toString() {
        String str = super.toString();
        StringBuffer sb = new StringBuffer();
        sb.append(str).append("Capacidade de marcação: ").append(this.marcacao).append("\n");
        return sb.toString();
    }

    public double calculaHabilidade() {
        return 0.7*super.getVelocidade() + super.getResistencia() + 2*super.getDestreza() + 0.7*super.getImpulsao()
                + 0.5*super.getCabeca() + 0.4*super.getRemate() + super.getPasse() + 0.8*super.getFinta() + 2*this.marcacao;
    }
}
