package com.gsm.jupjup.dto.laptop;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LaptopSaveRequestDto {
    private String laptopSerialNumber;
    private String laptopName;
    private String laptopBrand;
    private String studentName;
    private String classNumber;
    private Long specIdx;
}