// Name: Tariq Mohammed Areesh / ID: 2237498 / Lab instructor: Waseem Al-Akkad / Assignment: 1
package f102237498p1_easyrent;

public class Customer {
    private String first_name,last_name, email;
    private long credit_Card;
    private int discount_code;

    public Customer(String first_name, String last_name, String email, long credit_Card, int discount_code) {
        this.first_name = first_name;
        this.last_name = last_name;
        this.email = email;
        this.credit_Card = credit_Card;
        this.discount_code = discount_code;
    }

    public String getFirst_name() {
        return first_name;
    }
    
    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }
    
    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getEmail() {
        return email;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }

    public long getCredit_Card() {
        return credit_Card;
    }
    
    public void setCredit_Card(long credit_Card) {
        this.credit_Card = credit_Card;
    }

    public int getDiscount_code() {
        return discount_code;
    }

    public void setDiscount_code(int discount_code) {
        this.discount_code = discount_code;
    }

    @Override
    public String toString() {
        return "Customer{" + "first_name=" + first_name + ", last_name=" + last_name + ", email=" + email + ", credit_Card=" + credit_Card + ", discount_code=" + discount_code + '}';
    }
    
    
}
