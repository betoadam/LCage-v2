package recovery;

import parser.*;

public class First {
	static public final RecoverySet start = new RecoverySet();
	static public final RecoverySet variosFunc = new RecoverySet();
	static public final RecoverySet main = new RecoverySet();
	static public final RecoverySet varDecl = new RecoverySet();
	static public final RecoverySet atribuicao = new RecoverySet();
	static public final RecoverySet atribuir = new RecoverySet();
	static public final RecoverySet talvezListaExp = new RecoverySet();
	static public final RecoverySet loop = new RecoverySet();
	static public final RecoverySet listaExp = new RecoverySet();
	static public final RecoverySet listaExp2 = new RecoverySet();
	static public final RecoverySet mdeclaracao = new RecoverySet();
	static public final RecoverySet exp = new RecoverySet();
	static public final RecoverySet fator = new RecoverySet();
	static public final RecoverySet talvezFator = new RecoverySet();
	static public final RecoverySet seqComandos = new RecoverySet();
	static public final RecoverySet comando = new RecoverySet();
	static public final RecoverySet talvezElse = new RecoverySet();
	static public final RecoverySet exp_logicas = new RecoverySet();
	static public final RecoverySet fator_logico = new RecoverySet();
	static public final RecoverySet func = new RecoverySet();
	static public final RecoverySet talvezListaArg = new RecoverySet();
	static public final RecoverySet listaArg = new RecoverySet();
	static public final RecoverySet listaArg2 = new RecoverySet();
	static public final RecoverySet tipo = new RecoverySet();
	
	static {
		
		start.add(new Integer(LCageConstants.EOF));
		
		main.add(new Integer(LCageConstants.FUNCAO));
		main.add(new Integer(LCageConstants.EOF));
		
		variosFunc.add(new Integer(LCageConstants.EOF));
		
		varDecl.add(new Integer(LCageConstants.IDENTIFICADOR));
		varDecl.add(new Integer(LCageConstants.IF));
		varDecl.add(new Integer(LCageConstants.WHILE));
		varDecl.add(new Integer(LCageConstants.DO));
		varDecl.add(new Integer(LCageConstants.RETURN));
		varDecl.add(new Integer(LCageConstants.PRINT));
		varDecl.add(new Integer(LCageConstants.VAR));
		
		tipo.add(new Integer(LCageConstants.IDENTIFICADOR));
		
		atribuicao.add(new Integer(LCageConstants.VAR));
		atribuicao.add(new Integer(LCageConstants.IF));
		atribuicao.add(new Integer(LCageConstants.IDENTIFICADOR));
		atribuicao.add(new Integer(LCageConstants.WHILE));
		atribuicao.add(new Integer(LCageConstants.DO));
		atribuicao.add(new Integer(LCageConstants.RETURN));
		atribuicao.add(new Integer(LCageConstants.PRINT));
		atribuicao.add(new Integer(LCageConstants.FCHAVES));
		
		atribuir.add(new Integer(LCageConstants.IDENTIFICADOR));
		atribuir.add(new Integer(LCageConstants.IF));
		atribuir.add(new Integer(LCageConstants.WHILE));
		atribuir.add(new Integer(LCageConstants.DO));
		atribuir.add(new Integer(LCageConstants.RETURN));
		atribuir.add(new Integer(LCageConstants.PRINT));
		atribuir.add(new Integer(LCageConstants.VAR));
		atribuir.add(new Integer(LCageConstants.FCHAVES));
		

		talvezListaExp.add(new Integer(LCageConstants.FPARENT));
		

		loop.add(new Integer(LCageConstants.IDENTIFICADOR));
		loop.add(new Integer(LCageConstants.IF));
		loop.add(new Integer(LCageConstants.WHILE));
		loop.add(new Integer(LCageConstants.DO));
		loop.add(new Integer(LCageConstants.RETURN));
		loop.add(new Integer(LCageConstants.PRINT));
		loop.add(new Integer(LCageConstants.VAR));
		loop.add(new Integer(LCageConstants.FCHAVES));
		
		listaExp.add(new Integer(LCageConstants.FPARENT));
		
		listaExp2.add(new Integer(LCageConstants.FPARENT));
		
		mdeclaracao.add(new Integer(LCageConstants.IDENTIFICADOR));
		mdeclaracao.add(new Integer(LCageConstants.IF));
		mdeclaracao.add(new Integer(LCageConstants.WHILE));
		mdeclaracao.add(new Integer(LCageConstants.DO));
		mdeclaracao.add(new Integer(LCageConstants.RETURN));
		mdeclaracao.add(new Integer(LCageConstants.PRINT));
		mdeclaracao.add(new Integer(LCageConstants.VAR));
		mdeclaracao.add(new Integer(LCageConstants.FCHAVES));
		
		exp.add(new Integer(LCageConstants.PTOVIRGULA));
		exp.add(new Integer(LCageConstants.FPARENT));
		exp.add(new Integer(LCageConstants.OP));
		exp.add(new Integer(LCageConstants.VIRGULA));
		
		fator.add(new Integer(LCageConstants.PTOVIRGULA));
		fator.add(new Integer(LCageConstants.FPARENT));
		fator.add(new Integer(LCageConstants.OP));
		fator.add(new Integer(LCageConstants.VIRGULA));
		
		talvezFator.add(new Integer(LCageConstants.PTOVIRGULA));
		talvezFator.add(new Integer(LCageConstants.FPARENT));
		talvezFator.add(new Integer(LCageConstants.OP));
		talvezFator.add(new Integer(LCageConstants.VIRGULA));
		
		seqComandos.add(new Integer(LCageConstants.FCHAVES));
		
		comando.add(new Integer(LCageConstants.IDENTIFICADOR));
		comando.add(new Integer(LCageConstants.IF));
		comando.add(new Integer(LCageConstants.WHILE));
		comando.add(new Integer(LCageConstants.DO));
		comando.add(new Integer(LCageConstants.RETURN));
		comando.add(new Integer(LCageConstants.PRINT));
		comando.add(new Integer(LCageConstants.FCHAVES));
		
		talvezElse.add(new Integer(LCageConstants.PTOVIRGULA));
		
		exp_logicas.add(new Integer(LCageConstants.OPL));
		exp_logicas.add(new Integer(LCageConstants.FPARENT));
		
		fator_logico.add(new Integer(LCageConstants.OPL));
		fator_logico.add(new Integer(LCageConstants.FPARENT));
		
		func.add(new Integer(LCageConstants.FUNCAO));
		func.add(new Integer(LCageConstants.EOF));
		
		talvezListaArg.add(new Integer(LCageConstants.FPARENT));
		
		listaArg.add(new Integer(LCageConstants.FPARENT));
		
		listaArg2.add(new Integer(LCageConstants.FPARENT));
	}
}
