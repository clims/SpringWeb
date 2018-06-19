package spring.hiber.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "rockets")
public class Rocket {
    private int id;
    private Double deltav;
    private String name;
    private Double price;
    private Double weight;
    private Set<Satellite> satellites = new HashSet<>();
    private Set<Cosmonaut> cosmonauts = new HashSet<>();

    public Rocket() {

    }

    public Rocket(String name, Double deltav, Double price, Double weight) {
        this.deltav = deltav;
        this.name = name;
        this.price = price;
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
    @Column(name = "deltav")
    public Double getDeltav() {
        return deltav;
    }

    public void setDeltav(Double deltav) {
        this.deltav = deltav;
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
    @Column(name = "price")
    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    @Basic
    @Column(name = "weight")
    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }

    @OneToMany(mappedBy = "rocket", cascade = {CascadeType.PERSIST,CascadeType.REFRESH,CascadeType.ALL}, fetch = FetchType.EAGER)
    public Set<Satellite> getSatellites() {
        return satellites;
    }

    public void setSatellites(Set<Satellite> satellites) {
        this.satellites = satellites;
    }

    @ManyToMany(mappedBy = "rockets", fetch = FetchType.EAGER, cascade = {CascadeType.ALL, CascadeType.REFRESH, CascadeType.PERSIST})
    public Set<Cosmonaut> getCosmonauts() {
        return cosmonauts;
    }

    public void setCosmonauts(Set<Cosmonaut> cosmonauts) {
        this.cosmonauts = cosmonauts;
    }

    public void addCosmonaut(Cosmonaut cosmonaut) {
        this.cosmonauts.add(cosmonaut);
        cosmonaut.getRockets().add(this);
    }

    public void removeCosmonaut(Cosmonaut cosmonaut) {
        this.cosmonauts.remove(cosmonaut);
        cosmonaut.getRockets().remove(this);
    }

    public void addSatellite(Satellite satellite) {
        this.satellites.add(satellite);
        satellite.setRocket(this);
    }

    public void removeSatellite(Satellite satellite) {
        this.satellites.remove(satellite);
        satellite.setRocket(null);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Rocket rockets = (Rocket) o;
        return id == rockets.id &&
                Objects.equals(deltav, rockets.deltav) &&
                Objects.equals(name, rockets.name) &&
                Objects.equals(price, rockets.price) &&
                Objects.equals(weight, rockets.weight);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, deltav, name, price, weight);
    }

    @Override
    public String toString() {
        return "Rocket{" +
                "id=" + id +
                ", deltav=" + deltav +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", weight=" + weight +
                ", satellites=" + satellites +
                ", cosmonauts=" + cosmonauts +
                '}';
    }
}
