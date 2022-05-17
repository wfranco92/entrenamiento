package co.com.sofkau.entrenamiento.curso.values;

import co.com.sofka.domain.generic.ValueObject;

import java.util.Objects;

public class Directiz implements ValueObject<String> {

    // se sobreescribe el metodo value para retornar el value del objeto, asi como el constructor directriz
    private final String value;

    public Directiz(String value) {
        this.value = Objects.requireNonNull(value);
        if(this.value.isBlank()){
            throw new IllegalArgumentException("La Directriz no puede estar en blanco");
        }
    }

    @Override
    public String value() {
        return value;
    }
}
