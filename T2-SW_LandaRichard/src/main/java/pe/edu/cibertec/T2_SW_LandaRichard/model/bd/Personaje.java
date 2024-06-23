package pe.edu.cibertec.T2_SW_LandaRichard.model.bd;

import jakarta.persistence.*;
import lombok.Data;
import java.util.Date;
import java.util.List;

@Data
@Entity
@Table(name = "Personaje")

public class Personaje {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idPersonaje;

    @Column(name = "NomPersonaje", nullable = false, length = 50)
    private String nomPersonaje;

    @Column(name = "ApePersonaje", nullable = false, length = 50)
    private String apePersonaje;

    @Column(name = "FechNacPersonaje", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date fechNacPersonaje;

    @OneToMany(mappedBy = "personaje", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ProgramaTv> programasTv;

}
