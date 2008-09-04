package ${package}.domain.entity;

import org.domdrides.entity.UuidEntity;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
public class Widget extends UuidEntity
{
    private static final long serialVersionUID = 1L;

    private String name;

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }
}