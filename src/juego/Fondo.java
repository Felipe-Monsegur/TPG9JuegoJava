package juego;

import java.awt.Image;
import entorno.Entorno;
import entorno.Herramientas;

public class Fondo {

	private Image imag;

	public Fondo(double x, double y) {
		this.imag = Herramientas.cargarImagen("magma.png");
	}

	public void dibujar(Entorno e) {
		e.dibujarImagen(imag, e.ancho() / 2, e.alto() / 2, 0, 1);

	}
}
