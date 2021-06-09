import java.io.Serializable;
import java.time.LocalDate;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

public class Jogo implements Serializable {
    // Variáveis de instância
    private String equipaCasa; // nome da equipa da casa
    private String equipaVisitante; // nome da equipa visitante
    private int golosCasa; // golos marcados pela equipa da casa
    private int golosVisitante; // golos marcados pela equipa de fora
    private LocalDate dataJogo; // data do jogo
    private Set<Integer> jogadoresCasa; // jogadores da equipa de casa
    private Map<Integer,Integer> substituicoesCasa; // substituições efetuadas pela equipa de casa
    private Set<Integer> jogadoresVisitante; // lista de jogadores da equipa de fora
    private Map<Integer,Integer> substituicoesVisitante; // substituições efetuadas pela equipa de fora
    private int fimJogo; // Um jogo terminado tem esta variável com o valor 1

    // Construtores
    public Jogo(){
        this.equipaCasa = "n/a";
        this.equipaVisitante = "n/a";
        this.golosCasa = 0;
        this.golosVisitante = 0;
        this.dataJogo = LocalDate.MIN;
        this.jogadoresCasa = new TreeSet<>();
        this.substituicoesCasa = new TreeMap<>();
        this.jogadoresVisitante = new TreeSet<>();
        this.substituicoesVisitante = new TreeMap<>();
        this.fimJogo = 0;
    }

    public Jogo(String casa, String visitante, int golosC, int golosV, LocalDate dataJ, Set<Integer> jCasa,
                Map<Integer,Integer> subsC, Set<Integer> jVis, Map<Integer,Integer> subsVis){
        this.equipaCasa = casa;
        this.equipaVisitante = visitante;
        this.golosCasa = golosC;
        this.golosVisitante = golosV;
        this.dataJogo = dataJ;
        this.jogadoresCasa = new TreeSet<>(jCasa);
        this.substituicoesCasa = new TreeMap<>(subsC);
        this.jogadoresVisitante = new TreeSet<>(jVis);
        this.substituicoesVisitante = new TreeMap<>(subsVis);
        this.fimJogo = 1;
    }

    public Jogo (Jogo j){
        this.equipaCasa = j.getEquipaCasa();
        this.equipaVisitante = j.getEquipaVisitante();
        this.golosCasa = j.getGolosCasa();
        this.golosVisitante = j.getGolosVisitante();
        this.dataJogo = j.getDataJogo();
        setJogadoresCasa(j.getJogadoresCasa());
        setSubstituicoesCasa(j.getSubstituicoesCasa());
        setJogadoresVisitante(j.getJogadoresVisitante());
        setSubstituicoesVisitante(j.getSubstituicoesVisitante());
        this.fimJogo = j.getFimJogo();
    }

    // Getters e Setters
    public String getEquipaCasa() {
        return equipaCasa;
    }

    public void setEquipaCasa(String equipaCasa) {
        this.equipaCasa = equipaCasa;
    }

    public String getEquipaVisitante() {
        return equipaVisitante;
    }

    public void setEquipaVisitante(String equipaVisitante) {
        this.equipaVisitante = equipaVisitante;
    }

    public int getGolosCasa() {
        return golosCasa;
    }

    public void setGolosCasa(int golosCasa) {
        this.golosCasa = golosCasa;
    }

    public int getGolosVisitante() {
        return golosVisitante;
    }

    public void setGolosVisitante(int golosVisitante) {
        this.golosVisitante = golosVisitante;
    }

    public LocalDate getDataJogo() {
        return dataJogo;
    }

    public void setDataJogo(LocalDate dataJogo) {
        this.dataJogo = dataJogo;
    }

    public Set<Integer> getJogadoresCasa() {
        return new TreeSet<>(jogadoresCasa);
    }

    public void setJogadoresCasa(Set<Integer> jogadoresCasa) {
        this.jogadoresCasa = new TreeSet<>(jogadoresCasa);
    }

    public Map<Integer, Integer> getSubstituicoesCasa() {
        return new TreeMap<>(substituicoesCasa);
    }

    public void setSubstituicoesCasa(Map<Integer, Integer> substituicoesCasa) {
        this.substituicoesCasa = new TreeMap<>(substituicoesCasa);
    }

    public Set<Integer> getJogadoresVisitante() {
        return new TreeSet<>(jogadoresVisitante);
    }

    public void setJogadoresVisitante(Set<Integer> jogadoresVisitante) {
        this.jogadoresVisitante = new TreeSet<>(jogadoresVisitante);
    }

    public Map<Integer, Integer> getSubstituicoesVisitante() {
        return new TreeMap<>(substituicoesVisitante);
    }

