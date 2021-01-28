package com.gsm.jupjup.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity

public class Equipment {

    @Id
    private String name;

    private String content;

    private int count;

    @Lob
    private String img_equipment; //BLOB

    public void update(int count) {
        this.count = count;
    }

    public void updateAmount(int count){
        this.count = count;
    }
}