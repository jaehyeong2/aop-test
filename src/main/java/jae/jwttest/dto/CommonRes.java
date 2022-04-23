package jae.jwttest.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@NoArgsConstructor
@Getter
public class CommonRes<T> {
    private T data;
    private HttpStatus status;

    public CommonRes(T data) {
        this.data = data;
    }

    public CommonRes(T data, HttpStatus status) {
        this.data = data;
        this.status = status;
    }
}
