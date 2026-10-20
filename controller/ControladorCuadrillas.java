package controller;
import java.util.ArrayList;
import view.VistaConsola;
import model.Cuadrilla;
import model.Jornada;
import model.Diagnostico;
import model.OrdenDeServicio;
import model.Instalacion;
import model.InstalacionElectrica;
import model.MantenimientoAC;

public class ControladorCuadrillas {
    private ArrayList<Cuadrilla> cuadrillas;
    private Cuadrilla modelo;
    private VistaConsola vista;

    public ControladorCuadrillas(Cuadrilla modelo, VistaConsola vista) {
        this.cuadrillas = new ArrayList<>();
        this.modelo = modelo;
        this.vista = vista;
    }

    public ArrayList<Cuadrilla> mostrarCuadrillas() {
        return cuadrillas;
    }

    public Cuadrilla getCuadrillaPorCodigo(String codigo) {
        for (Cuadrilla cuadrilla : cuadrillas) {
            if (cuadrilla.getCodigo().equalsIgnoreCase(codigo)) {
                return cuadrilla;
            }
        }
        return null;
    }


    public void agregarCuadrilla(Cuadrilla cuadrilla) {
        cuadrillas.add(cuadrilla);
    }

    public void iniciar() {
        int opcion = 0;

        do {
            try {
                vista.mostrarMenu();
                opcion = vista.solicitarOpcion();

                if (opcion <= 1 || opcion > 8) {
                    throw new IllegalArgumentException(
                        "Esa opción no está disponible"
                    );
                }

                gestionarOpcion(opcion);

            } catch (NumberFormatException e) {
                vista.mostrarError("Debes ingresar una de las opciones.");

            } catch (IllegalArgumentException e) {
                vista.mostrarError(e.getMessage());

            } catch (java.util.InputMismatchException e) {
                vista.limpiarEntrada();
                vista.mostrarError("Debes ingresar un número dentro de las opciones.");

            } catch (java.util.NoSuchElementException e) { 
                return;
            }
        } while (opcion != 8);
    }

