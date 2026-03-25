import java.time.LocalDateTime;

public class Evento {
    String nomeDoEvento;
    String endereco;
    String categoria;
    LocalDateTime horario;
    String descricao;

    public Evento(String nomeDoEvento, String endereco, String categoria, LocalDateTime horario, String descricao) {
        this.nomeDoEvento = nomeDoEvento;
        this.endereco = endereco;
        this.categoria = categoria;
        this.horario = horario;
        this.descricao = descricao;
    }
}

