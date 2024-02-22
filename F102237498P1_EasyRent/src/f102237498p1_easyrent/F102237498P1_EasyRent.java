// Name: Tariq Mohammed Areesh / ID: 2237498 / Lab instructor: Waseem Al-Akkad / Assignment: 1
package f102237498p1_easyrent;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;
import java.util.Date;
import java.util.Random;

public class F102237498P1_EasyRent {
    // all methods are below the main method
    public static void main(String[] args) throws FileNotFoundException {
        // creating all files, scanners, and print writers & checking the input and output files whether they exist or not
        File inputCarFile = new File("inputCar.txt");
        File carsInfoFile = new File("carsInfo.txt");
        File reservationInputFile = new File("Reservationinput.txt");
        File reservationStatusFile = new File("ReservationStatus.txt");
        File analysisReportFile = new File("AnalysisReport.txt");
        if(!inputCarFile.exists()){
            System.out.println("inputCar fIle not found");
            System.exit(0);
        }
        if(carsInfoFile.exists()) {
            System.out.println("CarInfo file already exists");
            System.exit(0);
        }
        if(!reservationInputFile.exists()){
            System.out.println("ReservationInput fIle not found");
            System.exit(0);
        }
        if(reservationStatusFile.exists()) {
            System.out.println("ReservationStatus file already exists");
            System.exit(0);
        }
        if(analysisReportFile.exists()) {
            System.out.println("AnalysisReport file already exists");
            System.exit(0);
        }
        Scanner carInput = new Scanner(inputCarFile);
        PrintWriter carInfo = new PrintWriter(carsInfoFile);
        Scanner reservationInput = new Scanner(reservationInputFile);
        PrintWriter reservationStatus = new PrintWriter(reservationStatusFile);
        PrintWriter analysisReport = new PrintWriter(analysisReportFile);
        
        
        // create cars type array
        String carsType[] = new String[carInput.nextInt()];
        carsType = getCarsTypes(carInput, carsType); // a method for assigning cars types to carsType array
        
        // create cars and services array
        Car cars[] = new Car[carInput.nextInt()];
        Service services[] = new Service[carInput.nextInt()];
        
        addCarsAndServices(carInput, cars, services); // a method to read and add cars and services to their arrays from the "inputCar" file
        printCarsinformation(carInfo, cars); // a method to print cars information to the "carsInfo" file
        
        // create reservations array
        Reservation reservations[] = new Reservation[reservationInput.nextInt()];
        
        readReservations(reservationInput, reservations, cars, services); // a method to read reservations from the "ReservationInput" file       
        printReservationStatus(reservationStatus, reservations); // a method to print reservations status to the "ReservationStatus" file
        
        // an array to store "AnalysisReport" information
        String analysisTable [][] = new String[services.length+1][carsType.length+1];
        analysisTable = getAnalysisTableInformation(analysisTable, services, carsType, reservations); // a method to assign the information of the analysis table
        
         printAnalysisReport(analysisReport, analysisTable); // a method to print the information of the analysis table to the "AnalysisReport" file
         
    } // end of main
    
