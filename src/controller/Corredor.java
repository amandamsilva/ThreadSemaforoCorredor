package controller;

import java.util.Random;
import java.util.concurrent.Semaphore;

public class Corredor extends Thread {

	private int idThread;
	private Semaphore semaforo;
	private int distanciaCorredor = 200;
	private int pessoaAndou = 0;
	Random rdm = new Random();
	private int tempo = 0;
	private  static int cont = 0;
	
	public Corredor(int idThread, Semaphore semaforo) {
		this.idThread = idThread;
		this.semaforo = semaforo;
	}
	
	@Override
	public void run() {
		caminhar();
	}

	private void caminhar() {
		int distanciaPercorrida = 0;
		System.out.println("Pessoa #" + idThread + " começou a caminhar");
		while (distanciaPercorrida < distanciaCorredor) {
			
			pessoaAndou = rdm.nextInt(3) + 4;
			distanciaPercorrida += pessoaAndou;
			try {
				sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			if (distanciaPercorrida >= 200) {
				System.out.println("Pessoa #" + idThread + " chegou na porta");
			} else {
				System.out.println("A pessoa #" + idThread + " caminhou " + distanciaPercorrida + "m.");
			}
		}
		
		try {
			semaforo.acquire();
			porta();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			semaforo.release();
		}
		
	}

	private void porta() {
		cont++;
		System.out.println(
				"Pessoa #" + idThread + " foi a " + cont + "ª a abrir e cruzar a porta.");
		tempo = rdm.nextInt(1001) + 1000;
		try {
			sleep(tempo);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
	}
	
	
}
