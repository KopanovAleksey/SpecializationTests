import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Random;
import java.util.Scanner;

public class ToyStore {
    private ArrayList <Toy> toys;
    public ToyStore(){
        this.toys = new ArrayList<>();
    }

    public ToyStore(ArrayList<Toy> toys) {
        this.toys = toys;
    }

    public ArrayList<Toy> getToys() {
        return toys;
    }

    public void setToys(ArrayList<Toy> toys) {
        this.toys = toys;
    }

    public void addToy(Toy newToy){
        this.toys.add(newToy);
    }

    public int getToyId(){
        return toys.size() + 1;
    }

    public Toy getPrizeToy(){
        Random random = new Random();
        double prizeRate = random.nextDouble(1);
        for(Toy toy : toys.stream().sorted(Comparator.comparing(Toy::getRateDrop)).toList()){
            if(prizeRate < toy.getRateDrop() && toy.getCount() > 0){
                toy.setCount(toy.getCount()-1);
                return toy;
            }
        }
        for(Toy toy : toys.stream().sorted(Comparator.comparing(Toy::getRateDrop).reversed()).toList()){
            if(toy.getCount() > 0){
                toy.setCount(toy.getCount()-1);
                return toy;
            }
        }
        return null;
    }
}
