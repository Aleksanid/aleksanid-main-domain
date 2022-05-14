package ua.aleksanid.maindomain.models.ids;

import lombok.Data;
import ua.aleksanid.maindomain.models.ParameterType;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Data
@Embeddable
public class ParameterId implements Serializable {
    private Long productId;
    private ParameterType parameterType;
}
