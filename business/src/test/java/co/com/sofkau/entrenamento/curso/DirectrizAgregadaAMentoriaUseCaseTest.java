package co.com.sofkau.entrenamento.curso;

import co.com.sofka.business.generic.UseCaseHandler;
import co.com.sofka.business.repository.DomainEventRepository;
import co.com.sofka.business.support.RequestCommand;
import co.com.sofka.domain.generic.DomainEvent;
import co.com.sofkau.entrenamiento.curso.commands.AgregarDirectrizAMentoria;
import co.com.sofkau.entrenamiento.curso.events.CursoCreado;
import co.com.sofkau.entrenamiento.curso.events.DirectrizAgregadaAMentoria;
import co.com.sofkau.entrenamiento.curso.events.MentoriaCreada;
import co.com.sofkau.entrenamiento.curso.values.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class DirectrizAgregadaAMentoriaUseCaseTest {

    @InjectMocks
    private DirectrizAgregadaAMentoriaUseCase useCase;

    @Mock
    private DomainEventRepository repository;

    @Test
    public void agregarDirectrizAMentoriaHappyPass(){
        //arrange
        CursoId coursoId = CursoId.of("ddddd");
        MentoriaId mentoriaId = MentoriaId.of("qwerty");
        Directiz directriz = new Directiz("Hacer test");
        var command = new AgregarDirectrizAMentoria( coursoId, mentoriaId, directriz);

        when(repository.getEventsBy("ddddd")).thenReturn(history());
        useCase.addRepository(repository);
        //act

        var events = UseCaseHandler.getInstance()
                .setIdentifyExecutor(command.getMentoriaId().value())
                .syncExecutor(useCase, new RequestCommand<>(command))
                .orElseThrow()
                .getDomainEvents();

        //assert
        var event = (DirectrizAgregadaAMentoria)events.get(0);
        Assertions.assertEquals("Hacer test", event.getDirectiz().value());


    }
    private List<DomainEvent> history() {

        // se crea el historial de creacion de un curso
        Nombre nombre = new Nombre("DDD");
        Descripcion descripcion = new Descripcion("Curso complementario para el training");
        var event = new CursoCreado(
                nombre,
                descripcion
        );
        event.setAggregateRootId("xxxxx");

        // se crea el historial de creacion de una mentoria
        MentoriaId mentoriaId = MentoriaId.of("qwerty");
        Nombre nombreM = new Nombre("EEE");
        Fecha fecha = new Fecha(LocalDateTime.now(), LocalDate.now());
        var event2 = new MentoriaCreada(mentoriaId, nombreM, fecha);

        // se retornan ambos eventos del historial
        return List.of(event, event2);
    }


}