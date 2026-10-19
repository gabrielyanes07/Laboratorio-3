package model;

public class MantenimientoAC extends OrdenDeServicio {
    private double costoServicio;
    private int cantMantenimientos = 0;
    private String codigo;
    private int diasEstimados;

    public MantenimientoAC(int cantPuntos, String codigo, String nombreCliente, int horaEstimada, boolean esMetropolitano, int cantidadVisitas, String estado, double total) {
            super(nombreCliente, horaEstimada, esMetropolitano, cantidadVisitas, estado, total);
            cantMantenimientos++;
            this.codigo = "MAC -" + cantMantenimientos;
            this.costoServicio = 100 + 80 + horaEstimada*110;
            this.diasEstimados = 2;
        }
    }
    
    public double getCostoServicio() {
        return costoServicio;
    }
    public String getCodigo() {
        return codigo;
    }
    @Override
    public String toString() {
        return super.toString() + ", Tipo: Mantenimiento de Aire Acondicionado, Costo Servicio: " + costoServicio;
    }
    
}
