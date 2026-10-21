package model;

public class InstalacionElectrica extends Instalacion {
    private static int cantInstalacionesElectricas = 0;
    private double costMateriales;

    public InstalacionElectrica(String nombreCliente, String direccion, double horasEstimadas, boolean esMetropolitano, int cantPuntos, double costMateriales, String estado) {
        super(nombreCliente, direccion, horasEstimadas, esMetropolitano, cantPuntos, estado);
        cantInstalacionesElectricas++;
        setCodigo("IE -" + cantInstalacionesElectricas);
        this.costMateriales = costMateriales;
        setCostoServicio(calcularCosto());
        setTotal(getCostoServicio());
    }

    public InstalacionElectrica(int cantPuntos, double costMateriales, String nombreCliente, int horaEstimada, double minutosEstimados, boolean esMetropolitano, int cantidadVisitas, String estado, double total) {
        super(cantPuntos, nombreCliente, horaEstimada, minutosEstimados, esMetropolitano, cantidadVisitas, estado, total);
        cantInstalacionesElectricas++;
        setCodigo("IE -" + cantInstalacionesElectricas);
        this.costMateriales = costMateriales;
        setCostoServicio(calcularCosto());
        setTotal(getCostoServicio());
    }

    public double getCostMateriales() {
        return costMateriales;
    }

    public void setCostMateriales(double costMateriales) {
        this.costMateriales = costMateriales;
    }

    @Override
    public double calcularCosto() {
        return super.calcularCosto() + 120.0 + (costMateriales * 0.05);
    }

    @Override
    public int getTiempoEstimadoDias() {
        return super.getTiempoEstimadoDias() + 2;
    }

    @Override
    public int getMaxVisitasPermitidas() {
        return 2;
    }

    @Override
    public boolean requiereCertificacionRefrigeracion() {
        return false;
    }

    @Override
    public String getCategoria() {
        return "Instalación Eléctrica Certificada";
    }

    public static double cotizar(int cantPuntos, double horasEstimadas, boolean esMetropolitano, double costMateriales) {
        if (cantPuntos <= 0 || horasEstimadas <= 0 || costMateriales < 0) {
            return 0;
        }
        double base = Instalacion.cotizar(cantPuntos, horasEstimadas, esMetropolitano);
        return base + 120.0 + (costMateriales * 0.05);
    }

    public static double cotizar(int cantidadVisitas, int cantPuntos, int horaEstimada, double minutosEstimados, boolean esMetropolitano, double costMateriales) {
        return cotizar(cantPuntos, horaEstimada + (minutosEstimados / 60.0), esMetropolitano, costMateriales);
    }

    public static int tiempoEstimado(boolean esMetropolitano) {
        return Instalacion.tiempoEstimado(esMetropolitano) + 2;
    }
}
