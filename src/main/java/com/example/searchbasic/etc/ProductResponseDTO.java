package com.example.searchbasic.etc;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductResponseDTO {
    private Long productIdx;
    private String productCode;
    private String productName;
    private Long price;
    private Integer stockQuantity;
    private Boolean isSoldOut;
}
