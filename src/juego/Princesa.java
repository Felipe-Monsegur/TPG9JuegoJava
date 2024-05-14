package juego;


import java.awt.Color;
import entorno.Entorno;


public class Princesa {
	private double x;
	private double y;
	private double ancho;
	private double alto;
	private Color color;	
	
	//variables de instancia para saltar
	private double velocidadY;
	private double gravedad;
	private boolean enelaire;
	
	
	public Princesa(double x, double y) {
		this.x=x;
		this.y=y;
		this.ancho=40;
		this.alto=50;
		this.color=Color.PINK;
		this.velocidadY=0;
		this.gravedad=0.5;
		this.enelaire=false;

	}
	
	public void dibujar(Entorno e) {
		e.dibujarRectangulo(x, y, ancho, alto, 0, color);
		
	}
	public void moverderecha(Entorno e) {
		if(this.x+this.ancho/2<e.ancho())
			this.x+=5;
	}
	
	public void moverizquierda() {
		if(this.x-this.ancho/2-5>=0) {
			this.x-=5;
		}
	}
	
	// Funciones para saltar
    public void saltar() {
        if (!enelaire) {
            this.velocidadY = -12;
            enelaire = true;
        }
    }
    
//    public boolean colisionConElementos(Piso[] pisos) {
//        for (Piso piso : pisos) {
//            if (colisionConLadrillo(piso.getLadrillos()) {
//                return true; // Hay colisión con algún rectángulo del piso
//            }
//        }
//        return false; // No hay colisión con ningún rectángulo de ningún piso
//    }
//
//    public boolean colisionConRectangulo(Rectang rectangulo) {
//        return this.x + this.ancho/2 > rectangulo.getX() - rectangulo.getAncho()/2 &&
//               this.x - this.ancho/2 < rectangulo.getX() + rectangulo.getAncho()/2 &&
//               this.y + this.alto/2 + 5 > rectangulo.getY() - rectangulo.getAlto()/2 &&
//               this.y - this.alto/2 < rectangulo.getY() + rectangulo.getAlto()/2;
//    }
//
//    public void actualizar(Entorno e, Piso[] pisos) {
//        if (enelaire) {
//            this.y += this.velocidadY;
//            this.velocidadY += gravedad;
//            
//            // Verificar colisiones con cada rectángulo de cada piso
//            for (Piso piso : pisos) {
//                if (colisionConRectangulo(piso.getRectangIzquierdo()) || colisionConRectangulo(piso.getRectangDerecho())) {
//                    // Manejar la colisión, por ejemplo, detener la princesa
//                    this.y = Math.min(this.y, piso.getRectangIzquierdo().getY() - piso.getRectangIzquierdo().getAlto()/2 - this.alto/2);
//                    this.velocidadY = 0;
//                    enelaire = false;
//                    break; // Salir del bucle si se detecta una colisión
//                }
//            }
//        }
//    }
//    
    public boolean isEnelaire() {
        return enelaire;
    }

    public void setEnelaire(boolean enelaire) {
        this.enelaire = enelaire;
    }
}
