import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Scanner;

public class ToyStoreService {
    private ToyStore toyStore;
    private String filename;

    public ToyStoreService() {
        this.toyStore = new ToyStore();
        this.filename ="ToyStore.txt";
    }
    public ToyStoreService(String filename) {
        this.toyStore = new ToyStore();
        this.filename = filename ;
    }

    public void saveToys() {
        recountRateDrop();
        try (FileWriter writer = new FileWriter(this.filename)) {
            for (Toy toy : toyStore.getToys()) {
                writer.write(toy.getId() + "," + toy.getName() + "," + toy.getCount() + "," + toy.getRateDrop()+ "\n");
            }
        } catch (IOException e) {
            System.out.println("Failed to save toy data: " + e.getMessage());
        }
    }

    public void loadToys() {
        try (Scanner scanner = new Scanner(new File(this.filename))) {
            toyStore.setToys(new ArrayList<>());
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] toyData = line.split(",");
                int id = Integer.parseInt(toyData[0]);
                String name = toyData[1];
                int quantity = Integer.parseInt(toyData[2]);
                double weight = Double.parseDouble(toyData[3]);
                Toy toy = new Toy(id, name, quantity, weight);
                toyStore.addToy(toy);
            }
        } catch (FileNotFoundException e) {
            System.out.println("Toy data file not found. It will be created !");
            try {
                FileWriter fileWriter = new FileWriter(filename);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

    public void addToy(){
        Scanner scanner = new Scanner(System.in);
        try{
            boolean inStock = false;
            System.out.println("Введите название игрушки:");
            String name = scanner.nextLine();
            System.out.println("Введите количество игрушек:");
            int count = Integer.parseInt(scanner.next());
            scanner.nextLine();
            for (Toy toy: toyStore.getToys()){
                if (name.equals(toy.getName())){
                    toy.setCount(toy.getCount() + count);
                    inStock = true;
                }
            }
            if(!inStock){
                Toy newToy = new Toy(toyStore.getToyId(), name, count, 0);
                toyStore.addToy(newToy);
            }
            recountRateDrop();
            System.out.println("Игрушки успешно добавлены !");
        }catch (RuntimeException e){
            e.printStackTrace();
        }
    }
    public Toy getPrize(){
        Toy prizeToy =  toyStore.getPrizeToy();
        recountRateDrop();
        return prizeToy;
    }

    public void recountRateDrop(){
        int toysAmount = toyStore.getToys().stream()
                                            .mapToInt(Toy::getCount)
                                            .sum();
        for(Toy toy: toyStore.getToys()){
            toy.setRateDrop((double) toy.getCount() /toysAmount);
        }
    }
}
