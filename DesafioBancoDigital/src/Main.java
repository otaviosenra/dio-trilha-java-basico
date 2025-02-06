public class Main {
    public static void main(String[] args) throws Exception {
        Cliente venilton = new Cliente();
		venilton.setNome("Otavio");
		
		Conta cc = new ContaCorrente(venilton);
		Conta poupanca = new ContaPoupanca(venilton);

		cc.depositar(100);
		cc.transferir(100, poupanca);
		
		cc.imprimirExtrato();
		poupanca.imprimirExtrato();
    }
}
