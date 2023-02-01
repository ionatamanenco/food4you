package ia.ffy.foodforyou.restaurant;

import ia.ffy.foodforyou.login.model.User;
import ia.ffy.foodforyou.menu.Menu;
import ia.ffy.foodforyou.sitting_tables.SittingTables;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Entity
@Getter
@Setter
public class Restaurant {

    @Id
    @Column(nullable = false, updatable = false)
    private String id;

    @Column(nullable = false)
    private String companyName;

    @Column
    private String companyLogo;

    @Column
    private String companyDescription;

    @OneToOne(
            mappedBy = "userRestaurant",
            fetch = FetchType.LAZY
    )
    private User userRestaurant;

    @OneToOne(
            mappedBy = "restaurantMenu",
            fetch = FetchType.LAZY
    )
    private Menu restaurantMenu;

    @OneToMany(mappedBy = "restaurantTables")
    private Set<SittingTables> restaurantTablesSittingTables;

}
