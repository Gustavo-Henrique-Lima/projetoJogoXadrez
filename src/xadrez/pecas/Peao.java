package xadrez.pecas;

import tabuleirodojogo.Posicao;
import tabuleirodojogo.Tabuleiro;
import xadrez.CorPecas;
import xadrez.PecaXadrez;

public class Peao extends PecaXadrez{

	public Peao(Tabuleiro tabuleiro, CorPecas corDaPeca) 
	{
		super(tabuleiro, corDaPeca);
	}
	@Override
	public boolean[][] movimentosPossiveis() 
	{
		boolean[][] aux=new boolean[getTabuleiro().getColunas()][getTabuleiro().getColunas()];
		Posicao pecaAux=new Posicao(0, 0);
		if(getCorDaPeca()==CorPecas.WHITE)
		{
			pecaAux.setValue(posicao.getLinha()-1, posicao.getColuna());
			if(getTabuleiro().posicaoExistente(pecaAux) && !getTabuleiro().temPeca(pecaAux))
			{
				aux[pecaAux.getLinha()][pecaAux.getColuna()]=true;
			}
			pecaAux.setValue(posicao.getLinha()-2, posicao.getColuna());
			Posicao pecaAux2=new Posicao(posicao.getLinha()-1, posicao.getColuna());
			if(getTabuleiro().posicaoExistente(pecaAux) && !getTabuleiro().temPeca(pecaAux) && getTabuleiro().posicaoExistente(pecaAux2) && !getTabuleiro().temPeca(pecaAux2) && getContadorMovimentos()==0)
			{
				aux[pecaAux.getLinha()][pecaAux.getColuna()]=true;
			}
			pecaAux.setValue(posicao.getLinha()-1, posicao.getColuna()-1);
			if(getTabuleiro().posicaoExistente(pecaAux) && temPecaInimiga(pecaAux))
			{
				aux[pecaAux.getLinha()][pecaAux.getColuna()]=true;
			}
			pecaAux.setValue(posicao.getLinha()-1, posicao.getColuna()+1);
			if(getTabuleiro().posicaoExistente(pecaAux) && temPecaInimiga(pecaAux))
			{
				aux[pecaAux.getLinha()][pecaAux.getColuna()]=true;
			}
		}
		else
		{
			pecaAux.setValue(posicao.getLinha()+1, posicao.getColuna());
			if(getTabuleiro().posicaoExistente(pecaAux) && !getTabuleiro().temPeca(pecaAux))
			{
				aux[pecaAux.getLinha()][pecaAux.getColuna()]=true;
			}
			pecaAux.setValue(posicao.getLinha()+2, posicao.getColuna());
			Posicao pecaAux2=new Posicao(posicao.getLinha()+1, posicao.getColuna());
			if(getTabuleiro().posicaoExistente(pecaAux) && !getTabuleiro().temPeca(pecaAux) && getTabuleiro().posicaoExistente(pecaAux2) && !getTabuleiro().temPeca(pecaAux2) && getContadorMovimentos()==0)
			{
				aux[pecaAux.getLinha()][pecaAux.getColuna()]=true;
			}
			pecaAux.setValue(posicao.getLinha()+1, posicao.getColuna()-1);
			if(getTabuleiro().posicaoExistente(pecaAux) && temPecaInimiga(pecaAux))
			{
				aux[pecaAux.getLinha()][pecaAux.getColuna()]=true;
			}
			pecaAux.setValue(posicao.getLinha()+1, posicao.getColuna()+1);
			if(getTabuleiro().posicaoExistente(pecaAux) && temPecaInimiga(pecaAux))
			{
				aux[pecaAux.getLinha()][pecaAux.getColuna()]=true;
			}
		}
		return aux;
	}
	@Override
	public String toString()
	{
		return "P";
	}
}
