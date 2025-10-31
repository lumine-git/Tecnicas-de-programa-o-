public class PagamentoCartao implements Pagamento {
    private double valor;

    public PagamentoCartao(double valor) {
        this.valor = valor;
    }

    @Override
    public double calcularPagamento() {
        // Taxa de 5%
        return valor * 1.05;
    }

    @Override
    public String emitirRecibo() {
        double total = calcularPagamento();
        return "=== Recibo de Pagamento com Cartão ===\n" +
               "Valor base: R$ " + String.format("%.2f", valor) + "\n" +
               "Taxa (5%): R$ " + String.format("%.2f", valor * 0.05) + "\n" +
               "Total a pagar: R$ " + String.format("%.2f", total) + "\n";
    }
}
