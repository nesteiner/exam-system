package com.example.backend.service

import com.example.backend.model.Teacher
import com.example.backend.repository.RoleRepository
import com.example.backend.request.UpdateTeacherRequest
import com.example.backend.repository.TeacherRepository
import com.example.backend.request.RegisterTeacherRequest
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.repository.findByIdOrNull
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service
import org.springframework.security.core.userdetails.User as OtherUser

@Service
class TeacherService: UserDetailsService {
    @Autowired
    lateinit var teacherRepository: TeacherRepository
    @Autowired
    lateinit var roleRepository: RoleRepository

    fun insertOne(data: RegisterTeacherRequest): Teacher {
        val role = roleRepository.findByName("teacher")!!
        val teacher = Teacher(null, data.name, data.institute, data.sex, data.telephone, data.email, data.passwordHash,
            data.cardId, data.type, listOf(role))

        return teacherRepository.save(teacher)
    }

    fun deleteOne(id: Long) {
        teacherRepository.deleteById(id)
    }

    fun updateOne(data: UpdateTeacherRequest): Teacher {
        val ifteacher = teacherRepository.findByIdOrNull(data.id)!!
        val teacher = Teacher(
            data.id,
            data.name,
            data.institute,
            data.sex,
            data.telephone,
            data.email,
            data.passwordHash,
            data.cardId,
            data.type,
            ifteacher.roles
        )

        return teacherRepository.save(teacher)
    }

    fun findOne(id: Long): Teacher? {
        return teacherRepository.findByIdOrNull(id)
    }

    fun findOne(name: String): Teacher? {
        return teacherRepository.findByName(name)
    }

    fun findAll(): List<Teacher> {
        return teacherRepository.findAll()
    }

    fun findAll(pageable: Pageable): Page<Teacher> {
        return teacherRepository.findAll(pageable)
    }

    @Throws(UsernameNotFoundException::class)
    override fun loadUserByUsername(username: String): UserDetails {
        val optionalUser = teacherRepository.findByName(username)
        return optionalUser?.let {user ->
            val simpleGrantedAuthorities = user.roles.map {
                SimpleGrantedAuthority(it.name)
            }

            OtherUser(user.name, user.passwordHash, simpleGrantedAuthorities)
        } ?: throw UsernameNotFoundException("no such user")
    }
}