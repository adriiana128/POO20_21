public class Avancado extends Jogador{
    //Variaveis de instância
    private int penalti; // capacidade de marcação de penalties

    // Construtores
    public Avancado(){
        super();
        this.penalti = 0;
    }

    public Avancado(String name, int nr, int vel, int res, int dest, int imp, int cab, int rem, int pass, int pen){
        super(name,nr,vel,res,dest,imp,cab,rem,pass);
        this.penalti = pen;
    }

    public Avancado(Avancado a){
        super(a);
        this.penalti = a.getPenalti();
    }

    // Getters e Setters
    public Avancado clone(){
        return new Avancado(this);
    }

    public int getPenalti() {
        return penalti;
    }

    public void setPenalti(int penalti) {
        this.penalti = penalti;
    }

    // Clone
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Avancado avancado = (Avancado) o;
        return penalti == avancado.penalti;
    }

    // toString
    @Override
    public String toString() {
        String jog = super.toString();
        String eq = super.toStringEquipas();
        StringBuffer sb = new StringBuffer();
        sb.append(jog).append("Penalti: ").append(this.penalti).append(" | ").append(eq);
        return sb.toString();
    }

    // Método para cálculo da habilidade de um avançado
    public double calculaHabilidade() {
        return super.getVelocidade() + 0.8*super.getResistencia() + 0.7*super.getDestreza() + super.getImpulsao()
                + super.getCabeca() + super.getRemate() + 0.7*super.getPasse()+ 1.5*this.penalti;
    }
}