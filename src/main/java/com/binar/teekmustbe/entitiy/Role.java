package com.binar.teekmustbe.entitiy;

import com.binar.teekmustbe.enums.Roles;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.persistence.*;

@Setter
@Getter
@Entity
@Accessors(chain = true)
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Enumerated(EnumType.STRING)
    private Roles role;

    public Role() {

    }

    public Role(Roles role) {
        this.role = role;
    }
}
