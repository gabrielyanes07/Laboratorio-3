package model;

public class MantenimientoAC extends OrdenDeServicio {
    private double costoServicio;
    private int cantMantenimientos = 0;
    private String codigo;
    private int diasEstimados;

    public MantenimientoAC(String nombreCliente, int horaEstimada,double minutosEstimados, boolean esMetropolitano, int cantidadVisitas, String estado, double total) {
            super(nombreCliente, horaEstimada, minutosEstimados,esMetropolitano, cantidadVisitas, estado, total);
            cantMantenimientos++;
            this.codigo = "MAC -" + cantMantenimientos;
            if (cantidadVisitas > 1) {
                    this.costoServicio = 140 + 80 + (horaEstimada + minutosEstimados/60)*110;
                    this.diasEstimados = 2;
                

            }
            else{
                    this.costoServicio = 100 + 80 + (horaEstimada + minutosEstimados/60)*110;
                    this.diasEstimados = 2;
            }
        }
    
    
    public double getCostoServicio() {
        return costoServicio;
    }
    public String getCodigo() {
        return codigo;
    }
    public int getDiasEstimados() {
        return diasEstimados;
    }

    public static double cotizar(int cantidadVisitas, int horaEstimada, double minutosEstimados, boolean esMetropolitano) {
            if (cantidadVisitas > 1) {
                return 140 + 80 + (horaEstimada + minutosEstimados/60)*110; 
            }
            else{
                return 100 + 80 + (horaEstimada + minutosEstimados/60)*110;
            }
        }
    @Override
    public String toString() {
        return super.toString() + ", Tipo: Mantenimiento de Aire Acondicionado, Costo Servicio: " + costoServicio;
    }
}
    

