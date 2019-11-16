package com.mc.order.OrderService.domain;

import com.mc.order.api.models.OrderStatus;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.List;

@Entity
@NoArgsConstructor
@Getter
@Setter
@Table(name = "Orders")
public class OrderEntity {
    public OrderEntity(@NotNull OrderStatus status,
                       @NotNull BigDecimal totalCost,
                       @NotNull Integer totalAmount,
                       @NotNull @NotBlank String userName,
                       @NotNull List<ItemAdditionEntity> items) {
        this.status = status;
        this.totalCost = totalCost;
        this.totalAmount = totalAmount;
        this.userName = userName;
        this.items = items;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    private Long orderId;

    @NotNull
    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    @NotNull
    private BigDecimal totalCost;

    @NotNull
    private Integer totalAmount;

    @NotNull
    @NotBlank
    private String userName;

    @NotNull
    @ElementCollection(targetClass = ItemAdditionEntity.class)
    private List<ItemAdditionEntity> items;

    public void addToList(ItemAdditionEntity itemAdditionEntity) {
        items.add(itemAdditionEntity);
    }
}
