import java.text.DecimalFormat;

public class Emprestimo {
    private static final double TAXA_JUROS = 0.05;
    private static final DecimalFormat FORMATADOR = new DecimalFormat("#0.00");

    @SuppressWarnings("unused")
    private double valor;
    @SuppressWarnings("unused")
    private int parcelas;
    @SuppressWarnings("unused")
    private Conta conta;
    @SuppressWarnings("unused")
    private double juros;

    public Emprestimo(Conta conta, double valor, int parcelas) {
        this.conta = conta;
        this.valor = valor;
        this.parcelas = parcelas;
        this.juros = calcularJuros(valor, parcelas);
    }

    private double calcularJuros(double valor, int parcelas) {
        return valor * TAXA_JUROS * parcelas;
    }

    public static Emprestimo realizarEmprestimo(Conta conta, double valor, int parcelas) {
        Emprestimo emprestimo = new Emprestimo(conta, valor, parcelas);
        conta.adicionarEmprestimo(emprestimo);
        conta.depositar(valor);
        return emprestimo;
    }

    public static void simularEmprestimo(double valor, int parcelas) {
        double juros = valor * TAXA_JUROS * parcelas;
        double totalAPagar = valor + juros;

        System.out.println("=== Simulação de Empréstimo ===");
        exibirDetalhesSimulacao(valor, juros, totalAPagar, parcelas);
    }

    private static void exibirDetalhesSimulacao(double valor, double juros, double total, int parcelas) {
        System.out.println("Valor solicitado: R$ " + FORMATADOR.format(valor));
        System.out.println("Juros totais: R$ " + FORMATADOR.format(juros));
        System.out.println("Valor total a pagar: R$ " + FORMATADOR.format(total));
        System.out.println("Parcelas: " + parcelas);
        System.out.println("Valor de cada parcela: R$ " + FORMATADOR.format(total / parcelas));
    }
}
