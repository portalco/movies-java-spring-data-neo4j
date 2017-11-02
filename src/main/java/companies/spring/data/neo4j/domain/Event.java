package companies.spring.data.neo4j.domain;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import java.util.Date;
import org.neo4j.ogm.annotation.GraphId;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

/**
 *
 * @author teddy
 */

@JsonIdentityInfo(generator=ObjectIdGenerators.PropertyGenerator.class, property="id")
@NodeEntity
public class Event {
    @GraphId
	private Long id;

	private String description;

	private int released;

	private String tagline;

        private Date eventOccuredTimestamp;
        private Date eventReceivedTimestamp;
    
	@Relationship(type = "OCURRED_WITH", direction = Relationship.INCOMING)
	private List<Role> roles = new ArrayList<>();
    
        public Event() {
	}

	public Event(String description, int released) {

		this.description = description;
		this.released = released;
	}
        
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public int getReleased() {
		return released;
	}

	public String getTagline() {
		return tagline;
	}

	public Collection<Role> getRoles() {
		return roles;
	}

	public void addRole(Role role) {
		this.roles.add(role);
	}
        
        public Date getEventOccuredTimestamp() {
        return eventOccuredTimestamp;
    }

    public void setEventOccuredTimestamp(Date eventOccuredTimestamp) {
        this.eventOccuredTimestamp = eventOccuredTimestamp;
    }

    public Date getEventReceivedTimestamp() {
        return eventReceivedTimestamp;
    }

    public void setEventReceivedTimestamp(Date eventReceivedTimestamp) {
        this.eventReceivedTimestamp = eventReceivedTimestamp;
    }
}


