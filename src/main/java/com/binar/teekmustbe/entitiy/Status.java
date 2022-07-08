package com.binar.teekmustbe.entitiy;

import com.binar.teekmustbe.enums.StatusEnum;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

import javax.persistence.*;

@Setter
@Getter
@Entity
@Accessors(chain = true)
@ToString
public class Status {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Enumerated(EnumType.STRING)
    private StatusEnum status;

    public Status() {

    }

    public Status(StatusEnum status) {
        this.status = status;
    }
}
