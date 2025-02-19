package dio.rest_api.model;

public class Usuario {

    @Override
    public String toString() {
        return "Usuario [login=" + login + ", password=" + password + "]";
    }
    
    private Long id;
    private String login;
    private String password;

    public Usuario(String login, String password) {
        this.login = login;
        this.password = password;
    }

    public Usuario() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }
    
}
