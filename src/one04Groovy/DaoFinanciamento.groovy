package one04Groovy

import groovy.sql.Sql

class DaoFinanciamento {

	def sql
	public DaoFinanciamento(){
		sql = Sql.newInstance('jdbc:mysql://localhost:3306/one04',
				'root', 'root', 'com.mysql.jdbc.Driver')
		println "Conexão Feita"
	}

	def inserirFinanciamento(Financiamento financiamento){
		String sqlstring = """INSERT INTO financiamento(valor, taxa, qtd_parcela, id_client, nome_empresa)
VALUES(
${financiamento.valor},
${financiamento.taxa},
${financiamento.qtdParcela},
${financiamento.client.id},
'${financiamento.empresa}'
)"""
		sql.execute(sqlstring)

		println "Foi"
	}

	def listarClients(){
		println("ID | NOME | CPF | E-MAIL")
		sql.eachRow('select * from client') { cli ->
			println([
				cli.id,
				cli.name,
				cli.cpf,
				cli.email
			])
		}

		sql.close()
		println "Foi"
	}

	public Client logar(String email, String senha){
		//	select id, name, cpf, email, isadmin from client where email = "yagohs@hotmail.com" and senha = "Yago08111999";
		Client client = new Client();
		sql.eachRow('select id, name, cpf, email, isadmin from client where email = "'+email+'" and senha = "'+senha+'"') { cli ->
			client.id = cli.id
			client.name = (String) cli.name
			client.cpf = cli.cpf
			client.email = cli.email
			client.isAdmin = cli.isadmin

		}
		return client
		sql.close()
		println "Foi"
	}
}
