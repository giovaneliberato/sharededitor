package controller;

import model.Usuario;

public class LoggedUser {
    private static LoggedUser instance;
    private Usuario loggedUser = null;
    
    private LoggedUser(){}
    
    public static LoggedUser getInstance(){
        if (instance == null){
            instance = new LoggedUser();
        }
        return instance;
    }
    
    public void setLoggedUser(Usuario u){
        if (instance == null){
            instance = new LoggedUser();
        }
        loggedUser = u;
    }
    
    public Usuario getLoggedUser(){
        return loggedUser;
    }
    
}
