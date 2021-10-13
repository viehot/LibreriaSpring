package com.Libreria.Servicios;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Libreria.Repositorios.RepLibro;
import com.Libreria.entidades.Autor;
import com.Libreria.entidades.Editorial;
import com.Libreria.entidades.Libro;

@Service
public class ServicioLibro {

	@Autowired
	private RepLibro repLibro;

	@Autowired
	private ServicioAutor serviAut;

	@Autowired
	private ServicioEditorial servEdit;

	public void registroLibro(String isbn, String titulo, String anio, String ejemplares, String nombreAut,
			String nombreEdit) throws Exception {

		validar(isbn, titulo, anio, ejemplares, nombreAut, nombreEdit);

		Libro lib = new Libro();
		Autor aut = serviAut.findName(nombreAut);
		Editorial edit = servEdit.findName(nombreEdit);

		lib.setId(randomId());
		lib.setIsbn(Long.valueOf(isbn));
		lib.setTitulo(titulo);
		lib.setAnio(Integer.valueOf(anio));
		lib.setEjemplares(Integer.valueOf(ejemplares));
		lib.setEjemplPrestados(0);
		lib.setEjemplRestantes(lib.getEjemplares());
		lib.setAlta(true);
		lib.setAutor(aut);
		lib.setEditorial(edit);

		repLibro.save(lib);

	}

	public void eliminarLibro(int id) {
		repLibro.deleteById(id);
	}

	public void editarLibro(int id, String isbn, String titulo, String anio, String ejemplares, String nombreAut,
			String nombreEdit) throws Exception {

		validar(isbn, titulo, anio, ejemplares, nombreAut, nombreEdit);

		Libro lib = repLibro.getById(id);
		Autor aut = serviAut.findName(nombreAut);
		Editorial edit = servEdit.findName(nombreEdit);

		lib.setIsbn(Long.valueOf(isbn));
		lib.setTitulo(titulo);
		lib.setAnio(Integer.valueOf(anio));
		lib.setEjemplares(Integer.valueOf(ejemplares));
		lib.setEjemplRestantes(lib.getEjemplares() - lib.getEjemplPrestados());
		lib.setAutor(aut);
		lib.setEditorial(edit);

		repLibro.save(lib);
	}

	public List<Libro> listarLibros() {
		return repLibro.findAll();
	}

	public Libro listLibro(int id) {
		return repLibro.getById(id);
	}

	public void validar(String isbn, String titulo, String anio, String ejemplares, String autor, String editorial)
			throws Exception {
		LocalDate ahora = LocalDate.now();
		if (String.valueOf(isbn).equals("") || isbn == null || Long.valueOf(isbn) < 1000000 || isbn.isBlank()) {
			throw new Exception("Error. Debe ingresar 7 digitos numericos.");
		}
		if (titulo == null || titulo.isBlank()) {
			throw new Exception("Error. Ingrese un nombre");
		}
		if (ejemplares.equals("") || Integer.valueOf(ejemplares) < 0) {
			throw new Exception("Error. No puede ser 0 ejemplares");
		}
		if (anio.equals("") || Integer.valueOf(anio) > ahora.getYear()) {
			throw new Exception("Error. El añio no puede ser mayor al actual");
		}
		if (autor == null || autor.isBlank()) {
			throw new Exception("Error. Debe elegir un autor");
		}
		if (editorial == null || editorial.isBlank()) {
			throw new Exception("Error. Debe elegir una Editorial");
		}
	}

	public int randomId() {
		String uuid = UUID.randomUUID().toString(); // Un random
		int id = uuid.hashCode();// paso el código de ese uuid, para que quede un valor más corto
		id = id < 0 ? -id : id; // El hashCode puede ser (-) -- Esto es un if ternario: Si id<0, lo cambia al
								// opuesto. Si no (:) lo mantiene (+)
		return id;
	}

}
