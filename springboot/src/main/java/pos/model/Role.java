package pos.model;

import javax.persistence.*;

import java.io.Serializable;
import java.util.Set;


@Entity(name ="role")
@SequenceGenerator(name = "seq_role", sequenceName = "seq_role", allocationSize = 1, initialValue = 1)
public class Role implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id 
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private Integer id;

	@Column(name = "name")
	private String name;

	@Column(name = "description")
	private String description;

	private boolean deleted = false;

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "role_permission", joinColumns = @JoinColumn(name = "id_role"), inverseJoinColumns = @JoinColumn(name = "id_permission"))
	private Set<Permission> permissions;

	public String getName() { return this.name; }

	public String getDescription() { return this.description; }

	public boolean isDeleted() { return this.deleted; }

	public void setDeleted(boolean deleted) { this.deleted = deleted; }

	public Integer getId() { return this.id; }

	public Set<Permission> getPermissions() { return this.permissions; }

	public void setPermissions(Set<Permission> permissions) { this.permissions = permissions; }

}
