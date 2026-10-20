import controller.ControladorCuadrillas;
import model.Cuadrilla;
import view.VistaConsola;


public class Main {
    public static void main(String[] args) {
        ControladorCuadrillas controlador = new ControladorCuadrillas(new Cuadrilla(0, false), 
        new VistaConsola());
        VistaConsola vista = new VistaConsola();

        vista.mostrarMensaje("Sistema de órdenes lanzado correctamente.");
        controlador.iniciar();
    }
}
    
