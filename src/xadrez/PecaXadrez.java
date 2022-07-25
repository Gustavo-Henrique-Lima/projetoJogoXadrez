package xadrez;

import tabuleirodojogo.Peca;
import tabuleirodojogo.Posicao;
import tabuleirodojogo.Tabuleiro;

public abstract class PecaXadrez extends Peca{
	private CorPecas corDaPeca;

	public PecaXadrez(Tabuleiro tabuleiro, CorPecas corDaPeca) {
		super(tabuleiro);
		this.corDaPeca = corDaPeca;
	}
	public CorPecas getCorDaPeca() {
		return corDaPeca;
	}
	protected boolean temPecaInimiga(Posicao posicao)
	{
		PecaXadrez aux=(PecaXadrez)getTabuleiro().posicaoPeca(posicao);
		return aux!=null && aux.getCorDaPeca()!=corDaPeca;
	}
}
