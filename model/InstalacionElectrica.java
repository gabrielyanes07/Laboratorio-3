package model;
public class InstalacionElectrica extends OrdenDeServicio {
    private double costoServicio;
    private String codigo;
    private int cantInstalaciones = 0;
    private int diasEstimados;

    public InstalacionElectrica(int cantPuntos, double costMateriales, String nombreCliente, int horaEstimada, double minutosEstimados, boolean esMetropolitano, int cantidadVisitas, String estado, double total) {
        super(nombreCliente, horaEstimada, minutosEstimados, esMetropolitano, cantidadVisitas, estado, total);
        cantInstalaciones++;
        this.codigo = "IE -" + cantInstalaciones;
        if(cantidadVisitas > 2) {
            if (!esMetropolitano) {
                this.diasEstimados = 7;
                this.costoServicio = 260 + (horaEstimada + minutosEstimados / 60.0 + Math.round(cantPuntos * 0.75)) * 90 + costMateriales * 0.05;
            }
            else{
                this.diasEstimados = 5;
                this.costoServicio = 335 + (horaEstimada + minutosEstimados / 60.0 + Math.round(cantPuntos * 0.75)) * 90 + costMateriales * 0.05;
            }
        }
        else{
            if (!esMetropolitano) {
                this.diasEstimados = 7;
                this.costoServicio = 220 + (horaEstimada + minutosEstimados / 60.0 + Math.round(cantPuntos * 0.75)) * 90 + costMateriales * 0.05;
            }
            else{
                this.diasEstimados = 5;
                this.costoServicio = 295 + (horaEstimada + minutosEstimados / 60.0 + Math.round(cantPuntos * 0.75)) * 90 + costMateriales * 0.05;
            }  
        }
    }
    public double getCostoServicio() {
        return costoServicio;
    }
    public String getCodigo() {
        return codigo;
    }
    
    public static double cotizar(int cantidadVisitas, int cantPuntos, int horaEstimada,double minutosEstimados, boolean esMetropolitano, double costMateriales) {
        if(cantidadVisitas > 2) {
            if (!esMetropolitano) {
                return  260 + (horaEstimada + minutosEstimados / 60.0 + Math.round(cantPuntos * 0.75)) * 90 + costMateriales * 0.05;
            }
            else{
                return 335 + (horaEstimada + minutosEstimados / 60.0 + Math.round(cantPuntos * 0.75)) * 90 + costMateriales * 0.05;
            }
        }
        else{
            if (!esMetropolitano) {
                return 220 + (horaEstimada + minutosEstimados / 60.0 + Math.round(cantPuntos * 0.75)) * 90 + costMateriales * 0.05;
            }
            else{
                return 295 + (horaEstimada + minutosEstimados / 60.0 + Math.round(cantPuntos * 0.75)) * 90 + costMateriales * 0.05;
            }  
        }
    }
    public int getDiasEstimados() {
        return diasEstimados;
    }
    @Override 
    public String toString() {
        return super.toString() + ", Tipo: Instalación Eléctrica, Costo Servicio: " + costoServicio;
    }
}
