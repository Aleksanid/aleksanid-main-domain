package ua.aleksanid.maindomain.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@AllArgsConstructor
public class Parameter {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    @JsonIgnore
    private Long id;

    @Enumerated
    @Column(name = "parameter_type", nullable = false)
    private ParameterType parameterType;

    @Column(name = "is_available", nullable = false)
    private Boolean isAvailable;

    @Column(name = "value", nullable = false)
    private String value;

    @ToString.Exclude
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "product_id", nullable = false)
    @JsonIgnore
    private Product product;

}
