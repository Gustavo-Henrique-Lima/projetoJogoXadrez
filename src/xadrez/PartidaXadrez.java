package xadrez;

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
	private void setupPecaNova(char coluna,Integer linha,PecaXadrez peca)
	{
		tabuleiro.localPeca(peca, new PosicaoXadrez(coluna, linha).toPosicao());
	}
	private void setupInicial()
	{
		setupPecaNova('b',6,new Torre(tabuleiro,CorPecas.White));
		setupPecaNova('e',8,new Rei(tabuleiro,CorPecas.Black));
		setupPecaNova('e',1,new Rei(tabuleiro,CorPecas.White));
	}
}
