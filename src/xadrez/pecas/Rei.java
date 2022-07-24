package xadrez.pecas;

import tabuleirodojogo.Tabuleiro;
import xadrez.CorPecas;
import xadrez.PecaXadrez;

public class Rei extends PecaXadrez{

	public Rei(Tabuleiro tabuleiro, CorPecas corDaPeca) 
	{
		super(tabuleiro, corDaPeca);
	}
	@Override
	public String toString()
	{
		return "K";
	}
	@Override
	public boolean[][] movimentosPossiveis() {
		boolean[][] aux=new boolean[getTabuleiro().getColunas()][getTabuleiro().getColunas()];
		return aux;
	}
}
