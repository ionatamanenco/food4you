package ia.ffy.foodforyou.login.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

import java.util.UUID;

@Data
@Entity
@Table(name = "roles")
public class Role {

    @Id
    private String id;

    @Enumerated(EnumType.STRING)
    @Column(length = 20)
    private URole name;

    public Role() {
        id = UUID.randomUUID().toString();
    }
}