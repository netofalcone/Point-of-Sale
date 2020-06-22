package pos.model;

import javax.persistence.*;
import org.springframework.security.core.GrantedAuthority;
import java.util.List;
import java.util.Set;


@Entity
@Table(name ="role")
@SequenceGenerator(name = "seq_role", sequenceName = "seq_role", allocationSize = 1, initialValue = 1)
public class Role implements GrantedAuthority{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id 
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private Integer id;

	@Column(name = "name_role")
	private String name;

	@Column(name = "description_role")
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

	// TODO: Se apagar isso dá pau, porem é desnecessário já que contem o getName e essa interface não serve de nada. o ideal é colocar um extends Serializable
	public String getAuthority() { /* retorna o nome do papel, acesso ou autorização exemplo ROLE_GERENTE*/

		return this.name;
	}


}
