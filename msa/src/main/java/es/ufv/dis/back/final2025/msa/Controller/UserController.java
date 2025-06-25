package es.ufv.dis.back.final2025.msa.Controller;

import es.ufv.dis.back.final2025.msa.Model.Usuario;
import es.ufv.dis.back.final2025.msa.Service.PdfService;
import es.ufv.dis.back.final2025.msa.Service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/usuarios")
public class UserController {
    private final UserService userService;
    private final PdfService pdfService;

    public UserController(UserService userService, PdfService pdfService) {
        this.userService = userService;
        this.pdfService = pdfService;
    }

    @GetMapping
    public List<Usuario> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Usuario> getUserById(@PathVariable String id) {
        Optional<Usuario> usuario = userService.getUserById(id);
        return usuario.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Usuario createUser(@RequestBody Usuario usuario) {
        userService.saveUser(usuario);
        return usuario;
    }

    @PutMapping("/{id}")
    public ResponseEntity<Usuario> updateUser(@PathVariable String id, @RequestBody Usuario updatedUser) {
        userService.updateUser(id, updatedUser);
        return ResponseEntity.ok(updatedUser);
    }

    @GetMapping("/generar-pdf")
    public String generatePdf() {
        pdfService.generateUsersPdf(userService.getAllUsers(), "info.pdf"); // Nombre fijo exigido
        return "PDF generado correctamente";
    }
}