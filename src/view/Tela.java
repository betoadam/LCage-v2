package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

import java.awt.Choice;
import java.awt.BorderLayout;

import javax.swing.JButton;

import java.awt.Point;
import java.awt.Window.Type;
import java.awt.List;
import java.awt.Panel;
import java.awt.Color;
import java.awt.Button;
import java.awt.SystemColor;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;
import java.awt.ScrollPane;
import java.awt.TextField;
import java.awt.TextArea;

import javax.swing.JPanel;

import java.awt.Dialog.ModalExclusionType;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JLayeredPane;
import javax.swing.JTabbedPane;
import javax.swing.JInternalFrame;
import javax.swing.JTextArea;

import controleDeArquivos.*;

import javax.swing.SwingConstants;
import javax.swing.KeyStroke;

import java.awt.event.KeyEvent;
import java.awt.event.InputEvent;

import javax.swing.UIManager;
import javax.swing.JLabel;
import javax.swing.JSeparator;
import javax.swing.JFormattedTextField;
import javax.swing.JTextField;
import javax.swing.UIManager.LookAndFeelInfo;
import javax.swing.JToolBar;
import javax.swing.ImageIcon;
import javax.swing.JScrollBar;

import java.awt.event.MouseWheelListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Font;
import javax.swing.event.AncestorListener;
import javax.swing.event.AncestorEvent;

public class Tela  extends JFrame{
	
	//Variaveis da classe
	private JFrame frame;
	private JMenuBar menuBar;
	private JMenu mnFile; 
	private JMenuItem mntmNovo;
	private JMenuItem mntmAbrirArquivo;
	private JMenuItem mntmSalvar;
	private JButton btnNovo;
	private JButton btnAbrirArquivo;
	private JButton btnCompilador;
	private JPanel panel_1;
	private JPanel panel_3;
	private JPanel panel_2;
	private JScrollPane scrollPane;
	private JScrollPane scrollPane_2;
	private JScrollPane scrollPane_1;
	private JTextArea textArquivo;
	private JTextArea textMsg;
	private JTextArea textArea_1 ;
	private JTextArea textConsole;
	private File file;
	private String campoTexto = null;
	private JButton btnSalvarComo;
	private JScrollPane scrollPane_3;
	private JTextArea textScroll;
	private JMenuItem mntmSalvarComo;
	private btnNovoListener btnNovoL; //Listener para os botoes de novo
	private btnAbrirListener btnAbrirL; //Listener para os botoes de Abrir
	private boolean controleArquivo = false; //Variavel que controla o fluxo se pode ou não compilar
	private int contEnter = 1; //Conta a quantidade de enter

	

