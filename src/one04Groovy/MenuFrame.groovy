package one04Groovy

import java.awt.BorderLayout

import javax.swing.JFrame
import javax.swing.event.ListSelectionListener

import groovy.swing.SwingBuilder

class MenuFrame {

	public MenuFrame(Client client){
		Financiamento financiamento = new Financiamento();
		SwingBuilder swing = new SwingBuilder();
		swing.lookAndFeel('nimbus')
		swing.frame(id: 'log', title: 'Menu', size: [550, 530],
		show: true, locationRelativeTo: null, defaultCloseOperation: JFrame.DISPOSE_ON_CLOSE ){
			borderLayout(vgap: 5)

			swing.panel(constraints: BorderLayout.NORTH) {
				tableLayout {

					tr{
						td { label "ID: ${client.id}"  // text property is default, so it is implicit.
						}
					}
					tr{
						td { label "Nome: ${client.name}"  // text property is default, so it is implicit.
						}
					}
					tr{
						td { label "CPF: ${client.cpf}"  // text property is default, so it is implicit.
						}
					}
					tr{
						td { label "E-Mail: ${client.email}"  // text property is default, so it is implicit.
						}
					}
				}
			}

			swing.panel(constraints: BorderLayout.CENTER) {
				tableLayout {
					tr {
						td { label 'Valor Do Financiamento:' }
						td {
							textField financiamento.valor, id: 'streetField', columns: 20
						}
					}

					tr {
						td { label 'Taxa:' }
						td {
							textField id: 'cityField1', columns: 5, financiamento.taxa
						}
					}

					tr {
						td { label 'Quantidade de Parcelas:' }
						td {
							textField id: 'cityField2', columns: 5, financiamento.qtdParcela
						}
					}

					tr {
						td { label 'Empresa:' }
						td {
							textField id: 'cityField3', columns: 20, financiamento.empresa
						}
					}
				}
			}

			swing.panel(constraints: BorderLayout.SOUTH) {
				button text: 'Calcular', actionPerformed: {
					println financiamento.taxa
					println financiamento.qtdParcela
					println financiamento.valor
					println financiamento.empresa

					financiamento.client = client
					
					DaoFinanciamento daoFinanciamento = new DaoFinanciamento();
                    daoFinanciamento.inserirFinanciamento(financiamento);
					
					TabelaPrice tabela = new TabelaPrice(financiamento);

                    TableFrame tableFrame = new TableFrame();
					tableFrame.tableConst(financiamento); 
					swing.dispose();
					
				}
				button text: 'Sair', actionPerformed: { swing.dispose(); }
			}

			bean financiamento,
			valor : bind { Double.parseDouble(streetField.text) },

			taxa : bind { Double.parseDouble(cityField1.text) },

			qtdParcela : bind { Integer.parseInt(cityField2.text) },

			empresa : bind { cityField3.text }
		}
	}
}