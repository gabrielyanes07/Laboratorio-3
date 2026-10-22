# TecniHogar — Sistema de Gestión de Órdenes

Aplicación de consola en Java para cotizar y registrar servicios, gestionar cuadrillas y jornadas, registrar resultados de visitas y consultar reportes de gerencia.

## Requisitos

- Java 25 o superior instalado y disponible en el `PATH`.
- Una terminal: PowerShell en Windows, o Bash en Linux/macOS.

Comprueba la versión instalada:

```sh
java -version
```

Los archivos entregados son `.class` ya compilados con versión de bytecode **69 (Java 25)**. No necesitas compilarlos. Java 8, 11, 17 y 21 no pueden ejecutarlos. No se adjuntaron los archivos fuente `.java`.

## Preparar los archivos

Guarda los diez archivos `.class` en una misma carpeta, por ejemplo `TecniHogar`, y abre la terminal en esa carpeta. Aunque se entregaron juntos, las clases pertenecen a distintos paquetes y deben organizarse antes de ejecutar el programa.

### Windows (PowerShell)

```powershell
New-Item -ItemType Directory -Force -Path model, controller, view | Out-Null
Copy-Item ControladorCuadrillas.class controller\
Copy-Item VistaConsola.class view\
Copy-Item Cuadrilla.class, Diagnostico.class, Instalacion.class, InstalacionElectrica.class, Jornada.class, MantenimientoAC.class, OrdenDeServicio.class model\
```

`Main.class` debe permanecer en la carpeta principal.

### Linux/macOS (Bash)

```bash
mkdir -p model controller view
cp ControladorCuadrillas.class controller/
cp VistaConsola.class view/
cp Cuadrilla.class Diagnostico.class Instalacion.class InstalacionElectrica.class Jornada.class MantenimientoAC.class OrdenDeServicio.class model/
```

### Ubicación de las clases

| Carpeta | Archivos |
| --- | --- |
| Principal (`TecniHogar`) | `Main.class` |
| `controller/` | `ControladorCuadrillas.class` |
| `view/` | `VistaConsola.class` |
| `model/` | `Cuadrilla.class`, `Diagnostico.class`, `Instalacion.class`, `InstalacionElectrica.class`, `Jornada.class`, `MantenimientoAC.class`, `OrdenDeServicio.class` |

Los comandos de copia conservan los archivos originales; las copias dentro de los paquetes son las utilizadas al ejecutar.

## Ejecutar

Desde la carpeta principal que contiene `Main.class` y las tres subcarpetas, ejecuta:

```sh
java -cp . Main
```

El programa debe mostrar el mensaje `Sistema de órdenes lanzado correctamente.` y el menú de TecniHogar. Ingresa el número de una opción y presiona Enter.

## Uso básico

| Opción | Acción |
| --- | --- |
| 1 | Cotizar orden de servicio |
| 2 | Registrar orden de servicio |
| 3 | Registrar cuadrilla |
| 4 | Crear jornada para una cuadrilla |
| 5 | Asignar orden a una jornada |
| 6 | Retirar orden de una jornada |
| 7 | Iniciar una jornada |
| 8 | Registrar resultados de visitas de una jornada |
| 9 | Consultar órdenes y cuadrillas |
| 10 | Generar reportes de gerencia |
| 11 | Salir del programa |

Para una primera prueba, usa la opción **9** para consultar los datos iniciales. Luego crea una jornada con **4**, asigna una orden con **5**, inicia la jornada con **7** y registra el resultado de la visita con **8**. Usa los códigos que muestre el sistema.

- Escribe los decimales con punto, por ejemplo `1.5`.
- Responde `si` o `no` cuando se solicite.
- Los servicios de aire acondicionado requieren una cuadrilla certificada en refrigeración.
- La suma de horas de las órdenes no puede superar la capacidad de la cuadrilla.
- Los datos se gestionan en memoria; al cerrar y volver a ejecutar se cargan nuevamente los datos iniciales.

## Problemas frecuentes

| Error | Solución |
| --- | --- |
| `java` no se reconoce o `command not found` | Instala Java 25 o superior, agrega su carpeta `bin` al `PATH` y abre una terminal nueva. |
| `UnsupportedClassVersionError` | Verifica con `java -version` que se esté utilizando Java 25 o superior. |
| `Could not find or load main class Main` | Ejecuta el comando desde la carpeta que contiene `Main.class` y usa `java -cp . Main`. |
| `NoClassDefFoundError` con `model`, `view` o `controller` | Revisa que los archivos estén en las subcarpetas correspondientes y respeta las mayúsculas de los nombres. |

## Alcance de la verificación

Se revisaron las clases entregadas para identificar la versión de Java, los paquetes, el punto de entrada y las opciones del menú. No se pudo probar la ejecución en el entorno de revisión porque la instalación de Java falla al iniciar por falta de `libjli.so`.
