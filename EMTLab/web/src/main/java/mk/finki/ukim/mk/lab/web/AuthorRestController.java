package mk.finki.ukim.mk.lab.web;

import mk.finki.ukim.mk.lab.model.Author;
import mk.finki.ukim.mk.lab.model.dto.AuthorDto;
import mk.finki.ukim.mk.lab.service.AuthorService;
import mk.finki.ukim.mk.lab.service.CountryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api/authors")
public class AuthorRestController {
    private final AuthorService authorService;
    private final CountryService countryService;

    public AuthorRestController(AuthorService authorService, CountryService countryService) {
        this.authorService = authorService;
        this.countryService = countryService;
    }

    @GetMapping
    private List<Author> findAll(){
        return this.authorService.listAllAuthors();
    }
    @GetMapping("/{id}")
    public ResponseEntity<Author> findById(@PathVariable Long id) {
        return this.authorService.findById(id)
                .map(author -> ResponseEntity.ok().body(author))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<Author> save(@PathVariable Long id, @RequestBody AuthorDto authorDto) {
        return this.authorService.edit(id, authorDto)
                .map(author -> ResponseEntity.ok().body(author))
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Author> deleteById(@PathVariable Long id) {
        if(this.authorService.findById(id).isEmpty()) return ResponseEntity.notFound().build();
        this.authorService.deleteById(id);
        return ResponseEntity.ok().build();
    }

}
