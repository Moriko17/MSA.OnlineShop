package com.mc.order.api.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ItemDto {
    private Long itemId;
    private Integer amount;
    private String userName;
}
