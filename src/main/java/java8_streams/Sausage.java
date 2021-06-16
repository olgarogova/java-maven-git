package java8_streams;

import java.util.Objects;

public class Sausage {
    private String type;
    private int weight;
    private long cost;

    public Sausage(String type, int weight, long cost) {
        this.type = type;
        this.weight = weight;
        this.cost = cost;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public long getCost() {
        return cost;
    }

    public void setCost(long cost) {
        this.cost = cost;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Sausage)) return false;
        Sausage sausage = (Sausage) o;
        return getWeight() == sausage.getWeight() &&
                getCost() == sausage.getCost() &&
                Objects.equals(getType(), sausage.getType());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getType(), getWeight(), getCost());
    }

    @Override
    public String toString() {
        return "sausage{" +
                "type='" + type + '\'' +
                ", weight=" + weight +
                ", cost=" + cost +
                '}';
    }
}
