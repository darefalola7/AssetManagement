package com.sprintell.assetmanagement.models;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="groups")
public class Group {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long groupId;

    private String description;
    private String name;

    @ManyToMany(cascade={CascadeType.PERSIST, CascadeType.MERGE,
            CascadeType.DETACH,CascadeType.REFRESH})
    @JoinTable(
            name="personnel_group",
            joinColumns=@JoinColumn(name="groupId"),
            inverseJoinColumns=@JoinColumn(name="personnelId")
    )
    private List<Personnel> personnels;

    public Group() {
    }

    public Long getGroupId() {
        return groupId;
    }

    public void setGroupId(Long groupId) {
        this.groupId = groupId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
