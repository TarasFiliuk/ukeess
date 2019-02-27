package com.test.ukeess.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "employees")
public class Employees implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int employeesId;

    @Column(name = "employees_name")
    private String employeesName;

    @Enumerated(EnumType.STRING)
    private Active active;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "department_Id", referencedColumnName = "id")
    private Departments employeesDepartments;

    public enum Active {
        YES, NO;
        public boolean isActive() {
            return YES.equals(this);
        }

        public boolean isNonActive() {
            return NO.equals(this);
        }
    }
}
