import java.io.Serializable;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;
import java.util.Collections;
import java.time.LocalDate;
import java.util.List;
import java.util.ArrayList;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Random;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Calendar;

public class Parser implements Serializable {
    private Map<String, Jogador> jogadores; //todos os jogadores da aplicaçao
    private Map<String, Equipa> equipas; //equipas da aplicacao
    private Jogador jogador; //jogador registado no momento

    public Parser() {
        this.jogadores = new TreeMap<>();
        this.equipas = new TreeMap<>();
        this.jogador = null;
    }

    public Parser(Map<String, Jogador> jogadores, Jogador jogador) {
        this.jogadores = new TreeMap<String, Jogador>(jogadores);
        this.equipas = new TreeMap<String, Equipa>(equipas);
        this.jogador = jogador;
    }

    public Parser(Parser p) {
        this.jogadores = p.getJogadores();
        this.equipas = p.getEquipas();
        this.jogador = p.getJogador();
    }

    public Map<String, Jogador> getJogadores() {
        return this.jogadores.entrySet()
                .stream()
                .collect(Collectors.toMap(e -> e.getKey(), e -> e.getValue().clone()));
    }

    public Map<String, Equipa> getEquipas() {
        return this.equipas.entrySet()
                .stream()
                .collect(Collectors.toMap(e -> e.getKey(), e -> e.getValue().clone()));
    }

    public Jogador getJogador() {
        return this.jogador;
    }

    public int getTipoJogador() {
        if (this.jogador instanceof Avancado) return 1;
        if (this.jogador instanceof Defesa) return 2;
        if (this.jogador instanceof GuardaRedes) return 3;
        if (this.jogador instanceof Lateral) return 4;
        else if (this.jogador instanceof Medio) return 5;

        return 0;
    }

    /**---------------------Métodos---------------------**/

    /**
     * Funcão que inicializa a App
     **/
    public static Parser initApp() throws IOException, ClassNotFoundException {
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream("players.data"));
        Parser p = (Parser) ois.readObject();

