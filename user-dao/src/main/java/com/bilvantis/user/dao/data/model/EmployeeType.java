package com.bilvantis.user.dao.data.model;

import com.bilvantis.user.dao.service.util.BaseEntity;
import com.bilvantis.user.dao.service.util.CommonEntityListener;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@EntityListeners(CommonEntityListener.class)
public class EmployeeType extends BaseEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "designation")
    private String designation;

}