    //----------------------------------------------------------------------------------------------------------------------------------------------------
    public static String [] getCarsTypes(Scanner carInput, String [] carsType) {
        for(int i = 0;i<carsType.length;i++) {
            carsType[i] = carInput.next();
        }
        return carsType;
    }
    //----------------------------------------------------------------------------------------------------------------------------------------------------
    public static void addCarsAndServices(Scanner carInput, Car [] cars, Service [] services) {
        int carIndex = 0;
        int serviceIndex = 0;
            
        while(true) {
            String command = carInput.next();
            if(command.equalsIgnoreCase("AddCar")) {
                    cars[carIndex] = new Car(carInput.next(), carInput.nextInt(), carInput.nextDouble(), carInput.nextBoolean(), carInput.next(), carInput.nextBoolean());            
                    carIndex++;
            }
            else if(command.equalsIgnoreCase("AddService")) {
                    services[serviceIndex] = new Service(carInput.next(), carInput.nextDouble());
                    serviceIndex++;
            }
            else if(command.equalsIgnoreCase("Quit"))
                break;  
        }
    }
    // ---------------------------------------------------------------------------------------------------------------------------------------------------
    public static void printCarsinformation(PrintWriter carInfo, Car [] cars) {
        carInfo.println("--------------- Welcome to Car Renting  Data Base ---------------\n\n\n");
        for(int i = 0; i<cars.length;i++) {
            carInfo.println("\n");
            boolean isManual = cars[i].isTransmission_Manual();
            boolean isConvertible = cars[i].isConvertible();
            carInfo.println("The car Type: " + cars[i].getBrand() + " " + cars[i].getCarType() + ", " + "Year: " + cars[i].getYear_of_construction() + ", " + "Transmission: " + 
                    (isManual ? "Manual":"Automatic") + (isConvertible ? " and Convertible":"") + "\n");
            carInfo.println("------------------------------------------------------\n");
        }
        carInfo.close();
    }
    //----------------------------------------------------------------------------------------------------------------------------------------------------
    public static void readReservations(Scanner reservationInput, Reservation [] reservations, Car [] cars, Service [] services) {
        int reservationIndex = 0;
        while(true) {
            String reserve = reservationInput.next();
            if(reserve.equalsIgnoreCase("Reserve")) {
                
                String carType = reservationInput.next();
                boolean transmission = (reservationInput.next().equalsIgnoreCase("Manual") ? true : false);
                boolean convertible = (reservationInput.next().equalsIgnoreCase("Convertible") ? true : false);
                // check if the car is a avaliable by the method "isCarAvailable"
                if(!isCarAvailable(carType, transmission, convertible, cars)) { // a method to check whether the car is available or not
                    reservationInput.nextLine();
                    reservationIndex++;
                    continue;
                }
                // if the car is available assign the car by the method "checkCar" 
                Car car = checkCar(carType, transmission, convertible, cars); // a method to return the desired car in the reservation
                String pick_up_location = reservationInput.next();
                String drop_off_location = reservationInput.next();
                Date pickUpDate = new Date(Integer.parseInt(reservationInput.next()),Integer.parseInt(reservationInput.next()),Integer.parseInt(reservationInput.next()));
                Date dropOffDate = new Date(Integer.parseInt(reservationInput.next()) ,Integer.parseInt(reservationInput.next()),Integer.parseInt(reservationInput.next()));
                Customer customer= new Customer(reservationInput.next(), reservationInput.next(), reservationInput.next(), reservationInput.nextLong(), reservationInput.nextInt());
                String checkSubmit = reservationInput.next();
                String service = (!checkSubmit.equalsIgnoreCase("submit") ? checkSubmit:null); // check if there is an additional service before the submit word
                String resrvationCode = (customer.getFirst_name().charAt(0))+ "" + (customer.getLast_name().charAt(0)) + '_' + (String.valueOf(new Random().nextInt(999))) + '_' + (String.valueOf(car.getYear_of_construction()));
                
                reservations[reservationIndex] = new Reservation(pick_up_location, drop_off_location, pickUpDate, dropOffDate, resrvationCode, customer, car);
                
                // if there is an additional service add the service to the reservation
                if(service != null)
                    reservations[reservationIndex].addServices(checkService(service, services)); // "checkService" is a method that return the desired service in the reservation
                
                reservationIndex++;
            }
            else if(reserve.equalsIgnoreCase("Quit")) {
                break;
            }
        }
    }
    // --------------------------------------------------------------------------------------------------------------------------------------------------
    public static void printReservationStatus(PrintWriter reservationStatus, Reservation [] reservations) {
        reservationStatus.println("--------------- Welcome to Car Renting  Management System ---------------\n\n\n\n\n\n--------------- Display All System Procedures ---------------\n");
        
        for(int i = 0; i<reservations.length;i++) {
            reservationStatus.println("\n\n\n");
            
            // if there is no car (reservation at the given index = null) then print an appropriate message
            if(reservations[i] == null) {
                reservationStatus.println("SORRY: The reservation is NOT completed \n\nThere is no available Car  \n");
            }
            // else print all the information
            else {
                reservationStatus.println("DONE: The reservation is completed\n");
                reservationStatus.println("******Reservation Refrence number : " + reservations[i].getReservation_code() + "\n");
                reservationStatus.println("******Customer information : Customer Name: " + reservations[i].getCustomer().getFirst_name() + " " + 
                    reservations[i].getCustomer().getLast_name() + ", Email: " + reservations[i].getCustomer().getEmail() + 
                    ", Code: " + reservations[i].getCustomer().getDiscount_code() + "\n");
                reservationStatus.println("******Pick up location : " + reservations[i].getPick_up_location() + "\t******Drop of location : " + reservations[i].getDrop_of_location() + "\n");
                reservationStatus.println("******Pick up date : " + reservations[i].getPick_up_Date().getDate() + "-" + reservations[i].getPick_up_Date().getMonth() + "-" + 
                        reservations[i].getPick_up_Date().getYear() + "\t******Drop of date : " + reservations[i].getDrop_of_Date().getDate() + "-" + reservations[i].getDrop_of_Date().getMonth() + "-" 
                        + reservations[i].getDrop_of_Date().getYear() + "\n");
                reservationStatus.println("******Car information : The car Type: " + reservations[i].getCar().getBrand() + " " + reservations[i].getCar().getCarType() + ", Year: " +
                        reservations[i].getCar().getYear_of_construction() + ", Transmission: " + (reservations[i].getCar().isTransmission_Manual() ? "Manual":"Automatic") + 
                        (reservations[i].getCar().isConvertible() ? " and Convertible":"") + "\n");
                // if there is a service print the service, if not print a space
                reservationStatus.println("******Additional services : Service " + (reservations[i].getServices() != null ? reservations[i].getServices().getServicetype() : " ") + "\n");
                
                
                // get the inital total by multiplying the car's rate by the number of days
                reservationStatus.println("--------------- Invoice Details ---------------\n");
                int numOfDays = reservations[i].getDrop_of_Date().getDate() - reservations[i].getPick_up_Date().getDate();
                double intialTotal = (numOfDays == 0) ? (1 * reservations[i].getCar().calculateFinalPrice()):(numOfDays * reservations[i].getCar().calculateFinalPrice());
                reservationStatus.println(" Number of reserved days: "  + numOfDays + "\n");
                reservationStatus.println(" Intial Total: " + intialTotal + "\n");
                
                // add the payment for the service if any, if not print the total as is
                reservationStatus.println("--------------- Additional Services Price ---------------\n");
                double totalAfterService = (reservations[i].getServices() != null) ? intialTotal + reservations[i].getServices().getServiceprice() : intialTotal;
                reservationStatus.println(" Total After additional Services  : " + totalAfterService + "\n");
                
                // calculate the total after the discount by using the method "getDiscount"
                reservationStatus.println("--------------- Final Payment after Discount ---------------\n");
                reservationStatus.println(" Final Total  : " + (totalAfterService - (totalAfterService * getDiscount(reservations[i].getCustomer().getDiscount_code()))) + "\n"); // "getDiscount" is a method that check the customer code and return the discound value based on the first number of the code
            }
        }
        reservationStatus.close();
    }
    // --------------------------------------------------------------------------------------------------------------------------------------------------
    public static String [][] getAnalysisTableInformation(String [][] analysisTable, Service [] services, String [] carsType, Reservation [] reservations) {
        int serviceIndex = 0;
        int carIndex = 0;
        for(int col = 0; col<analysisTable[0].length;col++){
            for(int row = 0; row<analysisTable.length;row++){
                if(row == 0 && col == 0)
                    analysisTable[row][col] = "CarType#Services";
                else if(col == 0) {
                    analysisTable[row][col] = services[serviceIndex].getServicetype();
                    serviceIndex++;
                }
                else if(row == 0) {
                    analysisTable[row][col] = carsType[carIndex];
                    carIndex++;
                }
                else {
                    // "getValuesOfAnalysis" is a method that return all the values of every carType with its service
                    analysisTable[row][col] = getValuesOfAnalysis(row, col, analysisTable, reservations); 
                }
            }
        }
        return analysisTable;
    }
    // --------------------------------------------------------------------------------------------------------------------------------------------------
    public static void printAnalysisReport(PrintWriter analysisReport, String [][] analysisTable) {
        String [] analysisArrayFirstIndexStringSplit = analysisTable[0][0].split("#");
            analysisReport.println("-------------- Analysis Report ---------------\n\n\n");
            analysisReport.print("             " + analysisArrayFirstIndexStringSplit[0] + "     \t ");
            for(int i = 1; i<analysisTable[0].length;i++) {
                    analysisReport.print(analysisTable[0][i] + "		");
            }
            analysisReport.print("\n\n        " + analysisArrayFirstIndexStringSplit[1] + " \n\n        -----------------------------------------------------");
            for(int row = 1; row<analysisTable.length;row++) {
                analysisReport.println("\n");
                analysisReport.print("\t" + analysisTable[row][0] + "\t\t");
                for(int col=1;col<analysisTable[0].length;col++) {
                    analysisReport.print(analysisTable[row][col] + "\t\t");
                }
                
            }
            analysisReport.close();
    }
     // --------------------------------------------------------------------------------------------------------------------------------------------------
     public static boolean isCarAvailable (String type, boolean transmission, boolean convertible, Car [] cars) {
            for(int i = 0; i<cars.length;i++) {
                if((type.equalsIgnoreCase(cars[i].getCarType())) && (transmission == cars[i].isTransmission_Manual()) && 
                        (convertible == cars[i].isConvertible())) {
                    return true;
                }
            }
         return false;
        }
     // --------------------------------------------------------------------------------------------------------------------------------------------------
     public static Car checkCar (String type, boolean transmission, boolean convertible, Car [] cars) {
            for(int i = 0; i<cars.length;i++) {
                if((type.equalsIgnoreCase(cars[i].getCarType())) && (transmission == cars[i].isTransmission_Manual()) && 
                        (convertible == cars[i].isConvertible())) {
                    return cars[i];
                }
            }
         return null;
        }
     // --------------------------------------------------------------------------------------------------------------------------------------------------
     public static Service checkService (String service, Service [] services) {
         for(int i = 0; i<services.length;i++) {
             if(service.equalsIgnoreCase(services[i].getServicetype())) 
                 return services[i];
         }
        return null;
     }
     // --------------------------------------------------------------------------------------------------------------------------------------------------
     public static double getDiscount(int code) {
         code = code / 100;
        switch (code) {
            case 9, 8, 7 -> {
                return 0.20;
            }
            case 6, 5, 4 -> {
                return 0.15;
            }
            case 3, 2, 1, 0 -> {
                return 0.10;
            }
            default -> {
            }
        }
         
         return 0.0;
     }
     // --------------------------------------------------------------------------------------------------------------------------------------------------
     public static String getValuesOfAnalysis(int row, int col, String [][] analysis, Reservation [] reservation) {
         int count = 0;
         
         String type = analysis[0][col];
         String service = analysis[row][0];
         for(int i = 0; i<reservation.length;i++) {
             if((reservation[i] != null) && (reservation[i].getServices() != null)) {
                 
                  if((type.equalsIgnoreCase(reservation[i].getCar().getCarType())) && 
                     (service.equalsIgnoreCase(reservation[i].getServices().getServicetype()))) {
                 count++;
                }
             }
         }
         return String.valueOf(count);
     }
}
