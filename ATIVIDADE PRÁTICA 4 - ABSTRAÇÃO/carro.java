public class Carro {

    String cor;
    String modelo;
    int capacidadeTanque;
    boolean ligado = false; 


    public void ligar() {
        if (!ligado) {
            ligado = true;
            System.out.println("Carro ligado!");
        } else {
            System.out.println("O carro já está ligado.");
        }
    }

    public void desligar() {
        if (ligado) {
            ligado = false;
            System.out.println("Carro desligado!");
        } else {
            System.out.println("O carro já está desligado.");
        }
    }

    public void acelerar() {
        if (ligado) {
            System.out.println("O carro está acelerando!");
        } else {
            System.out.println("Não é possível acelerar. O carro está desligado.");
        }
    }

    public void frear() {
        System.out.println("O carro está freando!");
    }

    public void buzinar() {
        System.out.println("Biiiiiii! ");
    }
}
