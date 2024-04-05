package com.Employee.user.entity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@Table(name="`user`")
//employee entity table
public class User1 {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long Id;
        @Column(nullable = false)
        private  String firstName;
        @Column(nullable = false)
        private  String lastName;
         @Column(nullable = false,unique = true)
        private  String email;


}


