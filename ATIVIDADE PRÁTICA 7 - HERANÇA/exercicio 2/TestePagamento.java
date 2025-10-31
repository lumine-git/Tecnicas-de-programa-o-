public class TestePagamento {
    public static void main(String[] args) {
        double valor = 100.0;

        Pagamento cartao = new PagamentoCartao(valor);
        Pagamento dinheiro = new PagamentoDinheiro(valor);

        System.out.println(cartao.emitirRecibo());
        System.out.println(dinheiro.emitirRecibo());
    }
}
