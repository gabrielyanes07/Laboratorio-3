package controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
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
    private ArrayList<OrdenDeServicio> ordenes;
    private Cuadrilla modelo;
    private VistaConsola vista;
    private double ingresosPorRegistro;
    private double ingresosPorVisitasAdicionales;
    private Map<String, Double> ingresosPorCategoria;

    public ControladorCuadrillas(Cuadrilla modelo, VistaConsola vista) {
        this.cuadrillas = new ArrayList<>();
        this.ordenes = new ArrayList<>();
        this.modelo = modelo;
        this.vista = vista;
        this.ingresosPorRegistro = 0;
        this.ingresosPorVisitasAdicionales = 0;
        this.ingresosPorCategoria = new HashMap<>();
        this.ingresosPorCategoria.put("Diagnóstico", 0.0);
        this.ingresosPorCategoria.put("Instalación", 0.0);
        this.ingresosPorCategoria.put("Instalación Eléctrica Certificada", 0.0);
        this.ingresosPorCategoria.put("Mantenimiento de Aire Acondicionado", 0.0);
        cargarDatosIniciales();
    }

    private void cargarDatosIniciales() {
        Cuadrilla c1 = new Cuadrilla(8.0, true);
        Cuadrilla c2 = new Cuadrilla(6.0, false);
        Cuadrilla c3 = new Cuadrilla(10.0, true);
        agregarCuadrilla(c1);
        agregarCuadrilla(c2);
        agregarCuadrilla(c3);

        Diagnostico d1 = new Diagnostico("Carlos Gomez", "Zona 11", 1.0, true, "pendiente");
        Diagnostico d2 = new Diagnostico("Marta Ruiz", "Carretera a El Salvador", 1.5, false, "pendiente");

        Instalacion i1 = new Instalacion("Roberto Perez", "Zona 14", 2.5, true, 5, "pendiente");
        Instalacion i2 = new Instalacion("Sonia Lopez", "Mixco", 3.0, false, 2, "pendiente");

        InstalacionElectrica ie1 = new InstalacionElectrica("Pedro Ramirez", "Zona 4", 2.5, true, 5, 2000.0, "pendiente");
        InstalacionElectrica ie2 = new InstalacionElectrica("Jorge Morales", "Villa Nueva", 4.0, false, 4, 1000.0, "pendiente");

        MantenimientoAC ac1 = new MantenimientoAC("Oficinas Centrales", "Zona 9", 2.0, true, 18000.0, "pendiente");
        MantenimientoAC ac2 = new MantenimientoAC("Clinica Dental", "Zona 10", 3.0, true, 24000.0, "pendiente");

        ordenes.add(d1);
        ordenes.add(d2);
        ordenes.add(i1);
        ordenes.add(i2);
        ordenes.add(ie1);
        ordenes.add(ie2);
        ordenes.add(ac1);
        ordenes.add(ac2);
    }

    public ArrayList<Cuadrilla> mostrarCuadrillas() {
        return cuadrillas;
    }

    public Cuadrilla getCuadrillaPorCodigo(String codigo) {
        if (codigo == null) return null;
        for (Cuadrilla c : cuadrillas) {
            if (c.getCodigo().equalsIgnoreCase(codigo) || 
                c.getCodigo().replace(" ", "").equalsIgnoreCase(codigo.replace(" ", ""))) {
                return c;
            }
        }
        return null;
    }

    public OrdenDeServicio getOrdenPorCodigo(String codigo) {
        if (codigo == null) return null;
        for (OrdenDeServicio o : ordenes) {
            if (o.getCodigo().equalsIgnoreCase(codigo) || 
                o.getCodigo().replace(" ", "").equalsIgnoreCase(codigo.replace(" ", ""))) {
                return o;
            }
        }
        return null;
    }

    public void agregarCuadrilla(Cuadrilla cuadrilla) {
        cuadrillas.add(cuadrilla);
    }

    public void agregarOrden(OrdenDeServicio orden) {
        ordenes.add(orden);
    }

    public void iniciar() {
        int opcion = 0;
        do {
            try {
                vista.mostrarMenu();
                opcion = vista.solicitarOpcion();
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
        } while (opcion != 11);
    }

    public void gestionarOpcion(int opcion) {
        switch (opcion) {
            case 1:
                cotizarOrdenServicio();
                break;

            case 2:
                registrarOrdenServicio();
                break;

            case 3:
                registrarCuadrilla();
                break;

            case 4:
                crearJornada();
                break;

            case 5:
                asignarOrdenAJornada();
                break;

            case 6:
                retirarOrdenDeJornada();
                break;

            case 7:
                iniciarJornada();
                break;

            case 8:
                registrarResultadosVisitas();
                break;

            case 9:
                vista.mostrarOrdenesYCuadrillas(cuadrillas, ordenes);
                break;

            case 10:
                generarReportes();
                break;

            case 11:
                vista.mostrarMensaje("Saliendo del programa...");
                break;

            default:
                throw new IllegalArgumentException("Opción inválida.");
        }
    }

    private void cotizarOrdenServicio() {
        vista.mostrarMensaje("COTIZAR ORDEN DE SERVICIO");
        vista.mostrarMensaje("1. Diagnóstico");
        vista.mostrarMensaje("2. Instalación");
        vista.mostrarMensaje("3. Instalación Eléctrica Certificada");
        vista.mostrarMensaje("4. Mantenimiento de Aire Acondicionado");
        int tipo = vista.leerEntero("Seleccione la categoría de servicio:");
        if (tipo < 1 || tipo > 4) {
            vista.mostrarError("Categoría de servicio inválida.");
            return;
        }

        boolean esMetro = vista.validar("¿Es zona metropolitana? (si/no):");
        double costo = 0;
        int dias = 0;

        if (tipo == 1) {
            double horas = vista.validarHoras("Ingrese las horas estimadas (máximo 1.5):");
            costo = Diagnostico.cotizar(horas, esMetro);
            dias = Diagnostico.tiempoEstimado(esMetro);

        } else if (tipo == 2) {
            int puntos = vista.leerEntero("Ingrese la cantidad de puntos a instalar:");
            double horas = vista.leerDecimal("Ingrese las horas estimadas de trabajo:");
            costo = Instalacion.cotizar(puntos, horas, esMetro);
            dias = Instalacion.tiempoEstimado(esMetro);

        } else if (tipo == 3) {
            int puntos = vista.leerEntero("Ingrese la cantidad de puntos a instalar:");
            double horas = vista.leerDecimal("Ingrese las horas estimadas de trabajo:");
            double materiales = vista.leerDecimal("Ingrese el valor declarado de los materiales (Q):");
            costo = InstalacionElectrica.cotizar(puntos, horas, esMetro, materiales);
            dias = InstalacionElectrica.tiempoEstimado(esMetro);

        } else if (tipo == 4) {
            if (!esMetro) {
                vista.mostrarError("Solo se aceptan mantenimientos de aire acondicionado en la zona metropolitana.");
                return;
            }
            double btu = vista.leerDecimal("Ingrese la capacidad del equipo en BTU (entre 9,000 y 60,000):");
            if (btu < 9000 || btu > 60000) {
                vista.mostrarError("La capacidad en BTU debe estar entre 9,000 y 60,000.");
                return;
            }
            double horas = vista.leerDecimal("Ingrese las horas estimadas de trabajo:");
            costo = MantenimientoAC.cotizar(horas, esMetro, btu);
            dias = MantenimientoAC.tiempoEstimado();
        }

        vista.mostrarMensaje(String.format("Cotización calculada:\n  - Costo estimado: Q%.2f\n  - Tiempo estimado de atención: %d días hábiles", costo, dias));
    }

    private void registrarOrdenServicio() {
        vista.mostrarMensaje("REGISTRAR NUEVA ORDEN DE SERVICIO");
        vista.mostrarMensaje("1. Diagnóstico");
        vista.mostrarMensaje("2. Instalación");
        vista.mostrarMensaje("3. Instalación Eléctrica Certificada");
        vista.mostrarMensaje("4. Mantenimiento de Aire Acondicionado");
        int tipo = vista.leerEntero("Seleccione la categoría de servicio:");
        if (tipo < 1 || tipo > 4) {
            vista.mostrarError("Categoría de servicio inválida.");
            return;
        }

        String cliente = vista.leerCadena("Ingrese el nombre del cliente:");
        String direccion = vista.leerCadena("Ingrese la dirección del cliente:");
        boolean esMetro = vista.validar("¿Es zona metropolitana? (si/no):");

        OrdenDeServicio nuevaOrden = null;

        if (tipo == 1) {
            double horas = vista.validarHoras("Ingrese las horas estimadas (máximo 1.5):");
            nuevaOrden = new Diagnostico(cliente, direccion, horas, esMetro, "pendiente");

        } else if (tipo == 2) {
            int puntos = vista.leerEntero("Ingrese la cantidad de puntos a instalar:");
            double horas = vista.leerDecimal("Ingrese las horas estimadas de trabajo:");
            nuevaOrden = new Instalacion(cliente, direccion, horas, esMetro, puntos, "pendiente");

        } else if (tipo == 3) {
            int puntos = vista.leerEntero("Ingrese la cantidad de puntos a instalar:");
            double horas = vista.leerDecimal("Ingrese las horas estimadas de trabajo:");
            double materiales = vista.leerDecimal("Ingrese el valor declarado de los materiales (Q):");
            nuevaOrden = new InstalacionElectrica(cliente, direccion, horas, esMetro, puntos, materiales, "pendiente");

        } else if (tipo == 4) {
            if (!esMetro) {
                vista.mostrarError("Solo se aceptan mantenimientos de aire acondicionado en la zona metropolitana.");
                return;
            }
            double btu = vista.leerDecimal("Ingrese la capacidad del equipo en BTU (entre 9,000 y 60,000):");
            if (btu < 9000 || btu > 60000) {
                vista.mostrarError("La capacidad en BTU debe estar entre 9,000 y 60,000.");
                return;
            }
            double horas = vista.leerDecimal("Ingrese las horas estimadas de trabajo:");
            nuevaOrden = new MantenimientoAC(cliente, direccion, horas, esMetro, btu, "pendiente");
        }

        if (nuevaOrden == null) {
            vista.mostrarError("No se pudo generar la orden de servicio.");
            return;
        }

        vista.mostrarMensaje(String.format("Detalle de la cotización:\n  - Costo: Q%.2f\n  - Tiempo estimado de atención: %d días hábiles", nuevaOrden.getCostoServicio(), nuevaOrden.getTiempoEstimadoDias()));
        boolean acepta = vista.validar("¿El cliente acepta la orden para registrarla y pagar? (si/no):");
        if (acepta) {
            ordenes.add(nuevaOrden);
            ingresosPorRegistro += nuevaOrden.getCostoServicio();
            double previoCat = ingresosPorCategoria.getOrDefault(nuevaOrden.getCategoria(), 0.0);
            ingresosPorCategoria.put(nuevaOrden.getCategoria(), previoCat + nuevaOrden.getCostoServicio());
            vista.mostrarMensaje("Orden registrada exitosamente con código: " + nuevaOrden.getCodigo() + " en estado 'pendiente'.");
        } else {
            vista.mostrarMensaje("La cotización no fue aceptada. No se modificaron los registros ni los ingresos.");
        }
    }

    private void registrarCuadrilla() {
        vista.mostrarMensaje("\n--- REGISTRAR CUADRILLA ---");
        double maxHora = vista.leerDecimal("Ingrese la capacidad máxima de horas por jornada:");
        if (maxHora <= 0) {
            vista.mostrarError("La capacidad de horas debe ser mayor a cero.");
            return;
        }
        boolean cert = vista.validar("¿La cuadrilla está certificada en refrigeración? (si/no):");
        Cuadrilla nuevaCuadrilla = new Cuadrilla(maxHora, cert);
        agregarCuadrilla(nuevaCuadrilla);
        vista.mostrarMensaje("Se ha registrado la cuadrilla con código: " + nuevaCuadrilla.getCodigo());
    }

    private void crearJornada() {
        vista.mostrarMensaje("CREAR JORNADA PARA CUADRILLA");
        String codigoCuadrilla = vista.solicitarCodigo();
        Cuadrilla cuadrilla = getCuadrillaPorCodigo(codigoCuadrilla);
        if (cuadrilla == null) {
            vista.mostrarError("No se encontró ninguna cuadrilla con el código proporcionado.");
            return;
        }

        if (cuadrilla.tieneJornadaSinCerrar()) {
            vista.mostrarError("La cuadrilla " + cuadrilla.getCodigo() + " ya tiene una jornada sin cerrar. No puede tener más de una jornada activa.");
            return;
        }

        Jornada nuevaJornada = new Jornada(cuadrilla);
        cuadrilla.agregarJornada(nuevaJornada);
        vista.mostrarMensaje("Se ha creado exitosamente la jornada " + nuevaJornada.getCodigo() + " en estado 'en preparación' para la cuadrilla " + cuadrilla.getCodigo());
    }

    private void asignarOrdenAJornada() {
        vista.mostrarMensaje("\nASIGNAR ORDEN A JORNADA");
        String codigoCuadrilla = vista.solicitarCodigo();
        Cuadrilla cuadrilla = getCuadrillaPorCodigo(codigoCuadrilla);
        if (cuadrilla == null) {
            vista.mostrarError("No se encontró ninguna cuadrilla con el código proporcionado.");
            return;
        }

        Jornada jornada = cuadrilla.getJornadaActiva();
        if (jornada == null) {
            vista.mostrarError("La cuadrilla no tiene una jornada activa. Cree una jornada primero.");
            return;
        }

        if (!jornada.getEstado().equalsIgnoreCase("en preparación")) {
            vista.mostrarError("La jornada " + jornada.getCodigo() + " está en estado '" + jornada.getEstado() + "'. Solo se pueden asignar órdenes mientras la jornada esté en preparación.");
            return;
        }

        vista.mostrarMensaje("Órdenes pendientes disponibles:");
        boolean hayPendientes = false;
        for (OrdenDeServicio o : ordenes) {
            if (o.getEstado().equalsIgnoreCase("pendiente")) {
                vista.mostrarMensaje("  " + o.toString());
                hayPendientes = true;
            }
        }
        if (!hayPendientes) {
            vista.mostrarError("No hay órdenes pendientes disponibles para asignar.");
            return;
        }

        String codigoOrden = vista.solicitarCodigo("Ingrese el código de la orden a asignar:");
        OrdenDeServicio orden = getOrdenPorCodigo(codigoOrden);
        if (orden == null) {
            vista.mostrarError("No se encontró ninguna orden con el código proporcionado.");
            return;
        }

        if (!orden.getEstado().equalsIgnoreCase("pendiente")) {
            vista.mostrarError("La orden no está disponible para asignación. Su estado actual es: " + orden.getEstado());
            return;
        }

        for (OrdenDeServicio asignada : jornada.getOrdenes()) {
            if (asignada.getCodigo().equalsIgnoreCase(orden.getCodigo())) {
                vista.mostrarError("La orden ya se encuentra asignada a esta jornada.");
                return;
            }
        }

        if (orden.requiereCertificacionRefrigeracion() && !cuadrilla.isCertificada()) {
            vista.mostrarError("Incompatibilidad: Los mantenimientos de aire acondicionado solo pueden asignarse a cuadrillas certificadas en refrigeración.");
            return;
        }

        if (jornada.contarHoras() + orden.getHorasEstimadas() > cuadrilla.getMaxHora()) {
            vista.mostrarError(String.format("Capacidad excedida: Las horas estimadas de la orden (%.2f hrs) superan la capacidad disponible de la cuadrilla (%.2f hrs).",
                    orden.getHorasEstimadas(), jornada.getCapacidadDisponible()));
            return;
        }

        jornada.agregarOrden(orden);
        vista.mostrarMensaje(String.format("Orden %s asignada exitosamente a la jornada %s. Horas asignadas: %.2f / %.2f hrs.",
                orden.getCodigo(), jornada.getCodigo(), jornada.contarHoras(), cuadrilla.getMaxHora()));
    }

    private void retirarOrdenDeJornada() {
        vista.mostrarMensaje("RETIRAR ORDEN DE JORNADA");
        String codigoCuadrilla = vista.solicitarCodigo();
        Cuadrilla cuadrilla = getCuadrillaPorCodigo(codigoCuadrilla);
        if (cuadrilla == null) {
            vista.mostrarError("No se encontró ninguna cuadrilla con el código proporcionado.");
            return;
        }

        Jornada jornada = cuadrilla.getJornadaActiva();
        if (jornada == null) {
            vista.mostrarError("La cuadrilla no tiene una jornada activa.");
            return;
        }

        if (!jornada.getEstado().equalsIgnoreCase("en preparación")) {
            vista.mostrarError("Solo se pueden retirar órdenes mientras la jornada esté en preparación.");
            return;
        }

        if (jornada.getOrdenes().isEmpty()) {
            vista.mostrarError("La jornada no tiene órdenes asignadas.");
            return;
        }

        vista.mostrarMensaje("Órdenes asignadas a la jornada " + jornada.getCodigo() + ":");
        for (OrdenDeServicio o : jornada.getOrdenes()) {
            vista.mostrarMensaje("  " + o.toString());
        }

        String codigoOrden = vista.solicitarCodigo("Ingrese el código de la orden a retirar:");
        boolean retirada = jornada.retirarOrden(codigoOrden);
        if (retirada) {
            vista.mostrarMensaje("Orden " + codigoOrden + " retirada de la jornada exitosamente. Vuelve a estar 'pendiente'.");
        } else {
            vista.mostrarError("No se encontró la orden especificada en la jornada.");
        }
    }

    private void iniciarJornada() {
        vista.mostrarMensaje("INICIAR JORNADA");
        String codigoCuadrilla = vista.solicitarCodigo();
        Cuadrilla cuadrilla = getCuadrillaPorCodigo(codigoCuadrilla);
        if (cuadrilla == null) {
            vista.mostrarError("No se encontró ninguna cuadrilla con el código proporcionado.");
            return;
        }

        Jornada jornada = cuadrilla.getJornadaActiva();
        if (jornada == null) {
            vista.mostrarError("La cuadrilla no tiene una jornada activa.");
            return;
        }

        if (!jornada.getEstado().equalsIgnoreCase("en preparación")) {
            vista.mostrarError("La jornada ya ha sido iniciada previamente o no está en preparación (Estado actual: " + jornada.getEstado() + ").");
            return;
        }

        if (jornada.getOrdenes().isEmpty()) {
            vista.mostrarError("No se puede iniciar una jornada vacía. Debe asignarle al menos una orden.");
            return;
        }

        double cargoExtra = jornada.iniciarJornada();
        if (cargoExtra < 0) {
            vista.mostrarError("No fue posible iniciar la jornada.");
            return;
        }

        if (cargoExtra > 0) {
            ingresosPorVisitasAdicionales += cargoExtra;
            vista.mostrarMensaje(String.format("Se registraron cargos por visitas adicionales: Q%.2f (Q40 por cada visita posterior a la primera).", cargoExtra));
        }

        vista.mostrarMensaje("La jornada " + jornada.getCodigo() + " ha iniciado exitosamente y se encuentra 'en atención'. Todas sus órdenes pasaron a 'en atención'.");
    }

    private void registrarResultadosVisitas() {
        vista.mostrarMensaje("\n--- REGISTRAR RESULTADOS DE VISITAS ---");
        String codigoCuadrilla = vista.solicitarCodigo();
        Cuadrilla cuadrilla = getCuadrillaPorCodigo(codigoCuadrilla);
        if (cuadrilla == null) {
            vista.mostrarError("No se encontró ninguna cuadrilla con el código proporcionado.");
            return;
        }

        Jornada jornada = cuadrilla.getJornadaActiva();
        if (jornada == null || !jornada.getEstado().equalsIgnoreCase("en atención")) {
            vista.mostrarError("La cuadrilla no tiene una jornada en estado 'en atención'.");
            return;
        }

        ArrayList<OrdenDeServicio> pendientesDeResultado = new ArrayList<>();
        for (OrdenDeServicio o : jornada.getOrdenes()) {
            if (o.getEstado().equalsIgnoreCase("en atención")) {
                pendientesDeResultado.add(o);
            }
        }

        if (pendientesDeResultado.isEmpty()) {
            vista.mostrarMensaje("Todas las órdenes de la jornada ya tienen resultado registrado.");
            return;
        }

        vista.mostrarMensaje("Órdenes pendientes de resultado en la jornada " + jornada.getCodigo() + ":");
        for (OrdenDeServicio o : pendientesDeResultado) {
            vista.mostrarMensaje("  " + o.toString());
        }

        String codigoOrden = vista.solicitarCodigo("Ingrese el código de la orden a registrar resultado:");
        OrdenDeServicio ordenSeleccionada = null;
        for (OrdenDeServicio o : pendientesDeResultado) {
            if (o.getCodigo().replace(" ", "").equalsIgnoreCase(codigoOrden.replace(" ", ""))) {
                ordenSeleccionada = o;
                break;
            }
        }

        if (ordenSeleccionada == null) {
            vista.mostrarError("La orden especificada no está en atención en esta jornada.");
            return;
        }

        vista.mostrarMensaje("Seleccione el resultado de la visita:");
        vista.mostrarMensaje("1. Visita completada exitosamente");
        vista.mostrarMensaje("2. Visita fallida");
        int res = vista.leerEntero("Ingrese opción (1 o 2):");
        if (res != 1 && res != 2) {
            vista.mostrarError("Opción de resultado inválida.");
            return;
        }

        boolean completada = (res == 1);
        jornada.registrarResultado(ordenSeleccionada.getCodigo(), completada);

        vista.mostrarMensaje(String.format("Resultado registrado para %s. Estado actual de la orden: '%s' (Visitas realizadas: %d/%d).",
                ordenSeleccionada.getCodigo(), ordenSeleccionada.getEstado(), ordenSeleccionada.getVisitasRealizadas(), ordenSeleccionada.getMaxVisitasPermitidas()));

        if (jornada.getEstado().equalsIgnoreCase("cerrada")) {
            vista.mostrarMensaje("¡Todas las órdenes de la jornada " + jornada.getCodigo() + " han sido procesadas! La jornada ha quedado 'cerrada' y la cuadrilla " + cuadrilla.getCodigo() + " queda libre.");
        }
    }

    private void generarReportes() {
        vista.mostrarMensaje("==========================================================");
        vista.mostrarMensaje("                 REPORTES DE GERENCIA                     ");

        vista.mostrarMensaje("\n1. CANTIDAD DE ÓRDENES POR CATEGORÍA Y ESTADO:");
        String[] categorias = {"Diagnóstico", "Instalación", "Instalación Eléctrica Certificada", "Mantenimiento de Aire Acondicionado"};
        String[] estados = {"pendiente", "en atención", "completada", "cancelada"};

        for (String cat : categorias) {
            vista.mostrarMensaje("  [" + cat + "]");
            for (String est : estados) {
                int conteo = 0;
                for (OrdenDeServicio o : ordenes) {
                    if (o.getCategoria().equalsIgnoreCase(cat) && o.getEstado().equalsIgnoreCase(est)) {
                        conteo++;
                    }
                }
                vista.mostrarMensaje(String.format("    - %s: %d", est, conteo));
            }
        }

        vista.mostrarMensaje("\n2. REPORTE DE INGRESOS:");
        vista.mostrarMensaje(String.format("  - Ingresos por registro de órdenes: Q%.2f", ingresosPorRegistro));
        vista.mostrarMensaje(String.format("  - Ingresos por visitas adicionales: Q%.2f", ingresosPorVisitasAdicionales));
        double ingresoTotal = ingresosPorRegistro + ingresosPorVisitasAdicionales;
        vista.mostrarMensaje(String.format("  - INGRESO TOTAL: Q%.2f", ingresoTotal));
        vista.mostrarMensaje("  - Ingreso por categoría de servicio:");
        for (String cat : categorias) {
            double ingCat = ingresosPorCategoria.getOrDefault(cat, 0.0);
            vista.mostrarMensaje(String.format("    * %s: Q%.2f", cat, ingCat));
        }

        vista.mostrarMensaje("\n3. JORNADAS NO CERRADAS:");
        StringBuilder sbSinCerrar = new StringBuilder();
        for (Cuadrilla c : cuadrillas) {
            String info = c.obtenerJornadasSinCerrar();
            if (info != null) {
                sbSinCerrar.append(info);
            }
        }
        if (sbSinCerrar.length() == 0) {
            vista.mostrarMensaje("  (No hay jornadas sin cerrar. Todas las cuadrillas están libres).");
        } else {
            vista.mostrarMensaje(sbSinCerrar.toString().trim());
        }
        vista.mostrarMensaje("==========================================================");
    }
}
