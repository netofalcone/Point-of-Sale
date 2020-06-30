package pos.model;


import javax.persistence.*;

@Entity(name = "permission")
public class Permission {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "deleted")
    private boolean deleted = false;

    public Permission() {}

    public String getName() { return this.name; }

    public String getDescription() { return this.description; }

    public boolean isDeleted() { return this.deleted; }

    public void setDeleted(boolean deleted) { this.deleted = deleted; }

    public Integer getId() { return this.id; }
}
