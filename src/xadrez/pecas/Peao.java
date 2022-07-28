package xadrez.pecas;

import tabuleirodojogo.Posicao;
import tabuleirodojogo.Tabuleiro;
import xadrez.CorPecas;
import xadrez.PartidaXadrez;
import xadrez.PecaXadrez;

public class Peao extends PecaXadrez{
	private PartidaXadrez partida;
	public Peao(Tabuleiro tabuleiro, CorPecas corDaPeca,PartidaXadrez partida) 
	{
		super(tabuleiro, corDaPeca);
		this.partida=partida;
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
			if(posicao.getLinha()==3)
			{
				Posicao auxEsquerda=new Posicao(posicao.getLinha(), posicao.getColuna()-1);
				if(getTabuleiro().posicaoExistente(auxEsquerda) && temPecaInimiga(auxEsquerda) && getTabuleiro().posicaoPeca(auxEsquerda)==partida.getEnPassan())
				{
					aux[auxEsquerda.getLinha()-1][auxEsquerda.getColuna()]=true;
				}
				Posicao auxDireita=new Posicao(posicao.getLinha(), posicao.getColuna()+1);
				if(getTabuleiro().posicaoExistente(auxDireita) && temPecaInimiga(auxDireita) && getTabuleiro().posicaoPeca(auxDireita)==partida.getEnPassan())
				{
					aux[auxEsquerda.getLinha()-1][auxEsquerda.getColuna()]=true;
				}
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
			if(posicao.getLinha()==4)
			{
				Posicao auxEsquerda=new Posicao(posicao.getLinha(), posicao.getColuna()-1);
				if(getTabuleiro().posicaoExistente(auxEsquerda) && temPecaInimiga(auxEsquerda) && getTabuleiro().posicaoPeca(auxEsquerda)==partida.getEnPassan())
				{
					aux[auxEsquerda.getLinha()+1][auxEsquerda.getColuna()]=true;
				}
				Posicao auxDireita=new Posicao(posicao.getLinha(), posicao.getColuna()+1);
				if(getTabuleiro().posicaoExistente(auxDireita) && temPecaInimiga(auxDireita) && getTabuleiro().posicaoPeca(auxDireita)==partida.getEnPassan())
				{
					aux[auxEsquerda.getLinha()+1][auxEsquerda.getColuna()]=true;
				}
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
