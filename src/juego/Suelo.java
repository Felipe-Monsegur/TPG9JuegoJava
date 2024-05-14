package juego;

import java.awt.Color;
import java.awt.Image;
import entorno.Entorno;
import entorno.Herramientas;

public class Suelo {
	private Ladrillo[] suelo;
    
    
	public Suelo(double x, double y,Entorno e) {
		this.suelo = new Ladrillo[e.ancho()/50];
	      for (int i = 0; i < e.ancho()/50; i++) {
	          this.suelo[i] = new Ladrillo((i * (50)+25),y, y,y, e); // Crear cada ladrillo en su posición correspondiente
	      }
	}
	
	
	public void dibujar(Entorno entorno) {
	      for (Ladrillo ladrillo : suelo) {
	          ladrillo.dibujar(entorno); // Dibujar cada ladrillo del piso
	      }
	}
}
