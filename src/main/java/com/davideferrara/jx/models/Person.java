package com.davideferrara.jx.models;

import java.time.Period;

import com.davideferrara.jx.exceptions.PersonException;
import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Column(name = "dob", nullable = false)
    private LocalDate dob;

    @Transient // Specifica i campi che non appartengono al database
    private Integer age;

    @Column(name = "gender", nullable = false)
    private Character gender;

    @Column(name = "address", nullable = true)
    private String address;

    @Column(name = "cap", nullable = true)
    private String cap;

    @Column(name = "country", nullable = true)
    private String country;

    public static final int NAME_LEN = 24;
    public static final int CAP_LEN = 5;
    public static final String GENDERS = "MFBUR";
    public static final CharSequence NOT_ALLOWED_CHARS = new StringBuffer("£$%&/()=?ç°@§");

    // Costruttore di default
    public Person() {}

    public Person(String firstName, String lastName, LocalDate dob) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.dob = dob;
    }

    public Person(String firstName, String lastName, LocalDate dob, Character gender) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.dob = dob;
        this.gender = gender;
    }

    public Person(String firstName, String lastName, LocalDate dob, Character gender, String address, String cap, String country) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.dob = dob;
        this.gender = gender;
        this.address = address;
        this.cap = cap;
        this.country = country;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public LocalDate getDob() {
        return dob;
    }

    public void setDob(LocalDate dob) {
        this.dob = dob;
    }

    public Integer getAge() {
        return Period.between(this.dob, LocalDate.now()).getYears();
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Character getGender() {
        return gender;
    }

    public void setGender(Character gender) {
        validateGender(gender);
        this.gender = gender;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCap() {
        return cap;
    }

    public void setCap(String cap) {
        if(cap.length() < CAP_LEN){
            throw new PersonException("Cap must be at least 5 characters!");
        } else if (cap.length() > CAP_LEN) {
            throw new PersonException("Cap must be less than 5 characters!");
        }
        this.cap = cap;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public static void validateName(String firstName) {
        if(firstName == null){
            throw new PersonException("Name field cannot be null!");
        } else if (firstName.length() >= NAME_LEN) {
            throw new PersonException("Name can't be longer than " + NAME_LEN + " characters!");
        }
        else if (firstName.contains(NOT_ALLOWED_CHARS)){
            throw new PersonException("Name can't contain special caracters!");
        }
    }

    public static void validateGender(Character gender) {
        if(gender == null){
            throw new PersonException("Gender field cannot be null!");
        } else if (!GENDERS.contains(gender.toString())) {
            throw new PersonException("Gender must be M, F, B, or U!");
        }
    }

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", dob=" + dob +
                ", age=" + age +
                ", gender=" + gender +
                ", address='" + address + '\'' +
                ", cap='" + cap + '\'' +
                ", country='" + country + '\'' +
                '}';
    }
}
