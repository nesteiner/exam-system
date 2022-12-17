package com.example.backend.service

import com.example.backend.model.Admin
import com.example.backend.repository.AdminRepository
import com.example.backend.repository.RoleRepository
import com.example.backend.request.RegisterAdminRequest
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.repository.findByIdOrNull
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service
import org.springframework.security.core.userdetails.User as OtherUser

@Service
class AdminService: UserDetailsService {
    @Autowired
    lateinit var adminRepository: AdminRepository
    @Autowired
    lateinit var roleRepository: RoleRepository

    fun findAll(): List<Admin> {
        return adminRepository.findAll()
    }

    fun findOne(id: Long): Admin? {
        return adminRepository.findByIdOrNull(id)
    }

    fun findOne(name: String): Admin? {
        return adminRepository.findByName(name)
    }

    fun insertOne(data: RegisterAdminRequest): Admin {
        val role = roleRepository.findByName("admin")!!
        val admin = Admin(null, data.name, data.sex, data.telephone, data.email, data.passwordHash, data.cardId, listOf(role))
        return adminRepository.save(admin)
    }

    fun updateOne(admin: Admin): Admin {
        return adminRepository.save(admin)
    }

    fun deleteOne(id: Long) {
        adminRepository.deleteById(id)
    }

    override fun loadUserByUsername(username: String): UserDetails {
        val optionalUser = adminRepository.findByName(username)
        return optionalUser?.let {user ->
            val simpleGrantedAuthorities = user.roles.map {
                SimpleGrantedAuthority(it.name)
            }

            OtherUser(user.name, user.passwordHash, simpleGrantedAuthorities)
        } ?: throw UsernameNotFoundException("no such user")
    }
}