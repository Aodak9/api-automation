package org.bankTransaction.pojo;

/**
 * User class with attributes, methods, getters and setters.
 *
 * @author Emilio Navarro
 */
public class User {
    private int id;
    private String name;
    private String lastName;
    private int accountNumber;
    private double amount;
    private String transactionType;
    private String email;
    private boolean active;
    private String country;
    private String telephone;

    /**
     * User Constructor method.
     */
    public User() {
    }

    /**
     * Constructor method of User which contains values to customize the attributes the class have.
     * @param name String
     * @param lastName String
     * @param accountNumber int
     * @param amount double
     * @param transactionType String, any of: "withdrawal","payment","invoice" or,"deposit"
     * @param email String
     * @param active boolean
     * @param country String
     * @param telephone String
     */
    public User(String name, String lastName, int accountNumber, double amount, String transactionType, String email, boolean active, String country, String telephone) {
        this.name = name;
        this.lastName = lastName;
        this.accountNumber = accountNumber;
        this.amount = amount;
        this.transactionType = transactionType;
        this.email = email;
        this.active = active;
        this.country = country;
        this.telephone = telephone;
    }

    /**
     * getter for the User id that is created automatically from the endpoint.
     * @return the User id
     */
    public int getId() {
        return this.id;
    }

    /**
     * getter for the User name
     * @return the User name
     */
    public String getName() {
        return this.name;
    }

    /**
     * getter for the User lastName
     * @return the User lastName
     */
    public String getLastName() {
        return this.lastName;
    }

    /**
     * getter for the User account number
     * @return the User account number
     */
    public int getAccountNumber() {
        return this.accountNumber;
    }

    /**
     * getter for the User amount
     * @return the User amount
     */
    public double getAmount() {
        return this.amount;
    }

    /**
     * getter for the User transaction type
     * any type of:
     * "withdrawal"
     * "payment"
     * "invoice"
     * "deposit"
     * @return the User transaction type
     */
    public String getTransactionType() {
        return this.transactionType;
    }

    /**
     * getter for the User email
     * @return the User email
     */
    public String getEmail() {
        return this.email;
    }

    /**
     * getter of the User account status
     * @return true if the User account is active, otherwise false
     */
    public boolean isActive() {
        return this.active;
    }

    /**
     * getter for the User country
     * @return the User country
     */
    public String getCountry() {
        return this.country;
    }

    /**
     * getter for the User telephone
     * @return the User telephone
     */
    public String getTelephone() {
        return this.telephone;
    }

    /**
     * Setter to update the User name
     * @param name String
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Setter to update the User lastname
     * @param lastName String
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * Setter to update the User lastname
     * @param accountNumber int
     */
    public void setAccountNumber(int accountNumber) {
        this.accountNumber = accountNumber;
    }

    /**
     * Setter to update the User amount
     * @param amount double
     */
    public void setAmount(double amount) {
        this.amount = amount;
    }

    /**
     * Setter to update the User amount
     * * any type of:
     *      * "withdrawal"
     *      * "payment"
     *      * "invoice"
     *      * "deposit"
     * @param transactionType String
     */
    public void setTransactionType(String transactionType) {
        this.transactionType = transactionType;
    }

    /**
     * Setter to update the User email
     * @param email String
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Setter to update the User email
     * @param active boolean
     */
    public void setActive(boolean active) {
        this.active = active;
    }

    /**
     * Setter to update the User email
     * @param country String
     */
    public void setCountry(String country) {
        this.country = country;
    }

    /**
     * Setter to update the User email
     * @param telephone String
     */
    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }
}


