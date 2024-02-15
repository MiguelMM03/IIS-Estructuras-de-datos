//  NO MODIFICAR NOMBRE DE ESTE PAQUETE
package evalNestor;

public class Alumno {
	// Poned vuestros apellidos, nombre y UO; sustituyendolos en las asignaciones de debajo...
	String nombre="Miguel", // Primera en mayuscula y si es compuesto, sin espacios
			apellido1="Méndez", // Primera en mayuscula y si es compuesto, sin espacios
			apellido2="Murias", // Primera en mayuscula y si es compuesto, sin espacios
			uo="UO287687"; // El UO en mayusculas
	
	public String getNombreFicheroAlumno(){
		return apellido1+apellido2+nombre+"-"+uo;
	}

	public String email() {
		return uo+"@uniovi.es";
	}
	
}
