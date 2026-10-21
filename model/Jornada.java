package model;

import java.util.ArrayList;

public class Jornada {
    private ArrayList<OrdenDeServicio> ordenes;
    private String codigo;
    private static int numJornadas = 0;
    private Cuadrilla cuadrilla;
    private String estado;

    public Jornada() {
        this.codigo = "JOR -" + (++numJornadas);
        this.ordenes = new ArrayList<>();
        this.estado = "en preparación";
    }

    public Jornada(Cuadrilla cuadrilla) {
        this();
        this.cuadrilla = cuadrilla;
    }

    public String getCodigo() {
        return codigo;
    }

    public Cuadrilla getCuadrilla() {
        return cuadrilla;
    }

    public void setCuadrilla(Cuadrilla cuadrilla) {
        this.cuadrilla = cuadrilla;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public double contarHoras() {
        double totalHoras = 0;
        for (OrdenDeServicio orden : ordenes) {
            totalHoras += orden.getHorasEstimadas();
        }
        return totalHoras;
    }

    public double getCapacidadDisponible() {
        if (cuadrilla == null) {
            return 0;
        }
        return Math.max(0, cuadrilla.getMaxHora() - contarHoras());
    }

    public double contarCosto() {
        double totalCosto = 0;
        for (OrdenDeServicio orden : ordenes) {
            totalCosto += orden.getCostoServicio();
        }
        return totalCosto;
    }

    public double contarIngresoTotal() {
        return contarCosto();
    }

    public void agregarOrden(OrdenDeServicio orden) {
        if (orden != null) {
            this.ordenes.add(orden);
        }
    }

    public boolean retirarOrden(String codigoOrden) {
        if (!estado.equalsIgnoreCase("en preparación")) {
            return false;
        }
        for (int i = 0; i < ordenes.size(); i++) {
            if (ordenes.get(i).getCodigo().replace(" ", "").equalsIgnoreCase(codigoOrden.replace(" ", ""))) {
                OrdenDeServicio removida = ordenes.remove(i);
                removida.setEstado("pendiente");
                return true;
            }
        }
        return false;
    }

    public double iniciarJornada() {
        if (!estado.equalsIgnoreCase("en preparación") || ordenes.isEmpty()) {
            return -1;
        }
        estado = "en atención";
        double cargoAdicional = 0;
        for (OrdenDeServicio orden : ordenes) {
            if (orden.getVisitasRealizadas() >= 1) {
                cargoAdicional += 40.0;
            }
            orden.setEstado("en atención");
        }
        return cargoAdicional;
    }

    public boolean registrarResultado(String codigoOrden, boolean completada) {
        if (!estado.equalsIgnoreCase("en atención")) {
            return false;
        }
        OrdenDeServicio ordenTarget = null;
        for (OrdenDeServicio o : ordenes) {
            if (o.getCodigo().replace(" ", "").equalsIgnoreCase(codigoOrden.replace(" ", ""))) {
                ordenTarget = o;
                break;
            }
        }
        if (ordenTarget == null || !ordenTarget.getEstado().equalsIgnoreCase("en atención")) {
            return false;
        }
        ordenTarget.registrarVisita(completada);
        boolean todasAtendidas = true;
        for (OrdenDeServicio o : ordenes) {
            if (o.getEstado().equalsIgnoreCase("en atención")) {
                todasAtendidas = false;
                break;
            }
        }
        if (todasAtendidas) {
            estado = "cerrada";
        }
        return true;
    }

    public ArrayList<OrdenDeServicio> getOrdenes() {
        return ordenes;
    }

    public boolean todasOrdenesAtendidas() {
        for (OrdenDeServicio o : ordenes) {
            if (o.getEstado().equalsIgnoreCase("en atención")) {
                return false;
            }
        }
        return true;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Jornada: ").append(codigo).append(" | Estado: ").append(estado)
          .append(" | Horas: ").append(String.format("%.2f", contarHoras()))
          .append(" | Capacidad disponible: ").append(String.format("%.2f", getCapacidadDisponible()))
          .append("\n");
        for (OrdenDeServicio orden : ordenes) {
            sb.append("    ").append(orden.toString()).append("\n");
        }
        return sb.toString();
    }
}
