package com.fitcoach.fitcoach.dtos;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Collection;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ApiResponse<T> {
    Collection<T> items;
    Integer totalItems;
}
