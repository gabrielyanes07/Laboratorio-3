package model;

public class MantenimientoAC extends OrdenDeServicio {
    private static int cantMantenimientos = 0;
    private double capacidadBTU;

    public MantenimientoAC(String nombreCliente, String direccion, double horasEstimadas, boolean esMetropolitano, double capacidadBTU, String estado) {
        super(nombreCliente, direccion, horasEstimadas, esMetropolitano, estado, 0);
        cantMantenimientos++;
        setCodigo("MAC -" + cantMantenimientos);
        this.capacidadBTU = capacidadBTU;
        setCostoServicio(calcularCosto());
        setTotal(getCostoServicio());
    }

    public MantenimientoAC(String nombreCliente, int horaEstimada, double minutosEstimados, boolean esMetropolitano, int cantidadVisitas, String estado, double total) {
        super(nombreCliente, horaEstimada, minutosEstimados, esMetropolitano, cantidadVisitas, estado, total);
        cantMantenimientos++;
        setCodigo("MAC -" + cantMantenimientos);
        this.capacidadBTU = 12000;
        setCostoServicio(calcularCosto());
        setTotal(getCostoServicio());
    }

    public double getCapacidadBTU() {
        return capacidadBTU;
    }

    public void setCapacidadBTU(double capacidadBTU) {
        this.capacidadBTU = capacidadBTU;
    }

    @Override
    public double calcularCosto() {
        return 100.0 + (getHorasEstimadas() * 110.0) + 80.0;
    }

    @Override
    public int getTiempoEstimadoDias() {
        return 2;
    }

    @Override
    public int getMaxVisitasPermitidas() {
        return 1;
    }

    @Override
    public boolean requiereCertificacionRefrigeracion() {
        return true;
    }

    @Override
    public String getCategoria() {
        return "Mantenimiento de Aire Acondicionado";
    }

    public static double cotizar(double horasEstimadas, boolean esMetropolitano, double capacidadBTU) {
        if (!esMetropolitano || capacidadBTU < 9000 || capacidadBTU > 60000 || horasEstimadas <= 0) {
            return 0;
        }
        return 100.0 + (horasEstimadas * 110.0) + 80.0;
    }

    public static double cotizar(int cantidadVisitas, int horaEstimada, double minutosEstimados, boolean esMetropolitano) {
        if (!esMetropolitano) {
            return 0;
        }
        return 100.0 + ((horaEstimada + (minutosEstimados / 60.0)) * 110.0) + 80.0;
    }

    public static int tiempoEstimado() {
        return 2;
    }

    public int getDiasEstimados() {
        return getTiempoEstimadoDias();
    }
}
