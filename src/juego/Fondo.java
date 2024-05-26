package juego;

import java.awt.Color;
import java.awt.Image;
import entorno.Entorno;
import entorno.Herramientas;

public class Fondo {
		private double x;
		private double y;
		private double ancho;
		private double alto;
		private Color color;
		private Image imag;
		
		public Fondo(double x, double y) {
			this.x = x;
			this.y = y;
			this.ancho = 500;
			this.alto = 500;
			this.color = Color.red;
			
			this.imag = Herramientas.cargarImagen("magma.png");
		}
		
		public void dibujar(Entorno e) {
			e.dibujarImagen(imag, e.ancho()/2, e.alto()/2, 0, 1);
			
		}
	}
