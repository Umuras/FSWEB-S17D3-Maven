package com.workintech.zoo.entity;

import lombok.*;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Component
public class Kangaroo {
    private Integer id;
    private String name;
    private Double height;
    private Double weight;
    private String gender;
    private boolean isAggressive;

    public boolean getIsAggressive()
    {
        return isAggressive;
    }

    public void setIsAggressive(boolean isAggressive)
    {
        this.isAggressive = isAggressive;
    }
}
