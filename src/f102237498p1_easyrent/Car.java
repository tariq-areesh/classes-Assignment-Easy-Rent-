// Name: Tariq Mohammed Areesh / ID: 2237498 / Lab instructor: Waseem Al-Akkad / Assignment: 1
package f102237498p1_easyrent;
public class Car {
    private String brand, carType;
    private int year_of_construction;
    private double car_rate;
    private boolean transmission_Manual, convertible;

    public Car(String brand,  int year_of_construction, double car_rate, boolean transmission_Manual, String carType, boolean convertible) {
        this.brand = brand; 
        this.year_of_construction = year_of_construction;
        this.car_rate = car_rate;
        this.transmission_Manual = transmission_Manual;
        this.carType = carType;
        this.convertible = convertible;
        
    }
    
    
    @Override
    public String toString() {
        return "Car{" + "brand=" + brand + ", carType=" + carType + ", year_of_construction=" + year_of_construction + ", car_rate=" + car_rate + ", transmission_Manual=" + transmission_Manual + ", convertible=" + convertible + '}';
    }
 
    public void setBrand(String brand) {
        this.brand = brand;
    }
    
    public String getBrand() {
        return brand;
    }

    public void setCarType(String carType) {
        this.carType = carType;
    }
    
    public String getCarType() {
        return carType;
    }

    public void setYear_of_construction(int year_of_construction) {
        this.year_of_construction = year_of_construction;
    }

    public int getYear_of_construction() {
        return year_of_construction;
    }
    
    public void setCar_rate(double car_rate) {
        this.car_rate = car_rate;
    }
    
    public double getCar_rate() {
        return car_rate;
    }

    public void setTransmission_Manual(boolean transmission_Manual) {
        this.transmission_Manual = transmission_Manual;
    }
    
    public boolean isTransmission_Manual() {
        return transmission_Manual;
    }

    public void setConvertible(boolean convertible) {
        this.convertible = convertible;
    }
   
    public boolean isConvertible() {
        return convertible;
    }
    public double calculateFinalPrice() {
        if(this.carType.equalsIgnoreCase("Luxury")) 
            return (this.car_rate * 0.1) + this.car_rate;
        else
            return getCar_rate();
    }
}
