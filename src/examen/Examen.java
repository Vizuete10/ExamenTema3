package examen;

import java.util.Scanner;

public class Examen {

	// Declaramos las constantes para el número de filas y columnas de la sala, los
	// estados de los asientos y la opción de salir del programa
	private static final int FILAS = 6;
	private static final int COLUMNAS = 10;

	private static final char LIBRE = 'L';
	private static final char RESERVADO = 'R';
	private static final char OCUPADO = 'O';

	private static final int OPCION_SALIR = 7;

	public static void main(String[] args) {

		// Creamos el scanner para leer la entrada del usuario
		Scanner sc = new Scanner(System.in);

		// Creamos el array principal con el estado de los asientos de la sala, lo
		// inicializamos con el valor "L" o "LIBRE"
		char[][] sala = new char[FILAS][COLUMNAS];

		// Recorremos el array con un for para asignar el valor "L" o "LIBRE" a cada
		// asiento de la sala
		for (int i = 0; i < FILAS; i++) {
			for (int j = 0; j < COLUMNAS; j++) {
				sala[i][j] = LIBRE;
			}
		}
		// Creamos una variable para guardar la opción que el usuario introduce
		int opcion;

		// Creamos un bucle do-while para mostrar el menu y ejecutar la opción que el
		// usuario introduce, el bucle se ejecuta hasta que el usuario introduce la
		// opción de salir
		do {

			mostrarMenu();
			System.out.println("Elige una opción: ");
			opcion = sc.nextInt();

			ejecutarOpcion(opcion, sala, sc);

			System.out.println();

		} while (opcion != OPCION_SALIR);

		// Cerramos el scanner
		sc.close();

	}

	/**
	 * Función para pintar la sala con el valor inicial el cual es "L" o "LIBRE"
	 * 
	 * @param sala, array con el estado de los asientos de la sala
	 */
	public static void pintarSala(char[][] sala) {

		System.out.print("   ");

		// Recorremos el array con un for para mostrar el número de columna en la parte
		// superior de la tabla
		for (int j = 0; j < sala[0].length; j++) {
			System.out.print(j + " ");
		}

		System.out.println();

		// Recorremos el array con un for para mostrar el número de fila en la parte
		// izquierda de la tabla y el estado de cada asiento de la sala
		for (int i = 0; i < sala.length; i++) {

			System.out.print(i + "  ");

			for (int j = 0; j < sala[i].length; j++) {
				System.out.print(sala[i][j] + " ");
			}

			System.out.println();
		}
	}

	/**
	 * Función para mostrar el menu por pantalla con las diferentes opciones que hay
	 */
	public static void mostrarMenu() {

		// Mostramos el menu por pantalla con las diferentes opciones que hay
		System.out.println("TEATRO");
		System.out.println("1. Mostrar sala");
		System.out.println("2. Reservar asiento suelto");
		System.out.println("3. Reservar grupo en una fila");
		System.out.println("4. Confirmar reservas");
		System.out.println("5. Cancelar reservas");
		System.out.println("6. Estadísticas");
		System.out.println("7. Salir");
	}

	/**
	 * 
	 * @param opcion, El usuario introduce el número de la opción que quiere
	 *                ejecutar
	 * @param sala,   array con el estado de los asientos de la sala
	 * @param sc,     el scanner para leer la entrada del usuario
	 */
	public static void ejecutarOpcion(int opcion, char[][] sala, Scanner sc) {

		// Con un switch ejecutamos la opción que el usuario introduce, cada case
		// ejecuta una función diferente dependiendo de la opción que el usuario
		// introduce
		switch (opcion) {

		case 1:
			pintarSala(sala);
			break;

		case 2:
			reservarAsiento(sc, sala);
			break;

		case 3:
			reservaGrupo(sc, sala);
			break;

		case 4:
			confirmarReservas(sala);
			System.out.println("Reservas confirmadas.");
			break;

		case 5:
			cancelarReservas(sala);
			System.out.println("Reservas canceladas.");
			break;

		case 6:
			mostrarEstadisticas(sala);
			break;

		case 7:
			System.out.println("Saliendo del programa");
			break;

		default:
			System.out.println("Opción no válida.");

		}
	}

