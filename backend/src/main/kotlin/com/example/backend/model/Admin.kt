package com.example.backend.model

import jakarta.persistence.*
import org.hibernate.annotations.GenericGenerator

@Entity(name = "Admin")
class Admin(
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "myid")
    @GenericGenerator(name = "myid", strategy = "com.example.backend.generator.ManualInsertGenerator")
    override var id: Long?,

    @Column(length = 20, nullable = false)
    override var name: String,

    @Column(length = 2, nullable = false)
    var sex: String,

    @Column(length = 11, nullable = false)
    var telephone: String,

    @Column(length = 20, nullable = false)
    var email: String,

    @Column(length = 255, nullable = false)
    override var passwordHash: String,

    @Column(length = 18, nullable = false)
    var cardId: String,

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
        name = "AdminRole",
        joinColumns = [JoinColumn(name = "userid", referencedColumnName = "id")],
        inverseJoinColumns = [JoinColumn(name = "roleid", referencedColumnName = "id")]
    )
    override var roles: List<Role>
): User