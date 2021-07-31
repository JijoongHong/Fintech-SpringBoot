package fintech.spring.entity;

import lombok.Data;

@Data
public class Hello {
    final String name;
    /*lombok 쓰면 안해도 됨
    public Hello(String name) {
        this.name = name;
    }*/
}
