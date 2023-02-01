package ia.ffy.foodforyou.login.model;

import ia.ffy.foodforyou.order.Order;
import ia.ffy.foodforyou.payment_data.PaymentData;
import ia.ffy.foodforyou.restaurant.Restaurant;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Data
@Entity
@Table(name = "users",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = "email")
        })
public class User {

    @NotBlank
    @Id
    private String id;

    @NotBlank
    @Size(max = 20)
    @Column(name = "username")
    private String username;

    @NotBlank
    @Size(max = 20)
    @Column(name = "name")
    private String name;

    @NotBlank
    @Size(max = 20)
    @Column(name = "surname")
    private String surname;

    @NotBlank
    @Size(max = 50)
    @Email
    @Column(name = "email")
    private String email;

    @NotBlank
    @Size(max = 120)
    @Column(name = "password")
    private String password;

    @NotBlank
    @Size(max = 120)
    @Column(name = "subscription_plan")
    @Enumerated(EnumType.STRING)
    private SubscriptionPlanEnum subscriptionPlan;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles = new HashSet<>();

    @OneToOne
    @JoinColumn(name = "user_restaurant_id")
    private Restaurant userRestaurant;

    @OneToMany(mappedBy = "userPaymentInfo")
    private Set<PaymentData> userPaymentInfoPaymentsData;

    @OneToMany(mappedBy = "userOrder")
    private Set<Order> userOrderOrders;

    public User() {
        id = UUID.randomUUID().toString();
    }

    public User(String username, String email, String encode) {
    }
}