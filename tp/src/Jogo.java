import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Jogo implements Serializable {
    // Variáveis de instância
    private String equipaCasa;
    private String equipaVisitante;
    private int golosCasa;
    private int golosVisitante;
    private LocalDate dataJogo;
    private List<Integer> jogadoresCasa;
    private Map<Integer,Integer> substituicoesCasa;
    private List<Integer> jogadoresVisitante;
    private Map<Integer,Integer> substituicoesVisitante;

    // Construtores
    public Jogo(){
        this.equipaCasa = "n/a";
        this.equipaVisitante = "n/a";
        this.golosCasa = 0;
        this.golosVisitante = 0;
        this.dataJogo = LocalDate.MIN;
        this.jogadoresCasa = new ArrayList<>();
        this.substituicoesCasa = new HashMap<>();
        this.jogadoresVisitante = new ArrayList<>();
        this.substituicoesVisitante = new HashMap<>();
    }

    public Jogo(String casa, String visitante, int golosC, int golosV, LocalDate dataJ, List<Integer> jCasa,
                Map<Integer,Integer> subsC, List<Integer> jVis, Map<Integer,Integer> subsVis){
        this.equipaCasa = casa;
        this.equipaVisitante = visitante;
        this.golosCasa = golosC;
        this.golosVisitante = golosV;
        this.dataJogo = dataJ;
        this.jogadoresCasa = new ArrayList<>(jCasa);
        this.substituicoesCasa = new HashMap<>(subsC);
        this.jogadoresVisitante = new ArrayList<>(jVis);
        this.substituicoesVisitante = new HashMap<>(subsVis);
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

    public List<Integer> getJogadoresCasa() {
        return new ArrayList<>(jogadoresCasa);
    }

    public void setJogadoresCasa(List<Integer> jogadoresCasa) {
        this.jogadoresCasa = new ArrayList<>(jogadoresCasa);
    }

    public Map<Integer, Integer> getSubstituicoesCasa() {
        return new HashMap<>(substituicoesCasa);
    }

    public void setSubstituicoesCasa(Map<Integer, Integer> substituicoesCasa) {
        this.substituicoesCasa = new HashMap<>(substituicoesCasa);
    }

    public List<Integer> getJogadoresVisitante() {
        return new ArrayList<>(jogadoresVisitante);
    }

    public void setJogadoresVisitante(List<Integer> jogadoresVisitante) {
        this.jogadoresVisitante = new ArrayList<>(jogadoresVisitante);
    }

    public Map<Integer, Integer> getSubstituicoesVisitante() {
        return new HashMap<>(substituicoesVisitante);
    }

    public void setSubstituicoesVisitante(Map<Integer, Integer> substituicoesVisitante) {
        this.substituicoesVisitante = new HashMap<>(substituicoesVisitante);
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

    // toString
    @Override
    public String toString() {
        StringBuffer sb = new StringBuffer();
        sb.append("\n********* JOGO *********\n")
                .append("Data: ").append(dataJogo).append("\n")
                .append("Equipas: ").append(equipaCasa).append(" vs ").append(equipaVisitante).append("\n")
                .append("Resultado: "). append(golosCasa).append("-").append(golosVisitante).append("\n\n")
                .append(equipaCasa).append("\nTitulares:\n").append(jogadoresCasa).append("\n")
                .append("Banco:\n").append(substituicoesCasa).append("\n\n")
                .append(equipaVisitante).append("\nTitulares:\n").append(jogadoresVisitante).append("\n")
                .append("Banco:\n").append(substituicoesVisitante).append("\n");
        return sb.toString();
    }

    // Parser para jogos
    public static Jogo parse(String input){
        String[] campos = input.split(",");
        String[] data = campos[4].split("-");
        List<Integer> jc = new ArrayList<>();
        List<Integer> jf = new ArrayList<>();
        Map<Integer, Integer> subsC = new HashMap<>();
        Map<Integer, Integer> subsF = new HashMap<>();
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
}