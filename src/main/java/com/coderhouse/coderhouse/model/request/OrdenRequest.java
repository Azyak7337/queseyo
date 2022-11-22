package com.coderhouse.coderhouse.model.request;

import lombok.*;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrdenRequest {

    @NotNull
    private String userId;
}
