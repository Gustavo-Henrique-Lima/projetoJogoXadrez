package xadrez.pecas;

import tabuleirodojogo.Posicao;
import tabuleirodojogo.Tabuleiro;
import xadrez.CorPecas;
import xadrez.PartidaXadrez;
import xadrez.PecaXadrez;

public class Rei extends PecaXadrez{
	private PartidaXadrez partida;
	public Rei(Tabuleiro tabuleiro, CorPecas corDaPeca,PartidaXadrez partida) 
	{
		super(tabuleiro, corDaPeca);
		this.partida=partida;
	}
	private boolean reiPodeMover(Posicao posicao)
	{
		PecaXadrez peca=(PecaXadrez)getTabuleiro().posicaoPeca(posicao);
		return peca==null || peca.getCorDaPeca()!=getCorDaPeca();
	}
	private boolean testRoque(Posicao posicao)
	{
		PecaXadrez pecaAux=(PecaXadrez)getTabuleiro().posicaoPeca(posicao);
		return pecaAux!=null && pecaAux instanceof Torre && pecaAux.getCorDaPeca()==getCorDaPeca() && pecaAux.getContadorMovimentos()==0;
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
		//Jogada Roque
		if(getContadorMovimentos()==0 && !partida.getCheck())
		{
			Posicao ptorre1=new Posicao(posicao.getLinha(),posicao.getColuna()+3);
			if(testRoque(ptorre1))
			{
				Posicao p1=new Posicao(posicao.getLinha(),posicao.getColuna()+1);
				Posicao p2=new Posicao(posicao.getLinha(),posicao.getColuna()+2);
				if(getTabuleiro().posicaoPeca(p1)==null && getTabuleiro().posicaoPeca(p2)==null)
				{
					aux[posicao.getLinha()][posicao.getColuna()+2]=true;
				}
			}
		}
		//Jogada roque pro lado da rainha
		Posicao ptorre2=new Posicao(posicao.getLinha(),posicao.getColuna()-4);
		if(testRoque(ptorre2))
		{
			Posicao p1=new Posicao(posicao.getLinha(),posicao.getColuna()-1);
			Posicao p2=new Posicao(posicao.getLinha(),posicao.getColuna()-2);
			Posicao p3=new Posicao(posicao.getLinha(),posicao.getColuna()-3);
			if(getTabuleiro().posicaoPeca(p1)==null && getTabuleiro().posicaoPeca(p2)==null && getTabuleiro().posicaoPeca(p3)==null)
			{
				aux[posicao.getLinha()][posicao.getColuna()-2]=true;
			}
		}
		return aux;
	}
}
