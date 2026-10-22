import controller.ControladorCuadrillas;
import model.Cuadrilla;
import view.VistaConsola;

public class Main {
    public static void main(String[] args) {
        VistaConsola vista = new VistaConsola();
        ControladorCuadrillas controlador = new ControladorCuadrillas(new Cuadrilla(0, false), vista);

        vista.mostrarMensaje("Sistema de órdenes lanzado correctamente.");
        controlador.iniciar();
    }
}
