package com.example.demoroutingdatasource.member.domain

import javax.persistence.*

@Table(name = "member")
@Entity
class Member(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    val name: String,

    val isActive: Boolean
)
