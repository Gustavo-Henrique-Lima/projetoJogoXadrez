package xadrez;

import tabuleirodojogo.Peca;
import tabuleirodojogo.Tabuleiro;

public class PecaXadrez extends Peca{
	private CorPecas corDaPeca;

	public PecaXadrez(Tabuleiro tabuleiro, CorPecas corDaPeca) {
		super(tabuleiro);
		this.corDaPeca = corDaPeca;
	}

	public CorPecas getCorDaPeca() {
		return corDaPeca;
	}
}
