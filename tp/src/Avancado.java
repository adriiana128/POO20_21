public class Avancado extends Jogador{
    //Variaveis de instância
    private int penalti; // capacidade de marcação de penalties

    // Construtores
    public Avancado(){
        super();
        this.penalti = -1;
    }

    public Avancado(String iD, String name, int nr, int vel, int res, int dest, int imp, int cab, int rem, int pass, int fin, String eq, int pen){
        super(iD,name,nr,vel,res,dest,imp,cab,rem,pass,fin,eq);
        this.penalti = pen;
    }

    public Avancado(Avancado a){
        super(a);
        this.penalti = a.getPenalti();
    }

    public Avancado clone(){
        return new Avancado(this);
    }

    public int getPenalti() {
        return penalti;
    }

    public void setPenalti(int penalti) {
        this.penalti = penalti;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Avancado avancado = (Avancado) o;
        return penalti == avancado.penalti;
    }

    @Override
    public String toString() {
        String str = super.toString();
        StringBuffer sb = new StringBuffer();
        sb.append(str).append("Penalti: ").append(this.penalti).append("\n");
        return sb.toString();
    }

    public double calculaHabilidade() {
        return super.getVelocidade() + 0.8*super.getResistencia() + 0.7*super.getDestreza() + super.getImpulsao()
                + super.getCabeca() + super.getRemate() + 0.7*super.getPasse() + 2*super.getFinta() + 1.5*this.penalti;
    }
}