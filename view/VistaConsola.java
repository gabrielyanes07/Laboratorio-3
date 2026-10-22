package view;

import java.util.Scanner;
import java.util.ArrayList;
import model.Cuadrilla;
import model.Jornada;
import model.OrdenDeServicio;

public class VistaConsola {
    private Scanner scanner = new Scanner(System.in);

    public void mostrarMenu() {
        mostrarMensaje("----------------------------------------------------------");
        mostrarMensaje("TecniHogar - Sistema de Gestión de Órdenes");
        mostrarMensaje("1. Cotizar orden de servicio");
        mostrarMensaje("2. Registrar orden de servicio");
        mostrarMensaje("3. Registrar cuadrilla");
        mostrarMensaje("4. Crear jornada para una cuadrilla");
        mostrarMensaje("5. Asignar orden a una jornada");
        mostrarMensaje("6. Retirar orden de una jornada");
        mostrarMensaje("7. Iniciar una jornada");
        mostrarMensaje("8. Registrar resultados de visitas de una jornada");
        mostrarMensaje("9. Consultar órdenes y cuadrillas");
        mostrarMensaje("10. Generar reportes de gerencia");
        mostrarMensaje("11. Salir del programa");
    }

    public int solicitarOpcion() {
        mostrarMensaje("Ingrese el número de la opción deseada:");
        while (true) {
            try {
                int opcion = Integer.parseInt(scanner.nextLine().trim());
                if (opcion >= 1 && opcion <= 11) {
                    return opcion;
                } else {
                    mostrarError("Opción inválida. Ingrese un número entre 1 y 11.");
                }
            } catch (NumberFormatException e) {
                mostrarError("Entrada inválida. Ingrese un número entero.");
            }
        }
    }

    public String leerCadena(String mensaje) {
        while (true) {
            mostrarMensaje(mensaje);
            String input = scanner.nextLine().trim();
            if (!input.isEmpty()) {
                return input;
            }
            mostrarError("El texto no puede estar vacío.");
        }
    }

    public void mostrarCuadrillas() {
    }

    public void mostrarCuadrillas(ArrayList<Cuadrilla> cuadrillas) {
        if (cuadrillas == null || cuadrillas.isEmpty()) {
            mostrarMensaje("No hay cuadrillas registradas.");
            return;
        }
        for (Cuadrilla cuadrilla : cuadrillas) {
            mostrarMensaje("Cuadrilla: " + cuadrilla.getCodigo() + " | Certificada en refrigeración: " + (cuadrilla.isCertificada() ? "Sí" : "No") + " | Capacidad: " + String.format("%.2f", cuadrilla.getMaxHora()) + " hrs");
            ArrayList<Jornada> jornadas = cuadrilla.getJornadas();
            if (jornadas.isEmpty()) {
                mostrarMensaje("  (Sin jornadas registradas)");
            } else {
                for (Jornada jornada : jornadas) {
                    mostrarMensaje("  " + jornada.toString().trim());
                }
            }
        }
    }

    public void mostrarOrdenesYCuadrillas(ArrayList<Cuadrilla> cuadrillas, ArrayList<OrdenDeServicio> ordenes) {
        mostrarMensaje("\n=== CUADRILLAS REGISTRADAS ===");
        mostrarCuadrillas(cuadrillas);

        mostrarMensaje("\n=== ÓRDENES DE SERVICIO ===");
        if (ordenes == null || ordenes.isEmpty()) {
            mostrarMensaje("No hay órdenes registradas.");
            return;
        }
        for (OrdenDeServicio orden : ordenes) {
            mostrarMensaje("  " + orden.toString());
        }
    }

    public void limpiarEntrada() {
        if (scanner.hasNextLine()) {
            scanner.nextLine();
        }
    }

    public String solicitarCodigo() {
        return solicitarCodigo("Ingrese el código de la cuadrilla:");
    }

    public String solicitarCodigo(String mensaje) {
        while (true) {
            mostrarMensaje(mensaje);
            String input = scanner.nextLine().trim();
            if (!input.isEmpty()) {
                return input;
            }
            mostrarError("El código no puede estar vacío.");
        }
    }

    public boolean validar(String mensaje) {
        while (true) {
            mostrarMensaje(mensaje);
            String input = scanner.nextLine().trim();
            if (input.equalsIgnoreCase("si") || input.equalsIgnoreCase("sí") || input.equalsIgnoreCase("s")) {
                return true;
            } else if (input.equalsIgnoreCase("no") || input.equalsIgnoreCase("n")) {
                return false;
            }
            mostrarError("Por favor responda 'si' o 'no'.");
        }
    }

    public double validarHoras(String mensaje) {
        while (true) {
            mostrarMensaje(mensaje);
            try {
                double horas = Double.parseDouble(scanner.nextLine().trim());
                if (horas <= 0) {
                    mostrarError("Las horas estimadas deben ser mayores a cero.");
                    continue;
                }
                if (horas > 1.5) {
                    mostrarError("Las horas estimadas para diagnóstico no pueden superar 1.5 horas.");
                    continue;
                }
                return horas;
            } catch (NumberFormatException e) {
                mostrarError("Ingrese un número válido para las horas estimadas.");
            }
        }
    }

    public int leerOpcion(String mensaje) {
        mostrarMensaje(mensaje);
        while (true) {
            try {
                int opcion = Integer.parseInt(scanner.nextLine().trim());
                return opcion;
            } catch (NumberFormatException e) {
                mostrarError("Entrada inválida. Ingrese un número entero.");
            }
        }
    }

    public double leerDecimal(String mensaje) {
        while (true) {
            mostrarMensaje(mensaje);
            try {
                double valor = Double.parseDouble(scanner.nextLine().trim());
                if (Double.isFinite(valor) && valor >= 0) {
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
                mostrarError("Ingresa un valor entero mayor que cero.");
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
