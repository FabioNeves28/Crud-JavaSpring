
package model;

import jakarta.persistence.*;
import lombok.*;
@Entity
@Table(name="Livros")
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
public class Livro {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    private String title;

    @Column
    private String author;

}
