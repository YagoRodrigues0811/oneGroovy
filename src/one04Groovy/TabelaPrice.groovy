package one04Groovy

import java.text.NumberFormat

class TabelaPrice {
	
	public TabelaPrice(Financiamento financiamento){
		
		Double coeficiente = (Math.pow(1 + (financiamento.taxa / 100), financiamento.qtdParcela) * (financiamento.taxa / 100))/ (Math.pow(1 + (financiamento.taxa / 100), financiamento.qtdParcela) - 1);
		BigDecimal valorPrestacoes = financiamento.valor.multiply(new BigDecimal(coeficiente));
		BigDecimal juros = new BigDecimal(0);
		BigDecimal amortizacao = new BigDecimal(0);
		BigDecimal saldoDevedor =  new BigDecimal(financiamento.valor);
		System.out.println(
		"Num Prestação -------- Valor Prestação ----------------- Juros ----------------- Amortização ------------ Saldo Devedor");
		for (int i = 0; i <= financiamento.qtdParcela; i++) {
			println("----" + i + "---------------------"
			+ NumberFormat.getCurrencyInstance().format(valorPrestacoes.setScale(2, BigDecimal.ROUND_CEILING))
			+ "-------------------"
			+ NumberFormat.getCurrencyInstance().format(juros.setScale(2, BigDecimal.ROUND_HALF_DOWN))
			+ "-------------------"
			+ NumberFormat.getCurrencyInstance().format(amortizacao.setScale(2, BigDecimal.ROUND_CEILING))
			+ "------------"
			+ NumberFormat.getCurrencyInstance().format(saldoDevedor.setScale(2, BigDecimal.ROUND_HALF_DOWN)));

			juros = saldoDevedor.multiply(new BigDecimal(financiamento.taxa / 100));
			amortizacao = valorPrestacoes.subtract(juros);
			saldoDevedor = saldoDevedor.subtract(amortizacao);
		}
		BigDecimal valorPago = valorPrestacoes.multiply(new BigDecimal(financiamento.qtdParcela));
		System.out.println(
		"--------------------------------------------------------------------------------------------------------------------");
		System.out.println("Valor Pago ----------------"
		+ NumberFormat.getCurrencyInstance().format(valorPago.setScale(2, BigDecimal.ROUND_HALF_EVEN))
		+ "--------------------------------------------------------------------------------");
		System.out.println(
		"---------------------------------------------------------------------------------------------------------------------");
		System.out.println(
		"---------------------------------------------------------------------------------------------------------------------");
		System.out.println(
		"---------------------------------------------------------------------------------------------------------------------");
	}



}
