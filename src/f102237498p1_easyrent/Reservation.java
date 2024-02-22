// Name: Tariq Mohammed Areesh / ID: 2237498 / Lab instructor: Waseem Al-Akkad / Assignment: 1
package f102237498p1_easyrent;

import java.util.Date;

public class Reservation {
    private String reservation_code, pick_up_location, drop_of_location;
    private Date pick_up, drop_of, date_of_reservation;
    private Customer customer;
    private Car car;
    private Service additional_services;

    public Reservation(String pick_up_location, String drop_of_location, Date pick_up, Date drop_of, String reservation_code, Customer customer, Car car) {
        this.reservation_code = reservation_code;
        this.pick_up_location = pick_up_location;
        this.drop_of_location = drop_of_location;
        this.pick_up = pick_up;
        this.drop_of = drop_of;
        this.customer = customer;
        this.car = car;
    }

    
    public String getReservation_code() {
        return reservation_code;
    }

    public String getPick_up_location() {
        return pick_up_location;
    }

    public String getDrop_of_location() {
        return drop_of_location;
    }

    public Date getPick_up_Date() {
        return pick_up;
    }

    public Date getDrop_of_Date() {
        return drop_of;
    }

    public Date getDate_of_reservation() {
        date_of_reservation = new Date();
        return date_of_reservation;
    }

    public Customer getCustomer() {
        return customer;
    }

    public Car getCar() {
        return car;
    }

    public Service getServices() {
        return additional_services;
    }

    public void setReservation_code(String reservation_code) {
        this.reservation_code = reservation_code;
    }

    public void setPick_up_location(String pick_up_location) {
        this.pick_up_location = pick_up_location;
    }

    public void setDrop_of_location(String drop_of_location) {
        this.drop_of_location = drop_of_location;
    }

    public void setPick_up_date(Date pick_up) {
        this.pick_up = pick_up;
    }

    public void setDrop_of_date(Date drop_of) {
        this.drop_of = drop_of;
    }

    public void setDate_of_reservation(Date date_of_reservation) {
        this.date_of_reservation = date_of_reservation;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public void addServices(Service additional_services) {
        this.additional_services = additional_services;
    }
    
    
}
