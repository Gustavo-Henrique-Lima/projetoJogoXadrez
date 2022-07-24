package tabuleirodojogo;

public class Tabuleiro {
	private Integer linhas;
	private Integer colunas;
	private Peca[][] pecas; //Um tabuleiro contem várias peças, que por sua vez estão em um único tabuleiro
	public Tabuleiro(Integer linhas, Integer colunas) {
		this.linhas = linhas;
		this.colunas = colunas;
		this.pecas=new Peca [linhas][colunas];
	}
	public Integer getLinhas() {
		return linhas;
	}
	public void setLinhas(Integer linhas) {
		this.linhas = linhas;
	}
	public Integer getColunas() {
		return colunas;
	}
	public void setColunas(Integer colunas) {
		this.colunas = colunas;
	}
	//Método para retonar a linha e coluna da peça
	public Peca posicaoPeca(Integer linha,Integer coluna)
	{
		return pecas[linha][coluna];
	}
	//Método para retonar a posição da peça
	public Peca posicaoPeca(Posicao posicao)
	{
		return pecas[posicao.getLinha()][posicao.getColuna()];
	}
}
