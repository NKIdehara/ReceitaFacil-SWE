package br.edu.infnet.ReceitaFacil.repository;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import br.edu.infnet.ReceitaFacil.model.Receita;

@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class ReceitaRepositoryTest {
    
    @Autowired
    private ReceitaRepository receitaRepository;

    @Test
    public void ReceitaFacil_InclusaoDeReceita_True() {
        // Arrange
        // Receita receita = Receita.builder().nome("Teste 1").preparo("Preparo 1").dataReceita(Date.valueOf(LocalDate.now())).build();
        Receita receita = new Receita();
        receita.setNome("Teste 1");
        receita.setPreparo("Preparo 1");
        receita.setDataReceita(Date.valueOf(LocalDate.now()));

        // Act
        Receita testReceita = receitaRepository.save(receita);

        // Assert
        Assertions.assertThat(testReceita).isNotNull();
    }

    @Test
    public void ReceitaFacil_RetornaReceitasCadastradas_2() {
        // Arrange
        Receita receita1 = new Receita();
        receita1.setNome("Teste 1");
        receita1.setPreparo("Preparo 1");
        receita1.setDataReceita(Date.valueOf(LocalDate.now()));

        Receita receita2 = new Receita();
        receita2.setNome("Teste 2");
        receita2.setPreparo("Preparo 2");
        receita2.setDataReceita(Date.valueOf(LocalDate.now()));

        receitaRepository.save(receita1);
        receitaRepository.save(receita2);

        // Act
        List<Receita> receitas = receitaRepository.findAll();

        // Assert
        Assertions.assertThat(receitas.size()).isEqualTo(2);
    }
}
