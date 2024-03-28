package users;
import factorypattern.*;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

//import java.util.LinkedHashMap;
import java.util.Map;
import org.json.*;

public class Administrator {
    private Map<String, Subscriber> subscribers;
    private Map<String, TariffPlan> promotions;

    public Administrator() {
        this.subscribers = new HashMap<>();
        this.promotions =  new HashMap<>();
    }
    public boolean isPhoneNumberExists(String phoneNumber) {
        return subscribers.containsKey(phoneNumber);
    }
    public TariffPlan isPlanExists(String name) {
        return promotions.get(name);
    }
    public Subscriber getSubscriberByPhoneNumber(String phoneNumber) {

        return subscribers.get(phoneNumber);
    }
    
    public void storeSubscriberToJson(String fileName) {
        try {
            // Create a writer
            BufferedWriter writer = Files.newBufferedWriter(Paths.get(fileName));

            // Convert the subscribers map values to a list of Subscriber objects
            List<Subscriber> subscribersList = new ArrayList<>(subscribers.values());

            // Create ObjectMapper instance
            ObjectMapper mapper = new ObjectMapper().enable(SerializationFeature.INDENT_OUTPUT);

            // Write JSON array to file with pretty printing
            mapper.writeValue(writer, subscribersList);
          

            // Close the writer
            writer.close();
            System.out.println("Subscriber data stored in JSON file: " + fileName);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public Map<String, Subscriber> loadSubscribersFromJson(String fileName) {
        ObjectMapper mapper = new ObjectMapper();
        File file = new File(fileName);

        try {
            // Check if the file is empty
            if (file.length() == 0) {
                System.out.println("File is empty. No data to load.");
                return subscribers;
            }

            // Read JSON content from file and convert it to a list of TariffPlan objects
            List<Subscriber> subscribersL = mapper.readValue(file, new TypeReference<List<Subscriber>>() {});

          
            // Populate the promotions map with TariffPlan objects
            for (Subscriber subscribe : subscribersL) {
                this.subscribers.put(subscribe.getPhoneNumber(), subscribe);
                System.out.println(subscribe.getPhoneNumber());
            }

            System.out.println("Subscribers loaded successfully.");
        } catch (IOException e) {
            System.out.println("Error loading tariff plans from JSON file: " + e.getMessage());
        }

        return this.subscribers;
    }



  
    public void storeTariffPlanToJson(String fileName) {
        try {
            // Create a writer
            BufferedWriter writer = Files.newBufferedWriter(Paths.get(fileName));

            // Convert the tariffPlans map values to a list of TariffPlan objects
            List<TariffPlan> tariffPlansList = new ArrayList<>(promotions.values());

            // Create ObjectMapper instance
            ObjectMapper mapper = new ObjectMapper().enable(SerializationFeature.INDENT_OUTPUT);

            // Write JSON array to file
            mapper.writeValue(writer, tariffPlansList);

            // Close the writer
            writer.close();
            System.out.println("Tariff plans stored in JSON file: " + fileName);
        } catch (Exception ex) {
            System.out.println("Error storing tariff plans to JSON file: " + ex.getMessage());
        }
    }




    public Map<String, TariffPlan> loadTariffPlansFromJson(String fileName) {
        ObjectMapper mapper = new ObjectMapper();
        File file = new File(fileName);

        try {
            // Check if the file is empty
            if (file.length() == 0) {
                System.out.println("File is empty. No data to load.");
                return promotions;
            }

            // Read JSON content from file and convert it to a list of TariffPlan objects
            List<TariffPlan> tariffPlans = mapper.readValue(file, new TypeReference<List<TariffPlan>>() {});

          
            // Populate the promotions map with TariffPlan objects
            for (TariffPlan tariffPlan : tariffPlans) {
                promotions.put(tariffPlan.getName(), tariffPlan);
            }

            System.out.println("Tariff plans loaded successfully.");
        } catch (IOException e) {
            System.out.println("Error loading tariff plans from JSON file: " + e.getMessage());
        }

        return promotions;
    }
    


    public TariffPlan getTariffPlanFromFile(String fileName, String planName) {
        TariffPlan tariffPlan = null;

        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            StringBuilder jsonContent = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                jsonContent.append(line);
            }

            // Parse JSON content
            JSONArray jsonArray = new JSONArray(jsonContent.toString());

            // Iterate over JSON array to find the desired tariff plan
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObj = jsonArray.getJSONObject(i);
                String name = jsonObj.getString("name");

                // Check if the current tariff plan matches the desired name
                if (name.equals(planName)) {
                    double callCostPerMinute = jsonObj.getDouble("callCostPerMinute");
                    double dataCostPerKiloByte = jsonObj.getDouble("dataCostPerKiloByte");
                    double smsCost = jsonObj.getDouble("smsCost");
                    double smsLimit = jsonObj.getDouble("smsLimit");
                    double callMinutesLimit = jsonObj.getDouble("callMinutesLimit");
                    double dataUsageLimit = jsonObj.getDouble("dataUsageLimit");

                    // Create TariffPlan object
                    tariffPlan = new TariffPlan(name, smsCost, callCostPerMinute, dataCostPerKiloByte, smsLimit, callMinutesLimit,dataUsageLimit);
                    break; // Stop iterating once the desired tariff plan is found
                }
            }

            if (tariffPlan == null) {
                System.out.println("Tariff plan not found: " + planName);
            } else {
                System.out.println("Tariff plan loaded successfully: " + planName);
            }
        } catch (IOException | JSONException e) {
            System.out.println("Error loading tariff plans from JSON file: " + e.getMessage());
        }

