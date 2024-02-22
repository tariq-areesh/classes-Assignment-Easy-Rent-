// Name: Tariq Mohammed Areesh / ID: 2237498 / Lab instructor: Waseem Al-Akkad / Assignment: 1
package f102237498p1_easyrent;

public class Service {
    private String servicetype;
    private double serviceprice;

    public Service(String servicetype, double serviceprice) {
        this.servicetype = servicetype;
        this.serviceprice = serviceprice;
    }
    

    public String getServicetype() {
        return servicetype;
    }

    public double getServiceprice() {
        return serviceprice;
    }

    public void setServicetype(String servicetype) {
        this.servicetype = servicetype;
    }

    public void setServiceprice(double serviceprice) {
        this.serviceprice = serviceprice;
    }

    @Override
    public String toString() {
        return "Service{" + "servicetype=" + servicetype + ", serviceprice=" + serviceprice + '}';
    }
    
    
}