	/*
	 * Función para comprobar si la entrada del usuario es correcta
	 * 
	 * @param sala, array con el estado de los asientos de la sala
	 * 
	 * @param fila, número de fila que introduce el usuario
	 * 
	 * @param columna, número de columna que introduce el usuario
	 * 
	 * @return true/false, dependiendo si se cumple la condicion para comprobar si
	 * los datos introducidos son correctos
	 * 
	 */
	public static boolean esPosicionValida(char[][] sala, int fila, int columna) {

		return fila >= 0 && fila < sala.length && columna >= 0 && columna < sala[0].length;
	}

	/**
	 * Función para reservar un asiento en la sala, se le pide al usuario el número
	 * de fila y el número de columna y se comprueba si son valores correctos con la
	 * función : esPosicionValida
	 * 
	 * @param sc,   el scanner para leer la entrada del usuario
	 * @param sala, array pirncipal con el estado de los asientos de la sala
	 */
	public static void reservarAsiento(Scanner sc, char[][] sala) {

		// Pedimos al usuario el número de fila y el número de columna del asiento que
		// quiere reservar, mostramos el estado de la sala para que el usuario pueda
		// elegir un asiento libre
		System.out.println("¿Qué asiento quiere reservar?");
		System.out.println("La sala se encuentra en este estado: ");
		pintarSala(sala);
		System.out.println();
		System.out.println("Introduzca el número de fila");
		int fila = sc.nextInt();

		System.out.println("Introduzca el número de columna");
		int columna = sc.nextInt();

		// Comprobamos si los datos introducidos por el usuario son correctos con la
		// función esPosicionValida, si son correctos se comprueba si el asiento que
		// quiere reservar el usuario esta libre, si esta libre se reserva el asiento y
		// se muestra un mensaje de confirmación, si no esta libre se muestra un mensaje
		// de error
		if (esPosicionValida(sala, fila, columna)) {

			// Comprobamos si el asiento que quiere reservar el usuario esta libre, si esta
			// libre se reserva el asiento y se muestra un mensaje de confirmación, si no
			// esta libre se muestra un mensaje de error
			if (sala[fila][columna] == LIBRE) {
				sala[fila][columna] = RESERVADO;
				System.out.println(
						"Reserva realizafa con exito, su asiento esta en fila " + fila + " y la columna " + columna);
			} else {
				System.out.println(
						"Lo sentimos, el asiento que quiere reservar ya esta reservado u ocupado por otra persona, fijese en la tabla y elija un asiento que este libre");
			}

		} else {
			System.out.println("No se pudo realizar la reserva con exito");
			System.out.println(
					"El número de filas y columna tienes que estar dentro del espacio que disponemos ahora mismo, gracias.");
		}

	}

	/**
	 * Función para contar el número de asientos que hay con el estado que se ha
	 * introducido, se recorre el array con un for y se comprueba si el estado del
	 * asiento es igual al estado que se ha introducido, si es igual se incrementa
	 * el contador
	 * 
	 * @param sala,   array pirncipal con el estado de los asientos de la sala
	 * @param estado, el estado al que se quiere ver cuantos hay ( "L", "R", "O")
	 * @return contador, el número de asientos que hay con el estado que se ha
	 *         introducido
	 */
	public static int contarEstado(char[][] sala, char estado) {

		// Creamos una variable contador para contar el número de asientos que hay con
		// el estado que se ha introducido
		int contador = 0;

		// Recorremos el array con un for para contar el número de asientos que hay con
		// el estado que se ha introducido, si el estado del asiento es igual al estado
		// que se ha introducido se incrementa el contador
		for (int i = 0; i < sala.length; i++) {
			for (int j = 0; j < sala[i].length; j++) {

				if (sala[i][j] == estado) {
					contador++;
				}
			}
		}

		return contador;
	}

