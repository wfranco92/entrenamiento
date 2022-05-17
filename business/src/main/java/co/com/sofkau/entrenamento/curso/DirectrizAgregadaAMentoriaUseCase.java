package co.com.sofkau.entrenamento.curso;

import co.com.sofka.business.generic.UseCase;
import co.com.sofka.business.support.RequestCommand;
import co.com.sofka.business.support.ResponseEvents;
import co.com.sofkau.entrenamiento.curso.Curso;
import co.com.sofkau.entrenamiento.curso.commands.AgregarDirectrizAMentoria;

public class DirectrizAgregadaAMentoriaUseCase extends UseCase<RequestCommand<AgregarDirectrizAMentoria>, ResponseEvents> {

    @Override
    public void executeUseCase(RequestCommand<AgregarDirectrizAMentoria> agregarDirectrizAMentoriaRequestCommand) {

        //se trae el comando de agregarDirectrizAMentoriaRequestCommand
        var command = agregarDirectrizAMentoriaRequestCommand.getCommand();

        // se trae la informacion de un curso (agregado root) de acuerdo al command para poder ejecutar el comportamiento agregarDirectrizDeMentoria
        var curso = Curso.from(
                command.getCursoId(), repository().getEventsBy(command.getCursoId().value())
        );
        curso.agregarDirectrizDeMentoria(command.getMentoriaId(), command.getDirectiz());

        emit().onResponse(new ResponseEvents(curso.getUncommittedChanges()));

    }
}
