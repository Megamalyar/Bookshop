package model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Malyar on 04.04.2015.
 */
@Entity
@Table(name = "category_table")
public class Category implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "category_id")
    private Long categoryId;

    @Column(name = "category_name", nullable = false)
    private String categoryName;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.LAZY)
    @JoinTable(name = "book_category",
            joinColumns = @JoinColumn(name = "category_id"),
            inverseJoinColumns = @JoinColumn(name = "book_id"))
    @OrderBy(value="bookTitle")
    private Set<Book> bookList;

    public Category() {}

    public Set<Book> getBookList() {
        return bookList;
    }

    public void setBookList(Set<Book> bookList) {
        this.bookList = bookList;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public void addBook(Book book) {
        if (bookList == null) {
            bookList = new HashSet<Book>();
        }
        bookList.add(book);
    }

    public String getBookString() {
        if (bookList != null && !bookList.isEmpty()) {
            String result = "";
            for (Book book : bookList) {
                result += book.getBookTitle() + ", ";
            }
            return result;
        } else {
            return "no books";
        }
    }

    @Override
    public String toString() {
        return "Category{" +
                "bookList=" + bookList +
                ", categoryId=" + categoryId +
                ", categoryName='" + categoryName + '\'' +
                '}';
    }
}
