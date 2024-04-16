import java.util.Scanner; 

public class SimulationProcess {
	private Deques<Car> availableCars;
	private Queues<Customer> waitingCustomers;
	private Lists<Car> rentedCars;
	private Lists<Customer> rentedByCustomer;
	
	
	public SimulationProcess() {
		availableCars = new Deques<>();
		waitingCustomers = new Queues<>();
		rentedCars = new Lists<>();
		rentedByCustomer = new Lists<>();
		
		fillingQueues();
	}
	
	private void fillingQueues(){
		Scanner keyboard = new Scanner(System.in);
	    
        System.out.print("Enter available car count, N = ");
        int N = keyboard.nextInt();
        for(int i = 0; i < N; i++) { 
            Car car = new Car(i);
            availableCars.addToBack(car);
        }
    
        System.out.print("Enter customer count, k = ");
        int k = keyboard.nextInt();
        for (int i = 0; i < k; i++) {
            Customer customer = new Customer(i);
            waitingCustomers.enqueue(customer);
        }
	}
	
	public void simulateRentingProcess() {
		int day = 1;
		int allCarsNum = 0;
		int rentedCarsNum = 0;
		Queues<Customer> waitingCustTemp = new 	Queues<>();
		System.out.println("***Day" + day + "***");
		while (!waitingCustomers.isEmpty()) {
				
				if(!availableCars.isEmpty()) {
					Car tempCar = availableCars.removeFront();
					boolean isAccepted = false;
		
					System.out.println(String.format("Current %s quality = %.2f is offering to",tempCar.getId(), tempCar.getQuality()));
					
					int waitingCustomersNum = waitingCustomers.size();
					for(int i = 0; (i < waitingCustomersNum) && !isAccepted; i++) {
					    Customer tempCust = waitingCustomers.dequeue();
						
						if (tempCar.getQuality() >= tempCust.getThreshold()) {
							int rentPeriod = (int) (Math.random()*5 + 1);
							tempCar.setOccupancy(rentPeriod);
							rentedCars.add(tempCar);
							rentedByCustomer.add(tempCust);
							
							System.out.println(String.format("\tCurrent %s threshold = %.2f \t---accepted",
									tempCust.getId(), tempCust.getThreshold()));
							
							isAccepted = true;
							rentedCarsNum++;
						}
						else {
							System.out.println(String.format("\tCurrent %s threshold = %.2f \t---not accepted",
									tempCust.getId(), tempCust.getThreshold()));
							tempCust.setThreshold(tempCust.getThreshold() * 0.9);
							waitingCustTemp.enqueue(tempCust);
						}
					}
					
					while(!waitingCustomers.isEmpty()) {
						Customer customer = waitingCustomers.dequeue();
						waitingCustTemp.enqueue(customer);
					}
					
					while(!waitingCustTemp.isEmpty()) {
						Customer customer = waitingCustTemp.dequeue();
						waitingCustomers.enqueue(customer);
					}
					
					
					if(!isAccepted) {
						System.out.println("\t---not accepted by any customer---");
						availableCars.addToBack(tempCar);
						}
					
					allCarsNum++;
				}
		
				if((availableCars.isEmpty() || (allCarsNum == (availableCars.size() + rentedCarsNum))) && !waitingCustomers.isEmpty())
				{
					day++;
					System.out.println("All cars have been shown.");
					System.out.println("But there are still customers waiting");
					System.out.println("Rented cars: ");
					for(int k = 1; k <= rentedCars.getLength(); k++) {
						System.out.println("\t" + rentedCars.getEntry(k).getId() + " by " + rentedByCustomer.getEntry(k).getId()
								+ " occupancy = " + rentedCars.getEntry(k).getOccupancy());
					}


		
					System.out.print("Available cars: ");
					System.out.println();
					Deques<Car> tempDeque = new Deques<>();
					while (!availableCars.isEmpty()) {
						Car car = availableCars.removeFront();
						tempDeque.addToBack(car);
						System.out.print(car.getId() + " ");
						System.out.println();
					}

					while (!tempDeque.isEmpty()) {
						Car car = tempDeque.removeFront();
						availableCars.addToBack(car);
					}
					if (availableCars.isEmpty() && !waitingCustomers.isEmpty()) {
						System.out.println("No cars available.");
					}
					
					
					System.out.println("***** End of the Day*****");
					System.out.println();
					System.out.println("***Day" + day + "***");
					for(int j = rentedByCustomer.getLength(); j >= 1; j--) {
						int newOccupancy = rentedCars.getEntry(j).getOccupancy() - 1;
						rentedCars.getEntry(j).setOccupancy(newOccupancy);
						if(newOccupancy == 0) {
							Car becomeAvailable=rentedCars.remove(j);
							rentedByCustomer.remove(j);
							availableCars.addToFront(becomeAvailable);
						}
					}
					allCarsNum = 0;
					rentedCarsNum = 0;
				}
			
				
				
			}
			System.out.println("All cars have been shown.");
			System.out.println("All customers have rented a car.");
				
		}
	
	
	
	}
