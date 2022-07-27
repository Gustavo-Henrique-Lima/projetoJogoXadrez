package xadrez;

import tabuleirodojogo.Peca;
import tabuleirodojogo.Posicao;
import tabuleirodojogo.Tabuleiro;

public abstract class PecaXadrez extends Peca{
	private CorPecas corDaPeca;
	private Integer contadorMovimentos=0;
	
	public PecaXadrez(Tabuleiro tabuleiro, CorPecas corDaPeca) {
		super(tabuleiro);
		this.corDaPeca = corDaPeca;
	}
	public CorPecas getCorDaPeca() {
		return corDaPeca;
	}
	public Integer getContadorMovimentos()
	{
		return contadorMovimentos;
	}
	public void aumentarMovimento()
	{
		contadorMovimentos++;
	}
	public void diminuirMovimento()
	{
		contadorMovimentos--;
	}
	public PosicaoXadrez getPosicaoPeca()
	{
		return PosicaoXadrez.fromPosicao(posicao);
	}
	protected boolean temPecaInimiga(Posicao posicao)
	{
		PecaXadrez aux=(PecaXadrez)getTabuleiro().posicaoPeca(posicao);
		return aux!=null && aux.getCorDaPeca()!=corDaPeca;
	}
}
