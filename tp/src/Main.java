public class Main {
    public static void main(String[] args) throws Exception{
        Estado estado = new Estado();
        try {
            estado.parse();
        } catch (LinhaIncorretaException e ){
            System.out.println(e.getMessage());
        }
        Controlador controlador = new Controlador(estado);
        controlador.run();
    }
}
