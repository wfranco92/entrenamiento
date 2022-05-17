package co.com.sofkau.entrenamiento.curso.values;

import co.com.sofka.domain.generic.Identity;

public class MentoriaId extends Identity {

    // se crea el metodo constructor y get para el identificador
    public static MentoriaId of(String id) {
        return new MentoriaId(id);
    }
    public MentoriaId(String id){
        super(id);
    }
    public MentoriaId(){
    }

}