        ois.close();
        return p;
    }

    /**
     * Função que guarda os dados num ficheiro "players.data"
     **/
    public void gravar() throws IOException {
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("players.data"));
        oos.writeObject(this);
        oos.flush();
        oos.close();
    }

    /**
     * Função que regista um Jogador
     **/
    public void registaJogador(Jogador jogador) throws JogadorExistenteException {
        String id = jogador.getId();

        if (jogadores.containsKey(id))
            throw new JogadorExistenteException("Jogador já existe!");

        jogadores.put(id, jogador.clone());
    }

    public Parser clone() {
        return new Parser(this);
    }

    public void parse() {
        List<String> linhas = lerFicheiro("data.txt"); //alterar nome do ficheiro
        String[] linhaPartida;
        for (String linha : linhas) {
            linhaPartida = linha.split(":", 2);
            switch (linhaPartida[0]) {
                case "Avançado":
                    Avancado a = parseAvancado(linhaPartida[1]); // criar um Avancado
                    try {
                        registaJogador(a);
                    } catch (JogadorExistenteException e) {
                        System.out.println(e.getMessage());
                    }
                    try {
                        this.gravar();
                    } catch (IOException e) {
                        System.out.println("Não foi possível gravar os dados!");
                    }
                    System.out.println(a.toString());
                    break;
                case "Defesa":
                    Defesa d = parseDefesa(linhaPartida[1]);
                    try {
                        registaJogador(d);
                    } catch (JogadorExistenteException e) {
                        System.out.println(e.getMessage());
                    }
                    try {
                        this.gravar();
                    } catch (IOException e) {
                        System.out.println("Não foi possível gravar os dados!");
                    }

                    System.out.println(d.toString());
                    break;
                case "GuardaRedes":
                    GuardaRedes gr = parseGuardaRedes(linhaPartida[1]);
                    try {
                        registaJogador(gr);
                    } catch (JogadorExistenteException e) {
                        System.out.println(e.getMessage());
                    }
                    try {
                        this.gravar();
                    } catch (IOException e) {
                        System.out.println("Não foi possível gravar os dados!");
                    }
                    System.out.println(gr.toString());
                    break;
                case "Lateral":
                    Lateral l = parseLateral(linhaPartida[1]);
                    try {
                        registaJogador(l);
                    } catch (JogadorExistenteException e) {
                        System.out.println(e.getMessage());
                    }
                    try {
                        this.gravar();
                    } catch (IOException e) {
                        System.out.println("Não foi possível gravar os dados!");
                    }
                    System.out.println(l.toString());
                    break;
                case "Medio":
                    Medio m = parseMedio(linhaPartida[1]);
                    try {
                        registaJogador(m);
                    } catch (JogadorExistenteException e) {
                        System.out.println(e.getMessage());
                    }
                    try {
                        this.gravar();
                    } catch (IOException e) {
                        System.out.println("Não foi possível gravar os dados!");
                    }
                    System.out.println(m.toString());
                    break;
                case "Equipa":
                    Equipa eqp = parseEquipa(linhaPartida[1]);
                    registaEquipa(eqp);
                    for (Jogador p : this.jogadores.values()) {
                        if (p.getId().equals(eqp.getJogador().getId())) {
                            ((Jogador) p).getHistoricoEquipas().add(eqp.clone());
                        }
                    }

                    try {
                        this.gravar();
                    } catch (IOException e) {
                        System.out.println("Não foi possível gravar os dados!");
                    }
                    System.out.println(enc.toString());
                    break;
                default:
                    System.out.println("Linha invalida.");
                    break;
            }

        }
        System.out.println("done!");
    }

    public Avancado parseAvancado(String input){
        String[] campos = input.split(",");
        String idAvancado = ("av123");
        String name = campos[0];
        int nCamisola = campos[1];
        int velo = campos[2];
        int resist = campos[3];
        int destr = campos[4];
        int impul = campos[5];
        int cabe = campos[6];
        int rema = campos[7];
        int pa = campos[8];
        int fi = ;
        int habil = ;
        int pen = ;
        return new Avancado(idAvancado,name,nCamisola,velo,resist,destr,impul,cabe,rema,pa,fi,habil,new HashSet<>(),pen);
    }

    public Defesa parseDefesa(String input){
        String[] campos = input.split(",");
        String idDefesa = ("d123");
        String name = campos[0];
        int nCamisola = campos[1];
        int velo = campos[2];
        int resist = campos[3];
        int destr = campos[4];
        int impul = campos[5];
        int cabe = campos[6];
        int rema = campos[7];
        int pa = campos[8];
        int fi = ;
        int habil = ;
        int marc = ;
        return new Defesa(idDefesa,name,nCamisola,velo,resist,destr,impul,cabe,rema,pa,fi,habil,new HashSet<>(),marc);
    }

    public GuardaRedes parseGuardaRedes(String input){
        String[] campos = input.split(",");
        String idGuardaRedes = ("gr123");
        String name = campos[0];
        int nCamisola = campos[1];
        int velo = campos[2];
        int resist = campos[3];
        int destr = campos[4];
        int impul = campos[5];
        int cabe = campos[6];
        int rema = campos[7];
        int pa = campos[8];
        int fi = ;
        int habil = ;
        int elast = ;
        int defes = ;
        return new GuardaRedes(idGuardaRedes,name,nCamisola,velo,resist,destr,impul,cabe,rema,pa,fi,habil,new HashSet<>(),elast,defes);
    }

    public Lateral parseLateral(String input){
        String[] campos = input.split(",");
        String idLateral = ("l123");
        String name = campos[0];
        int nCamisola = campos[1];
        int velo = campos[2];
        int resist = campos[3];
        int destr = campos[4];
        int impul = campos[5];
        int cabe = campos[6];
        int rema = campos[7];
        int pa = campos[8];
        int fi = ;
        int habil = ;
        int cruza = ;
        return new Lateral(idLateral,name,nCamisola,velo,resist,destr,impul,cabe,rema,pa,fi,habil,new HashSet<>(),cruza);
    }

    public Medio parseMedio(String input){
        String[] campos = input.split(",");
        String idMedio = ("m123");
        String name = campos[0];
        int nCamisola = campos[1];
        int velo = campos[2];
        int resist = campos[3];
        int destr = campos[4];
        int impul = campos[5];
        int cabe = campos[6];
        int rema = campos[7];
        int pa = campos[8];
        int fi = ;
        int habil = ;
        int recu = ;
        return new Avancado(idMedio,name,nCamisola,velo,resist,destr,impul,cabe,rema,pa,fi,habil,new HashSet<>(),recu);
    }

    public Equipa parseEquipa(String input){
        String[] campos = input.split(",");
        String name = campos[0];
        Set<Jogador> j = new HashSet<>();
        double habgl = ;
        return new Equipa(name,j,habgl);
    }

    public List<String> lerFicheiro(String nomeFich) {
        List<String> lines = new ArrayList<>();
        try {
            lines = Files.readAllLines(Paths.get(nomeFich), StandardCharsets.UTF_8);
        } catch (IOException exc) {
            System.out.println(exc.getMessage());
        }
        return lines;
    }

}