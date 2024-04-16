
public class Car {
    private String id;
    private double quality;
    private int occupancy;

    public Car(int idNum) {
        this.id = generateID(idNum);
        this.quality = generateQuality();
        occupancy = 0;
    }

    public String getId() {
        return id;
    }

    public double getQuality() {
        return quality;
    }

    public int getOccupancy() {
        return occupancy;
    }

    public void setOccupancy(int occupancy) {
        this.occupancy = occupancy;
    }

    private String generateID(int idNum) {
    	return "car" + idNum;
    }
    
    private double generateQuality() {
    	return Math.random() * 2 + 1;
    }
}