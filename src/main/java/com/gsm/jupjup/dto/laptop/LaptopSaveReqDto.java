package com.gsm.jupjup.dto.laptop;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.gsm.jupjup.model.Admin;
import com.gsm.jupjup.model.Laptop;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LaptopSaveReqDto {
    @JsonIgnore
    private Admin admin;

    private String laptopSerialNumber;
    private String laptopName;
    private String laptopBrand;
    private String studentName;
    private String classNumber;
    private Long specIdx;

    public Laptop toEntity(){
        return Laptop.builder()
                .admin(this.admin)
                .laptopSerialNumber(this.laptopSerialNumber)
                .laptopName(this.laptopName)
                .laptopBrand(this.laptopBrand)
                .studentName(this.studentName)
                .classNumber(this.classNumber)
                .laptopSpecIdx(this.specIdx)
                .build();
    }
}