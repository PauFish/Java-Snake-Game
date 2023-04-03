import javax.swing.JFrame;

public class GameFrame extends JFrame {

	GameFrame(){
		/*
		GamePanel panel = new GamePanel();
		this.add(panel);
		*/
		this.add(new GamePanel()); //short
		
		this.setTitle("Snake"); //Title
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
		this.pack(); //if we add components a JFrame, pack will fit all the components
		this.setVisible(true);
		this.setLocationRelativeTo(null); //window middle of PC screen
	
	
	}
}
