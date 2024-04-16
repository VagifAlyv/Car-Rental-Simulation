public class Customer{
    private String id;
    private double threshold;

    public Customer(int idNum) {
        this.id = generateID(idNum);
        this.threshold = generateThreshold();
    }
   
    public String getId() {
        return id;
    }

    public double getThreshold() {
        return threshold;
    }

    public void setThreshold(double threshold) {
        this.threshold = threshold;
    }
    
    private String generateID(int idNum) {
    	return "cust" + idNum;
    }
    
    private double generateThreshold() {
    	return Math.random() * 2 + 1;
    }
}

