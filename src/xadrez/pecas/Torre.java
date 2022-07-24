package xadrez.pecas;

import tabuleirodojogo.Tabuleiro;
import xadrez.CorPecas;
import xadrez.PecaXadrez;

public class Torre extends PecaXadrez{

	public Torre(Tabuleiro tabuleiro, CorPecas corDaPeca) 
	{
		super(tabuleiro, corDaPeca);
	}
	@Override
	public String toString()
	{
		return "T";
	}
}
