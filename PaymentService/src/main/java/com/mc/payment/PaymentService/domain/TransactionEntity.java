package com.mc.payment.PaymentService.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
@NoArgsConstructor
@Getter
@Setter
@Table(name = "Transactions")
public class TransactionEntity {
    public TransactionEntity(
            @NotNull TransactionStatus status,
            @NotNull @NotBlank String userName,
            @NotNull Long order_id) {
        this.status = status;
        this.userName = userName;
        this.order_id = order_id;
    }

    @Id
    @Setter(AccessLevel.NONE)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private TransactionStatus status;

    @NotNull
    @NotBlank
    private String userName;

    @NotNull
    private Long order_id;
}
