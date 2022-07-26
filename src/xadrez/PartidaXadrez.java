package xadrez;


import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import tabuleirodojogo.Peca;
import tabuleirodojogo.Posicao;
import tabuleirodojogo.Tabuleiro;
import xadrez.pecas.Bispo;
import xadrez.pecas.Cavalo;
import xadrez.pecas.Peao;
import xadrez.pecas.Rainha;
import xadrez.pecas.Rei;
import xadrez.pecas.Torre;

public class PartidaXadrez {
	private Integer turno;
	private CorPecas corJogador;
	private Tabuleiro tabuleiro;
	private Boolean check=false;
	private Boolean checkMate=false;
	private PecaXadrez promocao;
	private PecaXadrez enPassan;
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
	public PecaXadrez getEnPassan()
	{
		return enPassan;
	}
	public PecaXadrez getPromocao()
	{
		return promocao;
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
		promocao=null;
		PecaXadrez pecaMovida=(PecaXadrez)tabuleiro.posicaoPeca(destino);
		if(pecaMovida instanceof Peao)
		{
			if((pecaMovida.getCorDaPeca()==CorPecas.WHITE && destino.getLinha()==0) || pecaMovida.getCorDaPeca()==CorPecas.BLACK && destino.getLinha()==7)
			{
				promocao=(PecaXadrez)tabuleiro.posicaoPeca(destino);
				promocao=transformar("Q");
			}
		}
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
		if(pecaMovida instanceof Peao && (destino.getLinha()==origem.getLinha()-2 || destino.getLinha()==origem.getLinha()+2))
		{
			enPassan=pecaMovida;
		}
		else
		{
			enPassan=null;
		}
		return (PecaXadrez)pecaCapturada;
	}
	public PecaXadrez transformar(String tipo)
	{
		if(promocao==null)
		{
			throw new IllegalStateException("Não existe peça que possa ser promovida");
		}
		if(!tipo.equals("B") && !tipo.equals("T") && !tipo.equals("C") && !tipo.equals("Q"))
		{
			return promocao;
		}
		Posicao posicaoPecaPromovida=promocao.getPosicaoPeca().toPosicao();
		Peca aux=tabuleiro.removerPeca(posicaoPecaPromovida);
		pecasTabuleiro.remove(aux);
		PecaXadrez newPiece=novaPeca(tipo,promocao.getCorDaPeca());
		tabuleiro.localPeca(newPiece, posicaoPecaPromovida);
		pecasTabuleiro.add(newPiece);
		return newPiece;
	}
	private PecaXadrez novaPeca(String tipo,CorPecas cor)
	{
		if(tipo.equals("B")) return new Bispo(tabuleiro, cor);
		if(tipo.equals("R")) return new Torre(tabuleiro, cor);
		if(tipo.equals("C")) return new Cavalo(tabuleiro, cor);
		return new Rainha(tabuleiro, cor);
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
		PecaXadrez aux=(PecaXadrez)tabuleiro.removerPeca(origem);
		aux.aumentarMovimento();
		Peca capturada=tabuleiro.removerPeca(destino);
		tabuleiro.localPeca(aux, destino);
		if(capturada!=null)
		{
			pecasTabuleiro.remove(capturada);
			pecasCapturadas.add(capturada);
		}
		if(aux instanceof Rei && destino.getColuna()==origem.getColuna()+2)
		{
			Posicao origemTorre=new Posicao(origem.getLinha(), origem.getColuna()+3);
			Posicao destinoTorre=new Posicao(origem.getLinha(), origem.getColuna()+1);
			PecaXadrez torre=(PecaXadrez)tabuleiro.removerPeca(origemTorre);
			tabuleiro.localPeca(torre, destinoTorre);
			torre.aumentarMovimento();
		}
		if(aux instanceof Rei && destino.getColuna()==origem.getColuna()-2)
		{
			Posicao origemTorre=new Posicao(origem.getLinha(), origem.getColuna()-4);
			Posicao destinoTorre=new Posicao(origem.getLinha(), origem.getColuna()-1);
			PecaXadrez torre=(PecaXadrez)tabuleiro.removerPeca(origemTorre);
			tabuleiro.localPeca(torre, destinoTorre);
			torre.aumentarMovimento();
		}
		if(aux instanceof Peao)
		{
			if(origem.getColuna()!=destino.getColuna() && capturada==null)
			{
				Posicao posicaoPeao;
				if(aux.getCorDaPeca()==CorPecas.WHITE)
				{
					posicaoPeao=new Posicao(destino.getLinha()+1, destino.getColuna());
				}
				else
				{
					posicaoPeao=new Posicao(destino.getLinha()-1, destino.getColuna());
				}
				capturada=tabuleiro.removerPeca(posicaoPeao);
				pecasCapturadas.add(capturada);
				pecasTabuleiro.remove(capturada);
			}
		}
		return capturada;
	}
	private void desfazerMovimento(Posicao origem,Posicao destino,Peca capturada)
	{
		PecaXadrez aux=(PecaXadrez)tabuleiro.removerPeca(destino);
		aux.diminuirMovimento();
		tabuleiro.localPeca(aux, origem);
		if(capturada!=null)
		{
			tabuleiro.localPeca(capturada, destino);
			pecasCapturadas.remove(capturada);
			pecasTabuleiro.add(capturada);
		}
		if(aux instanceof Rei && destino.getColuna()==origem.getColuna()+2)
		{
			Posicao origemTorre=new Posicao(origem.getLinha(), origem.getColuna()+3);
			Posicao destinoTorre=new Posicao(origem.getLinha(), origem.getColuna()+1);
			PecaXadrez torre=(PecaXadrez)tabuleiro.removerPeca(destinoTorre);
			tabuleiro.localPeca(torre, origemTorre);
			torre.diminuirMovimento();
		}
		if(aux instanceof Rei && destino.getColuna()==origem.getColuna()-2)
		{
			Posicao origemTorre=new Posicao(origem.getLinha(), origem.getColuna()-4);
			Posicao destinoTorre=new Posicao(origem.getLinha(), origem.getColuna()-1);
			PecaXadrez torre=(PecaXadrez)tabuleiro.removerPeca(destinoTorre);
			tabuleiro.localPeca(torre, origemTorre);
			torre.diminuirMovimento();
		}
		if(aux instanceof Peao)
		{
			if(origem.getColuna()!=destino.getColuna() && capturada==enPassan)
			{
				PecaXadrez peao=(PecaXadrez)tabuleiro.removerPeca(destino);
				Posicao posicaoPeao;
				if(aux.getCorDaPeca()==CorPecas.WHITE)
				{
					posicaoPeao=new Posicao(3, destino.getColuna());
				}
				else
				{
					posicaoPeao=new Posicao(4, destino.getColuna());
				}
				tabuleiro.localPeca(peao, posicaoPeao);
			}
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
		setupPecaNova('a',1,new Torre(tabuleiro,CorPecas.WHITE));
		setupPecaNova('b',1,new Cavalo(tabuleiro,CorPecas.WHITE));
		setupPecaNova('c',1,new Bispo(tabuleiro,CorPecas.WHITE));
		setupPecaNova('d',1,new Rainha(tabuleiro,CorPecas.WHITE));
		setupPecaNova('e',1,new Rei(tabuleiro,CorPecas.WHITE,this));
		setupPecaNova('f',1,new Bispo(tabuleiro,CorPecas.WHITE));
		setupPecaNova('g',1,new Cavalo(tabuleiro,CorPecas.WHITE));
		setupPecaNova('h',1,new Torre(tabuleiro,CorPecas.WHITE));
		setupPecaNova('a',2,new Peao(tabuleiro,CorPecas.WHITE,this));
		setupPecaNova('b',2,new Peao(tabuleiro,CorPecas.WHITE,this));
		setupPecaNova('c',2,new Peao(tabuleiro,CorPecas.WHITE,this));
		setupPecaNova('d',2,new Peao(tabuleiro,CorPecas.WHITE,this));
		setupPecaNova('e',2,new Peao(tabuleiro,CorPecas.WHITE,this));
		setupPecaNova('f',2,new Peao(tabuleiro,CorPecas.WHITE,this));
		setupPecaNova('g',2,new Peao(tabuleiro,CorPecas.WHITE,this));
		setupPecaNova('h',2,new Peao(tabuleiro,CorPecas.WHITE,this));
		
		setupPecaNova('a',8,new Torre(tabuleiro,CorPecas.BLACK));
		setupPecaNova('b',8,new Cavalo(tabuleiro,CorPecas.BLACK));
		setupPecaNova('c',8,new Bispo(tabuleiro,CorPecas.BLACK));
		setupPecaNova('d',8,new Rainha(tabuleiro,CorPecas.BLACK));
		setupPecaNova('e',8,new Rei(tabuleiro,CorPecas.BLACK,this));
		setupPecaNova('g',8,new Cavalo(tabuleiro,CorPecas.BLACK));
		setupPecaNova('h',8,new Torre(tabuleiro,CorPecas.BLACK));
		setupPecaNova('f',8,new Bispo(tabuleiro,CorPecas.BLACK));
		setupPecaNova('a',7,new Peao(tabuleiro,CorPecas.BLACK,this));
		setupPecaNova('b',7,new Peao(tabuleiro,CorPecas.BLACK,this));
		setupPecaNova('c',7,new Peao(tabuleiro,CorPecas.BLACK,this));
		setupPecaNova('d',7,new Peao(tabuleiro,CorPecas.BLACK,this));
		setupPecaNova('e',7,new Peao(tabuleiro,CorPecas.BLACK,this));
		setupPecaNova('f',7,new Peao(tabuleiro,CorPecas.BLACK,this));
		setupPecaNova('g',7,new Peao(tabuleiro,CorPecas.BLACK,this));
		setupPecaNova('h',7,new Peao(tabuleiro,CorPecas.BLACK,this));
	}
}
