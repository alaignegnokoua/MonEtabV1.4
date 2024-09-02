package ci.digitalacademy.MonEtabV14.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Student extends Person {

    @Column(unique=true, nullable=false)
    private String matricule;

    @Column(name = "phonr_number_father", nullable = false)
    private String phoneNumberFather;

    @OneToMany(mappedBy = "student")
    private Set<Absence> absences;
}