	/**
	 * Create the application.
	 */
	public Tela() {
		//Layout da tela
		this.lookFeel();
		
		btnNovoL = new btnNovoListener();
		btnAbrirL = new btnAbrirListener();
		
		setBackground(Color.LIGHT_GRAY);
		setTitle("  Compilador CBola");
		setBounds(100, 100, 1000, 618);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		menuBar = new JMenuBar();
		menuBar.setBackground(Color.LIGHT_GRAY);
		setJMenuBar(menuBar);
		
		mnFile = new JMenu("Arquivo");
		menuBar.add(mnFile);
		
		mntmNovo = new JMenuItem("Novo");
		mntmNovo.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, InputEvent.CTRL_MASK));
		mntmNovo.addActionListener(btnNovoL);
		mnFile.add(mntmNovo);
		
		mntmAbrirArquivo = new JMenuItem("Abrir Arquivo");
		mntmAbrirArquivo.addActionListener(btnAbrirL);
		mntmAbrirArquivo.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, InputEvent.CTRL_MASK));
		mnFile.add(mntmAbrirArquivo);
		
		mntmSalvar = new JMenuItem("Salvar");
		
		//Ação do botao para salvar
		mntmSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//Chama a função para fazer o processo de salvar
				salvar(); 
			}
		});
		mntmSalvar.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.CTRL_MASK));
		mntmSalvar.setHorizontalAlignment(SwingConstants.LEFT);
		mnFile.add(mntmSalvar);
		
		//Ação do botao para salvar como 
		mntmSalvarComo = new JMenuItem("Salvar Como...");
		mntmSalvarComo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//Chama a função para fazer o processo de salvar como
				salvarComo();
			}
		});
		mnFile.add(mntmSalvarComo);
		getContentPane().setLayout(null);
		
		panel_1 = new JPanel();
		panel_1.setBounds(54, 75, 557, 313);
		getContentPane().add(panel_1);
		panel_1.setLayout(null);
		
		textArquivo = new JTextArea();
		textArquivo.setFont(new Font("Consolas", Font.PLAIN, 15));
		textArquivo.setToolTipText("<p></p>");
		
		textArquivo.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent arg0) {
				//Caso o usuario clique com o botao ENTER
				if(arg0.getKeyCode() == 10){
					
					// Verifica nao esta vazio o textArquivo
					if(textArquivo.getText().trim().equals("") != true){
						//Incrementa o contador de linhas
						contEnter++;
						//Chama a função que conta as linhas do textArquivo
						contaLinha(10);
					}
					//Caso esteja vazio
					else{
						textScroll.setText("");
						//Incrementa o contador de linhas
						contEnter++;
						//Coloca o numero de linhas
						for(int i =1 ; i < contEnter; i++){
							textScroll.append(i + "\n");
						}
						textScroll.append(contEnter+"");
					}
				}
				//Caso o usuario clique BACKSPACE
				if(arg0.getKeyCode() == 8){
					// Verifica nao esta vazio o textArquivo
					if(textArquivo.getText().trim().equals("") != true){
						//Decrementa o contador de linhas
						contEnter--;
						//Chama a função que conta as linhas do textArquivo
						contaLinha(8);
					}
					//Caso esteja vazio
					else{
						textScroll.setText("");
						//Incrementa o contador de linhas
						contEnter--;
						//Coloca o numero de linhas
						for(int i =1 ; i < contEnter; i++){
							textScroll.append(i + "\n");
						}
						textScroll.append(contEnter+"");	
						
						//Caso o contador seja < 1
						if(contEnter < 1){
							contEnter =1;
							textScroll.setText("1");
						}
					}
				}
				//Caso o usuario clique para BAIXO ou para CIMA
				//A rola o scroll dos dois scrollPane, o do arquivo e o de linha
				if(arg0.getKeyCode() == 38 || arg0.getKeyCode() == 40){
					Point point = scrollPane_2.getViewport().getViewPosition();  
					scrollPane_3.getViewport().setViewPosition(point);
				}
			}
		});
		scrollPane_2 = new JScrollPane ();		
		scrollPane_2.setBounds(0, 0, 557, 313);
		scrollPane_2.setViewportView(textArquivo);
		textArquivo.setBounds(10, 55, 615, 285);
		panel_1.add(scrollPane_2);
		
		panel_3 = new JPanel();
		panel_3.setBounds(621, 75, 353, 313);
		getContentPane().add(panel_3);
		panel_3.setLayout(null);
		
		scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(0, 0, 353, 313);
		panel_3.add(scrollPane_1);
		
		textMsg = new JTextArea();
		textMsg.setForeground(new Color(0, 128, 0));
		textMsg.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 15));
		textMsg.setEditable(false);
		scrollPane_1.setViewportView(textMsg);
		
		panel_2 = new JPanel();
		panel_2.setBounds(10, 424, 964, 135);
		getContentPane().add(panel_2);
		panel_2.setLayout(null);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 0, 964, 135);
		panel_2.add(scrollPane);
		
		textConsole = new JTextArea();
		textConsole.setForeground(SystemColor.textHighlight);
		textConsole.setFont(new Font("Consolas", Font.BOLD | Font.ITALIC, 16));
		scrollPane.setViewportView(textConsole);
		textConsole.setEditable(false);
		
		JLabel lblArquivo = new JLabel("Arquivo");
		lblArquivo.setBounds(54, 55, 46, 14);
		getContentPane().add(lblArquivo);
		
		JLabel lblMensagem = new JLabel("Arvore Sint\u00E1tica");
		lblMensagem.setBounds(630, 55, 97, 14);
		getContentPane().add(lblMensagem);
		
		JLabel lblConsole = new JLabel("Console");
		lblConsole.setBounds(20, 399, 80, 14);
		getContentPane().add(lblConsole);
		
		btnNovo = new JButton("");
		btnNovo.setIcon(new ImageIcon(Tela.class.getResource("/javax/swing/plaf/metal/icons/ocean/file.gif")));
		btnNovo.setBounds(10, 11, 28, 21);
		getContentPane().add(btnNovo);
		btnNovo.addActionListener(btnNovoL);
		btnNovo.setBackground(SystemColor.activeCaptionBorder);
		
		btnAbrirArquivo = new JButton("");
		btnAbrirArquivo.setIcon(new ImageIcon(Tela.class.getResource("/javax/swing/plaf/metal/icons/ocean/directory.gif")));
		btnAbrirArquivo.setBounds(49, 11, 28, 21);
		getContentPane().add(btnAbrirArquivo);
		btnAbrirArquivo.setBackground(Color.LIGHT_GRAY);
		
		JButton btnSalvar = new JButton("");
		btnSalvar.setIcon(new ImageIcon(Tela.class.getResource("/javax/swing/plaf/metal/icons/ocean/floppy.gif")));
		btnSalvar.setBounds(84, 11, 28, 21);
		getContentPane().add(btnSalvar);
		//Ação do botao para salvar
		btnSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				salvar();
			}		
		});
		btnSalvar.setBackground(SystemColor.activeCaptionBorder);
		btnCompilador = new JButton("");
		btnCompilador.setIcon(new ImageIcon(Tela.class.getResource("/com/sun/javafx/scene/web/skin/Bold_16x16_JFX.png")));
		btnCompilador.setBounds(813, 11, 58, 36);
		getContentPane().add(btnCompilador);
		btnCompilador.setBackground(Color.LIGHT_GRAY);
		
		btnSalvarComo = new JButton("");
		//Ação do botao para salvar como
		btnSalvarComo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				salvarComo();
			}
		});
		btnSalvarComo.setIcon(new ImageIcon(Tela.class.getResource("/javax/swing/plaf/metal/icons/ocean/hardDrive.gif")));
		btnSalvarComo.setBounds(117, 11, 28, 21);
		getContentPane().add(btnSalvarComo);
		
		scrollPane_3 = new JScrollPane();
		scrollPane_3.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);  
		scrollPane_3.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);
		scrollPane_3.setBounds(10, 75, 34, 313);
		getContentPane().add(scrollPane_3);
		
		textScroll = new JTextArea();
		textScroll.setFont(new Font("Consolas", Font.PLAIN, 15));
		textScroll.setEnabled(false);
		textScroll.setEditable(false);
		textScroll.setText("1");
		scrollPane_3.setViewportView(textScroll);
		btnAbrirArquivo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//Verifica se o arquivo esta vazio
				if(textArquivo.getText().equals("") != true){
					int resp = JOptionPane.showConfirmDialog(null, "Salvar altera\u00e7\u00f5es? Caso não salve o arquivo sera perdido!!");
					
					//Para salvar as alterações
					if(resp ==0){
						 //Salvar um novo arquivo se ele n existir
	              	    if(file == null)
	              	    {
	              	     	salvarComo();
	              	    }else //Se o arquivo ja existir salvar nele
	              	    {
	              	      	salvar();
	              	    }
					} 
				}
				JFileChooser fc = new JFileChooser();
				int returnVal = fc.showOpenDialog(null);
				
		        if (returnVal == JFileChooser.APPROVE_OPTION) {
		            file = fc.getSelectedFile().getAbsoluteFile();
		            //Instancia a classe ReadFile que cuida de ler o arquivo
		            ReadFile read = new ReadFile();
		            //Abre o arquivo
		            read.openFile(file);
		            textArquivo.setText("");
		            campoTexto = new String();
		            campoTexto = read.readRecords();	            
		    		textArquivo.append(campoTexto);
		    		controleArquivo = true;
		    		textScroll.setText("");
		    		contEnter = read.getContLinhaArquivo();
		    		contEnter++;
		    		textScroll.append(read.numLine());
		    		read.closeFile();
		        } 
			}
		});
		
		scrollPane_2.addMouseWheelListener(new MouseWheelListener() {
			public void mouseWheelMoved(MouseWheelEvent arg0) {
				Point point = scrollPane_2.getViewport().getViewPosition();  
				scrollPane_3.getViewport().setViewPosition(point); 
			}
		});
		
		
		
		/*====================================================*/
		
	}
	
	public void lookFeel() {//Altera o tipo de interface que ira exibir
		  try {
			  for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
				  if ("Nimbus".equals(info.getName())) {
					  UIManager.setLookAndFeel(info.getClassName());
				  }
			  }
		  }catch (Exception e) {
		   JOptionPane.showMessageDialog(null,
		     "Nao foi possivel executar o nimbus");
		  }
	}
	
	/*=== Funçao que retorna um o botao do compilador ===*/
	public JButton getCompilar() {
		return btnCompilador;
	}

	public JTextArea getTextArquivo(){
		return textArquivo;
	}
	
	/*=== Funçao para adicionar msg no console ===*/
	public void setConsole(String msg){
		textConsole.append(msg);
	}
	
	/*=== Funçao para adicionar msg no campo de msg ===*/
	public void setMsg(String msg){
		textMsg.append(msg);
	}
	
	/*=== Funçao que retorna o arquivo que esta sendo editado ===*/
	public File getFile(){
		return file;
	}
	
	public void setFile(File file){
		this.file = file;
	}
	
	public String getCampoTexto(){
		return campoTexto;
	}
	
	public void setCampoTexto(String campoTexto){
		this.campoTexto = campoTexto;
	}
	
	public boolean getControleArquivo(){return controleArquivo;}
	
	public void setControleArquivo(boolean controle){
		this.controleArquivo = controle;
	}
	
	public void setVazioConsoleMsg(){
		textConsole.setText("");
		textMsg.setText("");
	}
	
	//Salvar se o arquivo ja existe
	public void salvar(){
		campoTexto = new String();	            
		controleArquivo = true;
		
		if(file !=null){
			campoTexto = new String();	            
    		controleArquivo = true;
			CreateFile create = new CreateFile();
			
			create.openFile(file);
			create.addRecords(textArquivo.getText());
			create.closeFile();
			
			campoTexto = textArquivo.getText();
		}else{
			salvarComo();
		}	
	}
	
	//Salvar se o arquivo ainda não existir
	public void salvarComo(){
		// Abre o negocio para selecionar
		JFileChooser jc = new JFileChooser();
		jc.setFileSelectionMode(JFileChooser.FILES_ONLY);
		int i= jc.showSaveDialog(null);
		
		//Se o retorno for == 1 quer dizer que o usuario apertou em cancelar 
		// Se não, ele escreveu o nome do arquivo, ou selecionou o arquivo que queira salvar
		if(i!=1) {
			// Pega o arquivo selecionado ou novo
			file = jc.getSelectedFile();
			campoTexto = new String();	            
    		controleArquivo = true;
			CreateFile create = new CreateFile();
			
			create.openFile(file);
			create.addRecords(textArquivo.getText());
			create.closeFile();
			//Fecha o arquivo
			
			campoTexto = textArquivo.getText();
		}
	}
	
	//Funcao para contar linhas
	public void contaLinha(int key){
		String[] separa = textArquivo.getText().split("\n");
		textScroll.setText("");
		
		//Esse if serve para caso tenha conteudo, mas o usuario deu muitos enter
		if(contEnter >= separa.length){
			for(int i =1 ; i < contEnter; i++){
				textScroll.append(i + "\n");
			}
			textScroll.append(contEnter+"");
		}else{
			for(int i =1 ; i < separa.length; i++){
				textScroll.append(i + "\n");
			}
			textScroll.append(separa.length+"");
			if(key ==10) textScroll.append((separa.length+1) + "");
			contEnter = separa.length;
		}
		
	}
	
	class btnNovoListener implements ActionListener{
		public void actionPerformed(ActionEvent arg0) {
			if(textArquivo.getText().equals("") != true){
				 //Salvar um novo arquivo se ele n existir
				int resp = JOptionPane.showConfirmDialog(null, "Salvar altera\u00e7\u00f5es? Caso não salve o arquivo sera perdido!!");
				if(resp ==0){
					if(file == null)
	          	    {
	          	     	salvarComo();
						
	          	    }else //Se o arquivo ja existir salvar nele
	          	    {
	          	      	salvar();
	          	    }
	          	    textArquivo.setText("");
	          	  	textScroll.setText("1");
	          	  	contEnter = 1;
				}else{
					textArquivo.setText("");
					textScroll.setText("1");
					contEnter = 1;
				}
	      	   
			}else{
				textArquivo.setText("");
				textScroll.setText("1");
				contEnter = 1;
			}
		}
	}
	
	class btnAbrirListener implements ActionListener{
		public void actionPerformed(ActionEvent arg0) {
			if(textArquivo.getText().equals("") != true){
				 //Salvar um novo arquivo se ele n existir
          	    if(file == null)
          	    {
          	     	salvarComo();
					
          	    }else //Se o arquivo ja existir salvar nele
          	    {
          	      	salvar();
          	    }
          	    textArquivo.setText("");
          	  	textScroll.setText("1");
          	  	contEnter = 1;
			}else{
				textArquivo.setText("");
				textScroll.setText("1");
				contEnter = 1;
			}
		}
	}

}

