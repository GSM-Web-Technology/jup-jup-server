package com.gsm.jupjup.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Laptop extends BaseTimeEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long laptopIdx;

    @Column(unique = true)
    // 노트북 시리얼 번호
    private String laptopSerialNumber;

    @Column
    // 노트북 이름
    private String laptopName;

    @Column
    // 노트북 제조사
    private String laptopBrand;

    private String studentName;
    private String classNumber;

    //laptopSpec 관계 설정
    @ManyToOne
    @JoinColumn(name = "specIdx")
    private LaptopSpec laptopSpec;

    @ManyToOne
    @JoinColumn(name = "adminIdx")
    private Admin admin;

    //update에 사용할 생성자
    public void update(String laptopName, String laptopBrand){
        this.laptopName = laptopName;
        this.laptopBrand = laptopBrand;
    }
}
