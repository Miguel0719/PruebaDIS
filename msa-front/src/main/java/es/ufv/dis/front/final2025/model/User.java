package es.ufv.dis.front.final2025.model;

import com.google.gson.annotations.SerializedName;
import java.util.UUID;
import java.util.Arrays;

public class User {
    private String id;
    private String name;
    private String email;
    private Address address;
    private PaymentMethod[] paymentMethods;

    // Constructor vacío necesario para Gson
    public User() {
    }

    // Constructor para crear nuevos usuarios
    public User(String name, String email, Address address, PaymentMethod[] paymentMethods) {
        this.id = UUID.randomUUID().toString();
        this.name = name;
        this.email = email;
        this.address = address;
        this.paymentMethods = paymentMethods;
    }

    // Getters y Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public PaymentMethod[] getPaymentMethods() {
        return paymentMethods;
    }

    public void setPaymentMethods(PaymentMethod[] paymentMethods) {
        this.paymentMethods = paymentMethods;
    }

    // Clase interna para Address
    public static class Address {
        private String street;
        private String city;
        private String zipCode;

        public Address() {
        }

        public String getStreet() {
            return street;
        }

        public void setStreet(String street) {
            this.street = street;
        }

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public String getZipCode() {
            return zipCode;
        }

        public void setZipCode(String zipCode) {
            this.zipCode = zipCode;
        }
    }

    // Clase interna para PaymentMethod
    public static class PaymentMethod {
        private String type;
        private String cardNumber;

        public PaymentMethod() {
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getCardNumber() {
            return cardNumber;
        }

        public void setCardNumber(String cardNumber) {
            this.cardNumber = cardNumber;
        }
    }

    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", address=" + address +
                ", paymentMethods=" + Arrays.toString(paymentMethods) +
                '}';
    }
}