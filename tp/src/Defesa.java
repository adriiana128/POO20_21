public class Defesa extends Jogador{
    //Variaveis de instância
    private int marcacao; // capacidade de marcar devidamente os jogadores adversários

    // Construtores
    public Defesa(){
        super();
        this.marcacao = -1;
    }

    public Defesa(String name, int nr, int vel, int res, int dest, int imp, int cab, int rem, int pass, int mar){
        super(name,nr,vel,res,dest,imp,cab,rem,pass);
        this.marcacao = mar;
    }

    public Defesa(Defesa d){
        super(d);
        this.marcacao = d.getMarcacao();
    }

    // Getters e Setters
    public int getMarcacao() {
        return marcacao;
    }

    public void setMarcacao(int marcacao) {
        this.marcacao = marcacao;
    }

    // Clone
    public Defesa clone(){
        return new Defesa(this);
    }

    // Equals
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Defesa defesa = (Defesa) o;
        return marcacao == defesa.marcacao;
    }

    // toString
    @Override
    public String toString() {
        String jog = super.toString();
        String eq = super.toStringEquipas();
        StringBuffer sb = new StringBuffer();
        sb.append(jog).append("Marcação: ").append(this.marcacao).append(" | ").append(eq);
        return sb.toString();
    }

    // Método para cálculo da habilidade de um defesa
    public double calculaHabilidade() {
        return 0.7*super.getVelocidade() + super.getResistencia() + 2*super.getDestreza() + 0.7*super.getImpulsao()
                + 0.5*super.getCabeca() + 0.4*super.getRemate() + super.getPasse() + 2*this.marcacao;
    }
}
