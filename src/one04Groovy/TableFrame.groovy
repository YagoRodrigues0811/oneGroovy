package one04Groovy

import java.text.NumberFormat

import groovy.swing.SwingBuilder

class TableFrame{

	public tableConst(Financiamento financiamento){

		Double coeficiente = (Math.pow(1 + (financiamento.taxa / 100), financiamento.qtdParcela) * (financiamento.taxa / 100))/ (Math.pow(1 + (financiamento.taxa / 100), financiamento.qtdParcela) - 1);
		BigDecimal valorPrestacoes = financiamento.valor.multiply(new BigDecimal(coeficiente));
		BigDecimal juros = new BigDecimal(0);
		BigDecimal amortizacao = new BigDecimal(0);
		BigDecimal saldoDevedor =  new BigDecimal(financiamento.valor);

		ArrayList<String[]> data = new ArrayList<String[]>();
		//		def data;
		def datas
		for (int i = 0; i <= financiamento.qtdParcela; i++) {
			datas = [prest : i,
			valorPrest : NumberFormat.getCurrencyInstance().format(valorPrestacoes.setScale(2, BigDecimal.ROUND_CEILING)),
			juros : NumberFormat.getCurrencyInstance().format(juros.setScale(2, BigDecimal.ROUND_HALF_DOWN)),
			amort : NumberFormat.getCurrencyInstance().format(amortizacao.setScale(2, BigDecimal.ROUND_CEILING)),
			sald : NumberFormat.getCurrencyInstance().format(saldoDevedor.setScale(2, BigDecimal.ROUND_HALF_DOWN))
		]
			data.add(datas)
			datas = null;
			juros = saldoDevedor.multiply(new BigDecimal(financiamento.taxa / 100));
			amortizacao = valorPrestacoes.subtract(juros);
			saldoDevedor = saldoDevedor.subtract(amortizacao);
		}


		//		def data = [[prest: 1 , valorPrest:256, juros: 255, amort: 8, sald: 1],
		//			[first:'zxcv', last:'tyui'],
		//			[first:'ghjk', last:'bnm']]

		SwingBuilder swing = new SwingBuilder()
		swing.lookAndFeel('nimbus')
		def frame = swing.frame(title:'Tabela Price', size: [1000, 1000], locationRelativeTo: null,){
			scrollPane {
				table {
					tableModel( list : data ) {
						propertyColumn(header:'Prestação', propertyName:'prest')
						propertyColumn(header:'Valor Prestação', propertyName:'valorPrest')
						propertyColumn(header:'Juros', propertyName:'juros')
						propertyColumn(header:'Amortização', propertyName:'amort')
						propertyColumn(header:'Saldo Devedor', propertyName:'sald')
					}
				}
			}
		}
		frame.pack()
		frame.show()
	}
}