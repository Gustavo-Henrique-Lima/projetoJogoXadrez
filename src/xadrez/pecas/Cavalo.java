package xadrez.pecas;

import tabuleirodojogo.Posicao;
import tabuleirodojogo.Tabuleiro;
import xadrez.CorPecas;
import xadrez.PecaXadrez;

public class Cavalo extends PecaXadrez{

	public Cavalo(Tabuleiro tabuleiro, CorPecas corDaPeca) 
	{
		super(tabuleiro, corDaPeca);
	}
	private boolean cavaloPodeMover(Posicao posicao)
	{
		PecaXadrez peca=(PecaXadrez)getTabuleiro().posicaoPeca(posicao);
		return peca==null || peca.getCorDaPeca()!=getCorDaPeca();
	}
	@Override
	public boolean[][] movimentosPossiveis() 
	{
		boolean[][] aux=new boolean[getTabuleiro().getColunas()][getTabuleiro().getColunas()];
		Posicao pAux=new Posicao(0, 0);
		
		pAux.setValue(posicao.getLinha()-1, posicao.getColuna()-2);
		if(getTabuleiro().posicaoExistente(pAux) && cavaloPodeMover(pAux))
		{
			aux[pAux.getLinha()][pAux.getColuna()]=true;
		}
	
		pAux.setValue(posicao.getLinha()-2, posicao.getColuna()-1);
		if(getTabuleiro().posicaoExistente(pAux) && cavaloPodeMover(pAux))
		{
			aux[pAux.getLinha()][pAux.getColuna()]=true;
		}
	
		pAux.setValue(posicao.getLinha()-2, posicao.getColuna()+1);
		if(getTabuleiro().posicaoExistente(pAux) && cavaloPodeMover(pAux))
		{
			aux[pAux.getLinha()][pAux.getColuna()]=true;
		}
	
		pAux.setValue(posicao.getLinha()-1, posicao.getColuna()+2);
		if(getTabuleiro().posicaoExistente(pAux) && cavaloPodeMover(pAux))
		{
			aux[pAux.getLinha()][pAux.getColuna()]=true;
		}
		
		pAux.setValue(posicao.getLinha()+1, posicao.getColuna()+2);
		if (getTabuleiro().posicaoExistente(pAux) && cavaloPodeMover(pAux)) 
		{
			aux[pAux.getLinha()][pAux.getColuna()] = true;
		}
	
		pAux.setValue(posicao.getLinha()+2, posicao.getColuna()+1);
		if (getTabuleiro().posicaoExistente(pAux) && cavaloPodeMover(pAux)) 
		{
			aux[pAux.getLinha()][pAux.getColuna()] = true;
		}
		
		pAux.setValue(posicao.getLinha()+2, posicao.getColuna()-1);
		if (getTabuleiro().posicaoExistente(pAux) && cavaloPodeMover(pAux)) 
		{
			aux[pAux.getLinha()][pAux.getColuna()] = true;
		}
		
		pAux.setValue(posicao.getLinha()+1, posicao.getColuna()-2);
		if (getTabuleiro().posicaoExistente(pAux) && cavaloPodeMover(pAux)) 
		{
			aux[pAux.getLinha()][pAux.getColuna()] = true;
		}
		return aux;
	}
	@Override
	public String toString()
	{
		return "C";
	}
}