    public void setSubstituicoesVisitante(Map<Integer, Integer> substituicoesVisitante) {
        this.substituicoesVisitante = new TreeMap<>(substituicoesVisitante);
    }

    public int getFimJogo() {
        return fimJogo;
    }

    public void setFimJogo() {
        this.fimJogo = 1;
    }

    // Clone
    public Jogo clone(){
        return new Jogo(this);
    }

    // Equals
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Jogo jogo = (Jogo) o;
        return golosCasa == jogo.golosCasa && golosVisitante == jogo.golosVisitante
                && equipaCasa.equals(jogo.equipaCasa) && equipaVisitante.equals(jogo.equipaVisitante)
                && dataJogo.equals(jogo.dataJogo) && jogadoresCasa.equals(jogo.jogadoresCasa)
                && substituicoesCasa.equals(jogo.substituicoesCasa) && jogadoresVisitante.equals(jogo.jogadoresVisitante)
                && substituicoesVisitante.equals(jogo.substituicoesVisitante);
    }
    public String toStringMap(Map<Integer,Integer> map){
        StringBuffer sb = new StringBuffer();
        for (Integer key : map.keySet()) sb.append(key + " --> " + map.get(key) + "\n");
        return sb.toString();
    }

    // toString
    @Override
    public String toString() {
        StringBuffer sb = new StringBuffer();
        sb.append("\n********* JOGO *********\n")
                .append("Data: ").append(dataJogo).append("\n")
                .append("Equipas: ").append(equipaCasa).append(" vs ").append(equipaVisitante).append("\n")
                .append("Resultado: "). append(golosCasa).append("-").append(golosVisitante).append("\n\n")
                .append(equipaCasa).append("\nTitulares:\n").append(jogadoresCasa).append("\n")
                .append("\nSubstituições:\n").append(toStringMap(substituicoesCasa)).append("\n\n")
                .append(equipaVisitante).append("\nTitulares:\n").append(jogadoresVisitante).append("\n\n")
                .append("Banco:\n").append(toStringMap(substituicoesVisitante)).append("\n");
        return sb.toString();
    }

    // Parser para jogos
    public static Jogo parse(String input){
        String[] campos = input.split(",");
        String[] data = campos[4].split("-");
        Set<Integer> jc = new TreeSet<>();
        Set<Integer> jf = new TreeSet<>();
        Map<Integer, Integer> subsC = new TreeMap<>();
        Map<Integer, Integer> subsF = new TreeMap<>();
        for (int i = 5; i < 16; i++){
            jc.add(Integer.parseInt(campos[i]));
        }
        for (int i = 16; i < 19; i++){
            String[] sub = campos[i].split("->");
            subsC.put(Integer.parseInt(sub[0]), Integer.parseInt(sub[1]));
        }
        for (int i = 19; i < 30; i++){
            jf.add(Integer.parseInt(campos[i]));
        }
        for (int i = 30; i < 33; i++){
            String[] sub = campos[i].split("->");
            subsF.put(Integer.parseInt(sub[0]), Integer.parseInt(sub[1]));
        }
        return new Jogo(campos[0], campos[1], Integer.parseInt(campos[2]), Integer.parseInt(campos[3]),
                LocalDate.of(Integer.parseInt(data[0]), Integer.parseInt(data[1]), Integer.parseInt(data[2])),
                jc, subsC, jf, subsF);
    }

    // Método para efetuar as substituições de jogadores
    public void efetuaSubstituicao(String nome, Integer out, Integer in) throws SubstituicaoInvalidaException{
        if (nome.equals(this.equipaCasa)) {
            if (!this.jogadoresCasa.contains(out) || this.substituicoesCasa.size() > 3) throw new SubstituicaoInvalidaException();
            this.jogadoresCasa.remove(out);
            this.jogadoresCasa.add(in);
            this.substituicoesCasa.put(out,in);
        }
        else if (nome.equals(this.equipaVisitante)){
            if (!this.jogadoresVisitante.contains(out) || this.substituicoesVisitante.size() > 3) throw new SubstituicaoInvalidaException();
            this.jogadoresVisitante.remove(out);
            this.jogadoresVisitante.add(in);
            this.substituicoesVisitante.put(out,in);
        }
        else throw new SubstituicaoInvalidaException();
    }

    // Método para verificar se um jogador está em campo
    public boolean presenteEmCampo(String nomeEquipa, int nrCamisola){
        boolean res = false;
        if (nomeEquipa.equals(this.equipaCasa)) {
            if (this.jogadoresCasa.contains(nrCamisola)) res = true;
        }
        else if (nomeEquipa.equals(this.equipaVisitante)){
            if (this.jogadoresVisitante.contains(nrCamisola)) res = true;
        }
        else System.out.println("Nome de Equipa inválido.");
        return res;
    }
}