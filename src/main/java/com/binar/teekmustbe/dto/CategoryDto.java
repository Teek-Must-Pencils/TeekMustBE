package com.binar.teekmustbe.dto;

import com.binar.teekmustbe.entitiy.Category;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;
import net.minidev.json.annotate.JsonIgnore;

import javax.validation.constraints.NotNull;

@Setter
@Getter
@Accessors(chain = true)
@ToString
@EqualsAndHashCode
public class CategoryDto implements Comparable<CategoryDto> {
    @JsonIgnore
    private long id;
    @NotNull
    private String categories;

    public CategoryDto() {

    }

    public CategoryDto(Category category) {
        id = category.getId();
        categories = category.getCategory().name().toLowerCase();
    }

    public int compareTo(CategoryDto o) {
        return Long.compare(this.getId(), o.getId());
    }
}
