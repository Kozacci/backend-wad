package pl.uwm.wateradventure.models.participants;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import pl.uwm.wateradventure.models.global.WaterAdventureChangeMetricEntity;
import pl.uwm.wateradventure.models.participant_courses.ParticipantCourseEntity;
import pl.uwm.wateradventure.models.participants.dtos.ParticipantEntityDTO;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "participants")
@Getter
@Setter
@NoArgsConstructor
public class ParticipantEntity extends WaterAdventureChangeMetricEntity implements UserDetails {

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    private String email;

    private String password;

    @Column(name = "phone_number")
    private String phoneNumber;

    @OneToMany(mappedBy = "participant")
    private List<ParticipantCourseEntity> participantCourses;

    @Enumerated(EnumType.STRING)
    private Role role;

    public ParticipantEntity(String firstName,
                             String lastName,
                             String email,
                             String password,
                             String phoneNumber) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.participantCourses = new ArrayList<>();
        this.role = Role.CLIENT;
    }

    public ParticipantEntityDTO toDto() {
        return ParticipantEntityDTO
                .builder()
                .firstName(this.firstName)
                .lastName(this.lastName)
                .email(this.email)
                .password(this.password)
                .phoneNumber(this.phoneNumber)
                .role(this.role)
                .build();
    }

    //    UserDetails override methods for Security Auth
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.enumValue));
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
