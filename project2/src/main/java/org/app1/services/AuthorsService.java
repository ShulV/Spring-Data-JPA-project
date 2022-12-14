package org.app1.services;

import org.app1.dao.AuthorDAO;
import org.app1.dao.BookDAO;
import org.app1.models.Author;
import org.app1.models.Book;
import org.app1.repositories.AuthorsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.app1.util.StringParser.parseStrNumSeqToList;

@Service
@Transactional(readOnly = true)
public class AuthorsService {

    private final AuthorsRepository authorsRepository;
    private final AuthorDAO authorDAO;

    @Autowired
    public AuthorsService(AuthorsRepository authorsRepository, AuthorDAO authorDAO) {
        this.authorsRepository = authorsRepository;
        this.authorDAO = authorDAO;
    }

    public List<Author> getAllAuthors() {
        return authorsRepository.findAll();
    }

    public Optional<Author> getAuthorById(int id) {
       return authorsRepository.findById(id);
    }

    @Transactional
    public void unlinkBookFromAuthors(Book book) {
        List<Author> authors = authorDAO.getAuthorsByBook(book);
        for (Author a: authors
             ) {
            a.removeBook(book);
        }
//        authorsRepository.saveAll(authors);
    }

    public Optional<Author> findById(int id) {
        return authorsRepository.findById(id);
    }

    public void saveAuthor(Author author) {
        authorsRepository.save(author);
    }

    public void deleteById(int id) {
        authorsRepository.deleteById(id);
    }

    public void updateAuthor(int id, Author updatedAuthor) {
        updatedAuthor.setId(id);
        authorsRepository.save(updatedAuthor);
    }

}
