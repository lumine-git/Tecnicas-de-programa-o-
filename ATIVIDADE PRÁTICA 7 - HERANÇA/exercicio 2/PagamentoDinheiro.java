public class PagamentoDinheiro implements Pagamento {
    private double valor;

    public PagamentoDinheiro(double valor) {
        this.valor = valor;
    }

    @Override
    public double calcularPagamento() {
        // Desconto de 10%
        return valor * 0.90;
    }

    @Override
    public String emitirRecibo() {
        double total = calcularPagamento();
        return "=== Recibo de Pagamento em Dinheiro ===\n" +
               "Valor base: R$ " + String.format("%.2f", valor) + "\n" +
               "Desconto (10%): R$ " + String.format("%.2f", valor * 0.10) + "\n" +
               "Total a pagar: R$ " + String.format("%.2f", total) + "\n";
    }
}
