public class Usuario {
    private String tema;
    private boolean notificacoes;
    private int volume;

    public Usuario(String tema, boolean notificacoes, int volume) {
        this.tema = tema;
        this.notificacoes = notificacoes;
        this.volume = volume;
    }

    public String getTema() { return tema; }
    public boolean isNotificacoes() { return notificacoes; }
    public int getVolume() { return volume; }

    @Override
    public String toString() {
        return "Tema: " + tema + 
               " | Notificações: " + (notificacoes ? "Ativadas" : "Desativadas") + 
               " | Volume: " + volume;
    }
}
