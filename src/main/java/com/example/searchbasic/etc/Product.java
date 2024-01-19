package com.example.searchbasic.etc;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.Where;
import org.jetbrains.annotations.NotNull;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "Product")
//@Where(clause = "deleteYn = 0")
@DynamicInsert
@DynamicUpdate
@NoArgsConstructor
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idx;

    @NotNull
    private String productCode;

    @NotNull
    private String productName;

    @NotNull
    private Long price;

    @NotNull
    private Integer stockQuantity;

    @NotNull
    private String category;

    @Lob
    private String description;

    @NotNull
    private Boolean isSoldOut = false; // 기본값으로 false 설정

//    @NotNull
//    private Boolean deleteYn = false; // 기본값으로 false 설정
//
//    @NotNull
//    private LocalDateTime createdAt = LocalDateTime.now(); // 생성 시 현재 시각으로 설정
//
//    @NotNull
//    private LocalDateTime updatedAt = LocalDateTime.now(); // 생성 시 현재 시각으로 설정

}