	/**
	 * Función para hacer una reserva en grupo, primero se pide la fila que quiere,
	 * luego el número de personas que son. Se ejecuta la función de
	 * reservarGrupoEnFila y se ejecuta para ver si se han podido reservar, si es
	 * asi se muestra de que asientos han sigo asignados, si no se le dice que no
	 * hay huecos en esa fila.
	 * 
	 * @param sc,   el scanner para leer la entrada del usuario
	 * 
	 * @param sala, array pirncipal con el estado de los asientos de la sala
	 */
	public static void reservaGrupo(Scanner sc, char[][] sala) {

		System.out.println("La sala se encuentra en este estado: ");
		pintarSala(sala);
		
		// Pedimos al usuario la fila que quiere reservar y el número de personas que son
		System.out.println("¿Qué fila quiere reservar para su grupo?");
		int fila = sc.nextInt();
		
		// Comprobamos si el número de fila introducido por el usuario es correcto, si no es correcto se muestra un mensaje de error y se sale de la función
		if (fila < 0 || fila >= sala.length) {
			System.out.println("Número de fila no válido.");
			return;
		}

		System.out.println("Número de personas: ");
		int personas = sc.nextInt();

		// Se ejecuta la función de reservarGrupoEnFila y se ejecuta para ver si se han podido reservar
		int[] resultado = reservarGrupoEnFila(sala, fila, personas);

		// Si se han podido reservar se muestra de que asientos han sigo asignados, si no se le dice que no hay huecos en esa fila
		if (resultado == null) {
			System.out.println("No hay hueco suficiente en esa fila.");
		}
			
		else {
			System.out.println("Reservado desde columna " + resultado[0] + " hasta " + resultado[1]);
		}
			
	}

	/**
	 * Función para seguir con la reserva en grupo, primero se comprueba si el
	 * número de fila introducido por el usuario es correcto, luego con un for se
	 * comprueba si en la fila introducida caben el número de personas que el
	 * usuario quiere. Si caben otro for recorre el array asignando asientos al
	 * grupo y se devuelve un array con el inicio y el fin del bloque de asientos
	 * asignados, si no caben se devuelve null
	 * 
	 * @param sala, array pirncipal con el estado de los asientos de la sala
	 * @param fila, número de fila que introduce el usuario
	 * @param numeroPersonas, el número de personas que el usuario quiere reservar
	 *                        asiento para ellas
	 * @return un array con el inicio y el fin del bloque de asientos asignados, si
	 *         no se han podido asignar se devuelve null
	 */
	public static int[] reservarGrupoEnFila(char[][] sala, int fila, int numeroPersonas) {

		// Comprobamos si el número de fila introducido por el usuario es correcto, 
		// si no es correcto se devuelve null
		if (fila < 0 || fila >= sala.length) {
			return null;
		}

		// Con un for se comprueba si en la fila introducida caben el número de personas
		// que el usuario quiere, si caben otro for recorre el array asignando asientos,
		// si no caben se devuelve null
		for (int inicio = 0; inicio <= sala[fila].length - numeroPersonas; inicio++) {

			boolean bloqueLibre = true;
			// Con otro for recorre el array asignando asientos, si encuentra un asiento que
			// no esta libre se cambia el valor de bloqueLibre a false y se sale del bucle
			for (int j = inicio; j < inicio + numeroPersonas; j++) {

				if (sala[fila][j] != LIBRE) {
					bloqueLibre = false;
					break;
				}
			}

			// Si el bloque de asientos esta libre se asignan los asientos al grupo y se
			// devuelve un array con el inicio y el fin del bloque de asientos asignados, si
			// no esta libre se sigue buscando otro bloque de asientos
			if (bloqueLibre) {

				for (int j = inicio; j < inicio + numeroPersonas; j++) {
					sala[fila][j] = RESERVADO;
				}

				return new int[] { inicio, inicio + numeroPersonas - 1 };
			}
		}

		return null;
	}

