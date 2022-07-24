package aplicacao;

import java.util.InputMismatchException;
import java.util.Scanner;

import tabuleirodojogo.ExcecaoTabuleiro;
import xadrez.ExcecaoXadrez;
import xadrez.PartidaXadrez;
import xadrez.PecaXadrez;
import xadrez.PosicaoXadrez;

public class Programa {
	public static void main(String[] args) {
		Scanner entrada=new Scanner(System.in);
		PartidaXadrez partida= new PartidaXadrez();
		while(true)
		{
			try
			{
				UI.limparTela();
				UI.printBoard(partida.getPecas());
				System.out.println();
				System.out.print("Posição de origem: ");
				PosicaoXadrez origem=UI.posicaoPeca(entrada);
				System.out.println();
				System.out.print("Posição de destino: ");
				PosicaoXadrez destino=UI.posicaoPeca(entrada);
				PecaXadrez pecaCapturada=partida.movimentoDasPecas(origem, destino);
			}
			catch (ExcecaoXadrez e) 
			{
				System.out.println(e.getMessage());
				entrada.nextLine();
			}
			catch(InputMismatchException e)
			{
				System.out.println(e.getMessage());
				entrada.nextLine();
			}
		}
		
	}
}
