package xadrez.pecas;

import tabuleirodojogo.Posicao;
import tabuleirodojogo.Tabuleiro;
import xadrez.CorPecas;
import xadrez.PecaXadrez;

public class Bispo extends PecaXadrez{

	public Bispo(Tabuleiro tabuleiro, CorPecas corDaPeca) 
	{
		super(tabuleiro, corDaPeca);
	}
	@Override
	public boolean[][] movimentosPossiveis() {
		boolean[][] aux=new boolean[getTabuleiro().getColunas()][getTabuleiro().getColunas()];
		Posicao pecaAux=new Posicao(0, 0);
		//Movimentar Noroeste
		pecaAux.setValue(posicao.getLinha() - 1, posicao.getColuna()-1);
		while (getTabuleiro().posicaoExistente(pecaAux) && !getTabuleiro().temPeca(pecaAux)) 
		{
			aux[pecaAux.getLinha()][pecaAux.getColuna()] = true;
			pecaAux.setValue(pecaAux.getLinha()-1, pecaAux.getColuna()-1);
		}
		if (getTabuleiro().posicaoExistente(pecaAux) && temPecaInimiga(pecaAux)) {
			aux[pecaAux.getLinha()][pecaAux.getColuna()] = true;
		}
		//Movimentar Nordeste
		pecaAux.setValue(posicao.getLinha()-1, posicao.getColuna()+1);
		while (getTabuleiro().posicaoExistente(pecaAux) && !getTabuleiro().temPeca(pecaAux)) 
		{
			aux[pecaAux.getLinha()][pecaAux.getColuna()] = true;
			pecaAux.setValue(pecaAux.getLinha()-1, pecaAux.getColuna()+1);
		}
		if (getTabuleiro().posicaoExistente(pecaAux) && temPecaInimiga(pecaAux)) 
		{
			aux[pecaAux.getLinha()][pecaAux.getColuna()] = true;
		}
		//Movimentar Sudeste
		pecaAux.setValue(posicao.getLinha()+1, posicao.getColuna()+1);
		while (getTabuleiro().posicaoExistente(pecaAux) && !getTabuleiro().temPeca(pecaAux)) 
		{
			aux[pecaAux.getLinha()][pecaAux.getColuna()] = true;
			pecaAux.setValue(pecaAux.getLinha()+1, pecaAux.getColuna()+1);
		}
		if (getTabuleiro().posicaoExistente(pecaAux) && temPecaInimiga(pecaAux)) 
		{
			aux[pecaAux.getLinha()][pecaAux.getColuna()] = true;
		}
		//Movimentar Sudoeste
		pecaAux.setValue(posicao.getLinha()+1, posicao.getColuna()-1);
		while (getTabuleiro().posicaoExistente(pecaAux) && !getTabuleiro().temPeca(pecaAux)) 
		{
			aux[pecaAux.getLinha()][pecaAux.getColuna()] = true;
			pecaAux.setValue(pecaAux.getLinha()+1, pecaAux.getColuna()-1);
		}
		if (getTabuleiro().posicaoExistente(pecaAux) && temPecaInimiga(pecaAux)) {
			aux[pecaAux.getLinha()][pecaAux.getColuna()] = true;
		}
		return aux;
	}
	@Override
	public String toString()
	{
		return "B";
	}
}
