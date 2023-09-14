package vn.edu.iuh.fit.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

import java.util.List;

@Entity
public class Role {
    @Id
    private String role_id;
    private String role_Name;
    private String description;
    private int status;

    @OneToMany(mappedBy = "role")
    private List<GrantAccess> listGA;
    public Role() {
    }

    public Role(String role_id, String role_Name, String description, int status) {
        this.role_id = role_id;
        this.role_Name = role_Name;
        this.description = description;
        this.status = status;
    }

    public String getRole_id() {
        return role_id;
    }

    public void setRole_id(String role_id) {
        this.role_id = role_id;
    }

    public String getRole_Name() {
        return role_Name;
    }

    public void setRole_Name(String role_Name) {
        this.role_Name = role_Name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Role{" +
                "role_id='" + role_id + '\'' +
                ", role_Name='" + role_Name + '\'' +
                ", description='" + description + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
