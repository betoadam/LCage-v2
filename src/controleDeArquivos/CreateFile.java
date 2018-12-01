package controleDeArquivos;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectOutputStream;

import javax.swing.JOptionPane;

public class CreateFile {
	
	// Classe para salvar em arquivo txt
	FileWriter fw;
	
	public void openFile(File arq){
		try{
			fw = new FileWriter(arq);
		}catch(IOException ioException){
			JOptionPane.showMessageDialog(null, "Erro ao abrir");
		}
	}
	
	public void addRecords(String str){
		try{
			//Salva todo o conteudo que esta no campo de texto e grava no arquivo
			fw.write(str);
			fw.flush();
		}catch(IOException e)
		{ e.printStackTrace(); }
	}
	
	public void closeFile(){
		try{
			fw.close();
		}catch(IOException e){
			e.printStackTrace();
		}
	}
}
