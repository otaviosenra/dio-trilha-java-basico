
public class ContaCorrente extends Conta {
	private Boolean bloqueada;

	public void bloquearConta() {
        this.bloqueada = true;
        System.out.println("Conta bloqueada.");
    }

    public void desbloquearConta() {
        this.bloqueada = false;
        System.out.println("Conta desbloqueada.");
    }

	public ContaCorrente(Cliente cliente) {
		super(cliente);
		this.bloqueada = false;
	}

	@Override
	public void imprimirExtrato() {
		System.out.println("=== Extrato Conta Corrente ===");
		super.imprimirInfosComuns();
	}

	@Override
    public void sacar(double valor) {
        if (bloqueada) {
            System.out.println("Conta bloqueada! Não é possível realizar o saque.");
        } else {
            super.sacar(valor);
        }
    }

    @Override
    public void transferir(double valor, IConta contaDestino) {
        if (bloqueada) {
            System.out.println("Conta bloqueada! Não é possível realizar a transferência.");
        } else {
            super.transferir(valor, contaDestino);
        }
    }

    @Override
    public void depositar(double valor) {
        if (bloqueada) {
            System.out.println("Conta bloqueada! Não é possível realizar o depósito.");
        } else {
            super.depositar(valor);
        }
    }
	
}
