package aplicacao;

import java.util.Scanner;

import xadrez.PartidaXadrez;
import xadrez.PecaXadrez;
import xadrez.PosicaoXadrez;

public class Programa {
	public static void main(String[] args) {
		Scanner entrada=new Scanner(System.in);
		PartidaXadrez partida= new PartidaXadrez();
		while(true)
		{
			UI.printBoard(partida.getPecas());
			System.out.println();
			System.out.print("Posição de origem: ");
			PosicaoXadrez origem=UI.posicaoPeca(entrada);
			System.out.println();
			System.out.print("Posição de destino: ");
			PosicaoXadrez destino=UI.posicaoPeca(entrada);
			PecaXadrez pecaCapturada=partida.movimentoDasPecas(origem, destino);
		}
		
	}
}
