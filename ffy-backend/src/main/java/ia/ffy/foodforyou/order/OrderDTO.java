package ia.ffy.foodforyou.order;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class OrderDTO {

    private Long id;

    @NotNull
    @Size(max = 255)
    private String totalPrice;

    @NotNull
    private OrderStatusEnum status;

    @Size(max = 255)
    private String userOrder;

    private List<Long> ordersHistory;

}
