package aplicacao;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import xadrez.ExcecaoXadrez;
import xadrez.PartidaXadrez;
import xadrez.PecaXadrez;
import xadrez.PosicaoXadrez;

public class Programa {
	public static void main(String[] args) {
		Scanner entrada=new Scanner(System.in);
		PartidaXadrez partida= new PartidaXadrez();
		List<PecaXadrez> pecasCapturadas=new ArrayList<>();
		while(!partida.getCheckMate())
		{
			try
			{
				UI.limparTela();
				UI.printPartida(partida,pecasCapturadas);
				System.out.println();
				System.out.print("Posição de origem: ");
				PosicaoXadrez origem=UI.posicaoPeca(entrada);
				boolean[][] movimentosPossivesPeca=partida.movimentosPossiveisParaPeca(origem);
				UI.limparTela();
				UI.printBoard(partida.getPecas(),movimentosPossivesPeca);
				System.out.println();
				System.out.print("Posição de destino: ");
				PosicaoXadrez destino=UI.posicaoPeca(entrada);
				PecaXadrez pecaCapturada=partida.movimentoDasPecas(origem, destino);
				if(pecaCapturada!=null)
				{
					pecasCapturadas.add(pecaCapturada);
				}
				if(partida.getPromocao()!=null)
				{
					System.out.println("Insira o tipo de peça que você deseja promover (B/Q/T/C): ");
					String tipo=entrada.nextLine().toUpperCase();
					while (!tipo.equals("B") && !tipo.equals("T") && !tipo.equals("C") && !tipo.equals("Q"))
					{
						System.out.println("Valor inválido!!Insira o tipo de peça que você deseja promover (B/Q/T/C): ");
						tipo=entrada.nextLine().toUpperCase();
					}
					partida.transformar(tipo);
				}
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
		UI.limparTela();
		UI.printPartida(partida, pecasCapturadas);
	}
}
