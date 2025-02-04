import java.math.BigDecimal;
import java.util.Scanner;

public class ContaTerminal {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Por favor, digite o número da Agência:");
        String agencia = scanner.nextLine();

        System.out.println("Por favor, digite o número da Conta:");
        int numero = scanner.nextInt();
        scanner.nextLine(); 

        System.out.println("Por favor, digite o nome do Cliente:");
        String cliente = scanner.nextLine();

        BigDecimal saldo = null;
        boolean saldoValido = false;
        while (!saldoValido) {
            System.out.println("Por favor, digite o saldo da Conta:");
            try {
                saldo = scanner.nextBigDecimal();
                saldoValido = true;
            } catch (Exception e) {
                System.out.println("Valor inválido! Digite um número válido (use virgula como separador decimal).");
                scanner.nextLine();
            }
        }

        scanner.close();

        System.out.println("\nOlá " + cliente + ", obrigado por criar uma conta em nosso banco, sua agência é " 
            + agencia + ", conta " + numero + " e seu saldo " + saldo + " já está disponível para saque.");
    }
}