package com.mc.warehouse.WarehouseService.DataObjects;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class DeltaDto implements Serializable {
    private Long id;
    private Integer delta;
}