        return tariffPlan;
    }


    public void registerSubscriber(String name, String surName, String residence, String phoneNumber, Account account) {

        ConcreteSubscriberFactory createSubs = new ConcreteSubscriberFactory ();
        Subscriber subscriber = createSubs.createSubscriber(name, surName, residence, phoneNumber, account);
        subscribers.put(phoneNumber, subscriber);
        loadSubscribersFromJson("Subscribers.json");
        storeSubscriberToJson("Subscribers.json");
        System.out.println("New subscriber registered: " + subscriber.getName());
    }
    public void updateSubscriber(String name, String surName, String residence, String phoneNumber, Account account) {

        ConcreteSubscriberFactory createSubs = new ConcreteSubscriberFactory ();
        Subscriber subscriber = createSubs.createSubscriber(name, surName, residence, phoneNumber, account);
        loadSubscribersFromJson("Subscribers.json");
        subscribers.put(phoneNumber, subscriber);
        storeSubscriberToJson("Subscribers.json");
        System.out.println("Data Updated: " + subscriber.getName());
    }
    
    

    public void registerTariffPlan(String name, double smsCost, double minutesCost,  double dataConnectionCost, double smsLimit, double dataUsageLimit, double callMinutesLimit) {
    	
    	  // Create the new tariff plan
        ConcreteTariffPlanFactory createPlan = new ConcreteTariffPlanFactory();
    	// Add the new tariff plan to the promotions map
        TariffPlan plan = createPlan.createTariffPlan(name, smsCost, minutesCost, dataConnectionCost, smsLimit, callMinutesLimit, dataUsageLimit);
        promotions.put(name, plan);
    	// Load existing tariff plans
        loadTariffPlansFromJson("TariffPlans.json");

        // Store all tariff plans back to the JSON file
        storeTariffPlanToJson("TariffPlans.json");

        System.out.println("New promotion inserted: " + plan.getName());
    }
    public void updateTariffPlan(String name, double smsCost, double minutesCost,  double dataConnectionCost, double smsLimit, double dataUsageLimit, double callMinutesLimit) {
    	
  	  // Create the new tariff plan
      ConcreteTariffPlanFactory createPlan = new ConcreteTariffPlanFactory();
      
      
  	// Add the new tariff plan to the promotions map
      TariffPlan plan = createPlan.createTariffPlan(name, smsCost, minutesCost, dataConnectionCost, smsLimit, callMinutesLimit, dataUsageLimit);
      loadTariffPlansFromJson("TariffPlans.json");
      promotions.put(name, plan);
    
  	// Load existing tariff plans
     

      // Store all tariff plans back to the JSON file
      storeTariffPlanToJson("TariffPlans.json");

      System.out.println("New promotion inserted: " + plan.getName());
  }

    public void showUsagePercentage(Subscriber subscriber) {
        // Logic to calculate and display usage percentages for the subscriber
        System.out.println("Usage percentages for subscriber: " + subscriber.getName());
    }
}