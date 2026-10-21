package model;

public class Diagnostico extends OrdenDeServicio {
    private static int cantDiagnosticos = 0;

    public Diagnostico(String nombreCliente, String direccion, double horasEstimadas, boolean esMetropolitano, String estado) {
        super(nombreCliente, direccion, horasEstimadas, esMetropolitano, estado, esMetropolitano ? 150.0 : 225.0);
        cantDiagnosticos++;
        setCodigo("D -" + cantDiagnosticos);
        setCostoServicio(calcularCosto());
        setTotal(getCostoServicio());
    }

    public Diagnostico(String nombreCliente, int horaEstimada, double minutosEstimados, boolean esMetropolitano, int cantidadVisitas, String estado, double total) {
        super(nombreCliente, horaEstimada, minutosEstimados, esMetropolitano, cantidadVisitas, estado, total);
        cantDiagnosticos++;
        setCodigo("D -" + cantDiagnosticos);
        setCostoServicio(calcularCosto());
        setTotal(getCostoServicio());
    }

    @Override
    public double calcularCosto() {
        return isEsMetropolitano() ? 150.0 : 225.0;
    }

    @Override
    public int getTiempoEstimadoDias() {
        return isEsMetropolitano() ? 1 : 2;
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
        return "Diagnóstico";
    }

    public static double cotizar(double horasEstimadas, boolean esMetropolitano) {
        if (horasEstimadas <= 0 || horasEstimadas > 1.5) {
            return 0;
        }
        return esMetropolitano ? 150.0 : 225.0;
    }

    public static double cotizar(int horasEstimadas, int cantidadVisitas, boolean esMetropolitano) {
        return cotizar((double) horasEstimadas, esMetropolitano);
    }

    public static int tiempoEstimado(boolean esMetropolitano) {
        return esMetropolitano ? 1 : 2;
    }

    public int getDiasEstimados() {
        return getTiempoEstimadoDias();
    }
}
