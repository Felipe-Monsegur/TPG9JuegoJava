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
	
//	funciones para saltar	
	public void saltar() {
        if (!enelaire) {
            this.velocidadY=-10;
            enelaire=true;
        }
    }
    public void actualizar(Entorno e) {
        if (enelaire) {
            this.y+=this.velocidadY;
            this.velocidadY+=gravedad;
            
            if (this.y+this.alto/2>e.alto()) {
                this.y=e.alto()-this.alto/2;
                this.velocidadY=0;
                enelaire=false;
            }
        }
    }
    
    //princesa colisiona con suelo ?????
    public boolean colisionconpiso(Suelo suelo) {
    	return this.x + this.ancho/2 > suelo.getX() - suelo.getAncho()/2 &&
				 this.x - this.ancho/2 < suelo.getX() + suelo.getAncho()/2 &&
				 this.y + this.alto/2 > suelo.getY() - suelo.getAlto()/2 &&
				 this.y - this.alto/2 < suelo.getY() + suelo.getAlto()/2; 

    }public boolean colisionConCaja(Cajas caja) {
        return this.x + this.ancho/2 > caja.getX() - caja.getAncho()/2 &&
                this.x - this.ancho/2 < caja.getX() + caja.getAncho()/2 &&
                this.y + this.alto/2 > caja.getY() - caja.getAlto()/2 &&
                this.y - this.alto/2 < caja.getY() + caja.getAlto()/2; 
     }
 
    
}
