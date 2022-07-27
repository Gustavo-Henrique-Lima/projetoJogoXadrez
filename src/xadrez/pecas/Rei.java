package xadrez.pecas;

import tabuleirodojogo.Posicao;
import tabuleirodojogo.Tabuleiro;
import xadrez.CorPecas;
import xadrez.PecaXadrez;

public class Rei extends PecaXadrez{

	public Rei(Tabuleiro tabuleiro, CorPecas corDaPeca) 
	{
		super(tabuleiro, corDaPeca);
	}
	private boolean reiPodeMover(Posicao posicao)
	{
		PecaXadrez peca=(PecaXadrez)getTabuleiro().posicaoPeca(posicao);
		return peca==null || peca.getCorDaPeca()!=getCorDaPeca();
	}
	@Override
	public String toString()
	{
		return "K";
	}
	@Override
	public boolean[][] movimentosPossiveis() {
		boolean[][] aux=new boolean[getTabuleiro().getColunas()][getTabuleiro().getColunas()];
		Posicao pAux=new Posicao(0, 0);
		//Mover pra cima
		pAux.setValue(posicao.getLinha()-1, posicao.getColuna());
		if(getTabuleiro().posicaoExistente(pAux) && reiPodeMover(pAux))
		{
			aux[pAux.getLinha()][pAux.getColuna()]=true;
		}
		//Mover pra baixo
		pAux.setValue(posicao.getLinha()+1, posicao.getColuna());
		if(getTabuleiro().posicaoExistente(pAux) && reiPodeMover(pAux))
		{
			aux[pAux.getLinha()][pAux.getColuna()]=true;
		}
		//Mover para a esquerda
		pAux.setValue(posicao.getLinha(), posicao.getColuna()-1);
		if(getTabuleiro().posicaoExistente(pAux) && reiPodeMover(pAux))
		{
			aux[pAux.getLinha()][pAux.getColuna()]=true;
		}
		//Mover para a direita
		pAux.setValue(posicao.getLinha(), posicao.getColuna()+1);
		if(getTabuleiro().posicaoExistente(pAux) && reiPodeMover(pAux))
		{
			aux[pAux.getLinha()][pAux.getColuna()]=true;
		}
		//Mover para a noroeste
		pAux.setValue(posicao.getLinha()-1, posicao.getColuna()-1);
		if (getTabuleiro().posicaoExistente(pAux) && reiPodeMover(pAux)) 
		{
			aux[pAux.getLinha()][pAux.getColuna()] = true;
		}
		//Mover para a nordeste
		pAux.setValue(posicao.getLinha()-1, posicao.getColuna()+1);
		if (getTabuleiro().posicaoExistente(pAux) && reiPodeMover(pAux)) 
		{
			aux[pAux.getLinha()][pAux.getColuna()] = true;
		}
		//Mover para a sudoeste
		pAux.setValue(posicao.getLinha()+1, posicao.getColuna()-1);
		if (getTabuleiro().posicaoExistente(pAux) && reiPodeMover(pAux)) 
		{
			aux[pAux.getLinha()][pAux.getColuna()] = true;
		}
		//Mover para a sudeste
		pAux.setValue(posicao.getLinha()+1, posicao.getColuna()+1);
		if (getTabuleiro().posicaoExistente(pAux) && reiPodeMover(pAux)) 
		{
			aux[pAux.getLinha()][pAux.getColuna()] = true;
		}
		return aux;
	}
}
