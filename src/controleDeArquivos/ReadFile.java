package controleDeArquivos;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;

import javax.swing.JOptionPane;

/*
 * 	CLASS Para ler o arquivo
 * 
 * */
public class ReadFile {

	private BufferedReader input;
	private int contLinhaArquivo;

	// Função que abre o arquivo
	public void openFile(File arq) {
		try {
			// abrindo arquivo para leitura
			FileReader reader = new FileReader(arq);

			input = new BufferedReader(reader);
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "Erro ao abrir");
		}
	}

	/* === Função que le o arquivo selecionado === */
	public String readRecords() {
		contLinhaArquivo = 0;
		String result = "";
		String linha = "";
		try {
			while (true) {
				linha = input.readLine();
				if (linha == null)
					break;
				result += linha + "\n";
				contLinhaArquivo++;
			}

		} catch (IOException e) {
			JOptionPane.showMessageDialog(null,"Erro na leitura, contact o suporte\n Erro: "+e.getStackTrace(),"Erro",0);
		}
		return result;
	}
	
	/* === Funcao que conta o numero de lina === */
	public String numLine(){
		String numeroDeLinhas = new String();
		for(int i =1; i<=contLinhaArquivo; i++){
			numeroDeLinhas += i + "\n";
		}
		numeroDeLinhas += (contLinhaArquivo+1)+"";
		return numeroDeLinhas;
	}

	public void closeFile() {

		try {
			input.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public int getContLinhaArquivo(){
		return contLinhaArquivo;
	}

}
