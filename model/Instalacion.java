package model;

public class Instalacion extends OrdenDeServicio {
    private static int cantInstalaciones = 0;
    protected int cantPuntos;

    public Instalacion(String nombreCliente, String direccion, double horasEstimadas, boolean esMetropolitano, int cantPuntos, String estado) {
        super(nombreCliente, direccion, horasEstimadas, esMetropolitano, estado, 0);
        cantInstalaciones++;
        setCodigo("I -" + cantInstalaciones);
        this.cantPuntos = cantPuntos;
        setCostoServicio(calcularCosto());
        setTotal(getCostoServicio());
    }

    public Instalacion(int cantPuntos, String nombreCliente, int horaEstimada, double minutosEstimados, boolean esMetropolitano, int cantidadVisitas, String estado, double total) {
        super(nombreCliente, horaEstimada, minutosEstimados, esMetropolitano, cantidadVisitas, estado, total);
        cantInstalaciones++;
        setCodigo("I -" + cantInstalaciones);
        this.cantPuntos = cantPuntos;
        setCostoServicio(calcularCosto());
        setTotal(getCostoServicio());
    }

    public int getCantPuntos() {
        return cantPuntos;
    }

    public void setCantPuntos(int cantPuntos) {
        this.cantPuntos = cantPuntos;
    }

    @Override
    public double calcularCosto() {
        double horasPorPuntos = cantPuntos * 0.75;
        double horasFacturables = Math.max(getHorasEstimadas(), horasPorPuntos);
        double horasAFacturar = Math.ceil(horasFacturables);
        double tarifaBase = isEsMetropolitano() ? 100.0 : 175.0;
        return tarifaBase + (horasAFacturar * 90.0);
    }

    @Override
    public int getTiempoEstimadoDias() {
        return isEsMetropolitano() ? 3 : 5;
    }

    @Override
    public int getMaxVisitasPermitidas() {
        return 3;
    }

    @Override
    public boolean requiereCertificacionRefrigeracion() {
        return false;
    }

    @Override
    public String getCategoria() {
        return "Instalación";
    }

    public static double cotizar(int cantPuntos, double horasEstimadas, boolean esMetropolitano) {
        if (cantPuntos <= 0 || horasEstimadas <= 0) {
            return 0;
        }
        double horasPorPuntos = cantPuntos * 0.75;
        double horasFacturables = Math.max(horasEstimadas, horasPorPuntos);
        double horasAFacturar = Math.ceil(horasFacturables);
        double tarifaBase = esMetropolitano ? 100.0 : 175.0;
        return tarifaBase + (horasAFacturar * 90.0);
    }

    public static double cotizar(int cantidadVisitas, int cantPuntos, int horaEstimada, double minutosEstimados, boolean esMetropolitano) {
        return cotizar(cantPuntos, horaEstimada + (minutosEstimados / 60.0), esMetropolitano);
    }

    public static int tiempoEstimado(boolean esMetropolitano) {
        return esMetropolitano ? 3 : 5;
    }

    public int getDiasEstimados() {
        return getTiempoEstimadoDias();
    }
}