	/**
	 * 
	 * Función para confirmar las reservas, se recorre el array con un for y se
	 * sustituye "R", RESERVADO, por "O", OCUPADO, para confirmar las reservas realizadas por el usuario, 
	 * si el asiento esta reservado se cambia a ocupado, si el asiento no esta reservado no se hace nada
	 * 
	 * @param sala, array pirncipal con el estado de los asientos de la sala
	 */
	public static void confirmarReservas(char[][] sala) {

		// Con un for recorremos el array y sustituimos "R", RESERVADO, por "O", OCUPADO
		for (int i = 0; i < sala.length; i++) {
			for (int j = 0; j < sala[i].length; j++) {

				if (sala[i][j] == RESERVADO) {
					sala[i][j] = OCUPADO;
				}

			}
		}
	}

	/**
	 * Función para cancelar las reservas, se recorre el array con un for y se sustituye "R", RESERVADO, por "L", LIBRE, para cancelar las reservas
	 * 
	 * @param sala, array pirncipal con el estado de los asientos de la sala
	 */
	public static void cancelarReservas(char[][] sala) {

		for (int i = 0; i < sala.length; i++) {
			for (int j = 0; j < sala[i].length; j++) {

				if (sala[i][j] == RESERVADO) {
					sala[i][j] = LIBRE;
				}

			}
		}
	}

	/**
	 * Función para mostrar las estadísticas de la sala, se cuentan el número de
	 * asientos libres, reservados y ocupados con la función contarEstado, se
	 * calcula el porcentaje de ocupación real, se busca la fila con más asientos
	 * ocupados y se muestran los resultados por pantalla 
	 * 
	 * @param sala, array pirncipal con el estado de los asientos de la sala
	 */
	public static void mostrarEstadisticas(char[][] sala) {

		// Contamos el número de asientos libres, reservados y ocupados con la función contarEstado
		int libres = contarEstado(sala, LIBRE);
		int reservados = contarEstado(sala, RESERVADO);
		int ocupados = contarEstado(sala, OCUPADO);

		// Calculamos el porcentaje de ocupación real, el total de asientos se calcula
		int total = sala.length * sala[0].length;
		
		// El porcentaje se calcula dividiendo el número de asientos ocupados entre el total de asientos y multiplicando por 100 para obtener el porcentaje
		double porcentaje = (ocupados * 100.0) / total;

		// Buscamos la fila con más asientos ocupados
		int filaMasOcupada = -1;
		int maxOcupados = -1;

		// Recorremos el array con un for para contar el número de asientos ocupados en cada fila
		for (int i = 0; i < sala.length; i++) {

			// Creamos una variable para contar el número de asientos ocupados en la fila actual
			int ocupadosFila = 0;

			// Con otro for recorremos la fila actual para contar el número de asientos ocupados, si el asiento esta ocupado se incrementa el contador de ocupados en la fila
			for (int j = 0; j < sala[i].length; j++) {

				if (sala[i][j] == OCUPADO)
					ocupadosFila++;
			}

			// Si el número de asientos ocupados en la fila actual es mayor que el máximo de ocupados encontrado hasta ahora, se actualiza el máximo y se guarda la fila actual como la fila con más ocupados
			if (ocupadosFila > maxOcupados) {
				maxOcupados = ocupadosFila;
				filaMasOcupada = i;
			}
		}

		// Mostramos los resultados por pantalla
		System.out.println("Libres: " + libres);
		System.out.println("Reservados: " + reservados);
		System.out.println("Ocupados: " + ocupados);
		System.out.println("Ocupación real: " +  porcentaje + " % ");
		System.out.println("Fila con más ocupados: " + filaMasOcupada);
	}

}
