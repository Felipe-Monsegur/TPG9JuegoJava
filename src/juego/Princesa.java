package juego;


import java.awt.Color;
import java.util.ArrayList;

import entorno.Entorno;


public class Princesa {
	private double x;
	private double y;
	private double ancho;
	private double alto;
	private Color color;
	private int direccion;
	
	//variables de instancia para saltar
	private double velocidadY;
	private double gravedad;
	private boolean enelaire;
	
//	// Agregar lista de ladrillos para colisiones
//    private ArrayList<Ladrillo> piso;
	
	public Princesa(double x, double y) {
		this.x=x;
		this.y=y;
		this.ancho=40;
		this.alto=50;
		this.color=Color.PINK;
		//this.direcion=;
		this.velocidadY=0;
		this.gravedad=0.5;
		this.enelaire=false;

	}
	
	public void dibujar(Entorno e) {
		e.dibujarRectangulo(x, y, ancho, alto, 0, color);
		
	}
	public void moverDerecha(Entorno e) {
		if(this.x+this.ancho/2<e.ancho())
			this.x+=5;
	}
	
	public void moverIzquierda() {
		if(this.x-this.ancho/2-3>=0) {
			this.x-=5;
		}
	}
	
	public  Balas disparar() {
		return new Balas(this.x, this.y);
	}
	
	
	 public void mover(Ladrillo[][] pisos) {
	        boolean ensuelo = false; // verifica si la princesa está en el suelo

	        // verifica colisiones con bloques en todos los pisos
	        for (Ladrillo[] piso : pisos) {
	            for (Ladrillo ladrillo : piso) {
	                if (colision(ladrillo)) {
	                	//rebote para abajo
	                	if (this.y - this.alto/2 > ladrillo.getY() + ladrillo.getAlto()/2) {
	                		 y = ladrillo.getY() + ladrillo.getAlto() / 2 + this.alto / 2;
	                		 velocidadY = -velocidadY; // Cambiar la dirección del movimiento
	                        ensuelo = false; 
	                	}
	                	else if (velocidadY>0) { // si está cayendo
	                        y = ladrillo.getY()-25 - this.alto/2 ;
	                        enelaire = false;
	                        velocidadY = 0;
	                        ensuelo = true; // La princesa está en el suelo
	                  
	                    }
	                }
	            }
	        }
	        
        // si la princesa no está en el suelo, cae
        if (!ensuelo) {
            velocidadY += gravedad;
            y += velocidadY;
            enelaire = true;
        }
    }

	
    // hace que el personaje salte
    public void saltar() {
        if (!enelaire) {
            enelaire = true;
            velocidadY = -10;
        }
    }
    
    
    private boolean colision(Ladrillo ladrillo) {
        return this.x + this.ancho / 2 > ladrillo.getX() - ladrillo.getAncho() / 2 &&
                this.x - this.ancho / 2 < ladrillo.getX() + ladrillo.getAncho() / 2 &&
                this.y + this.alto / 2 > ladrillo.getY() - ladrillo.getAlto() / 2 &&
                this.y - this.alto / 2 < ladrillo.getY() + ladrillo.getAlto() / 2;
    }
}
