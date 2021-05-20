public class Lateral extends Jogador{
    //Variaveis de instância
    private int cruzamento;

    // Construtores
    public Lateral(){
        super();
        this.cruzamento = -1;
    }

    public Lateral(String name, int nr, int vel, int res, int dest, int imp, int cab, int rem, int pass, int fin, String eq, int cruz){
        super(name,nr,vel,res,dest,imp,cab,rem,pass,fin,eq);
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

    public double calculaHabilidade() {
        return 0.8*super.getVelocidade() + 0.9*super.getResistencia() + super.getDestreza() + 0.6*super.getImpulsao()
                + 0.4*super.getCabeca() + 0.7*super.getRemate() + 0.8*super.getPasse() + 0.6*super.getFinta() + 2*this.cruzamento;
    }
}


