package juego;

import java.awt.Color;
import java.util.Random;
import entorno.Entorno;

public class Piso {
//    private Rectang rectangIzquierdo;
//    private Cajas cajas;
//    private Rectang rectangDerecho;
//
//    public Piso(double x, double y, double ancho, double alto, Entorno e) {
//        double anchoCajas = ancho / 6; // Ancho de la caja marrón, 1/6 del ancho total
//        Random rand = new Random();
//        int randomX = rand.nextInt(800-(int)(anchoCajas/2)) + (int)(anchoCajas/2); // Posición x aleatoria dentro del ancho del piso
//        int r = randomX;
//        this.cajas = new Cajas(r, y, anchoCajas, alto, e);
//        this.rectangDerecho = new Rectang(x + (r+(anchoCajas/2)), y, ancho, alto, Color.ORANGE, e);
//        this.rectangIzquierdo = new Rectang((r-(anchoCajas/2)) - x, y, ancho, alto, Color.ORANGE, e);
//    }
//    public Rectang getRectangIzquierdo() {
//		return rectangIzquierdo;
//	}
//	public Cajas getCajas() {
//		return cajas;
//	}
//	public Rectang getRectangDerecho() {
//		return rectangDerecho;
//	}
//
//	public void dibujar(Entorno entorno) {
//        rectangDerecho.dibujar(entorno);
//        cajas.dibujar(entorno);
//        rectangIzquierdo.dibujar(entorno);
//    }
//	
	
	
	
	
//	CON BLOQUES EN VEZ DE RECTANGULOS
  private Ladrillo[] ladrillos;
  private Metal metales;
  private double x;
  private double y;
  private double ancho;
  private double alto;
  

  public Piso(double x, double y, double ancho, double alto, Entorno e) {
      double anchoMetal = 150;//las cajas tiene ancho 150 pero lo vuelvo a aclarar aca para que se entienda los calulos
      Random rand = new Random();
      int randomX = rand.nextInt(700-(int)(anchoMetal/2)) + (int)(anchoMetal/2); // Posición x aleatoria dentro del ancho del piso
      int r = randomX;
      this.metales = new Metal(r, y, anchoMetal, alto, e);
      this.ladrillos = new Ladrillo[e.ancho()/50];
      for (int i = 0; i < e.ancho()/50; i++) {
          this.ladrillos[i] = new Ladrillo((i * (50)+25), y, ancho,alto,e); // Crear cada ladrillo en su posición correspondiente
      }
  }

	public Ladrillo[] getLadrillos() {
		return ladrillos;
	}
	public Metal getMetales() {
		return metales;
	}
	 public double getAlto() {
	        return alto;
	    }

	    public double getY() {
	        return y;
	    }
	
	
	
	
public void dibujar(Entorno entorno) {
      for (Ladrillo ladrillo : ladrillos) {
          ladrillo.dibujar(entorno); // Dibujar cada ladrillo del piso
      }
      metales.dibujar(entorno);
  }
}

