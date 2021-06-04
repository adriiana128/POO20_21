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
        String jog = super.toString();
        String eq = super.toStringEquipas();
        StringBuffer sb = new StringBuffer();
        sb.append(jog).append("Recuperação: ").append(this.recuperacao).append(" | ")
                .append("Posição: ").append(this.getClass().getSimpleName()).append(" | ")
                .append(eq);
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

    // Parser para médios
    public static Medio parse(String input){
        String[] campos = input.split(",");
        return new Medio(campos[0], Integer.parseInt(campos[1]),
                Integer.parseInt(campos[2]),
                Integer.parseInt(campos[3]),
                Integer.parseInt(campos[4]),
                Integer.parseInt(campos[5]),
                Integer.parseInt(campos[6]),
                Integer.parseInt(campos[7]),
                Integer.parseInt(campos[8]),
                Integer.parseInt(campos[9]));
    }
}