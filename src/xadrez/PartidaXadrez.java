package xadrez;


import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import tabuleirodojogo.Peca;
import tabuleirodojogo.Posicao;
import tabuleirodojogo.Tabuleiro;
import xadrez.pecas.Rei;
import xadrez.pecas.Torre;

public class PartidaXadrez {
	private Integer turno;
	private CorPecas corJogador;
	private Tabuleiro tabuleiro;
	private Boolean check=false;
	private Boolean checkMate=false;
	private List<Peca> pecasCapturadas=new ArrayList<>();
	private List<Peca> pecasTabuleiro=new ArrayList<>();
	public PartidaXadrez()
	{
		this.tabuleiro=new Tabuleiro(8, 8);
		turno=1;
		corJogador=CorPecas.WHITE;
		setupInicial();
	}
	public Integer getTurno() 
	{
		return turno;
	}
	public CorPecas getCorJogador() 
	{
		return corJogador;
	}
	public Boolean getCheck() 
	{
		return check;
	}
	public Boolean getCheckMate()
	{
		return checkMate;
	}
	public PecaXadrez[][] getPecas()
	{
		PecaXadrez[][] pecasTabuleiro=new PecaXadrez[tabuleiro.getLinhas()][tabuleiro.getColunas()];
		for(int i=0;i<tabuleiro.getLinhas();i++)
		{
			for(int j=0;j<tabuleiro.getColunas();j++)
			{
				pecasTabuleiro[i][j]=(PecaXadrez) tabuleiro.posicaoPeca(i, j);
			}
		}
		return pecasTabuleiro;
	}
	private void setupPecaNova(char coluna,Integer linha,PecaXadrez peca)
	{
		tabuleiro.localPeca(peca, new PosicaoXadrez(coluna, linha).toPosicao());
		pecasTabuleiro.add(peca);
	}
	public PecaXadrez movimentoDasPecas(PosicaoXadrez posicaoOrigem,PosicaoXadrez posicaoDestino)
	{
		Posicao origem=posicaoOrigem.toPosicao();
		Posicao destino=posicaoDestino.toPosicao();
		validarPosicao(origem);
		validarPosicaoDestino(origem,destino);
		Peca pecaCapturada=movimentacao(origem,destino);
		if(testCheck(corJogador))
		{
			desfazerMovimento(origem, destino, pecaCapturada);
			throw new ExcecaoXadrez("Jogada desfeita, você não pode se colocar em xeque");
		}
		check=(testCheck(corOponente(corJogador)))?true:false;
		if(testCheckMate(corOponente(corJogador)))
		{
			checkMate=true;
		}
		else
		{
			proximoTurno();
		}
		return (PecaXadrez)pecaCapturada;
	}
	private void validarPosicao(Posicao posicao)
	{
		if(!tabuleiro.temPeca(posicao))
		{
			throw new ExcecaoXadrez("Não existe peça na posição indicada");
		}
		if(corJogador!=((PecaXadrez)tabuleiro.posicaoPeca(posicao)).getCorDaPeca())
		{
			throw new ExcecaoXadrez("A peça escolhida não é sua");
		}
		if(!tabuleiro.posicaoPeca(posicao).algumMovimentoPossivel())
		{
			throw new ExcecaoXadrez("Não existem movimentos possíveis para a peça escolhida");
		}
	}
	private void validarPosicaoDestino(Posicao origem,Posicao destino)
	{
		if(!tabuleiro.posicaoPeca(origem).movimentosPossiveis(destino))
		{
			throw new ExcecaoXadrez("A peça escolhida não pode ser movida para a posição de destino");
		}
	}
	private Peca movimentacao(Posicao origem,Posicao destino)
	{
		Peca aux=tabuleiro.removerPeca(origem);
		Peca capturada=tabuleiro.removerPeca(destino);
		tabuleiro.localPeca(aux, destino);
		if(capturada!=null)
		{
			pecasTabuleiro.remove(capturada);
			pecasCapturadas.add(capturada);
		}
		return capturada;
	}
	private void desfazerMovimento(Posicao origem,Posicao destino,Peca capturada)
	{
		Peca aux=tabuleiro.removerPeca(destino);
		tabuleiro.localPeca(aux, origem);
		if(capturada!=null)
		{
			tabuleiro.localPeca(capturada, destino);
			pecasCapturadas.remove(capturada);
			pecasTabuleiro.add(capturada);
		}
	}
	public boolean[][] movimentosPossiveisParaPeca(PosicaoXadrez posicao)
	{
		Posicao posicaoAux=posicao.toPosicao();
		validarPosicao(posicaoAux);
		return tabuleiro.posicaoPeca(posicaoAux).movimentosPossiveis();
	}
	private void proximoTurno()
	{
		turno++;
		corJogador=(corJogador==CorPecas.WHITE) ? CorPecas.BLACK : CorPecas.WHITE;
	}
	private CorPecas corOponente(CorPecas cor)
	{
		return (cor==CorPecas.WHITE) ? CorPecas.BLACK: CorPecas.WHITE;
	}
	private PecaXadrez rei(CorPecas cor)
	{
		List<Peca> pecas=pecasTabuleiro.stream().filter(x-> ((PecaXadrez)x).getCorDaPeca()==cor).collect(Collectors.toList());
		for(Peca p: pecas)
		{
			if(p instanceof Rei)
			{
				return (PecaXadrez)p;
			}
		}
		throw new IllegalStateException("Não existe o rei "+cor+" no tabuleiro");
	}
	private boolean testCheck(CorPecas cor)
	{
		Posicao posicaoRei=rei(cor).getPosicaoPeca().toPosicao();
		List<Peca> pecasOponente=pecasTabuleiro.stream().filter(x-> ((PecaXadrez)x).getCorDaPeca()==corOponente(cor)).collect(Collectors.toList());
		for(Peca p: pecasOponente)
		{
			boolean[][] aux=p.movimentosPossiveis();
			if(aux[posicaoRei.getLinha()][posicaoRei.getColuna()])
			{
				return true;
			}
		}
		return false;
	}
	private boolean testCheckMate(CorPecas cor)
	{
		if(!testCheck(cor))
		{
			return false;
		}
		List<Peca> peca=pecasTabuleiro.stream().filter(x-> ((PecaXadrez)x).getCorDaPeca()==cor).collect(Collectors.toList());
		for(Peca p: peca)
		{
			boolean[][] mat=p.movimentosPossiveis();
			for(int i=0;i<tabuleiro.getColunas();i++)
			{
				for(int j=0;j<tabuleiro.getColunas();j++)
				{
					if(mat[i][j])
					{
						Posicao origem=((PecaXadrez)p).getPosicaoPeca().toPosicao();
						Posicao destino=new Posicao(i,j);
						Peca pecaCaptura=movimentacao(origem, destino);
						boolean testCheck=testCheck(cor);
						desfazerMovimento(origem, destino, pecaCaptura);
						if(!testCheck)
						{
							return false;
						}
					}
				}
			}
		}
		return true;
	}
	private void setupInicial()
	{
		setupPecaNova('c',1,new Torre(tabuleiro,CorPecas.WHITE));
		setupPecaNova('c',2,new Torre(tabuleiro,CorPecas.WHITE));
		setupPecaNova('d',2,new Torre(tabuleiro,CorPecas.WHITE));
		setupPecaNova('e',2,new Torre(tabuleiro,CorPecas.WHITE));
		setupPecaNova('e',1,new Torre(tabuleiro,CorPecas.WHITE));
		setupPecaNova('d',1,new Rei(tabuleiro,CorPecas.WHITE));
		
		setupPecaNova('c',7,new Torre(tabuleiro,CorPecas.BLACK));
		setupPecaNova('c',8,new Torre(tabuleiro,CorPecas.BLACK));
		setupPecaNova('d',7,new Torre(tabuleiro,CorPecas.BLACK));
		setupPecaNova('e',7,new Torre(tabuleiro,CorPecas.BLACK));
		setupPecaNova('e',8,new Torre(tabuleiro,CorPecas.BLACK));
		setupPecaNova('d',8,new Rei(tabuleiro,CorPecas.BLACK));
	}
}
