package br.com.jdsb.enderecoapi.resource.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class StandardError {
    private LocalDateTime timestamp;
    private String error;
    private Integer status;
    private String path;

}
