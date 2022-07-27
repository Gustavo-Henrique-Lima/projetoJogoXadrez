package xadrez.pecas;

import tabuleirodojogo.Posicao;
import tabuleirodojogo.Tabuleiro;
import xadrez.CorPecas;
import xadrez.PecaXadrez;

public class Torre extends PecaXadrez{

	public Torre(Tabuleiro tabuleiro, CorPecas corDaPeca) 
	{
		super(tabuleiro, corDaPeca);
	}
	@Override
	public boolean[][] movimentosPossiveis() 
	{
		boolean[][] aux=new boolean[getTabuleiro().getColunas()][getTabuleiro().getColunas()];
		Posicao pecaAux=new Posicao(0, 0);
		//Movimentar pra frente
		pecaAux.setValue(posicao.getLinha() - 1, posicao.getColuna());
		while (getTabuleiro().posicaoExistente(pecaAux) && !getTabuleiro().temPeca(pecaAux)) 
		{
			aux[pecaAux.getLinha()][pecaAux.getColuna()] = true;
			pecaAux.setLinha(pecaAux.getLinha() - 1);
		}
		if (getTabuleiro().posicaoExistente(pecaAux) && temPecaInimiga(pecaAux)) {
			aux[pecaAux.getLinha()][pecaAux.getColuna()] = true;
		}
		//Movimentar pra esquerda
		pecaAux.setValue(posicao.getLinha(), posicao.getColuna()-1);
		while (getTabuleiro().posicaoExistente(pecaAux) && !getTabuleiro().temPeca(pecaAux)) 
		{
			aux[pecaAux.getLinha()][pecaAux.getColuna()] = true;
			pecaAux.setColuna(pecaAux.getColuna()-1);
		}
		if (getTabuleiro().posicaoExistente(pecaAux) && temPecaInimiga(pecaAux)) 
		{
			aux[pecaAux.getLinha()][pecaAux.getColuna()] = true;
		}
		//Movimentar pra direita
		pecaAux.setValue(posicao.getLinha(), posicao.getColuna()+1);
		while (getTabuleiro().posicaoExistente(pecaAux) && !getTabuleiro().temPeca(pecaAux)) 
		{
			aux[pecaAux.getLinha()][pecaAux.getColuna()] = true;
			pecaAux.setColuna(pecaAux.getColuna()+1);
		}
		if (getTabuleiro().posicaoExistente(pecaAux) && temPecaInimiga(pecaAux)) 
		{
			aux[pecaAux.getLinha()][pecaAux.getColuna()] = true;
		}
		//Movimentar pra trás
		pecaAux.setValue(posicao.getLinha()+1, posicao.getColuna());
		while (getTabuleiro().posicaoExistente(pecaAux) && !getTabuleiro().temPeca(pecaAux)) 
		{
			aux[pecaAux.getLinha()][pecaAux.getColuna()] = true;
			pecaAux.setLinha(pecaAux.getLinha()+1);
		}
		if (getTabuleiro().posicaoExistente(pecaAux) && temPecaInimiga(pecaAux)) {
			aux[pecaAux.getLinha()][pecaAux.getColuna()] = true;
		}
		return aux;
	}
	@Override
	public String toString()
	{
		return "T";
	}
}
