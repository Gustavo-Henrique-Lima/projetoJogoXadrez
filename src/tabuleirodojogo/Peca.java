package tabuleirodojogo;

public abstract class Peca {
	protected Posicao posicao;
	private Tabuleiro tabuleiro;
	public Peca(Tabuleiro tabuleiro) {
		this.tabuleiro = tabuleiro;
		this.posicao=null;
	}
	protected Tabuleiro getTabuleiro() {
		return tabuleiro;
	}
	public abstract boolean[][] movimentosPossiveis();
	
	public boolean movimentosPossiveis(Posicao posicao)
	{
		return movimentosPossiveis()[posicao.getLinha()][posicao.getColuna()];
	}
	public boolean algumMovimentoPossivel()
	{
		boolean[][] aux=movimentosPossiveis();
		for(int i=0;i<aux.length;i++)
		{
			for(int j=0;j<aux.length;j++)
			{
				if(aux[i][j])
				{
					return true;
				}
			}
		}
		return false;
	}
}
