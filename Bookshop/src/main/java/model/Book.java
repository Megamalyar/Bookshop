package model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Malyar on 04.04.2015.
 */
@Entity
@Table(name = "book_table")
public class Book implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "book_id")
    private Long bookId;

    @Column(name = "book_title")
    private String bookTitle;

    @Column(name = "book_price")
    private Double bookPrice;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.LAZY)
    @JoinTable(name = "book_author",
            joinColumns = @JoinColumn(name = "book_id"),
            inverseJoinColumns = @JoinColumn(name = "author_id"))
    @OrderBy(value="authorName")
    private Set<Author> authorList;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.LAZY)
    @JoinTable(name = "book_category",
            joinColumns = @JoinColumn(name = "book_id"),
            inverseJoinColumns = @JoinColumn(name = "category_id"))
    @OrderBy(value="categoryName")
    private Set<Category> categoryList;

    public Book() {}

    public Set<Author> getAuthorList() {
        return authorList;
    }

    public void setAuthorList(Set<Author> authorList) {
        this.authorList = authorList;
    }

    public Long getBookId() {
        return bookId;
    }

    public void setBookId(Long bookId) {
        this.bookId = bookId;
    }

    public String getBookTitle() {
        return bookTitle;
    }

    public void setBookTitle(String bookTitle) {
        this.bookTitle = bookTitle;
    }

    public Double getBookPrice() {
        return bookPrice;
    }

    public void setBookPrice(Double bookPrice) {
        this.bookPrice = bookPrice;
    }

    public Set<Category> getCategoryList() {
        return categoryList;
    }

    public void setCategoryList(Set<Category> categoryList) {
        this.categoryList = categoryList;
    }

    public void addAuthor(Author author) {
        if (authorList == null) {
            authorList = new HashSet<Author>();
        }
        authorList.add(author);
    }

    public void addCategory(Category category) {
        if (categoryList == null) {
            categoryList = new HashSet<Category>();
        }
        categoryList.add(category);
    }

    @Override
    public String toString() {
        return "Book{" +
                "authorList=" + authorList +
                ", bookId=" + bookId +
                ", bookTitle='" + bookTitle + '\'' +
                ", bookPrice=" + bookPrice +
                ", categoryList=" + categoryList +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Book)) return false;

        Book book = (Book) o;

        if (!bookId.equals(book.bookId)) return false;

        return true;
    }

    public String getAuthorString() {
        if (authorList != null && !authorList.isEmpty()) {
            String result = "";
            for (Author author : authorList) {
                result += author.getAuthorName() + ", ";
            }
            return result;
        } else {
            return "no authors";
        }
    }

    public String getCategoryString() {
        if (categoryList != null && !categoryList.isEmpty()) {
            String result = "";
            for (Category category : categoryList) {
                result += category.getCategoryName() + ", ";
            }
            return result;
        } else {
            return "no categories";
        }
    }

    @Override
    public int hashCode() {
        return bookId.hashCode();
    }
}
