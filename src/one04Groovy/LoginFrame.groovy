package one04Groovy

import java.awt.BorderLayout
import java.awt.event.WindowEvent

import javax.swing.JFrame

import groovy.swing.SwingBuilder

public class LoginFrame{
	public Client login(){
		Client client = new Client();
		SwingBuilder swing = new SwingBuilder()
			// edt method makes sure UI is build on Event Dispatch Thread.
			swing.lookAndFeel 'nimbus'  // Simple change in look and feel.
			swing.frame(id: 'log', title: 'Login', size: [350, 230],
			show: true, locationRelativeTo: null, defaultCloseOperation: JFrame.DISPOSE_ON_CLOSE ) {
				borderLayout(vgap: 5)

				swing.panel(constraints: BorderLayout.CENTER,
				border: compoundBorder([
					emptyBorder(10),
					titledBorder('Entre com o seu login:')
				])) {
					tableLayout {
						tr {
							td { label 'E-Mail:'  // text property is default, so it is implicit.
							}
							td {
								textField client.email, id: 'streetField', columns: 20
							}
						}

						tr {
							td { label 'Senha:' }
							td {
								passwordField id: 'cityField', columns: 20, client.senha
							}
						}
					}

				}

				swing.panel(constraints: BorderLayout.SOUTH) {
					button text: 'Entrar', actionPerformed: {
						DaoClient dao = new DaoClient();
						client = dao.logar(client.email, client.senha);
						Main mainM = new Main();
						mainM.inicio();
                       	swing.dispose();		
						MenuFrame menu = new MenuFrame(client)   			
						}
					button text: 'Sair', actionPerformed: { 
						swing.dispose();
						
					}
				}

				// Binding of textfield's to address object.
				bean client,
				email: bind { streetField.text },

				senha: bind { cityField.text }
				
			
			}
          
		
        return client
	}
}