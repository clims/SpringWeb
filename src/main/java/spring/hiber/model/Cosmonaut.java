package spring.hiber.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "cosmonauts")
public class Cosmonaut {
    private int id;
    private String name;
    private Integer age;
    private Role role;
    private Set<Rocket> rockets = new HashSet<>();

    public Cosmonaut() {
    }

    public Cosmonaut(String name, Integer age) {
        this.name = name;
        this.age = age;
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
    @Column(name = "name", nullable = true, length = 30)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "age", nullable = true)
    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    @Column(length = 10)
    @Enumerated(EnumType.STRING)
    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    @ManyToMany(cascade = {CascadeType.MERGE,CascadeType.REFRESH, CascadeType.PERSIST}, fetch = FetchType.EAGER)
    @JoinTable(name = "cosmonaut_rocket",
            joinColumns = {@JoinColumn(name = "cosmonaut_id")},
            inverseJoinColumns = {@JoinColumn(name = "rocket_id")})
    public Set<Rocket> getRockets() {
        return rockets;
    }

    public void setRockets(Set<Rocket> rockets) {
        this.rockets = rockets;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cosmonaut that = (Cosmonaut) o;
        return id == that.id &&
                Objects.equals(name, that.name) &&
                Objects.equals(age, that.age);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, name, age);
    }

    @Override
    public String toString() {
        return "\n\tCosmonaut{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", role=" + role +
//                ", rockets=" + rockets +
                '}';
    }
}
