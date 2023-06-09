package com.eviro.assessment.grad001.yolandamabotja.domains;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class AccountProfile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String accountHolderName;
    private String accountHolderSurname;
    private String httpImageLink;

    public AccountProfile(){
        //Default constructor requires a JPA
        //


    }
    public AccountProfile(String accountHolderName , String accountHolderSurname, String httpImageLink){
//        this.id =id;
        this.accountHolderName = accountHolderName;
        this.accountHolderSurname = accountHolderSurname;
        this.httpImageLink = httpImageLink;
    }
    private Long getId(){
        return id;
    }
    public String getAccountHolderName(){
        return accountHolderName;
    }
    public void setAccountHolderName(String accountHolderName){
        this.accountHolderName = accountHolderName;
    }
    public String getAccountHolderSurname(){
        return accountHolderSurname;
    }
    public void setAccountHolderSurname(String accountHolderSurname){
        this.accountHolderSurname = accountHolderSurname;
    }
    public String getHttpImageLink(){
        return httpImageLink;
    }

    public void setHttpImageLink(String httpImageLink) {
        this.httpImageLink = httpImageLink;
    }
}
