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
	private boolean cayendo;
	private boolean saltando;
	
	
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
	public void moverDerecha(Entorno e) {
		if(this.x+this.ancho/2<e.ancho())
			this.x+=5;
	}
	
	public void moverIzquierda() {
		if(this.x-this.ancho/2-3>=0) {
			this.x-=5;
		}
	}
	
//	funciones para saltar	
	public void saltar() {
        if (!enelaire) {
            this.velocidadY=-12;
            enelaire=true;
            saltando = true;
        }
    }
//	public void actualizar(Entorno e, Piso[] pisos, Suelo suelo) {
//	    if (saltando) {
//	        this.y += this.velocidadY;
//	        this.velocidadY += gravedad;
//
//	        // Verificar colisiones con los ladrillos en los pisos
//	        for (Piso piso : pisos) {
//	            for (Ladrillo ladrillo : piso.getLadrillos()) {
//	                if (colisionConLadrillo(ladrillo)) {
//	                    // Si la princesa colisiona con un ladrillo del piso, ajustar su posición
//	                    if (this.y - this.alto / 2 < ladrillo.getY() + ladrillo.getAlto() / 2) {
//	                        this.y = ladrillo.getY() + ladrillo.getAlto() / 2 + this.alto / 2;
//	                        this.velocidadY = 0;
//	                    } else {
//	                        saltando = false;
//	                        this.velocidadY = 0;
//	                    }
//	                }
//	            }
//	        }

//	        // Verificar colisiones con los ladrillos en el suelo
//	        for (Ladrillo ladrillo : suelo.getSuelo()) {
//	            if (colisionConLadrillo(ladrillo)) {
//	                // Si la princesa colisiona con un ladrillo del suelo, ajustar su posición
//	                if (this.y - this.alto / 2 < ladrillo.getY() + ladrillo.getAlto() / 2) {
//	                    this.y = ladrillo.getY() + ladrillo.getAlto() / 2 + this.alto / 2;
//	                    this.velocidadY = 0;
//	                } else {
//	                    saltando = false;
//	                    this.velocidadY = 0;
//	                }
//	            }
//	        }

//	        // Verificar si la princesa ha caído al suelo
//	        if (saltando && this.y + this.alto / 2 - 45 > e.alto()) {
//	            this.y = e.alto() - this.alto / 2 - 45;
//	            this.velocidadY = 0;
//	            enelaire = false;
//	            saltando= false;
//	        }
//	    }
//	}
   

    
    private boolean colisionConLadrillo(Ladrillo ladrillo) {
        return this.x + this.ancho / 2 > ladrillo.getX() - ladrillo.getAncho() / 2 &&
                this.x - this.ancho / 2 < ladrillo.getX() + ladrillo.getAncho() / 2 &&
                this.y + this.alto / 2 > ladrillo.getY() - ladrillo.getAlto() / 2 &&
                this.y - this.alto / 2 < ladrillo.getY() + ladrillo.getAlto() / 2;
    }
}
