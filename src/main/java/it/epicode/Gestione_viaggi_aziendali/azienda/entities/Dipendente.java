package it.epicode.Gestione_viaggi_aziendali.azienda.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@Table(name = "dipendenti")
public class Dipendente {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long dipendenteId;

    @Column(nullable = false,  unique = true,  length = 50)
    private String username;

    @Column(nullable = false, length = 50)
    private String nome;

    @Column(name = "avatar_url", nullable = false)
    private String avatarUrl;

    @Column(nullable = false, length = 50)
    private String cognome;

    @Email
    @Column(nullable = false, length = 50, unique = true)
    private String email;

    public Dipendente(String username, String nome, String cognome, String email) {
        this.username = username;
        this.nome = nome;
        this.cognome = cognome;
        this.email = email;
    }

    @Override
    public String toString() {
        return "Dipendente: " + dipendenteId + '\n' +
                "Username:  " + username + '\n' +
                "Nome: " + nome + '\n' +
                "Cognome: " + cognome + '\n' +
                "Email: " + email;
    }
}
