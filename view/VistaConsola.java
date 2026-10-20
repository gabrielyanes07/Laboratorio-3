package view;
import java.util.Scanner;
import model.Cuadrilla;
import controller.ControladorCuadrillas;

public class VistaConsola {
    Scanner scanner = new Scanner(System.in);

    public void mostrarMenu() {
        mostrarMensaje("----------------------------------------------------------");
        mostrarMensaje("Bienvenido al Menú, elija una de las siguientes opciones:");
        mostrarMensaje("1. Mostrar órdenes de servicio");
        mostrarMensaje("2. Mostrar el ingreso total");
        mostrarMensaje("3. Mostrar jornadas sin cerrar, con cuadrillas");
        mostrarMensaje("4. Agregar una cuadrilla");
        mostrarMensaje("5. Agregar Jornada a una cuadrilla");
        mostrarMensaje("6. Agregar Orden de Servicio a una jornada");
        mostrarMensaje("7. Cotizar una orden de servicio");
        mostrarMensaje("8. Salir del programa");
    }

    public int solicitarOpcion() {
        mostrarMensaje("Ingrese el número de la opción deseada:");
        while (true) {
            try {
                int opcion = Integer.parseInt(scanner.nextLine().trim());
                if (opcion >= 1 && opcion <= 8) {
                    return opcion;
                } else {
                    mostrarError("Opción inválida. Por favor, ingrese un número entre 1 y 8.");
                }
            } catch (NumberFormatException e) {
                mostrarError("Entrada inválida. Por favor, ingrese un número entero.");
            }
        }
    }

    public String leerCadena(String mensaje) {
        mostrarMensaje(mensaje);
        return scanner.nextLine();
    }

    public void mostrarCuadrillas() {
    }

    public void limpiarEntrada() {
        if (scanner.hasNextLine()) {
            scanner.nextLine();
        }
    }

    public String solicitarCodigo(){
        mostrarMensaje("Ingrese el código de la cuadrilla");
        String codigo = scanner.nextLine();
        return codigo;
    }

    public boolean validar(String mensaje){
        mostrarMensaje(mensaje);
        String certificadoInput = scanner.nextLine();
        if (certificadoInput.equalsIgnoreCase("si")) {
            return true;
        } else {
            return false;
        }
    }

    public int validarHoras(String mensaje) {
        while(true){
            try{
                int horasEstimadas = Integer.parseInt(scanner.nextLine().trim());
                double minutosEstimados = Double.parseDouble(scanner.nextLine().trim());
                double horasTotales = horasEstimadas + minutosEstimados / 60.0;
                if (horasTotales>1.5){
                        throw new IllegalArgumentException("Las horas estimadas deben ser menores o iguales a 1.5");
                        }
                return horasEstimadas;
                        }catch(Exception e){
                            mostrarError("Ingrese un valor numérico válido para las horas estimadas.");
                            continue;
                        }
                }
    }

    public int leerOpcion(String mensaje){
        mostrarMensaje(mensaje);
        while (true) {
            try {
                int opcion = Integer.parseInt(scanner.nextLine().trim());
                if (opcion >= 1 && opcion <= 8) {
                    return opcion;
                } else {
                    mostrarError("Opción inválida. Por favor, ingrese un número entre 1 y 8.");
                }
            } catch (NumberFormatException e) {
                mostrarError("Entrada inválida. Por favor, ingrese un número entero.");
            }
        }
    }

    public double leerDecimal(String mensaje) {
        while (true) {
            mostrarMensaje(mensaje);

            try {
                double valor = Double.parseDouble(scanner.nextLine().trim());

                if (Double.isFinite(valor) && valor > 0) {
                    return valor;
                }

                mostrarError("Ingresa un número válido mayor o igual a cero.");
            } catch (NumberFormatException e) {
                mostrarError("Ingresa un número; usa punto para los decimales.");
            }
        }
    }

    public int leerEntero(String mensaje) {
        while (true) {
            mostrarMensaje(mensaje);

            try {
                int valor = Integer.parseInt(scanner.nextLine().trim());

                if (valor > 0) {
                    return valor;
                }

                mostrarError("Ingresa un valor mayor que cero.");
            } catch (NumberFormatException e) {
                mostrarError("Ingresa un número entero válido.");
        }
    }
}

    public void mostrarMensaje(String mensaje) {
        System.out.println(mensaje);
    }

    public void mostrarError(String mensaje) {
        System.out.println("Error: " + mensaje);
    }
}