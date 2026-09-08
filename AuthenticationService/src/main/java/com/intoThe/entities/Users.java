package com.intoThe.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "INTO_USER_DATA")
@AllArgsConstructor
@Getter
@Setter
public class Users extends BaseAuditEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;
    @Column(unique = true)
    private String userName;
    private String userEmail;
    private Boolean isUserActive;
    private String password;
    private Boolean isUserVerified;
//    @OneToMany(cascade = CascadeType.ALL, mappedBy = "userInfo",fetch = FetchType.EAGER)
//    private List<Address> addresses;


    public Users() {
        this(true, false);
    }
    public Users(Boolean isUserActive) {
        this.isUserActive = isUserActive;
    }

    public Users(Boolean isUserActive, Boolean isUserVerified) {
        this.isUserActive = isUserActive;
        this.isUserVerified = isUserVerified;
    }
}