    public void gestionarOpcion(int opcion) {
        switch (opcion) {
            case 1:
                vista.mostrarCuadrillas();
                break;

            case 2:
                double ingresoTotal = modelo.calcularIngresoTotal();
                vista.mostrarMensaje("El ingreso total es: " + ingresoTotal);
                break;

            case 3:
                String cuadrillasSinCerrar = modelo.obtenerJornadasSinCerrar();
                vista.mostrarMensaje("Jornadas sin cerrar con cuadrillas:\n" + cuadrillasSinCerrar);
                break;

            case 4:
                vista.mostrarMensaje("Agregando una nueva Cuadrilla...");
                double maxHora = vista.leerEntero("Ingrese el máximo de horas para la cuadrilla:");
                boolean certificadoInput = vista.validar("Ingrese si posee el certificado (si/no):");
                Cuadrilla nuevaCuadrilla = new Cuadrilla(maxHora, certificadoInput);
                agregarCuadrilla(nuevaCuadrilla);
                break;

            case 5:
                vista.mostrarMensaje("Agregando una nueva Jornada a una cuadrilla...");
                String codigoCuadrilla = vista.solicitarCodigo();
                Cuadrilla cuadrilla = getCuadrillaPorCodigo(codigoCuadrilla);
                if (cuadrilla != null) {
                    Jornada nuevaJornada = new Jornada();
                    cuadrilla.agregarJornada(nuevaJornada);
                    vista.mostrarMensaje("Se ha agregado una nueva jornada a la cuadrilla " + codigoCuadrilla);
                } else {
                    vista.mostrarError("No se encontró ninguna cuadrilla con el código proporcionado.");
                }
                break;
            
            case 6:
                vista.mostrarMensaje("Agregando una nueva Orden de Servicio a una jornada...");
                String codigoCuadrillaOrden = vista.solicitarCodigo();
                Cuadrilla cuadrillaOrden = getCuadrillaPorCodigo(codigoCuadrillaOrden);
                if (cuadrillaOrden != null) {
                    ArrayList<Jornada> jornadas = cuadrillaOrden.getJornadas();
                    String codigoJornada = vista.solicitarCodigo();
                    for (Jornada jornada : jornadas){
                        if (jornada.getCodigo().equals(codigoJornada)){
                            vista.mostrarMensaje("Cotizando una orden de servicio...");
                            vista.mostrarMensaje("1. Diagnóstico");
                            vista.mostrarMensaje("2. Instalación");
                            vista.mostrarMensaje("3. Instalación Eléctrica");
                            vista.mostrarMensaje("4. Mantenimiento de Aire Acondicionado");
                            int ordenTipo = vista.leerEntero("Ingrese el tipo de orden:");
                            if (ordenTipo < 1 || ordenTipo > 4) {
                                vista.mostrarError("Tipo de orden inválido.");
                                break;
                            }
                            else{
                                OrdenDeServicio nuevaOrden = null;
                                if (ordenTipo == 1) {
                                    String nombreCliente = vista.leerCadena("Ingrese el nombre del cliente:");
                                    int horasEstimadas = vista.validarHoras("Ingrese las horas estimadas para el diagnóstico:");
                                    boolean metropolitano = vista.validar("¿Es metropolitano? (si/no):");
                                    int cantidadVisitas = vista.leerEntero("Ingrese la cantidad de visitas:");
                                    double minutosEstimados = vista.leerDecimal("Ingrese los minutos estimados:");
                                    double costoDiagnostico = Diagnostico.cotizar(horasEstimadas,cantidadVisitas, metropolitano);
                                    nuevaOrden = new Diagnostico(nombreCliente, horasEstimadas, minutosEstimados, metropolitano, cantidadVisitas, "Pendiente", costoDiagnostico);

                                } else if (ordenTipo == 2) {
                                    String nombreCliente = vista.leerCadena("Ingrese el nombre del cliente:");
                                    int cantPuntos = vista.leerEntero("Ingrese la cantidad de puntos:");
                                    int cantidadVisitas = vista.leerEntero("Ingrese la cantidad de visitas:");
                                    int horaEstimada = vista.leerEntero("Ingrese la hora estimada:");
                                    double minutosEstimados = vista.leerDecimal("Ingrese los minutos estimados:");
                                    boolean esMetropolitano = vista.validar("¿Es metropolitano? (si/no):");
                                    double costoInstalacion = Instalacion.cotizar(cantidadVisitas,cantPuntos,horaEstimada, minutosEstimados, esMetropolitano);
                                    nuevaOrden = new Instalacion(cantPuntos, nombreCliente, horaEstimada, minutosEstimados, esMetropolitano, cantidadVisitas,"Pendiente", costoInstalacion);

                                } else if (ordenTipo == 3) {
                                    String nombreCliente = vista.leerCadena("Ingrese el nombre del cliente:");
                                    int cantPuntos = vista.leerEntero("Ingrese la cantidad de puntos:");
                                    int cantidadVisitas = vista.leerEntero("Ingrese la cantidad de visitas:");
                                    int horaEstimada = vista.leerEntero("Ingrese la hora estimada:");
                                    double minutosEstimados = vista.leerDecimal("Ingrese los minutos estimados:");
                                    boolean esMetropolitano = vista.validar("¿Es metropolitano? (si/no):");
                                    double costMateriales = vista.leerDecimal("Ingrese el costo de los materiales:");
                                    double costoInstalacionElectrica = InstalacionElectrica.cotizar(cantidadVisitas, cantPuntos, horaEstimada, minutosEstimados, esMetropolitano, costMateriales);
                                    nuevaOrden = new InstalacionElectrica(cantPuntos, costMateriales, nombreCliente,  horaEstimada, minutosEstimados, esMetropolitano, cantidadVisitas, "Pendiente", costoInstalacionElectrica);

                                } else if (ordenTipo == 4) {
                                    String nombreCliente = vista.leerCadena("Ingrese el nombre del cliente:");
                                    int cantidadVisitas = vista.leerEntero("Ingrese la cantidad de visitas:");
                                    int horaEstimada = vista.leerEntero("Ingrese la hora estimada:");
                                    double minutosEstimados = vista.leerDecimal("Ingrese los minutos estimados:");     
                                    boolean esMetropolitano = vista.validar("¿Es metropolitano? (si/no):");
                                    double costoMantenimientoAC = MantenimientoAC.cotizar(cantidadVisitas, horaEstimada, minutosEstimados, esMetropolitano);
                                    nuevaOrden = new MantenimientoAC(nombreCliente, horaEstimada, minutosEstimados, esMetropolitano, cantidadVisitas, "Pendiente", costoMantenimientoAC);
                            }
                            jornada.agregarOrden(nuevaOrden);
                            vista.mostrarMensaje("Se ha agregado una nueva orden de servicio a la jornada " + codigoJornada);
                        }
                    }
                }
            } else {
                    vista.mostrarError("No se encontró ninguna cuadrilla con el código proporcionado.");
                }
                break;

            case 7:
                vista.mostrarMensaje("Cotizando una orden de servicio...");
                vista.mostrarMensaje("1. Diagnóstico");
                vista.mostrarMensaje("2. Instalación");
                vista.mostrarMensaje("3. Instalación Eléctrica");
                vista.mostrarMensaje("4. Mantenimiento de Aire Acondicionado");
                int ordenTipo = vista.leerEntero("Ingrese el tipo de orden:");
                if (ordenTipo < 1 || ordenTipo > 4) {
                    vista.mostrarError("Tipo de orden inválido.");
                    break;
                }
                else{
                    if (ordenTipo == 1) {
                        int horasEstimadas = vista.validarHoras("Ingrese las horas estimadas para el diagnóstico:");
                        boolean metropolitano = vista.validar("¿Es metropolitano? (si/no):");
                        int cantidadVisitas = vista.leerEntero("Ingrese la cantidad de visitas:");
                        double costoDiagnostico = Diagnostico.cotizar(horasEstimadas,cantidadVisitas, metropolitano);
                        vista.mostrarMensaje("El costo del diagnóstico es: " + costoDiagnostico);

                    } else if (ordenTipo == 2) {
                        int cantPuntos = vista.leerEntero("Ingrese la cantidad de puntos:");
                        int cantidadVisitas = vista.leerEntero("Ingrese la cantidad de visitas:");
                        int horaEstimada = vista.leerEntero("Ingrese la hora estimada:");
                        double minutosEstimados = vista.leerDecimal("Ingrese los minutos estimados:");
                        boolean esMetropolitano = vista.validar("¿Es metropolitano? (si/no):");
                        double costoInstalacion = Instalacion.cotizar(cantidadVisitas,cantPuntos,horaEstimada, minutosEstimados, esMetropolitano);
                        vista.mostrarMensaje("El costo de la instalación es: " + costoInstalacion);
                    } else if (ordenTipo == 3) {
                        int cantPuntos = vista.leerEntero("Ingrese la cantidad de puntos:");
                        int cantidadVisitas = vista.leerEntero("Ingrese la cantidad de visitas:");
                        int horaEstimada = vista.leerEntero("Ingrese la hora estimada:");
                        double minutosEstimados = vista.leerDecimal("Ingrese los minutos estimados:");
                        boolean esMetropolitano = vista.validar("¿Es metropolitano? (si/no):");
                        double costMateriales = vista.leerDecimal("Ingrese el costo de los materiales:");
                        double costoInstalacionElectrica = InstalacionElectrica.cotizar(cantidadVisitas, cantPuntos, horaEstimada, minutosEstimados, esMetropolitano, costMateriales);
                        vista.mostrarMensaje("El costo de la instalación eléctrica es: " + costoInstalacionElectrica);
                    } else if (ordenTipo == 4) {
                        int cantidadVisitas = vista.leerEntero("Ingrese la cantidad de visitas:");
                        int horaEstimada = vista.leerEntero("Ingrese la hora estimada:");
                        double minutosEstimados = vista.leerDecimal("Ingrese los minutos estimados:");
                        boolean esMetropolitano = vista.validar("¿Es metropolitano? (si/no):");
                        double costoMantenimientoAC = MantenimientoAC.cotizar(cantidadVisitas, horaEstimada, minutosEstimados, esMetropolitano);
                        vista.mostrarMensaje("El costo del mantenimiento de aire acondicionado es: " + costoMantenimientoAC);
                }
                break;
            }

            case 8:
                vista.mostrarMensaje("Saliendo del programa...");
                break;

            default:
                throw new IllegalArgumentException("Opción inválida.");
        }
    }
}
