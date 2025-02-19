package dio.rest_api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dio.rest_api.model.Usuario;
import dio.rest_api.repository.UsuarioRepository;

@RestController
@RequestMapping("/usuarios")
// @PreAuthorize("hasRole('ADMIN')") // ISSO DEFINE QUE ESTE CONTROLLER SÓ SERA ACESSIVEL AOS USUARIOS DE ROLE ADMIN
public class UsuarioController {
    @Autowired
    private UsuarioRepository repository;

    @PostMapping("/save")
    public void post(@RequestBody Usuario usuario){
        repository.save(usuario);
    }
    @PutMapping("/update")
    public void put(@RequestBody Usuario usuario){
        repository.update(usuario);
    }

    @GetMapping("/findAll")
    public List<Usuario> getAll(){
        return repository.listAll();
    }

    @GetMapping("/findById/{id}")
    public Usuario getOne(@PathVariable("id") Long id){
        return repository.finById(id);
    }
    @DeleteMapping("/usuarios/{id}")
    public void delete(@PathVariable("id") Integer id){
        repository.remove(id);
    }
}
