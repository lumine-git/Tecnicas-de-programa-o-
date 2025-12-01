public class Principal {
    public static void main(String[] args) {

        // Objeto 1
        Carro carro1 = new Carro();
        carro1.cor = "Preto";
        carro1.modelo = "Civic";
        carro1.capacidadeTanque = 50;

        System.out.println("Carro 1 - Modelo: " + carro1.modelo);
        carro1.ligar();
        carro1.acelerar();


        // Objeto 2
        Carro carro2 = new Carro();
        carro2.cor = "Vermelho";
        carro2.modelo = "Onix";
        carro2.capacidadeTanque = 45;

        System.out.println("\nCarro 2 - Cor: " + carro2.cor);
        carro2.acelerar(); // não vai acelerar, pois está desligado
        carro2.ligar();
        carro2.buzinar();
    }
}
