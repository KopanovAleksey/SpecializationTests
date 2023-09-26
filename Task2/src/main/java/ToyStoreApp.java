public class ToyStoreApp {
    public static void main(String[] args) {
        ToyStoreService toyStoreService = new ToyStoreService();
        toyStoreService.loadToys();
        Toy prizeToy = toyStoreService.getPrize();
        if (prizeToy != null) {
            System.out.println("Congratulations! You won a " + prizeToy.getName());
            toyStoreService.saveToys();
        } else {
            System.out.println("Sorry, no prize toy available.");
        }
//        toyStoreService.addToy();
        toyStoreService.saveToys();
    }
}
