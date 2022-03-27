package view;

import java.util.concurrent.Semaphore;

import controller.Corredor;

public class Main {

	public static void main(String[] args) {
		
		Semaphore semaforo = new Semaphore(1);
		
		for (int idThread = 1; idThread < 5; idThread++) {
			Thread tCorredor = new Corredor(idThread, semaforo);
			tCorredor.start();
		}
	}
}
