package com.lt.domain;

import java.io.Serializable;
import javax.persistence.*;
import lombok.Data;

@Data
@Entity
public class Cita implements Serializable {
    
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column (name = "morning")
    private boolean morning;
    @Column(name = "evening")
    private boolean evening;
    @Column(name = "night")
    private boolean night;

    
}
