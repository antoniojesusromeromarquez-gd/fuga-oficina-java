import java.util.Scanner;
import java.util.ArrayList;
import java.util.Queue;
import java.util.LinkedList;

public class Main {
    
    // Clase Molde para nuestros Objetos Bug
    static class Bug {
        String descripcion;
        int dañoEstres; 

        public Bug(String descripcion, int dañoEstres) {
            this.descripcion = descripcion;
            this.dañoEstres = dañoEstres;
        }
    }
    
    // Variables globales de estado
    static int vida = 100;
    static boolean juegoActivo = true;
    static Queue<Bug> colaBugs = new LinkedList<>();

    public static void main(String[] args) {
        Scanner teclado = new Scanner(System.in);
        ArrayList<String> inventario = new ArrayList<>();
        String habitacionActual = "mesa";
        
        // Inicializamos la cola con un par de bugs
        colaBugs.add(new Bug("Bug menor en las hojas de estilo CSS", 5));
        colaBugs.add(new Bug("Romper producción con un NullPointerException", 25));
        
        System.out.println("=== BIENVENIDO A: FUGA DE LA OFICINA (v4.5) ===");
        System.out.println("Es viernes, son las 17:50. Ahora tienes el control total de tus movimientos.");
        System.out.println("------------------------------------------------------------------");
        
        while (juegoActivo && vida > 0) {
            
            // Si hay 3 o más bugs acumulados, sufrimos daño por estrés al inicio del turno
            if (colaBugs.size() >= 3) {
                System.out.println("\n🚨 ¡BANDEJA DE ENTRADA SATURADA! Los bugs acumulados te pasan factura:");
                int dañoTotal = 0;
                for (Bug b : colaBugs) {
                    System.out.println("-> " + b.descripcion + " (Estrés: +" + b.dañoEstres + ")");
                    dañoTotal += b.dañoEstres;
                }
                vida -= dañoTotal;
                System.out.println("Daño por estrés recibido: " + dañoTotal + " | Vida restante: " + vida);
            }
            
            if (vida <= 0) break;
            
            switch (habitacionActual) {
                case "mesa":
                    habitacionActual = escenaMesa(teclado, inventario);
                    break;
                case "pasillo":
                    habitacionActual = escenaPasillo(teclado, inventario);
                    break;
                case "cafetería":
                    habitacionActual = escenaCafeteria(teclado, inventario);
                    break;
                case "salida":
                    habitacionActual = escenaSalida();
                    break;
                default:
                    juegoActivo = false;
            }
        }
        
        // FIN DEL JUEGO
        System.out.println("\n---------------------------------");
        if (vida <= 0) {
            System.out.println("GAME OVER: Colapso por deuda técnica. Te toca pringar el fin de semana.");
        } else {
            System.out.println("¡Fin de la simulación! Has escapado con éxito gestionando tus tiempos.");
        }
        teclado.close();
    }

    // ==========================================
    //           MÉTODOS DE LAS ESCENAS
    // ==========================================

    public static String escenaMesa(Scanner teclado, ArrayList<String> inventario) {
        System.out.println("\n[ESTÁS EN TU MESA]");
        System.out.println("Tienes " + colaBugs.size() + " tareas encoladas.");
        
        if (!colaBugs.isEmpty()) {
            System.out.println("El problema al frente de la cola es: \"" + colaBugs.peek().descripcion + "\"");
        }
        System.out.println("¿Qué quieres hacer? (mirar / trabajar / salir)");
        System.out.print("> ");
        String opcion = teclado.nextLine().toLowerCase();
        
        if (opcion.equals("mirar")) {
            if (!inventario.contains("contraseña")) {
                System.out.println("Ves un post-it con la contraseña del servidor. ¡Guardada!");
                inventario.add("contraseña");
            } else {
                System.out.println("No hay nada nuevo aquí.");
            }
            return "mesa";
        } else if (opcion.equals("trabajar")) {
            if (!colaBugs.isEmpty()) {
                Bug bugResuelto = colaBugs.poll(); 
                System.out.println("¡Solucionado! Has resuelto: \"" + bugResuelto.descripcion + "\"");
            } else {
                System.out.println("¡Bandeja vacía! Estás libre de tareas por ahora.");
            }
            return "mesa";
        } else if (opcion.equals("salir")) {
            return "pasillo";
        }
        return "mesa";
    }

