package mk.finki.ukim.mk.lab.config;

import jakarta.annotation.PostConstruct;
import mk.finki.ukim.mk.lab.model.Author;
import mk.finki.ukim.mk.lab.model.Book;
import mk.finki.ukim.mk.lab.model.Country;
import mk.finki.ukim.mk.lab.model.enumeration.BookCategory;
import mk.finki.ukim.mk.lab.repository.AuthorRepository;
import mk.finki.ukim.mk.lab.repository.BookRepository;
import mk.finki.ukim.mk.lab.repository.CountryRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Locale;

@Component
public class DataInitializier {

    private final CountryRepository countryRepository;
    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;

    public DataInitializier(CountryRepository countryRepository, AuthorRepository authorRepository, BookRepository bookRepository) {
        this.countryRepository = countryRepository;
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
    }
    @PostConstruct
    public void init(){
        for (int i=1; i<=10; i++){
            Country country = new Country();
            country.setName(String.format("Country %d", i));
            country.setContinent(String.format("Continent %d", i));
            countryRepository.save(country);

            Author author = new Author();
            author.setName(String.format("Name %d", i));
            author.setSurname(String.format("Surname %d", i));
            author.setCountry(country);
            authorRepository.save(author);

            Book book = new Book();
            book.setName(String.format("Name %d", i));
            book.setAuthor(author);
            book.setCategory(BookCategory.values()[i % BookCategory.values().length]);
            book.setAvailableCopies(i);
            bookRepository.save(book);

        }
    }
}
