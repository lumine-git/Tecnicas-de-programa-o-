class Conversor {
    public double converter(double celsius) {
        return (celsius * 9 / 5) + 32; // Celsius → Fahrenheit
    }

    public double converter(double km, boolean isKmParaMilha) {
        if (isKmParaMilha) {
            return km * 0.621371;
        } else {
            return km / 0.621371;
        }
    }

    public String converter(String texto) {
        return texto.toUpperCase();
    }
}

public class TesteConversor {
    public static void main(String[] args) {
        Conversor conv = new Conversor();

        System.out.println("Celsius para Fahrenheit: " + conv.converter(25.0));
        System.out.println("Km para Milhas: " + conv.converter(10.0, true));
        System.out.println("Texto em maiúsculas: " + conv.converter("polimorfismo"));
    }
}