    public static String escenaPasillo(Scanner teclado, ArrayList<String> inventario) {
        System.out.println("\n[ESTÁS EN EL PASILLO]");
        // MODIFICADO: Nueva opción añadida visualmente
        System.out.println("Opciones: (ir a cafetería / intentar salir / volver a la mesa)");
        System.out.print("> ");
        String opcion = teclado.nextLine().toLowerCase();
        
        // Riesgo de que entre un bug al pasear por aquí
        if (Math.random() < 0.5) {
            double azar = Math.random();
            if (azar < 0.6) {
                System.out.println("📩 Un QA te reporta un bug de interfaz.");
                colaBugs.add(new Bug("Texto desalineado en el menú", 8));
            } else {
                System.out.println("💥 ¡ALERTA! El CTO te asigna un error crítico.");
                colaBugs.add(new Bug("Caída de la base de datos en producción", 30));
            }
        }
        
        if (opcion.equals("ir a cafetería")) {
            return "cafetería";
        } else if (opcion.equals("intentar salir")) {
            if (inventario.contains("contraseña")) {
                System.out.println("Le gritas la contraseña al jefe y corres.");
                return "salida";
            } else {
                System.out.println("Te pilla el jefe. Charla insufrible y te manda de vuelta.");
                vida -= 50;
                return "mesa";
            }
        } else if (opcion.equals("volver a la mesa")) {
            // MODIFICADO: Lógica de retorno voluntario
            System.out.println("Decides que hay demasiada tensión en el pasillo y regresas a tu puesto.");
            return "mesa";
        }
        return "pasillo";
    }

    public static String escenaCafeteria(Scanner teclado, ArrayList<String> inventario) {
        System.out.println("\n[ESTÁS EN LA CAFETERÍA]");
        // MODIFICADO: Ahora puedes elegir a dónde volver
        System.out.println("¿Qué haces? (tomar café / volver al pasillo / volver a la mesa)");
        System.out.print("> ");
        String opcion = teclado.nextLine().toLowerCase();
        
        if (opcion.equals("tomar café")) {
            if (!inventario.contains("café")) {
                System.out.println("Subidón de cafeína. Recuperas 25 de vida.");
                vida = Math.min(100, vida + 25);
                inventario.add("café");
            } else {
                System.out.println("No queda más café.");
            }
            return "cafetería";
            
        } else if (opcion.equals("volver al pasillo") || opcion.equals("volver a la mesa")) {
            // Guardamos el destino original que eligió el usuario
            String destino = opcion.equals("volver al pasillo") ? "pasillo" : "mesa";
            
            // Sigue existiendo el riesgo de que el charlatán te embosque al salir de la cocina
            if (Math.random() < 0.4) { 
                System.out.println("\n⚠️ ¡EL CHARLATÁN TE EMBOSCA EN LA PUERTA!");
                if (inventario.contains("café")) {
                    System.out.println("Le das tu café al charlatán para poder huir.");
                    inventario.remove("café");
                    return destino; // Consigues llegar a donde querías
                } else {
                    System.out.println("Te drena la energía vital hablándote de Bitcoins.");
                    vida -= 30;
                    System.out.println("Paciencia actual: " + vida);
                    return destino; // Llegas cansado, pero llegas
                }
            }
            return destino; // Te mueves sin contratiempos
        }
        return "cafetería";
    }

    public static String escenaSalida() {
        System.out.println("\n[¡LIBERTAD!] ¡Has salido de la oficina!");
        juegoActivo = false;
        return "salida";
    }
}
