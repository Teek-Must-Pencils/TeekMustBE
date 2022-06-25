package com.binar.teekmustbe.entitiy;

import com.binar.teekmustbe.dto.CategoryDto;
import com.binar.teekmustbe.enums.Categories;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.persistence.*;

@Setter
@Getter
@Entity
@Accessors(chain = true)
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Enumerated(EnumType.STRING)
    private Categories category;

    public Category() {

    }

    public Category(Categories category) {
        this.category = category;
    }

}
