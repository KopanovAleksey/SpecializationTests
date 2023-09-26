import java.util.Comparator;
import java.util.Objects;

public class Toy {
    private int id;
    private String name;
    private int count;
    private double rateDrop;

    public Toy(int id, String name, int count, double rateDrop) {
        this.id = id;
        this.name = name;
        this.count = count;
        this.rateDrop = rateDrop;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public double getRateDrop() {
        return rateDrop;
    }

    public void setRateDrop(double rateDrop) {
        this.rateDrop = rateDrop;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Toy toy = (Toy) o;
        return id == toy.id && Double.compare(toy.rateDrop, rateDrop) == 0 && Objects.equals(name, toy.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, count, rateDrop);
    }

    @Override
    public String toString() {
        return "Toy{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }


}
