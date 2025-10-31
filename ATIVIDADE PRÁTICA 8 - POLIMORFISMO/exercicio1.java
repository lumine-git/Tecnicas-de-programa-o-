class Veiculo {
    public void mover() {
        System.out.println("O veículo está se movendo");
    }
}

class Carro extends Veiculo {
    @Override
    public void mover() {
        System.out.println("O carro está dirigindo");
    }
}

class Bicicleta extends Veiculo {
    @Override
    public void mover() {
        System.out.println("A bicicleta está pedalando");
    }
}

public class TesteVeiculo {
    public static void main(String[] args) {
        Veiculo v1 = new Carro();
        Veiculo v2 = new Bicicleta();

        v1.mover();
        v2.mover();
    }
}
