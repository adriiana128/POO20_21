public class Lateral extends Jogador{
    //Variaveis de instância
    private int cruzamento;

    // Construtores
    public Lateral(){
        super();
        this.cruzamento = 0;
    }

    public Lateral(String name, int nr, int vel, int res, int dest, int imp, int cab, int rem, int pass, int cruz){
        super(name,nr,vel,res,dest,imp,cab,rem,pass);
        this.cruzamento = cruz;
    }

    public Lateral(Lateral l){
        super(l);
        this.cruzamento = l.getCruzamento();
    }

    // Getters e Setters
    public int getCruzamento() {
        return cruzamento;
    }

    public void setCruzamento(int cruzamento) {
        this.cruzamento = cruzamento;
    }

    // Equals
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Lateral lateral = (Lateral) o;
        return cruzamento == lateral.cruzamento;
    }

    // toString
    @Override
    public String toString() {
        String jog = super.toString();
        String eq = super.toStringEquipas();
        StringBuffer sb = new StringBuffer();
        sb.append(jog).append("Cruzamento: ").append(this.cruzamento).append(" | ")
                .append("Posição: ").append(this.getClass().getSimpleName()).append(" | ")
                .append(eq);
        return sb.toString();
    }

    // Clone
    public Lateral clone(){
        return new Lateral(this);
    }

    // Método para cálculo da habilidade de um lateral
    public double calculaHabilidade() {
        return 0.8*super.getVelocidade() + 0.9*super.getResistencia() + super.getDestreza() + 0.6*super.getImpulsao()
                + 0.4*super.getCabeca() + 0.7*super.getRemate() + 0.8*super.getPasse() + 2*this.cruzamento;
    }

    // Parser para laterais
    public static Lateral parse(String input){
        String[] campos = input.split(",");
        return new Lateral(campos[0], Integer.parseInt(campos[1]),
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


