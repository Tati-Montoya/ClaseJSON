package domain;

public class Amount {
    private final double value;

    public Amount(double value) {
        if(value > 100000) {
            throw new IllegalArgumentException("El valor no puede ser negativo");
        }
        this.value = value;
    }

    public double getValue() {
        return value;
    }
}
