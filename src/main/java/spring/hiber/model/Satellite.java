package spring.hiber.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "satellites")
public class Satellite {
    private int id;
    private String name;
    private String specialization;
    private Double weight;
    private Rocket rocket;

    public Satellite() {
    }

    public Satellite(String name, String specialization, Double weight) {
        this.name = name;
        this.specialization = specialization;
        this.weight = weight;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "name", length = 30)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "specialization", length = 30)
    public String getSpecialization() {
        return specialization;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }

    @Basic
    @Column(name = "weight", precision = 0)
    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }

    @ManyToOne
    @JoinColumn(name = "rocket_id", referencedColumnName = "id", nullable = false)
    public Rocket getRocket() {
        return rocket;
    }

    public void setRocket(Rocket rocket) {
        this.rocket = rocket;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Satellite that = (Satellite) o;
        return id == that.id &&
                Objects.equals(name, that.name) &&
                Objects.equals(specialization, that.specialization) &&
                Objects.equals(weight, that.weight);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, name, specialization, weight);
    }

    @Override
    public String toString() {
        return "\n\tSatellite{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", specialization='" + specialization + '\'' +
                ", weight=" + weight +
               // ", rocket=" + rocket +
                '}';
    }
}
