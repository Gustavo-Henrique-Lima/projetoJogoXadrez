package tabuleirodojogo;

public class Tabuleiro {
	private Integer linhas;
	private Integer colunas;
	private Peca[][] pecas; //Um tabuleiro contem várias peças, que por sua vez estão em um único tabuleiro
	public Tabuleiro(Integer linhas, Integer colunas) {
		if(linhas<1 || colunas<1)
		{
			throw new ExcecaoTabuleiro("Erro ao criar o tabuleiro, as medidas passadas são inválidas. Tente novamente");
		}
		this.linhas = linhas;
		this.colunas = colunas;
		this.pecas=new Peca [linhas][colunas];
	}
	public Integer getLinhas() {
		return linhas;
	}
	public Integer getColunas() {
		return colunas;
	}
	//Método para retonar a linha e coluna da peça
	public Peca posicaoPeca(Integer linha,Integer coluna)
	{
		if(!posicaoExistente(linha,coluna))
		{
			throw new ExcecaoTabuleiro("Posição fora do tabuleiro");
		}
		return pecas[linha][coluna];
	}
	//Método para retonar a posição da peça
	public Peca posicaoPeca(Posicao posicao)
	{
		if(!posicaoExistente(posicao))
		{
			throw new ExcecaoTabuleiro("Posição fora do tabuleiro");
		}
		return pecas[posicao.getLinha()][posicao.getColuna()];
	}
	public void localPeca(Peca peca, Posicao posicao)
	{
		if(temPeca(posicao))
		{
			throw new ExcecaoTabuleiro("Já existe uma peça na posição: "+posicao);
		}
		pecas[posicao.getLinha()][posicao.getColuna()]=peca; //Colocando a peça na posição que foi passada
		peca.posicao=posicao;
	}
	private Boolean posicaoExistente(Integer linha,Integer coluna)
	{
		return linha>=0 && linha<linhas && coluna>=0 && coluna<colunas;
	}
	public Boolean posicaoExistente(Posicao posicao)
	{
		return posicaoExistente(posicao.getLinha(),posicao.getColuna());
	}
	public Boolean temPeca(Posicao posicao)
	{
		if(!posicaoExistente(posicao))
		{
			throw new ExcecaoTabuleiro("Posição fora do tabuleiro");
		}
		return posicaoPeca(posicao) !=null;
	}
}
