package xadrez;

import tabuleirodojogo.Posicao;
import tabuleirodojogo.Tabuleiro;
import xadrez.pecas.Rei;
import xadrez.pecas.Torre;

public class PartidaXadrez {
	private Tabuleiro tabuleiro;
	
	public PartidaXadrez()
	{
		this.tabuleiro=new Tabuleiro(8, 8);
		setupInicial();
	}
	public PecaXadrez[][] getPecas()
	{
		PecaXadrez[][] pecasTabuleiro=new PecaXadrez[tabuleiro.getLinhas()][tabuleiro.getColunas()];
		for(int i=0;i<tabuleiro.getLinhas();i++)
		{
			for(int j=0;j<tabuleiro.getColunas();j++)
			{
				pecasTabuleiro[i][j]=(PecaXadrez) tabuleiro.posicaoPeca(i, j);
			}
		}
		return pecasTabuleiro;
	}
	private void setupInicial()
	{
		tabuleiro.localPeca(new Torre(tabuleiro,CorPecas.White), new Posicao(2, 1));
		tabuleiro.localPeca(new Rei(tabuleiro,CorPecas.Black), new Posicao(0,4));
		tabuleiro.localPeca(new Rei(tabuleiro,CorPecas.White), new Posicao(7,4));
	}
}
