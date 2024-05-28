package juego;

import java.awt.Image;
import java.util.Random;

import entorno.Entorno;
import entorno.Herramientas;

public class Trex {
	private double x;
	private double y;
	private double ancho;
	private double alto;
	private int direccion;
	private Image imag;

	private double velocidadY;
	private double gravedad;
	private Random random;
	private Hueso hueso;

	public Trex(double x, double y) {
		this.x = x;
		this.y = y;
		this.ancho = 30;
		this.alto = 50;
		this.direccion = 1;
		this.velocidadY = 0;
		this.gravedad = 0.5;
		this.random = new Random();
		this.imag = Herramientas.cargarImagen("trex.png");

	}

	public void dibujar(Entorno e) {
		e.dibujarImagen(imag, x, y - 7, 0, 0.1);
		if (direccion == 1) {
			this.imag = Herramientas.cargarImagen("trex.png");
		} else {
			this.imag = Herramientas.cargarImagen("trexIzq.png");
		}
	}

	public void mover(Ladrillo[][] pisos, Entorno e, Trex[][] otrosTrexs) {
	    boolean ensuelo = false; // verifica si el Trex está en el suelo

	    // verifica colisiones con bloques en todos los pisos
	    for (Ladrillo[] piso : pisos) {
	        for (Ladrillo ladrillo : piso) {
	            if (ladrillo != null && colision(ladrillo)) {
	                // rebote para abajo
	                if (velocidadY > 0) { // si está cayendo
	                    y = ladrillo.getY() - 25 - this.alto / 2;
	                    velocidadY = 0;
	                    ensuelo = true; // El Trex está en el suelo
	                }
	            }
	        }
	    }

	    // que no se superpongan...
	    for (Trex[] pisoTrex : otrosTrexs) {
	        for (Trex otroTrex : pisoTrex) {
	        	 if (otroTrex != null && otroTrex != this && colisionConTrex(otroTrex)) {
	                 if (this.y < otroTrex.y) { // Si este Trex está encima del otro
	                     if (velocidadY > 0) { // Si está cayendo
	                         this.y = otroTrex.y - this.alto; // Ajusta la posición para que no se superponga
	                         velocidadY = 0; // Detén la caída
	                         ensuelo = true; // El Trex está en el suelo (sobre otro Trex)
	                     }
	                 } else {
	                     if (this.direccion == otroTrex.direccion) {
	                         // Si ambos Trex se mueven en la misma dirección y colisionan, cambiar dirección de uno de ellos
	                         this.direccion = -this.direccion;
	                     }
	                 }
	            }
	        }
	    }

	    // si no está en el suelo, cae
	    if (!ensuelo) {
	        velocidadY += gravedad;
	        y += velocidadY;
	    }

	    // cuando llega a los bordes cambia de direccion
	    if (this.x + this.ancho / 2 >= e.ancho()) {
	        this.direccion = -1;
	    } else if (this.x - this.ancho / 2 <= 0) {
	        this.direccion = 1;
	    }
	    if (this.direccion == 1) {
	        moverderecha();
	    } else {
	        moverizquierda();
	    }
	}

	public void moverderecha() {
		this.x += 0.9;
		this.direccion = 1;
	}

	public void moverizquierda() {
		this.x -= 0.9;
		this.direccion = -1;
	}

	public Hueso getHueso() {
		return this.hueso;
	}

	public void setHueso(Hueso hueso) {
		this.hueso = hueso;
	}

	public void dispararHueso() {
		if (hueso == null && random.nextInt(50) == 2) { // random para que no disparen SIEMPRE
			hueso = new Hueso(this.x, this.y, this.direccion);
		}
	}

	private boolean colision(Ladrillo ladrillo) {
		return this.x + this.ancho / 2 > ladrillo.getX() - ladrillo.getAncho() / 2
				&& this.x - this.ancho / 2 < ladrillo.getX() + ladrillo.getAncho() / 2
				&& this.y + this.alto / 2 > ladrillo.getY() - ladrillo.getAlto() / 2
				&& this.y - this.alto / 2 < ladrillo.getY() + ladrillo.getAlto() / 2;
	}

	public boolean colisionConBala(Bala balas) {
		return balas != null && balas.getX() + balas.getDiametro() / 2 > this.x - this.ancho / 2
				&& balas.getX() - balas.getDiametro() / 2 < this.x + this.ancho / 2
				&& balas.getY() + balas.getDiametro() / 2 > this.y - this.alto / 2
				&& balas.getY() - balas.getDiametro() / 2 < this.y + this.alto / 2;
	}
	
	private boolean colisionConTrex(Trex otroTrex) {
	    return this.x < otroTrex.x + otroTrex.ancho &&
	           this.x + this.ancho > otroTrex.x &&
	           this.y < otroTrex.y + otroTrex.alto &&
	           this.y + this.alto > otroTrex.y;
	}
	
	public boolean colisionConPrincesa(Princesa princesa) {
	    return this.x + this.ancho / 2 > princesa.getX() - princesa.getAncho() / 2 &&
	           this.x - this.ancho / 2 < princesa.getX() + princesa.getAncho() / 2 &&
	           this.y + this.alto / 2 > princesa.getY() - princesa.getAlto() / 2 &&
	           this.y - this.alto / 2 < princesa.getY() + princesa.getAlto() / 2;
	}
	
	
	public double getX() {
		return x;
	}
	public double getY() {
		return y;
	}
	public double getAncho() {
		return ancho;
	}
	public double getAlto() {
		return alto;
	}

}
