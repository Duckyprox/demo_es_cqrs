package com.example.demo_cqrs_es.request.cmd;

import com.example.demo_cqrs_es.es.mediator.Request;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDeleteCmd implements Request<Void> {
    private Integer id;
}
